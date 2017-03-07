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

<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/util.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/parteTecnico.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/manejoMaestros.js"></script>

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
				<s:text name="parteTecnico.ajusteProduccionMes.titulo.principal" />
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
							<s:if test="funNuevoActivo">
								<td class="nuevo"><a href="ajusteProduccion.action"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>
							<s:if test="funAprobarActivo">
								<td class="aprobar"><a href="#"
									onclick="javascript:showLoading(); procesarFormulario(document.ajuste.id,'ajuste.codigoAjuste','un Ajuste de Producción','Ajuste','Aprobar');">
										<img alt="" src="../images/transparente.gif">
								</a></td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td class="filtrar"><a href="#"
									onclick="javascript:filtrarAjustes('formularioFiltro');"> <img
										alt="" src="../images/transparente.gif">
								</a></td>
							</s:if>
							<s:if test="funGestionarActivo">
								<td class="anular"><a href="gestionarParteTecnico.action">
										<img alt="" src="../images/transparente.gif">
								</a></td>
							</s:if>

							<s:if test="funEliminarActivo">
								<td class="eliminar"><a href="#"
									onclick="javascript:eliminarAjusteParteTecnico(document.ajuste.id,'ajuste.codigoAjuste','un Ajuste de Producción','Ajuste','Aprobar');">
										<img alt="" src="../images/transparente.gif">
								</a></td>
							</s:if>
						</tr>
						<tr>
							<s:if test="funNuevoActivo">
								<td><a href="ajusteProduccion.action">Ajustar</a></td>
							</s:if>
							<s:if test="funAprobarActivo">
								<td><a href="#"
									onclick="javascript:showLoading(); procesarFormulario(document.ajuste.id,'ajuste.codigoAjuste','un Ajuste de Producción','Ajuste','Aprobar');">Aprobar</a>
								</td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td><a href="#"
									onclick="javascript:filtrarAjustes('formularioFiltro');">Filtrar</a>
								</td>
							</s:if>

							<s:if test="funGestionarActivo">
								<td><a href="gestionarParteTecnico.action">Gestionar</a></td>
							</s:if>
							<s:if test="funEliminarActivo">
								<td><a href="#"
									onclick="javascript:eliminarAjusteParteTecnico(document.ajuste.id,'ajuste.codigoAjuste','un Ajuste de Producción','¿Esta seguro que desea eliminar el regitro seleccionado?','Ajuste','Eliminar');"
									onclick="#">Eliminar</a></td>
							</s:if>

						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<div class="content">
				<div class="titulo">
					<s:text name="parteTecnico.ajusteProduccionMes.titulo.consultar"></s:text>
				</div>
				<div id="formularios">
					<s:form theme="simple" id="formularioFiltro"
						name="formularioFiltro" action="listarAjusteProduccionMes">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td width="20%" colspan="1">
									<table>
										<tr>
											<td><s:text
													name="manejoMaestro.proceso.etiqueta.lineaNegocio" /> :</td>
											<td>
												<table>
													<tr>
														<td><s:select name="lineaSeleccionada"
																id="lineaSeleccionada" value="%{lineaSeleccionada}"
																list="lineas" listKey="codigo" listValue="nombre"
																theme="simple" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td width="20%" colspan="1">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td><s:text
													name="parteTecnico.ajusteProduccionMes.etiqueta.estado" />
												:</td>
											<td>
												<table>
													<tr>
														<td><s:select
																name="estadoAjusteProduccionSeleccionada"
																id="estadoAjusteProduccionSeleccionada"
																value="%{estadoAjusteProduccionSeleccionada}"
																list="estadosAjusteProduccion" listKey="codigo"
																listValue="nombre" headerValue="Seleccione" headerKey=""
																theme="simple" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td width="20%" colspan="1">
									<table>
										<tr>
											<td><s:text
													name="parteTecnico.ajusteProduccionMes.etiqueta.anio" /> :
											</td>
											<td>
												<table>
													<tr>
														<td><s:select name="anioSeleccionado"
																id="anioSeleccionado" value="%{anioSeleccionado}"
																list="anios" headerValue="Seleccione" headerKey="null"
																theme="simple" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td width="20%" colspan="1">
									<table>
										<tr>
											<td><s:text
													name="parteTecnico.ajusteProduccionMes.etiqueta.mes" /> :
											</td>
											<td>
												<table>
													<tr>
														<td><s:select name="mesSeleccionado"
																id="mesSeleccionado" value="%{mesSeleccionado}"
																list="meses" headerValue="Seleccione" headerKey=""
																theme="simple" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</s:form>
					<table width="100%">
						<tr>
							<td>
								<div id="loading"></div>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center" width="100%"><s:form
									method="post" name="ajuste" namespace="parteTecnico"
									id="ajuste">

									<s:if test="ajustesProduccionDetalle.size > 0">
										<div>
											<table class="dataTable2">
												<tr>
													<td><display:table name="ajustesProduccionDetalle"
															id="ajustesProduccionDetalleTable" pagesize="10"
															cellpadding="0" cellspacing="0" defaultorder="ascending"
															defaultsort="1" sort="list" requestURI=""
															class="dataTable">
															<display:column title="Selecci&oacute;n">
																<input type="radio" name="ajuste.codigoAjuste"
																	value="${ajustesProduccionDetalleTable.codigoAjuste}"
																	style="width: 10px" />
															</display:column>
															<display:column title="Año - Mes de Ajuste"
																property="mesAnio" />
															<display:column title="Sociedad" property="sociedad" />
															<display:column title="Unidad" property="unidad" />
															<display:column title="L&iacute;nea de Negocio"
																property="lineaNegocio" />
															<display:column title="Estado" property="estado" />
															<display:column title="Usuario Ajusta"
																property="usuarioAjusta" />
															<display:column title="Usuario Aprueba"
																property="usuarioAprueba" />
															<display:column title="Fecha Aprueba"
																property="fechaAprueba" />


															<display:column title="Envio Consumo SAP"
																property="mensajeConsumo" />

															<display:column title="Envio Combustible SAP"
																property="mensajeCombustible" />


														</display:table></td>
												</tr>
											</table>
										</div>
									</s:if>

								</s:form></td>
						</tr>
						<tr>
							<td colspan="4" align="left" width="100%">
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

						<s:if test="activoLog">
							<br />
							<tr>

								<td><s:textarea cssClass="logAjusteProduccion" rows="15"
										cols="140" name="log" value="%{log}"></s:textarea></td>

							</tr>
						</s:if>
					</table>
				</div>
			</div>
			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />
		</div>
	</div>
</body>
</html>