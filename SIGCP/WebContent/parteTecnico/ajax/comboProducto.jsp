<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="productos != null">
	<s:select name="valorProducto" value="%{valorProducto}"
		id="valorProducto" list="productos" listKey="codigo"
		listValue="nombre" emptyOption="true" cssClass="cortoCombo"
		onchange="javascript:document.getElementById('valorProducto').value = this.value; update('comboOculto');  return false;" />
</s:if>
<s:if test="productos == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
