<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="estadosCubicacion != null">
	<s:select name="valorEstadoCub" id="valorEstadoCub"
		value="%{valorEstadoCub}" list="estadosCubicacion"
		cssClass="cortoCombo" listKey="codigo" listValue="nombre"
		emptyOption="true" />
</s:if>
<s:if test="estadosCubicacion == null">
	<select name="vacio" disabled="disabled">
	</select>
</s:if>
