package com.live.service.impl.live;

import com.live.entity.live.LiveRecordEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.live.service.live.LiveRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import java.util.List;

@Service("liveRecordService")
@Transactional
public class LiveRecordServiceImpl extends CommonServiceImpl implements LiveRecordServiceI {

    @Override
    public LiveRecordEntity getByArtistId(String id) {
//        String sql = "select id,artistid,starttime,endtime,minute,state from a_liverecord where artistid='"+id+"' and state=0";
        String hql = "from LiveRecordEntity where artistid ='"+id+"' and state =0 ";
        List<LiveRecordEntity> list = this.findByQueryString(hql);
        if(list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}