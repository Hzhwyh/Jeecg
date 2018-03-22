<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="artistApplyList" title="艺人基本信息" actionUrl="artistController.do?datagrid&OP=1" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="昵称" field="nick"   width="120"></t:dgCol>
   <t:dgCol title="真实姓名" field="realname"   width="120"></t:dgCol>
   <t:dgCol title="性别" field="sex" dictionary="sex"  width="120"></t:dgCol>
   <t:dgCol title="身份证" field="idcard"   width="120"></t:dgCol>
   <t:dgCol title="QQ" field="qq"   width="120"></t:dgCol>
   <t:dgCol title="主播类型" field="type" dictionary="artistType"  width="120"></t:dgCol>
   <t:dgCol title="申请时间" field="applytime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="家族" field="orgname"   width="120"></t:dgCol>
   <t:dgCol title="家族id" field="orgid" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="艺人状态" field="state" replace="正常_0,申请_1,解约_2"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <t:dgDelOpt title="删除" url="artistController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="申请入驻" icon="icon-add" url="artistController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="通过" icon="icon-redo" url="artistController.do?updateState&state=0" funname="redo"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="artistController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script>
   function redo(title,url,tableName){
    var row = $('#artistApplyList').datagrid("getSelected");
    createdialog('审批通过 ', '是否同意该艺人入驻 ?', url+"&id="+row.id,tableName);
   }
</script>