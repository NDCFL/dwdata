package com.dwsj.service;


import com.dwsj.common.StatusQuery;
import com.dwsj.vo.Verifcode;
import org.apache.ibatis.annotations.Param;

public interface VerifcodeService extends BaseService<Verifcode>{
    String queryByCode(String mobile);
    void updateCodeStatus(StatusQuery statusQuery);
    Verifcode getVerifcode(String mobile);
    Integer cnt(String mobile ,String ip);
}