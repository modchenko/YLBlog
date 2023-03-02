package com.yelan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yelan.domain.dto.AddArticleDto;
import com.yelan.domain.dto.ArticleDto;
import com.yelan.domain.entity.Article;
import com.yelan.domain.result.ResponseResult;


/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 18:29:19
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult updateViewCount(Long id);

    ResponseResult getArticleDetail(Long id);

    ResponseResult add(AddArticleDto article);

    ResponseResult selectArticlePage(Article article, Integer pageNum, Integer pageSize);

    ResponseResult getInfo(Long id);

    ResponseResult edit(ArticleDto article);
}
