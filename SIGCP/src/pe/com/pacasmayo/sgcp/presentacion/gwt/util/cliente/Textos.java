package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

/**
 * Clase utilitaria que crea diversos textos genericos
 */
public class Textos implements ClienteServicioGwt {

	public static final String CANTIDAD_ALTURAS = "cantidadAlturas";
	private static final String TITULO_CANTIDAD_DE_ALTURAS = "Num. alturas";
	public static final String NOMBRE_FECHA_ACTUAL = "fechaActual";
	public static final String TEXTO_ETIQUETA_FECHA_ACTUAL = "Fecha de registro";
	public static final String NOMBRE_HORA_ENERGIA = "horaEnergia";
	public static final String TEXTO_HORA_POTENCIA = "horaPotencia";
	private static final String TEXTO_ETIQUETA_HORAS_DE_POTENCIA = "Horas de Potencia";
	private static final String TEXTO_ETIQUETA_HORAS_DE_ENERGIA = "Horas de Energ&iacute;a";
	private static final String CAMPO_FECHA = "fechaItem";
	private static final String ETIQUETA_FECHA = "Fecha";
	private static final String MASCARA_NUMERICA_ANNO_4_DIGITOS = "####";
	private static final String MASCARA_NUMERICA_2_DIGITOS = "##";
	private static final String MASCARA_NUMERICA_1_DIGITO = "#";
	private static final String HINT_ANNO = "<nobr>(YYYY)</nobr>";
	private static final String ETIQUETA_ANNO = "A\u00f1o";
	private static Label tituloHorasPotencia = new Label();
	private static Label tituloHorasEnergia = new Label();
	public static final String NOMBRE_FECHA_INICIO = "fechaInicio";
	public static final String NOMBRE_FECHA_FIN = "fechaFin";
	public static final String NOMBRE_FECHA_FILTRADO = "fechaFiltrado";
	public static final String ANNO = "anno";

	public static final String TEXTO_DIVISION = "division";
	private static final String TITULO_DIVISION = "Divisi&oacute;n";
	private static final String TITULO_SOCIEDAD = "Sociedad";
	public static final String TEXTO_SOCIEDAD = "sociedad";
	public static final String TEXTO_UNIDAD = "unidad";
	private static final String TITULO_UNIDAD = "Unidad";
	private static final String TITULO_LINEA_NEGOCIO = "L&iacute;nea de negocio";
	public static final String TEXTO_LINEA_NEGOCIO = "lineaNegocio";
	public static final String TEXTO_PROCESO = "proceso";
	public static final String TEXTO_PRODUCTO = "producto";
	private static final String TITULO_PROCESO = "Proceso";
	private static final String TITULO_PRODUCTO = "Producto";
	private static final String TITULO_ESTADO = "Estado";
	public static final String TEXTO_ESTADO = "estado";

	private static ConstantesUtil CONSTANTES = GWT.create(ConstantesUtil.class);

	/**
	 * Metodo que retorna un texto editable para ingresar el a�o en formato de
	 * 4 digitos.
	 * 
	 * @return
	 */
	public static TextItem cargarAnno() {

		TextItem anno = new TextItem();
		anno.setTitle(ETIQUETA_ANNO);
		anno.setName(ANNO);
		anno.setMask(MASCARA_NUMERICA_ANNO_4_DIGITOS);
		anno.setHint(HINT_ANNO);
		anno.setWidth(60);
		return anno;
	}

	/**
	 * Metodo que retorna un campo de seleccion de fecha
	 * 
	 * @return
	 */
	public static DateItem cargarCampoSeleccionFecha() {

		DateItem fecha = new DateItem(CAMPO_FECHA, ETIQUETA_FECHA);
		fecha.setUseTextField(true);
		fecha.setUseMask(true);
		fecha.setName(NOMBRE_FECHA_FILTRADO);
		return fecha;
	}

