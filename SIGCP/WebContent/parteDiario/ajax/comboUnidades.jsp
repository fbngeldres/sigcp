<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="unidades != null && unidades.size() > 0">
	<s:select name="valorUnidad" id="valorUnidad" value="%{valorUnidad}"
		list="unidades" listKey="codigo" listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboLineasNegocio');javascript:actualizarCodUnidad();return false;" />


	<script type="text/javascript">

		update('comboLineasNegocio')</script>
</s:if>
<s:else>
	<select name="vacio" disabled="disabled">
	</select>
</s:else>

<script>
	update("comboLineasNegocio");
	actualizarCodUnidad();
</script>