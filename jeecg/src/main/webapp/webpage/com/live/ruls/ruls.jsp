<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>家族规则表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="rulsController.do?save">
			<input id="id" name="id" type="hidden" value="${rulsPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							家族:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" type="hidden" id="orgid" name="orgid" ignore="ignore"  value="${rulsPage.orgid}" />
						<input class="inputxt" id="orgname" name="orgname" ignore="ignore" readonly="readonly"  value="${rulsPage.orgname}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							达标小时:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="standard" name="standard" ignore="ignore"  value="${rulsPage.standard}" datatype="d" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>