	/**
	 * Metodo que retorna un espacio en blanco usado para dejar lineas de por
	 * medio simulando un Enter
	 * 
	 * @return
	 */
	public static Label obtenerEspacioBlanco() {
		Label espacioBlanco = new Label();
		espacioBlanco.setBorder(ConstantesGWT.BORDE_3PX_BLANCO);
		espacioBlanco.setHeight(25);
		espacioBlanco.setShowEdges(false);
		return espacioBlanco;
	}

	/**
	 * Metodo que retorna una texto estatico no editable (como una etiqueta) de
	 * fecha de fin
	 * 
	 * @return
	 */
	public static StaticTextItem cargarFechaFin() {

		StaticTextItem fechaFin = new StaticTextItem();
		fechaFin.setName(NOMBRE_FECHA_FIN);
		fechaFin.setTitle(ConstantesGWT.ETIQUETA_FECHA_DE_FIN);
		fechaFin.setDisabled(true);
		return fechaFin;
	}

	/**
	 * Metodo que retorna una texto estatico no editable (como una etiqueta) de
	 * fecha de inicio
	 * 
	 * @return
	 */
	public static StaticTextItem cargarFechaInicio() {

		StaticTextItem fechaInicio = new StaticTextItem();
		fechaInicio.setName(NOMBRE_FECHA_INICIO);
		fechaInicio.setTitle(ConstantesGWT.ETIQUETA_FECHA_INICIO);
		fechaInicio.setDisabled(true);
		return fechaInicio;
	}

	public static Label obtenerTituloHorasPotencia() {
		tituloHorasPotencia.setID("tituloHorasPotencia");
		tituloHorasPotencia.setContents(TEXTO_ETIQUETA_HORAS_DE_POTENCIA);
		tituloHorasPotencia.setVisible(false);
		tituloHorasPotencia.setWidth(300);
		tituloHorasPotencia.setHeight(20);
		return tituloHorasPotencia;
	}

	public static Label obtenerTituloHorasEnergia() {
		tituloHorasEnergia.setID("tituloHorasEnergia");
		tituloHorasEnergia.setVisible(false);
		tituloHorasEnergia.setContents(TEXTO_ETIQUETA_HORAS_DE_ENERGIA);
		tituloHorasEnergia.setWidth(300);
		tituloHorasEnergia.setHeight(20);
		tituloHorasEnergia.setTop(20);
		return tituloHorasEnergia;
	}

	/**
	 * Metodo que retorna la etiqeuta de horas de potencia
	 * 
	 * @return
	 */
	public static Label obtenerEtiquetaHoraPotencia() {
		Label valorHoraPotencia = new Label();
		valorHoraPotencia.setLeft(150);
		valorHoraPotencia.setHeight(20);
		valorHoraPotencia.setContents("");
		return valorHoraPotencia;
	}

	/**
	 * Metodo que retorna la etiqeuta de horas de energia
	 * 
	 * @return
	 */
	public static Label obtenerEtiquetaHoraEnergia() {
		Label valorHoraEnergia = new Label();
		valorHoraEnergia.setLeft(150);
		valorHoraEnergia.setTop(20);
		valorHoraEnergia.setHeight(20);
		valorHoraEnergia.setContents("");
		return valorHoraEnergia;
	}

