package com.dwsj.service;

import com.dwsj.vo.QQPhoneVo;

public interface QQPhoneService extends BaseService<QQPhoneVo>{
    QQPhoneVo getInfoByQQ(String qq);
    QQPhoneVo getInfoByHash(String hash);
    QQPhoneVo getInfo(QQPhoneVo qqPhoneVo);
}
