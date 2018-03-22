<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>粉丝记录表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="fansRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${fansRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr style="display: none;">
					<td align="right">
						<label class="Validform_label">
							关注时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="attentiondate" name="attentiondate" ignore="ignore"    value="<fmt:formatDate value='${fansRecordPage.attentiondate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="display: none;">
					<td align="right">
						<label class="Validform_label">
							取消时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="canceldate" name="canceldate" ignore="ignore"    value="<fmt:formatDate value='${fansRecordPage.canceldate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="display: none;">
					<td align="right">
						<label class="Validform_label">
							艺人ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="artistid" name="artistid" ignore="ignore"  value="${fansRecordPage.artistid}" readonly="readonly" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							艺人名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="artistname" name="artistname" ignore="ignore"  value="${fansRecordPage.artistname}" readonly="readonly" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							粉丝qq:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fansqq" name="fansqq" datatype="d" value="${fansRecordPage.fansqq}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="display: none;">
					<td align="right">
						<label class="Validform_label">
							状态：0关注	1取消关注:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="state" name="state" ignore="ignore"  value="${fansRecordPage.state}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>