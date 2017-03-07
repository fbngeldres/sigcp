<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="procesos != null && procesos.size() > 0 ">
	<s:select name="valorProceso" id="valorProceso" value="%{valorProceso}"
		list="procesos" listKey="codigo" listValue="nombre" emptyOption="true"
		cssClass="cortoCombo"
		onchange="javascript:document.getElementById('valorProceso').value = this.value;document.getElementById('valorProcesoFiltrado').value = '';update('comboProductos');return false;" />
	<script>
		document.getElementById('valorProcesoFiltrado').value = document.getElementById('valorProceso').value;
		update('comboProductos');
	</script>
</s:if>
<s:else>
	<select name="vacio" disabled="disabled">
	</select>
	<script>
		document.getElementById('valorProcesoFiltrado').value = '';
		update('comboProductos');
	</script>
</s:else>