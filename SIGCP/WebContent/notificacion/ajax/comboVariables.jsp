<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="variables != null && variables.size() > 0">
	<s:select name="valorVariables" id="valorVariables"
		value="%{valorVariables}" list="variables" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboProcesos');return false;" />
</s:if>
<s:else>
	<select name="vacio" disabled="disabled">
	</select>
</s:else>