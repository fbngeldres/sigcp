<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="sociedades != null && sociedades.size() > 0">
	<s:select name="valorSociedad" id="valorSociedad"
		value="%{valorSociedad}" list="sociedades" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboUnidadesCantera');return false;" />
</s:if>
<s:if test="sociedades == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>