<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="rulsList" title="家族规则表" actionUrl="rulsController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="家族ID" field="orgid" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="家族名称" field="orgname"   width="120"></t:dgCol>
   <t:dgCol title="达标小时" field="standard"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="createdate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="状态标志" field="state" dictionary="status"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <%--<t:dgDelOpt title="删除" url="rulsController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <t:dgToolBar title="制定规则" icon="icon-add" url="rulsController.do?addorupdate" funname="addruls"></t:dgToolBar>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="rulsController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="rulsController.do?addorupdate" funname="detail"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
<script>
    function addruls(title,addurl,gname,width,height){
        $.dialog.confirm('确定制定新规则吗', function () {
            add(title, addurl, gname, width, height);
        });
    }
</script>