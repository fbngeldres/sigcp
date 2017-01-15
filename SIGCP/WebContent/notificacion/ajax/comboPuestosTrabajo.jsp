<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="puestosDeTrabajo != null && puestosDeTrabajo.size() > 0">
	<s:select name="valorPuestoTrabajo" id="valorPuestoTrabajo"
		value="%{valorPuestoTrabajo}" list="puestosDeTrabajo" listKey="codigo"
		listValue="nombre" emptyOption="true" cssClass="normal"
		cssStyle="width: 280px"
		onchange="javascript:update('comboVariablesProdOper');return false;" />
</s:if>
<s:if test="puestosDeTrabajo == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>