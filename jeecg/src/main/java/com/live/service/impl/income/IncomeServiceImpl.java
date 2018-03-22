package com.live.service.impl.income;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.live.service.income.IncomeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("incomeService")
@Transactional
public class IncomeServiceImpl extends CommonServiceImpl implements IncomeServiceI {
	
}