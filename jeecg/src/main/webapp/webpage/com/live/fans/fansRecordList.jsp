<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="fansRecordList" title="粉丝记录表" actionUrl="fansRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="艺人昵称" field="artistname"  query="true" width="120"></t:dgCol>
   <t:dgCol title="关注时间" field="attentiondate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="取消时间" field="canceldate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="粉丝qq" field="fansqq"   width="120" query="true"></t:dgCol>
   <t:dgCol title="累计赠送金额" field="amounts"   width="120"></t:dgCol>
   <t:dgCol title="状态" field="state" dictionary="concern" query="true"  width="120"></t:dgCol>
  <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="fansRecordController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <%--<t:dgToolBar title="录入" icon="icon-add" url="fansRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="fansRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="fansRecordController.do?addorupdate" funname="detail"></t:dgToolBar>--%>
   <t:dgToolBar title="取消关注" icon="icon-undo" url="fansRecordController.do?cancel" funname="cancel"></t:dgToolBar>
   <t:dgToolBar title="赠送礼物" icon="icon-edit" url="incomeController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript">
    function cancel(title,url,tableName){
        var row = $('#fansRecordList').datagrid("getSelected");
        if(row == null){
            tip("请选择一条记录");
            return false;
        }
        if(row.state=="1"){
            tip("已取消关注");
            return false;
        }
        createdialog(title, '是否'+title+' ?', url + "&id=" + row.id, tableName);
    }
</script>