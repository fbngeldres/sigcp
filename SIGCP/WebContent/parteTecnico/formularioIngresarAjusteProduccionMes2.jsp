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

					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<div class="content">
				<div class="titulo">
					<s:text name="parteTecnico.ajusteProduccionMes.titulo.crear"></s:text>
				</div>
				<div id="formularios">
					<s:form theme="simple" id="frm_registroAjusteProduccion"
						name="frm_registroAjusteProduccion"
						action="ingresarAjusteProduccionMes">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td width="20%" colspan="1">
									<table>
										<tr>
											<td><s:text
													name="manejoMaestro.proceso.etiqueta.lineaNegocio" />:</td>
											<td>
												<table>
													<tr>
														<td><s:select name="lineaSeleccionada"
																id="lineaSeleccionada" value="%{lineaSeleccionada}"
																list="lineas" listKey="codigo" listValue="nombre"
																onchange="javascript:show_gruposAjuste();return false;"
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
													name="parteTecnico.ajusteProduccionMes.etiqueta.estado" />:
											</td>
											<td>
												<table>
													<tr>
														<td><s:select
																name="estadoAjusteProduccionSeleccionada"
																id="estadoAjusteProduccionSeleccionada"
																value="%{estadoAjusteProduccionSeleccionada}"
																list="estadosAjusteProduccion" listKey="codigo"
																listValue="nombre" headerValue="Seleccione"
																headerKey="-1" theme="simple" /></td>
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
													name="parteTecnico.ajusteProduccionMes.etiqueta.anio" />:
											</td>
											<td>
												<table>
													<tr>
														<td><s:select name="anioSeleccionado"
																id="anioSeleccionado" value="%{anioSeleccionado}"
																list="anios" headerValue="Seleccione" headerKey="-1"
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
													name="parteTecnico.ajusteProduccionMes.etiqueta.mes" />:</td>
											<td>
												<table>
													<tr>
														<td><s:select name="mesSeleccionado"
																id="mesSeleccionado" value="%{mesSeleccionado}"
																list="meses" headerValue="Seleccione" headerKey="-1"
																theme="simple" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="1">
									<table>
										<tr>
											<td><s:text
													name="parteTecnico.ajusteProduccionMes.etiqueta.grupoAjuste" />:
											</td>
											<td><s:url id="d_url_grupos" action="listarGrupoAjuste" />
												<s:div showLoadingText="true"
													errorText="Error al intentar listar los grupos"
													id="lstgrupos" href="%{d_url_grupos}" theme="ajax"
													listenTopics="listar_grupos"
													formId="frm_registroAjusteProduccion">
												</s:div></td>
										</tr>
									</table>
								</td>
								<td colspan="1">
									<table>
										<tr>
											<td><s:text
													name="parteTecnico.ajusteProduccionMes.etiqueta.grupoProductos" />:
											</td>
											<td><s:url id="d_url_grupoProductos"
													action="listarGrupoProuctosAjuste" /> <s:div
													showLoadingText="true"
													errorText="Error al intentar listar los productos"
													id="lstgrupoProductos" href="%{d_url_grupoProductos}"
													theme="ajax" listenTopics="listar_grupoProductos"
													formId="frm_registroAjusteProduccion">
												</s:div></td>
										</tr>
									</table>
								</td>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td colspan="4" align="left" width="100%">
									<div>
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
								</td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />
		</div>
	</div>
</body>
</html>