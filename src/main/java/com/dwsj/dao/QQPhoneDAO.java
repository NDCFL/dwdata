package com.dwsj.dao;

import com.dwsj.vo.QQPhoneVo;
import org.springframework.stereotype.Repository;

@Repository
public interface QQPhoneDAO extends BaseDAO<QQPhoneVo>{
    QQPhoneVo getInfoByQQ(String qq);
    QQPhoneVo getInfoByHash(String hash);
    QQPhoneVo getInfo(QQPhoneVo qqPhoneVo);
}
