<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="procesos != null">
	<s:select name="valorProceso" id="valorProceso" value="%{valorProceso}"
		list="procesos" listKey="codigo" listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboProductoParteDiario');return false;" />
</s:if>
<s:if test="procesos == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>