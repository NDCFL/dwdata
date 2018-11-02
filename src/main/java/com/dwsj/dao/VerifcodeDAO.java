package com.dwsj.dao;

import com.dwsj.common.StatusQuery;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.Verifcode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by aQiu.
 */
@Repository
public interface VerifcodeDAO extends  BaseDAO<Verifcode> {
    String queryByCode(String mobile);
    void updateCodeStatus(StatusQuery statusQuery);
    Verifcode getVerifcode(String mobile);
    Integer cnt(@Param("mobile") String mobile ,@Param("ip") String ip);
}