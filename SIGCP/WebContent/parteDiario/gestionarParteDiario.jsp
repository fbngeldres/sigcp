<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>CEMENTOS PACASMAYO - Sistema de Gesti&oacute;n y Control
	de Producci&oacute;n</title>

<s:head theme="ajax" />

<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css" />
<script language="JavaScript" type='text/javascript'
	src='<%=request.getContextPath()%>/scripts/main.js'></script>
<script
	src="<%=request.getContextPath()%>/scripts/AC_RunActiveContent.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/fechaUtil.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/cookies.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/util.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/validacionFormularios.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/gestionarNotificacionPlanta.js"></script>
<script language="JavaScript" type='text/javascript'
	src='<%=request.getContextPath()%>/scripts/main.js'></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.4.min.js"></script>

</head>

<body onload="iniciarMenu()">
	<div class="content-ppal">

		<!-- INCLUDE HEADER -->
		<jsp:include page="../comun/cabeceraPagina.jsp" flush="true" />

		<!-- INCLUDE MENU -->
		<jsp:include page="../comun/menu.jsp" flush="true" />

		<!--  INICIO CONTENIDO INTERNO -->
		<div id="contenido" class="content-int">

			<!--  INICIO TITULO-->
			<div class="titulo">
				<s:text name="parteDiario.gestionar.titulo.principal"></s:text>
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
							<s:if test="funCerrarActivo">
								<td class="aprobar"><a href="#"
									onclick="javascript:procesarFormulario('notificacionesSearch','notificacion.codigo','cerrarNotificacionPlanta.action','\u00BFEst\u00E1 seguro de Cerrar el Parte Diario, no tendr\u00E1 opci\u00F3n a Reprocesar?');"><img
										alt=""
										src="<%=request.getContextPath()%>/images/transparente.gif">
								</a></td>
							</s:if>

							<s:if test="funEliminarActivo">
								<td class="reprocesar"><a href="#"
									onclick="javascript:procesarFormulario('notificacionesSearch','notificacion.codigo','reprocesarNotificacionPlanta.action','\u00BFEst\u00E1 seguro de Re-procesar Parte Diario?');">
										<img alt="" src="../images/transparente.gif">
								</a></td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td class="filtrar"><a href="#"
									onclick="javascript:btnFiltrarClicked('formularioFiltro');"><img
										alt=""
										src="<%=request.getContextPath()%>/images/transparente.gif">
								</a></td>
							</s:if>

							<s:if test="funRevertirActivo">
								<td class="reversion"><a href="#"
									onclick="javascript:procesarFormulario('notificacionesSearch','notificacion.codigo','abrirNotificacionPlanta.action','\u00BFEst\u00E1 seguro de abrir Parte Diario?');"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>

<s:if test="funCerrarActivo">
								<td class="email"><a href="#"
									onclick="javascript:enviarEmail('formularioFiltro','enviarReporteProduccionDiaria.action','\u00BFEst\u00E1 seguro que desea enviar el reporte de Produccion Diaria?');"><img
										alt=""
										src="<%=request.getContextPath()%>/images/transparente.gif">
								</a></td>
							</s:if>

						</tr>
						<tr>
							<s:if test="funCerrarActivo">
								<td><a href="#"
									onclick="javascript:procesarFormulario('notificacionesSearch','notificacion.codigo','cerrarNotificacionPlanta.action','\u00BFEst\u00E1 seguro de Cerrar el Parte Diario, no tendr\u00E1 opci\u00F3n a Reprocesar?');"><s:text
											name="boton.cerrar.pd"></s:text> </a></td>
							</s:if>


							<s:if test="funEliminarActivo">
								<td><a href="#"
									onclick="javascript:procesarFormulario('notificacionesSearch','notificacion.codigo','reprocesarNotificacionPlanta.action','\u00BFEst\u00E1 seguro de Re-procesar Parte Diario?');"><s:text
											name="boton.anular.pd"></s:text> </a></td>
							</s:if>

							<s:if test="funConsultarActivo">
								<td><a href="#"
									onclick="javascript:btnFiltrarClicked('formularioFiltro');"><s:text
											name="boton.filtrar.pd"></s:text> </a></td>
							</s:if>
							<s:if test="funRevertirActivo">
								<td><a href="#"
									onclick="javascript:procesarFormulario('notificacionesSearch','notificacion.codigo','abrirNotificacionPlanta.action','\u00BFEst\u00E1 seguro de abrir Parte Diario?');">Abrir</a>
								</td>
							</s:if>

