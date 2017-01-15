<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="puestosTrabajo != null && puestosTrabajo.size() > 0">
	<s:select name="valorPuestoTrabajo" id="valorPuestoTrabajo"
		value="%{valorPuestoTrabajo}" list="puestosTrabajo" listKey="codigo"
		listValue="nombre" emptyOption="true" cssClass="normal"
		cssStyle="width: 280px" />
</s:if>
<s:else>
	<select name="vacio" style="width: 280px" disabled="disabled">
	</select>
</s:else>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>