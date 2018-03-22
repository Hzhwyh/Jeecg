<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>直播记录表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="liveRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${liveRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							艺人ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="artistid" name="artistid" ignore="ignore"  value="${liveRecordPage.artistid}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="starttime" name="starttime" ignore="ignore"    value="<fmt:formatDate value='${liveRecordPage.starttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="endtime" name="endtime" ignore="ignore"    value="<fmt:formatDate value='${liveRecordPage.endtime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态：
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="state" typeGroupCode="liveStatus" defaultVal="${liveRecordPage.state}"></t:dictSelect>
						<%--<input class="inputxt" id="state" name="state" ignore="ignore"  value="${liveRecordPage.state}" datatype="n" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>