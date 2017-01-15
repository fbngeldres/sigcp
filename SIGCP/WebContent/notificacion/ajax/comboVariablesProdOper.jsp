<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="variables != null">
	<s:select name="valorVariablesProdOper" id="valorVariablesProdOper"
		value="%{valorVariablesProdOper}" list="variables" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:update('detalleVariables');return false;" />
</s:if>
<s:else>
	<select name="vacio" disabled="disabled">
	</select>
</s:else>