<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu"%>
<%@page import="pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogic"%>
<%@page
	import="pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction,pe.com.pacasmayo.sgcp.bean.UsuarioBean"%>
<%@page
	import="pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction"%>
<%@page
	import="net.sf.navigator.menu.MenuComponent,java.util.List,java.util.ArrayList,java.util.Iterator,org.apache.struts2.ServletActionContext"%>

<%
	String username = "";
	List menus = new ArrayList();

	String valor = "";
	if (session.getAttribute(AplicacionAction.USUARIO_SESION) != null) {
		UsuarioBean usuario = (UsuarioBean) session
				.getAttribute(AplicacionAction.USUARIO_SESION);
		username = usuario.getNombreCompleto();

		menus = (List) ServletActionContext.getRequest().getSession()
				.getAttribute(MenuLogic.TOPMENUS + usuario.getLogin());
		valor = "repository" + usuario.getLogin();

		System.out.println("-->" + valor);

	}
%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/cookies.js"></script>

<script src="<%=request.getContextPath()%>/scripts/menu.js"
	type="text/javascript"></script>

<!--MENU EXPANDIDO-->
<table id="tablaMenuExpandido" border="0" cellspacing="0"
	cellpadding="0">
	<tbody>
		<tr>
			<%
				if (!valor.equals("")) {

					try {
			%>
			<td><menu:useMenuDisplayer name="DropDown"
					bundle="org.apache.struts.action.MESSAGE" repository="<%=valor%>">


					<table cellpadding="0" cellspacing="0" width="100%">
						<%
							for (Iterator iterator = menus.iterator(); iterator
												.hasNext();) {
						%>
						<tr>
							<td width="100%"><menu:displayMenu
									name="<%=((MenuComponent) iterator.next())
									.getName()%>" /></td>
						</tr>
						<%
							}
						%>
					</table>
				</menu:useMenuDisplayer></td>
			<%
				} catch (Exception e) {
						e.printStackTrace();
					}
			%>

			<td><img
				src="<%=request.getContextPath()%>/images/menu-cerrar.gif"
				name="MenuSwitch" border="0" id="MenuSwitch"
				onclick="switchMenu2();" style="cursor: pointer" /></td>
			<%
				} else {
			%>
			<td>Vuelva <a href="/inicio.jsp"
				style="text-decoration: underline">Iniciar Sesión</a>
			</td>
			<%
				}
			%>

		</tr>
	</tbody>
</table>

<!-- MENU COLAPSADO-->
<table id="tablaMenuColapsado" cellspacing="0" cellpadding="0">
	<tbody>
		<tr>
			<td valign="bottom"><img
				src="<%=request.getContextPath()%>/images/menu-abrir.gif"
				name="MenuSwitch" border="0" id="MenuSwitch" onclick="switchMenu2()"
				style="cursor: pointer" /></td>
		</tr>
	</tbody>
</table>
<!--FIN MENU COLAPSADO-->
