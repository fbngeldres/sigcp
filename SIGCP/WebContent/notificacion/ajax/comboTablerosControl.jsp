<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="tablerosControl != null">
	<s:select name="valorLineaNegocio" id="valorLineaNegocio"
		value="%{valorTableroControl}" list="tablerosControl" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboPuestosTrabajo');return false;" />
</s:if>
<s:if test="tablerosControl == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>