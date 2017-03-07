<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="puestosTrabajo != null">
	<s:select id="valorPuestoTrabajo" value="%{valorPuestoTrabajo}"
		list="puestosTrabajo" listKey="codigo" listValue="nombre"
		emptyOption="true" name="valorPuestoTrabajo" />
</s:if>
<s:if test="puestosTrabajo == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>