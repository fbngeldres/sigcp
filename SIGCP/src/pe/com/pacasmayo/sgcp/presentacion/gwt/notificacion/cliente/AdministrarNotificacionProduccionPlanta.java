package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificaciondiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.DialogoCargarOrdenOSilo.TipoDialogo;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.componentes.GridVariablesOperacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.componentes.GridVariablesProduccion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Combos;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.VerificarSesion;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RowMouseUpEvent;
import com.smartgwt.client.widgets.grid.events.RowMouseUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: AdministrarNotificacionProduccionPlanta.java
 * Modificado: Jan 31, 2011 3:59:55 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AdministrarNotificacionProduccionPlanta implements EntryPoint, ClienteServicioGwt {

	private static final String LINEA_NEGOCIO_TXT = "Linea de Negocio: ";
	private static final String VP = "VP";
	private static final String VP_ = "%VP_";
	private static final String VO = "VO";
	private static final String VO_ = "%VO_";
	private static final String VARIABLE_OPERACION = "operacion";
	private static final String VARIABLE_PRODUCCION = "produccion";
	private static final String ESTADO_ACTIVO = "Activo";

	public static final String NOMBRE_FECHA_ACTUAL = "fechaActual";

	private static DynamicForm formularioCombos;
	//private static Img imagenProduccionLavado;
	//private static Label etiquetaProduccionLavado;
	private static Img imagenGrabar;
	private static Label etiquetaGrabar;

	private static List<ColumnareporteDTO> columnas = new ArrayList<ColumnareporteDTO>();
	private static GridVariablesProduccion gridVarProd = new GridVariablesProduccion();
	private static GridVariablesProduccion gridVarProdSubtotales = new GridVariablesProduccion();
	private static final GridVariablesOperacion gridVarOper = new GridVariablesOperacion();

	private static final VLayout layout = new VLayout();

	private static boolean notificacionExiste = false;
	private static SelectItem divisionItem;
	private static SelectItem sociedadItem;
	private static SelectItem unidadItem;
	private static SelectItem lineaNegocioItem;
	private static SelectItem procesoItem;
	private static SelectItem tableroControlItem;
	private static SelectItem puestoTrabajoItem;
	private static SelectItem variableItem;
	private static DateItem fechaItem;

	private static Label lblGridProduc;

	private static Label lblGridProducSubtotales;

	private static Label lineaNegocioNombreLbl;
	private static Long codigoLineaNegocioFiltrado;

	private static List<DatoReporteDTO> lista = null;
	private static List<DatoReporteDTO> datos = new ArrayList<DatoReporteDTO>();

	public static String codigoNotificacionProduccionSeleccionada = "";

	private static LinkedHashMap<String, String> mapaOrden = new LinkedHashMap<String, String>();

	private static NotificacionServiceAsync servicioNotificacion = GWT.create(NotificacionService.class);
	/**
	 * interfaz usada para obtener las constantes (i18n)
	 */
	private static ConstantesModuloNotificaciones CONSTANTES = GWT.create(ConstantesModuloNotificaciones.class);

	private static final String CSS_LIGHT_RED_MESSAGE = "light_red_message";
	private static final String CSS_LIGHT_RED_MESSAGE_ESTADO = "light_red_message2";
	private static final String CSS_LIGHT_RED_BG = "background_notifPlanta";
	private static final String CSS_TITLE_NOTIF_PLANTA = "titleNotificacionPlanta";

	private static Label lblEstadoNotificacion;

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		dibujarBandaIconos();
		formularioCombos = initFormularioCombos();

		RootPanel.get("formularioCombos").add(formularioCombos);

		LayoutSpacer layoutSpacer = new LayoutSpacer();
		layoutSpacer.setHeight(5);
		layout.addMember(layoutSpacer);

		lineaNegocioNombreLbl = new Label();
		lineaNegocioNombreLbl.setHeight(12);
		lineaNegocioNombreLbl.setAlign(Alignment.LEFT);
		lineaNegocioNombreLbl.setStyleName(CSS_LIGHT_RED_MESSAGE);
		lineaNegocioNombreLbl.setVisible(false);
		layout.addMember(lineaNegocioNombreLbl);

		lblEstadoNotificacion = new Label();
		lblEstadoNotificacion.setHeight(5);
		lblEstadoNotificacion.setWidth(200);
		lblEstadoNotificacion.setAlign(Alignment.LEFT);
		lblEstadoNotificacion.setStyleName(CSS_LIGHT_RED_MESSAGE_ESTADO);
		lblEstadoNotificacion.setContents("Estado: ");
		lblEstadoNotificacion.setVisible(true);
		layout.addMember(lblEstadoNotificacion);

		layoutSpacer = new LayoutSpacer();
		layoutSpacer.setHeight(15);
		layout.addMember(layoutSpacer);

		layout.setOverflow(Overflow.VISIBLE);
		layout.setAutoWidth();

		lblGridProduc = new Label(CONSTANTES.tituloGridProduccion());
		lblGridProduc.setWrap(false);
		lblGridProduc.setWidth100();
		lblGridProduc.setAutoHeight();
		lblGridProduc.setVisible(false);
		lblGridProduc.setAlign(Alignment.LEFT);
		lblGridProduc.setStyleName(CSS_TITLE_NOTIF_PLANTA);
		layout.addMember(lblGridProduc);

		layoutSpacer = new LayoutSpacer();
		layoutSpacer.setHeight(15);
		layout.addMember(layoutSpacer);

		gridVarProd.setVisible(false);
		gridVarProd.addEditCompleteHandler(new EditCompleteHandler() {
			public void onEditComplete(EditCompleteEvent event) {
				actualiazarSubtotales();
			}
		});
		layout.addMember(gridVarProd);

		layoutSpacer = new LayoutSpacer();
		layoutSpacer.setHeight(15);
		layout.addMember(layoutSpacer);

		HLayout hlayout = new HLayout();

		layoutSpacer = new LayoutSpacer();
		layoutSpacer.setWidth(223);
		hlayout.addMember(layoutSpacer);

		VLayout subtotaleslayout = new VLayout();

		lblGridProducSubtotales = new Label(CONSTANTES.tituloGridProduccionSubtotales());
		lblGridProducSubtotales.setWrap(false);
		lblGridProducSubtotales.setWidth(400);
		lblGridProducSubtotales.setAutoHeight();
		lblGridProducSubtotales.setVisible(false);
		lblGridProducSubtotales.setAlign(Alignment.LEFT);
		lblGridProducSubtotales.setStyleName(CSS_TITLE_NOTIF_PLANTA);
		subtotaleslayout.addMember(lblGridProducSubtotales);

		layoutSpacer = new LayoutSpacer();
		layoutSpacer.setHeight(15);
		subtotaleslayout.addMember(layoutSpacer);

		gridVarProdSubtotales.setDisabled(false);
		gridVarProdSubtotales.setCanEdit(false);
		gridVarProdSubtotales.setHeight(150);
		gridVarProdSubtotales.setVisible(false);
		subtotaleslayout.addMember(gridVarProdSubtotales);

		hlayout.addMember(subtotaleslayout);

		// layout.addMember(gridVarProdSubtotales);

		layout.addMember(hlayout);

		RootPanel.get("formularioVariables").add(layout);

		agregarEventosFormularioCombos();

		// Timer para verificar si la sesion finaliza y mostrarle esto al
		// usuario
		VerificarSesion verificarSesion = new VerificarSesion();
		verificarSesion.iniciarSesionTimer();
	}

	private void actualiazarSubtotales() {
		List<DatoReporteDTO> registros = gridVarProd.obtenerListaSubtotales(columnas);
		gridVarProdSubtotales.limpiarGrid();
		gridVarProdSubtotales.cargarGridSubtotales(columnas, registros);
	}

	public static void mostrarBarraProgreso() {
		RootPanel.get("barraProgreso").setVisible(true);
	}

	public static void ocultarBarraProgreso() {
		RootPanel.get("barraProgreso").setVisible(false);
	}

	private static void habilitarDeshabilitarGrabar(boolean disabled) {

		imagenGrabar.setDisabled(disabled);
		etiquetaGrabar.setDisabled(disabled);
	}

	private void eventoClickGrabar() {
		if (variableItem.isDisabled() || variableItem.getValue() == null || datos.size() == 0 || columnas.size() == 0) {
			Window.alert(CONSTANTES.variableNoSeleccionada());
			return;
		}

		String codigoTableroStr = tableroControlItem.getValue().toString();

		if (codigoTableroStr == null || codigoTableroStr.equals("")) {
			Window.alert(CONSTANTES.debeSeleccionarUnValorDeTableroControl());
			return;
		}
		habilitarDeshabilitarGrabar(true);
		mostrarBarraProgreso();

		final Date fechaActual = (Date) fechaItem.getValue();
		final Long codigoPuestoTrabajo = Long.parseLong(puestoTrabajoItem.getValue().toString());
		final Long codigoLineaNegocio = Long.parseLong(lineaNegocioItem.getValue().toString());
		final Long codigoTableroControl = Long.parseLong(codigoTableroStr);
		final Long codigoRegistroReporteECS = datos.get(0).getPkRegistroReporte();

		GWT.log("linea: " + codigoLineaNegocio + " tablero: " + codigoTableroControl + " fecha: " + fechaActual);

		// ASYNCALLBACK VALIDACION
		final AsyncCallback<Boolean> callbackValidacion = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				Window.alert(CONSTANTES.errorRegistrandoLaNotificacion());
				GWT.log("FALLO EN METODO:  registrarNotificacionDiaria", throwable);
				habilitarDeshabilitarGrabar(false);
				return;
			}

			public void onSuccess(Boolean registrosCorrecto) {
				if (registrosCorrecto) {
					registrarNotificacionDiaria(codigoLineaNegocio, codigoTableroControl, codigoRegistroReporteECS,
							codigoPuestoTrabajo, fechaActual);
				} else {

					Window.alert("Las horas de produccion no pueden ser 0");
					habilitarDeshabilitarGrabar(false);
					ocultarBarraProgreso();
				}

			}
		};

		lista = gridVarProd.exportarGrid(columnas);
		servicioNotificacion.validarRegistrosNotificacionDiaria(lista, columnas, callbackValidacion);

	}

	/**
	 * Crea el registro en la tabla de notificacion diaria
	 * 
	 * @param codigoLineaNegocio codigo de la linea de negocio
	 * @param codigoTableroControl codigo del tablero de control
	 * @param codigoRegistroReporteECS codigo de registro del reporte ECS
	 * @param codigoPuestoTrabajo codigo del puestro de trabajo
	 * @param fechaActual fecha
	 */
	private static void registrarNotificacionDiaria(final Long codigoLineaNegocio, final Long codigoTableroControl,
			final Long codigoRegistroReporteECS, final Long codigoPuestoTrabajo, final Date fechaActual) {

		GWT.log(" entro a registrarNotificacionDiaria");

		final AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				Window.alert(CONSTANTES.errorRegistrandoLaNotificacion());
				GWT.log("FALLO EN METODO:  registrarNotificacionDiaria", throwable);
				habilitarDeshabilitarGrabar(false);
				return;
			}

			public void onSuccess(Boolean estaInsertado) {
				habilitarDeshabilitarGrabar(false);
				if (!estaInsertado) {
					Window.alert(CONSTANTES.errorRegistrandoLaNotificacion());
					return;
				}
				GWT.log(" exito");

				obtenerNotificacionDiaria(codigoLineaNegocio, codigoTableroControl, codigoPuestoTrabajo,
						codigoRegistroReporteECS, fechaActual);
			}
		};

		// Verifica si todo el grid esta lleno en caso de que sea VP
		if (variableItem.getValue().toString().equals(VARIABLE_PRODUCCION)) {
			GWT.log("exportando grid");
			lista = gridVarProd.exportarGrid(columnas);
			GWT.log("grid eportado  correctamente");
			if (lista == null) {
				Window.alert(CONSTANTES.debeAsignarOrdenesYSilos());
				habilitarDeshabilitarGrabar(false);
				return;
			}
		}

		SC.confirm(CONSTANTES.confirmacionGrabar(), new BooleanCallback() {
			public void execute(Boolean value) {
				if (value != null && value) {
					servicioNotificacion.registrarNotificacionDiaria(codigoLineaNegocio, codigoTableroControl, fechaActual,
							codigoPuestoTrabajo, callback);
				} else {
					habilitarDeshabilitarGrabar(false);
					ocultarBarraProgreso();
				}
			}
		});

	}

	/**
	 * Obtiene la notificacion diaria registrada
	 * 
	 * @param codigoLineaNegocio codigo de la linea de negocio
	 * @param codigoTableroControl codigo del tablero de control
	 * @param codigoRegistroReporteECS codigo de registro del reporte ECS
	 * @param codigoPuestoTrabajo codigo del puestro de trabajo
	 * @param fechaActual fecha
	 */
	public static void obtenerNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl,
			final Long codigoPuestoTrabajo, final Long codigoRegistroReporteECS, final Date fechaActual) {
		GWT.log("entro  a obtenerNotificacionDiaria");
		AsyncCallback<NotificaciondiariaDTO> callback = new AsyncCallback<NotificaciondiariaDTO>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("FALLO EN METODO:  obtenerNotificacionDiaria", throwable);
				return;
			}

			public void onSuccess(NotificaciondiariaDTO notificacionDiaria) {

				if (notificacionDiaria != null) {
					GWT.log("se obtuvo  notif diaria con exito");
					Long codigoNotificacionDiaria = notificacionDiaria.getPkCodigoNotificaciondiaria();

					// Para VO se usa la lista "datos" porque este CU solo es
					// una consulta por sugerencia de la BSL. Se llama Registrar
					// VO pero es una consulta de VO!
					if (variableItem.getValue().toString().equals(VARIABLE_OPERACION)) {
						GWT.log("llamando a registrarNotificacionesOperacion");
						registrarNotificacionesOperacion(datos, codigoRegistroReporteECS, codigoNotificacionDiaria,
								codigoPuestoTrabajo, fechaActual);
					}

					if (variableItem.getValue().toString().equals(VARIABLE_PRODUCCION)) {
						GWT.log("llamando a registrarNotificacionesProduccion");
						registrarNotificacionesProduccion(lista, codigoRegistroReporteECS, codigoNotificacionDiaria,
								codigoPuestoTrabajo, fechaActual);
					}
				}
			}
		};

		servicioNotificacion.obtenerNotificacionDiaria(codigoLineaNegocio, codigoTableroControl, fechaActual, callback);
	}

	/**
	 * Almacena en bd los registros asociados a cada hora de pruduccion en el
	 * puesto de trabajo seleccionado
	 * 
	 * @param listaDatos lista de los registros que contiene la tabla( grid de
	 *            notificaciones de produccion )
	 * @param codigoRegistroReporteECS codigo de registro del reporte ECS
	 * @param codigoNotificacionDiaria codigo de la notificacion diaria
	 *            registrada
	 * @param codigoPuestoTrabajo codigo del puestro de trabajo
	 * @param fechaActual fecha
	 */
	private static void registrarNotificacionesProduccion(List<DatoReporteDTO> listaDatos, Long codigoRegistroReporteECS,
			final Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro) {

		GWT.log("Entro a: registrarNotificacionesProduccion");

		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				Window.alert(CONSTANTES.errorRegistrandoLaNotificacion());
				GWT.log("FALLO! EN METODO:  registrarNotificacionesProduccion", throwable);
				return;
			}

			public void onSuccess(Boolean estaInsertado) {
				if (!estaInsertado) {
					Window.alert(CONSTANTES.errorInesperado());
					ocultarBarraProgreso();
					return;
				}

				GridVariablesProduccion.cargarCodigosNotificaciones(codigoNotificacionDiaria, gridVarProd);
				notificacionExiste = true;

				Window.alert(CONSTANTES.registroExitoso());

				ocultarBarraProgreso();
			}
		};

		servicioNotificacion.registrarNotificacionesProduccion(listaDatos, columnas, codigoRegistroReporteECS,
				codigoNotificacionDiaria, codigoPuestoTrabajo, fechaRegistro, callback);
	}

	/**
	 * Metodo que resetea el grid que se tenga en pantalla e inicia la carga del
	 * grid operacion
	 */
	public static void eventoclickFiltrar() {
		GWT.log("iniciando metodo: resetIniciarCarga");
		columnas.clear();
		datos.clear();
		gridVarProd.limpiarGrid();
		gridVarProd.setDisabled(false);
		notificacionExiste = false;

		gridVarProdSubtotales.limpiarGrid();

		Object procesoVal = procesoItem.getValue();
		Object puestoTrabVal = puestoTrabajoItem.getValue();

		if (procesoVal == null || puestoTrabVal == null) {
			Window.alert(CONSTANTES.debeSeleccionarPuestoTrabYProceso());
			return;
		}
		codigoLineaNegocioFiltrado = Long.parseLong((String) lineaNegocioItem.getValue());
		Long codigoProceso = Long.parseLong(procesoVal.toString());
		Long codigoPuestoTrabajo = Long.parseLong(puestoTrabVal.toString());

		mostrarBarraProgreso();

		obtenerProceso(codigoProceso, codigoPuestoTrabajo);
	}

	/**
	 * Metodo carga el proceso DTO y continua con la secuencia de carga de datos
	 * 
	 * @param codigoProceso
	 * @param codigoPuestoTrabajo
	 */
	public static void obtenerProceso(Long codigoProceso, final Long codigoPuestoTrabajo) {
		GWT.log("iniciando metodo: obtenerProceso");
		AsyncCallback<ProcesoDTO> callback = new AsyncCallback<ProcesoDTO>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("Fallo  en metodo: obtenerProceso", throwable);
				return;
			}

			public void onSuccess(ProcesoDTO proceso) {

				if (proceso != null) {
					String estadoColumna = ESTADO_ACTIVO;
					String estadoPlantilla = ESTADO_ACTIVO;
					String siglasProceso = proceso.getSiglasProceso();

					obtenerPuestoTrabajo(codigoPuestoTrabajo, proceso.getPkCodigoProceso(), siglasProceso, estadoColumna,
							estadoPlantilla);
				}
			}
		};

		servicioComunicacion.obtenerProceso(codigoProceso, callback);
	}

	/**
	 * Metodo que carga el puesto de trabajo y continua con la secuencia de
	 * carga de datos
	 * 
	 * @param codigoPuestoTrabajo
	 * @param codigoProceso
	 * @param siglasProceso
	 * @param estadoColumna
	 * @param estadoPlantilla
	 */
	public static void obtenerPuestoTrabajo(final Long codigoPuestoTrabajo, final Long codigoProceso, final String siglasProceso,
			final String estadoColumna, final String estadoPlantilla) {
		GWT.log("iniciando metodo: obtenerPuestoTrabajo");
		AsyncCallback<PuestoTrabajoDTO> callback = new AsyncCallback<PuestoTrabajoDTO>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				GWT.log("error en metodo: obtenerPuestoTrabajo", throwable);
				Window.alert(CONSTANTES.errorInesperado());
				return;
			}

			public void onSuccess(PuestoTrabajoDTO puestoTrabajo) {

				if (puestoTrabajo != null) {

					String variableItemVal = variableItem.getValue().toString();

					if (variableItemVal != null) {
						String siglasPuestotrabajo = puestoTrabajo.getSiglasPuestotrabajo();
						String nombreArchivoVO = VO_ + siglasProceso + "_" + siglasPuestotrabajo + "%";
						String nombreArchivoVp = VP_ + siglasProceso + "_" + siglasPuestotrabajo + "%";

						if (variableItemVal.equals(VARIABLE_OPERACION)) {
							obtenerColumnas(codigoPuestoTrabajo, codigoProceso, nombreArchivoVO, estadoColumna, estadoPlantilla,
									VO);
						} else {
							obtenerColumnas(codigoPuestoTrabajo, codigoProceso, nombreArchivoVp, estadoColumna, estadoPlantilla,
									VP);
						}

					} else {
						Window.alert(CONSTANTES.debeSeleccionarUnTipoDeVariables());
					}
				}
			}
		};
		servicioComunicacion.obtenerPuestoTrabajo(codigoPuestoTrabajo, callback);
	}

	/**
	 * Metodo que carga la lista de objetos columnaDTO para construir el grid y
	 * continua con la secuencia de carga de datos para construir el grid.
	 * 
	 * @param codigoPuestoTrabajo
	 * @param codigoProceso
	 * @param nombreArchivo
	 * @param estadoColumna
	 * @param estadoPlantilla
	 */
	public static void obtenerColumnas(final Long codigoPuestoTrabajo, final Long codigoProceso, final String nombreArchivo,
			final String estadoColumna, final String estadoPlantilla, final String tipoVariable) {
		GWT.log("iniciando metodo: obtenerColumnas");
		limpiarLineaNegocioOrden();

		final Long codigoLineaNegocio = Long.parseLong(lineaNegocioItem.getValue().toString());
		final Date fechaReg = (Date) fechaItem.getValue();
		final String codigoTableroStr = (String) tableroControlItem.getValue();

		if (codigoTableroStr == null || codigoTableroStr.equals("")) {
			ocultarBarraProgreso();
			Window.alert(CONSTANTES.debeSeleccionarUnValorDeTableroControl());
			return;
		}

		final Long codigoTableroControl = new Long(codigoTableroStr);

		final AsyncCallback<List<ColumnareporteDTO>> obtenerColsCallback = new AsyncCallback<List<ColumnareporteDTO>>() {
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				ocultarBarraProgreso();
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("Fallo  en metodo: obtenerColumnas", throwable);
				return;
			}

			public void onSuccess(List<ColumnareporteDTO> columnasPar) {
				if (columnasPar.equals(null) || columnasPar.size() == 0) {
					ocultarBarraProgreso();
					Window.alert(CONSTANTES.noExistePlantillaEcsParaLaFecha());
					return;
				}

				columnas = columnasPar;
				Date fechaActual = (Date) fechaItem.getValue();

				if (notificacionExiste) {
					GWT.log("obtenerDatos onSuccess, existen registros");
					notificacionExiste = true;
					obtenerDatosBD(codigoLineaNegocio, codigoTableroControl, fechaReg, codigoProceso);
				} else {
					GWT.log("obtenerDatos onSuccess, NO existen registros");
					lblEstadoNotificacion.setContents("Estado: Generado");
					obtenerDatosEcs(fechaActual, nombreArchivo, tipoVariable, codigoProceso);
				}
			}
		};

		AsyncCallback<Boolean> verifSiNotifExisteCallback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				ocultarBarraProgreso();
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("FALLO EN METODO: verificarSiExisteNotificacion.", throwable);
				return;
			}

			public void onSuccess(Boolean existenRegistros) {
				notificacionExiste = existenRegistros;

				if (notificacionExiste) {
					servicioNotificacion.obtenerColumnasReportePorNotif(codigoLineaNegocio, codigoTableroControl, fechaReg,
							codigoPuestoTrabajo, obtenerColsCallback);
				} else {
					System.out.println("-->" + codigoPuestoTrabajo);
					System.out.println("-->" + codigoProceso);
					System.out.println("-->" + estadoColumna);
					System.out.println("-->" + estadoPlantilla);
					
					servicioNotificacion.obtenerColumnasReporte(codigoPuestoTrabajo, codigoProceso, estadoColumna,
							estadoPlantilla, obtenerColsCallback);
				}
			}

		};

		servicioNotificacion.verificarSiExistenRegistrosNotifProd(codigoLineaNegocio, codigoTableroControl, fechaReg,
				codigoPuestoTrabajo, verifSiNotifExisteCallback);
	}

	/**
	 * Muestra en la gui el grid correspondiente a la variable seleccionada
	 * (produccion u operacion)
	 */
	private static void mostrarGrid() {
		String valorVariable = (String) variableItem.getValue();

		if (valorVariable == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnTipoDeVariables());
			return;
		}

		// if (valorVariable.equals(VARIABLE_OPERACION)) {
		// layout.removeChild(gridVarProd);
		// layout.addMember(gridVarOper);
		// lblGridProduc.setContents(CONSTANTES.tituloGridOperacion());
		// lblGridProduc.setVisible(true);
		//
		// }

		lblGridProduc.setVisible(true);
		lblGridProducSubtotales.setVisible(true);

		gridVarProd.setVisible(true);
		gridVarProdSubtotales.setVisible(true);
	}

	/**
	 * Obtiene los datos de los registros del grid de la bd (cuando ya se ha
	 * registrado la notificacion diaria para el puesto de trabajo seleccionado)
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param fechaReg
	 * @param codigoProceso
	 */
	private static void obtenerDatosBD(Long codigoLineaNegocio, Long codigoTableroControl, final Date fechaReg,
			final Long codigoProceso) {

		GWT.log("iniciando metodo: obtenerDatosBD");

		AsyncCallback<List<DatoReporteDTO>> asyncCallback = new AsyncCallback<List<DatoReporteDTO>>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				GWT.log("fallo  en metodo: obtenerDatosBD", throwable);
				Window.alert(CONSTANTES.falloIntentandoObtenerDatosDelReporte());
				return;
			}

			public void onSuccess(List<DatoReporteDTO> datosPar) {
				if (datosPar == null || (datosPar.size() == 0)) {
					GWT.log("no se encontraron datos para el reporte");
					Window.alert(CONSTANTES.noSeEncontraronDatosParaElReporte());
					ocultarBarraProgreso();
					return;
				}

				datos = datosPar;
				asignarEventoEnColsOrdenYSilo(fechaReg, codigoProceso);
				mostrarGrid();
				DatoReporteDTO datoReporteDTO = datosPar.get(0);
				Boolean notificacionAprobada = datoReporteDTO.getNotificacionAprobada();

				lblEstadoNotificacion.setContents("Estado: " + datoReporteDTO.getEstadoNotificaciondiaria());
				GWT.log("Valor notif aprobada: " + notificacionAprobada.booleanValue());

				if (notificacionAprobada.booleanValue()) {
					habilitarODeshabilitarMenuYGrids(false);

					Window.alert(CONSTANTES.notifRegistradaAprobadaValoresCargadosConExito());
				} else {
					habilitarODeshabilitarMenuYGrids(true);
					Window.alert(CONSTANTES.notifRegistradaValoresCargadosConExito());
				}

				// este timer se crea para dejar que termine de pintar las filas
				// en el grid antes de hacer la revisión de la linea de negocio
				new Timer() {
					@Override
					public void run() {
						revisarLineaNegocioDeOrden();
					}

				}.schedule(500);

				List<DatoReporteDTO> registros = gridVarProd.obtenerListaSubtotales(columnas);
				gridVarProdSubtotales.limpiarGrid();
				gridVarProdSubtotales.cargarGridSubtotales(columnas, registros);
			}
		};

		Long codigoPuestoTrabajo = Long.parseLong(puestoTrabajoItem.getValue().toString());

		servicioNotificacion.obtenerDatosBD(codigoLineaNegocio, codigoTableroControl, codigoProceso, codigoPuestoTrabajo,
				fechaReg, asyncCallback);

	}

	private static void habilitarODeshabilitarMenuYGrids(Boolean editable) {
		GWT.log("Entro a funcion que habilita o deshabilita");
		GWT.log("Valor editable: " + editable.booleanValue());
		gridVarProd.setDisabled(!editable);
//		imagenProduccionLavado.setDisabled(!editable);
//		etiquetaProduccionLavado.setDisabled(!editable);
		imagenGrabar.setDisabled(!editable);
		etiquetaGrabar.setDisabled(!editable);

		ocultarBarraProgreso();
	}

	/**
	 * Obtiene los datos del reporte ecs cargado en la bd
	 * 
	 * @param fecha
	 * @param nombre
	 * @param tipoVariable
	 * @param codigoProceso
	 */
	public static void obtenerDatosEcs(final Date fecha, String nombre, String tipoVariable, final Long codigoProceso) {
		GWT.log("iniciando metodo: obtenerDatosEcs");
		AsyncCallback<List<DatoReporteDTO>> asyncCallback = new AsyncCallback<List<DatoReporteDTO>>() {
			public void onFailure(Throwable throwable) {
				GWT.log("Fallo  en metodo: obtenerDatosEcs", throwable);
				Window.alert(CONSTANTES.falloIntentandoObtenerDatosDelReporte());
				ocultarBarraProgreso();
				return;
			}

			public void onSuccess(final List<DatoReporteDTO> datosPar) {
				GWT.log("obtenerDatosEcs culmino con exito");
				if (datosPar == null || datosPar.size() == 0) {
					GWT.log("lista es null");
					SC.confirm(CONSTANTES.deseaRealizarLaCargaManual(), new BooleanCallback() {
						public void execute(Boolean value) {
							if (value != null && value) {
								GWT.log("carga manual true");
								cargarProducionesManuales(fecha, codigoProceso);
							} else {
								ocultarBarraProgreso();
							}
						}
					});
				} else {
					datos = datosPar;
					asignarEventoEnColsOrdenYSilo(fecha, codigoProceso);
					mostrarGrid();
					ocultarBarraProgreso();
				}
			}
		};

		servicioNotificacion.obtenerDatosReporte(fecha, nombre, tipoVariable, codigoProceso, asyncCallback);
	}

	/**
	 * crea una lista de registros en blanco con las horas, para que el usuario
	 * realice la carga manul en el grid
	 * 
	 * @param codigoProceso
	 * @param fecha
	 * @param fecha
	 * @param codigoProceso
	 * @param codigoProceso
	 */
	private static void cargarProducionesManuales(final Date fecha, final Long codigoProceso) {
		GWT.log("iniciando metodo: cargarProducionesManuales");
		AsyncCallback<List<DatoReporteDTO>> asyncCallback = new AsyncCallback<List<DatoReporteDTO>>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				GWT.log("fallo  en metodo: obtenerDatosReporteCargaManual", throwable);
				Window.alert(CONSTANTES.falloIntentandoObtenerDatosDelReporte());
				return;
			}

			public void onSuccess(List<DatoReporteDTO> datosReporte) {
				datos = datosReporte;
				asignarEventoEnColsOrdenYSilo(fecha, codigoProceso);
				mostrarGrid();
				ocultarBarraProgreso();
			}
		};

		servicioNotificacion.obtenerDatosReporteCargaManual(asyncCallback);
	}

	/**
	 * Asigna los evento a las columnas de orden de produccion, de forma que
	 * cuando se hagan click aparezcan los popups de carga de cada uno
	 * 
	 * @param fecha fecha de registro
	 * @param codigoProceso
	 * @param codigoProceso codigo del proceso
	 */
	private static void asignarEventoEnColsOrdenYSilo(final Date fecha, final Long codigoProceso) {

		gridVarProdSubtotales.agregarColumnasVariablesSubtotal(columnas);

		if (variableItem.getValue().toString().equals(VARIABLE_OPERACION)) {
			gridVarOper.cargarVariablesOperacion(columnas, datos);
		} else {
			gridVarProd.agregarColumnasVariables(columnas);
			gridVarProd.cargarGrid(columnas, datos);
			// Handler del mouse para capturar el codigo de la notif de
			// produc.
			RowMouseUpHandler handler = new RowMouseUpHandler() {
				public void onRowMouseUp(RowMouseUpEvent event) {
					Record record = event.getRecord();
					codigoNotificacionProduccionSeleccionada = String.valueOf(record
							.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_NOTIFICACION_PRODUCCION));
				}
			};
			gridVarProd.addRowMouseUpHandler(handler);

			// Agrega dialogo para asignar las ordenes de produccion
			RecordClickHandler recordClickHandlerOrden = new RecordClickHandler() {
				public void onRecordClick(RecordClickEvent event) {
					DialogoCargarOrdenOSilo dialogoCargarOrden = new DialogoCargarOrdenOSilo(servicioNotificacion, fecha,
							codigoProceso, columnas.get(0).getPkCodigoPlantillareporte(), mapaOrden, gridVarProd,
							gridVarProdSubtotales, columnas, TipoDialogo.DLG_ORDENES_PRODUC);
					dialogoCargarOrden.showDialog();

				}
			};

			gridVarProd.getField(GridVariablesProduccion.COLUMNA_ORDEN).addRecordClickHandler(recordClickHandlerOrden);

			// Agrega el dialogo para asignar los silo
			RecordClickHandler recordClickHandlerSilo = new RecordClickHandler() {
				public void onRecordClick(RecordClickEvent event) {
					DialogoCargarOrdenOSilo dialogoCargarOrden = new DialogoCargarOrdenOSilo(servicioNotificacion, fecha,
							codigoProceso, columnas.get(0).getPkCodigoPlantillareporte(), mapaOrden, gridVarProd,
							gridVarProdSubtotales, columnas, TipoDialogo.DLG_SILO);
					dialogoCargarOrden.showDialog();

				}
			};

			gridVarProd.getField(GridVariablesProduccion.COLUMNA_SILO).addRecordClickHandler(recordClickHandlerSilo);

			// Agrega el dialogo para asignar las plantillas
			RecordClickHandler recordClickHandlerPlantilla = new RecordClickHandler() {
				public void onRecordClick(RecordClickEvent event) {
					DialogoCargarOrdenOSilo cargarOrdenOSilo = new DialogoCargarOrdenOSilo(servicioNotificacion, fecha,
							codigoProceso, columnas.get(0).getPkCodigoPlantillareporte(), mapaOrden, gridVarProd,
							gridVarProdSubtotales, columnas, TipoDialogo.DLG_PLANTILLA);
					cargarOrdenOSilo.showDialog();
				}
			};

			gridVarProd.getField(GridVariablesProduccion.COLUMNA_PLANTILLA).addRecordClickHandler(recordClickHandlerPlantilla);
		}
	}

