<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="actividades != null && actividades.size() > 0">
	<s:select name="valorActividad" id="valorActividad"
		value="%{valorActividad}" list="actividades" listKey="codigo"
		listValue="nombre" emptyOption="true" />
</s:if>
<s:else>
	<select name="vacio" disabled="disabled">
	</select>
</s:else>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>