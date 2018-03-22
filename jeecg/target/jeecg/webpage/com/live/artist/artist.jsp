<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>艺人基本信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="artistController.do?save">
			<input id="id" name="id" type="hidden" value="${artistPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							昵称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="nick" name="nick" ignore="ignore"  value="${artistPage.nick}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							真实姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="realname" name="realname" ignore="ignore"  value="${artistPage.realname}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
						<%--<input class="inputxt" id="sex" name="sex" ignore="ignore"  value="${artistPage.sex}" datatype="n" />--%>
						<t:dictSelect field="sex" type="select" typeGroupCode="sex" hasLabel="false" title="性别" defaultVal="${artistPage.sex}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							身份证:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="idcard" name="idcard" ignore="ignore"  value="${artistPage.idcard}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							QQ:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="qq" name="qq" ignore="ignore"  value="${artistPage.qq}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							主播类型:
						</label>
					</td>
					<td class="value">
						<%--<input class="inputxt" id="type" name="type" ignore="ignore"  value="${artistPage.type}"  />--%>
						<t:dictSelect field="type" typeGroupCode="artistType" defaultVal="${artistPage.type}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							家族:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="orgname" name="orgname" ignore="ignore"  value="${artistPage.orgname}" />
						<input class="inputxt" type="hidden" id="orgid" name="orgid" ignore="ignore"  value="${artistPage.orgid}" />
						<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" id="departSearch" onclick="openDepartmentSelect()">选择</a>
						<a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" id="departRedo" onclick="callbackClean()">清空</a>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="display: none">
					<td align="right">
						<label class="Validform_label">
							入驻时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="entertime" name="entertime" ignore="ignore"    value="<fmt:formatDate value='${artistPage.entertime}' type="date" pattern="yyyy-MM-dd"/>" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							粉丝数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fans" name="fans" ignore="ignore"  value="${artistPage.fans}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							等级:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="levels" name="levels" ignore="ignore"  value="${artistPage.levels}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							总收益:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="profit" name="profit" ignore="ignore"  value="${artistPage.profit}" datatype="d" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							直播小时数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="livetime" name="livetime" ignore="ignore"  value="${artistPage.livetime}" datatype="d" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							达标天数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="standarddays" name="standarddays" ignore="ignore"  value="${artistPage.standarddays}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							直播状态(0：休息	1：直播):
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="livestatus" name="livestatus" ignore="ignore"  value="${artistPage.livestatus}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							艺人状态(0：正常	1：申请	2：解约):
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="state" name="state" ignore="ignore"  value="${artistPage.state}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
<script>
	function openDepartmentSelect() {
		$.dialog.setting.zIndex = getzIndex();
		var orgIds = $("#orgid").val();

		$.dialog({content: 'url:departController.do?departSelectradio&orgIds='+orgIds, zIndex: getzIndex(), title: '组织机构列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
			{name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackDepartmentSelect, focus: true},
			{name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
		]}).zindex();

	}
	function callbackDepartmentSelect() {
		var iframe = this.iframe.contentWindow;
		var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
		var nodes = treeObj.getSelectedNodes(true);
		if(nodes.length>0){
			var ids='',names='';
//			for(i=0;i<nodes.length;i++){
				var node = nodes[0];
				ids += node.id;
				names += node.name;
//			}
			$('#orgname').val(names);
			$('#orgname').blur();
			$('#orgid').val(ids);
		}
	}

	function callbackClean(){
		$('#orgname').val('');
		$('#orgid').val('');
	}
</script>