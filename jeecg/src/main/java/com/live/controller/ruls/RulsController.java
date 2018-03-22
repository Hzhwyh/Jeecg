package com.live.controller.ruls;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
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

import com.live.entity.ruls.RulsEntity;
import com.live.service.ruls.RulsServiceI;

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
 * @Description: 家族规则表
 * @author zhangdaihao
 * @date 2018-01-21 23:48:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/rulsController")
public class RulsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RulsController.class);

	@Autowired
	private RulsServiceI rulsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 家族规则表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/live/ruls/rulsList");
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
	public void datagrid(RulsEntity ruls,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RulsEntity.class, dataGrid);
		String departId = ResourceUtil.getSessionUser().getDepartid();
		TSDepart depart = rulsService.getEntity(TSDepart.class,departId);
		ruls.setOrgid(depart.getId());
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ruls, request.getParameterMap());
		this.rulsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除家族规则表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RulsEntity ruls, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ruls = systemService.getEntity(RulsEntity.class, ruls.getId());
		message = "家族规则表删除成功";
		rulsService.delete(ruls);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加家族规则表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RulsEntity ruls, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ruls.getId())) {
			message = "家族规则表更新成功";
			RulsEntity t = rulsService.get(RulsEntity.class, ruls.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ruls, t);
				rulsService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "家族规则表更新失败";
			}
		} else {
			message = "新规则制定成功";
			String departId = ResourceUtil.getSessionUser().getDepartid();
			rulsService.InvalidAll(departId);	//将旧规则都失效

			ruls.setState(0);
			ruls.setCreatedate(new Date());
			rulsService.save(ruls);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 家族规则表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RulsEntity ruls, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ruls.getId())) {
			ruls = rulsService.getEntity(RulsEntity.class, ruls.getId());
			req.setAttribute("rulsPage", ruls);
		}else{
			String departId = ResourceUtil.getSessionUser().getDepartid();
			TSDepart depart = rulsService.getEntity(TSDepart.class,departId);
			String orgid = depart.getId();
			String orgname = depart.getDepartname();
			ruls.setOrgid(orgid);
			ruls.setOrgname(orgname);
			req.setAttribute("rulsPage", ruls);
		}
		return new ModelAndView("com/live/ruls/ruls");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<RulsEntity> list() {
		List<RulsEntity> listRulss=rulsService.getList(RulsEntity.class);
		return listRulss;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		RulsEntity task = rulsService.get(RulsEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody RulsEntity ruls, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RulsEntity>> failures = validator.validate(ruls);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		rulsService.save(ruls);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = ruls.getId();
		URI uri = uriBuilder.path("/rest/rulsController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody RulsEntity ruls) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<RulsEntity>> failures = validator.validate(ruls);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		rulsService.saveOrUpdate(ruls);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		rulsService.deleteEntityById(RulsEntity.class, id);
	}
}
