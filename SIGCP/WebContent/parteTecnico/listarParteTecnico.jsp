<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.DecimalFormat"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<s:head theme="ajax" />

<title>CEMENTOS PACASMAYO - Sistema de Gestión y Control de
	Producción</title>

<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/blitzer/jquery-ui-1.7.2.custom.css"
	rel="stylesheet" />
<script language="JavaScript" type='text/javascript'
	src='<%=request.getContextPath()%>/scripts/main.js'></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/accordian.pack.js"></script>
<script
	src="<%=request.getContextPath()%>/scripts/AC_RunActiveContent.js"
	type="text/javascript"></script>

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
	src="<%=request.getContextPath()%>/scripts/validacionFormularios.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/componente.trimestres.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/ordenProduccion.js"></script>

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
				<s:text name="parteTecnico.parteTecnico.titulo.principal" />
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
							<!-- Agregado por Fabian Geldres -->
							<s:if test="funConsultarActivo">
								<td class="excel"><a href="#"
									onclick="javascript: exportar('frm_listarParteTecnico','XLS',1);"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td class="pdfIcon"><a href="#"
									onclick="javascript: exportar('frm_listarParteTecnico','PDF',1);"><img
										alt="" src="../images/transparente.gif"> </a></td>
							</s:if>



						</tr>
						<tr>

							<!-- Agregado por Fabian Geldres -->
							<s:if test="funConsultarActivo">
								<td><a href="#"
									onclick="javascript: exportar('frm_listarParteTecnico','XLS',1);">Exporta&nbsp;Excel</a>
								</td>
							</s:if>
							<s:if test="funConsultarActivo">
								<td><a href="#"
									onclick="javascript: exportar('frm_listarParteTecnico','PDF',1);">Exporta&nbsp;PDF</a>
								</td>
							</s:if>

							<!-- FIN -->

						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<div class="content">
				<div class="titulo">
					<s:text name="parteTecnico.parteTecnico.titulo.generar"></s:text>
				</div>
				<div id="formularios">
					<s:form theme="simple" id="frm_listarParteTecnico"
						name="frm_listarParteTecnico" action="listarParteTecnico"
						namespace="/parteTecnico" method="post">

						<s:hidden id="mensajeErrorValidacion"
							value="%{mensajeErrorValidacion}"></s:hidden>
						<s:hidden id="tituloCampoDivision" value="%{tituloCampoDivision}"></s:hidden>
						<s:hidden id="tituloCampoSociedad" value="%{tituloCampoSociedad}"></s:hidden>
						<s:hidden id="tituloCampoUnidad" value="%{tituloCampoUnidad}"></s:hidden>
						<s:hidden id="tituloCampoLineaNegocio"
							value="%{tituloCampoLineaNegocio}"></s:hidden>
						<s:hidden id="tituloCampoAnio" value="%{tituloCampoAnio}"></s:hidden>
						<s:hidden id="tituloCampoMes" value="%{tituloCampoMes}"></s:hidden>
						<s:hidden id="tituloCampoInforme" value="%{tituloCampoInforme}"></s:hidden>

						<s:hidden name="valorInformeFiltrado"
							value="%{valorInformeFiltrado}" id="valorInformeFiltrado"></s:hidden>
						<s:hidden name="valorDivisionFiltrado"
							value="%{valorDivisionFiltrado}" id="valorDivisionFiltrado"></s:hidden>
						<s:hidden name="valorSociedadFiltrado"
							value="%{valorSociedadFiltrado}" id="valorSociedadFiltrado"></s:hidden>
						<s:hidden name="valorUnidadFiltrado"
							value="%{valorUnidadFiltrado}" id="valorUnidadFiltrado"></s:hidden>
						<s:hidden name="valorLineaNegocioFiltrado"
							value="%{valorLineaNegocioFiltrado}"
							id="valorLineaNegocioFiltrado"></s:hidden>
						<s:hidden name="valorAnnioFiltrado" value="%{valorAnnioFiltrado}"
							id="valorAnnioFiltrado"></s:hidden>
						<s:hidden name="valorProductoFiltrado"
							value="%{valorProductoFiltrado}" id="valorProductoFiltrado"></s:hidden>
						<s:hidden name="valorMesFiltrado" value="%{valorMesFiltrado}"
							id="valorMesFiltrado"></s:hidden>


						<!-- valor si es PDF O XLS -->
						<s:hidden name="xlsPDF" id="xlsPDF"></s:hidden>




						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td><s:text
										name="parteTecnico.parteTecnico.etiqueta.division" /> :</td>
								<td class="campooblig">(*)</td>
								<td><s:select name="valorDivision" id="valorDivision"
										value="%{valorDivision}" list="divisiones" listKey="codigo"
										emptyOption="true" listValue="nombre" cssClass="cortoCombo"
										theme="simple"
										onchange="javascript:document.getElementById('valorSociedad').value = '';document.getElementById('valorUnidad').value = '';document.getElementById('valorDivision').value = this.value; update('comboSociedad'); return false;" />
								</td>

								<td><s:text
										name="parteTecnico.parteTecnico.etiqueta.sociedad" /> :</td>
								<td class="campooblig">(*)</td>
								<td><s:url id="detalleSociedad" action="cargarSociedad" />
									<s:div showLoadingText="true" id="sociedades"
										loadingText="Cargando Sociedad..." href="%{detalleSociedad}"
										theme="ajax" listenTopics="comboSociedad"
										executeScripts="true" formId="frm_listarParteTecnico" /></td>

								<td><s:text
										name="parteTecnico.parteTecnico.etiqueta.unidad" /> :</td>
								<td class="campooblig">(*)</td>
								<td><s:url id="detalleUnidad" action="cargarUnidad" /> <s:div
										showLoadingText="true" id="unidades"
										loadingText="Cargando Unidad..." href="%{detalleUnidad}"
										theme="ajax" listenTopics="comboUnidad" executeScripts="true"
										formId="frm_listarParteTecnico" /></td>
							</tr>
							<tr>
								<td><s:text
										name="manejoMaestro.proceso.etiqueta.lineaNegocio" /> :</td>
								<td class="campooblig"></td>
								<td><s:url id="detalleLineaNegocio"
										action="cargarLineaNegocio" /> <s:div showLoadingText="true"
										id="lineasNegocio" loadingText="Cargando Líneas Negocio..."
										href="%{detalleLineaNegocio}" theme="ajax"
										listenTopics="comboLineaNegocio"
										formId="frm_listarParteTecnico" /></td>
								<td><s:text
										name="parteTecnico.ajusteProduccionMes.etiqueta.anio" /> :</td>
								<td class="campooblig">(*)</td>
								<td><s:select name="valorAnnio" id="valorAnnio"
										value="%{valorAnnio}" list="anios" headerValue="Seleccione"
										headerKey="-1" theme="simple"
										onchange="javascript:document.getElementById('valorAnnio').value = this.value; update('comboOculto');  return false;" />
								</td>
								<td><s:text
										name="parteTecnico.ajusteProduccionMes.etiqueta.mes" /> :</td>
								<td class="campooblig">(*)</td>
								<td><s:select name="valorMes" id="valorMes"
										value="%{valorMes}" list="meses" headerValue="Seleccione"
										headerKey="-1" theme="simple" listKey="codigo" listValue="mes"
										onchange="javascript:document.getElementById('valorMes').value = this.value; update('comboOculto');  return false;" />
								</td>
							</tr>
							<tr>
								<td><s:text
										name="parteTecnico.parteTecnico.etiqueta.informe" /> :</td>
								<td class="campooblig">(*)</td>
								<td><s:select name="valorInforme" id="valorInforme"
										value="%{valorInforme}" list="informes" listKey="codigo"
										cssClass="comboCorto" listValue="valor"
										headerValue="Seleccione" headerKey="-1" theme="simple"
										onchange="javascript:document.getElementById('valorInforme').value = this.value; return false;" />
								</td>
								<td>
									<!--  < name="parteTecnico.parteTecnico.etiqueta.material" />
										:-->
								</td>
								<td></td>
								<td>
									<!-- < id="detalleProducto" action="cargarProducto" />
										< showLoadingText="true" id="productos"
											loadingText="Cargando Producto..." href="%{detalleProducto}"
											theme="ajax" listenTopics="comboProducto"
											formId="frm_listarParteTecnico" />-->
								</td>


								<td></td>
							</tr>

							<tr>

							</tr>
						</table>
					</s:form>
					<br />
					<div id="linkFiltrarTecnico"></div>
					<div>
						<s:if test="parteTecnicoComponentes.size > 0">
							<table cellspacing="0" class="dataTable3 width_100">
								<tr>
									<td>Producción</td>
								</tr>
							</table>

							<table cellspacing="0" class="dataTable3 width_100">
								<tr>
									<!--  	<th>
											Tipo de Producto
										</th>
										<th>
											Orden de Proceso
										</th>-->
									<th>Proceso</th>
									<!--	<th>
											Línea de Negocio
										</th>-->
									<th>Material</th>
									<th>UM</th>
									<th>Saldo Inicial</th>
									<th>Producción</th>
									<th>Consumo</th>
									<th>Inventario Final</th>
									<th>Producción Acumulada</th>
									<th>Consumo Acumulado</th>
								</tr>
								<s:iterator value="parteTecnicoComponentes" status="st">
									<tr>
										<!--<td>
												<s:property value="%{tipoProducto}" />
											</td>
											<td>
												<s:property value="%{ordenProceso}" />
											</td>-->
										<td class="left"><s:property value="%{proceso}" /></td>
										<!--<td>
												<s:property value="%{linea}" />
											</td>-->
										<td class="left"><s:property value="%{componente}" /></td>
										<td><s:property value="%{unidadMedida}" /></td>
										<td class="right"><s:property
												value="%{getText('{0,number,#,##0.00}',{saldoInicial})}" />
										</td>
										<td class="right"><s:property
												value="%{getText('{0,number,#,##0.00}',{ingreso})}" /></td>
										<td class="right"><s:property
												value="%{getText('{0,number,#,##0.00}',{consumo})}" /></td>
										<td class="right"><s:property
												value="%{getText('{0,number,#,##0.00}',{saldoFinal})}" /></td>
										<td class="right"><s:property
												value="%{getText('{0,number,#,##0.00}',{produccionAcumulada})}" />
										</td>
										<td class="right"><s:property
												value="%{getText('{0,number,#,##0.00}',{consumoAcumulado})}" />
										</td>
									</tr>
								</s:iterator>
							</table>
						</s:if>
					</div>
					<div>
						<s:if test="parteTecnicoPuestoTrabajoComponentes.size > 0">
							<table cellspacing="0" class="dataTable3 width_100">
								<tr>
									<td>Operaciones</td>
								</tr>
							</table>

							<table cellspacing="0" class="dataTable3 width_100" border="0">
								<tr>
									<!--  	<th>
											Tipo Producto
										</th>
										<th>
											Orden Proceso
										</th>-->
									<th>Proceso</th>
									<!-- 	<th>
											Línea Negocio
										</th>-->
									<th>Material</th>
									<th>Puesto Trabajo</th>
									<th>Producción</th>
									<th>Horas Trabajo</th>

									<s:iterator value="%{combustibles}" status="st4">
										<th colspan="1"><s:property /></th>
									</s:iterator>

								</tr>
								<tr>
									<td colspan="17">&nbsp;</td>
								</tr>
								<s:iterator value="parteTecnicoPuestoTrabajoComponentes"
									status="st" id="item">
									<tr>
										<!-- 	<td>
												<s:property value="%{tipoProducto}" />
											</td>
											<td>
												<s:property value="%{ordenProceso}" />
											</td>-->
										<td><s:property value="%{proceso}" /></td>
										<!--<td>
												<s:property value="%{linea}" />
											</td>-->
										<td><s:property value="%{componente}" /></td>
										<td colspan="12">
											<!-- Detalle de puestos de trabajo -->
											<table border="1" width="100%">
												<s:iterator value="%{detallePuestoTrabajo}" status="st2">
													<tr>
														<td><s:property value="%{puestoTrabajo}" /></td>
														<td><s:property
																value="%{getText('{0,number,#,##0.00}',{produccion})}" />
														</td>
														<td><s:property
																value="%{getText('{0,number,#,##0.00}',{tiempoProduccion})}" />
														</td>

														<s:iterator value="%{detalleConsumoCombustible}"
															status="st3">
															<td><s:property
																	value="%{getText('{0,number,#,##0.00}',{consumoComponenteCombustible})}" />
															</td>
														</s:iterator>

													</tr>
												</s:iterator>
											</table>
										</td>
									</tr>
								</s:iterator>
							</table>
						</s:if>
					</div>
					<s:if
						test="listaConsumoCombustiblesSolidos.size > 0 || listaConsumoCombustiblesLiquidos.size > 0">
						<div id="accordion">
							<h3>
								<a href="#">Consumo de Combustibles Sólidos</a>
							</h3>
							<div>
								<table cellspacing="0" class="tabla">
									<s:if test="listaConsumoCombustiblesSolidos.size == 0">
											No hay Consumos de Combustibles Sólidos para mostrar.
										</s:if>
									<s:if test="listaConsumoCombustiblesSolidos.size > 0">
										<tr>
											<td class="tablaHeadersecundaria tdTitle"></td>
											<td class="tablaHeadersecundaria tdTitle"></td>
											<td colspan="2" class="dataTable3">En Calentamiento</td>
											<td colspan="2" class="dataTable3">En Producción</td>
											<td colspan="2" class="dataTable3">Consumo Total</td>
										</tr>
										<tr>
											<td class="dataTable3">Material Combustible</td>
											<td class="dataTable3">Puesto de Trabajo</td>
											<td class="dataTable3">Mes</td>
											<td class="dataTable3">Año</td>
											<td class="dataTable3">Mes</td>
											<td class="dataTable3">Año</td>
											<td class="dataTable3">Mes</td>
											<td class="dataTable3">Año</td>
										</tr>
										<s:iterator value="listaConsumoCombustiblesSolidos">
											<tr align="left">
												<td align="left" class="tdboder"><s:property
														value="%{nombreProductoCombustible}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{nombrePuestoTrabajo}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoCalentamientoMes}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoCalentamientoAcumulado}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoProduccionMes}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoProduccionAcumulado}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoTotalMes}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoTotalAcumulado}" /></td>
											</tr>
										</s:iterator>
									</s:if>
								</table>
							</div>
							<h3>
								<a href="#">Consumo de Combustibles Líquidos</a>
							</h3>
							<div>
								<table cellspacing="0" class="tabla">
									<s:if test="listaConsumoCombustiblesLiquidos.size == 0">
											No hay Consumos de Combustibles Líquidos para mostrar.
										</s:if>
									<s:if test="listaConsumoCombustiblesLiquidos.size > 0">
										<tr>
											<td class="tablaHeadersecundaria tdTitle"></td>
											<td class="tablaHeadersecundaria tdTitle"></td>
											<td colspan="2" class="dataTable3">En Calentamiento</td>
											<td colspan="2" class="dataTable3">En Producción</td>
											<td colspan="2" class="dataTable3">Consumo Total</td>
										</tr>
										<tr>
											<td class="dataTable3">Material Combustible</td>
											<td class="dataTable3">Puesto de Trabajo</td>
											<td class="dataTable3">Mes</td>
											<td class="dataTable3">Año</td>
											<td class="dataTable3">Mes</td>
											<td class="dataTable3">Año</td>
											<td class="dataTable3">Mes</td>
											<td class="dataTable3">Año</td>
										</tr>
										<s:iterator value="listaConsumoCombustiblesLiquidos">
											<tr align="left">
												<td align="left" class="tdboder"><s:property
														value="%{nombreProductoCombustible}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{nombrePuestoTrabajo}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoCalentamientoMes}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoCalentamientoAcumulado}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoProduccionMes}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoProduccionAcumulado}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoTotalMes}" /></td>
												<td align="left" class="tdboder"><s:property
														value="%{consumoTotalAcumulado}" /></td>
											</tr>
										</s:iterator>
									</s:if>
								</table>
							</div>
						</div>
					</s:if>
					<div>
						<s:if test="parteTecnicoAnexoCrudo.size > 0">
							<table cellspacing="0" class="tabla">
								<tr align="left" height="24px">
									<s:iterator value="nombreTiposCrudo" status="nomTipCrudoStatus">
										<td class="dataTable3"><s:property /></td>
									</s:iterator>
								</tr>
								<s:iterator value="parteTecnicoAnexoCrudo" status="stCrudo">
									<tr>
										<s:iterator value="consumos" status="anexoCrudoStatus">
											<td align="left" class="tdboder"><s:property
													value="valorConsumo" /></td>
										</s:iterator>
									</tr>
								</s:iterator>
							</table>
						</s:if>
					</div>
					<div>
						<s:if test="parteTecnicoAnexoClinker.size > 0">
							<table cellspacing="0" class="tabla">
								<tr align="left" height="24px">
									<s:iterator value="nombreTiposClinker"
										status="nomTipClinkerStatus">
										<td class="dataTable3"><s:property /></td>
									</s:iterator>
								</tr>
								<s:iterator value="parteTecnicoAnexoClinker" status="stClinker">
									<tr>
										<s:iterator value="consumos" status="anexoClinkerStatus">
											<td align="left" class="tdboder"><s:property
													value="valorConsumo" /></td>
										</s:iterator>
									</tr>
								</s:iterator>
							</table>
						</s:if>
					</div>
					<div align="center">
						<br>
						<s:if test="parteTecnicoAnexoCemento.size > 0">
							<table cellspacing="0" class="tabla">
								<tr align="left" height="24px">
									<s:iterator value="nombreTiposCemento"
										status="nomTipCementoStatus">
										<td class="dataTable3"><s:property /></td>
									</s:iterator>
								</tr>
								<s:iterator value="parteTecnicoAnexoCemento" status="stCemento">
									<tr>
										<s:iterator value="consumos" status="anexoCementoStatus">
											<td align="left" class="tdboder"><s:property
													value="valorConsumo" /></td>
										</s:iterator>
									</tr>
								</s:iterator>
							</table>
						</s:if>
					</div>
					<s:if test="hasActionErrors()">
						<tr>
							<td colspan="4">
								<div class="error">
									<s:actionerror />
								</div>
							</td>
						</tr>
					</s:if>
					<s:if test="hasFieldErrors()">
						<tr>
							<td colspan="4">
								<div class="advertencia">
									<s:fielderror></s:fielderror>
								</div>
							</td>
						</tr>
					</s:if>
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
					</div>
				</div>
			</div>
			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />
		</div>
	</div>
</body>
</html>