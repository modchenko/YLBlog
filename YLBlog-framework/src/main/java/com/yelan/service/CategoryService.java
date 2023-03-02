package com.yelan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yelan.domain.entity.Category;
import com.yelan.domain.result.ResponseResult;
import com.yelan.domain.vo.CategoryVo;
import com.yelan.domain.vo.PageVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 18:29:49
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}
