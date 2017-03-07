<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="unidades != null && unidades.size() > 0">
	<s:select name="valorUnidad" value="%{valorUnidad}" list="unidades"
		id="valorUnidad" listKey="codigo" listValue="nombre"
		cssClass="cortoCombo" emptyOption="true"
		onchange="javascript:document.getElementById('valorUnidadFiltrado').value = this.value; eliminarUnidadLinea();update('comboLineaNegocio'); return false;" />
</s:if>
<s:else>
	<select name="vacio" disabled="disabled" id="valorUnidad">
	</select>
	<script>
		document.getElementById("valorUnidadFiltrado").value = "";
		document.getElementById("valorLineaNegocioFiltrado").value = "";
		update('comboLineaNegocio');
	</script>
</s:else>
