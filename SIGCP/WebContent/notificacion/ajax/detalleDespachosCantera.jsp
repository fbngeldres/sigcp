<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<table class="dataTable2">
	<tr>
		<td><display:table name="despachosCantera" id="despachosCantera"
				pagesize="10" cellpadding="0" cellspacing="0"
				defaultorder="ascending" defaultsort="6" sort="list" requestURI=""
				class="dataTable">
				<display:setProperty name="basic.empty.showtable">true</display:setProperty>
				<display:column title="Hora" property="hora" sortable="false" />
				<display:column title="Balanza" property="balanza" sortable="false" />
				<display:column title="Producto"
					property="ordenProduccion.produccion.producto.nombre"
					sortable="false" />
				<display:column title="Origen"
					property="origenUbicacionCantera.nombre" sortable="false" />
				<display:column title="Proveedor" property="proveedor"
					sortable="false" />
				<display:column title="Tara" property="tara" sortable="false" />
				<display:column title="Peso Bruto" property="pesoBruto"
					sortable="false" />
				<display:column title="Peso Neto" property="pesoNeto"
					sortable="false" />
				<display:column title="Placa" property="placaVolquete"
					sortable="false" />
				<display:column title="Conductor" property="conductor"
					sortable="false" />
			</display:table></td>
	</tr>
</table>
<s:if test="hasActionErrors()">
	<div class="advertencia">
		<s:actionerror />
	</div>
</s:if>