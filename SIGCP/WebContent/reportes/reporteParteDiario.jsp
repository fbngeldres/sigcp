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



<%--		<script type="text/javascript"--%>
<%--			src="<%=request.getContextPath()%>/scripts/manejoMaestros.js"></script>	--%>

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
							<s:if test="funConsultarActivo">
								<td class="excel"><a href="#"
									onclick="javascript: cambiarActionEXCEL('formularioFiltro',0,2);"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td class="pdfIcon"><a href="#"
									onclick="javascript: cambiarActionPDF('formularioFiltro',0,2);"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>

						</tr>
						<tr>
							<s:if test="funConsultarActivo">
								<td><a href="#"
									onclick="javascript: cambiarActionEXCEL('formularioFiltro',0,2);">Exporta&nbsp;Excel</a>
								</td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td><a href="#"
									onclick="javascript: cambiarActionPDF('formularioFiltro',0,2);">Exporta&nbsp;PDF</a>
								</td>
							</s:if>

						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<div class="content">

				<s:form method="post" namespace="reportes" name="formularioFiltro"
					id="formularioFiltro" action="" target="" theme="simple">
					<s:hidden name="valorSociedadFiltrado"
						value="%{valorSociedadFiltrado}"></s:hidden>
					<s:hidden name="valorUnidadFiltrado" value="%{valorUnidadFiltrado}"></s:hidden>
					<!-- valor si es PDF O XLS -->
					<s:hidden name="xlsPDF" id="xlsPDF"></s:hidden>
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
									listenTopics="comboSociedadesParteDiario" executeScripts="true"
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
<!-- 						<tr> -->
<%-- 							<td><s:text name="manejoMaestro.alcance.reporte"></s:text> : --%>
<%-- 								<span class="campooblig">(*)</span></td> --%>
<!-- 							,3:'Detalle Por Puesto de Trabajo',4:'Consumo de Componentes por Producto', 5:'Detalle de Produccion' -->
<%-- 							<td colspan="2"><s:select name="valorReporte" headerKey="-1" --%>
<%-- 									id="valorReporte" cssClass="select_largo" --%>
<%-- 									headerValue="Seleccione Reporte" --%>
<%-- 									list="#{1:'Parte Diario',2:'Produccion Por Puesto de Trabajo'}" --%>
<%-- 									value="valorReporte" onchange="javascript:elegirReporte(this);" /> --%>

<!-- 							</td> -->
<!-- 						</tr> -->
						
						<tr>
								<td><s:text name="mensaje.validacion.anio" /></td>

								<td><s:select list="anios" name="anio" listKey="codigo"
										value="%{anio}" listValue="valor" emptyOption="true"></s:select>
								</td>


								<td><s:text name="mensaje.validacion.mes" /></td>

								<td><s:select list="meseslst" listKey="codigo" name="mes"
										listValue="mes" emptyOption="true" value="%{mes}"></s:select>
								</td>

							</tr>

							<tr>
								<td id="lblproceso"><s:text name="Proceso"></s:text> :</td>
								<td id="cmbproceso"><s:select id="valorProceso"
										name="valorProceso" value="%{valorProceso}" list="procesos"
										listKey="codigo" listValue="nombre" emptyOption="true"
										cssClass="select_largo"
										onchange="javascript:update('comboProductoPuestoTrabajo');return false;" />
								</td>
								<td id="lblproducto"><s:text name="Producto"></s:text> :</td>
								<td id="cmbproducto"><s:url
										id="detalleComboProductoParteDiario"
										action="cargaProductoReporteParteDiario" /> <s:div
										showLoadingText="true" id="comboProductoParteDiario"
										href="%{detalleComboProductoParteDiario}" theme="ajax"
										listenTopics="comboProductoPuestoTrabajo"
										formId="formularioFiltro">
									</s:div></td>
								<td id="lblpuestotrabajo"><s:text name="Puesto de Trabajo"></s:text>
									:</td>
								<td id="cmbpuestotrabajo"><s:url
										id="detalleComboPuestoTrabajo"
										action="cargaPuestoTrabajoReporteParteDiario" /> <s:div
										showLoadingText="true" id="comboPuestoTrabajo"
										href="%{detalleComboPuestoTrabajo}" theme="ajax"
										listenTopics="comboProductoPuestoTrabajo"
										formId="formularioFiltro">
									</s:div></td>



							</tr>
						

						<tr>
							<td></td>
							<td colspan="6"></td>
						</tr>
					</table>



					<div id="formularioReporte">
						<table>
							


						</table>

					</div>

				</s:form>




			</div>



			<div>
				<br>
				<s:if test="hasActionMessages()">
					<tr>
						<td colspan="4">p
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
</html>

