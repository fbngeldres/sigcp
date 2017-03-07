<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="unidades != null && unidades.size() > 0">
	<s:select name="valorUnidad" id="valorUnidad" value="%{valorUnidad}"
		list="unidades" listKey="codigo" listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboLineaNegocioParteDiario'); update('comboPuestoTrabajo');return false;" />
</s:if>
<s:if test="unidades == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>