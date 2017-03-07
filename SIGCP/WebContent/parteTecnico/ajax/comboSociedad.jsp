<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="sociedades != null && sociedades.size() > 0">
	<s:select name="valorSociedad" id="valorSociedad"
		value="%{valorSociedad}" list="sociedades" listKey="codigo"
		listValue="nombre" emptyOption="true" cssClass="cortoCombo"
		onchange="javascript:document.getElementById('valorSociedadFiltrado').value = this.value;eliminarSociedadUnidadLinea();update('comboUnidad'); return false;" />
</s:if>
<s:else>
	<select name="vacio" disabled="disabled" id="valorSociedad">
	</select>
	<script>
		document.getElementById('valorSociedadFiltrado').value ="";
		document.getElementById("valorUnidadFiltrado").value = "";
		document.getElementById("valorLineaNegocioFiltrado").value = "";
		update('comboUnidad');
	</script>

</s:else>
