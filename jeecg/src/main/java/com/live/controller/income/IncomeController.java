package com.live.controller.income;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.live.entity.artist.ArtistEntity;
import com.live.entity.fans.FansRecordEntity;
import com.live.entity.income.ArtistIncomeViewEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.store.Query;
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

import com.live.entity.income.IncomeEntity;
import com.live.service.income.IncomeServiceI;

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

import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 收益记录表
 * @author zhangdaihao
 * @date 2018-01-21 21:52:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/incomeController")
public class IncomeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IncomeController.class);

	@Autowired
	private IncomeServiceI incomeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 收益记录表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/live/income/incomeList");
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
	public void datagrid(ArtistIncomeViewEntity artistIncome, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ArtistIncomeViewEntity.class, dataGrid);

		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, artistIncome, request.getParameterMap());
		this.incomeService.getDataGridReturn(cq, true);

		String sql = "select sum(amounts) totals from a_income ai left join a_artist ar on ai.artist_id = ar.id where 1=1 ";
		if(StringUtils.isNotBlank(artistIncome.getNick())){
			sql +=" and ar.nick like  '%"+artistIncome.getNick()+"%' ";
		}
		if(StringUtils.isNotBlank(artistIncome.getDepartname())){
			sql +=" and ar.orgname like  '%"+artistIncome.getDepartname()+"%' ";
		}
		List<Map<String,Object>> list = incomeService.findForJdbc(sql);
		if(list.size()>0){
			String totalSum = String.valueOf(list.get(0).get("totals"));
			dataGrid.setFooter("nick:合计：,amounts:"+(totalSum==null||totalSum.equals("null")?"0":totalSum));
		}

		if(StringUtils.isNotBlank(artistIncome.getNick())){
			artistIncome.setNick("*"+artistIncome.getNick()+"*");
		}
		if(StringUtils.isNotBlank(artistIncome.getDepartname())){
			artistIncome.setDepartname("*"+artistIncome.getDepartname()+"*");
		}

		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除收益记录表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(IncomeEntity income, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		income = systemService.getEntity(IncomeEntity.class, income.getId());
		message = "收益记录表删除成功";
		incomeService.delete(income);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收益记录表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(IncomeEntity income, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(income.getId())) {
			message = "收益记录表更新成功";
			IncomeEntity t = incomeService.get(IncomeEntity.class, income.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(income, t);
				incomeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "收益记录表更新失败";
			}
		} else {
			message = "收益记录表添加成功";
			income.setCreatedate(new Date());
			income.setState(1);
			incomeService.save(income);

			List<FansRecordEntity> fansList = systemService.findHql("from FansRecordEntity where artistid=? and fansqq=?",income.getArtistId(),income.getFansqq());
			FansRecordEntity fans = fansList.get(0);
			double amounts = 0;

			if(fans.getAmounts() != null){
				amounts = fans.getAmounts()+income.getAmounts();	//粉丝记录表累计收益
			}else{
				amounts = income.getAmounts();
			}
			fans.setAmounts(amounts);

			ArtistEntity artist = systemService.get(ArtistEntity.class,fans.getArtistid());
			if(artist.getProfit() !=null){
				amounts = artist.getProfit()+income.getAmounts();
			}else{
				amounts = income.getAmounts();
			}
			artist.setProfit(amounts);


			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 收益记录表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FansRecordEntity fansRecord, HttpServletRequest req) {
		fansRecord = systemService.get(FansRecordEntity.class,fansRecord.getId());
		IncomeEntity income = new IncomeEntity();
		income.setArtistId(fansRecord.getArtistid());
		income.setFansqq(fansRecord.getFansqq());
		req.setAttribute("incomePage", income);
		ModelAndView mv = new ModelAndView("com/live/income/income");
		mv.addObject("artistname",fansRecord.getArtistname());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<IncomeEntity> list() {
		List<IncomeEntity> listIncomes=incomeService.getList(IncomeEntity.class);
		return listIncomes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		IncomeEntity task = incomeService.get(IncomeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody IncomeEntity income, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<IncomeEntity>> failures = validator.validate(income);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		incomeService.save(income);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = income.getId();
		URI uri = uriBuilder.path("/rest/incomeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody IncomeEntity income) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<IncomeEntity>> failures = validator.validate(income);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		incomeService.saveOrUpdate(income);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		incomeService.deleteEntityById(IncomeEntity.class, id);
	}
}
