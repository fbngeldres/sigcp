<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="procesos != null">
	<s:select name="valorProceso" id="valorProceso" value="%{valorProceso}"
		list="procesos" listKey="codigo" listValue="nombre" emptyOption="true"
		cssClass="normal" cssStyle="width: 200px"
		onchange="javascript:update('comboActividadesCantera');return false;" />
</s:if>
<s:if test="procesos == null">
	<select name="vacio" disabled="disabled" style="width: 200px">
	</select>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>