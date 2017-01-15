<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<s:textfield name="nombreUnidadMedida" id="nombreUnidadMedida"
	value="%{nombreUnidadMedida}" readonly="true" cssClass="disable"
	cssStyle="width: 70px" />
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>