	/**
	 * Metodo que retorna un StaticTextItem no editable con la fecha actual
	 * 
	 * @return
	 */
	public static StaticTextItem obtenerFechaActualStaticTextItem() {

		final StaticTextItem fechaActual = new StaticTextItem();
		fechaActual.setName(NOMBRE_FECHA_ACTUAL);
		fechaActual.setTitle(TEXTO_ETIQUETA_FECHA_ACTUAL);
		fechaActual.setDisabled(true);

		final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(ConstantesGWT.PATRON_FECHA_APLICACION);

		servicioComunicacion.obtenerFechaActual(new AsyncCallback<Date>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(Date fechaRecibida) {
				fechaActual.setValue(dateFormatter.format(fechaRecibida));
			}
		});
		return fechaActual;
	}

	/**
	 * Metodo que retorna un texto editable para ingresar el a�o en formato de
	 * 4 digitos.
	 * 
	 * @return
	 */
	public static TextItem obtenerTextoCantidadAlturas() {

		TextItem cantidadAlturas = new TextItem();
		cantidadAlturas.setTitle(TITULO_CANTIDAD_DE_ALTURAS);
		cantidadAlturas.setName(CANTIDAD_ALTURAS);
		cantidadAlturas.setMask(MASCARA_NUMERICA_1_DIGITO);
		cantidadAlturas.setWidth(30);
		return cantidadAlturas;
	}

	/**
	 * Metodo que retorna un textarea editable para ingresar las observaciones
	 */
	public static TextAreaItem obtenerTextoObservaciones() {

		TextAreaItem observaciones = new TextAreaItem();
		observaciones.setTitle("Observaciones");
		observaciones.setName("observaciones");
		observaciones.setWidth(300);
		return observaciones;
	}

	public static TextAreaItem obtenerTextoObservacionesCortas() {

		TextAreaItem observaciones = new TextAreaItem();
		observaciones.setTitle("Observaciones");
		observaciones.setName("observaciones");
		observaciones.setWidth(390);
		return observaciones;
	}

	/**
	 * Metodo que retorna un textarea editable para ingresar las el ajuste
	 */
	public static StaticTextItem obtenerTextoAjuste() {

		StaticTextItem observaciones = new StaticTextItem();
		observaciones.setTitle(CONSTANTES.etiquetaAjustePropuesto());
		observaciones.setName("ajuste");
		observaciones.setWidth(60);

		return observaciones;
	}

	public static StaticTextItem obtenerTextoDivision() {

		StaticTextItem divisionItem = new StaticTextItem();
		divisionItem.setName(TEXTO_DIVISION);
		divisionItem.setTitle(TITULO_DIVISION);
		divisionItem.setDisabled(true);

		return divisionItem;
	}

	public static StaticTextItem obtenerTextoSociedad() {

		StaticTextItem sociedadItem = new StaticTextItem();
		sociedadItem.setName(TEXTO_SOCIEDAD);
		sociedadItem.setTitle(TITULO_SOCIEDAD);
		sociedadItem.setDisabled(true);

		return sociedadItem;
	}

	public static StaticTextItem obtenerTextoUnidad() {

		StaticTextItem unidadItem = new StaticTextItem();
		unidadItem.setName(TEXTO_UNIDAD);
		unidadItem.setTitle(TITULO_UNIDAD);
		unidadItem.setDisabled(true);

		return unidadItem;
	}

	public static StaticTextItem obtenerTextoLineaNegocio() {

		StaticTextItem lineaNegocioItem = new StaticTextItem();
		lineaNegocioItem.setName(TEXTO_LINEA_NEGOCIO);
		lineaNegocioItem.setTitle(TITULO_LINEA_NEGOCIO);
		lineaNegocioItem.setDisabled(true);

		return lineaNegocioItem;
	}

	public static StaticTextItem obtenerTextoProceso() {

		StaticTextItem procesoItem = new StaticTextItem();
		procesoItem.setName(TEXTO_PROCESO);
		procesoItem.setTitle(TITULO_PROCESO);
		procesoItem.setDisabled(true);

		return procesoItem;
	}

	public static StaticTextItem obtenerTextoProducto() {

		StaticTextItem procesoItem = new StaticTextItem();
		procesoItem.setName(TEXTO_PRODUCTO);
		procesoItem.setTitle(TITULO_PRODUCTO);
		procesoItem.setDisabled(true);

		return procesoItem;
	}

	public static StaticTextItem obtenerTextoEstado() {

		StaticTextItem estadoItem = new StaticTextItem();
		estadoItem.setName(TEXTO_ESTADO);
		estadoItem.setTitle(TITULO_ESTADO);
		estadoItem.setDisabled(true);

		return estadoItem;
	}

}