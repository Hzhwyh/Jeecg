package com.live.service.impl.ruls;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.live.service.ruls.RulsServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("rulsService")
@Transactional
public class RulsServiceImpl extends CommonServiceImpl implements RulsServiceI {

    @Override
    public void InvalidAll(String orgid) {
        String updateSql = "update a_ruls set state=1 where orgid=?";
        this.executeSql(updateSql,orgid);
    }
}