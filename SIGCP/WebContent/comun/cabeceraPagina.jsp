
<%@page
	import="pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction,pe.com.pacasmayo.sgcp.bean.UsuarioBean"%>
<%@page
	import="pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction"%>
<script
	src="<%=request.getContextPath()%>/scripts/AC_RunActiveContent.js"
	type="text/javascript"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>


<%
	String username = "Nombre Prueba";

	System.out.println(session
			.getAttribute(AplicacionAction.USUARIO_SESION) + "");
	if (session.getAttribute(AplicacionAction.USUARIO_SESION) != null) {
		UsuarioBean usuario = (UsuarioBean) session
				.getAttribute(AplicacionAction.USUARIO_SESION);
		username = usuario.getNombreCompleto();
	}
%>

<div class="header">
	<div class="home">
		<a href="<s:url namespace="/seguridad" action="principal"/>">Inicio</a>
	</div>
	<div class="cerrarsesion">
		<a href="<s:url namespace="/seguridad" action="salirDeSesion"/>">Cerrar
			Sesi&oacute;n</a>
	</div>
	<div class="animacion">
		<script type="text/javascript">
AC_FL_RunContent( 'codebase','http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0','width','250','height','69','src','<%=request.getContextPath()%>/images/animacion/fotos','quality','high','pluginspage','http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash','movie','<%=request.getContextPath()%>
			/images/animacion/fotos'); //end AC code
		</script>
		<noscript>
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0"
				width="250" height="69">
				<param name="movie"
					value="<%=request.getContextPath()%>/images/animacion/fotos.swf" />
				<param name="quality" value="high" />
				<embed
					src="<%=request.getContextPath()%>/images/animacion/fotos.swf"
					quality="high"
					pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"
					type="application/x-shockwave-flash" width="250" height="69"></embed>
			</object>
		</noscript>
	</div>
	<div class="bienvenido">
		Bienvenido
		<%=username%></div>
</div>