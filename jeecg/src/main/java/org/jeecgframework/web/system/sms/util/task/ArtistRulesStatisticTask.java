package org.jeecgframework.web.system.sms.util.task;

import com.live.entity.artist.ArtistEntity;
import com.live.entity.ruls.RulsEntity;
import com.live.service.artist.ArtistServiceI;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName:SmsSendTask 所有信息的发送定时任务类
 * @Description: TODO
 * @author Comsys-skyCc cmzcheng@gmail.com
 * @date 2014-11-13 下午5:06:34
 * 
 */

@Service("artistRulesStatisticTask")
public class ArtistRulesStatisticTask implements Job{
	
	@Autowired
	private TSSmsServiceI tSSmsService;
	@Autowired
	private ArtistServiceI artistServiceI;
	
	@Scheduled(cron="0 0 23 * * ?")
	public void run() {
		//每日判断该艺人的直播时间是否超过艺人家族的标准时间，若超出标准时间则艺人的达标天数+1，（group by直播记录表的艺人ID sum累计分钟）
		List<ArtistEntity> artistlist = artistServiceI.findByQueryString("from ArtistEntity where state=0");
		for(ArtistEntity a:artistlist){
			String orgid = a.getOrgid();	//家族id
			double standard =0.0;	//达标小时数
			double livehours = 0.0;	//当天直播小时数
			List<RulsEntity> ruls = artistServiceI.findByQueryString("from RulsEntity where state=0 and orgid="+orgid);
			if(ruls.size()>0){
				standard = ruls.get(0).getStandard();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			List<Map<String,Object>> list = artistServiceI.findForJdbc("select sum(minute) totalmin,SUBSTR(starttime,1,10) date from a_liverecord where artistid='"+artistlist+"' and SUBSTR(starttime,1,10)='"+date+"' group by date ");
			if(list!=null && list.size()>0) {
				livehours = new BigDecimal((String) list.get(0).get("totalmin")).doubleValue();

				if(livehours>standard){
					int standardays = a.getStandarddays()==null?0:a.getStandarddays();
					a.setStandarddays(standardays+1);	//达标天数+1

					artistServiceI.saveOrUpdate(a);
				}
			}
		}
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		run();
	}

}
