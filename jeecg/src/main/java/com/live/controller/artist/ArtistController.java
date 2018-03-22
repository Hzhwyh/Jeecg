package com.live.controller.artist;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.live.entity.live.LiveRecordEntity;
import com.live.service.live.LiveRecordServiceI;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.live.entity.artist.ArtistEntity;
import com.live.service.artist.ArtistServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 艺人表
 * @author zhangdaihao
 * @date 2018-01-21 20:26:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/artistController")
public class ArtistController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ArtistController.class);

	@Autowired
	private ArtistServiceI artistService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private LiveRecordServiceI liveRecordService;
	@Autowired
	private Validator validator;
	


	/**
	 * 艺人表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/live/artist/artistList");
	}

	/**
	 * 艺人表列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "applylist")
	public ModelAndView applylist(HttpServletRequest request) {
		return new ModelAndView("com/live/artist/artistApplyList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ArtistEntity artist,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String op = request.getParameter("OP");	//0-艺人查询列表（所有）	1-艺人申请列表（仅申请状态）
		CriteriaQuery cq = new CriteriaQuery(ArtistEntity.class, dataGrid);
		if("1".equals(op)){
			artist.setState(1);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, artist, request.getParameterMap());
		this.artistService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除艺人表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ArtistEntity artist, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		artist = systemService.getEntity(ArtistEntity.class, artist.getId());
		message = "艺人表删除成功";
		artistService.delete(artist);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 艺人升级
	 *
	 * @return
	 */
	@RequestMapping(params = "advance")
	@ResponseBody
	public AjaxJson advance(ArtistEntity artist, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		artist = systemService.getEntity(ArtistEntity.class, artist.getId());
		if(artist.getLevels() == null) {
			artist.setLevels(1);
		}else{
			artist.setLevels(artist.getLevels()+1);
		}
		artistService.saveOrUpdate(artist);
		message = "艺人升级成功";
		systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}


	/**
	 * 更改艺人状态
	 *
	 * @return
	 */
	@RequestMapping(params = "updateState")
	@ResponseBody
	public AjaxJson dissolution(ArtistEntity artist, HttpServletRequest request) {
		String message = null;
		String state = request.getParameter("state");
		AjaxJson j = new AjaxJson();
		artist = systemService.getEntity(ArtistEntity.class, artist.getId());


		if("2".equals(state)) {
			message = "艺人已解约";
			artist.setState(Integer.parseInt(state));
			artistService.saveOrUpdate(artist);
			systemService.addLog(message, Globals.Log_Type_DISS, Globals.Log_Leavel_INFO);
		}else if("0".equals(state)){
			message = "艺人已入驻";
			artist.setState(Integer.parseInt(state));
			artist.setEntertime(new Date());
			artist.setLivestatus(0);//休息_0,直播_1
			artistService.saveOrUpdate(artist);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 直播接口
	 *
	 * @return
	 */
	@RequestMapping(params = "live")
	@ResponseBody
	public AjaxJson live(ArtistEntity artist, HttpServletRequest request) {
		String message = null;
		String state = request.getParameter("state");
		AjaxJson j = new AjaxJson();
		artist = systemService.getEntity(ArtistEntity.class, artist.getId());


		if("0".equals(state)) {
			message = "开始直播";

			//修改艺人直播状态
			artist.setLivestatus(1);
			artistService.saveOrUpdate(artist);

			//新添直播记录表
			LiveRecordEntity liveRecord = new LiveRecordEntity();
			liveRecord.setArtistid(artist.getId());
			liveRecord.setState(0);
			liveRecord.setStarttime(new Date());
			artistService.save(liveRecord);

			systemService.addLog(message, Globals.Log_Type_DISS, Globals.Log_Leavel_INFO);
		}else if("1".equals(state)){
			message = "结束直播";

			//修改艺人直播状态
			artist.setLivestatus(0);

			//修改直播记录表状态
			LiveRecordEntity liveRecord = liveRecordService.getByArtistId(artist.getId());
			liveRecord.setEndtime(new Date());
			liveRecord.setState(1);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(liveRecord.getStarttime());
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(liveRecord.getEndtime());
			liveRecord.setMinute(DateUtils.dateDiff('m',cal2,cal1));

			//计算直播小时数
			double hours = DateUtils.dateDiff(liveRecord.getEndtime(),liveRecord.getStarttime());
			artist.setLivetime(hours);

			//计算直播达标小时数	使用定时任务，每天23：30分开始计算每个主播今天的直播时间，看是否达标，达标则更改主播字段

			artistService.saveOrUpdate(liveRecord);
			artistService.saveOrUpdate(artist);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加艺人表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ArtistEntity artist, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();

		if (StringUtil.isNotEmpty(artist.getId())) {
			message = "艺人表更新成功";
			ArtistEntity t = artistService.get(ArtistEntity.class, artist.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(artist, t);
				artistService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "艺人表更新失败";
			}
		} else {
			message = "艺人申请成功";
			artist.setApplytime(new Date());
			artist.setState(1);	//艺人状态(0：正常	1：申请	2：解约)
			artistService.save(artist);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 艺人表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ArtistEntity artist, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(artist.getId())) {
			artist = artistService.getEntity(ArtistEntity.class, artist.getId());
			req.setAttribute("artistPage", artist);
		}
		return new ModelAndView("com/live/artist/artist");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ArtistEntity> list() {
		List<ArtistEntity> listArtists=artistService.getList(ArtistEntity.class);
		return listArtists;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ArtistEntity task = artistService.get(ArtistEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ArtistEntity artist, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ArtistEntity>> failures = validator.validate(artist);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		artistService.save(artist);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = artist.getId();
		URI uri = uriBuilder.path("/rest/artistController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ArtistEntity artist) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ArtistEntity>> failures = validator.validate(artist);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		artistService.saveOrUpdate(artist);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		artistService.deleteEntityById(ArtistEntity.class, id);
	}
}
