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
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Combos;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.UtilConverter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
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
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VStack;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DialogoCargarOrden.java
 * Modificado: Feb 5, 2011 9:00:38 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class DialogoCargarOrdenOSilo extends com.smartgwt.client.widgets.Window implements ClienteServicioGwt {

	private static SelectItem comboOrden;
	private static SelectItem comboSilos;
	private static SelectItem comboPlantilla;
	private RadioGroupItem tipoCambios;

	private static Date fecha;
	private static Long codigoPlantillaReporte;

	private static LinkedHashMap<String, String> mapaSilos = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String, String> mapaOrden = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String, String> mapaPlantilla = new LinkedHashMap<String, String>();

	private LinkedHashMap<String, String> plantillasAuto = new LinkedHashMap<String, String>();

	private static NotificacionServiceAsync servicioNotificacion;
	private static GridVariablesProduccion gridVarProd;
	private static GridVariablesProduccion gridVarProdSubtotales;

	private static List<ColumnareporteDTO> columnas;
	private static TipoDialogo tipoDialogo;

	private static final String NOMBRE_CBO_SILO = "silo";
	private static final String NOMBRE_CBO_PLANTILLA = "plantilla";

	/**
	 * interfaz usada para obtener las constantes (i18n)
	 */
	private static ConstantesModuloNotificaciones CONSTANTES = GWT.create(ConstantesModuloNotificaciones.class);
	private static Long codigoProceso;

	public enum TipoDialogo {
		DLG_SILO, DLG_ORDENES_PRODUC, DLG_PLANTILLA;
	}

	public DialogoCargarOrdenOSilo(NotificacionServiceAsync servicioNotificacion, Date fecha, Long codigoProceso,
			Long codigoPlantillaReporte, LinkedHashMap<String, String> mapaOrden, GridVariablesProduccion gridVarProd,
			GridVariablesProduccion gridVarProdsubtotales, List<ColumnareporteDTO> columnas, TipoDialogo tipoDialogo) {
		super();

		initParams(servicioNotificacion, fecha, codigoProceso, codigoPlantillaReporte, mapaOrden, gridVarProd,
				gridVarProdsubtotales, columnas, tipoDialogo);
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
	 * @param codigoPlantillaReporte2
	 * @param codigoProc codigo de ordenes de produccion
	 * @param mapaOrdenes LinkedHashMap con las ordenes de produccion
	 * @param gridVarProduc listgrid que contiene las variables de prodccion
	 * @param columnas
	 * @param gridVarProdsubtotales
	 * @param tipo enum que indica si el dialogo es para seleccionar ordenes o
	 *            silo
	 */
	private static void initParams(NotificacionServiceAsync servicio, Date fechaReg, Long pcodigoProceso,
			Long pCodigoPlantillaReporte, LinkedHashMap<String, String> mapaOrdenes, GridVariablesProduccion gridVarProduc,
			GridVariablesProduccion gridVarProdsubtotales, List<ColumnareporteDTO> columnas, TipoDialogo tipo) {
		DialogoCargarOrdenOSilo.servicioNotificacion = servicio;
		DialogoCargarOrdenOSilo.fecha = fechaReg;
		DialogoCargarOrdenOSilo.codigoProceso = pcodigoProceso;
		DialogoCargarOrdenOSilo.codigoPlantillaReporte = pCodigoPlantillaReporte;
		DialogoCargarOrdenOSilo.mapaOrden = mapaOrdenes;
		DialogoCargarOrdenOSilo.gridVarProd = gridVarProduc;
		DialogoCargarOrdenOSilo.gridVarProdSubtotales = gridVarProdsubtotales;
		DialogoCargarOrdenOSilo.columnas = columnas;
		DialogoCargarOrdenOSilo.tipoDialogo = tipo;
	}

	/**
	 * Construye la gui del popup
	 */
	private void initiGUI() {
		setWidth(400);
		setHeight(290);

		String titulo = null;

		switch (tipoDialogo) {
		case DLG_ORDENES_PRODUC:
			titulo = CONSTANTES.agregarOrden();
			break;
		case DLG_SILO:
			titulo = CONSTANTES.agregarSilo();
			break;
		case DLG_PLANTILLA:
			titulo = CONSTANTES.agregarPlantilla();
			break;
		default:
			break;
		}

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

		DynamicForm form = new DynamicForm();
		form.setWidth(350);
		form.setNumCols(2);
		form.setColWidths(350);
		form.setWrapItemTitles(true);
		form.setLayoutAlign(VerticalAlignment.TOP);
		form.setLayoutAlign(Alignment.LEFT);

		tipoCambios = new RadioGroupItem();
		tipoCambios.setTitle(CONSTANTES.cpTipoModificacion());
		tipoCambios.setVertical(false);
		tipoCambios.setWidth(200);
		tipoCambios.setColSpan(2);
		tipoCambios.setAlign(Alignment.CENTER);
		tipoCambios.setValueMap(CONSTANTES.aplicarATodosLosRegistros(), CONSTANTES.aplicarDeAquiEnAdelante(),
				CONSTANTES.aplicarSoloARegistroActual());

		switch (tipoDialogo) {
		case DLG_ORDENES_PRODUC:
			comboOrden = cargarComboOrden();
			form.setFields(comboOrden, tipoCambios);
			break;
		case DLG_SILO:
			comboSilos = cargarComboSilos();
			form.setFields(comboSilos, tipoCambios);
			break;
		case DLG_PLANTILLA:
			ListGridRecord record = gridVarProd.getSelectedRecord();
			String codigoProductoStr = record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PRODUCTO);
			if (codigoProductoStr == null || codigoProductoStr == "") {
				Window.alert(CONSTANTES.debeSeleccionarUnaOrdenDeProduccion());
				hide();
				return;
			}
			comboPlantilla = cargarPlantilla(codigoProductoStr);
			form.setFields(comboPlantilla, tipoCambios);
			break;
		default:
			break;
		}

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
	private static SelectItem cargarComboOrden() {

		final SelectItem cboOrden = Combos.cargarComboOrden();
		cboOrden.setTitle(CONSTANTES.ordenProduccionConPlantilla());
		cboOrden.setAllowEmptyValue(false);
		cboOrden.setColSpan(2);

		AsyncCallback<List<OrdenProduccionDTO>> asyncCallback = new AsyncCallback<List<OrdenProduccionDTO>>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<OrdenProduccionDTO> ordenes) {
				setOrdenesInCombo(ordenes);
			}

		};

		servicioNotificacion.getOrdenesByMesPlantillaLiberadas(fecha, codigoPlantillaReporte, asyncCallback);

		return cboOrden;
	}

	private static void setOrdenesInCombo(List<OrdenProduccionDTO> ordenes) {
		if (ordenes == null || ordenes.size() == 0) {
			Window.alert(CONSTANTES.noExistenOrdenesLiberadasConProductoConPlantilla());
			return;
		}

		mapaOrden = UtilConverter.obtenerMapaOrdenDTO(ordenes);
		comboOrden.setValueMap(mapaOrden);
	}

	private void botonAceptarClicked() {
		if (tipoCambios.getValue() == null) {
			Window.alert(CONSTANTES.debeSelecionarElTipoDeModificacion());
			return;
		}

		switch (tipoDialogo) {
		case DLG_ORDENES_PRODUC:
			aceptarEnDialogoOrdenes();
			break;
		case DLG_SILO:
			aceptarEnDialogoSilo();
			break;
		case DLG_PLANTILLA:
			aceptarEnDialogoPlantilla();
			break;
		default:
			break;
		}

	}

	/**
	 * Funcion que obtiene el DTO de la orden seleccionada y llama a la funcion
	 * que se encarga de setear los valores en las celdas respectivas.
	 */
	private void aceptarEnDialogoOrdenes() {

		String codigoOrden = (String) comboOrden.getValue();

		if (codigoOrden == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnaOrdenDeProduccion());
			return;
		}

		AsyncCallback<OrdenProduccionDTO> asyncCallback = new AsyncCallback<OrdenProduccionDTO>() {

			public void onFailure(Throwable throwable) {
				Window.alert(CONSTANTES.errorInesperado());
				GWT.log("Fallo  en metodo: cargarProductoPorCodigoOrden", throwable);
			}

			public void onSuccess(final OrdenProduccionDTO ordenDTO) {
				servicioComunicacion.obtenerPropiedadPorClave("codigo.productos.clinker", new AsyncCallback<String>() {

					public void onFailure(Throwable throwable) {
						GWT.log("Fallo  en metodo: cargarProductoPorCodigoOrden", throwable);
						return;
					}

					public void onSuccess(String codigosClinker) {
						String productoStr = ordenDTO.getProduccion().getProducto().getNombreProducto();
						int indexOf = productoStr.indexOf(codigosClinker);
						boolean esClinker = indexOf >= 0;

						asignarOrdenesEnCeldas(ordenDTO, esClinker);
					}
				});
			}

		};
		servicioComunicacion.obtenerOrdenProduccionDTO(Long.valueOf(codigoOrden), asyncCallback);
	}

	/**
	 * Funcion que asigna el valor de la orden de produccion en la celda (o en
	 * todos los registros si el check esta seleccionado)
	 * 
	 * @param esClinker
	 * @param codigosClinker
	 */
	private void asignarOrdenesEnCeldas(final OrdenProduccionDTO ordenDTO, final boolean esClinker) {
		final String nombreOrden = mapaOrden.get(ordenDTO.getPkCodigoOrdenproduccion().toString());
		final LineaNegocioDTO lineaNegocioDTO = ordenDTO.getProduccion().getProceso().getLineaNegocio();

		final Long codigoOrdenproduccion = ordenDTO.getPkCodigoOrdenproduccion();
		final String codProductoStr = ordenDTO.getProduccion().getProducto().getPkCodigoProducto().toString();

		servicioComunicacion.cargarPlantillaMasReciente(codigoPlantillaReporte, Long.valueOf(codProductoStr),
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
						if (tipoCambios.getValue().equals(CONSTANTES.aplicarATodosLosRegistros())) {
							int row = 0;
							for (ListGridRecord record : gridVarProd.getRecords()) {
								asignarOrdenProduccionARegistro(ordenDTO, nombreOrden, lineaNegocioDTO, record, row, esClinker,
										plantillaProductoDTO, codigoOrdenproduccion.toString(), codProductoStr);
								row++;
							}
						} else if (tipoCambios.getValue().equals(CONSTANTES.aplicarSoloARegistroActual())) {
							ListGridRecord record = gridVarProd.getSelectedRecord();

							asignarOrdenProduccionARegistro(ordenDTO, nombreOrden, lineaNegocioDTO, record,
									gridVarProd.getEditRow(), esClinker, plantillaProductoDTO, codigoOrdenproduccion.toString(),
									codProductoStr);
						} else {
							ListGridRecord record = gridVarProd.getSelectedRecord();
							asignarOrdenProduccionARegistro(ordenDTO, nombreOrden, lineaNegocioDTO, record,
									gridVarProd.getEditRow(), esClinker, plantillaProductoDTO, codigoOrdenproduccion.toString(),
									codProductoStr);
							NotificacionesUtil.cambiarOrdenProduccionEnHorasSiguientes(gridVarProd, record);
							NotificacionesUtil.cambiarPlantillaEnHorasSiguientes(gridVarProd, record);
						}

						AdministrarNotificacionProduccionPlanta.revisarLineaNegocioDeOrden();

						gridVarProd.refreshFields();
						DialogoCargarOrdenOSilo.this.hide();

						actualiazarSubtotales();
					}
				});
	}

	/**
	 * Asiga los datos de la orden de produccion al registro pasado como
	 * parametro
	 * 
	 * @param ordenDTO
	 * @param nombreOrden
	 * @param lineaNegocioDTO
	 * @param record
	 * @param row
	 * @param esClinker
	 * @param plantillaProductoDTO
	 * @param codigoOrdenproduccion
	 * @param codProductoStr
	 */
	private void asignarOrdenProduccionARegistro(OrdenProduccionDTO ordenDTO, String nombreOrden,
			LineaNegocioDTO lineaNegocioDTO, final ListGridRecord record, final int row, boolean esClinker,
			PlantillaProductoDTO plantillaProductoDTO, String codigoOrdenproduccion, String codProductoStr) {

		record.setAttribute(GridVariablesProduccion.COLUMNA_ORDEN, nombreOrden);
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN, codigoOrdenproduccion);
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PRODUCTO, codProductoStr);
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_LINEA_NEGOCIO, lineaNegocioDTO.getPkCodigoLineanegocio());
		record.setAttribute(GridVariablesProduccion.COLUMNA_NOMBRE_LINEA_NEGOCIO, lineaNegocioDTO.getNombreLineanegocio());

		if (!esClinker) {
			Long codigoPlantillaProducto = plantillaProductoDTO.getPkCodigoPlantillaProducto();
			String nombrePlantillaProducto = plantillaProductoDTO.getNombrePlantillaProducto();
			String valor = codigoPlantillaProducto + "-" + nombrePlantillaProducto;
			plantillasAuto.put(codigoOrdenproduccion.toString(), valor);

			record.setAttribute(GridVariablesProduccion.COLUMNA_PLANTILLA, nombrePlantillaProducto);
			record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PLANTILLA, codigoPlantillaProducto);
		}
	}

	/**
	 * Funcion que asigna el valor de silo en la celda (o en todos los registros
	 * si el check esta seleccionado)
	 */
	private void aceptarEnDialogoSilo() {
		String codigoSilo = (String) comboSilos.getValue();

		if (codigoSilo == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnValorDeSilo());
			return;
		}

		String nombreSilo = mapaSilos.get(codigoSilo);
		String codigoOrdenProduccion = "";
		if (tipoCambios.getValue().equals(CONSTANTES.aplicarATodosLosRegistros())) {
			GWT.log("entro  a modicar el silo en todos los registros");
			RecordList recordList = gridVarProd.getDataAsRecordList();
			ListGridRecord recodClick = gridVarProd.getSelectedRecord();
			codigoOrdenProduccion = recodClick.getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN);

			for (Record record : recordList.toArray()) {
				if (codigoOrdenProduccion.equals(record.getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN))) {
					asignarSiloARegistro(codigoSilo, nombreSilo, record);
				}
			}
		} else if (tipoCambios.getValue().equals(CONSTANTES.aplicarSoloARegistroActual())) {
			GWT.log("entro  a modicar el silo en todos los registros del actual en adelante");
			ListGridRecord record = gridVarProd.getSelectedRecord();
			asignarSiloARegistro(codigoSilo, nombreSilo, record);
		} else {
			ListGridRecord record = gridVarProd.getSelectedRecord();
			asignarSiloARegistro(codigoSilo, nombreSilo, record);
			NotificacionesUtil.cambiarSiloEnHorasSiguientes(gridVarProd, record);
		}

		gridVarProd.refreshFields();
		this.hide();
	}

	/**
	 * Funcion que asigna el valor de silo en la celda (o en todos los registros
	 * si el check esta seleccionado)
	 */
	private void aceptarEnDialogoPlantilla() {
		String codigoPalntilla = (String) comboPlantilla.getValue();

		if (codigoPalntilla == null) {
			Window.alert(CONSTANTES.debeSeleccionarUnaPlantilla());
			return;
		}

		String nombrePlantilla = mapaPlantilla.get(codigoPalntilla);
		String codigoOrdenProduccion = "";
		if (tipoCambios.getValue().equals(CONSTANTES.aplicarATodosLosRegistros())) {
			GWT.log("entro  a modicar el silo en todos los registros");
			RecordList recordList = gridVarProd.getDataAsRecordList();
			ListGridRecord recodClick = gridVarProd.getSelectedRecord();
			codigoOrdenProduccion = recodClick.getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN);

			for (Record record : recordList.toArray()) {
				if (codigoOrdenProduccion.equals(record.getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN))) {
					asignarPantillaARegistro(codigoPalntilla, nombrePlantilla, record);
				}

			}
		} else if (tipoCambios.getValue().equals(CONSTANTES.aplicarSoloARegistroActual())) {
			GWT.log("entro  a modicar el silo en todos los registros del actual en adelante");
			ListGridRecord record = gridVarProd.getSelectedRecord();
			asignarPantillaARegistro(codigoPalntilla, nombrePlantilla, record);
		} else {
			ListGridRecord record = gridVarProd.getSelectedRecord();
			asignarPantillaARegistro(codigoPalntilla, nombrePlantilla, record);
			NotificacionesUtil.cambiarPlantillaEnHorasSiguientes(gridVarProd, record);
		}

		gridVarProd.refreshFields();
		this.hide();
	}

	/**
	 * Asigna los valores del silo al registro pasado como parametro
	 * 
	 * @param codigoSilo
	 * @param nombreSilo
	 * @param record
	 */
	private void asignarSiloARegistro(String codigoSilo, String nombreSilo, Record record) {
		record.setAttribute(GridVariablesProduccion.COLUMNA_SILO, nombreSilo);
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_SILO, codigoSilo);
	}

	/**
	 * Asigna los valores del silo al registro pasado como parametro
	 * 
	 * @param codigoSilo
	 * @param nombreSilo
	 * @param record
	 */
	private void asignarPantillaARegistro(String codigoPlantilla, String nombrePlantilla, Record record) {
		record.setAttribute(GridVariablesProduccion.COLUMNA_PLANTILLA, nombrePlantilla);
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PLANTILLA, codigoPlantilla);
	}

	/**
	 * Metodo que inicializa el combo para caragar los valores de Silo
	 * 
	 * @return SelectItem
	 */
	private static SelectItem cargarComboSilos() {
		comboSilos = new SelectItem();
		comboSilos.setName(NOMBRE_CBO_SILO);
		comboSilos.setTitle(CONSTANTES.silo());
		comboSilos.setAllowEmptyValue(false);
		comboSilos.setColSpan(2);

		servicioComunicacion.cargarMediosAlmacenamientoSegunProceso(codigoProceso,
				new AsyncCallback<List<MedioAlmacenamientoDTO>>() {

					public void onFailure(Throwable arg0) {
						Window.alert(ConstantesGWT.SERVER_ERROR);
					}

					public void onSuccess(List<MedioAlmacenamientoDTO> silos) {
						mapaSilos = UtilConverter.obtenerMapaMedioAlmacenamientoDTO(silos);
						comboSilos.setValueMap(mapaSilos);
					}
				});
		return comboSilos;
	}

	private static SelectItem cargarPlantilla(String codigoProductoStr) {
		comboPlantilla = new SelectItem();
		comboPlantilla.setName(NOMBRE_CBO_PLANTILLA);
		comboPlantilla.setTitle(CONSTANTES.plantilla());
		comboPlantilla.setAllowEmptyValue(false);
		comboPlantilla.setColSpan(2);

		GWT.log("Cargando plantillas");
		GWT.log("codigoProductoStr: " + codigoProductoStr);
		GWT.log("codigoPlantillaReporte: " + codigoPlantillaReporte);

		servicioComunicacion.cargarPlantillas(codigoPlantillaReporte, new Long(codigoProductoStr),
				new AsyncCallback<List<PlantillaProductoDTO>>() {

					public void onFailure(Throwable arg0) {
						GWT.log("Fallo en metodo: cargarPlantillas");
						Window.alert(ConstantesGWT.SERVER_ERROR);
					}

					public void onSuccess(List<PlantillaProductoDTO> plantillas) {
						if (plantillas == null || plantillas.size() == 0) {
							Window.alert("No se encontron plantillas que correspondan con la HR activa del producto");
							return;
						}

						GWT.log("Plantillas cargadas exitosamente, cantidad: " + plantillas.size());
						mapaPlantilla = UtilConverter.obtenerMapaPlantillaProductoDTO(plantillas);
						comboPlantilla.setValueMap(mapaPlantilla);
					}
				});
		return comboPlantilla;
	}
}
