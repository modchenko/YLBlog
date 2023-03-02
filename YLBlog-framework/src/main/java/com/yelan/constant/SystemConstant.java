package com.yelan.constant;

/**
 * @ClassName: SystemConstant
 * @Description:
 * @Author: ChenKo
 * @Date: 2023/3/1
 */
public class SystemConstant {
    /**
     *  文章是草稿
     */
    public static final String  ARTICLE_STATUS_DRAFT = "1";
    /**
     *  文章是正常分布状态,不是草稿
     */
    public static final String  ARTICLE_STATUS_NORMAL = "0";

    public static final String  ARTICLE_STATUS_PUBLISHED = "1";


    public static final String  STATUS_NORMAL = "0";
    /**
     * 友链状态为审核通过
     */
    public static final String  LINK_STATUS_NORMAL = "0";
    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";
    /**
     * 根评论
     */
    public static final String ROOT_COMMENT = "-1";
    public static final String MENU = "C";
    public static final String BUTTON = "F";
    /** 正常状态 */
    public static final String NORMAL = "0";
    public static final String ADMAIN = "1";
}
