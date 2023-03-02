package com.yelan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yelan.domain.entity.Comment;
import com.yelan.domain.result.ResponseResult;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 18:29:57
 */
public interface CommentService extends IService<Comment> {
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);

}
