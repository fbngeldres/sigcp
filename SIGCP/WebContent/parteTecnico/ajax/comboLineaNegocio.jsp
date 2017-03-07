<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="lineasNegocio != null && lineasNegocio.size() > 0">
	<s:select name="valorLineaNegocio" value="%{valorLineaNegocio}"
		list="lineasNegocio" listKey="codigo" id="valorLineaNegocio"
		listValue="nombre" emptyOption="true" cssClass="cortoCombo"
		onchange="javascript:document.getElementById('valorLineaNegocioFiltrado').value = this.value;return false;" />
</s:if>
<s:else>
	<select name="vacio" disabled="disabled" id="valorLineaNegocio">
	</select>
</s:else>
