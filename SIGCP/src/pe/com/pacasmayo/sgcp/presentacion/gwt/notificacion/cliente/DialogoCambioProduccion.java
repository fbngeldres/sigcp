package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PlantillaProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.componentes.GridVariablesProduccion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.UtilConverter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VStack;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DialogoCambioProduccion.java
 * Modificado: Mar 31, 2011 9:00:38 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class DialogoCambioProduccion extends com.smartgwt.client.widgets.Window implements ClienteServicioGwt {

	private static LinkedHashMap<String, String> mapaSilos = new LinkedHashMap<String, String>();
	private DynamicForm form;
	private static SelectItem comboOrdenOrigen;
	private static SelectItem comboOrdenDestino;

	private static SelectItem comboSilosOrigen;
	private static SelectItem comboSilosDestino;

	private static SelectItem comboMinutos;

	private static RadioGroupItem tipoCambios;

	private static Date fecha;
	private static String hora;
	private static Long codigoPlantillaReporte;
	private static GridVariablesProduccion gridVarProd;
	private static GridVariablesProduccion gridVarProdSubtotales;

	private static List<ColumnareporteDTO> columnas;

	private final Integer CAMBIO_PRODUCCION_OK = new Integer(1);
	private final Integer ERROR_NO_EXISTE_NOTIF_DIA_SIG = new Integer(2);
	private final Integer ERROR_ORDEN_PROD_DISTINTA = new Integer(3);

	private static NotificacionServiceAsync servicioNotificacion;

	/**
	 * Variables para Cambio de Producción
	 */

	private boolean isLavado = false;

	/**
	 * interfaz usada para obtener las constantes (i18n)
	 */
	private static ConstantesModuloNotificaciones CONSTANTES = GWT.create(ConstantesModuloNotificaciones.class);
	private final Long codigoPuestoTrabajo;
	private final Long codigoLineaNegocio;
	private final Long codigoTableroControl;

	public DialogoCambioProduccion(NotificacionServiceAsync servicioNotificacion, Date fecha, Long codigoProceso, String hora,
			GridVariablesProduccion gridVarProd, GridVariablesProduccion gridVarProdsubtotales, List<ColumnareporteDTO> columnas,
			Long codigoPuestoTrabajo, Long codigoLineaNegocio, Long codigoTableroControl) {
		super();
		this.codigoPuestoTrabajo = codigoPuestoTrabajo;
		this.codigoLineaNegocio = codigoLineaNegocio;
		this.codigoTableroControl = codigoTableroControl;

		initParams(servicioNotificacion, fecha, codigoProceso, hora, gridVarProd, gridVarProdsubtotales, columnas);
		initiGUI();
	}

	private void actualiazarSubtotales() {
		List<DatoReporteDTO> registros = gridVarProd.obtenerListaSubtotales(columnas);
		gridVarProdSubtotales.limpiarGrid();
		gridVarProdSubtotales.cargarGridSubtotales(columnas, registros);
	}

	/**
	 * Inicializa los atrubutos de la clase
	 * 
	 * @param servicio servicio de gwt para el modulo de notificaciones
	 * @param fechaReg fecha
	 * @param pgridVarProdsubtotales
	 * @param pcolumnas
	 * @param gridVarProd2
	 * @param codigoProc codigo de ordenes de produccion
	 */
	private static void initParams(NotificacionServiceAsync servicio, Date fechaReg, Long pcodigoPlantillaReporte, String phora,
			GridVariablesProduccion pgridVarProd, GridVariablesProduccion pgridVarProdsubtotales,
			List<ColumnareporteDTO> pcolumnas) {
		DialogoCambioProduccion.servicioNotificacion = servicio;
		DialogoCambioProduccion.fecha = fechaReg;
		DialogoCambioProduccion.codigoPlantillaReporte = pcodigoPlantillaReporte;
		DialogoCambioProduccion.hora = phora;
		DialogoCambioProduccion.gridVarProd = pgridVarProd;

		DialogoCambioProduccion.gridVarProdSubtotales = pgridVarProdsubtotales;
		DialogoCambioProduccion.columnas = pcolumnas;
	}

	/**
	 * Construye la gui del popup
	 */
	private void initiGUI() {
		setWidth(800);
		setHeight(400);

		String titulo = CONSTANTES.cpCambioProduccion();

		setTitle(titulo);

		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();

		Canvas canvas = new Canvas();
		final VStack vStack = new VStack();
		vStack.setWidth100();

		LayoutSpacer espacioBlanco = new LayoutSpacer();
		espacioBlanco.setHeight(5);

		vStack.addMember(espacioBlanco);

		Label bandaRoja = new Label();
		bandaRoja.setBackgroundColor(ConstantesGWT.COLOR_ROJO);
		bandaRoja.setHeight(10);
		bandaRoja.setWidth100();
		bandaRoja.setShowEdges(false);

		vStack.addMember(bandaRoja);

		espacioBlanco = new LayoutSpacer();
		espacioBlanco.setHeight(40);
		vStack.addMember(espacioBlanco);

		form = new DynamicForm();
		form.setWidth(750);
		form.setColWidths(130);
		form.setNumCols(6);
		form.setWrapItemTitles(true);
		form.setLayoutAlign(VerticalAlignment.TOP);
		form.setLayoutAlign(Alignment.CENTER);

		tipoCambios = new RadioGroupItem();
		tipoCambios.setTitle(CONSTANTES.cpTipoModificacion());
		tipoCambios.setVertical(false);
		tipoCambios.setWidth(200);
		tipoCambios.setColSpan(4);
		tipoCambios.setAlign(Alignment.CENTER);
		tipoCambios.setValueMap(CONSTANTES.cpCambioProduccion(), CONSTANTES.cpLavadoSilo());
		tipoCambios.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {
				if (event.getItem().getValue().equals(CONSTANTES.cpCambioProduccion())) {
					comboMinutos.setVisible(true);
					comboOrdenOrigen.setVisible(true);
					comboSilosOrigen.setVisible(false);
					comboOrdenDestino.setVisible(false);
					comboSilosDestino.setVisible(false);
					isLavado = false;
				} else if (event.getItem().getValue().equals(CONSTANTES.cpLavadoSilo())) {
					comboOrdenOrigen.setVisible(true);
					comboSilosOrigen.setVisible(true);
					comboOrdenDestino.setVisible(true);
					comboSilosDestino.setVisible(true);
					comboMinutos.setVisible(false);
					isLavado = true;
				}
				markForRedraw();
			}

		});

		cargarComboOrden();
		cargarComboSilos();

		comboMinutos = cargarComboMinutos();

		form.setFields(tipoCambios, comboOrdenOrigen, comboSilosOrigen, comboOrdenDestino, comboSilosDestino, comboMinutos);
		vStack.addMember(form);

		final HStack hStack = new HStack();

		IButton botonAceptar = new IButton(CONSTANTES.aceptar());
		botonAceptar.setAlign(Alignment.CENTER);
		botonAceptar.setAutoFit(true);
		botonAceptar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				botonAceptarClicked();
			}

		});
		hStack.addMember(botonAceptar);
		hStack.setAlign(Alignment.CENTER);
		vStack.addMember(espacioBlanco);
		vStack.addMember(hStack);

		canvas.addChild(vStack);
		addItem(canvas);

		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				hide();
			}
		});

		// es necesario mostrar primero el popup para poder ocultar los
		// elementos y que se puedan marcar a futuro para un redraw.
		show();
		comboOrdenOrigen.setVisible(false);
		comboSilosOrigen.setVisible(false);
		comboOrdenDestino.setVisible(false);
		comboSilosDestino.setVisible(false);
		comboMinutos.setVisible(false);
		hide();
		markForRedraw();

	}

	public void showDialog() {
		show();
	}

	/**
	 * Metodo que carga el combo de ordenes de produccion del proceso y del mes
	 * actual
	 * 
	 * @return SelectItem
	 */
	private static void cargarComboOrden() {

		comboOrdenDestino = new SelectItem();
		comboOrdenDestino.setName("comboOrden");
		comboOrdenDestino.setAllowEmptyValue(false);
		comboOrdenDestino.setColSpan(1);
		comboOrdenDestino.setTitle(CONSTANTES.cpOrdenProduccionLavado());

		comboOrdenOrigen = new SelectItem();
		comboOrdenOrigen.setName("comboOrdenLavado");
		comboOrdenOrigen.setAllowEmptyValue(false);
		comboOrdenOrigen.setColSpan(1);
		comboOrdenOrigen.setTitle(CONSTANTES.cpOrdenCambioProduccion());

		AsyncCallback<List<OrdenProduccionDTO>> asyncCallback = new AsyncCallback<List<OrdenProduccionDTO>>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<OrdenProduccionDTO> ordenes) {
				setOrdenesInCombo(ordenes);
			}

		};

		servicioNotificacion.getOrdenesByMesPlantillaLiberadas(fecha, codigoPlantillaReporte, asyncCallback);
	}

	private static void setOrdenesInCombo(List<OrdenProduccionDTO> ordenes) {
		if (ordenes == null || ordenes.size() == 0) {
			Window.alert(CONSTANTES.noExistenOrdenesLiberadasConProductoConPlantilla());
			return;
		}
		LinkedHashMap<String, String> mapaOrdenDTO = UtilConverter.obtenerMapaOrdenDTO(ordenes);
		comboOrdenOrigen.setValueMap(mapaOrdenDTO);
		comboOrdenDestino.setValueMap(mapaOrdenDTO);
	}

	private void botonAceptarClicked() {
		if (tipoCambios.getValue() != null) {
			SC.confirm(CONSTANTES.cambioProduccionIrreversible(), new BooleanCallback() {
				public void execute(Boolean value) {
					if (value != null && value) {
						if (isLavado) {
							aceptarCambioProduccionLavado();
						} else {
							ListGridRecord record = gridVarProd.getSelectedRecord();

							Long codOrndeProd = Long.parseLong(record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN));

							AsyncCallback<Integer> asyncCallback = new AsyncCallback<Integer>() {
								public void onFailure(Throwable throwable) {
									GWT.log("fallo  en metodo: validarSiExisteNotfiDiaSiguinte", throwable);
									Window.alert(CONSTANTES.errorInesperado());
									return;
								}

								public void onSuccess(Integer result) {
									if (result.intValue() == CAMBIO_PRODUCCION_OK) {
										aceptarCambioProduccionNormal();
									} else if (result.intValue() == ERROR_NO_EXISTE_NOTIF_DIA_SIG) {
										Window.alert(CONSTANTES.errorCambioProduccionUltimaHora());
									} else if (result.intValue() == ERROR_ORDEN_PROD_DISTINTA) {
										Window.alert(CONSTANTES.errorCambioProduccionUltimaHoraOrdenDist());
									}
								}
							};

							servicioNotificacion.validarSiExisteNotficacionDiaSiguinte(codigoLineaNegocio, codigoPuestoTrabajo,
									codigoTableroControl, fecha, hora, codOrndeProd, asyncCallback);

						}
					}
				}
			});
		}
	}

	/**
	 * Funcion que obtiene el DTO de la orden seleccionada y llama a la funcion
	 * que se encarga de setear los valores en las celdas respectivas.
	 */
	private void aceptarCambioProduccionLavado() {
		GWT.log("entro a aceptarCambioProduccionLavado");

		final String codigoOrdenComboOrigen = (String) comboOrdenOrigen.getValue();
		final String codigoOrdenComboDestino = (String) comboOrdenDestino.getValue();
		final String codigoSiloDestino = (String) comboSilosDestino.getValue();
		final String codigoSiloOrigen = (String) comboSilosOrigen.getValue();
		final String nombreSiloDestino = mapaSilos.get(codigoSiloDestino);

		boolean valido = validarCamposLavado(codigoOrdenComboOrigen, codigoOrdenComboDestino, codigoSiloOrigen, codigoSiloDestino);

		if (!valido) {
			return;
		}

		// Callback para cargar la orden del combo destino (orden de la hora de
		// lavado)
		AsyncCallback<OrdenProduccionDTO> asyncCallbackDestino = new AsyncCallback<OrdenProduccionDTO>() {
			public void onFailure(Throwable throwable) {
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("Fallo  en metodo: cargarProductoPorCodigoOrden", throwable);
			}

			public void onSuccess(final OrdenProduccionDTO ordenDestino) {
				// Callback para cargar la orden del combo origen (orden del
				// cambio de produccion)
				AsyncCallback<OrdenProduccionDTO> asyncCallbackOrigen = new AsyncCallback<OrdenProduccionDTO>() {

					public void onFailure(Throwable throwable) {
						Window.alert(CONSTANTES.errorInesperado());
						GWT.log("Fallo  en metodo: cargarProductoPorCodigoOrden", throwable);
					}

					public void onSuccess(final OrdenProduccionDTO ordenOrigen) {
						String codProductoStr = ordenOrigen.getProduccion().getProducto().getPkCodigoProducto().toString();
						servicioComunicacion.cargarPlantillaMasReciente(codigoPlantillaReporte, new Long(codProductoStr),
								new AsyncCallback<PlantillaProductoDTO>() {

									public void onFailure(Throwable arg0) {
										GWT.log("Fallo en metodo: cargarPlantillas");
										Window.alert(ConstantesGWT.SERVER_ERROR);
									}

									public void onSuccess(PlantillaProductoDTO plantillaProductoOriginalDTO) {
										if (plantillaProductoOriginalDTO == null) {
											Window.alert("No se encontro una plantilla que corresponda con la HR activa del producto");
											return;
										}

										asinarOrdenProduccionOriginal(ordenOrigen, plantillaProductoOriginalDTO);

										String codProductoDestStr = ordenDestino.getProduccion().getProducto()
												.getPkCodigoProducto().toString();
										servicioComunicacion.cargarPlantillaMasReciente(codigoPlantillaReporte, new Long(
												codProductoDestStr), new AsyncCallback<PlantillaProductoDTO>() {

											public void onFailure(Throwable arg0) {
												GWT.log("Fallo en metodo: cargarPlantillas");
												Window.alert(ConstantesGWT.SERVER_ERROR);
											}

											public void onSuccess(PlantillaProductoDTO plantillaProductoDestinoDTO) {
												if (plantillaProductoDestinoDTO == null) {
													Window.alert("No se encontro una plantilla que corresponda con la HR activa del producto");
													return;
												}

												asinarOrdenProduccionHoraLavado(ordenDestino, plantillaProductoDestinoDTO);
												asignarSiloOrigenYDestinoLavado(codigoSiloDestino, codigoSiloOrigen,
														nombreSiloDestino);

												gridVarProd.refreshFields();

												actualiazarSubtotales();
											}
										});
									}
								});
					}

				};
				servicioComunicacion.obtenerOrdenProduccionDTO(new Long(codigoOrdenComboOrigen), asyncCallbackOrigen);
			}

		};
		servicioComunicacion.obtenerOrdenProduccionDTO(new Long(codigoOrdenComboDestino), asyncCallbackDestino);
		this.hide();
	}

	/**
	 * @param codigoSiloDestino
	 * @param codigoSiloOrigen
	 * @param nombreSiloDestino
	 */
	private void asignarSiloOrigenYDestinoLavado(final String codigoSiloDestino, final String codigoSiloOrigen,
			final String nombreSiloDestino) {
		ListGridRecord recordHoraLavado = gridVarProd.getSelectedRecord();

		recordHoraLavado.setAttribute(GridVariablesProduccion.COLUMNA_SILO, nombreSiloDestino);
		recordHoraLavado.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_SILO, codigoSiloDestino);
		recordHoraLavado.setAttribute(GridVariablesProduccion.COLUMNA_OBSERVACION, CONSTANTES.obsLavadoSilo());
		recordHoraLavado.setAttribute(GridVariablesProduccion.COLUMNA_CAMBIO_PRODUCCION_LAVADO, true);
		GWT.log("asigno codigo silo con exito");

		ListGridRecord recordAuxiliar = new ListGridRecord();

		String horaStr = gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_HORA);
		String turno = gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);

		String nombreSiloOrigen = mapaSilos.get(codigoSiloOrigen);

		recordAuxiliar.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA,
				gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA));
		recordAuxiliar.setAttribute(GridVariablesProduccion.COLUMNA_HORA, horaStr);
		recordAuxiliar.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO, turno);
		recordAuxiliar.setAttribute(GridVariablesProduccion.COLUMNA_SILO, nombreSiloOrigen);
		recordAuxiliar.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_SILO, codigoSiloOrigen);

		NotificacionesUtil.cambiarSiloEnHorasSiguientes(gridVarProd, recordAuxiliar);
	}

	/**
	 * Valida los campos que debe seleccionar el usuario para el cambio de
	 * produccion con lavado
	 * 
	 * @param codigoOrdenComboOrigen
	 * @param codigoOrdenComboDestino
	 * @param codigoSiloOrigen
	 * @param codigoSiloDestino
	 * @return
	 */
	private boolean validarCamposLavado(String codigoOrdenComboOrigen, String codigoOrdenComboDestino, String codigoSiloOrigen,
			String codigoSiloDestino) {
		if (codigoOrdenComboOrigen == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnaOrdenDeProduccionOrigen());
			return false;
		}

		if (codigoOrdenComboDestino == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnaOrdenDeProduccionDestino());
			return false;
		}

		GWT.log("codigoOrden: " + codigoOrdenComboOrigen);

		String codigoOrdenGrid = gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN);
		GWT.log("codigoOrdenCombo: " + codigoOrdenGrid);

		if (codigoOrdenComboOrigen == codigoOrdenGrid) {
			Window.alert(CONSTANTES.debeSeleccionarUnaOrdenDistinta());
			return false;
		}

		if (codigoSiloDestino == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnValorDeSiloDestino());
			return false;
		}

		if (codigoSiloOrigen == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnValorDeSiloOrigen());
			return false;
		}

		return true;
	}

	private void asinarOrdenProduccionOriginal(OrdenProduccionDTO ordenDTO, PlantillaProductoDTO plantillaProductoOriginalDTO) {
		LineaNegocioDTO lineaNegocioDTO = ordenDTO.getProduccion().getProceso().getLineaNegocio();

		ListGridRecord record = new ListGridRecord();

		String horaStr = gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_HORA);
		String turno = gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);

		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA,
				gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA));
		record.setAttribute(GridVariablesProduccion.COLUMNA_HORA, horaStr);
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO, turno);

		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN_ANT_CAMBIO_PRODUCCION,
				record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN));

		record.setAttribute(GridVariablesProduccion.COLUMNA_ORDEN, ordenDTO.getProduccion().getProducto().getNombreProducto()
				+ " " + ordenDTO.getProduccion().getProceso().getSiglasProceso());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN, ordenDTO.getPkCodigoOrdenproduccion());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PRODUCTO, ordenDTO.getProduccion().getProducto()
				.getPkCodigoProducto().toString());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_LINEA_NEGOCIO, lineaNegocioDTO.getPkCodigoLineanegocio());
		record.setAttribute(GridVariablesProduccion.COLUMNA_NOMBRE_LINEA_NEGOCIO, lineaNegocioDTO.getNombreLineanegocio());

		record.setAttribute(GridVariablesProduccion.COLUMNA_PLANTILLA, plantillaProductoOriginalDTO.getNombrePlantillaProducto());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PLANTILLA,
				plantillaProductoOriginalDTO.getPkCodigoPlantillaProducto());

		NotificacionesUtil.cambiarOrdenProduccionEnHorasSiguientes(gridVarProd, record);
		NotificacionesUtil.cambiarPlantillaEnHorasSiguientes(gridVarProd, record);
	}

	/**
	 * @param plantillaProductoDestinoDTO
	 */
	private void asinarOrdenProduccionHoraLavado(OrdenProduccionDTO ordenOrigen, PlantillaProductoDTO plantillaProductoDestinoDTO) {
		LineaNegocioDTO lineaNegocioDTO = ordenOrigen.getProduccion().getProceso().getLineaNegocio();

		ListGridRecord record = gridVarProd.getSelectedRecord();

		GWT.log("Obtuvo registro dle grid");

		record.setAttribute(GridVariablesProduccion.COLUMNA_ORDEN, ordenOrigen.getProduccion().getProducto().getNombreProducto()
				+ " " + ordenOrigen.getProduccion().getProceso().getSiglasProceso());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN, ordenOrigen.getPkCodigoOrdenproduccion());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PRODUCTO, ordenOrigen.getProduccion().getProducto()
				.getPkCodigoProducto().toString());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_LINEA_NEGOCIO, lineaNegocioDTO.getPkCodigoLineanegocio());
		record.setAttribute(GridVariablesProduccion.COLUMNA_NOMBRE_LINEA_NEGOCIO, lineaNegocioDTO.getNombreLineanegocio());

		record.setAttribute(GridVariablesProduccion.COLUMNA_PLANTILLA, plantillaProductoDestinoDTO.getNombrePlantillaProducto());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PLANTILLA,
				plantillaProductoDestinoDTO.getPkCodigoPlantillaProducto());

		GWT.log("Asigno nueva orden y lines de negocio");
	}

	/**
	 * Funcion que obtiene el DTO de la orden seleccionada y llama a la funcion
	 * que se encarga de setear los valores en las celdas respectivas.
	 */
	private void aceptarCambioProduccionNormal() {
		String minuto = (String) comboMinutos.getValue();
		if (minuto == null) {
			Window.alert(CONSTANTES.errorMinutoCambioProduccion());
			return;
		}

		String codigoOrden = (String) comboOrdenOrigen.getValue();

		if (codigoOrden == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnaOrdenDeProduccion());
			return;
		}

		if (codigoOrden == gridVarProd.getSelectedRecord().getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN)) {
			Window.alert(CONSTANTES.debeSeleccionarUnaOrdenDistinta());
			return;
		}

		asignarOrdenYSiloCambioProdNormal(codigoOrden);

		this.hide();
	}

	private void asignarOrdenYSiloCambioProdNormal(String codigoOrden) {
		GWT.log("obtuvo codigo orden del combo");

		AsyncCallback<OrdenProduccionDTO> asyncCallback = new AsyncCallback<OrdenProduccionDTO>() {

			public void onFailure(Throwable throwable) {
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("Fallo  en metodo: cargarProductoPorCodigoOrden", throwable);
			}

			public void onSuccess(final OrdenProduccionDTO ordenDTO) {
				GWT.log("obtuvo la orden de prod de la bd con exito");
				String codProductoStr = ordenDTO.getProduccion().getProducto().getPkCodigoProducto().toString();
				servicioComunicacion.cargarPlantillaMasReciente(codigoPlantillaReporte, new Long(codProductoStr),
						new AsyncCallback<PlantillaProductoDTO>() {

							public void onFailure(Throwable arg0) {
								GWT.log("Fallo en metodo: cargarPlantillas");
								Window.alert(ConstantesGWT.SERVER_ERROR);
							}

							public void onSuccess(PlantillaProductoDTO plantillaProductoDTO) {
								if (plantillaProductoDTO == null) {
									Window.alert("No se encontro una plantilla que corresponda con la HR activa del producto");
									return;
								}

								ListGridRecord record = NotificacionesUtil.asinarOrdenDesdeOrdenDTO(ordenDTO, gridVarProd);

								String minuto = (String) comboMinutos.getValue();

								record.setAttribute(GridVariablesProduccion.COLUMNA_CAMBIO_PRODUCCION_NORMAL, true);
								record.setAttribute(GridVariablesProduccion.COLUMNA_CAMBIO_PRODUCCION_HORA, hora + ":" + minuto);
								record.setAttribute(GridVariablesProduccion.COLUMNA_OBSERVACION, CONSTANTES.obsCambioProduccion());

								Long codigoPlantillaProducto = plantillaProductoDTO.getPkCodigoPlantillaProducto();
								String nombrePlantillaProducto = plantillaProductoDTO.getNombrePlantillaProducto();

								record.setAttribute(GridVariablesProduccion.COLUMNA_PLANTILLA, nombrePlantillaProducto);
								record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PLANTILLA, codigoPlantillaProducto);

								NotificacionesUtil.cambiarOrdenProduccionEnHorasSiguientes(gridVarProd, record);
								NotificacionesUtil.cambiarPlantillaEnHorasSiguientes(gridVarProd, record);

								gridVarProd.refreshFields();

								actualiazarSubtotales();

								GWT.log("Asigno nueva orden y lines de negocio");
							}
						});
			}

		};
		servicioComunicacion.obtenerOrdenProduccionDTO(new Long(codigoOrden), asyncCallback);

	}

	/**
	 * Metodo que inicializa el combo para caragar los valores de Silo
	 * 
	 * @return SelectItem
	 */
	private static void cargarComboSilos() {
		comboSilosOrigen = new SelectItem();
		comboSilosOrigen.setName("siloOrigen");
		comboSilosOrigen.setTitle(CONSTANTES.silo());
		comboSilosOrigen.setAllowEmptyValue(false);
		comboSilosOrigen.setColSpan(2);

		comboSilosDestino = new SelectItem();
		comboSilosDestino.setName("siloDestino");
		comboSilosDestino.setTitle(CONSTANTES.siloLavado());
		comboSilosDestino.setAllowEmptyValue(false);
		comboSilosDestino.setColSpan(2);

		servicioComunicacion.cargarMediosAlmacenamiento(new AsyncCallback<List<MedioAlmacenamientoDTO>>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<MedioAlmacenamientoDTO> silos) {
				mapaSilos = UtilConverter.obtenerMapaMedioAlmacenamientoDTO(silos);
				comboSilosOrigen.setValueMap(mapaSilos);
				comboSilosDestino.setValueMap(mapaSilos);
			}
		});
	}

	/**
	 * Metodo que inicializa el combo para caragar los valores de Silo
	 * 
	 * @return SelectItem
	 */
	private static SelectItem cargarComboMinutos() {
		comboMinutos = new SelectItem();
		comboMinutos.setColSpan(2);

		comboMinutos.setTitle(CONSTANTES.cpHoraExacta() + " " + hora);
		comboMinutos.setAllowEmptyValue(false);

		LinkedHashMap<String, String> minutos = new LinkedHashMap<String, String>();
		for (int i = 1; i < 60; i++) {
			minutos.put(i + "", i + "");
		}
		comboMinutos.setValueMap(minutos);

		return comboMinutos;
	}

}
