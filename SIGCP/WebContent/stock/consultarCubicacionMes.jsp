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
	src="<%=request.getContextPath()%>/scripts/fechaUtil.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.4.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/util.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/validacionFormularios.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/consultarCubicacion.js"></script>

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
				<s:text name="stock.consultaCubicacionMes"></s:text>
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
						
							<s:if test="funNuevoActivo">
								<td class="nuevo"><a href="registrarCubicacion.action"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>
							<s:if test="funModificarActivo">
								<td class="modificar"><a href="#"
									onclick="javascript:procesarFormularioGWT(document.cubicacionesSearch.id,'cubicacionProductoBean.codigo','una Cubicación','CubicacionProducto','detallar');"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>
							<s:if test="funAprobarActivo">
								<td class="aprobar"><a href="#"
									onclick="javascript:procesarFormulario(document.cubicacionesSearch.id,'cubicacionProductoBean.codigo','una Cubicación','CubicacionProducto','aprobar');"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>
							<s:if test="funEliminarActivo">
								<td class="anular"><a href="#"
									onclick="javascript:procesarFormulario(document.cubicacionesSearch.id,'cubicacionProductoBean.codigo','una Cubicación','CubicacionProducto','anular');"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td class="filtrar"><a href="#"
									onclick="javascript:filtrar('formularioFiltro');insertarCarga('linkFiltrarCubicacion');">
										<img alt="" src="../images/transparente.gif">
								</a></td>
							</s:if>
							<s:if test="funRevertirActivo">
								<td class="reversion"><a href="#"
									onclick="javascript:revertir(document.cubicacionesSearch.id,'cubicacionProductoBean.codigo','una Cubicación','CubicacionProducto','revertir');">
										<img alt="" src="../images/transparente.gif">
								</a></td>
							</s:if>
						</tr>
						<tr>
							<s:if test="funNuevoActivo">
								<td><a href="registrarCubicacion.action">Nuevo</a></td>
							</s:if>
							<s:if test="funModificarActivo">
								<td><a href="#"
									onclick="javascript:procesarFormularioGWT(document.cubicacionesSearch.id,'codigosCubicaciones','una Cubicación','CubicacionProducto','detallar');">Modificar</a>
								</td>
							</s:if>
							<s:if test="funAprobarActivo">
								<td><a href="#"
									onclick="javascript:procesarFormulario(document.cubicacionesSearch.id,'codigosCubicaciones','una Cubicación','CubicacionProducto','aprobar');">Aprobar</a>
								</td>
							</s:if>
							<s:if test="funEliminarActivo">
								<td><a href="#"
									onclick="javascript:procesarFormulario(document.cubicacionesSearch.id,'codigosCubicaciones','una Cubicación','CubicacionProducto','anular');">Anular</a>
								</td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td><a href="#"
									onclick="javascript:filtrar('formularioFiltro');insertarCarga('linkFiltrarCubicacion');">Filtrar</a>
								</td>
							</s:if>
							<s:if test="funRevertirActivo">
								<td><a href="#"
									onclick="javascript:revertir(document.cubicacionesSearch.id,'codigosCubicaciones','una Cubicación','CubicacionProducto','revertir');">Revertir</a>
								</td>
							</s:if>
						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->

			<div class="content">
				<div class="titulo">
					<s:text name="stock.consultaCubicacionMes"></s:text>
				</div>
				<div id="formularios">
					<s:form method="post" namespace="/stock" name="formularioFiltro"
						id="formularioFiltro" theme="simple">
						<input type="hidden" id="valorCargaCompletada"
							name="valorCargaCompletada" />
						<s:hidden id="mensajeErrorValidacion"
							value="%{mensajeErrorValidacion}"></s:hidden>
						<s:hidden id="tituloCampoDivision" value="%{tituloCampoDivision}"></s:hidden>
						<s:hidden id="tituloCampoSociedad" value="%{tituloCampoSociedad}"></s:hidden>
						<s:hidden id="tituloCampoUnidad" value="%{tituloCampoUnidad}"></s:hidden>
						<s:hidden id="tituloCampoLineaNegocio"
							value="%{tituloCampoLineaNegocio}"></s:hidden>
						<s:hidden id="tituloCampoMes" value="%{tituloCampoMes}"></s:hidden>

						<s:hidden name="valorDivisionFiltrado" id="valorDivisionFiltrado"
							value="%{valorDivisionFiltrado}"></s:hidden>
						<s:hidden name="valorSociedadFiltrado" id="valorSociedadFiltrado"
							value="%{valorSociedadFiltrado}"></s:hidden>
						<s:hidden name="valorUnidadFiltrado" id="valorUnidadFiltrado"
							value="%{valorUnidadFiltrado}"></s:hidden>
						<s:hidden name="valorLineaNegocioFiltrado"
							id="valorLineaNegocioFiltrado"
							value="%{valorLineaNegocioFiltrado}"></s:hidden>
						<s:hidden name="valorProcesoFiltrado" id="valorProcesoFiltrado"
							value="%{valorProcesoFiltrado}"></s:hidden>
						<s:hidden name="valorProductoFiltrado" id="valorProductoFiltrado"
							value="%{valorProductoFiltrado}"></s:hidden>

						<table>
							<tr>
								<td><s:text name="manejoMaestro.sociedad.etiqueta.division"></s:text>
									: <span class="campooblig">(*)</span></td>
								<td><s:select name="valorDivision" id="valorDivision"
										value="%{valorDivision}" list="divisiones" listKey="codigo"
										listValue="nombre" emptyOption="true" cssClass="cortoCombo"
										onchange="javascript:update('comboSociedades');return false;" />
								</td>
								<td><s:text name="manejoMaestro.unidad.etiqueta.sociedad"></s:text>
									: <span class="campooblig">(*)</span></td>
								<td><s:url id="detalleComboSociedades"
										action="cargarSociedadesConsultaCubicacion"
										includeParams="none" /> <s:div showLoadingText="true"
										id="comboSociedades" href="%{detalleComboSociedades}"
										theme="ajax" listenTopics="comboSociedades"
										formId="formularioFiltro" executeScripts="true">
									</s:div></td>
								<td><s:text
										name="manejoMaestro.lineaNegocio.etiqueta.unidad"></s:text> :
									<span class="campooblig">(*)</span></td>
								<td><s:url id="detalleComboUnidades"
										action="cargarUnidadesConsultaCubicacion" includeParams="none" />
									<s:div showLoadingText="true" id="comboUnidades"
										href="%{detalleComboUnidades}" theme="ajax"
										listenTopics="comboUnidades" formId="formularioFiltro"
										executeScripts="true">
									</s:div></td>
							</tr>
							<tr>
								<td><s:text
										name="manejoMaestro.proceso.etiqueta.lineaNegocio"></s:text> :
									<span class="campooblig">(*)</span></td>
								<td><s:url id="detalleComboLineasNegocio"
										action="cargarLineasNegocioConsultaCubicacion"
										includeParams="none" /> <s:div showLoadingText="true"
										id="comboLineaNegocioMovProd"
										href="%{detalleComboLineasNegocio}" theme="ajax"
										listenTopics="comboLineaNegocio" formId="formularioFiltro"
										executeScripts="true">
									</s:div></td>
								<td><s:text name="manejoMaestro.proceso.etiqueta.proceso"></s:text>
									:</td>
								<td><s:url id="detalleComboProcesos"
										action="cargarProcesosConsultaCubicacion" includeParams="none" />
									<s:div showLoadingText="true" id="comboProcesos"
										href="%{detalleComboProcesos}" theme="ajax"
										listenTopics="comboProcesos" formId="formularioFiltro"
										executeScripts="true">
									</s:div></td>
								<td><s:text name="manejoMaestro.proceso.etiqueta.producto"></s:text>
									:</td>
								<td><s:url id="detalleComboProductos"
										action="cargarProductosConsultaCubicacion"
										includeParams="none" /> <s:div showLoadingText="true"
										id="comboProductos" href="%{detalleComboProductos}"
										theme="ajax" listenTopics="comboProductos"
										formId="formularioFiltro" executeScripts="true">
									</s:div></td>
							</tr>
							<tr>
								<td><s:text name="stock.consultaCubicacionMes.estado"></s:text>
									:</td>
								<td><s:select name="valorEstadoCub" id="valorEstadoCub"
										value="%{valorEstadoCub}" list="estadosCubicacion"
										listKey="codigo" listValue="nombre" emptyOption="true" /></td>

								<td><s:text name="stock.consultaCubicacionMes.anio"></s:text>
									:</td>
								<td><s:select name="anioCubicacion" id="anioCubicacion"
										value="%{anioCubicacion}" cssClass="fecha" list="anios"
										listKey="codigo" listValue="valor" emptyOption="true" /></td>
								<td><s:text name="stock.consultaCubicacionMes.mes"></s:text>
									: <span class="campooblig">(*)</span></td>
								<td><s:select name="mesCubicacion" id="mesCubicacion"
										value="%{mesCubicacion}" list="meses" emptyOption="true"
										cssClass="cortoCombo" /></td>
							</tr>
						</table>
					</s:form>
				</div>
				<div id="linkFiltrarCubicacion"></div>
				<table width="100%">
					<tr>
						<td align="center"><s:form method="post"
								name="cubicacionesSearch" namespace="/stock"
								id="cubicacionesSearch">
								<div style="padding-left: 5px; padding-top: 15px;">
									<table class="dataTable2">
										<tr>
											<td><display:table name="cubicacionesProductos"
													id="produccionesTable" pagesize="10" cellpadding="0"
													cellspacing="0" defaultorder="ascending" defaultsort="1"
													sort="list" requestURI="" class="dataTable">
													<display:caption style="text-align: left;margin-left: 23px">
														<input type="checkbox" name="chkPadre" value=""
															onClick="javascript:jselecCheckBox('cubicacionesSearch','codigosCubicaciones');"
															title="Seleccionar Todos">
													</display:caption>
													<display:column>
														<input type="checkbox" name="codigosCubicaciones"
															value="${produccionesTable.codigo}" class="radio" />
													</display:column>
													<display:column title="Año"
														property="anoCubicacionproducto" sortable="true" />

													<display:column title="Mes"
														property="mesCubicacionproducto" sortable="true" />

													<display:column title="Unidad"
														property="lineanegocio.unidad.nombre" sortable="true" />


													<display:column title="Línea de Negocio"
														property="lineanegocio.nombre" sortable="true" />

													<display:column title="Proceso"
														property="produccion.proceso.nombre" sortable="true" />

													<display:column title="Producto"
														property="produccion.producto.nombre" sortable="true" />

													<display:column title="Estado"
														property="estadocubicacion.nombre" sortable="true" />

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
