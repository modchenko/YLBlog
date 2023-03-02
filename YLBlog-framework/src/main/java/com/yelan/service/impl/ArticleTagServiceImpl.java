package com.yelan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yelan.domain.entity.ArticleTag;
import com.yelan.mapper.ArticleTagMapper;
import com.yelan.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 18:29:39
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