//	public void eventoClickCambioProduccionLavado() {
//
//		if (notificacionExiste) {
//			ListGridRecord record = gridVarProd.getSelectedRecord();
//			if (record != null) {
//
//				String hora = record.getAttribute(GridVariablesProduccion.COLUMNA_HORA);
//				Long codigoPuestoTrabajo = Long.parseLong(puestoTrabajoItem.getValue().toString());
//				Long codigoLineaNegocio = Long.parseLong(lineaNegocioItem.getValue().toString());
//				Long codigoTableroControl = Long.parseLong(tableroControlItem.getValue().toString());
//
//				DialogoCambioProduccion dcp = new DialogoCambioProduccion(servicioNotificacion, (Date) fechaItem.getValue(),
//						columnas.get(0).getPkCodigoPlantillareporte(), hora, gridVarProd, gridVarProdSubtotales, columnas,
//						codigoPuestoTrabajo, codigoLineaNegocio, codigoTableroControl);
//				dcp.showDialog();
//			}
//		} else {
//			Window.alert(CONSTANTES.debeRegistrarLasNotificaciones());
//		}
//	}

	/**
	 * Crea el formulario que contiene los filtros para carga la data o
	 * registrar la notificacion
	 * 
	 * @return
	 */
	private static DynamicForm initFormularioCombos() {

		DynamicForm form = new DynamicForm();

		form.setNumCols(6);
		form.setWidth(670);
		form.setColWidths(25, 25, 25, 25, 25, 25);
		form.setAlign(Alignment.LEFT);

		divisionItem = Combos.cargarComboDivisiones();
		sociedadItem = Combos.cargarComboSociedades();
		unidadItem = Combos.cargarComboUnidades();
		lineaNegocioItem = Combos.cargarComboLineasNegocio();
		procesoItem = Combos.cargarComboProcesos();
		tableroControlItem = Combos.cargarComboTablerosControl();
		puestoTrabajoItem = Combos.cargarComboPuestoTrabajo();
		variableItem = Combos.cargarComboVariables();

		fechaItem = Combos.cargarComboFecha(NOMBRE_FECHA_ACTUAL, CONSTANTES.fechaDeRegistro());

		form.setItems(divisionItem, sociedadItem, unidadItem, lineaNegocioItem, procesoItem, puestoTrabajoItem,
				tableroControlItem, fechaItem, variableItem/* , buttonItem */);

		return form;
	}

	/**
	 * Metodo para agregar los eventos de actualizacion de los combos del
	 * registro de produccion semanal: division, sociedad,unidad,linea de
	 * negocio, proceso
	 * 
	 * @param formularioDiaProduccion
	 * @param divisionItem
	 * @param sociedadItem
	 * @param unidadItem
	 * @param lineaNegocioItem
	 * @param procesoItem
	 * @param fechaInicio
	 * @param fechaFin
	 */
	private void agregarEventosFormularioCombos() {
		Combos.addDivisionItemChangeHandlerNotificacion(divisionItem, sociedadItem, unidadItem, lineaNegocioItem, procesoItem,
				tableroControlItem, puestoTrabajoItem, variableItem);

		Combos.addSociedadItemChangeHandlerNotificacion(sociedadItem, unidadItem, lineaNegocioItem, procesoItem,
				tableroControlItem, puestoTrabajoItem, variableItem);

		Combos.addUnidadItemChangeHandlerNotificacion(unidadItem, procesoItem, lineaNegocioItem, puestoTrabajoItem,
				tableroControlItem, variableItem);

		Combos.addLineaNegocioItemChangeHandlerRegistro(lineaNegocioItem, procesoItem);

		Combos.addProcesoItemChangeNotificacionParaPuestoTrabajo(procesoItem, puestoTrabajoItem);

		Combos.addPuestoTrabajoItemChangeHandlerNotificacion(puestoTrabajoItem, unidadItem, variableItem, tableroControlItem);
	}

	/**
	 * Metodo que crea la banda de iconos del menu superior
	 */
	private void dibujarBandaIconos() {
		// Refrescar
		Img imagenRefrescar = new Img("", 18, 20);
		imagenRefrescar.setName(ConstantesGWT.NOMBRE_IMAGEN_REFRESCAR);
		imagenRefrescar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRefrescar();
			}
		});
		Label etiquetaRefrescar = new Label(ConstantesGWT.REFRESCAR);
		etiquetaRefrescar.setCursor(Cursor.HAND);
		etiquetaRefrescar.setHeight(15);
		etiquetaRefrescar.setID(ConstantesGWT.NOMBRE_ETIQUETA_REFRESCAR);
		etiquetaRefrescar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRefrescar();
			}
		});

		RootPanel rootPanel = RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REFRESCAR);
		rootPanel.add(imagenRefrescar);
		rootPanel.setStyleName(ConstantesGWT.NOMBRE_ESTILO_REFRESCAR);
		rootPanel.add(etiquetaRefrescar);

		// Cambio de Produccion por Lavado
