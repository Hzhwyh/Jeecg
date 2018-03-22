<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="incomeList" title="收益记录" actionUrl="incomeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="艺人ID" field="artistId"  hidden="true" width="120"></t:dgCol>
   <t:dgCol title="艺人" field="nick" query="true"  width="120"></t:dgCol>
   <t:dgCol title="收益金额" field="amounts"   width="120"></t:dgCol>
   <t:dgCol title="收益时间" field="createdate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="家族" field="departname"  query="true" width="120"></t:dgCol>
  <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="incomeController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <%--<t:dgToolBar title="录入" icon="icon-add" url="incomeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="incomeController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="incomeController.do?addorupdate" funname="detail"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>