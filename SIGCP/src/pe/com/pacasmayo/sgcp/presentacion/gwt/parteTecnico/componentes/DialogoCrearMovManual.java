package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.LinkedHashMap;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ParteTecnicoService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ParteTecnicoServiceAsync;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.UtilConverter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
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
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VStack;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DialogoCrearMovManual.java
 * Modificado: Sep 12, 2011 5:06:59 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class DialogoCrearMovManual extends com.smartgwt.client.widgets.Window implements ClienteServicioGwt {

	private final GridMovimientosDeAjuste gridMovimientosDeAjuste;
	private DynamicForm form;
	private SelectItem comboPuesto;
	private static RadioGroupItem tipoComponente;
	private static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);
	private final LinkedHashMap<String, String> componentes;
	private static SelectItem comboComponenteOProducto;
	private final LinkedHashMap<String, String> puestosTrabajo;
	private TextItem txtCantNovimiento;
	private TextAreaItem txtCantObservacion;
	private final GridMovimientoComponentes gridMovimientoComponentes;
	private final GridConsumoComponentesPuestoTrabajo gridConsumoPuestoTrabajo;
	private final GridConsumoComponentesPuestoTrabajo gridConsumoComponentes;
	private final boolean esClinker;

	private static ParteTecnicoServiceAsync servicioRegistrarAjusteProduccionMes = GWT.create(ParteTecnicoService.class);

	public DialogoCrearMovManual(GridMovimientosDeAjuste gridMovimientosDeAjuste, GridMovimientoComponentes gridMovComponentes,
			LinkedHashMap<String, String> componentes, LinkedHashMap<String, String> puestosTrabajo,
			GridConsumoComponentesPuestoTrabajo gridConsumoPuestoTrabajo,
			GridConsumoComponentesPuestoTrabajo gridConsumoComponentes, boolean esClinker) {
		this.gridMovimientosDeAjuste = gridMovimientosDeAjuste;
		this.gridMovimientoComponentes = gridMovComponentes;
		this.componentes = componentes;
		this.puestosTrabajo = puestosTrabajo;
		this.gridConsumoPuestoTrabajo = gridConsumoPuestoTrabajo;
		this.gridConsumoComponentes = gridConsumoComponentes;
		this.esClinker = esClinker;
		initiGUI();
		initCombos();
	}

	private void initiGUI() {
		setWidth(600);
		setHeight(330);

		String titulo = CONSTANTES.tituloDialogoCrearMovManual();

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
		form.setWidth(550);
		form.setColWidths(130);
		form.setNumCols(4);
		form.setWrapItemTitles(true);
		form.setLayoutAlign(VerticalAlignment.TOP);
		form.setLayoutAlign(Alignment.CENTER);

		comboPuesto = new SelectItem();
		comboPuesto.setName("comboPuesto");
		comboPuesto.setAllowEmptyValue(false);
		comboPuesto.setColSpan(1);
		comboPuesto.setTitle(CONSTANTES.tituloColPuestoTrabajo() + ConstantesGWT.CAMPO_OBLIGATORIO);

		comboComponenteOProducto = new SelectItem();
		comboComponenteOProducto.setName("comboComponente");
		comboComponenteOProducto.setAllowEmptyValue(false);
		comboComponenteOProducto.setColSpan(1);
		comboComponenteOProducto.setTitle(CONSTANTES.tituloColComponentes() + ConstantesGWT.CAMPO_OBLIGATORIO);

		tipoComponente = new RadioGroupItem();
		tipoComponente.setTitle(CONSTANTES.tipoComponente());
		tipoComponente.setVertical(false);
		tipoComponente.setWidth(200);
		tipoComponente.setColSpan(1);
		tipoComponente.setValueMap(CONSTANTES.tipoComponenteHojaRuta(), CONSTANTES.tipoComponenteDemas());
		tipoComponente.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {
				if (event.getItem().getValue().equals(CONSTANTES.tipoComponenteHojaRuta())) {
					GWT.log("entro al if");
					comboComponenteOProducto.clearValue();
					comboComponenteOProducto.setValueMap(componentes);
				} else if (event.getItem().getValue().equals(CONSTANTES.tipoComponenteDemas())) {
					GWT.log("entro al else if");
					comboComponenteOProducto.clearValue();
					try {
						AsyncCallback<List<ProductoDTO>> asyncCallback = new AsyncCallback<List<ProductoDTO>>() {

							public void onFailure(Throwable arg0) {
								Window.alert(ConstantesGWT.SERVER_ERROR);
							}

							public void onSuccess(List<ProductoDTO> productos) {
								setProductosInComboComponente(productos);
							}

						};

						servicioRegistrarAjusteProduccionMes.getProductos(asyncCallback);
					} catch (Exception e) {
						// TODO Manejo de Log
						e.printStackTrace();
					}
				}
				markForRedraw();
			}
		});

		txtCantNovimiento = new TextItem();
		txtCantNovimiento.setName("txtCantNovimiento");
		txtCantNovimiento.setTitle(CONSTANTES.campoDialogoCantMov() + ConstantesGWT.CAMPO_OBLIGATORIO);

		txtCantObservacion = new TextAreaItem();
		txtCantObservacion.setName("txtCantObservacion");
		txtCantObservacion.setTitle(CONSTANTES.tituloColObservacion() + ConstantesGWT.CAMPO_OBLIGATORIO);
		txtCantObservacion.setColSpan(3);
		txtCantObservacion.setHeight(50);

		form.setFields(comboPuesto, tipoComponente, comboComponenteOProducto, txtCantNovimiento, txtCantObservacion);
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

	private void initCombos() {
		comboPuesto.setValueMap(puestosTrabajo);
	}

	private void botonAceptarClicked() {
		boolean valido = validarCampos();

		if (!valido) {
			Window.alert(CONSTANTES.debeIntroducirValoresEnTodoLosCampos());
			return;
		}

		Double valorCanMov = null;

		try {
			valorCanMov = Double.parseDouble(txtCantNovimiento.getValue().toString());
		} catch (Exception e) {
			Window.alert(CONSTANTES.campoCantMovNumerico());
			return;
		}

		Long codigoPuesto = Long.parseLong((String) comboPuesto.getValue());
		String tipoComponenteMovAjuste = (String) tipoComponente.getValue();
		Long codigoComponenteOProducto = Long.parseLong((String) comboComponenteOProducto.getValue());

		String nombrePuesto = comboPuesto.getDisplayValue();
		String nombreComponente = comboComponenteOProducto.getDisplayValue();

		// codProducto

		ListGridRecord record = crearRegistro(valorCanMov, codigoPuesto, tipoComponenteMovAjuste, codigoComponenteOProducto,
				nombrePuesto, nombreComponente);

		gridMovimientosDeAjuste.agregarRegistroManual(record);

		// actualizarGridMovimientoComponentes(record);

		// actualizarGridConsumoPorPuestoTrab(valorCanMov, record);

	}

	/**
	 * @param valorCanMov
	 * @param codigoPuesto
	 * @param codigoComponenteOProducto
	 * @param nombrePuesto
	 * @param nombreComponente
	 * @return
	 */
	private ListGridRecord crearRegistro(Double valorCanMov, Long codigoPuesto, String tipoComponenteMovAjuste,
			Long codigoComponenteOProducto, String nombrePuesto, String nombreComponente) {
		ListGridRecord record = new ListGridRecord();
		record.setAttribute(GridMovimientosDeAjuste.CODIGO_PUESTO_TRABAJO, codigoPuesto);
		record.setAttribute(GridMovimientosDeAjuste.COLUMNA_PUESTO_TRABAJO, nombrePuesto);
		record.setAttribute(GridMovimientosDeAjuste.COLUMNA_TIPO_COMPONENTE, tipoComponenteMovAjuste);
		record.setAttribute(GridMovimientosDeAjuste.COLUMNA_COMPONENTES, nombreComponente);
		record.setAttribute(GridMovimientosDeAjuste.COLUMNA_OBSERVACION, txtCantObservacion.getValue());
		record.setAttribute(GridMovimientosDeAjuste.COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_MANUAL);
		record.setAttribute(GridMovimientosDeAjuste.COLUMNA_AJUSTE_CONSUMO, valorCanMov);
		record.setAttribute(GridMovimientosDeAjuste.COLUMNA_ELIMINAR, ConstantesGWT.NOMBRE_IMAGEN_ELIMINAR);

		// Si se agregó un producto el record tiene producto
		// Si se agregó un componente el record tiene componente Y producto
		if (ConstantesGWT.TIPO_COMPONENTE_PRODUCTOS.equals(record.getAttribute(GridMovimientosDeAjuste.COLUMNA_TIPO_COMPONENTE))) {
			record.setAttribute(GridMovimientosDeAjuste.COLUMNA_CODIGO_PRODUCTO_COMP, codigoComponenteOProducto);
		} else if (ConstantesGWT.TIPO_COMPONENTE_HOJA_RUTA.equals(record
				.getAttribute(GridMovimientosDeAjuste.COLUMNA_TIPO_COMPONENTE))) {
			record.setAttribute(GridMovimientosDeAjuste.CODIGO_COMPONENTE, codigoComponenteOProducto);
			Record recordConProducto = gridMovimientoComponentes.getRecordList().find(CONSTANTES.codigoComponente(),
					codigoComponenteOProducto);
			if (recordConProducto == null) {
				throw new IllegalArgumentException("inconsistencia. no se encontró el producto para el componente "
						+ codigoComponenteOProducto);
			}
			record.setAttribute(GridMovimientosDeAjuste.COLUMNA_CODIGO_PRODUCTO_COMP,
					recordConProducto.getAttribute(CONSTANTES.codigoProductoComponente()));
		}
		return record;
	}

	/**
	 * @param valorCanMov Double
	 * @param codigoComponenteMovAjuste Long
	 */
	private void actualizarGridMovimientoComponentes(ListGridRecord ajusteRecord) {
		ListGridRecord[] movProductosRecords = gridMovimientoComponentes.getRecords();
		for (ListGridRecord movRecord : movProductosRecords) {
			String datoGridAjuste = null;
			String datoGridMovimientos = null;

			if (ConstantesGWT.TIPO_COMPONENTE_PRODUCTOS.equals(ajusteRecord
					.getAttribute(GridMovimientosDeAjuste.COLUMNA_TIPO_COMPONENTE))) {
				datoGridAjuste = ajusteRecord.getAttribute(GridMovimientosDeAjuste.CODIGO_COMPONENTE);
				datoGridMovimientos = movRecord.getAttribute(CONSTANTES.codigoComponente());
			} else if (ConstantesGWT.TIPO_COMPONENTE_HOJA_RUTA.equals(ajusteRecord
					.getAttribute(GridMovimientosDeAjuste.COLUMNA_TIPO_COMPONENTE))) {
				datoGridAjuste = ajusteRecord.getAttribute(GridMovimientosDeAjuste.CODIGO_PUESTO_TRABAJO);
				datoGridMovimientos = movRecord.getAttribute(CONSTANTES.codigoProductoComponente());
			}

			// Sumar ajuste al consumo existente
			if (datoGridAjuste.equals(datoGridMovimientos)) {
				Double consumo = movRecord.getAttributeAsDouble(CONSTANTES.colConsumo());
				consumo += ajusteRecord.getAttributeAsDouble(GridMovimientosDeAjuste.COLUMNA_AJUSTE_CONSUMO);
				movRecord.setAttribute(CONSTANTES.colConsumo(), consumo);
				gridMovimientoComponentes.refreshFields();
				break;
			}

		}
	}

	/**
	 * Valida los campos requeridos en el dialogo para crear el movimiento
	 * 
	 * @return true si los campos son validos, false en caso contrario
	 */
	private boolean validarCampos() {
		boolean valido = true;

		valido &= comboPuesto.getValue() != null;
		valido &= tipoComponente.getValue() != null;
		valido &= comboComponenteOProducto.getValue() != null;
		valido &= txtCantNovimiento.getValue() != null;
		valido &= txtCantObservacion.getValue() != null;

		return valido;
	}

	private static void setProductosInComboComponente(List<ProductoDTO> productos) {
		if (productos == null || productos.size() == 0) {
			Window.alert(CONSTANTES.noExistenProductos());
			return;
		}
		LinkedHashMap<String, String> mapaProductoDTO = UtilConverter.obtenerValueMapProductos(productos);
		comboComponenteOProducto.setValueMap(mapaProductoDTO);
	}

}
