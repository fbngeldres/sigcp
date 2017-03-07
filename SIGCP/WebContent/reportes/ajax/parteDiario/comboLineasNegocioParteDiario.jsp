<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="lineasNegocio != null">
	<s:select name="valorLineaNegocio" id="valorLineaNegocio"
		value="%{valorLineaNegocio}" list="lineasNegocio" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboProcesosParteDiario');return false;" />
</s:if>
<s:if test="lineasNegocio == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>