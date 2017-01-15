<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="sociedades != null && sociedades.size() > 0">
	<s:select name="valorSociedad" id="valorSociedad"
		value="%{valorSociedad}" list="sociedades" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:update('comboUnidades');return false;" />
	<script type="text/javascript">

		update('comboUnidades')</script>
</s:if>
<s:else>
	<select name="vacio" disabled="disabled">
	</select>
</s:else>

<script>
	update("comboUnidades");
</script>