//		imagenProduccionLavado = new Img("", 18, 20);
//		imagenProduccionLavado.setName(ConstantesGWT.NOMBRE_IMAGEN_PRODUCCIONLAVADO);
//		imagenProduccionLavado.setDisabled(false);
//		imagenProduccionLavado.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				eventoClickCambioProduccionLavado();
//			}
//		});
//		etiquetaProduccionLavado = new Label(ConstantesGWT.PRODUCCIONLAVADO);
//		etiquetaProduccionLavado.setCursor(Cursor.HAND);
//		etiquetaProduccionLavado.setHeight(15);
//		etiquetaProduccionLavado.setWidth(120);
//		etiquetaProduccionLavado.setID(ConstantesGWT.NOMBRE_ETIQUETA_PRODUCCIONLAVADO);
//		etiquetaProduccionLavado.setDisabled(false);
//		etiquetaProduccionLavado.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				eventoClickCambioProduccionLavado();
//			}
//		});

//		rootPanel = RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_PRODUCCIONLAVADO);
//		rootPanel.add(imagenProduccionLavado);
//		rootPanel.setStyleName(ConstantesGWT.NOMBRE_ESTILO_PRODUCCIONLAVADO);
//		rootPanel.add(etiquetaProduccionLavado);

		// Grabar
		imagenGrabar = new Img("", 18, 20);
		imagenGrabar.setName(ConstantesGWT.NOMBRE_IMAGEN_GRABAR);
		imagenGrabar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGrabar();
			}
		});

		etiquetaGrabar = new Label(CONSTANTES.menuGrabar());
		etiquetaGrabar.setCursor(Cursor.HAND);
		etiquetaGrabar.setHeight(15);
		etiquetaGrabar.setID(ConstantesGWT.NOMBRE_ETIQUETA_GRABAR);
		etiquetaGrabar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGrabar();
			}
		});

		rootPanel = RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GRABAR);
		rootPanel.add(imagenGrabar);
		rootPanel.setStyleName(ConstantesGWT.NOMBRE_ESTILO_GRABAR);
		rootPanel.add(etiquetaGrabar);

		// Filtrar
		Img imagenFiltrar = new Img("", 18, 20);
		imagenFiltrar.setName(ConstantesGWT.NOMBRE_IMAGEN_FILTRAR);
		imagenFiltrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoclickFiltrar();
			}
		});

		Label etiquetaFiltrar = new Label(ConstantesGWT.FILTRAR);
		etiquetaFiltrar.setCursor(Cursor.HAND);
		etiquetaFiltrar.setHeight(15);
		etiquetaFiltrar.setID(ConstantesGWT.NOMBRE_ETIQUETA_FILTRAR);
		etiquetaFiltrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoclickFiltrar();
			}
		});

		rootPanel = RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_FILTRAR);
		rootPanel.add(imagenFiltrar);
		rootPanel.setStyleName(ConstantesGWT.NOMBRE_ESTILO_FILTRAR);
		rootPanel.add(etiquetaFiltrar);
	}

	public void eventoClickRefrescar() {
		Window.Location.assign(ConstantesGWT.LINK_CREAR_NOTIFICACIONES_PLANTA);
	}

	private static void registrarNotificacionesOperacion(List<DatoReporteDTO> listaDatos, Long codigoRegistroReporteECS,
			Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable throwable) {
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("Error en metodo: registrarNotificacionesOperacion", throwable);
				return;
			}

			public void onSuccess(Boolean estaInsertado) {
				if (!estaInsertado) {
					Window.alert(CONSTANTES.errorInesperado());
					CONSTANTES.errorInesperado();
				}

				Window.alert(CONSTANTES.registroExitoso());
				Window.Location.assign(ConstantesGWT.LINK_CONSULTAR_NOTIFICACIONES_PLANTA);
			}
		};

		servicioNotificacion.registrarNotificacionesOperacion(listaDatos, codigoRegistroReporteECS, codigoNotificacionDiaria,
				codigoPuestoTrabajo, fechaRegistro, callback);
	}

	public static void revisarLineaNegocioDeOrden() {
		Long codigoLineaNegocioOrden;
		String nombreLineaNegocioOrden = "";
		boolean encontroLineaNegocioDiferente = false;
		int row = 0;
		for (ListGridRecord record : gridVarProd.getRecords()) {
			String codigoLineaNegocioOrdenStr = record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_LINEA_NEGOCIO);
			if (codigoLineaNegocioOrdenStr != null) {
				codigoLineaNegocioOrden = new Long(codigoLineaNegocioOrdenStr);
				if (codigoLineaNegocioOrden.longValue() != codigoLineaNegocioFiltrado.longValue()) {
					record.set_baseStyle(CSS_LIGHT_RED_BG);
					nombreLineaNegocioOrden = record.getAttribute(GridVariablesProduccion.COLUMNA_NOMBRE_LINEA_NEGOCIO);
					encontroLineaNegocioDiferente = true;

					gridVarProd.refreshRow(row);
				} else {
					record.set_baseStyle(null);
				}
			}
		}
		if (encontroLineaNegocioDiferente) {
			lineaNegocioNombreLbl.setContents(LINEA_NEGOCIO_TXT + nombreLineaNegocioOrden);
			lineaNegocioNombreLbl.setVisible(true);
		} else {
			limpiarLineaNegocioOrden();
		}
	}

	public static void limpiarLineaNegocioOrden() {
		lineaNegocioNombreLbl.setContents("");
		lineaNegocioNombreLbl.setVisible(false);
	}
}