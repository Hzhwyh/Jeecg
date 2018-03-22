package com.live.controller.fans;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.live.entity.artist.ArtistEntity;
import org.apache.log4j.Logger;
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

import com.live.entity.fans.FansRecordEntity;
import com.live.service.fans.FansRecordServiceI;

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
 * @Description: 粉丝记录表
 * @author zhangdaihao
 * @date 2018-01-21 23:49:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/fansRecordController")
public class FansRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FansRecordController.class);

	@Autowired
	private FansRecordServiceI fansRecordService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 粉丝记录表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/live/fans/fansRecordList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FansRecordEntity fansRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FansRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fansRecord, request.getParameterMap());
		this.fansRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除粉丝记录表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FansRecordEntity fansRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		fansRecord = systemService.getEntity(FansRecordEntity.class, fansRecord.getId());
		message = "粉丝记录表删除成功";
		fansRecordService.delete(fansRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加粉丝记录表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FansRecordEntity fansRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fansRecord.getId())) {
			message = "粉丝记录表更新成功";
			FansRecordEntity t = fansRecordService.get(FansRecordEntity.class, fansRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fansRecord, t);
				fansRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "粉丝记录表更新失败";
			}
		} else {
			//先判断该粉丝qq是否已关注过该艺人
			List<FansRecordEntity> list = systemService.findHql("from FansRecordEntity where artistid=? and fansqq=? ",fansRecord.getArtistid(),fansRecord.getFansqq());
			if(list.size()>0){	//若有重复关注则删除之前重复的记录
				for(FansRecordEntity f:list) {
					systemService.delete(f);
				}
			}

			message = "粉丝记录表添加成功";
			fansRecord.setAttentiondate(new Date());
			fansRecord.setState(0);
			fansRecordService.save(fansRecord);

			list = systemService.findHql("from FansRecordEntity where state=0 and artistid=? ",fansRecord.getArtistid());

			ArtistEntity artist = systemService.get(ArtistEntity.class,fansRecord.getArtistid());

			int fans = list.size();
			artist.setFans(fans);
			fansRecordService.saveOrUpdate(artist);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 粉丝取消关注
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "cancel")
	@ResponseBody
	public AjaxJson cancel(FansRecordEntity fansRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fansRecord.getId())) {
			message = "已取消关注";
			FansRecordEntity t = fansRecordService.get(FansRecordEntity.class, fansRecord.getId());
			t.setState(1);
			t.setCanceldate(new Date());
			systemService.saveOrUpdate(t);
			ArtistEntity artist = systemService.get(ArtistEntity.class,t.getArtistid());
			artist.setFans(artist.getFans()-1);
			systemService.saveOrUpdate(artist);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 粉丝记录表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FansRecordEntity fansRecord, HttpServletRequest req) {

		if (StringUtil.isNotEmpty(fansRecord.getId())) {	//新添粉丝记录
			ArtistEntity artistEntity = systemService.getEntity(ArtistEntity.class,fansRecord.getId());
			fansRecord = new FansRecordEntity();
			fansRecord.setArtistid(artistEntity.getId());
			fansRecord.setArtistname(artistEntity.getNick());
		}
		req.setAttribute("fansRecordPage", fansRecord);
		return new ModelAndView("com/live/fans/fansRecord");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FansRecordEntity> list() {
		List<FansRecordEntity> listFansRecords=fansRecordService.getList(FansRecordEntity.class);
		return listFansRecords;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		FansRecordEntity task = fansRecordService.get(FansRecordEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody FansRecordEntity fansRecord, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FansRecordEntity>> failures = validator.validate(fansRecord);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		fansRecordService.save(fansRecord);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = fansRecord.getId();
		URI uri = uriBuilder.path("/rest/fansRecordController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody FansRecordEntity fansRecord) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FansRecordEntity>> failures = validator.validate(fansRecord);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		fansRecordService.saveOrUpdate(fansRecord);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		fansRecordService.deleteEntityById(FansRecordEntity.class, id);
	}
}
