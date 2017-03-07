<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="lineasNegocio != null && lineasNegocio.size() > 0">
	<s:select name="valorLineaNegocio" id="valorLineaNegocio"
		value="%{valorLineaNegocio}" list="lineasNegocio" listKey="codigo"
		listValue="nombre" emptyOption="true" cssClass="cortoCombo"
		onchange="javascript:document.getElementById('valorLineaNegocio').value = this.value;document.getElementById('valorLineaNegocioFiltrado').value = '';update('comboProcesos');return false;" />

	<script>
		document.getElementById('valorLineaNegocioFiltrado').value = document.getElementById('valorLineaNegocio').value;
		update('comboProcesos');
	</script>
</s:if>
<s:else>
	<select name="vacio" disabled="disabled">
	</select>
	<script>
		document.getElementById('valorLineaNegocioFiltrado').value = '';
		update('comboProcesos');
	</script>
</s:else>