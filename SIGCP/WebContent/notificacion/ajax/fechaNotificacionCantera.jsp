<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<s:if test="fechaNotificacionDiaria != null">
	<s:textfield name="fechaNotificacionDiaria"
		id="fechaNotificacionDiaria" value="%{fechaNotificacionDiaria}"
		readonly="true" cssClass="disable" />
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>