package com.live.service.live;

import com.live.entity.live.LiveRecordEntity;
import org.jeecgframework.core.common.service.CommonService;

public interface LiveRecordServiceI extends CommonService{

    LiveRecordEntity getByArtistId(String id);
}
