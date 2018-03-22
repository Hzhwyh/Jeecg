<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>收益记录表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="incomeController.do?save">
			<input id="id" name="id" type="hidden" value="${incomePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							艺人昵称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="artistId" name="artistId" type="hidden" value="${incomePage.artistId}" datatype="*" />
						<input class="inputxt" id="artistname" name="artistname"   value="${artistname}" datatype="*" readonly />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							粉丝QQ:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fansqq" name="fansqq"   value="${incomePage.fansqq}" datatype="*" readonly />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							赠送金额:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="amounts" name="amounts" value="${incomePage.amounts}" datatype="d" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="display:none;">
					<td align="right">
						<label class="Validform_label">
							收益时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createdate" name="createdate" ignore="ignore"    value="<fmt:formatDate value='${incomePage.createdate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="display:none;">
					<td align="right">
						<label class="Validform_label">
							结算状态（0未结算，1已结算）:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="state" name="state" ignore="ignore"  value="${incomePage.state}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>