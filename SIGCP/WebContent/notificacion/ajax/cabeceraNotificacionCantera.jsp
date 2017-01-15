<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<s:if test="notificacionDiaria != null">
	<table class="dataTable1">
		<tr>
			<td><s:text
					name="notificacion.consultaNotificacionProduccionPlanta.FechaNotificacion"></s:text>:
			</td>
			<td><s:textfield name="fechaNuevaNotificacionDiaria"
					id="fechaNuevaNotificacionDiaria"
					value="%{notificacionDiaria.fechaNotificacionDiaria}"
					readonly="true" cssClass="disable" /></td>
			<td>Observación:</td>
			<td><s:textarea id="observacionNotificacion"
					name="observacionNotificacion" cols="33" rows=""></s:textarea></td>
		</tr>
	</table>
</s:if>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>