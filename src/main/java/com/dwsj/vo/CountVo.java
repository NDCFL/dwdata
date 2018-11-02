package com.dwsj.vo;

public class CountVo {
    private Long allCount;//全部客户
    private Long gzCount;//已关注客户
    private Long hzCount;//已合作客户
    private Long whzCount;//未合作客户
    private Integer jifen;//客户的总积分

    public Long getAllCount() {
        return allCount;
    }

    public void setAllCount(Long allCount) {
        this.allCount = allCount;
    }

    public Long getGzCount() {
        return gzCount;
    }

    public void setGzCount(Long gzCount) {
        this.gzCount = gzCount;
    }

    public Long getHzCount() {
        return hzCount;
    }

    public void setHzCount(Long hzCount) {
        this.hzCount = hzCount;
    }

    public Long getWhzCount() {
        return whzCount;
    }

    public void setWhzCount(Long whzCount) {
        this.whzCount = whzCount;
    }

    public Integer getJifen() {
        return jifen;
    }

    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }
}
