package com.yelan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yelan.domain.entity.Link;
import com.yelan.domain.result.ResponseResult;
import com.yelan.domain.vo.PageVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 18:30:05
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}
