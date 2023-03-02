package com.yelan.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: BeanCopyUtil
 * @Description:
 * @Author: ChenKo
 * @Date: 2023/3/1
 */
public class BeanCopyUtil {

    private BeanCopyUtil() {
    }

    ;

    /**
     * 单个复制
     */
    public static <T> T copyBean(Object source, Class<T> clazz) {
        //创建返回的目标对象
        T result = null;
        try {
            result = clazz.newInstance();
            //复制属性
            BeanUtils.copyProperties(source, result);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;

    }
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                //map()将已有元素转换为另一个对象类型，一对一逻辑，返回新的stream流
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
