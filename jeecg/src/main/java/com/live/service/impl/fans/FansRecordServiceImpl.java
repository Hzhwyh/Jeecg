package com.live.service.impl.fans;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.live.service.fans.FansRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("fansRecordService")
@Transactional
public class FansRecordServiceImpl extends CommonServiceImpl implements FansRecordServiceI {
	
}