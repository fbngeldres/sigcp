package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaStockDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.stock.componentes.GridDetalle;
import pe.com.pacasmayo.sgcp.presentacion.gwt.stock.componentes.GridStock;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Combos;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Textos;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RowMouseUpEvent;
import com.smartgwt.client.widgets.grid.events.RowMouseUpHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/*
 * SGCP (Sistema de Gestion y Control de la Produccion) 
 * Archivo: ConsultarMedicionPorProducto.java
 * Modificado: Apr 14, 2010 6:06:08 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ConsultarMedicionPorProductos implements EntryPoint, ClienteServicioGwt {

	private static final String MENSAJE_NO_SE_TIENEN_REGISTROS_DE_MEDICION_ASOCIADOS = "No se tienen registros de medici\u00f3n asociados para la fecha indicada.";
	private static final String MENSAJE_VALIDACION_REGISTRO_SELECCIONADO = "Debe seleccionar un registro de medici\u00f3n.";
	private static final String LINK_REGISTRAR_MEDICION = "../stock/registrarMedicion.action";
	private static final String CONFIRMAR_APROBAR_REGISTROMEDICION = "Aprobar\u00e1 el registro de medici\u00f3n seleccionado. Desea continuar?";
	private static final String CONFIRMAR_REVERSION_REGISTROMEDICION = "Revertir\u00e1 el registro de medici\u00f3n seleccionado. Desea continuar?";

	private static final String CONFIRMAR_ANULAR_REGISTROMEDICION = "Anular\u00e1 el registro de medici\u00f3n seleccionado. Desea continuar?";
	private static final String MENSAJE_REGISTROMEDICION_YA_APROBADO = "El registro de medici\u00f3n seleccionado ya se encuentra en estado Aprobado.";
	private static final String MENSAJE_REGISTROMEDICION_YA_ANULADO = "El registro de medici\u00f3n seleccionado ya se encuentra en estado Anulado.";
	private static final String MENSAJE_REGISTROMEDICION_ANULADO_O_GENERADO = "El registro de medici\u00f3n seleccionado se encuentra en Generado \u00f3 Anulado.";
	// Variables para los properties
	static Map<String, String> mapaPropiedades = new HashMap<String, String>();
	public static final String CONST_MENSAJE_ERROR = "mensaje.general.error";
	public static final String CONST_DIVISION = "mensaje.validacion.division";
	public static final String CONST_SOCIEDAD = "mensaje.validacion.sociedad";
	public static final String CONST_UNIDAD = "mensaje.validacion.unidad";
	public static final String CONST_LINEANEGOCIO = "mensaje.validacion.lineaNegocio";
	public static final String CONST_MES = "mensaje.validacion.mes";
	public static final String CONST_PROCESO = "mensaje.validacion.proceso";
	public static final String CONST_ANIO = "mensaje.validacion.anio";

	private static final ConstantesModuloStock CONSTANTES = GWT.create(ConstantesModuloStock.class);

	private DynamicForm formularioCombos;
	private Canvas gridStockCanvas = new Canvas();
	private Canvas gridDetalleCanvas = new Canvas();
	private GridStock gridStock;
	private GridDetalle gridDetalle;
	private VLayout layoutGrid = new VLayout();
	private static StockServiceAsync servicioStock = GWT.create(StockService.class);

	public void onModuleLoad() {

		cargarVariables();
		dibujarBandaIconos();
		formularioCombos = initFormularioCombos();
		RootPanel.get("formularioCombos").add(formularioCombos);
		Combos.agregarEventosFormularioCombos(formularioCombos);

		layoutGrid.setWidth(700);
		layoutGrid.addMember(Textos.obtenerEspacioBlanco());
		layoutGrid.addMember(gridStockCanvas);
		layoutGrid.addMember(Textos.obtenerEspacioBlanco());
		layoutGrid.addMember(gridDetalleCanvas);
		RootPanel.get("gridStock").add(layoutGrid);
		layoutGrid.draw();

	}

	private void cargarVariables() {
		// Se buscan ciertas propiedades en el resource.properties que se van a
		// utilizar para ciertas validaciones y ejecucion de formulas
		List<String> listaClaves = Arrays.asList(CONST_MENSAJE_ERROR, CONST_DIVISION, CONST_SOCIEDAD, CONST_UNIDAD,
				CONST_LINEANEGOCIO, CONST_PROCESO, CONST_ANIO, CONST_MES);

		servicioComunicacion.obtenerMapaPropiedadesPorListaClaves(listaClaves, new AsyncCallback<Map<String, String>>() {
			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(Map<String, String> mapa) {
				Iterator<String> iterator = mapa.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next().toString();
					String value = mapa.get(key).toString();
					mapaPropiedades.put(key, value);
				}
			}
		});
	}

	/**
	 * Metodo que llena combos dependientes
	 */
	public static DynamicForm initFormularioCombos() {

		DynamicForm form = new DynamicForm();

		form.setNumCols(6);
		form.setWidth(700);
		form.setColWidths(120, 10, 90, 10, 90, 10);
		form.setAlign(Alignment.LEFT);
		// Cargamos el combo de divisiones
		SelectItem divisionItem = Combos.cargarComboDivisiones();
		divisionItem.setTitle(divisionItem.getTitle());

		// Cargamos las sociedades segun la division seleccionada
		SelectItem sociedadItem = Combos.cargarComboSociedades();
		sociedadItem.setTitle(sociedadItem.getTitle());

		// Cargamos las unidades segun la sociedad seleccionada
		SelectItem unidadItem = Combos.cargarComboUnidades();
		unidadItem.setTitle(unidadItem.getTitle());

		// Cargamos las lineas de negocio segun la unidad seleccionada
		SelectItem lineaNegocioItem = Combos.cargarComboLineasNegocio();
		lineaNegocioItem.setTitle(lineaNegocioItem.getTitle());

		// Cargamos los procesos segun la linea de negocio seleccionada
		SelectItem procesoItem = Combos.cargarComboProcesos();
		procesoItem.setTitle(procesoItem.getTitle());

		// Cargamos los tipos de almacenamiento
		SelectItem tipoAlmacenamientoItem = Combos.cargarComboTiposMedioAlmacenamiento();

		// Cargamos los estados de registro de medicion
		SelectItem estadoRegistroMedicionItem = Combos.cargarComboEstadosRegistroMedicion();

		// Cargamos el campo de texto para el año
		SelectItem annoItem = Combos.cargarComboAnnos();
		annoItem.setAllowEmptyValue(true);
		annoItem.setTitle(annoItem.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);

		// Cargamos el combo de meses
		SelectItem mesesItem = Combos.cargarComboMeses();
		mesesItem.setTitle(mesesItem.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);

		// Agregamos los combos al formulario
		form.setItems(divisionItem, sociedadItem, unidadItem, lineaNegocioItem, procesoItem, tipoAlmacenamientoItem, annoItem,
				mesesItem, estadoRegistroMedicionItem);

		return form;
	}

	/**
	 * Metodo que crea la banda de iconos del menu superior
	 */
	private void dibujarBandaIconos() {

		// Nuevo
		Img imagenNuevo = new Img("", 18, 20);
		imagenNuevo.setName(ConstantesGWT.NOMBRE_IMAGEN_NUEVO);
		imagenNuevo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickNuevo();
			}
		});
		Label etiquetaNuevo = new Label(CONSTANTES.etiquetaMedicion());
		etiquetaNuevo.setCursor(Cursor.HAND);
		etiquetaNuevo.setHeight(15);
		etiquetaNuevo.setDisabled(true);
		etiquetaNuevo.setID(ConstantesGWT.NOMBRE_ETIQUETA_NUEVO);
		etiquetaNuevo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickNuevo();
			}
		});

	

		if (RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_NUEVO) != null) {
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_NUEVO).add(imagenNuevo);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_NUEVO).setStyleName(ConstantesGWT.NOMBRE_ESTILO_NUEVO);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_NUEVO).add(etiquetaNuevo);
		}

		// Aprobar
		Img imagenAprobar = new Img("", 18, 20);
		imagenAprobar.setName(ConstantesGWT.NOMBRE_IMAGEN_APROBAR);
		imagenAprobar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickcambiarEstado(true);
			}
		});
		Label etiquetaAprobar = new Label(ConstantesGWT.APROBAR);
		etiquetaAprobar.setCursor(Cursor.HAND);
		etiquetaAprobar.setHeight(15);
		etiquetaAprobar.setID(ConstantesGWT.NOMBRE_ETIQUETA_APROBAR);
		etiquetaAprobar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickcambiarEstado(true);
			}
		});
		
		if(RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_APROBAR) != null){
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_APROBAR).add(imagenAprobar);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_APROBAR).setStyleName(ConstantesGWT.NOMBRE_ESTILO_APROBAR);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_APROBAR).add(etiquetaAprobar);

		}
		
		// Anular
		Img imagenAnular = new Img("", 18, 20);
		imagenAnular.setName(ConstantesGWT.NOMBRE_ETIQUETA_ANULAR);
		imagenAnular.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickcambiarEstado(false);
			}
		});
		Label etiquetaAnular = new Label(ConstantesGWT.ANULAR);
		etiquetaAnular.setCursor(Cursor.HAND);
		etiquetaAnular.setHeight(15);
		etiquetaAnular.setID(ConstantesGWT.NOMBRE_ETIQUETA_ANULAR);
		etiquetaAnular.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickcambiarEstado(false);
			}
		});
		
		if(RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_ANULAR) != null){
			
		
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_ANULAR).add(imagenAnular);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_ANULAR).setStyleName(ConstantesGWT.NOMBRE_ESTILO_ANULAR);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_ANULAR).add(etiquetaAnular);
		}
		// Filtrar
		Img imagenFiltrar = new Img("", 18, 20);
		imagenFiltrar.setName(ConstantesGWT.NOMBRE_IMAGEN_FILTRAR);
		imagenFiltrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickFiltrar();
			}
		});
		Label etiquetaFiltrar = new Label(ConstantesGWT.FILTRAR);
		etiquetaFiltrar.setCursor(Cursor.HAND);
		etiquetaFiltrar.setHeight(15);
		etiquetaFiltrar.setID(ConstantesGWT.NOMBRE_ETIQUETA_FILTRAR);
		etiquetaFiltrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickFiltrar();
			}
		});
		
		if(RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_FILTRAR) != null){
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_FILTRAR).add(imagenFiltrar);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_FILTRAR).setStyleName(ConstantesGWT.NOMBRE_ESTILO_FILTRAR);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_FILTRAR).add(etiquetaFiltrar);

		}
		
		// Revertir
		Img imagenRevertir = new Img("", 18, 20);
		imagenRevertir.setName(ConstantesGWT.NOMBRE_IMAGEN_REVERSION);
		imagenRevertir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRevertir();
			}
		});
		Label etiquetaRevertir = new Label(ConstantesGWT.REVERSION);
		etiquetaRevertir.setCursor(Cursor.HAND);
		etiquetaRevertir.setHeight(15);
		etiquetaRevertir.setID(ConstantesGWT.NOMBRE_ETIQUETA_REVERSION);
		etiquetaRevertir.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRevertir();
			}

		});
		
		if(RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REVERSION) != null){
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REVERSION).add(imagenRevertir);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REVERSION).setStyleName(ConstantesGWT.NOMBRE_ESTILO_REVERSION);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REVERSION).add(etiquetaRevertir);
		}
		
	}

	private void eventoClickRevertir() {
		if (gridStock == null) {
			Window.alert(MENSAJE_VALIDACION_REGISTRO_SELECCIONADO);
			return;
		}

		// Capturamos el registro seleccionado
		final Record registroSeleccionado = gridStock.getSelectedRecord();

		// valida si el registro de la lista es seleccionado
		if (registroSeleccionado == null) {
			Window.alert(MENSAJE_VALIDACION_REGISTRO_SELECCIONADO);
			return;
		}
		// Extraemos el codigo del registro seleccionado
		final Long codigoRegistroMedicion = Long.valueOf(registroSeleccionado.getAttribute(GridStock.COLUMNA_CODIGO_REGISTRO));
		final Long codigoEstadoRegistro = Long.valueOf(registroSeleccionado.getAttribute(GridStock.COLUMNA_CODIGO_ESTADO));

		final String codigoEstado = ConstantesGWT.CODIGO_ESTADO_REGISTRO_MEDICION_APROBADA;

		servicioComunicacion.obtenerPropiedadPorClave(codigoEstado, new AsyncCallback<String>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(String codigoEstadoObtenidoStr) {

				Long codigoEstadoObtenido = Long.valueOf(codigoEstadoObtenidoStr);

				boolean resultadoConfirmacion = false;
				if (codigoEstadoRegistro.longValue() == codigoEstadoObtenido.longValue()) {

					resultadoConfirmacion = Window.confirm(CONFIRMAR_REVERSION_REGISTROMEDICION);

				} else {

					Window.alert(MENSAJE_REGISTROMEDICION_ANULADO_O_GENERADO);
					return;
				}

				if (resultadoConfirmacion) {

					final String codigoEstadoGenerado = ConstantesGWT.CODIGO_ESTADO_REGISTRO_MEDICION_GENERADA;
					servicioComunicacion.obtenerPropiedadPorClave(codigoEstadoGenerado, new AsyncCallback<String>() {

						public void onFailure(Throwable arg0) {
							Window.alert(ConstantesGWT.SERVER_ERROR);

						}

						public void onSuccess(String codigoObtenido) {

							Long estadoGenerado = Long.valueOf(codigoObtenido);

							cambiarEstado(codigoRegistroMedicion, estadoGenerado, registroSeleccionado);

						}

					});

				}
			}
		});
	}

	private void cambiarEstado(Long codigoRegistroMedicion, final Long codigoEstadoObtenido, final Record registroSeleccionado) {
		servicioStock.cambiarEstadoRegistroMedicion(codigoRegistroMedicion, codigoEstadoObtenido, new AsyncCallback<String>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(final String mensaje) {
				String nombreEstado = ConstantesGWT.NOMBRE_ESTADO_REGISTRO_MEDICION_GENERADO;

				servicioComunicacion.obtenerPropiedadPorClave(nombreEstado, new AsyncCallback<String>() {

					public void onFailure(Throwable arg0) {

						Window.alert(ConstantesGWT.SERVER_ERROR);
					}

					public void onSuccess(final String nombreEstadoObtenido) {

						registroSeleccionado.setAttribute(GridStock.COLUMNA_NOMBRE_ESTADO, nombreEstadoObtenido);
						registroSeleccionado.setAttribute(GridStock.COLUMNA_CODIGO_ESTADO, codigoEstadoObtenido);
						Window.alert(mensaje);
						gridStock.refreshFields();

					}
				});
			}
		});

	}

	/**
	 * Metodo para filtrar la lista de mediciones
	 */
	private void eventoClickFiltrar() {

		// Validamos la seleccion de proceso, mes y aï¿½o
		if (validarFiltrosObligatorios()) {

			// Capturamos los valores de los filtros de proceso, mes y aï¿½o
			final Long codigoProceso = Long.parseLong(formularioCombos.getField(Combos.COMBO_PROCESO).getValue().toString());
			final Integer anno = Integer.parseInt(formularioCombos.getField(Combos.COMBO_ANNO).getValue().toString());
			final String mes = formularioCombos.getField(Combos.COMBO_MES).getValue().toString();

			// Validamos la seleccion de filtro de etado de registro de medicion
			// y
			// de tipo de medio de almacenamiento
			final Long codigoEstadoRegistroMedicion = validarFiltroEstado();
			final Long codigoTipoMedio = validarFiltroTipoMedio();

			// Al filtrar debemos limpiar el grid de detalles de silos
			if (gridDetalle != null) {
				gridDetalle.limpiarGrid();
			}

			// Llamamos al metodo obtenerMapaStock del servicio de stock
			servicioStock.obtenerListaRegistroStock(codigoProceso, codigoEstadoRegistroMedicion, codigoTipoMedio, anno, mes,
					new AsyncCallback<List<RegistroTablaStockDTO>>() {
						public void onFailure(Throwable arg0) {
							Window.alert(ConstantesGWT.SERVER_ERROR);
						}

						public void onSuccess(List<RegistroTablaStockDTO> listaRegistroStock) {

							gridStock = new GridStock(listaRegistroStock);
							gridStock.setParentElement(gridStockCanvas);
							gridStock.cargarGridStock(listaRegistroStock);
							gridStock.draw();

							// Definimos el manejador del evento de click del
							// mouse,
							// que se encargara de actualizar el grid inferior
							// de
							// detalle del stock del dia
							RowMouseUpHandler handler = new RowMouseUpHandler() {

								public void onRowMouseUp(RowMouseUpEvent event) {

									// Capturamos la fila seleccionada del grid
									// de
									// stock para ver la fecha y ver los
									// detalles
									ListGridRecord record = gridStock.getSelectedRecord();
									Long codigoRegistroMedicion = Long.parseLong(record
											.getAttribute(GridStock.COLUMNA_CODIGO_REGISTRO));

									// Cargamos la lista de detalles del silo
									// para
									// ese dia.
									servicioStock.obtenerListaRegistroDetallesStock(codigoRegistroMedicion,
											new AsyncCallback<List<RegistroTablaMedicionDiaDTO>>() {

												public void onFailure(Throwable arg0) {
													Window.alert(ConstantesGWT.SERVER_ERROR);

												}

												public void onSuccess(List<RegistroTablaMedicionDiaDTO> listaRegistroDetalleStock) {

													if ((listaRegistroDetalleStock != null)
															&& (listaRegistroDetalleStock.size() != 0)) {
														if (gridDetalle != null) {
															gridDetalle.destroy();
														}
														gridDetalle = new GridDetalle(listaRegistroDetalleStock);
														gridDetalle.setParentElement(gridDetalleCanvas);

														gridDetalle.cargarGridDetalle(listaRegistroDetalleStock);
														gridDetalle.draw();
														gridDetalle.show();
													} else {
														Window.alert(MENSAJE_NO_SE_TIENEN_REGISTROS_DE_MEDICION_ASOCIADOS);
													}
												}
											});
								}
							};

							gridStock.addRowMouseUpHandler(handler);

						}
					});
		}
	}

	private Long validarFiltroTipoMedio() {
		Long codigoTipoMedio = null;
		if (formularioCombos.getField(Combos.COMBO_TIPOS_MEDIO).getValue() != null) {

			codigoTipoMedio = Long.parseLong(formularioCombos.getField(Combos.COMBO_TIPOS_MEDIO).getValue().toString());
		}
		return codigoTipoMedio;
	}

	private Long validarFiltroEstado() {
		Long codigoEstadoRegistroMedicion = null;

		if (formularioCombos.getField(Combos.COMBO_ESTADO_REGISTRO_MEDICION).getValue() != null) {
			codigoEstadoRegistroMedicion = Long.parseLong(formularioCombos.getField(Combos.COMBO_ESTADO_REGISTRO_MEDICION)
					.getValue().toString());
		}
		return codigoEstadoRegistroMedicion;
	}

	private boolean validarFiltrosObligatorios() {

		boolean validado = true;
		String campos = "";
		String GUION = "- ";
		String RETURN = "\r";

		// Cargamos los mensajes desde el resource.properties
		String mensaje = mapaPropiedades.get(CONST_MENSAJE_ERROR);
		String tituloCampoDivision = mapaPropiedades.get(CONST_DIVISION);
		String tituloCampoSociedad = mapaPropiedades.get(CONST_SOCIEDAD);
		String tituloCampoUnidad = mapaPropiedades.get(CONST_UNIDAD);
		String tituloCampoLineaNegocio = mapaPropiedades.get(CONST_LINEANEGOCIO);
		String tituloCampoProceso = mapaPropiedades.get(CONST_PROCESO);
		String tituloCampoAnio = mapaPropiedades.get(CONST_ANIO);
		String tituloCampoMes = mapaPropiedades.get(CONST_MES);

		// Campo Division
		if (formularioCombos.getField(Combos.COMBO_DIVISION).getValue() == null) {
			validado = false;
			campos += GUION + tituloCampoDivision + RETURN;
		}
		// Campo Sociedad
		if (formularioCombos.getField(Combos.COMBO_SOCIEDAD).getValue() == null) {
			validado = false;
			campos += GUION + tituloCampoSociedad + RETURN;
		}
		// Campo Unidad
		if (formularioCombos.getField(Combos.COMBO_UNIDAD).getValue() == null) {
			validado = false;
			campos += GUION + tituloCampoUnidad + RETURN;
		}
		// Campo Linea de Negocio
		if (formularioCombos.getField(Combos.COMBO_LINEA_NEGOCIO).getValue() == null) {
			validado = false;
			campos += GUION + tituloCampoLineaNegocio + RETURN;
		}
		// Campo Proceso
		if (formularioCombos.getField(Combos.COMBO_PROCESO).getValue() == null) {
			validado = false;
			campos += GUION + tituloCampoProceso + RETURN;
		}
		// Campo Anio
		if (formularioCombos.getField(Combos.COMBO_ANNO).getValue() == null) {
			validado = false;
			campos += GUION + tituloCampoAnio + RETURN;
		}
		// Campo Division
		if (formularioCombos.getField(Combos.COMBO_MES).getValue() == null) {
			validado = false;
			campos += GUION + tituloCampoMes + RETURN;
		}

		if (!validado) {
			mensaje += campos;
			Window.alert(mensaje);
			return false;
		}

		return true;

	}

	/**
	 * Metodo para llamar a la vetnana de registro de medicion
	 */
	private void eventoClickNuevo() {
		Window.Location.assign(LINK_REGISTRAR_MEDICION);
	}

	/**
	 * Metodo para aprobar la Medicion diaria seleccionada
	 */
	private void eventoClickcambiarEstado(final boolean aprobar) {

		if (gridStock == null) {
			Window.alert(MENSAJE_VALIDACION_REGISTRO_SELECCIONADO);
			return;
		}

		// Capturamos el registro seleccionado
		final Record registroSeleccionado = gridStock.getSelectedRecord();

		// valida si el registro de la lista es seleccionado
		if (registroSeleccionado == null) {
			Window.alert(MENSAJE_VALIDACION_REGISTRO_SELECCIONADO);
			return;
		}

		// Extraemos el codigo del registro seleccionado
		final Long codigoRegistroMedicion = new Long(registroSeleccionado.getAttribute(GridStock.COLUMNA_CODIGO_REGISTRO));
		final Long codigoEstadoRegistro = new Long(registroSeleccionado.getAttribute(GridStock.COLUMNA_CODIGO_ESTADO));

		final String codigoEstado = aprobar ? ConstantesGWT.CODIGO_ESTADO_REGISTRO_MEDICION_APROBADA
				: ConstantesGWT.CODIGO_ESTADO_REGISTRO_MEDICION_ANULADO;
		servicioComunicacion.obtenerPropiedadPorClave(codigoEstado, new AsyncCallback<String>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(String codigoEstadoObtenidoStr) {

				Long codigoEstadoObtenido = Long.valueOf(codigoEstadoObtenidoStr);

				if (codigoEstadoRegistro.longValue() == codigoEstadoObtenido.longValue()) {
					if (aprobar) {
						Window.alert(MENSAJE_REGISTROMEDICION_YA_APROBADO);
					} else {
						Window.alert(MENSAJE_REGISTROMEDICION_YA_ANULADO);
					}
					return;
				}

				String mensajeConfirmar = aprobar ? CONFIRMAR_APROBAR_REGISTROMEDICION : CONFIRMAR_ANULAR_REGISTROMEDICION;
				boolean resultadoConfirmacion = Window.confirm(mensajeConfirmar);
				if (resultadoConfirmacion) {
					servicioStock.cambiarEstadoRegistroMedicion(codigoRegistroMedicion, codigoEstadoObtenido,
							new AsyncCallback<String>() {

								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(final String mensaje) {

									String nombreEstado = aprobar ? ConstantesGWT.NOMBRE_ESTADO_REGISTRO_MEDICION_APROBADA
											: ConstantesGWT.NOMBRE_ESTADO_REGISTRO_MEDICION_APNULADA;

									servicioComunicacion.obtenerPropiedadPorClave(nombreEstado, new AsyncCallback<String>() {

										public void onFailure(Throwable arg0) {
											Window.alert(ConstantesGWT.SERVER_ERROR);
										}

										public void onSuccess(final String nombreEstadoObtenido) {

											servicioComunicacion.obtenerPropiedadPorClave(codigoEstado,
													new AsyncCallback<String>() {

														public void onFailure(Throwable arg0) {
															Window.alert(ConstantesGWT.SERVER_ERROR);
														}

														public void onSuccess(String codigoEstadoAprobado) {
															Long codigoEstado = new Long(codigoEstadoAprobado);

															registroSeleccionado.setAttribute(GridStock.COLUMNA_NOMBRE_ESTADO,
																	nombreEstadoObtenido);
															registroSeleccionado.setAttribute(GridStock.COLUMNA_CODIGO_ESTADO,
																	codigoEstado);
															Window.alert(mensaje);
															gridStock.refreshFields();
														}
													});
										}
									});
								}
							});
				}
			}
		});
	}

}