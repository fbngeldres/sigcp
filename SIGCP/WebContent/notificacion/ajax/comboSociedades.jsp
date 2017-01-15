<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="sociedades != null && sociedades.size() > 0">
	<s:select name="valorSociedad" id="valorSociedad"
		value="%{valorSociedad}" list="sociedades" listKey="codigo"
		listValue="nombre" emptyOption="true"
		onchange="javascript:document.getElementById('valorSociedad').value = this.value; document.getElementById('valorSociedadFiltrado').value = ''; update('comboUnidades');return false;" />
	<script>
		document.getElementById('valorSociedadFiltrado').value = document.getElementById('valorSociedad').value;
		update('comboUnidades');
	</script>
</s:if>
<s:else>
	<select name='vacio' disabled="disabled">
	</select>
	<script>
		document.getElementById('valorSociedadFiltrado').value = '';
		update('comboUnidades');
	</script>
</s:else>