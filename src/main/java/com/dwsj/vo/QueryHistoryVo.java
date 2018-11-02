package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:tb_query_history表的实体类
 * @version
 * @author:  YuDong
 * @创建时间: 2018-06-23
 */
public class QueryHistoryVo implements Serializable{
    private Long id;//用户查询记录

    private Date createTime;//查询时间

    private Long pcUserId;//用户id

    private Long kehuId;//借款人id

    private Integer type;//tb_fixed_price表id，关联查询类型,为1则表示本次查询为“信贷高级查询”

    private Long typeCorrespondId;//type所对应的id。例：若type为0，则本字段的值则为运营商表的id，根据此id去查询运营商的数据

    public Long getId() {
        return id;
    }

    /**
     * 用户查询记录
     * @param id 用户查询记录
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 查询时间
     * @return create_date 查询时间
     */

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Long getPcUserId() {
        return pcUserId;
    }

    public void setPcUserId(Long pcUserId) {
        this.pcUserId = pcUserId;
    }

    public Long getKehuId() {
        return kehuId;
    }

    public void setKehuId(Long kehuId) {
        this.kehuId = kehuId;
    }

    /**
     * 类型，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     * @return type 类型，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     * @param type 类型，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * type所对应的id，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     * @return type_correspond_id type所对应的id，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     */
    public Long getTypeCorrespondId() {
        return typeCorrespondId;
    }

    /**
     * type所对应的id，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     * @param typeCorrespondId type所对应的id，0运营商,1淘宝,2支付宝,3京东,4QQ通讯录,5学信网,6手机号码定位,7手机号二要素,8身份证二要素
     */
    public void setTypeCorrespondId(Long typeCorrespondId) {
        this.typeCorrespondId = typeCorrespondId;
    }
}