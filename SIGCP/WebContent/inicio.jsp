<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/util.js"></script>

<body>
	<s:form id="loginform" action="iniciarSesion" namespace="/seguridad">
		<table class="content-inicio borders" cellspacing="0">
			<tr>
				<td><img src="<%=request.getContextPath()%>/images/logo.jpg"
					alt="Cementos Pacasmayo" width="600" height="157"></td>
			</tr>
			<tr>
				<td align="center">

					<div class="errors">
						<s:actionerror cssClass="autenticacion" />
					</div>
					<table cellspacing="0" align="center" width="160">

						<s:textfield name="login" label="Usuario" size="40" maxLength="40"
							onkeypress="return alfanumerico(event)" cssClass="autenticacion" />
						<s:password name="password" label="Contraseña" size="40"
							onkeypress="return alfanumerico(event)" maxlength="40"
							cssClass="autenticacion" />
						<s:submit value="Aceptar" cssClass="buttonautenticar"
							align="center" />
					</table>
				</td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/images/banda-inf.jpg"
					width="600" height="74"></td>
			</tr>
		</table>
	</s:form>
</body>
</html>