<s:if test="funCerrarActivo">
								<td><a href="#"
									onclick="javascript:enviarEmail('formularioFiltro','enviarReporteProduccionDiaria.action','\u00BFEst\u00E1 seguro que desea enviar el reporte de Produccion Diaria?');">Enviar Email</a>
								</td>
							</s:if>
						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<div class="content">
				<div class="titulo">
					<s:text name="parteDiario.gestionar.titulo.gestion"></s:text>
				</div>
				<div id="formularios">
					<s:form method="post" name="formularioFiltro" id="formularioFiltro"
						namespace="/notificacion" action="gestionarParteDiario.action"
						theme="simple">
						<input type="hidden" id="valorCargaCompletada"
							name="valorCargaCompletada" />
						<s:hidden id="elementoNoSeleccionado"
							value="%{elementoNoSeleccionado}"></s:hidden>
						<s:hidden id="mensajeErrorValidacion"
							value="%{mensajeErrorValidacion}"></s:hidden>
						<s:hidden id="tituloCampoDivision" value="%{tituloCampoDivision}"></s:hidden>
						<s:hidden id="tituloCampoSociedad" value="%{tituloCampoSociedad}"></s:hidden>
						<s:hidden id="tituloCampoUnidad" value="%{tituloCampoUnidad}"></s:hidden>
						<s:hidden id="tituloCampoLineaNegocio"
							value="%{tituloCampoLineaNegocio}"></s:hidden>
						<s:hidden id="tituloCampoMes" value="%{tituloCampoMes}"></s:hidden>
						<s:hidden id="codUnidad" value="%{valorUnidad}"></s:hidden>

						<!--  Filtros -->
						<s:hidden id="valorDivisionFiltrado" name="valorDivisionFiltrado"
							value="%{valorDivisionFiltrado}"></s:hidden>
						<s:hidden id="valorSociedadFiltrado" name="valorSociedadFiltrado"
							value="%{valorSociedadFiltrado}"></s:hidden>
						<s:hidden id="valorUnidadFiltrado" name="valorUnidadFiltrado"
							value="%{valorUnidadFiltrado}"></s:hidden>
						<s:hidden id="valorLineaNegocioFiltrado"
							name="valorLineaNegocioFiltrado"
							value="%{valorLineaNegocioFiltrado}"></s:hidden>
						<s:hidden id="valorEstadoFiltrado" name="valorEstadoFiltrado"
							value="%{valorEstadoFiltrado}"></s:hidden>
						<s:hidden id="valorAnioFiltrado" name="valorAnioFiltrado"
							value="%{valorAnioFiltrado}"></s:hidden>
						<s:hidden id="valorMesFiltrado" name="valorMesFiltrado"
							value="%{valorMesFiltrado}"></s:hidden>


						<table>
							<tr>
								<td><s:text name="manejoMaestro.sociedad.etiqueta.division"></s:text>
									: <span class="campooblig">(*)</span></td>
								<td><s:select name="valorDivision" id="valorDivision"
										value="%{valorDivision}" list="divisiones" listKey="codigo"
										listValue="nombre" emptyOption="true"
										onchange="javascript:update('comboSociedades');
												return false;"
										onclick="eliminarValoresDivision();" theme="simple" /></td>
								<td><s:text name="manejoMaestro.unidad.etiqueta.sociedad"></s:text>
									: <span class="campooblig">(*)</span></td>
								<td><s:url id="detalleComboSociedades"
										action="cargarSociedadesGestionarPD" includeParams="none" />
									<s:div showLoadingText="true" id="comboSociedades"
										href="%{detalleComboSociedades}" theme="ajax"
										listenTopics="comboSociedades" formId="formularioFiltro"
										executeScripts="true" onclick="eliminarValoresSociedad()">
									</s:div></td>
								<td><s:text
										name="manejoMaestro.lineaNegocio.etiqueta.unidad"></s:text> :
									<span class="campooblig">(*)</span></td>
								<td><s:url id="detalleComboUnidades"
										action="cargarUnidadesGestionarPD" includeParams="none" /> <s:div
										showLoadingText="true" id="comboUnidades"
										href="%{detalleComboUnidades}" theme="ajax"
										listenTopics="comboUnidades" formId="formularioFiltro"
										executeScripts="true" onclick="eliminarValoresUnidad();">
									</s:div></td>
							</tr>
							<tr>
								<td><s:text
										name="manejoMaestro.proceso.etiqueta.lineaNegocio"></s:text> :
									<span class="campooblig">(*)</span></td>
								<td><s:url id="detalleComboLineasNegocio"
										action="cargarLineasNegocioGestionarPD" includeParams="none" />
									<s:div showLoadingText="true" id="comboLineasNegocio"
										href="%{detalleComboLineasNegocio}" theme="ajax"
										listenTopics="comboLineasNegocio" formId="formularioFiltro"
										onclick="eliminarValoresLinea();">
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
										name="notificacion.notificacionPlanta.etiqueta.mes"></s:text>
									: <span class="campooblig">(*)</span></td>
								<td><s:select name="mes" id="mes" value="%{mes}"
										list="meses" listKey="codigo" listValue="mes"
										emptyOption="true" /></td>
								<td><s:text
										name="notificacion.notificacionPlanta.etiqueta.estadoNotificacion"></s:text>
									:</td>
								<td><s:select name="valorEstadoNotificacion"
										id="valorEstadoNotificacion"
										value="%{valorEstadoNotificacion}" list="estados"
										listKey="codigo" listValue="nombreEstadoNotificacion"
										emptyOption="true" /></td>
							</tr>
						</table>
					</s:form>
				</div>
				<table width="100%">
					<tr>
						<td>
							<div id="loading">&nbsp;</div>
						</td>
					</tr>
					<tr>
						<td align="center"><s:form method="post"
								name="notificacionesSearch" namespace="/stock"
								id="notificacionesSearch">
								<div style="padding-left: 5px; padding-top: 15px;">
									<table class="dataTable2">
										<tr>
											<td><display:table name="notificacionesPlanta"
													id="notificacionesPlantaTabla" pagesize="10"
													cellpadding="0" cellspacing="0" defaultorder="ascending"
													defaultsort="3" sort="list" requestURI="" class="dataTable">
													<display:setProperty name="basic.empty.showtable">true</display:setProperty>
													<display:column title="Selección" sortable="true">
														<input type="radio" class="radiobutton"
															name="notificacion.codigo"
															value="${notificacionesPlantaTabla.codigo}" />
													</display:column>
													<display:column title="Código" property="codigo"
														sortable="true" />
													<display:column title="Fecha de Notificacion"
														property="fechaNotificacionDiaria"
														format="{0,date,dd/MM/yyyy}" sortable="true" />
													<display:column title="Estado"
														property="estadoNotificacion.nombreEstadoNotificacion"
														sortable="false" />
													<display:column title="Usuario Aprueba"
														property="usuarioAprueba.nombre" sortable="true" />
													<display:column title="Usuario Cierra"
														property="usuarioCierra.nombre" sortable="true" />
												</display:table></td>
										</tr>
									</table>
								</div>
							</s:form></td>
					</tr>
					<tr>
						<td colspan="6" align="left" width="100%">
							<div>
								<s:if test="hasActionMessages()">
									<tr>
										<td colspan="4">
											<div class="exito">
												<s:actionmessage />
											</div>
										</td>
									</tr>
								</s:if>
								<s:if test="hasActionErrors()">
									<tr>
										<td colspan="4">
											<div class="error">
												<s:actionerror />
											</div>
										</td>
									</tr>
								</s:if>

							</div>
						</td>
					</tr>
				</table>

			</div>

			<!-- INCLUDE FOOTER -->

			<jsp:include page="../comun/piePagina.jsp" flush="true" />

		</div>
	</div>

</body>
</html>
