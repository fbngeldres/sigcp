package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DivisionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoNotificacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoRegistroMedicionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.SociedadDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TableroControlDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TipoMedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.UnidadDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.DateItemSelectorFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: Combos.java
 * Modificado: Feb 25, 2010 9:25:19 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

/**
 * Clase utilitaria de carga de combos de entidades maestras utilizada en varios
 * casos de uso
 */

public class Combos implements ClienteServicioGwt {

	private static final String TITULO_SOCIEDAD = "Sociedad";
	private static final String TITULO_DIVISION = "Divisi&oacute;n";
	private static final String TITULO_UNIDAD = "Unidad";
	private static final String TITULO_LINEA_DE_NEGOCIO = "L&iacute;nea de Negocio";
	private static final String TITULO_PROCESO = "Proceso";
	private static final String TITULO_ALMACENAMIENTO = "Tipo Medio";
	private static final String TITULO_ESTADO_REGISTRO_MEDICION = "Estado";
	private static final String TITULO_ESTADO = "Estado";
	private static final String TITULO_PRODUCTO = "Producto";
	private static final String TITULO_ORDEN = "Orden";
	private static final String TITULO_PUESTO_TRABAJO = "Puesto de Trabajo";
	private static final String TITULO_TABLERO_CONTROL = "Tablero de Control";
	private static final String TITULO_VARIABLES = "Variables";
	private static final String TITULO_MES = "Mes";
	private static final String TITULO_ANIO = "A&ntilde;o";
	private static final String TITULO_GRUPO_AJUSTE = "Grupo Ajuste";

	public static final String COMBO_ESTADO_PRODUCCION_SEMANAL = "estado";
	public static final String COMBO_TIPOS_MEDIO = "tiposMedio";
	public static final String COMBO_ESTADO_REGISTRO_MEDICION = "estadoRegistroMedicion";
	public static final String COMBO_ESTADO = "estado";
	public static final String COMBO_GRUPO_AJUSTE = "grupoAjuste";
	public static final String COMBO_MES = "mes";
	public static final String COMBO_PROCESO = "proceso";
	public static final String COMBO_LINEA_NEGOCIO = "lineaNegocio";
	public static final String COMBO_DIA_PRODUCCION = "diaProduccion";
	public static final String COMBO_SOCIEDAD = "sociedad";
	public static final String COMBO_UNIDAD = "unidad";
	public static final String COMBO_DIVISION = "division";
	public static final String COMBO_PRODUCTO = "producto";
	public static final String COMBO_ORDEN = "orden";
	public static final String COMBO_ANNO = "anno";
	public static final String COMBO_PUESTO_TRABAJO = "puestoTrabajo";
	public static final String COMBO_TABLERO_CONTROL = "tableroControl";
	public static final String COMBO_VARIABLES = "variables";

	/**
	 * Metodo que retorna el combo de meses cargado
	 * 
	 * @return
	 */
	public static SelectItem cargarComboAnnos() {

		SelectItem annoItem = new SelectItem();
		annoItem.setName(COMBO_ANNO);
		annoItem.setTitle(TITULO_ANIO);
		annoItem.setValueMap(ConstantesGWT.getAnnos());
		annoItem.setValue(ConstantesGWT.getAnnoActual());

		return annoItem;
	}

	/**
	 * Metodo que retorna el combo de meses cargado
	 * 
	 * @return
	 */
	public static SelectItem cargarComboMeses() {
		SelectItem mesesItem = new SelectItem();
		mesesItem.setName(COMBO_MES);
		mesesItem.setTitle(TITULO_MES);
		mesesItem.setAllowEmptyValue(true);
		mesesItem.setValueMap(UtilConverter.convertirStringEnumToStringArray(ConstantesGWT.MESES.values()));

		mesesItem.setAllowEmptyValue(false);
		mesesItem.setValue(ConstantesGWT.MESES.values()[ConstantesGWT.getMesActual() - 1].toString());

		return mesesItem;
	}

	/**
	 * Metodo para cargar los estados
	 */
	public static SelectItem cargarComboEstados() {

		SelectItem estadoItem = new SelectItem();
		estadoItem.setName(COMBO_ESTADO);
		estadoItem.setTitle(TITULO_ESTADO);
		estadoItem.setDisabled(true);
		return estadoItem;
	}

	/**
	 * Metodo para cargar los Meses del periodo contable
	 */
	public static SelectItem cargarComboMesesPeriodoContable() {

		SelectItem estadoItem = new SelectItem();
		estadoItem.setName(COMBO_MES);
		estadoItem.setTitle(TITULO_MES);
		return estadoItem;
	}

	/**
	 * Metodo para cargar los A�os del periodo contable
	 */
	public static SelectItem cargarComboAniosPeriodoContable() {

		SelectItem estadoItem = new SelectItem();
		estadoItem.setName(COMBO_ANNO);
		estadoItem.setTitle(TITULO_ANIO);
		return estadoItem;
	}

	/**
	 * Metodo para cargar los A�os del periodo contable
	 */
	public static SelectItem cargarComboGrupoAjuste() {

		SelectItem grupoAjusteItem = new SelectItem();
		grupoAjusteItem.setName(COMBO_GRUPO_AJUSTE);
		grupoAjusteItem.setTitle(TITULO_GRUPO_AJUSTE);
		return grupoAjusteItem;
	}

	/**
	 * Metodo que retorna el combo de Estados de registro de medicion cargado
	 * 
	 * @return
	 */
	public static SelectItem cargarComboEstadosRegistroMedicion() {
		final SelectItem estadoRegistroMedicionItem = new SelectItem();
		estadoRegistroMedicionItem.setName(COMBO_ESTADO_REGISTRO_MEDICION);
		estadoRegistroMedicionItem.setTitle(TITULO_ESTADO_REGISTRO_MEDICION);
		estadoRegistroMedicionItem.setAllowEmptyValue(true);
		estadoRegistroMedicionItem.setDisabled(false);
		// estadoRegistroMedicionItem.setWidth(60);

		servicioComunicacion.cargarEstadosRegistroMedicion(new AsyncCallback<List<EstadoRegistroMedicionDTO>>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(

			List<EstadoRegistroMedicionDTO> estadosRegistroMedicionDTO) {
				LinkedHashMap<String, String> mapaEstadoRegistroMedicion = UtilConverter
						.obtenerMapaEstadoRegistroMedicionDTO(estadosRegistroMedicionDTO);
				estadoRegistroMedicionItem.setValueMap(mapaEstadoRegistroMedicion);

				// Creamos el objeto callback para asignarlo en la
				// llamada
				AsyncCallback<String> callback = new AsyncCallback<String>() {

					public void onFailure(Throwable arg0) {
						Window.alert(ConstantesGWT.SERVER_ERROR);
					}

					public void onSuccess(String estadoProduccionSemanalGenerada) {
						estadoRegistroMedicionItem.setValue(estadoProduccionSemanalGenerada);
					}
				};

				// Cargamos el estado de produccion semanal generada y
				// deshabilitamos el combo
				servicioComunicacion.obtenerPropiedadPorClave(ConstantesGWT.CODIGO_ESTADO_REGISTRO_MEDICION_GENERADA, callback);

			}
		});

