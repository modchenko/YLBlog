package com.yelan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yelan.constant.SystemConstant;
import com.yelan.domain.dto.AddArticleDto;
import com.yelan.domain.dto.ArticleDto;
import com.yelan.domain.entity.Article;
import com.yelan.domain.entity.ArticleTag;
import com.yelan.domain.entity.Category;
import com.yelan.domain.result.ResponseResult;
import com.yelan.domain.vo.*;
import com.yelan.mapper.ArticleMapper;
import com.yelan.service.ArticleService;
import com.yelan.service.ArticleTagService;
import com.yelan.service.CategoryService;
import com.yelan.util.BeanCopyUtil;
import com.yelan.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 18:29:19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;
    @Override
    public ResponseResult hotArticleList() {
        //查询并排序
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getIsDeleted, SystemConstant.ARTICLE_STATUS_NORMAL);
        lambdaQueryWrapper.eq(Article::getIsPublished,SystemConstant.ARTICLE_STATUS_PUBLISHED);
        lambdaQueryWrapper.orderByDesc(Article::getViewCount);
        //封装进page,10条
        Page<Article> page=new Page(1,10);
        page(page,lambdaQueryWrapper);
        //再从page拿出来，放进list
        List<Article> list=page.getRecords();
        List<HotArticleVo> hotArticleVoList= BeanCopyUtil.copyBeanList(list,HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVoList);

    }
    //TODO 这个可以改成连表查询

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //传入了categoryid
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        lambdaQueryWrapper.eq(Article::getIsPublished,SystemConstant.ARTICLE_STATUS_PUBLISHED);
        lambdaQueryWrapper.eq(Article::getIsDeleted,SystemConstant.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        Page<Article> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        List<Article> articles=page.getRecords();
        articles.stream()
                .map(article -> {article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                    return article;})
                .collect(Collectors.toList());
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtil.copyBeanList(page.getRecords(), ArticleListVo.class);




        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();

    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        Article article=getById(id);
        //实时点赞数在Redis
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());

        //转换为Vo
        ArticleDetailVo articleDetailVo=BeanCopyUtil.copyBean(article,ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult add(AddArticleDto articledto) {

        Article article=BeanCopyUtil.copyBean(articledto,Article.class);
        save(article);

        List<ArticleTag> articleTags=articledto.getTags()
                .stream()
                .map(longId->new ArticleTag(articledto.getId(),longId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();


    }

    @Override
    public ResponseResult selectArticlePage(Article article, Integer pageNum, Integer pageSize) {
        Page<Article> page=new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper=new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.like(StringUtils.hasText(article.getTitle()),Article::getTitle, article.getTitle());
        articleLambdaQueryWrapper.like(StringUtils.hasText(article.getSummary()),Article::getSummary, article.getSummary());
        page(page,articleLambdaQueryWrapper);
        List<Article> articleList=page.getRecords();
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtil.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo=new PageVo();
        pageVo.setRows(articleListVos);
        pageVo.setTotal(page.getTotal());

        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult getInfo(Long id) {
        Article article = getById(id);
        //获取关联标签
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        List<ArticleTag> articleTags = articleTagService.list(articleTagLambdaQueryWrapper);
        List<Long> tagsLongList=articleTags.stream().map(articleTag->articleTag.getTagId()).collect(Collectors.toList());
        ArticleVo articleVo=BeanCopyUtil.copyBean(article,ArticleVo.class);
        articleVo.setTags(tagsLongList);
        return ResponseResult.okResult(articleVo);
    }

    @Override
    public ResponseResult edit(ArticleDto articleDto) {
        Article article = BeanCopyUtil.copyBean(articleDto, Article.class);
        //更新博客信息
        updateById(article);
        //删除原有的 标签和博客的关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);
        //添加新的博客和标签的关联信息
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(articleDto.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }
}
