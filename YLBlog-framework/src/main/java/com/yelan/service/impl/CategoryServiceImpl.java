package com.yelan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yelan.constant.SystemConstant;
import com.yelan.domain.entity.Article;
import com.yelan.domain.entity.Category;
import com.yelan.domain.result.ResponseResult;
import com.yelan.domain.vo.CategoryVo;
import com.yelan.domain.vo.PageVo;
import com.yelan.mapper.CategoryMapper;
import com.yelan.service.ArticleService;
import com.yelan.service.CategoryService;
import com.yelan.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 18:29:49
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getIsPublished, SystemConstant.ARTICLE_STATUS_PUBLISHED);
        List<Article> articleList=articleService.list(lambdaQueryWrapper);
        Set<Long> categorySet=articleList.stream().map(article -> article.getCategoryId()).collect(Collectors.toSet());

        List<Category> categoryList=listByIds(categorySet);
        categoryList=categoryList.stream()
                .filter(category -> SystemConstant.LINK_STATUS_NORMAL.equals(category.getIsForbidden()))
                .collect(Collectors.toList());
        List<CategoryVo> categoryVoList= BeanCopyUtil.copyBeanList(categoryList,CategoryVo.class);
        return ResponseResult.okResult(categoryList);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getIsForbidden, SystemConstant.ARTICLE_STATUS_NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }

    @Override
    public PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.hasText(category.getName()),Category::getName, category.getName());
        queryWrapper.eq(Objects.nonNull(category.getIsForbidden()),Category::getIsForbidden, category.getIsForbidden());

        Page<Category> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        //转换成VO
        List<Category> categories = page.getRecords();

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(categories);
        return pageVo;
    }
}
