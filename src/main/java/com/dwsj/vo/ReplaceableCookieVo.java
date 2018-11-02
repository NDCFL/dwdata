package com.dwsj.vo;

/**
 * 描述:tb_replaceable_cookie表的实体类
 * @version
 * @author:  win7 虚拟机
 * @创建时间: 2018-05-10
 */
public class ReplaceableCookieVo {
    private Long id;//凭证云、今收到等需要用到登录状态的cookie

    private String name;//平台名

    private String cookie;

    private String remark;//备注

    public Long getId() {
        return id;
    }

    /**
     * 凭证云、今收到等需要用到登录状态的cookie
     * @param id 凭证云、今收到等需要用到登录状态的cookie
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 平台名
     * @return name 平台名
     */
    public String getName() {
        return name;
    }

    /**
     * 平台名
     * @param name 平台名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return cookie 
     */
    public String getCookie() {
        return cookie;
    }

    /**
     * 
     * @param cookie 
     */
    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}