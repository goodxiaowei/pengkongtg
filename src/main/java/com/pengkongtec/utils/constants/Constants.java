package com.pengkongtec.utils.constants;

/**
 * @author: hxy
 * @description: 通用常量类, 单个业务的常量请单开一个类, 方便常量的分类管理
 * @date: 2017/10/24 10:15
 */
public class Constants {

    public static final String SUCCESS_CODE = "100";
    public static final String SUCCESS_MSG = "请求成功";
    
    public static final String FAIL_CODE = "500";
    public static final String FAIL_MSG = "请求失败";


    /**
     * session中存放用户信息的key值
     */
    public static final String SESSION_USER_INFO = "userInfo";
    public static final String SESSION_USER_PERMISSION = "userPermission";
    
    /**
     * 任务状态
     */
    public static final String TASK_DRAFT = "DRAFT";// 草稿
    public static final String TASK_APPROVALING = "APPROVALING";// 审批中
    public static final String TASK_APPROVALED = "APPROVALED";// 审批通过
    public static final String TASK_REJECT = "REJECT";// 驳回
    public static final String TASK_WITHDRAW = "WITHDRAW";// 撤回
    
    /**
     * 数据状态/返回值请求状态
     */
    public static final String STATUS_SUCCESS = "1";// 有效
    public static final String STATUS_FAIL = "2";// 无效
    
    /**
     * 分页
     */
    public static final Integer PAGENO_ZERO = 0;
    public static final Integer PAGESIZE_TEN = 10;
}
