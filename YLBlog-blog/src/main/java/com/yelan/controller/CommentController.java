package com.yelan.controller;


import com.yelan.constant.SystemConstant;
import com.yelan.domain.dto.AddCommentDto;
import com.yelan.domain.entity.Comment;
import com.yelan.domain.result.ResponseResult;
import com.yelan.service.CommentService;
import com.yelan.util.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstant.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
        Comment comment = BeanCopyUtil.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }


    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "pageNum",value = "页号"),
           @ApiImplicitParam(name = "pageSize",value = "每页大小")
    }
    )
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstant.LINK_COMMENT,null,pageNum,pageSize);
    }
}
