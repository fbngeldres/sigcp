<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<s:head theme="ajax" />

<s:if test="productos != null">
	<s:select name="productoSeleccionado" id="productoSeleccionado"
		value="%{productoSeleccionado}" list="productos" listKey="codigo"
		listValue="nombre" headerValue="Seleccione" headerKey="-1"
		theme="simple" />
</s:if>