<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<table class="dataTable2">
	<tr>
		<td><display:table name="notificacionesPlanta"
				id="notificacionesPlantaTabla" pagesize="10" cellpadding="0"
				cellspacing="0" defaultorder="ascending" defaultsort="6" sort="list"
				requestURI="" class="dataTable">
				<display:setProperty name="basic.empty.showtable">true</display:setProperty>
				<display:column title="Selección" sortable="true">
					<input type="radio" class="radiobutton" name="notificacion.codigo"
						value="${notificacionesPlantaTabla.codigo}" />
				</display:column>
				<display:column title="Código" property="codigo" sortable="false" />
				<display:column title="Fecha de Notificacion"
					property="fechaNotificacionDiaria" format="{0,date,dd/MM/yyyy}"
					sortable="false" />
				<display:column title="Estado"
					property="estadoNotificacion.nombreEstadoNotificacion"
					sortable="false" />
				<display:column title="Usuario Aprueba"
					property="usuarioAprueba.nombre" sortable="false" />
				<display:column title="Usuario Registra"
					property="usuarioRegistra.nombre" sortable="false" />
			</display:table></td>
	</tr>
</table>
