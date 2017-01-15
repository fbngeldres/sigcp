<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="procesos != null">
	<s:select name="valorProceso" id="valorProceso" value="%{valorProceso}"
		list="procesos" listKey="codigo" listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboTablerosControl');return false;" />
</s:if>
<s:if test="procesos == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>