		return estadoRegistroMedicionItem;
	}

	/**
	 * Metodo que retorna el combo de Tipos de medio de almancenamiento cargado
	 * 
	 * @return
	 */
	public static SelectItem cargarComboTiposMedioAlmacenamiento() {
		final SelectItem tipoMedioItem = new SelectItem();
		tipoMedioItem.setName(COMBO_TIPOS_MEDIO);
		tipoMedioItem.setTitle(TITULO_ALMACENAMIENTO);
		tipoMedioItem.setAllowEmptyValue(true);
		tipoMedioItem.setDisabled(false);

		servicioComunicacion.cargarTiposMediosAlmacenamiento(new AsyncCallback<List<TipoMedioAlmacenamientoDTO>>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<TipoMedioAlmacenamientoDTO> mediosDTO) {
				LinkedHashMap<String, String> mapaTipoMedios = UtilConverter.obtenerMapaTipoMediosAlmacenamientoDTO(mediosDTO);
				tipoMedioItem.setValueMap(mapaTipoMedios);

			}
		});

		return tipoMedioItem;
	}

	/**
	 * Metodo para actualizar los procesos, segun la linea de negocio
	 * seleccionada
	 */
	public static SelectItem cargarComboProcesos() {

		SelectItem procesoItem = new SelectItem();
		procesoItem.setName(COMBO_PROCESO);
		procesoItem.setTitle(TITULO_PROCESO + ConstantesGWT.CAMPO_OBLIGATORIO);
		procesoItem.setAllowEmptyValue(true);
		procesoItem.setDisabled(true);
		return procesoItem;
	}

	/**
	 * Metodo para actualizar lineas de negocio, segun la unidad seleccionada
	 */
	public static SelectItem cargarComboLineasNegocio() {
		final SelectItem lineaNegocioItem = new SelectItem();
		lineaNegocioItem.setName(COMBO_LINEA_NEGOCIO);
		lineaNegocioItem.setTitle(TITULO_LINEA_DE_NEGOCIO + ConstantesGWT.CAMPO_OBLIGATORIO);
		lineaNegocioItem.setAllowEmptyValue(true);

		// realizamos la asignación por defecto de la unidad
		servicioComunicacion.valorPorDefectoUnidad(new AsyncCallback<String>() {

			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					// Window.alert(CONSTANTES.errorComunicacionServidor());
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}
			}

			public void onSuccess(String codigoUnidad) {

				if (codigoUnidad != null && codigoUnidad.trim().length() > 0) {
					servicioComunicacion.cargarLineasNegocioPorUnidad(new Long(codigoUnidad),
							new AsyncCallback<List<LineaNegocioDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<LineaNegocioDTO> lineasDTO) {

									LinkedHashMap<String, String> mapaLineaNegocio = UtilConverter
											.obtenerMapaLineaNegocioDTO(lineasDTO);
									lineaNegocioItem.setValueMap(mapaLineaNegocio);

									// realizamos la asignación por defecto de
									// la unidad
									servicioComunicacion.valorPorDefectoUnidad(new AsyncCallback<String>() {

										public void onFailure(Throwable throwable) {
											if (throwable instanceof ServicioGWTGlobalException) {
												ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
												Window.alert(e.getMensaje());
											} else {
												// Window.alert(CONSTANTES.errorComunicacionServidor());
												Window.alert(ConstantesGWT.SERVER_ERROR);
											}
										}

										public void onSuccess(String codigoUnidad) {

											if (codigoUnidad == null || codigoUnidad.trim().length() == 0) {
												lineaNegocioItem.setDisabled(true);
											}
										}
									});
								}
							});

				}
			}
		});

		return lineaNegocioItem;
	}

	/**
	 * Metodo para actualizar combo de unidades, segun la sociedad seleccionada
	 */
	public static SelectItem cargarComboUnidades() {
		final SelectItem unidadItem = new SelectItem();
		unidadItem.setName(COMBO_UNIDAD);
		unidadItem.setTitle(TITULO_UNIDAD + ConstantesGWT.CAMPO_OBLIGATORIO);
		unidadItem.setAllowEmptyValue(true);

		// realizamos la asignación por defecto de la sociedad
		servicioComunicacion.valorPorDefectoSociedad(new AsyncCallback<String>() {

			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					// Window.alert(CONSTANTES.errorComunicacionServidor());
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}
			}

			public void onSuccess(String codigoSociedad) {

				if (codigoSociedad != null && codigoSociedad.trim().length() > 0) {
					servicioComunicacion.cargarUnidadesPorSociedad(new Long(codigoSociedad),
							new AsyncCallback<List<UnidadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<UnidadDTO> unidadesDTO) {

									LinkedHashMap<String, String> mapaUnidad = UtilConverter.obtenerMapaUnidadDTO(unidadesDTO);
									unidadItem.setValueMap(mapaUnidad);

									// realizamos la asignación por defecto de
									// la unidad
									servicioComunicacion.valorPorDefectoUnidad(new AsyncCallback<String>() {

										public void onFailure(Throwable throwable) {
											if (throwable instanceof ServicioGWTGlobalException) {
												ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
												Window.alert(e.getMensaje());
											} else {
												// Window.alert(CONSTANTES.errorComunicacionServidor());
												Window.alert(ConstantesGWT.SERVER_ERROR);
											}
										}

										public void onSuccess(String codigoUnidad) {

											if (codigoUnidad != null && codigoUnidad.trim().length() > 0) {
												unidadItem.setDefaultValue(codigoUnidad);
											} else {
												unidadItem.setDisabled(true);
											}
										}
									});
								}
							});

				}
			}
		});

		return unidadItem;
	}

	/**
	 * Metodo que retorna el combo de Divisiones cargado
	 * 
	 * @return
	 */
	public static SelectItem cargarComboDivisiones() {

		final SelectItem divisionItem = new SelectItem();
		divisionItem.setName(COMBO_DIVISION);
		divisionItem.setTitle(TITULO_DIVISION + ConstantesGWT.CAMPO_OBLIGATORIO);
		divisionItem.setAllowEmptyValue(true);
		servicioComunicacion.cargarDivisiones(new AsyncCallback<List<DivisionDTO>>() {
			public void onFailure(Throwable arg0) {
				arg0.printStackTrace();
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<DivisionDTO> divisionesDTO) {

				LinkedHashMap<String, String> mapaDivision = UtilConverter.obtenerMapaDivisionDTO(divisionesDTO);
				divisionItem.setValueMap(mapaDivision);

				// realizamos la asignación por defecto de la división
				servicioComunicacion.valorPorDefectoDivision(new AsyncCallback<String>() {

					public void onFailure(Throwable throwable) {
						if (throwable instanceof ServicioGWTGlobalException) {
							ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
							Window.alert(e.getMensaje());
						} else {
							// Window.alert(CONSTANTES.errorComunicacionServidor());
							Window.alert(ConstantesGWT.SERVER_ERROR);
						}
					}

					public void onSuccess(String codigoDivision) {

						if (codigoDivision != null && codigoDivision.trim().length() > 0) {
							divisionItem.setDefaultValue(codigoDivision);
						}
					}
				});

			}
		});
		return divisionItem;
	}

	/**
	 * Metodo para actualizar combo de sociedades, segun la division
	 * seleccionada
	 */
	public static SelectItem cargarComboSociedades() {
		final SelectItem sociedadItem = new SelectItem();
		sociedadItem.setName(COMBO_SOCIEDAD);
		sociedadItem.setTitle(TITULO_SOCIEDAD + ConstantesGWT.CAMPO_OBLIGATORIO);
		sociedadItem.setAllowEmptyValue(true);

		// realizamos la asignación por defecto de la división
		servicioComunicacion.valorPorDefectoDivision(new AsyncCallback<String>() {

			public void onFailure(Throwable throwable) {
				 throwable.printStackTrace();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					// Window.alert(CONSTANTES.errorComunicacionServidor());
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}
			}

			public void onSuccess(String codigoDivision) {

				if (codigoDivision != null && codigoDivision.trim().length() > 0) {
					servicioComunicacion.cargarSociedadesPorDivision(new Long(codigoDivision),
							new AsyncCallback<List<SociedadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<SociedadDTO> sociedadesDTO) {

									LinkedHashMap<String, String> mapaSociedad = UtilConverter
											.obtenerMapaSociedadDTO(sociedadesDTO);
									sociedadItem.setValueMap(mapaSociedad);

									// realizamos la asignación por defecto de
									// la sociedad
									servicioComunicacion.valorPorDefectoSociedad(new AsyncCallback<String>() {

										public void onFailure(Throwable throwable) {
											if (throwable instanceof ServicioGWTGlobalException) {
												ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
												Window.alert(e.getMensaje());
											} else {
												// Window.alert(CONSTANTES.errorComunicacionServidor());
												Window.alert(ConstantesGWT.SERVER_ERROR);
											}
										}

										public void onSuccess(String codigoSociedad) {

											if (codigoSociedad != null && codigoSociedad.trim().length() > 0) {
												sociedadItem.setDefaultValue(codigoSociedad);
											} else {
												sociedadItem.setDisabled(true);
											}
										}
									});
								}
							});

				}
			}
		});

		return sociedadItem;
	}

	/**
	 * Metodo para actualizar combo de tableros de Control
	 */
	public static SelectItem cargarComboTablerosControl() {

		final SelectItem tablerosControlItem = new SelectItem();
		tablerosControlItem.setName(COMBO_TABLERO_CONTROL);
		tablerosControlItem.setTitle(TITULO_TABLERO_CONTROL);
		tablerosControlItem.setAllowEmptyValue(false);
		tablerosControlItem.setDisabled(true);
		return tablerosControlItem;
	}

	/**
	 * Metodo para actualizar lineas de negocio, segun la unidad seleccionada
	 */
	public static SelectItem cargarComboProducto() {

		SelectItem productoItem = new SelectItem();
		productoItem.setName(COMBO_PRODUCTO);
		productoItem.setTitle(TITULO_PRODUCTO + ConstantesGWT.CAMPO_OBLIGATORIO);
		productoItem.setAllowEmptyValue(true);
		productoItem.setDisabled(true);
		return productoItem;
	}

	/**
	 * Metodo para cargar ordenes de produccion
	 */
	public static SelectItem cargarComboOrden() {

		SelectItem ordenItem = new SelectItem();

		ordenItem.setName(COMBO_ORDEN);
		ordenItem.setTitle(TITULO_ORDEN);
		return ordenItem;
	}

	public static SelectItem cargarComboPuestoTrabajo() {

		final SelectItem puestoTrabajoItem = new SelectItem();

		puestoTrabajoItem.setName(COMBO_PUESTO_TRABAJO);
		puestoTrabajoItem.setTitle(TITULO_PUESTO_TRABAJO + ConstantesGWT.CAMPO_OBLIGATORIO);
		puestoTrabajoItem.setDisabled(true);

		servicioComunicacion.cargarPuestosTrabajo(new AsyncCallback<List<PuestoTrabajoDTO>>() {
			public void onFailure(Throwable arg0) {
				arg0.printStackTrace();
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<PuestoTrabajoDTO> puestosDTO) {

				LinkedHashMap<String, String> mapaPuestoTrabajo = UtilConverter.obtenerMapaPuestoTrabajoDTO(puestosDTO);
				puestoTrabajoItem.setValueMap(mapaPuestoTrabajo);
			}
		});

		return puestoTrabajoItem;
	}

	/**
	 * Metodo para cargar puestos de trabajo segun el codigo del proceso
	 */
	public static SelectItem cargarComboPuestoTrabajPorProceso() {
		SelectItem puestosTrabajo = new SelectItem();
		puestosTrabajo.setName(COMBO_PUESTO_TRABAJO);
		puestosTrabajo.setTitle(TITULO_PUESTO_TRABAJO);
		puestosTrabajo.setAllowEmptyValue(true);
		puestosTrabajo.setDisabled(true);
		return puestosTrabajo;
	}

	/**
	 * Metodo para actualizar combo de Variables
	 */
	public static SelectItem cargarComboVariables() {

		final SelectItem variablesItem = new SelectItem();
		variablesItem.setName(COMBO_VARIABLES);
		variablesItem.setTitle(TITULO_VARIABLES);

		// Para cargar las variables en el combo
		LinkedHashMap<String, String> mapaVariables = new LinkedHashMap<String, String>();

		mapaVariables.put("produccion", "Produccion");
		mapaVariables.put("operacion", "Operacion");

		variablesItem.setValueMap(mapaVariables);
		variablesItem.setValue("produccion");
		variablesItem.setDisabled(true);

		return variablesItem;
	}

	public static SelectItem cargarEstadosNotificacion() {
		final SelectItem estadosItem = new SelectItem();
		estadosItem.setName("estadosNotificacion");
		estadosItem.setTitle("Estado");
		estadosItem.setAllowEmptyValue(true);
		estadosItem.setDisabled(false);
		// estadoRegistroMedicionItem.setWidth(60);

		servicioComunicacion.cargarEstadosNotificacion(new AsyncCallback<List<EstadoNotificacionDTO>>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<EstadoNotificacionDTO> estadosNotificacionDTO) {

				LinkedHashMap<String, String> mapaEstadoNotificacion = UtilConverter
						.obtenerMapaEstadoNotificacionDTO(estadosNotificacionDTO);
				estadosItem.setValueMap(mapaEstadoNotificacion);

				// Creamos el objeto callback para asignarlo en la
				// llamada
				AsyncCallback<String> callback = new AsyncCallback<String>() {

					public void onFailure(Throwable arg0) {
						Window.alert(ConstantesGWT.SERVER_ERROR);
					}

					public void onSuccess(String estadoNotificacionGenerada) {
						estadosItem.setValue(estadoNotificacionGenerada);
					}
				};

				// Cargamos el estado de produccion semanal generada y
				// deshabilitamos el combo
				servicioComunicacion.obtenerPropiedadPorClave(ConstantesGWT.CODIGO_ESTADO_NOTIFICACION_GENERADA, callback);
			}
		});
		return estadosItem;
	}

	/**
	 * Metodo que actualiza el combo de productos segun el proceso seleccionado
	 * 
	 * @param procesoItem
	 * @param productoItem
	 */
	public static void addProcesoItemChangeHandlerRegistro(SelectItem procesoItem, final SelectItem productoItem) {

		ChangeHandler changeHandler = new ChangeHandler() {

			public void onChange(ChangeEvent event) {

				String valorProceso = (String) event.getValue();

				if (valorProceso != null) {

					AsyncCallback<List<ProductoDTO>> asyncCallback = new AsyncCallback<List<ProductoDTO>>() {
						public void onFailure(Throwable arg0) {
							Window.alert(ConstantesGWT.SERVER_ERROR);
						}

						public void onSuccess(List<ProductoDTO> productoDTOs) {

							LinkedHashMap<String, String> mapaProductos = UtilConverter.obtenerValueMapProductos(productoDTOs);

							productoItem.setValueMap(mapaProductos);
							productoItem.setDisabled(false);
							productoItem.clearValue();
						}
					};

					servicioComunicacion.cargarProductoPorProceso(Long.parseLong(valorProceso), asyncCallback);
				} else {
					// Aqui seteamos el combo de procesos a null y lo
					// deshabilitamos...
					String cadena = null;
					productoItem.setDefaultValue(cadena);
					productoItem.clearValue();
					productoItem.setDisabled(true);
				}
			}
		};

		procesoItem.addChangeHandler(changeHandler);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * Metodo que carga el combo de productos segun el codigo del proceso
	 * 
	 * @param procesoItem
	 * @param productoItem
	 */
	public static SelectItem cargarComboProductosPorCodigoProceso(Long codigoProceso) {

		final SelectItem productoItem = cargarComboProducto();

		AsyncCallback<List<ProductoDTO>> asyncCallback = new AsyncCallback<List<ProductoDTO>>() {
			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<ProductoDTO> productoDTOs) {

				LinkedHashMap<String, String> mapaProductos = UtilConverter.obtenerValueMapProductos(productoDTOs);

				productoItem.setValueMap(mapaProductos);

				productoItem.setDisabled(false);
				productoItem.clearValue();
			}
		};

		servicioComunicacion.cargarProductoPorProceso(codigoProceso, asyncCallback);

		return productoItem;
	}

	public static SelectItem cargarComboOrdenesPorCodigoProceso(Long codigoProceso) {

		final SelectItem ordenItem = cargarComboProducto();

		AsyncCallback<List<ProductoDTO>> asyncCallback = new AsyncCallback<List<ProductoDTO>>() {
			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<ProductoDTO> productoDTOs) {

				LinkedHashMap<String, String> mapaProductos = UtilConverter.obtenerValueMapProductos(productoDTOs);

				ordenItem.setValueMap(mapaProductos);

				ordenItem.setDisabled(false);
				ordenItem.clearValue();
			}
		};

		servicioComunicacion.cargarProductoPorProceso(codigoProceso, asyncCallback);

		return ordenItem;
	}

	// //////////////////////////////////////////////////////////////////
	public static void addTableroControlItemChangeHandlerRegistro(SelectItem tablerosControlItem,
			final SelectItem puestosTrabajoItem) {
		tablerosControlItem.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				String valorTableroControl = (String) event.getValue();
				if (valorTableroControl != null) {
					servicioComunicacion.cargarPuestosTrabajoPorTableroControl(Long.parseLong(valorTableroControl),
							new AsyncCallback<List<PuestoTrabajoDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<PuestoTrabajoDTO> puestosTrabajo) {
									LinkedHashMap<String, String> mapaPuestoTrabajo = UtilConverter
											.obtenerMapaPuestoTrabajoDTO(puestosTrabajo);
									puestosTrabajoItem.setValueMap(mapaPuestoTrabajo);
									puestosTrabajoItem.setDisabled(false);
									puestosTrabajoItem.clearValue();
								}
							});
				} else { // Aqui seteamos el combo de procesos a null y lo
					// deshabilitamos...
					puestosTrabajoItem.clearValue();
					puestosTrabajoItem.setDisabled(true);
				}
			}
		});
	}

	public static void addLineaNegocioItemChangeHandlerRegistro(SelectItem lineaNegocioItem, final SelectItem procesoItem) {
		lineaNegocioItem.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				String valorLineaNegocio = (String) event.getValue();
				if (valorLineaNegocio != null) {
					AsyncCallback<List<ProcesoDTO>> callback = new AsyncCallback<List<ProcesoDTO>>() {
						public void onFailure(Throwable arg0) {
							Window.alert(ConstantesGWT.SERVER_ERROR);
							GWT.log("Error en combo Linea de Negocio", arg0);
						}

						public void onSuccess(List<ProcesoDTO> procesosDTO) {
							LinkedHashMap<String, String> mapaProceso = UtilConverter.obtenerMapaProcesoDTO(procesosDTO);
							procesoItem.setValueMap(mapaProceso);
							procesoItem.setDisabled(false);
							procesoItem.clearValue();
						}
					};

					servicioComunicacion.cargarProcesosPorLineaNegocio(Long.parseLong(valorLineaNegocio), callback);
				} else { // Aqui seteamos el combo de procesos a null y lo
					// deshabilitamos...
					String cadena = null;
					//resetCombosProceso(procesoItem);
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);
				}
			}
		});
	}

	public static void addLineaNegocioItemChangeHandlerRegistro(SelectItem lineaNegocioItem, final SelectItem procesoItem,
			final SelectItem productoItem) {
		lineaNegocioItem.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				String valorLineaNegocio = (String) event.getValue();
				if (valorLineaNegocio != null) {
					servicioComunicacion.cargarProcesosPorLineaNegocio(Long.parseLong(valorLineaNegocio),
							new AsyncCallback<List<ProcesoDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<ProcesoDTO> procesosDTO) {
									LinkedHashMap<String, String> mapaProceso = UtilConverter.obtenerMapaProcesoDTO(procesosDTO);
									procesoItem.setValueMap(mapaProceso);
									String defecto = null;
									procesoItem.setDefaultValue(defecto); 
									procesoItem.setDisabled(false);
									procesoItem.clearValue();
									
									productoItem.setDefaultValue(defecto);
									productoItem.clearValue();
									productoItem.setDisabled(true);
								}
							});
				} else { // Aqui seteamos el combo de procesos a null y lo
					// deshabilitamos...
					String cadena = null;
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);
					
					productoItem.setDefaultValue(cadena);
					productoItem.clearValue();
					productoItem.setDisabled(true);
				}
			}
		});
	}

	/**
	 * Metodo que actualiza el combo lineas de negocio segun la unidad
	 * seleccionada
	 * 
	 * @param unidadItem
	 * @param lineaNegocioItem
	 * @param procesoItem
	 */
	public static void addUnidadItemChangeHandlerRegistro(SelectItem unidadItem, final SelectItem lineaNegocioItem,
			final SelectItem procesoItem) {
		unidadItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorUnidad = (String) event.getValue();
				if (valorUnidad != null) {
					servicioComunicacion.cargarLineasNegocioPorUnidad(Long.parseLong(valorUnidad),
							new AsyncCallback<List<LineaNegocioDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<LineaNegocioDTO> lineasNegocioDTO) {
									LinkedHashMap<String, String> mapaLineaNegocio = UtilConverter
											.obtenerMapaLineaNegocioDTO(lineasNegocioDTO);
									lineaNegocioItem.setValueMap(mapaLineaNegocio);
									String defecto = null;
									lineaNegocioItem.setDefaultValue(defecto);
									lineaNegocioItem.setDisabled(false);
									lineaNegocioItem.clearValue();
									
									//resetCombosProceso(procesoItem);
									procesoItem.setDefaultValue(defecto);
									procesoItem.clearValue();
									procesoItem.setDisabled(true);
								}
							});
				} else { // Aqui seteamos los combo de lineaNeg y proceso a
					// null y lo deshabilitamos...
					String cadena = null;
					//resetCombosLineaNegocio(lineaNegocioItem);
					lineaNegocioItem.setDefaultValue(cadena);
					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);
					
					//resetCombosProceso(procesoItem);
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);
				}
			}
		});

	}

	public static void addUnidadItemChangeHandlerNotificacion(SelectItem unidadItem, final SelectItem procesoItem,
			final SelectItem lineaNegocioItem, final SelectItem puestoTrabajoItem, final SelectItem tableroControlItem,
			final SelectItem variablesItem) {
		ChangeHandler unidadChangeHandler = new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorUnidad = (String) event.getValue();
				if (valorUnidad != null) {
					Long codigoUnidad = Long.parseLong(valorUnidad);

					AsyncCallback<List<LineaNegocioDTO>> callbackLineaNegocio = new AsyncCallback<List<LineaNegocioDTO>>() {
						public void onFailure(Throwable arg0) {
							Window.alert(ConstantesGWT.SERVER_ERROR);
						}

						public void onSuccess(List<LineaNegocioDTO> lineasNegocioDTO) {
							// Para cargar Las lineas de Negocio
							LinkedHashMap<String, String> mapaLineaNegocio = UtilConverter
									.obtenerMapaLineaNegocioDTO(lineasNegocioDTO);
							lineaNegocioItem.setValueMap(mapaLineaNegocio);
							resetCombo(lineaNegocioItem, false);

							// Habilita el combo de Puestos de Trabajo
							resetCombo(puestoTrabajoItem, false);
						}
					};

					servicioComunicacion.cargarLineasNegocioPorUnidad(codigoUnidad, callbackLineaNegocio);
				} else {
					resetCombo(lineaNegocioItem, true);
					resetCombo(puestoTrabajoItem, true);
				}
				resetCombo(procesoItem, true);
				resetCombo(tableroControlItem, true);
				resetCombo(variablesItem, true);
			}
		};

		unidadItem.addChangeHandler(unidadChangeHandler);
	}

	public static void addPuestoTrabajoItemChangeHandlerNotificacion(SelectItem puestoTrabajoItem, final SelectItem unidadItem,
			final SelectItem variablesItem, final SelectItem tableroControlItem) {

		ChangeHandler puestoTrabajoChangeHandler = new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorPuestoTrabajo = (String) event.getValue();
				if (valorPuestoTrabajo != null) {
					Long codigoPuestoTrabajo = Long.parseLong(valorPuestoTrabajo);
					Long codigoUnidad = Long.parseLong(unidadItem.getValue().toString());

					AsyncCallback<List<TableroControlDTO>> callbackTableroControl = new AsyncCallback<List<TableroControlDTO>>() {
						public void onFailure(Throwable arg0) {
							Window.alert(ConstantesGWT.SERVER_ERROR);
						}

						public void onSuccess(List<TableroControlDTO> tablerosDTO) {
							LinkedHashMap<String, String> mapaTableroControl = UtilConverter
									.obtenerMapaTableroControlDTO(tablerosDTO);
							tableroControlItem.setValueMap(mapaTableroControl);
							resetCombo(tableroControlItem, false);

							Iterator<String> iterator = mapaTableroControl.keySet().iterator();
							if (iterator.hasNext()) {
								tableroControlItem.setValue(iterator.next());
							}
							// Se habilita el combo de variables
							resetCombo(variablesItem, false);
						}
					};

					servicioComunicacion.cargarTablerosControlPorPuestoTrabajoUnidad(codigoPuestoTrabajo, codigoUnidad,
							callbackTableroControl);
				} else {
					resetCombo(tableroControlItem, true);
					resetCombo(variablesItem, true);
				}
			}
		};

		puestoTrabajoItem.addChangeHandler(puestoTrabajoChangeHandler);
	}

	public static void addDivisionItemChangeHandlerNotificacion(SelectItem divisionItem, final SelectItem sociedadItem,
			final SelectItem unidadItem, final SelectItem lineaNegocioItem, final SelectItem procesoItem,
			final SelectItem tableroControlItem, final SelectItem puestoTrabajoItem, final SelectItem variableItem) {
		divisionItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				String valorDivision = (String) event.getValue();

				if (valorDivision != null) {
					servicioComunicacion.cargarSociedadesPorDivision(Long.parseLong(valorDivision),
							new AsyncCallback<List<SociedadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<SociedadDTO> sociedadesDTO) {
									LinkedHashMap<String, String> mapaSociedad = UtilConverter
											.obtenerMapaSociedadDTO(sociedadesDTO);
									sociedadItem.setValueMap(mapaSociedad);
									String cadena = null;
									sociedadItem.setDefaultValue(cadena);
									resetCombo(sociedadItem, false);
									
									unidadItem.setDefaultValue(cadena);
									resetCombo(unidadItem, true);
								}
							});
				} else { // Aqui seteamos los combos de sociedad, unidad,
					// lineaNeg y proceso a null
					// y los deshabilitamos...
					
					String cadena = null;
					sociedadItem.setDefaultValue(cadena);
					resetCombo(sociedadItem, true);
					
					unidadItem.setDefaultValue(cadena);
					resetCombo(unidadItem, true);
					
				}

				resetCombo(lineaNegocioItem, true);
				resetCombo(procesoItem, true);
				resetCombo(tableroControlItem, true);
				resetCombo(puestoTrabajoItem, true);
				resetCombo(variableItem, true);
			}
		});
	}

	public static void addSociedadItemChangeHandlerNotificacion(SelectItem sociedadItem, final SelectItem unidadItem,
			final SelectItem lineaNegocioItem, final SelectItem procesoItem, final SelectItem tableroControlItem,
			final SelectItem puestoTrabajoItem, final SelectItem variableItem) {

		sociedadItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorSociedad = (String) event.getValue();
				if (valorSociedad != null) {
					servicioComunicacion.cargarUnidadesPorSociedad(Long.parseLong(valorSociedad),
							new AsyncCallback<List<UnidadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<UnidadDTO> unidadesDTO) {
									LinkedHashMap<String, String> mapaUnidad = UtilConverter.obtenerMapaUnidadDTO(unidadesDTO);
									unidadItem.setValueMap(mapaUnidad);
									String cadena = null;
									unidadItem.setDefaultValue(cadena);
									resetCombo(unidadItem, false);
								}
							});
				} else{
					String cadena = null;
					unidadItem.setDefaultValue(cadena);
					resetCombo(unidadItem, true);
				}
				resetCombo(lineaNegocioItem, true);
				resetCombo(procesoItem, true);
				resetCombo(tableroControlItem, true);
				resetCombo(puestoTrabajoItem, true);
				resetCombo(variableItem, true);
			}
		});
	}

	public static void addUnidadItemChangeHandlerRegistro(SelectItem unidadItem, final SelectItem lineaNegocioItem,
			final SelectItem procesoItem, final SelectItem productoItem) {
		unidadItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorUnidad = (String) event.getValue();
				if (valorUnidad != null) {
					servicioComunicacion.cargarLineasNegocioPorUnidad(Long.parseLong(valorUnidad),
							new AsyncCallback<List<LineaNegocioDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<LineaNegocioDTO> lineasNegocioDTO) {
									LinkedHashMap<String, String> mapaLineaNegocio = UtilConverter
											.obtenerMapaLineaNegocioDTO(lineasNegocioDTO);
									lineaNegocioItem.setValueMap(mapaLineaNegocio);
									String defecto = null;
									lineaNegocioItem.setDefaultValue(defecto);
									lineaNegocioItem.setDisabled(false);
									lineaNegocioItem.clearValue();
									
									procesoItem.setDefaultValue(defecto);
									procesoItem.clearValue();
									procesoItem.setDisabled(true);

									productoItem.setDefaultValue(defecto);
									productoItem.clearValue();
									productoItem.setDisabled(true);
									
									/*procesoItem.clearValue();
									productoItem.clearValue();*/
								}
							});
				} else { // Aqui seteamos los combo de lineaNeg y proceso a
					// null y lo deshabilitamos...
					String cadena = null;
					lineaNegocioItem.setDefaultValue(cadena);
					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);
					
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);

					productoItem.setDefaultValue(cadena);
					productoItem.clearValue();
					productoItem.setDisabled(true);
				}
			}
		});

	}

	//agregado nuevo con puesto de trabajo
	public static void addUnidadItemChangeHandlerRegistro(SelectItem unidadItem, final SelectItem lineaNegocioItem,
			final SelectItem procesoItem, final SelectItem productoItem, final SelectItem puestoTrabajoItem) {
		unidadItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorUnidad = (String) event.getValue();
				if (valorUnidad != null) {
					servicioComunicacion.cargarLineasNegocioPorUnidad(Long.parseLong(valorUnidad),
							new AsyncCallback<List<LineaNegocioDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<LineaNegocioDTO> lineasNegocioDTO) {
									LinkedHashMap<String, String> mapaLineaNegocio = UtilConverter
											.obtenerMapaLineaNegocioDTO(lineasNegocioDTO);
									lineaNegocioItem.setValueMap(mapaLineaNegocio);
									lineaNegocioItem.setDisabled(false);
									lineaNegocioItem.clearValue();
									procesoItem.clearValue();
									productoItem.clearValue();
								}
							});
				} else { // Aqui seteamos los combo de lineaNeg y proceso a
					// null y lo deshabilitamos...
					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);

					procesoItem.clearValue();
					procesoItem.setDisabled(true);

					resetCombosProducto(productoItem);
					
					puestoTrabajoItem.clearValue();
					puestoTrabajoItem.setDisabled(true);
				}
			}
		});

	}
	
	/**
	 * Metodo que actualiza el combo de unidades segun la sociedad seleccionada
	 * 
	 * @param sociedadItem
	 * @param unidadItem
	 * @param lineaNegocioItem
	 * @param procesoItem
	 */
	public static void addSociedadItemChangeHandlerRegistro(SelectItem sociedadItem, final SelectItem unidadItem,
			final SelectItem lineaNegocioItem, final SelectItem procesoItem) {

		sociedadItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorSociedad = (String) event.getValue();
				if (valorSociedad != null) {
					servicioComunicacion.cargarUnidadesPorSociedad(Long.parseLong(valorSociedad),
							new AsyncCallback<List<UnidadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<UnidadDTO> unidadesDTO) {
									LinkedHashMap<String, String> mapaUnidad = UtilConverter.obtenerMapaUnidadDTO(unidadesDTO);
									String defecto = null;
									
									unidadItem.setValueMap(mapaUnidad);
									unidadItem.setDefaultValue(defecto);
									unidadItem.setDisabled(false);
									unidadItem.clearValue();

									//resetCombosLineaNegocio(lineaNegocioItem);
									lineaNegocioItem.setDefaultValue(defecto);
									lineaNegocioItem.clearValue();
									lineaNegocioItem.setDisabled(true);
									
									//resetCombosProceso(procesoItem);
									procesoItem.setDefaultValue(defecto);
									procesoItem.clearValue();
									procesoItem.setDisabled(true);
								}
							});
				} else { // Aqui seteamos los combos de unidad, lineaNeg, y
					// proceso a null
					// y lo deshabilitamos...
					//resetCombosUnidad(unidadItem);
					String cadena = null;
					unidadItem.setDefaultValue(cadena);
					unidadItem.clearValue();
					unidadItem.setDisabled(true);
					
					//resetCombosLineaNegocio(lineaNegocioItem);
					lineaNegocioItem.setDefaultValue(cadena);
					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);
					
					//resetCombosProceso(procesoItem);
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);
				}
			}
		});
	}

	public static void addSociedadItemChangeHandlerRegistro(SelectItem sociedadItem, final SelectItem unidadItem,
			final SelectItem lineaNegocioItem, final SelectItem procesoItem, final SelectItem productoItem) {

		sociedadItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorSociedad = (String) event.getValue();
				if (valorSociedad != null) {
					servicioComunicacion.cargarUnidadesPorSociedad(Long.parseLong(valorSociedad),
							new AsyncCallback<List<UnidadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<UnidadDTO> unidadesDTO) {
									LinkedHashMap<String, String> mapaUnidad = UtilConverter.obtenerMapaUnidadDTO(unidadesDTO);
									unidadItem.setValueMap(mapaUnidad);
									String defecto = null;
									unidadItem.setDefaultValue(defecto);
									unidadItem.setDisabled(false);
									unidadItem.clearValue();
									
									lineaNegocioItem.setDefaultValue(defecto);
									lineaNegocioItem.clearValue();
									lineaNegocioItem.setDisabled(true);
									
									procesoItem.setDefaultValue(defecto);
									procesoItem.clearValue();
									procesoItem.setDisabled(true);

									productoItem.setDefaultValue(defecto);
									productoItem.clearValue();
									productoItem.setDisabled(true);
									
									/*lineaNegocioItem.clearValue();
									procesoItem.clearValue();
									productoItem.clearValue();*/
								}
							});
				} else { // Aqui seteamos los combos de unidad, lineaNeg, y
					// proceso a null
					// y lo deshabilitamos...
					String cadena = null;
					unidadItem.setDefaultValue(cadena);
					unidadItem.clearValue();
					unidadItem.setDisabled(true);
					
					//resetCombosLineaNegocio(lineaNegocioItem);
					lineaNegocioItem.setDefaultValue(cadena);
					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);
					
					//resetCombosProceso(procesoItem);
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);

					//resetCombosProducto(productoItem);
					productoItem.setDefaultValue(cadena);
					productoItem.clearValue();
					productoItem.setDisabled(true);
				}
			}
		});
	}

	//Agregado nuevo con puesto de trabajo
	public static void addSociedadItemChangeHandlerRegistro(SelectItem sociedadItem, final SelectItem unidadItem,
			final SelectItem lineaNegocioItem, final SelectItem procesoItem, final SelectItem productoItem, final SelectItem puestoTrabajoItem) {

		sociedadItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorSociedad = (String) event.getValue();
				if (valorSociedad != null) {
					servicioComunicacion.cargarUnidadesPorSociedad(Long.parseLong(valorSociedad),
							new AsyncCallback<List<UnidadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<UnidadDTO> unidadesDTO) {
									LinkedHashMap<String, String> mapaUnidad = UtilConverter.obtenerMapaUnidadDTO(unidadesDTO);
									unidadItem.setValueMap(mapaUnidad);
									unidadItem.setDisabled(false);
									unidadItem.clearValue();
									lineaNegocioItem.clearValue();
									procesoItem.clearValue();
									productoItem.clearValue();
								}
							});
				} else { // Aqui seteamos los combos de unidad, lineaNeg, y
					// proceso a null
					// y lo deshabilitamos...
					
					//unidadItem.clearValue();
					//unidadItem.setDisabled(true);
					String cadena=null;
					unidadItem.setDefaultValue(cadena);
					unidadItem.clearValue();
					unidadItem.setDisabled(true);

					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);

					procesoItem.clearValue();
					procesoItem.setDisabled(true);

					resetCombosProducto(productoItem);
					
					puestoTrabajoItem.clearValue();
					puestoTrabajoItem.setDisabled(true);
				}
			}
		});
	}
	
	/**
	 * Metodo que actualiza el combo de sociedad segun la division seleccionada
	 * 
	 * @param divisionItem
	 * @param sociedadItem
	 * @param unidadItem
	 * @param lineaNegocioItem
	 * @param procesoItem
	 */
	public static void addDivisionItemChangeHandlerRegistro(SelectItem divisionItem, final SelectItem sociedadItem,
			final SelectItem unidadItem, final SelectItem lineaNegocioItem, final SelectItem procesoItem) {
		divisionItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				String valorDivision = (String) event.getValue();

				if (valorDivision != null) {
					servicioComunicacion.cargarSociedadesPorDivision(Long.parseLong(valorDivision),
							new AsyncCallback<List<SociedadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<SociedadDTO> sociedadesDTO) {
									LinkedHashMap<String, String> mapaSociedad = UtilConverter
											.obtenerMapaSociedadDTO(sociedadesDTO);
									String defecto = null;
									sociedadItem.setValueMap(mapaSociedad);
									sociedadItem.setDefaultValue(defecto);
									sociedadItem.setDisabled(false);
									sociedadItem.clearValue();
									
									//resetCombosUnidad(unidadItem);
									unidadItem.setDefaultValue(defecto);
									unidadItem.clearValue();
									unidadItem.setDisabled(true);
									
									//resetCombosLineaNegocio(lineaNegocioItem);
									lineaNegocioItem.setDefaultValue(defecto);
									lineaNegocioItem.clearValue();
									lineaNegocioItem.setDisabled(true);
									
									//resetCombosProceso(procesoItem);
									procesoItem.setDefaultValue(defecto);
									procesoItem.clearValue();
									procesoItem.setDisabled(true);
								}
							});
				} else { // Aqui seteamos los combos de sociedad, unidad,
					// lineaNeg y proceso a null
					// y los deshabilitamos...
					String cadena=null;
					sociedadItem.setDefaultValue(cadena);
					sociedadItem.clearValue();
					sociedadItem.setDisabled(true);
					
					//resetCombosUnidad(unidadItem);
					unidadItem.setDefaultValue(cadena);
					unidadItem.clearValue();
					unidadItem.setDisabled(true);
					
					//resetCombosLineaNegocio(lineaNegocioItem);
					lineaNegocioItem.setDefaultValue(cadena);
					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);
					
					//resetCombosProceso(procesoItem);
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);
				}
			}
		});
	}

	/**
	 * Metodo que actualiza el combo de sociedad segun la division seleccionada
	 * 
	 * @param divisionItem
	 * @param sociedadItem
	 * @param unidadItem
	 * @param lineaNegocioItem
	 * @param procesoItem
	 */
	public static void addDivisionItemChangeHandlerRegistro(SelectItem divisionItem, final SelectItem sociedadItem,
			final SelectItem unidadItem, final SelectItem lineaNegocioItem, final SelectItem procesoItem,
			final SelectItem productoItem) {
		divisionItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				String valorDivision = (String) event.getValue();

				if (valorDivision != null) {
					servicioComunicacion.cargarSociedadesPorDivision(Long.parseLong(valorDivision),
							new AsyncCallback<List<SociedadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<SociedadDTO> sociedadesDTO) {
									LinkedHashMap<String, String> mapaSociedad = UtilConverter
											.obtenerMapaSociedadDTO(sociedadesDTO);
									sociedadItem.setValueMap(mapaSociedad);
									
									String defecto = null;
									sociedadItem.setDefaultValue(defecto);
									sociedadItem.setDisabled(false);
									sociedadItem.clearValue();
									
									unidadItem.setDefaultValue(defecto);
									unidadItem.clearValue();
									unidadItem.setDisabled(true);
									
									lineaNegocioItem.setDefaultValue(defecto);
									lineaNegocioItem.clearValue();
									lineaNegocioItem.setDisabled(true);
									
									procesoItem.setDefaultValue(defecto);
									procesoItem.clearValue();
									procesoItem.setDisabled(true);
									;
									productoItem.setDefaultValue(defecto);
									productoItem.clearValue();
									productoItem.setDisabled(true);
									
									/*unidadItem.clearValue();
									lineaNegocioItem.clearValue();
									procesoItem.clearValue();
									productoItem.clearValue();*/
								}
							});
				} else { // Aqui seteamos los combos de sociedad, unidad,
					// lineaNeg y proceso a null
					// y los deshabilitamos...
					resetCombosProducto(productoItem);
					
					String cadena=null;
					//resetCombosSociedad(sociedadItem);
					sociedadItem.setDefaultValue(cadena);
					sociedadItem.clearValue();
					sociedadItem.setDisabled(true);
					
					//resetCombosUnidad(unidadItem);
					unidadItem.setDefaultValue(cadena);
					unidadItem.clearValue();
					unidadItem.setDisabled(true);
					
					//resetCombosLineaNegocio(lineaNegocioItem);
					lineaNegocioItem.setDefaultValue(cadena);
					lineaNegocioItem.clearValue();
					lineaNegocioItem.setDisabled(true);
					
					//resetCombosProceso(procesoItem);
					procesoItem.setDefaultValue(cadena);
					procesoItem.clearValue();
					procesoItem.setDisabled(true);
					
					//resetCombosProducto(productoItem);
					productoItem.setDefaultValue(cadena);
					productoItem.clearValue();
					productoItem.setDisabled(true);
				}
			}
		});
	}

	
	//Agregado nuevo con puesto de trabajo
	public static void addDivisionItemChangeHandlerRegistro(SelectItem divisionItem, final SelectItem sociedadItem,
			final SelectItem unidadItem, final SelectItem lineaNegocioItem, final SelectItem procesoItem,
			final SelectItem productoItem, final SelectItem puestoTrabajoItem) {
		divisionItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				String valorDivision = (String) event.getValue();

				if (valorDivision != null) {
					servicioComunicacion.cargarSociedadesPorDivision(Long.parseLong(valorDivision),
							new AsyncCallback<List<SociedadDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<SociedadDTO> sociedadesDTO) {
									LinkedHashMap<String, String> mapaSociedad = UtilConverter
											.obtenerMapaSociedadDTO(sociedadesDTO);
									sociedadItem.setValueMap(mapaSociedad);
									sociedadItem.setDisabled(false);
									sociedadItem.clearValue();
									unidadItem.clearValue();
									lineaNegocioItem.clearValue();
									procesoItem.clearValue();
									productoItem.clearValue();
								}
							});
				} else { // Aqui seteamos los combos de sociedad, unidad,
					// lineaNeg y proceso a null
					// y los deshabilitamos...
					
					//resetCombosSociedad(sociedadItem);
					String cadena=null;
					sociedadItem.setDefaultValue(cadena);
					sociedadItem.clearValue();
					sociedadItem.setDisabled(true);
					
					//resetCombosUnidad(unidadItem);
					unidadItem.setDefaultValue(cadena);
					unidadItem.clearValue();
					unidadItem.setDisabled(true);
					
					resetCombosLineaNegocio(lineaNegocioItem);
					resetCombosProceso(procesoItem);
					resetCombosProducto(productoItem);
					
					puestoTrabajoItem.clearValue();
					puestoTrabajoItem.setDisabled(true);
				}
			}
		});
	}
	
	
	
	
	public static void addProcesoItemChangeNotificacionParaPuestoTrabajo(final SelectItem procesoItem,
			final SelectItem puestosItem) {
		procesoItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String valorProceso = (String) event.getValue();

				// Window.alert("dentro del change habndler ");

				if (valorProceso != null) {
					servicioComunicacion.cargarPuestosTrabajoDTOPorProceso(Long.parseLong(valorProceso),
							new AsyncCallback<List<PuestoTrabajoDTO>>() {
								public void onFailure(Throwable arg0) {
									Window.alert(ConstantesGWT.SERVER_ERROR);
								}

								public void onSuccess(List<PuestoTrabajoDTO> puestosDTO) {

									// Window.alert("puestosDTO recibidos
									// "+puestosDTO.size());
									LinkedHashMap<String, String> mapaPuestos = UtilConverter
											.obtenerMapaPuestoTrabajoDTO(puestosDTO);
									puestosItem.setValueMap(mapaPuestos);
									puestosItem.setDisabled(false);
								}
							});
				} else {
					puestosItem.clearValue();
					puestosItem.setDisabled(true);
				}
			}
		});
	}

	public static void resetCombo(final SelectItem combo, boolean deshabilitar) {
		combo.setDisabled(deshabilitar);
		combo.clearValue();
	}

	/**
	 * Metodo para resetear el combo de sociedad
	 * 
	 * @param sociedadItem
	 */
	public static void resetCombosDivision(final SelectItem divisionItem) {
		divisionItem.clearValue();
		cargarComboDivisiones();
	}

	/**
	 * Metodo para resetear el combo de sociedad
	 * 
	 * @param sociedadItem
	 */
	public static void resetCombosSociedad(final SelectItem sociedadItem) {
		sociedadItem.clearValue();
		sociedadItem.setDisabled(true);
	}

	/**
	 * Metodo para resetear el combo de unidad
	 * 
	 * @param sociedadItem
	 */
	public static void resetCombosUnidad(final SelectItem unidadItem) {
		unidadItem.clearValue();
		unidadItem.setDisabled(true);
	}

	/**
	 * Metodo para resetear el combo de linea de negocio
	 * 
	 * @param sociedadItem
	 */
	public static void resetCombosLineaNegocio(final SelectItem lineaNegocioItem) {
		lineaNegocioItem.clearValue();
		lineaNegocioItem.setDisabled(true);
	}

	/**
	 * Metodo para resetear el combo de proceso
	 * 
	 * @param sociedadItem
	 */
	public static void resetCombosProceso(final SelectItem procesoItem) {
		procesoItem.clearValue();
		procesoItem.setDisabled(true);
	}

	/**
	 * Metodo para resetear el combo de linea de negocio
	 * 
	 * @param sociedadItem
	 */
	public static void resetCombosProducto(final SelectItem productoItem) {
		productoItem.clearValue();
		productoItem.setDisabled(true);
	}

	/**
	 * Metodo que agrega los eventos al formularios que contienen combos de
	 * Division,Sociedad,Unidad,Linea Negocio, Proceso.
	 * 
	 * @param formularioCombos
	 */
	public static void agregarEventosFormularioCombos(DynamicForm formularioCombos) {

		SelectItem divisionItem = (SelectItem) formularioCombos.getField(Combos.COMBO_DIVISION);
		SelectItem sociedadItem = (SelectItem) formularioCombos.getField(Combos.COMBO_SOCIEDAD);
		SelectItem unidadItem = (SelectItem) formularioCombos.getField(Combos.COMBO_UNIDAD);
		SelectItem lineaNegocioItem = (SelectItem) formularioCombos.getField(Combos.COMBO_LINEA_NEGOCIO);
		SelectItem procesoItem = (SelectItem) formularioCombos.getField(Combos.COMBO_PROCESO);

		Combos.addDivisionItemChangeHandlerRegistro(divisionItem, sociedadItem, unidadItem, lineaNegocioItem, procesoItem);
		Combos.addSociedadItemChangeHandlerRegistro(sociedadItem, unidadItem, lineaNegocioItem, procesoItem);
		Combos.addUnidadItemChangeHandlerRegistro(unidadItem, lineaNegocioItem, procesoItem);
		Combos.addLineaNegocioItemChangeHandlerRegistro(lineaNegocioItem, procesoItem);
	}

	/**
	 * Metodo que agrega los eventos al formularios que contienen combos de
	 * Division,Sociedad,Unidad,Linea Negocio, Proceso, y Puesto de Trabajo.
	 * Usado en el CU de Registro de notificacion
	 * 
	 * @param formularioCombos
	 */
	public static void agregarEventosFormularioCombosRegistroNotificacion(DynamicForm formularioCombos) {

		// Window.alert("dentro del
		// agregarEventosFormularioCombosRegistroNotificacion");

		// Este metodo reusa el anterior...
		agregarEventosFormularioCombos(formularioCombos);

		// Mas el caso de puestos de trabajo...
		SelectItem puestosItem = (SelectItem) formularioCombos.getField(Combos.COMBO_PUESTO_TRABAJO);

		SelectItem procesoItem = (SelectItem) formularioCombos.getField(Combos.COMBO_PROCESO);
		Combos.addProcesoItemChangeNotificacionParaPuestoTrabajo(procesoItem, puestosItem);

	}

	/**
	 * Metodo que agrega los eventos al formularios que contienen combos de
	 * Division,Sociedad,Unidad,Linea Negocio, Proceso, y Puesto de Trabajo.
	 * Usado en el CU de Registro de notificacion
	 * 
	 * @param formularioCombos
	 */
	public static void agregarEventosFormularioCombosAdministrarTablaValor(DynamicForm formularioCombos) {

		SelectItem divisionItem = (SelectItem) formularioCombos.getField(Combos.COMBO_DIVISION);
		SelectItem sociedadItem = (SelectItem) formularioCombos.getField(Combos.COMBO_SOCIEDAD);
		SelectItem unidadItem = (SelectItem) formularioCombos.getField(Combos.COMBO_UNIDAD);
		SelectItem lineaNegocioItem = (SelectItem) formularioCombos.getField(Combos.COMBO_LINEA_NEGOCIO);
		SelectItem procesoItem = (SelectItem) formularioCombos.getField(Combos.COMBO_PROCESO);

		Combos.addDivisionItemChangeHandlerRegistro(divisionItem, sociedadItem, unidadItem, lineaNegocioItem, procesoItem);
		Combos.addSociedadItemChangeHandlerRegistro(sociedadItem, unidadItem, lineaNegocioItem, procesoItem);
		Combos.addUnidadItemChangeHandlerRegistro(unidadItem, lineaNegocioItem, procesoItem);

	}

	/**
	 * Metodo que carga el combo de silos
	 * 
	 * @return
	 */
	public static SelectItem cargarComboSilos() {
		final SelectItem comboSilos = new SelectItem();
		comboSilos.setName("silo");
		comboSilos.setTitle("Silo");
		comboSilos.setAllowEmptyValue(true);

		servicioComunicacion.cargarMediosAlmacenamiento(new AsyncCallback<List<MedioAlmacenamientoDTO>>() {

			public void onFailure(Throwable arg0) {
				com.google.gwt.user.client.Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<MedioAlmacenamientoDTO> silos) {
				LinkedHashMap<String, String> mapaSilos = UtilConverter.obtenerMapaMedioAlmacenamientoDTO(silos);
				comboSilos.setValueMap(mapaSilos);
			}
		});
		return comboSilos;
	}

	/**
	 * Metodo que carga el combo de silos
	 * 
	 * @return
	 */
	public static SelectItem cargarComboOrdenes(Long codigoProceso) {
		final SelectItem comboOrden = cargarComboOrden();

		servicioComunicacion.obtenerOrdenesProduccionDTOByProceso(codigoProceso, new AsyncCallback<List<OrdenProduccionDTO>>() {

			public void onFailure(Throwable arg0) {
				com.google.gwt.user.client.Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(List<OrdenProduccionDTO> ordenes) {
				LinkedHashMap<String, String> mapaOrden = UtilConverter.obtenerMapaOrdenDTO(ordenes);
				comboOrden.setValueMap(mapaOrden);
			}
		});
		return comboOrden;
	}

	public static DateItem cargarComboFecha(String nombre, String titulo) {
		Date date = new Date();

		DateItem fechaItem = new DateItem();
		fechaItem.setValue(date);
		fechaItem.setName(nombre);
		fechaItem.setTitle(titulo);
		fechaItem.setDisplayFormat(DateDisplayFormat.TOEUROPEANSHORTDATE);
		fechaItem.setSelectorFormat(DateItemSelectorFormat.DAY_MONTH_YEAR);

		long time = date.getTime();
		time = time - 2 * (ConstantesGWT.MILI_SEG_EN_1_ANIO);
		date.setTime(time);
		fechaItem.setStartDate(date);

		return fechaItem;
	}
}