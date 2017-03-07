package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadocubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.stock.componentes.GridCubicacionMes;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Combos;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.UtilConverter;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.VerificarSesion;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: RegistrarCubicacionMes.java
 * Modificado: Jul 22, 2011 8:40:15 AM
 * Autor: andrey.bottoni
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class RegistrarCubicacion implements EntryPoint, ClienteServicioGwt {

	private static final String TITULO_COMBO_ESTADO = "Estado";
	private static final String COMBO_ESTADO = "estado";
	private static final String NOMBRE_ETIQUETA_GRABAR = "etiquetaGrabar";
	private static final String NOMBRE_ETIQUETA_REFRESCAR = "etiquetaRefrescar";
	private static final String NOMBRE_IMAGEN_GRABAR = "imagenGrabar";
	private static final String NOMBRE_IMAGEN_REFRESCAR = "imagenRefrescar";
	private static final String NOMBRE_IMAGEN_CONSULTAR = "imagenConsultar";
	private static final String NOMBRE_ETIQUETA_CONSULTAR = "etiquetaConsultar";
	private static final String NOMBRE_ESTILO_CONSULTAR = "consultar";
	private static final String CONSULTAR = "Consultar";
	private static final String NOMBRE_ESTILO_GRABAR = "grabar";
	private static final String GRABAR = "Grabar";
	private static final String NOMBRE_ESTILO_REFRESCAR = "refrescar";
	private static final String REFRESCAR = "Refrescar";

	private static final String COMBO_MEDIO_ALMACENAMIENTO = "medioAlmacenamiento";
	private static final String TITULO_MEDIO_ALMACENAMIENTO = "Medio Almac.";

	private static final String TXT_AREA_CRESTA = "areaCresta";
	private static final String TITULO_AREA_CRESTA = "&Aacute;rea de Cresta (m2)";

	private static final String TXT_AREA_PIE = "areaPie";
	private static final String TITULO_AREA_PIE = "&Aacute;rea de Pie (m2)";

	private static final String TXT_DIF_NIVEL = "difNivel";
	private static final String TITULO_DIF_NIVEL = "Dif de Nivel (mts)";

	private static final String TXT_VOLUMEN = "volumen";
	private static final String TITULO_VOLUMEN = "Volumen (m3)";

	private static final String TITULO_UNIDAD_KG = "Unidad Kg (m)";
	private static final String TXT_UNIDAD_KG = "unidadKg";

	private static final String TITULO_ALT_LIBRE_PROMEDIO = "Alt Libre Promedio (m)";
	private static final String TXT_ALT_LADO_PROMEDIO = "altLibrePromedio";

	private static final String TITULO_DENSIDAD = "Densidad";
	private static final String TXT_DENSIDAD = "densidad";

	private static final String TITULO_HUMEDAD_PONDERADA = "Humedad";
	private static final String TXT_HUMEDAD_PONDERADA = "humedadPonderada";

	private static final String TITULO_ALT_LADO_CLINKER = "Alt Lado Clinker (m)";
	private static final String TXT_ALT_LADO_CLINKER = "altLadoClinker";

	private static final String TITULO_ALT_CENTRAL = "Alt Central (m)";
	private static final String TXT_ALT_CENTRAL = "altCentral";

	private static final String TITULO_ALT_LADO_CARBON = "Alt Lado Carbon (m)";
	private static final String TXT_ALT_LADO_CARBON = "altLadoCarbon";

	private static final String TITULO_RELACION = "Relaci&oacute;n";
	private static final String TXT_RELACION = "relacion";

	private static final String TXT_AREA_OCUPADA = "areaOcupada";
	private static final String TITULO_AREA_OCUPADA = "&Aacute;rea Ocupada (m2)";

	private static final String TITULO_OBSERVACIONES = "Observaciones";
	private static final String TXT_OBSERVACIONES = "observaciones";

	private static final String TITULO_AGREGAR_CUBICACION = "Agregar";

	private static final double VALOR_INVALIDO_TEXFIELD_NUMERICO = -1D;
	private static final String MSJ_VALIDACION_MEDIO_ALMACENAMIENTO = "Debe seleccionar el Medio de Almacenamiento";
	private static final String MSJ_VALIDACION_MEDIO_ALMACENAMIENTO_REPETIDO = "El medio de almacenamiento ya esta registrado";

	private static final String MSJ_VALIDACION_TIPO_MEDIO_ALMACENAMIENTO = "Debe seleccionar el Tipo de Medio de Almacenamiento";
	private static final String MSJ_VALIDACION_AREA_CRESTA = "El valor del campo Area Cresta es requerido y debe ser un numero positivo";
	private static final String MSJ_VALIDACION_AREA_PIE = "El valor del campo Area Pie es requerido y debe ser un numero positivo";
	private static final String MSJ_VALIDACION_DIF_NIVEL = "El valor del campo Dif Nivel es requerido y debe ser un numero positivo";
	private static final String MSJ_VALIDACION_VOLUMEN = "El valor del campo Volumen es requerido y debe ser un numero positivo";
	private static final String MSJ_VALIDACION_AREA_OCUPADA = "El valor del campo Area Ocupada es requerido y debe ser un numero positivo";
	private static final String MSJ_VALIDACION_ALT_LADO_PROM = "El valor del campo Alt Libre Promedio es requerido y debe ser un numero positivo";
	private static final String MSJ_VALIDACION_RELACION = "El valor del campo Relacion debe ser un numero positivo";
	private static final String MSJ_VALIDACION_ALT_LADO_CARBON = "El valor del campo Alt Lado Carbon debe ser un numero positivo";
	private static final String MSJ_VALIDACION_ALT_CENTRAL = "El valor del campo Alt Central es requerido y debe ser un numero positivo";
	private static final String MSJ_VALIDACION_ALT_LADO_CLINKER = "El valor del campo Alt Lado Clinker debe ser un numero positivo";
	private static final String MSJ_VALIDACION_UNIDAD = "El valor del campo Unidad (Kg) debe ser un numero positivo";
	private static final String MSJ_VALIDACION_COMBO_LINEA_NEGOCIO = "Debe seleccionar un valor para el campo Linea de Negocio";
	private static final String MSJ_VALIDACION_COMBO_PRODUCTO = "Debe seleccionar un valor para el campo Producto";
	private static final String MSJ_VALIDACION_COMBO_ESTADO = "Debe seleccionar un valor para el campo Estado";
	private static final String MSJ_VALIDACION_COMBO_PROCESO = "Debe seleccionar un valor para el campo Proceso";
	private static final String MENSAJ_ERROR = "Ocurrio un error intentando realizar el registro. Consulte al administrador del sistema";
	private static final String EXITO = "exito";
	private static final String ERROR = "error";

	// Variables para los properties
	static Map<String, String> mapaPropiedades = new HashMap<String, String>();
	private static final String TMA_SILO = "registro.cubicacion.codigo.tipomedioalmacenamiento.silo";
	private static final String TMA_FIGURAS = "registro.cubicacion.codigo.tipomedioalmacenamiento.figuras";
	private static final String MA_FIG1 = "registro.cubicacion.codigo.medioalmacenamiento.fig1";
	private static final String MA_FIG2 = "registro.cubicacion.codigo.medioalmacenamiento.fig2";
	private static final String MA_FIG3 = "registro.cubicacion.codigo.medioalmacenamiento.fig3";
	private static final String MA_FIG4 = "registro.cubicacion.codigo.medioalmacenamiento.fig4";
	private static final String PROD_CLKI = "registro.cubicacion.codigo.producto.clk1";
	private static final String PROD_CLKV = "registro.cubicacion.codigo.producto.clk5";
	String codProductoStr;

	private DynamicForm formularioCombos;
	private DynamicForm formularioDatosCubicacionProducto;
	private DynamicForm formularioTablaCubicacion;
	private GridCubicacionMes gridCubicacionMes;
	private VLayout layout;
	private VLayout resultLayout;
	private HLayout searchFormPanel;
	private HLayout addFormPanel;
	private Img imagenGrabar;
	private Label etiquetaGrabar;
	private Label etiquetaEspaciadora;
	private Label etiquetaResultados;
	private SelectItem procesoItem;

	private static DateItem fechaItem;
	private static final ConstantesModuloStock CONSTANTES = GWT.create(ConstantesModuloStock.class);

	/**
	 * Metodo de carga del Punto de entrada
	 */
	public void onModuleLoad() {

		cargarVariables();
		dibujarBandaIconos();
		searchFormPanel = new HLayout();
		addFormPanel = new HLayout();
		resultLayout = new VLayout();
		formularioCombos = initFormularioCombos();
		searchFormPanel.addChild(formularioCombos);
		initFormularioDatosCubicacionProducto();
		searchFormPanel.addChild(formularioCombos);
		addFormPanel.addChild(formularioDatosCubicacionProducto);

		RootPanel.get("formularioCombos").add(searchFormPanel);
		RootPanel.get("formularioDatosCubicacionProducto").add(addFormPanel);
		initFormularioTablaCubicacion();
		RootPanel.get("formularioTablaCubicacion").add(layout);

		layout.draw();

		// Timer para verificar si la sesion finaliza y mostrarle esto al
		// usuario
		VerificarSesion verificarSesion = new VerificarSesion();
		verificarSesion.iniciarSesionTimer();
	}

	public static void mostrarBarraProgreso() {
		RootPanel.get("barraProgreso").setVisible(true);
	}

	public static void ocultarBarraProgreso() {
		RootPanel.get("barraProgreso").setVisible(false);
	}

	private void cargarVariables() {
		// Se buscan ciertas propiedades en el resource.properties que se van a
		// utilizar para ciertas validaciones y ejecucion de formulas
		List<String> listaClaves = Arrays.asList(TMA_SILO, TMA_FIGURAS, MA_FIG1, MA_FIG2, MA_FIG3, MA_FIG4, PROD_CLKI, PROD_CLKV);
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
	 * Metodo que carga los combos del formulario del registro de cubicacion
	 */
	private DynamicForm initFormularioCombos() {

		DynamicForm form = new DynamicForm();
		form.setNumCols(6);
		form.setWidth(700);
		form.setTitleWidth(400);
		form.setAlign(Alignment.LEFT);
		form.setColWidths(120, 10, 90, 10, 90, 10);
		int anchoCombos = 110;

		SelectItem divisionItem = Combos.cargarComboDivisiones();
		divisionItem.setTitle(divisionItem.getTitle());
		divisionItem.setRequired(true);
		divisionItem.setWidth(anchoCombos);

		SelectItem sociedadItem = Combos.cargarComboSociedades();
		sociedadItem.setTitle(sociedadItem.getTitle());
		sociedadItem.setWidth(anchoCombos);

		SelectItem unidadItem = Combos.cargarComboUnidades();
		unidadItem.setTitle(unidadItem.getTitle());
		unidadItem.setWidth(anchoCombos);
		unidadItem.setEndRow(true);

		SelectItem lineaNegocioItem = Combos.cargarComboLineasNegocio();
		lineaNegocioItem.setTitle(lineaNegocioItem.getTitle());
		lineaNegocioItem.setWidth(anchoCombos);

		procesoItem = Combos.cargarComboProcesos();
		procesoItem.setTitle(procesoItem.getTitle());
		procesoItem.setWidth(anchoCombos);

		SelectItem productoItem = Combos.cargarComboProducto();
		productoItem.setTitle(productoItem.getTitle());
		productoItem.setWidth(anchoCombos);
		productoItem.setEndRow(true);

		SelectItem estadoItem = cargarComboEstado();
		estadoItem.setWidth(anchoCombos);

		fechaItem = Combos.cargarComboFecha("fecha", "Fecha");
		fechaItem.addChangedHandler(fechaChangeHandler());

		form.setItems(divisionItem, sociedadItem, unidadItem, lineaNegocioItem, procesoItem, productoItem, estadoItem, fechaItem);

		Combos.addDivisionItemChangeHandlerRegistro(divisionItem, sociedadItem, unidadItem, lineaNegocioItem, procesoItem,
				productoItem);
		Combos.addSociedadItemChangeHandlerRegistro(sociedadItem, unidadItem, lineaNegocioItem, procesoItem, productoItem);
		Combos.addUnidadItemChangeHandlerRegistro(unidadItem, lineaNegocioItem, procesoItem, productoItem);
		Combos.addLineaNegocioItemChangeHandlerRegistro(lineaNegocioItem, procesoItem, productoItem);
		Combos.addProcesoItemChangeHandlerRegistro(procesoItem, productoItem);

		productoItem.addChangedHandler(productoChangeHandler());

		return form;
	}

	private ChangedHandler fechaChangeHandler() {
		return new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				validarRegistroExistente();
			}
		};
	}

	private ChangedHandler productoChangeHandler() {
		return new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				limpiarTextFieldsFormularioDatos();
				formularioDatosCubicacionProducto.getField(COMBO_MEDIO_ALMACENAMIENTO).clearValue();
				formularioDatosCubicacionProducto.getField(Combos.COMBO_TIPOS_MEDIO).clearValue();
				gridCubicacionMes.limpiarGrid();

				validarRegistroExistente();
			}
		};
	}

	private void validarRegistroExistente() {
		String codLineanegocioStr = getValStrFromformularioCombos(Combos.COMBO_LINEA_NEGOCIO);
		if (codLineanegocioStr == null || codLineanegocioStr.equals("")) {
			return;
		}
		int codigoLineaNegocio = Integer.parseInt((codLineanegocioStr));

		String codProcesoStr = getValStrFromformularioCombos(Combos.COMBO_PROCESO);
		if (codProcesoStr == null || codProcesoStr.equals("")) {
			return;
		}
		int codigoProceso = Integer.parseInt((codProcesoStr));

		codProductoStr = getValStrFromformularioCombos(Combos.COMBO_PRODUCTO);
		if (codProductoStr == null || codProductoStr.equals("")) {
			return;
		}

		int codigoProducto = Integer.parseInt((codProductoStr));

		Date fechaCubicacion = (Date) fechaItem.getValue();

		servicioComunicacion.validarSiExisteCubicacionProducto(codigoLineaNegocio, codigoProducto, codigoProceso,
				fechaCubicacion, new AsyncCallback<Boolean>() {

					public void onFailure(Throwable arg0) {
						Window.alert(MENSAJ_ERROR);
					}

					public void onSuccess(Boolean existe) {
						if (existe) {
							Window.alert(CONSTANTES.cubicacionRegistrada());
							etiquetaGrabar.setDisabled(true);
							imagenGrabar.setDisabled(true);
							return;
						}
						etiquetaGrabar.setDisabled(false);
						imagenGrabar.setDisabled(false);
					}
				});
	}

	private SelectItem cargarComboEstado() {
		final SelectItem estadoItem = new SelectItem(COMBO_ESTADO, TITULO_COMBO_ESTADO);
		estadoItem.setAllowEmptyValue(false);
		servicioComunicacion.cargarEstadosCubicacion(new AsyncCallback<List<EstadocubicacionDTO>>() {
			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<EstadocubicacionDTO> estadoCubicacionDTO) {
				LinkedHashMap<String, String> mapaEstadosCubicacion = UtilConverter
						.obtenerMapaEstadoCubicacion(estadoCubicacionDTO);
				estadoItem.setValueMap(mapaEstadosCubicacion);
				estadoItem.setValue(1L);
				estadoItem.setAllowEmptyValue(false);
			}
		});
		return estadoItem;
	}

	private void addTipoMedioItemHandlerRegistro(final SelectItem tipoMedioItem, final SelectItem medioAlmacenamientoItem) {
		ChangeHandler changeHandler = new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if (procesoItem.getValue() == null) {
					Window.alert("Debe seleccionar  un proceso para poder cargar los medios de almacenamiento.");
					tipoMedioItem.clearValue();
					return;
				}
				long codigoTMA_SILO = 0;
				if (mapaPropiedades.get(TMA_SILO) != null) {
					codigoTMA_SILO = Long.parseLong(mapaPropiedades.get(TMA_SILO));
				}

				String valorTipoMedio = (String) event.getValue();

				if (valorTipoMedio != null) {
					final Long codigoProceso = new Long(procesoItem.getValue().toString());
					Long codigoProducto;
					codigoProducto = (codProductoStr == null) ? Long.valueOf(-1) : Long.valueOf(codProductoStr);

					servicioComunicacion.cargarMediosAlmacenamiento(new Long(valorTipoMedio), codigoProceso, codigoProducto,
							new AsyncCallback<List<MedioAlmacenamientoDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<MedioAlmacenamientoDTO> mediosDTO) {
									LinkedHashMap<String, String> mapaMedioAlmacenamiento = UtilConverter
											.obtenerMapaMedioAlmacenamientoDTO(mediosDTO);
									medioAlmacenamientoItem.setValueMap(mapaMedioAlmacenamiento);
									medioAlmacenamientoItem.setDisabled(false);
									medioAlmacenamientoItem.clearValue();
								}
							});
					long codigoTipoMedio = Long.parseLong((valorTipoMedio));
					boolean bool = true;
					if (codigoTipoMedio == codigoTMA_SILO) {
						formularioDatosCubicacionProducto.getField(TXT_VOLUMEN).clearValue();
						bool = false;
					}
					activarODesactivarTextFieldsOpcionales(bool);
					showItemByName(TXT_VOLUMEN);
				} else {
					medioAlmacenamientoItem.clearValue();
					medioAlmacenamientoItem.setDisabled(true);

				}
				/**
				 * Limpiamos la densidad
				 */
				formularioDatosCubicacionProducto.getField(TXT_DENSIDAD).clearValue();
			}

		};

		tipoMedioItem.addChangeHandler(changeHandler);

	}

	private void addMedioAlmacItemHandlerRegistro(SelectItem medioAlmacenamientoItem) {
		ChangeHandler changeHandler = new ChangeHandler() {

			public void onChange(ChangeEvent event) {

				// Id del Medio de Almacenamiento Fig1
				long codigoMA_FIG1 = 0;
				long codigoMA_FIG2 = 0;
				long codigoMA_FIG3 = 0;
				long codigoMA_FIG4 = 0;

				if (mapaPropiedades.get(MA_FIG1) != null) {
					// Id del Medio de Almacenamiento Fig1
					codigoMA_FIG1 = Long.parseLong(mapaPropiedades.get(MA_FIG1));
				}

				if (mapaPropiedades.get(MA_FIG2) != null) {
					// Id del Medio de Almacenamiento Fig2
					codigoMA_FIG2 = Long.parseLong(mapaPropiedades.get(MA_FIG2));
				}

				if (mapaPropiedades.get(MA_FIG3) != null) {
					// Id del Medio de Almacenamiento Fig3
					codigoMA_FIG3 = Long.parseLong(mapaPropiedades.get(MA_FIG3));
				}

				if (mapaPropiedades.get(MA_FIG4) != null) {
					// Id del Medio de Almacenamiento Fig4
					codigoMA_FIG4 = Long.parseLong(mapaPropiedades.get(MA_FIG4));
				}

				String valorMedioAlmacenamiento = (String) event.getValue();

				if (valorMedioAlmacenamiento != null) {
					long codigoMedioAlmacenamiento = Long.parseLong((valorMedioAlmacenamiento));
					/*
					 * El campo de Volumen se esconde cuando el Tipo de Medio de
					 * Almacenamiento escogido es Figuras y el Medio de
					 * Almacenamiento es Fig1, Fig2, Fig3 o Fig4. Se esconde
					 * porque para estos medios el volumen es calculado
					 */
					if ((codigoMedioAlmacenamiento == codigoMA_FIG1) || (codigoMedioAlmacenamiento == codigoMA_FIG2)
							|| (codigoMedioAlmacenamiento == codigoMA_FIG3) || (codigoMedioAlmacenamiento == codigoMA_FIG4)) {
						hideItemByName(TXT_VOLUMEN);
					} else {
						showItemByName(TXT_VOLUMEN);
					}

					/**
					 * AGREGADO POR JORDAN
					 */

					servicioComunicacion.cargarDensidadMedioAlmacenamiento(codigoMedioAlmacenamiento,
							new AsyncCallback<Double>() {

								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(Double densidad) {
									formularioDatosCubicacionProducto.getField(TXT_DENSIDAD).setValue(densidad);
								}

							});

				}
				limpiarTextFieldsFormularioDatos();
			}
		};
		medioAlmacenamientoItem.addChangeHandler(changeHandler);

	}

	/**
	 * Metodo que esconde el Item dado el nombre
	 * 
	 * @param itemName
	 */
	private void hideItemByName(String itemName) {
		formularioDatosCubicacionProducto.hideItem(itemName);
	}

	/**
	 * Metodo que muestra el Item dado el nombre
	 * 
	 * @param itemName
	 */
	private void showItemByName(String itemName) {
		formularioDatosCubicacionProducto.getItem(itemName).show();
	}

	/**
	 * Metod que activa o desactiva un conjunto de TextFields en el Formulario
	 * DatosCubicacionProducto
	 * 
	 * @param isDisable
	 */
	private void activarODesactivarTextFieldsOpcionales(boolean isDisable) {
		if (isDisable) {
			formularioDatosCubicacionProducto.hideItem(TXT_RELACION);
			formularioDatosCubicacionProducto.hideItem(TXT_ALT_CENTRAL);
			formularioDatosCubicacionProducto.hideItem(TXT_ALT_LADO_CARBON);
			formularioDatosCubicacionProducto.hideItem(TXT_ALT_LADO_CLINKER);
			formularioDatosCubicacionProducto.hideItem(TXT_ALT_LADO_PROMEDIO);
			formularioDatosCubicacionProducto.hideItem(TXT_UNIDAD_KG);
		} else {
			formularioDatosCubicacionProducto.getItem(TXT_RELACION).show();
			formularioDatosCubicacionProducto.getItem(TXT_ALT_CENTRAL).show();
			formularioDatosCubicacionProducto.getItem(TXT_ALT_LADO_CARBON).show();
			formularioDatosCubicacionProducto.getItem(TXT_ALT_LADO_CLINKER).show();
			formularioDatosCubicacionProducto.getItem(TXT_ALT_LADO_PROMEDIO).show();
			formularioDatosCubicacionProducto.getItem(TXT_UNIDAD_KG).show();
		}
	}

	private void initFormularioTablaCubicacion() {
		layout = new VLayout();
		layout.setWidth(700);
		// El valor anterior era true.El caso pedia que las columnas
		// Densidad y TM se mostrasen
		gridCubicacionMes = new GridCubicacionMes(true);
		layout.addMember(gridCubicacionMes);
	}

	private void initFormularioDatosCubicacionProducto() {
		formularioDatosCubicacionProducto = new DynamicForm();
		formularioDatosCubicacionProducto.setNumCols(8);
		formularioDatosCubicacionProducto.setWidth(700);
		formularioDatosCubicacionProducto.setTitleWidth(350);
		formularioDatosCubicacionProducto.setAlign(Alignment.LEFT);

		int colSpam = 3;
		SelectItem tipoMedioItem = Combos.cargarComboTiposMedioAlmacenamiento();
		tipoMedioItem.setTitle(tipoMedioItem.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);
		tipoMedioItem.setColSpan(colSpam);

		SelectItem medioAlmacenamientoItem = cargarComboMedioAlmacenamiento(colSpam);
		medioAlmacenamientoItem.setTitle(medioAlmacenamientoItem.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);

		TextItem areaCresta = new TextItem(TXT_AREA_CRESTA, TITULO_AREA_CRESTA);
		areaCresta.setTitle(areaCresta.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);
		areaCresta.setWidth(70);

		TextItem areaPie = new TextItem(TXT_AREA_PIE, TITULO_AREA_PIE);
		areaPie.setTitle(areaPie.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);
		areaPie.setWidth(70);

		TextItem difNivel = new TextItem(TXT_DIF_NIVEL, TITULO_DIF_NIVEL);
		difNivel.setTitle(difNivel.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);
		difNivel.setWidth(70);

		TextItem areaOcupada = new TextItem(TXT_AREA_OCUPADA, TITULO_AREA_OCUPADA);
		areaOcupada.setTitle(areaOcupada.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);
		areaOcupada.setEndRow(true);
		areaOcupada.setWidth(70);

		TextItem volumen = new TextItem(TXT_VOLUMEN, TITULO_VOLUMEN);
		volumen.setTitle(volumen.getTitle() + ConstantesGWT.CAMPO_OBLIGATORIO);
		volumen.setWidth(70);

		TextItem relacion = new TextItem(TXT_RELACION, TITULO_RELACION);
		relacion.setWidth(70);

		TextItem altLadoCarbon = new TextItem(TXT_ALT_LADO_CARBON, TITULO_ALT_LADO_CARBON);
		altLadoCarbon.setWidth(70);

		TextItem altCentral = new TextItem(TXT_ALT_CENTRAL, TITULO_ALT_CENTRAL);
		altCentral.setWidth(70);
		altCentral.setEndRow(true);

		TextItem altLadoClinker = new TextItem(TXT_ALT_LADO_CLINKER, TITULO_ALT_LADO_CLINKER);
		altLadoClinker.setWidth(70);

		TextItem altLadoPromedio = new TextItem(TXT_ALT_LADO_PROMEDIO, TITULO_ALT_LIBRE_PROMEDIO);
		altLadoPromedio.setWidth(70);

		TextItem unidadKg = new TextItem(TXT_UNIDAD_KG, TITULO_UNIDAD_KG);
		unidadKg.setWidth(70);
		unidadKg.setEndRow(true);

		TextAreaItem observacionesItem = new TextAreaItem(TXT_OBSERVACIONES, TITULO_OBSERVACIONES);
		observacionesItem.setColSpan(3);
		observacionesItem.setWidth(280);
		observacionesItem.setHeight(35);
		// observacionesItem.setEndRow(true);
		observacionesItem.setLength(50);

		// Agregado por Fabian
		// Muestra la densidad de un Almacen
		TextItem densidad = new TextItem(TXT_DENSIDAD, TITULO_DENSIDAD);
		densidad.setWidth(50);
		densidad.setAttribute("readOnly", true);

		TextItem humnedad = new TextItem(TXT_HUMEDAD_PONDERADA, TITULO_HUMEDAD_PONDERADA);
		humnedad.setWidth(50);
		humnedad.setEndRow(true);

		ButtonItem addCubicacionBtn = new ButtonItem();
		addCubicacionBtn.setTitle(TITULO_AGREGAR_CUBICACION);
		addCubicacionBtn.setColSpan(7);
		addCubicacionBtn.setAlign(Alignment.RIGHT);
		addCubicacionBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				try {
					registraCubicacion();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Se agrego el TextItem Densidad
		formularioDatosCubicacionProducto.setItems(tipoMedioItem, medioAlmacenamientoItem, areaCresta, areaPie, difNivel,
				areaOcupada, volumen, relacion, altLadoCarbon, altCentral, altLadoClinker, altLadoPromedio, unidadKg,
				observacionesItem, densidad, humnedad, addCubicacionBtn);

		addTipoMedioItemHandlerRegistro(tipoMedioItem, medioAlmacenamientoItem);

		addMedioAlmacItemHandlerRegistro(medioAlmacenamientoItem);

	}

	private SelectItem cargarComboMedioAlmacenamiento(int colSpam) {
		SelectItem medioAlmacenamientoItem = new SelectItem(COMBO_MEDIO_ALMACENAMIENTO, TITULO_MEDIO_ALMACENAMIENTO);
		medioAlmacenamientoItem.setAllowEmptyValue(true);
		medioAlmacenamientoItem.setDisabled(true);
		medioAlmacenamientoItem.setColSpan(colSpam);
		medioAlmacenamientoItem.setEndRow(true);
		return medioAlmacenamientoItem;
	}

	private String getValStr(String field) {
		Object value = formularioDatosCubicacionProducto.getField(field).getValue();

		return value == null ? null : value.toString();
	}

	private String getValStrFromformularioCombos(String field) {
		Object value = formularioCombos.getField(field).getValue();

		return value == null ? null : value.toString();
	}

	private Double getValAsDouble(String field) {
		Object value = formularioDatosCubicacionProducto.getField(field).getValue();

		try {
			return value == null ? VALOR_INVALIDO_TEXFIELD_NUMERICO : Double.parseDouble(value.toString());
		} catch (Exception e) {
			return VALOR_INVALIDO_TEXFIELD_NUMERICO;
		}
	}

	private Double getValAsDoubleNullable(String field) {
		Object value = formularioDatosCubicacionProducto.getField(field).getValue();

		try {
			return value == null ? null : Double.parseDouble(value.toString());
		} catch (Exception e) {
			return VALOR_INVALIDO_TEXFIELD_NUMERICO;
		}
	}

	private void registraCubicacion() {

		TablaCubicacionDTO tablaCubicacionDTO = new TablaCubicacionDTO();

		Date fecha = (Date) fechaItem.getValue();
		tablaCubicacionDTO.setFecha(fecha);

		String codLineanegocioStr = getValStrFromformularioCombos(Combos.COMBO_LINEA_NEGOCIO);
		if (codLineanegocioStr == null || codLineanegocioStr.equals("")) {
			Window.alert(MSJ_VALIDACION_COMBO_LINEA_NEGOCIO);
			return;
		}
		int codLineanegocio = Integer.parseInt((codLineanegocioStr));
		tablaCubicacionDTO.setCodigoLineaNegocio(codLineanegocio);

		String codProcesoStr = getValStrFromformularioCombos(Combos.COMBO_PROCESO);
		if (codProcesoStr == null || codProcesoStr.equals("")) {
			Window.alert(MSJ_VALIDACION_COMBO_PROCESO);
			return;
		}
		int codProceso = Integer.parseInt((codProcesoStr));
		tablaCubicacionDTO.setCodigoProceso(codProceso);

		String codProductoStr = getValStrFromformularioCombos(Combos.COMBO_PRODUCTO);
		if (codProductoStr == null || codProductoStr.equals("")) {
			Window.alert(MSJ_VALIDACION_COMBO_PRODUCTO);
			return;
		}

		String codTipoMedioStr = getValStr(Combos.COMBO_TIPOS_MEDIO);
		if (codTipoMedioStr == null || codTipoMedioStr.equals("")) {
			Window.alert(MSJ_VALIDACION_TIPO_MEDIO_ALMACENAMIENTO);
			return;
		}
		long codigoTipoMedio = Long.parseLong((codTipoMedioStr));
		String tipoMedioAlmac = UtilConverter.getMapaTipoMedioaALm().get(codigoTipoMedio);

		String codMedioAlmacStr = getValStr(COMBO_MEDIO_ALMACENAMIENTO);
		if (codMedioAlmacStr == null || codMedioAlmacStr.equals("")) {
			Window.alert(MSJ_VALIDACION_MEDIO_ALMACENAMIENTO);
			return;
		}
		long codigoMedioAlmac = Long.parseLong((codMedioAlmacStr));
		String medioAlmac = UtilConverter.getMapaMedioaALm().get(codigoMedioAlmac);

		List<TablaCubicacionDTO> tablaCubicacionDTOBuscar = gridCubicacionMes.exportarGrid();
		for (TablaCubicacionDTO tablaCubicacionDTOB : tablaCubicacionDTOBuscar) {
			if (tablaCubicacionDTOB.getMedioAlmacenamiento().equals(medioAlmac)) {
				Window.alert(MSJ_VALIDACION_MEDIO_ALMACENAMIENTO_REPETIDO);
				return;
			}
		}

		tablaCubicacionDTO.setTipoMedioAlmacenamiento(tipoMedioAlmac);
		tablaCubicacionDTO.setMedioAlmacenamiento(medioAlmac);
		tablaCubicacionDTO.setCodigoMedioalmacenamiento(Integer.parseInt(codMedioAlmacStr));

		Double areaCresta = getValAsDouble(TXT_AREA_CRESTA);
		if (areaCresta == VALOR_INVALIDO_TEXFIELD_NUMERICO || areaCresta <= 0) {
			Window.alert(MSJ_VALIDACION_AREA_CRESTA);
			return;
		}
		tablaCubicacionDTO.setAreaCresta(areaCresta);

		Double areaPie = getValAsDouble(TXT_AREA_PIE);
		if (areaPie == VALOR_INVALIDO_TEXFIELD_NUMERICO || areaPie <= 0) {
			Window.alert(MSJ_VALIDACION_AREA_PIE);
			return;
		}
		tablaCubicacionDTO.setAreaPie(areaPie);

		Double difNivel = getValAsDouble(TXT_DIF_NIVEL);
		if (difNivel == VALOR_INVALIDO_TEXFIELD_NUMERICO || difNivel <= 0) {
			Window.alert(MSJ_VALIDACION_DIF_NIVEL);
			return;
		}
		tablaCubicacionDTO.setDiferenciaNivel(difNivel);

		Double areaOcupada = getValAsDouble(TXT_AREA_OCUPADA);
		if (areaOcupada == VALOR_INVALIDO_TEXFIELD_NUMERICO || areaOcupada <= 0) {
			Window.alert(MSJ_VALIDACION_AREA_OCUPADA);
			return;
		}
		tablaCubicacionDTO.setAreaOcupada(areaOcupada);

		// Variables Locales a usar para Validaciones

		// Variable Altura Libre Promedio
		Double altLibreProm = 0D;
		// Codigo del Producto
		long codProd = Long.parseLong(codProductoStr);
		// Id del Producto CLK-I
		long prodClk1 = Long.parseLong(mapaPropiedades.get(PROD_CLKI));
		// Id del Producto CLK-V
		long prodClk5 = Long.parseLong(mapaPropiedades.get(PROD_CLKV));
		// Id del tipo de Medio de Almacenamiento SILO
		long codigoTMA_SILO = Long.parseLong(mapaPropiedades.get(TMA_SILO));
		// Id del tipo de Medio de Almacenamiento FIGURAS
		long codigoTMA_FIGURAS = Long.parseLong(mapaPropiedades.get(TMA_FIGURAS));
		// Id del Medio de Almacenamiento Fig1
		long codigoMA_FIG1 = Long.parseLong(mapaPropiedades.get(MA_FIG1));
		// Id del Medio de Almacenamiento Fig2
		long codigoMA_FIG2 = Long.parseLong(mapaPropiedades.get(MA_FIG2));
		// Id del Medio de Almacenamiento Fig3
		long codigoMA_FIG3 = Long.parseLong(mapaPropiedades.get(MA_FIG3));
		// Id del Medio de Almacenamiento Fig4
		long codigoMA_FIG4 = Long.parseLong(mapaPropiedades.get(MA_FIG4));

		// Si el Tipo del Medio de Almacenamiento es Silo
		if (codigoTipoMedio == codigoTMA_SILO) {
			if ((codProd == prodClk1) || (codProd == prodClk5)) {
				altLibreProm = getValAsDouble(TXT_ALT_LADO_PROMEDIO);
			} else {
				altLibreProm = getValAsDoubleNullable(TXT_ALT_LADO_PROMEDIO);
			}
			if (altLibreProm == null || (altLibreProm == VALOR_INVALIDO_TEXFIELD_NUMERICO || altLibreProm <= 0)) {
				Window.alert(MSJ_VALIDACION_ALT_LADO_PROM);
				return;
			}
			// Calculo del volumen para tipo de medio SILO

			Double volumen;
			if ((codProd == prodClk1) || (codProd == prodClk5)) {
				volumen = 1500D + altLibreProm.doubleValue() * 380D;
			} else {
				volumen = getValAsDouble(TXT_VOLUMEN);
			}
			if (volumen == VALOR_INVALIDO_TEXFIELD_NUMERICO || volumen <= 0) {
				Window.alert(MSJ_VALIDACION_VOLUMEN);
				return;
			}

			tablaCubicacionDTO.setVolumen(volumen);
			tablaCubicacionDTO.setAlturaLibrePromedio(altLibreProm);

			Double relacion = getValAsDoubleNullable(TXT_RELACION);
			if (relacion != null && (relacion == VALOR_INVALIDO_TEXFIELD_NUMERICO || relacion <= 0)) {
				Window.alert(MSJ_VALIDACION_RELACION);
				return;
			}
			tablaCubicacionDTO.setRelacionCubicacion(relacion);

			Double altLadoCarbon = getValAsDoubleNullable(TXT_ALT_LADO_CARBON);
			if (altLadoCarbon != null && (altLadoCarbon == VALOR_INVALIDO_TEXFIELD_NUMERICO || altLadoCarbon <= 0)) {
				Window.alert(MSJ_VALIDACION_ALT_LADO_CARBON);
				return;
			}
			tablaCubicacionDTO.setAlturaLadoCarboni(altLadoCarbon);

			Double altLadoCentral = getValAsDoubleNullable(TXT_ALT_CENTRAL);
			if (altLadoCentral != null && (altLadoCentral == VALOR_INVALIDO_TEXFIELD_NUMERICO || altLadoCentral <= 0)) {
				Window.alert(MSJ_VALIDACION_ALT_CENTRAL);
				return;
			}
			tablaCubicacionDTO.setAlturaCentral(altLadoCentral);

			Double altLadoClinker = getValAsDoubleNullable(TXT_ALT_LADO_CLINKER);
			if (altLadoClinker != null && (altLadoClinker == VALOR_INVALIDO_TEXFIELD_NUMERICO || altLadoClinker <= 0)) {
				Window.alert(MSJ_VALIDACION_ALT_LADO_CLINKER);
				return;
			}
			tablaCubicacionDTO.setAlturaLadoClinker(altLadoClinker);

			Double unidad = getValAsDoubleNullable(TXT_UNIDAD_KG);
			if (unidad != null && (unidad == VALOR_INVALIDO_TEXFIELD_NUMERICO || unidad <= 0)) {
				Window.alert(MSJ_VALIDACION_UNIDAD);
				return;
			}
			tablaCubicacionDTO.setUnidad(unidad);
			// Si el Tipo del Medio de Almacenamiento es Figuras y el Medio es
			// Fig1
		} else if ((codigoTipoMedio == codigoTMA_FIGURAS) && (codigoMedioAlmac == codigoMA_FIG1)) {
			// Se calcula el Volumen de la Figura Piramide Truncada
			Double volumen = (difNivel / 3) * (areaCresta + areaPie + Math.sqrt(areaCresta * areaPie));
			tablaCubicacionDTO.setVolumen(volumen);
			// Si el Tipo del Medio de Almacenamiento es Figuras y el Medio es
			// Fig2
		} else if ((codigoTipoMedio == codigoTMA_FIGURAS) && (codigoMedioAlmac == codigoMA_FIG2)) {
			// Se calcula el Volumen de la Figura Cilindro Truncado
			Double volumen = Math.PI * (difNivel / 3) * (Math.pow(areaPie, 2) + Math.pow(areaCresta, 2) + (areaPie * areaCresta));
			tablaCubicacionDTO.setVolumen(volumen);
			// Si el Tipo del Medio de Almacenamiento es Figuras y el Medio es
			// Fig3
		} else if ((codigoTipoMedio == codigoTMA_FIGURAS) && (codigoMedioAlmac == codigoMA_FIG3)) {
			// Se calcula el Volumen de la Figura Cono
			Double volumen = (Math.PI * Math.pow(areaPie, 2) * difNivel) / 3;
			tablaCubicacionDTO.setVolumen(volumen);
			// Si el Tipo del Medio de Almacenamiento es Figuras y el Medio es
			// Fig4
		} else if ((codigoTipoMedio == codigoTMA_FIGURAS) && (codigoMedioAlmac == codigoMA_FIG4)) {
			// Se calcula el Volumen de la Figura Area Media
			Double volumen = ((areaCresta + areaPie) / 2) * difNivel;
			tablaCubicacionDTO.setVolumen(volumen);
		} else {
			Double volumen = getValAsDouble(TXT_VOLUMEN);
			if (volumen == VALOR_INVALIDO_TEXFIELD_NUMERICO || volumen <= 0) {
				Window.alert(MSJ_VALIDACION_VOLUMEN);
				return;
			}
			tablaCubicacionDTO.setVolumen(volumen);
		}

		String observaciones = getValStr(TXT_OBSERVACIONES);
		tablaCubicacionDTO.setObservacionCubicacion(observaciones);

		int codProducto = Integer.parseInt((codProductoStr));
		tablaCubicacionDTO.setCodigoProducto(codProducto);

		String codEstadoStr = getValStrFromformularioCombos(COMBO_ESTADO);
		if (codEstadoStr == null || codEstadoStr.equals("")) {
			Window.alert(MSJ_VALIDACION_COMBO_ESTADO);
			return;
		}
		int codEstado = Integer.parseInt((codEstadoStr));
		tablaCubicacionDTO.setCodigoEstado(codEstado);

		/**
		 * Agregado por Jordan
		 */
		Double densidad = getValAsDoubleNullable(TXT_DENSIDAD);
		if (densidad != null && (densidad == VALOR_INVALIDO_TEXFIELD_NUMERICO || densidad <= 0)) {
			// Valor por defecto si no tiene densidad
			densidad = 1.0;
		}
		tablaCubicacionDTO.setDensidadMedioAlmc(densidad);

		Double humedad = getValAsDoubleNullable(TXT_HUMEDAD_PONDERADA);
		if (humedad != null && (humedad == VALOR_INVALIDO_TEXFIELD_NUMERICO || humedad <= 0)) {
			// Valor por defecto si no tiene densidad
			humedad = 0.0;
		}
		tablaCubicacionDTO.setHumedadPonderada(humedad);
		gridCubicacionMes.agregarRegistro(tablaCubicacionDTO);
		limpiarTextFieldsFormularioDatos();
	}

	/**
	 * Metodo que crea la banda de iconos del menu superior
	 */
	private void dibujarBandaIconos() {

		// Refrescar
		Img imagenRefrescar = new Img("", 18, 20);
		imagenRefrescar.setName(NOMBRE_IMAGEN_REFRESCAR);
		imagenRefrescar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRefrescar();
			}
		});
		Label etiquetaRefrescar = new Label(REFRESCAR);
		etiquetaRefrescar.setCursor(Cursor.HAND);
		etiquetaRefrescar.setHeight(15);
		etiquetaRefrescar.setID(NOMBRE_ETIQUETA_REFRESCAR);
		etiquetaRefrescar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRefrescar();
			}
		});
		RootPanel.get(NOMBRE_ESTILO_REFRESCAR).add(imagenRefrescar);
		RootPanel.get(NOMBRE_ESTILO_REFRESCAR).setStyleName(NOMBRE_ESTILO_REFRESCAR);
		RootPanel.get(NOMBRE_ESTILO_REFRESCAR).add(etiquetaRefrescar);

		// Grabar
		imagenGrabar = new Img("", 18, 20);
		imagenGrabar.setName(NOMBRE_IMAGEN_GRABAR);
		imagenGrabar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGrabar();
			}
		});
		etiquetaGrabar = new Label(GRABAR);
		etiquetaGrabar.setCursor(Cursor.HAND);
		etiquetaGrabar.setHeight(15);
		etiquetaGrabar.setID(NOMBRE_ETIQUETA_GRABAR);
		etiquetaGrabar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGrabar();
			}
		});

		RootPanel.get(NOMBRE_ESTILO_GRABAR).add(imagenGrabar);
		RootPanel.get(NOMBRE_ESTILO_GRABAR).setStyleName(NOMBRE_ESTILO_GRABAR);
		RootPanel.get(NOMBRE_ESTILO_GRABAR).add(etiquetaGrabar);

		// Consultar
		Img imagenConsultar = new Img("", 18, 20);
		imagenConsultar.setName(NOMBRE_IMAGEN_CONSULTAR);
		imagenConsultar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRegresar();
			}
		});
		Label etiquetaConsultar = new Label(CONSULTAR);
		etiquetaConsultar.setCursor(Cursor.HAND);
		etiquetaConsultar.setHeight(15);
		etiquetaConsultar.setID(NOMBRE_ETIQUETA_CONSULTAR);
		etiquetaConsultar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRegresar();
			}
		});

		RootPanel.get(NOMBRE_ESTILO_CONSULTAR).add(imagenConsultar);
		RootPanel.get(NOMBRE_ESTILO_CONSULTAR).setStyleName(NOMBRE_ESTILO_CONSULTAR);
		RootPanel.get(NOMBRE_ESTILO_CONSULTAR).add(etiquetaConsultar);

	}

	private void limpiarTextFieldsFormularioDatos() {
		formularioDatosCubicacionProducto.getField(TXT_AREA_CRESTA).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_AREA_PIE).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_DIF_NIVEL).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_VOLUMEN).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_OBSERVACIONES).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_AREA_OCUPADA).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_RELACION).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_ALT_CENTRAL).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_ALT_LADO_CARBON).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_ALT_LADO_CLINKER).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_ALT_LADO_PROMEDIO).clearValue();
		formularioDatosCubicacionProducto.getField(TXT_UNIDAD_KG).clearValue();

	}

	/**
	 * Metodo que se ejecuta al hacer click en Grabar para registrar la
	 * produccion semanal
	 */
	private void eventoClickGrabar() {
		Date fechaCubicacion = (Date) fechaItem.getValue();

		String codLineanegocioStr = getValStrFromformularioCombos(Combos.COMBO_LINEA_NEGOCIO);
		if (codLineanegocioStr == null || codLineanegocioStr.equals("")) {
			Window.alert(MSJ_VALIDACION_COMBO_LINEA_NEGOCIO);
			return;
		}
		int codigoLineaNegocio = Integer.parseInt((codLineanegocioStr));

		String codProcesoStr = getValStrFromformularioCombos(Combos.COMBO_PROCESO);
		if (codProcesoStr == null || codProcesoStr.equals("")) {
			Window.alert(MSJ_VALIDACION_COMBO_PROCESO);
			return;
		}
		int codigoProceso = Integer.parseInt((codProcesoStr));

		String codProductoStr = getValStrFromformularioCombos(Combos.COMBO_PRODUCTO);
		if (codProductoStr == null || codProductoStr.equals("")) {
			Window.alert(MSJ_VALIDACION_COMBO_PRODUCTO);
			return;
		}

		int codigoProducto = Integer.parseInt((codProductoStr));

		mostrarBarraProgreso();

		servicioComunicacion.validarSiExisteCubicacionProducto(codigoLineaNegocio, codigoProducto, codigoProceso,
				fechaCubicacion, new AsyncCallback<Boolean>() {

					public void onFailure(Throwable throwable) {
						ocultarBarraProgreso();
						Window.alert(MENSAJ_ERROR);
					}

					public void onSuccess(Boolean existe) {
						if (existe) {
							Window.alert(CONSTANTES.cubicacionRegistrada());
							ocultarBarraProgreso();
							return;
						}

						if (gridCubicacionMes.getTotalRows() <= 0) {
							Window.alert(CONSTANTES.noHaRegistradoNingunaCubicacion());
							ocultarBarraProgreso();
							return;
						}

						List<TablaCubicacionDTO> listaCubicaciones = gridCubicacionMes.exportarGrid();

						servicioComunicacion.registrarCubicacion(listaCubicaciones, new AsyncCallback<String>() {

							public void onFailure(Throwable arg0) {
								mostrarMensajeResultado(ConstantesGWT.MENSAJE_ERROR_REGISTRO_CUBICACION_EXITOSA, ERROR);
							}

							public void onSuccess(String resultado) {
								Window.alert(resultado);
								if (resultado.equals(ConstantesGWT.OPERACION_EXITOSA)) {
									resultLayout.destroy();
									mostrarMensajeResultado(ConstantesGWT.MENSAJE_REGISTRO_CUBICACION_EXITOSA, EXITO);
								} else {
									resultLayout.destroy();
									mostrarMensajeResultado(ConstantesGWT.MENSAJE_ERROR_REGISTRO_CUBICACION_EXITOSA, ERROR);
								}
								ocultarBarraProgreso();
							}
						});

						limpiarTextFieldsFormularioDatos();
						formularioDatosCubicacionProducto.getField(COMBO_MEDIO_ALMACENAMIENTO).clearValue();
						formularioDatosCubicacionProducto.getField(Combos.COMBO_TIPOS_MEDIO).clearValue();
						gridCubicacionMes.limpiarGrid();
					}
				});

	}

	/**
	 * Metodo para refrescar la pagina
	 */
	private void eventoClickRefrescar() {
		formularioCombos.removeFromParent();
		formularioCombos = initFormularioCombos();
		searchFormPanel.addChild(formularioCombos);

		formularioDatosCubicacionProducto.removeFromParent();
		initFormularioDatosCubicacionProducto();
		addFormPanel.addChild(formularioDatosCubicacionProducto);

		gridCubicacionMes.limpiarGrid();
		resultLayout.destroy();
	}

	/**
	 * Metodo para regresar a la pagina de consulta
	 */
	private void eventoClickRegresar() {
		Window.Location.assign(ConstantesGWT.LINK_CONSULTAR_CUBICACIONES_MES);
	}

	/**
	 * Muestra Mensaje de Resultado Exitoso o Error
	 * 
	 * @param mensaje
	 * @param nombreClaseEstilo
	 */
	private void mostrarMensajeResultado(String mensaje, String nombreClaseEstilo) {
		resultLayout = new VLayout();
		resultLayout.setStyleName("paddingTop");
		resultLayout.setWidth(700);
		resultLayout.addMember(crearMensajeExitosoLabel(mensaje, nombreClaseEstilo));
		RootPanel.get("formularioTablaCubicacion").add(resultLayout);
	}

	/**
	 * Crea el Label para el Mensaje de Resultado Exitoso o Error
	 * 
	 * @param mensajeLabel
	 * @param nombreClaseEstilo
	 * @return
	 */
	public Canvas crearMensajeExitosoLabel(String mensajeLabel, String nombreClaseEstilo) {
		Label label = new Label();
		label.setHeight(15);
		label.setStyleName(nombreClaseEstilo);
		label.setContents(mensajeLabel);
		return label;
	}

	// Getters y setters
	public DynamicForm getFormularioCombos() {
		return formularioCombos;
	}

	public DynamicForm getFormularioDatosCubicacionProducto() {
		return formularioDatosCubicacionProducto;
	}

	public void setFormularioDatosCubicacionProducto(DynamicForm formularioDatosCubicacionProducto) {
		this.formularioDatosCubicacionProducto = formularioDatosCubicacionProducto;
	}

	public DynamicForm getFormularioTablaCubicacion() {
		return formularioTablaCubicacion;
	}

	public void setFormularioTablaCubicacion(DynamicForm formularioTablaCubicacion) {
		this.formularioTablaCubicacion = formularioTablaCubicacion;
	}

	public Label getEtiquetaResultados() {
		return etiquetaResultados;
	}

	public void setEtiquetaResultados(Label etiquetaResultados) {
		this.etiquetaResultados = etiquetaResultados;
	}

	public Label getEtiquetaEspaciadora() {
		return etiquetaEspaciadora;
	}

	public void setEtiquetaEspaciadora(Label etiquetaEspaciadora) {
		this.etiquetaEspaciadora = etiquetaEspaciadora;
	}

}
