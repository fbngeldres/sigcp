<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../comun/cabecera.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
	<h2>
		SGCP - Pacasmayo (Pagina de Prueba) <br>
	</h2>
	Usuario:
	<s:property value="usuario.login" />
	<hr />
	<strong>Planificación</strong>
	<ul>
		<li><s:url action="planificar" id="url"></s:url> <a
			href="<s:property value="#url"/>">Ejemplo de Paginador</a> <br /></li>
		<li><s:url action="consultaPlanificacion" id="url"></s:url> <a
			href="<s:property value="#url"/>">Planificar Plan de Producción</a> <br />
		</li>
	</ul>

	<strong>Mantenimiento</strong>
	<ul>
		<li><s:url action="maestroUnidad" id="url"
				namespace="manejoMaestros"></s:url> <a
			href="<s:property value="#url"/>">Administrar Unidades (Crud de
				Ejemplo)</a> <br /></li>
		<li><s:url action="unidad!input" id="url"></s:url> <a
			href="<s:property value="#url"/>">Administrar X cosa</a> <br /></li>
	</ul>
	<a href="<s:property value="#url"/>">Cerrar sesi&oacute;n</a>
	<s:url action="cerrarSesion" id="url" />
</body>
</html>