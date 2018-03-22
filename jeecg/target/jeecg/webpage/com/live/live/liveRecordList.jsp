<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="liveRecordList" title="直播记录表" actionUrl="liveRecordController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="艺人ID" field="artistid"   width="120"></t:dgCol>
   <t:dgCol title="开始时间" field="starttime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="结束时间" field="endtime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="累计分钟" field="minute"   width="120"></t:dgCol>
   <t:dgCol title="状态" dictionary="liveStatus" field="state"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="liveRecordController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  <%-- <t:dgToolBar title="录入" icon="icon-add" url="liveRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="liveRecordController.do?addorupdate" funname="update"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="liveRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>