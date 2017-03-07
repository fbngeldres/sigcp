<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="productos != null">
	<s:select name="valorProducto" id="valorProducto"
		value="%{valorProducto}" list="productos" listKey="codigo"
		listValue="nombre" emptyOption="true" cssClass="cortoCombo" />
</s:if>
<s:if test="productos == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>