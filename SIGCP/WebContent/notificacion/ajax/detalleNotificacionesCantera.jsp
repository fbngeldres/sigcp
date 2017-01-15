<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<table class="dataTable2">
	<tr>
		<td><display:table name="notificacionesCantera"
				id="notificacionesCantera" pagesize="10" cellpadding="0"
				cellspacing="0" defaultorder="ascending" defaultsort="6" sort="list"
				requestURI="" class="dataTable">
				<display:setProperty name="basic.empty.showtable">true</display:setProperty>
				<display:column title="Fecha de Notificación"
					property="fechaNotificacion" format="{0,date,dd/MM/yyyy}"
					sortable="false" />
				<display:column title="Actividad" property="actividad.nombre"
					sortable="false" />
				<display:column title="Origen"
					property="origenUbicacionCantera.nombre" sortable="false" />
				<display:column title="Destino"
					property="destinoUbicacionCantera.nombre" sortable="false" />
				<display:column title="Proceso"
					property="ordenProduccion.produccion.proceso.nombre"
					sortable="false" />
				<display:column title="Producto"
					property="ordenProduccion.produccion.producto.nombre"
					sortable="false" />
				<display:column title="Puesto Trabajo"
					property="puestoTrabajo.nombre" sortable="false" />
			</display:table></td>
	</tr>
</table>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>