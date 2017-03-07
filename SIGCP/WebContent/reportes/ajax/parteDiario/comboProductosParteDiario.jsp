<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="productos != null">
	<s:select id="valorProducto" value="%{valorProducto}" list="productos"
		listKey="codigo" listValue="nombre" emptyOption="true"
		name="valorProducto" />
</s:if>
<s:if test="productos == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>
