<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<s:head theme="ajax" />

<title>CEMENTOS PACASMAYO - Sistema de Gesti&oacute;n y Control
	de Producci&oacute;n</title>

<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css" />

<link type="text/css"
	href="<%=request.getContextPath()%>/css/blitzer/jquery-ui-1.7.2.custom.css"
	rel="stylesheet" />
<script language="JavaScript" type='text/javascript'
	src='<%=request.getContextPath()%>/scripts/main.js'></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.4.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/fechaUtil.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/reporteParteDiario.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/util.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/ui.datepicker-es.js"></script>
</head>

<body onload="iniciarMenu();">

	<div class="content-ppal">

		<!-- INCLUDE HEADER -->
		<jsp:include page="../comun/cabeceraPagina.jsp" flush="true" />

		<!-- INCLUDE MENU -->
		<jsp:include page="../comun/menu.jsp" flush="true" />

		<!--  INICIO CONTENIDO INTERNO -->
		<div id="contenido" class="content-int">

			<!--  INICIO TITULO-->
			<div class="titulo">
				<s:text name="reportes.reporteParteDiario.titulo.principal" />
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
							<td class="excel"><a href="#"
								onclick="javascript:generarReporteEXCELClicked('formularioFiltro');"><img
									alt="" src="../images/transparente.gif"> </a></td>
							<td class="pdfIcon"><a href="#"
								onclick="javascript:generarReportePDFClicked('formularioFiltro');"><img
									alt="" src="../images/transparente.gif"> </a></td>
						</tr>
						<tr>
							<td><a href="#"
								onclick="javascript:generarReporteEXCELClicked('formularioFiltro');">Exporta&nbsp;Excel</a>
							</td>
							<td><a href="#"
								onclick="javascript:generarReportePDFClicked('formularioFiltro');">Exporta&nbsp;PDF</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--  INICIO CONTENIDO -->
			<div class="content">

				<s:form method="post" name="formularioFiltro" id="formularioFiltro"
					namespace="/reportes"
					action="crearReporteDetalleParteDiario.action" theme="simple">
					<s:hidden id="valorTipoExp" name="valorTipoExp"
						value="%{valorTipoExp}"></s:hidden>
					<s:hidden name="valorCargaCompletada" id="valorCargaCompletada"
						value="NO" />
					<s:hidden name="valorSociedad"></s:hidden>
					<s:hidden name="valorUnidad"></s:hidden>
					<table>
						<tr>
							<td><s:text
									name="manejoMaestro.lineaNegocio.etiqueta.division"></s:text> :
								<span class="campooblig">(*)</span></td>
							<td><s:select name="valorDivision" id="valorDivision"
									value="%{valorDivision}" list="divisiones" listKey="codigo"
									listValue="nombre" emptyOption="true"
									onchange="javascript:update('comboSociedadesParteDiario');return false;" />
							</td>
							<td><s:text
									name="manejoMaestro.lineaNegocio.etiqueta.sociedad"></s:text> :
								<span class="campooblig">(*)</span></td>
							<td><s:url id="detalleComboSociedadesParteDiario"
									action="cargaSociedadesReporteParteDiario" /> <s:div
									showLoadingText="true" id="comboSociedadesParteDiario"
									href="%{detalleComboSociedadesParteDiario}" theme="ajax"
									listenTopics="comboSociedadesParteDiario"
									formId="formularioFiltro">
								</s:div></td>
							<td><s:text
									name="manejoMaestro.lineaNegocio.etiqueta.unidad"></s:text> : <span
								class="campooblig">(*)</span></td>
							<td><s:url id="detalleComboUnidadesParteDiario"
									action="cargaUnidadesReporteParteDiario" /> <s:div
									showLoadingText="true" id="comboUnidadesParteDiario"
									href="%{detalleComboUnidadesParteDiario}" theme="ajax"
									listenTopics="comboUnidadesParteDiario"
									formId="formularioFiltro">
								</s:div></td>
						</tr>
						<tr>
							<td><s:text name="Linea de Negocio"></s:text> : <span
								class="campooblig">(*)</span></td>
							<td><s:url id="detalleComboLineaNegocioParteDiario"
									action="cargaLineasNegocioReporteParteDiario" /> <s:div
									showLoadingText="true" id="comboLineaNegocioParteDiario"
									href="%{detalleComboLineaNegocioParteDiario}" theme="ajax"
									listenTopics="comboLineaNegocioParteDiario"
									formId="formularioFiltro">
								</s:div></td>
							<td><s:text name="Proceso"></s:text> : <span
								class="campooblig">(*)</span></td>
							<td><s:url id="detalleComboProcesoParteDiario"
									action="cargaProcesosReporteParteDiario" /> <s:div
									showLoadingText="true" id="comboProcesosParteDiario"
									href="%{detalleComboProcesoParteDiario}" theme="ajax"
									listenTopics="comboProcesosParteDiario"
									formId="formularioFiltro">
								</s:div></td>
							<td><s:text name="Producto"></s:text> :</td>
							<s:hidden id="valorProductoHidden" name="valorProducto"
								value="%{valorProducto}"></s:hidden>
							<td><s:url id="detalleComboProductoParteDiario"
									action="cargaProductoReporteParteDiario" /> <s:div
									showLoadingText="true" id="comboProductoParteDiario"
									href="%{detalleComboProductoParteDiario}" theme="ajax"
									listenTopics="comboProductoParteDiario"
									formId="formularioFiltro">
								</s:div></td>
						</tr>
						<tr>
							<td><s:text
									name="notificacion.notificacionPlanta.etiqueta.anio"></s:text>
								:</td>
							<td><s:select name="anio" id="anio" value="%{anio}"
									list="anios" listKey="codigo" listValue="valor"
									emptyOption="true" cssClass="fecha" /></td>
							<td><s:text
									name="notificacion.notificacionPlanta.etiqueta.mes"></s:text> :
								<span class="campooblig">(*)</span></td>
							<td><s:select name="mes" id="mes" value="%{mes}"
									list="meses" emptyOption="true" /></td>

							<td><s:text
									name="manejoMaestro.proceso.etiqueta.tipoProducto"></s:text> :
							</td>
							<td><s:select name="tipoProducto" id="tipoProducto"
									value="%{tipoProducto}" list="tiposMaterial"
									emptyOption="false" /></td>
						</tr>
					</table>
				</s:form>
			</div>

			<div>
				<br>
				<s:if test="hasActionMessages()">
					<tr>
						<td colspan="4">
							<div class="advertencia">
								<s:actionmessage />
							</div>
						</td>
					</tr>
				</s:if>
			</div>
			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />
		</div>
	</div>
</body>
</html>

