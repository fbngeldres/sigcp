<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="sociedades != null && sociedades.size() > 0">
	<s:select name="valorSociedad" id="valorSociedad"
		value="%{valorSociedad}" list="sociedades" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboUnidadesParteDiario');return false;" />
	<script>
		update("comboUnidadesParteDiario");
	</script>
</s:if>
<s:if test="sociedades == null">
	<select name="vacio" disabled="disabled">
	</select>
	<script>
		document.getElementById('formularioFiltro_valorSociedadFiltrado').value ="";
		document.getElementById('formularioFiltro_valorUnidadFiltrado').value ="";
		update("comboUnidadesParteDiario");
	</script>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>
