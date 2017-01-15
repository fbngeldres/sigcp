<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<s:head theme="ajax" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

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
	src="<%=request.getContextPath()%>/scripts/ui.datepicker-es.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/util.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/centroCostos.js"></script>

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
			<h4 class="titulo">
				<s:text name="notificacion.notificacionProduccion.titulo"></s:text>
			</h4>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
						</tr>
						<tr>
						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<s:if test="valorTipoCambioProduccion == 1">
				<div class="content">
					<div class="titulo">
						<s:text
							name="notificacion.notificacionProduccion.titulo.cambioProduccion"></s:text>
					</div>
					<div id="formularios">
						<table>
							<tr>
								<td>
									<!-- CONTENIDO FORMULARIOS -->
								</td>
							</tr>
						</table>
					</div>
					<div class="content">

						<s:hidden name="codigo" value="%{codigo}" />

						<!-- CONTENIDO PAGINA -->
						<s:form action="modificarNotificacionProduccion.action"
							namespace="/notificacion" method="post" theme="simple"
							id="formularioFiltro" name="formularioFiltro">

							<table>

								<tr>
									<td><s:text
											name="notificacion.notificacionProduccion.modificacion.horaExacta" />

									</td>
									<td><s:text name="%{hora}"></s:text> : <s:select
											name="minuto" id="minuto" list="minutos"></s:select></td>
								</tr>

								<tr>
									<s:hidden name="notificacionProduccionCodigo"
										value="%{notificacionProduccionCodigo}"></s:hidden>
									<s:hidden name="horaCambioProduccion"
										value="%{horaCambioProduccion}"></s:hidden>

								</tr>
								<tr>
									<td></td>
									<td class="width_buttons"><s:submit key="boton.guardar"
											cssClass="buttonaceptar" /> <s:submit key="boton.cancelar"
											cssClass="buttoncancelar"
											name="redirect-action:tipoCambioProduccion" /></td>
								</tr>
							</table>
						</s:form>
						<s:if test="hasActionErrors()">
							<tr>
								<td colspan="4">
									<div class="advertencia">
										<s:actionerror />
									</div>
								</td>
							</tr>
						</s:if>
						<s:if test="hasFieldErrors()">
							<tr>
								<td colspan="4">
									<div class="advertencia">
										<img
											src="<%=request.getContextPath()%>/images/warning_16x16.gif" />
										<s:fielderror></s:fielderror>
									</div>
								</td>
							</tr>
						</s:if>
					</div>
				</div>
			</s:if>
			<s:elseif test="valorTipoCambioProduccion == 2">
				<div class="content">
					<div class="titulo">
						<s:text
							name="notificacion.notificacionProduccion.titulo.cambioProduccionLavado"></s:text>
					</div>
					<div id="formularios">
						<table>
							<tr>
								<td>
									<!-- CONTENIDO FORMULARIOS -->
								</td>
							</tr>
						</table>
					</div>
					<div class="content">

						<s:hidden name="codigo" value="%{codigo}" />

						<!-- CONTENIDO PAGINA -->
						<s:form action="crearNotificacionProduccionLavado.action"
							namespace="/notificacion" method="post" theme="simple"
							id="formularioFiltro" name="formularioFiltro">

							<table>

								<tr>
									<td><s:text
											name="notificacion.notificacionProduccion.modificacion.ordenProduccion"></s:text>
										<s:select list="ordenesProduccion" name="ordenProduccion"
											id="ordenProduccion" listValue="numeroOrden" listKey="codigo"></s:select>
									</td>
								</tr>
								<tr>
									<td><s:text
											name="notificacion.notificacionProduccion.modificacion.silo"></s:text>

									</td>
								</tr>
								<tr>
									<td><s:select list="silos" name="silo" id="silo"
											listValue="nombre" listKey="codigo"></s:select></td>
								</tr>


								<tr>
									<s:hidden name="notificacionProduccionCodigo"
										value="%{notificacionProduccionCodigo}"></s:hidden>

								</tr>
								<tr>
									<td></td>
									<td class="width_buttons"><s:submit key="boton.guardar"
											cssClass="buttonaceptar" /> <s:submit key="boton.cancelar"
											cssClass="buttoncancelar"
											name="redirect-action:tipoCambioProduccion" /></td>
								</tr>
							</table>
						</s:form>
						<s:if test="hasActionErrors()">
							<tr>
								<td colspan="4">
									<div class="advertencia">
										<s:actionerror />
									</div>
								</td>
							</tr>
						</s:if>
						<s:if test="hasFieldErrors()">
							<tr>
								<td colspan="4">
									<div class="advertencia">
										<img
											src="<%=request.getContextPath()%>/images/warning_16x16.gif" />
										<s:fielderror></s:fielderror>
									</div>
								</td>
							</tr>
						</s:if>
					</div>
				</div>
			</s:elseif>
			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />
		</div>
	</div>
</body>
</html>