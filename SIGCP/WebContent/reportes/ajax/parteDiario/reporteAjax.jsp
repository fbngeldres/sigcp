<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if
	test="dataDireccionReporte !=null && dataDireccionReporte != 'Error' ">
	<s:div id="linkGenerarReporteHtmlAjax">


		<s:text name="%{dataDireccionReporte}">
			<s:param name="divDataReporte" value="%{dataDireccionReporte}" />
		</s:text>
		<script type="text/javascript">
<%--alert($("#linkGenerarReporteHtmlAjax").text());--%>
var direccionReporte = $("#linkGenerarReporteHtmlAjax").text();
$(".advertencia").parent().html(" ");
$(".advertencia").html(" ");
$("#linkGenerarReporteHtmlAjax").html("<div id=\"exito\" class=\"exito\">Reporte Generado</div>");
newReporte = window.open((String)(this.href).substring(0,((String)(this.href).length) -9)+"tempReportes/"+direccionReporte,'newReporte',
'width=auto,height=auto,resizable=yes,scrollbars=yes');
setTimeout("update('/borrarTemporal')",2500);
</script>
	</s:div>
</s:if>
<s:if test="dataDireccionReporte =='Error' ">
	<s:div id="linkGenerarReporteHtmlAjax">
		<div>
			<div class="advertencia"></div>
		</div>
		<script type="text/javascript">
<%--alert($("#linkGenerarReporteHtmlAjax").text());--%>
$(".advertencia").parent().html("<div id=\"advertencia\" class=\"advertencia\">No se ha encontrado ningun registro</div>");

</script>
		<%--<div id="advertencia" class="advertencia">No se ha encontrado ningun registro</div>
--%>
	</s:div>
</s:if>

