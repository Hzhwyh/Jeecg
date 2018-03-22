<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="artistList" title="艺人基本信息" actionUrl="artistController.do?datagrid&OP=0" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="昵称" field="nick"   width="120"></t:dgCol>
   <t:dgCol title="真实姓名" field="realname"   width="120"></t:dgCol>
   <t:dgCol title="性别" field="sex" dictionary="sex"  width="120"></t:dgCol>
   <t:dgCol title="身份证" field="idcard"   width="120"></t:dgCol>
   <t:dgCol title="QQ" field="qq"   width="120"></t:dgCol>
   <t:dgCol title="主播类型" field="type" dictionary="artistType"  width="120"></t:dgCol>
   <t:dgCol title="入驻时间" field="entertime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="粉丝数" field="fans"   width="120"></t:dgCol>
   <t:dgCol title="等级" field="levels"   width="120"></t:dgCol>
   <t:dgCol title="总收益" field="profit"   width="120"></t:dgCol>
   <t:dgCol title="直播小时数" field="livetime"   width="120"></t:dgCol>
   <t:dgCol title="达标天数" field="standarddays"   width="120"></t:dgCol>
   <t:dgCol title="家族" field="orgname"   width="120"></t:dgCol>
   <t:dgCol title="家族id" field="orgid" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="直播状态" field="livestatus" replace="休息_0,直播_1"   width="120"></t:dgCol>
   <t:dgCol title="艺人状态" field="state" replace="正常_0,申请_1,解约_2"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <t:dgDelOpt title="删除" url="artistController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <%--<t:dgToolBar title="录入" icon="icon-add" url="artistController.do?addorupdate" funname="add"></t:dgToolBar>--%>
   <t:dgToolBar title="编辑" icon="icon-edit" url="artistController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="升级" icon="icon-redo" url="artistController.do?advance" funname="redo"></t:dgToolBar>
   <t:dgToolBar title="解约" icon="icon-undo" url="artistController.do?updateState&state=2" funname="redo"></t:dgToolBar>
   <t:dgToolBar title="开始直播" icon="icon-redo" url="artistController.do?live&state=0" funname="redo"></t:dgToolBar>
   <t:dgToolBar title="结束直播" icon="icon-undo" url="artistController.do?live&state=1" funname="redo"></t:dgToolBar>
   <t:dgToolBar title="新添粉丝" icon="icon-add" url="fansRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="artistController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script>

 function redo(title,url,tableName){
  var row = $('#artistList').datagrid("getSelected");
  if(row == null || row == undefined){
   tip("请选择一位艺人");
   return false;
  }
  if(row.state == 2){
   tip("该艺人已解约");
   return false;
  }
  if(row.state == 1){
   tip("该艺人还未成为正式成员");
   return false;
  }

  if(title == "开始直播" && row.livestatus == 1){
   tip("该艺人正在直播中");
   return false;
  }
  if(title == "结束直播" && row.livestatus == 0){
   tip("该艺人正在休息");
   return false;
  }
   createdialog(title, '是否'+title+' ?', url + "&id=" + row.id, tableName);
 }
</script>