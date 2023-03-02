package com.yelan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yelan.domain.entity.Tag;
import com.yelan.mapper.TagMapper;
import com.yelan.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 18:30:30
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
