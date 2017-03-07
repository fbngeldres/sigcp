package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.componentes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente.ConstantesModuloStock;
import pe.com.pacasmayo.sgcp.presentacion.gwt.stock.cliente.StockServiceAsync;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridDetalleRegistro.java
 * Modificado: Apr 15, 2010 3:45:27 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class GridDetalleRegistro extends ListGrid {

	private static final String COLUMNA_CODIGO_MEDICION = "codigoMedicion";
	private static final String COLUMNA_STOCK_SEGURIDAD = "stockSeguridad";
	private static final String COLUMNA_FACTOR_METROS_CUBICOS = "factorMetrosCubicos";
	private static final String COLUMNA_CANTIDAD_ALTURAS = "cantidadAlturas";
	private static final String MENSAJE_VALIDACION_ALTURA_VALOR_NUMERICO = "La Altura debe ser un valor num&eacute;rico.";
	private static final String COLUMNA_MEDIO_ALMACENAMIENTO = "Medio Almacenamiento";
	private static final String COLUMNA_CANTIDAD = "Cantidad";
	private static final String COLUMNA_NOMBRE_SILO = "Silo";
	private static final String COLUMNA_CODIGO_SILO = "codigoSilo";
	private static final String COLUMNA_ALTURA_SILO = "alturaSilo";
	private static final String COLUMNA_PRODUCTO = "Producto";
	private static final String COLUMNA_CODIGO_PRODUCTO = "codigoProducto";
	private static final String COLUMNA_CODIGO_PRODUCCION = "codigoProduccion";
	// private static final String COLUMNAS_ALTURAS = "Altura de Silos (m)";
	private static final String COLUMNA_ALTURA = "H";
	private static Integer cantidadMaximaAlturas = 0;
	public static Integer tipoMedio = 0;
	private static final String COLUMNA_CANTIDAD_ALMACENADA = "Cantidad Almac.(T)";
	private static final String COLUMNA_CAPACIDAD = "Capacidad(T)";
	private static final String COLUMNA_PORCENTAJE_USO = "% de Uso";
	// private static final String MASCARA_NUMERICA_1_DIGITO = "#";
	private static final int LONGITUD_MAXIMA_ALTURAS = 4;

	// Este mapa representa las columnas de las alturas, y tiene como clave el
	// num de altura,
	// y como valor a cada uno de los objetos ListGridField que seran columna de
	// altura
	private Map<String, ListGridField> mapaAlturas = new LinkedHashMap<String, ListGridField>();

	// Este mapa representa las columnas del grid, y tiene como clave el num
	// columna, y como valor a cada uno de los objetos ListGridField
	private Map<String, ListGridField> mapaColumnas = new LinkedHashMap<String, ListGridField>();

	// private static StockServiceAsync servicioStock =
	// GWT.create(StockService.class);

	/**
	 * interfaz usada para obtener las constantes (i18n)
	 */
	private static ConstantesModuloStock CONSTANTES = GWT.create(ConstantesModuloStock.class);

	/**
	 * Constructor del grid de detalle del silo por dia
	 */
	public GridDetalleRegistro() {

		setSaveLocally(true);
		setModalEditing(false);
		setEditEvent(ListGridEditEvent.CLICK);
		setSelectionType(SelectionStyle.SINGLE);

		setCanReorderFields(false);
		setCanGroupBy(false);
		setCanResizeFields(true);
		setCanFreezeFields(false);
		setShowHeaderContextMenu(false);
		setShowEmptyMessage(true);

		setWidth100();
		setHeight(360);
		setCanSort(false);

		setLoadingDataMessage(CONSTANTES.cargandoDatos());
		setEmptyMessage(CONSTANTES.noExistenDatosParaMostrar());
	}

	public void cargarColumnas(List<RegistroTablaMedicionDiaDTO> listaRegistros, Long codigoTipoMedio) {

		cantidadMaximaAlturas = 0;
		ListGridField codigoMedicion = new ListGridField(COLUMNA_CODIGO_MEDICION);
		codigoMedicion.setType(ListGridFieldType.INTEGER);
		codigoMedicion.setHidden(true);
		mapaColumnas.put(COLUMNA_CODIGO_MEDICION, codigoMedicion);

		ListGridField codigoProducto = new ListGridField(COLUMNA_CODIGO_PRODUCTO);
		codigoProducto.setType(ListGridFieldType.INTEGER);
		codigoProducto.setHidden(true);
		mapaColumnas.put(COLUMNA_CODIGO_PRODUCTO, codigoProducto);

		ListGridField producto = new ListGridField();
		producto.setType(ListGridFieldType.TEXT);
		producto.setCanEdit(false);
		producto.setName(COLUMNA_PRODUCTO);
		producto.setAlign(Alignment.CENTER);
		producto.setWidth(90);

		// Agregamos la columna al mapa de columnas
		mapaColumnas.put(COLUMNA_PRODUCTO, producto);

		ListGridField codigoProduccion = new ListGridField(COLUMNA_CODIGO_PRODUCCION);
		codigoProduccion.setType(ListGridFieldType.INTEGER);
		codigoProduccion.setHidden(true);
		mapaColumnas.put(COLUMNA_CODIGO_PRODUCCION, codigoProduccion);

		// Codigo del medio de almacenamiento
		ListGridField codigoSilo = new ListGridField(COLUMNA_CODIGO_SILO);
		codigoSilo.setType(ListGridFieldType.INTEGER);
		codigoSilo.setHidden(true);
		mapaColumnas.put(COLUMNA_CODIGO_SILO, codigoSilo);

		ListGridField alturaSilo = new ListGridField(COLUMNA_ALTURA_SILO);
		alturaSilo.setType(ListGridFieldType.FLOAT);
		alturaSilo.setHidden(true);
		mapaColumnas.put(COLUMNA_ALTURA_SILO, alturaSilo);

		ListGridField cantidadAlturasSilo = new ListGridField(COLUMNA_CANTIDAD_ALTURAS);
		cantidadAlturasSilo.setType(ListGridFieldType.INTEGER);
		cantidadAlturasSilo.setHidden(true);
		mapaColumnas.put(COLUMNA_CANTIDAD_ALTURAS, cantidadAlturasSilo);

		// Si es Silo:
		if (codigoTipoMedio.equals(ConstantesGWT.CODIGO_TIPO_MEDIOALMACENAMIENTO_SILO)) {

			ListGridField nombreSilo = new ListGridField();
			nombreSilo.setType(ListGridFieldType.INTEGER);
			nombreSilo.setCanEdit(false);
			nombreSilo.setName(COLUMNA_NOMBRE_SILO);
			nombreSilo.setAlign(Alignment.CENTER);
			nombreSilo.setWidth(120);
			// Agregamos la columna al mapa de columnas
			mapaColumnas.put(COLUMNA_NOMBRE_SILO, nombreSilo);

			ListGridField factorMetrosCubicos = new ListGridField(COLUMNA_FACTOR_METROS_CUBICOS);
			factorMetrosCubicos.setType(ListGridFieldType.FLOAT);
			factorMetrosCubicos.setHidden(true);
			mapaColumnas.put(COLUMNA_FACTOR_METROS_CUBICOS, factorMetrosCubicos);

			ListGridField stockSeguridad = new ListGridField(COLUMNA_STOCK_SEGURIDAD);
			stockSeguridad.setType(ListGridFieldType.FLOAT);
			stockSeguridad.setHidden(true);
			mapaColumnas.put(COLUMNA_STOCK_SEGURIDAD, stockSeguridad);

			// Iteramos sobre la lista de registros para averiguar la cantidad
			// maxima de medidas de alturas, y poder construir N columnas de
			// alturas
			// para todo el grid
			if ((listaRegistros != null) && (!listaRegistros.isEmpty())) {
				for (int i = 0; i < listaRegistros.size(); i++) {
					RegistroTablaMedicionDiaDTO registro = listaRegistros.get(i);
					Long numeroAlturas = registro.getNumeroAlturas();
					int numeroAlturasAsInt = numeroAlturas == null ? 0 : numeroAlturas.intValue();
					Integer cantidadAlturas = numeroAlturasAsInt;
					// nos quedamos con la cantidad maxima de alturas posibles
					// que tendra el grid.
					// Este valor esta dado por el mayor valor de cantidad de
					// alturas que tengan los silos
					// que vienen en la lista.
					if (cantidadAlturas > cantidadMaximaAlturas) {
						cantidadMaximaAlturas = cantidadAlturas;
					}
				}
			}

			// En este for agregamos dinamicamente las columnas al mapa de
			// columnas
			for (int i = 1; i <= cantidadMaximaAlturas; i++) {
				ListGridField columnaAltura = new ListGridField(COLUMNA_ALTURA + i);
				columnaAltura.setWidth(40);
				columnaAltura.setAlign(Alignment.CENTER);
				columnaAltura.setCanEdit(true);
				columnaAltura.setType(ListGridFieldType.FLOAT);

				// columnaAltura.setCellFormatter(obtenerFormato2Num2Decimales());
				// addAlturaCellEditorHandler(columnaAltura);
				columnaAltura.addCellSavedHandler(obtenerAlturaCellSavedEvent(columnaAltura.getName(), codigoTipoMedio));
				mapaColumnas.put(COLUMNA_ALTURA + i, columnaAltura);
			}

			ListGridField cantidadAlmac = new ListGridField();
			cantidadAlmac.setType(ListGridFieldType.FLOAT);
			cantidadAlmac.setCanEdit(false);
			cantidadAlmac.setName(COLUMNA_CANTIDAD_ALMACENADA);
			cantidadAlmac.setAlign(Alignment.CENTER);
			cantidadAlmac.setWidth(110);
			cantidadAlmac.setCellFormatter(obtenerFormatoCelda2Decimales());
			mapaColumnas.put(COLUMNA_CANTIDAD_ALMACENADA, cantidadAlmac);

			ListGridField capacidad = new ListGridField();
			capacidad.setType(ListGridFieldType.FLOAT);
			capacidad.setCanEdit(false);
			capacidad.setWidth(130);
			capacidad.setName(COLUMNA_CAPACIDAD);
			capacidad.setAlign(Alignment.CENTER);
			capacidad.setCellFormatter(obtenerFormatoCelda2Decimales());
			mapaColumnas.put(COLUMNA_CAPACIDAD, capacidad);

			ListGridField porcentaje = new ListGridField();
			porcentaje.setType(ListGridFieldType.FLOAT);
			// porcentaje.setCanEdit(true);
			porcentaje.setWidth(70);
			porcentaje.setName(COLUMNA_PORCENTAJE_USO);
			porcentaje.setAlign(Alignment.CENTER);
			porcentaje.addCellSavedHandler(obtenerPorcentajeCellSavedEvent());

			porcentaje.setCellFormatter(obtenerFormato2Num2Decimales());
			mapaColumnas.put(COLUMNA_PORCENTAJE_USO, porcentaje);

		}
		// Sino es Silo:
		else {

			ListGridField medioAlmacenamiento = new ListGridField();
			medioAlmacenamiento.setType(ListGridFieldType.INTEGER);
			medioAlmacenamiento.setCanEdit(false);
			medioAlmacenamiento.setName(COLUMNA_MEDIO_ALMACENAMIENTO);
			medioAlmacenamiento.setAlign(Alignment.CENTER);
			// Agregamos la columna al mapa de columnas
			mapaColumnas.put(COLUMNA_MEDIO_ALMACENAMIENTO, medioAlmacenamiento);

			ListGridField cantidad = new ListGridField();
			cantidad.setType(ListGridFieldType.FLOAT);
			cantidad.setCanEdit(true);
			cantidad.setName(COLUMNA_CANTIDAD);
			cantidad.setAlign(Alignment.CENTER);
			cantidad.addCellSavedHandler(obtenerCantidadCellSavedEvent());
			mapaColumnas.put(COLUMNA_CANTIDAD, cantidad);

			// Agregamos Capacidad
			ListGridField capacidad = new ListGridField();
			capacidad.setType(ListGridFieldType.FLOAT);
			capacidad.setCanEdit(false);
			capacidad.setName(COLUMNA_CAPACIDAD);
			capacidad.setAlign(Alignment.CENTER);
			mapaColumnas.put(COLUMNA_CAPACIDAD, capacidad);

			ListGridField cantidadAlmac = new ListGridField();
			cantidadAlmac.setType(ListGridFieldType.FLOAT);
			cantidadAlmac.setCanEdit(false);
			cantidadAlmac.setName(COLUMNA_CANTIDAD_ALMACENADA);
			cantidadAlmac.setAlign(Alignment.CENTER);
			mapaColumnas.put(COLUMNA_CANTIDAD_ALMACENADA, cantidadAlmac);
		}

		// Convertimos el mapa de columnas en un arreglo para poder asignarlo
		// mediante el metodo setFields al grid como las columnas que lo
		// constituyen
		ListGridField[] arregloColumnas = new ListGridField[mapaColumnas.size()];
		int indiceMapa = 0;
		for (Iterator<String> iterator = mapaColumnas.keySet().iterator(); iterator.hasNext();) {
			String clave = iterator.next();
			ListGridField field = mapaColumnas.get(clave);
			arregloColumnas[indiceMapa] = field;
			indiceMapa++;
		}

		setFields(arregloColumnas);
	}

	@Override
	protected boolean canEditCell(int rowNum, int colNum) {
		// Si es la columna de porcentaje, valida que no tenga valor en las
		// alturas para poder ser editable
		if (getField(colNum).getName().equals(COLUMNA_PORCENTAJE_USO)) {
			ListGridRecord record = getRecord(rowNum);
			String numeroAlturasStr = record.getAttribute(COLUMNA_CANTIDAD_ALTURAS);
			Long numeroAlturas = numeroAlturasStr == null ? 0L : Long.valueOf(numeroAlturasStr);

			List<Double> listaAlturas = obtenerListaAlturas(record, numeroAlturas);

			// no tiene alturas
			if (listaAlturas == null || listaAlturas.size() == 0) {
				return true;
			}

			// tiene algun valor en alturas distinto de cero
			for (Double valorAltura : listaAlturas) {
				if (valorAltura != null && valorAltura != 0d) {
					// Window.alert("La columna: " + COLUMNA_PORCENTAJE_USO + "
					// "
					// + "solo es editable en caso de que no se tengan alturas
					// registradas");
					return false;
				}
			}

			// tiene alturas pero con valor 0
			return true;
		}
		return super.canEditCell(rowNum, colNum);
	}

	private CellSavedHandler obtenerPorcentajeCellSavedEvent() {
		CellSavedHandler cellSavedHandler = new CellSavedHandler() {
			public void onCellSaved(CellSavedEvent event) {
				Record record = event.getRecord();
				Object newValue = event.getNewValue();
				if (newValue == null) {
					Window.alert("el porcentaje no puede ser un valor nulo.");
					record.setAttribute(COLUMNA_PORCENTAJE_USO, event.getOldValue());
				} else {
					try {
						double valorPorcentaje = Double.parseDouble(newValue.toString());
						Double capacidad = record.getAttributeAsDouble(COLUMNA_CAPACIDAD);
						double cantAlmac = valorPorcentaje * capacidad / 100;
						record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, cantAlmac);
					} catch (NumberFormatException e) {
						Window.alert("El  nuevo  valor de la columna porcentaje debe ser numerico");
						record.setAttribute(COLUMNA_PORCENTAJE_USO, event.getOldValue());
					}
				}
			}
		};
		return cellSavedHandler;
	}

	private CellFormatter obtenerFormatoCelda2Decimales() {
		CellFormatter cellFormatter = new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
					return nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}
			}
		};

		return cellFormatter;
	}

	private CellFormatter obtenerFormato2Num2Decimales() {
		CellFormatter formatoDosNumdosDec = new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat("##.##");
					return nf.format(((Number) value).doubleValue()) + " %";
				} catch (Exception e) {
					return value.toString();
				}
			}
		};

		return formatoDosNumdosDec;
	}

	@Override
	protected String getGridSummary(ListGridField field) {
		String valueStr = super.getGridSummary(field);
		if (valueStr == null) {
			return "0";
		}
		try {
			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			Double d = nf.parse(valueStr);
			String format = nf.format(d);
			return format;
		} catch (Exception e) {
			return valueStr;
		}
	}

	/**
	 * Metodo de actualizacion de la cantidad almacenada y el porcentaje de uso
	 * del silo
	 * 
	 * @param columnaAltura
	 */
	private CellSavedHandler obtenerAlturaCellSavedEvent(final String nombreColumna, final Long codigoTipoMedio) {
		CellSavedHandler cellSavedHandler = new CellSavedHandler() {
			public void onCellSaved(CellSavedEvent event) {
				int rowNum = event.getRowNum();
				Record record = event.getRecord();
				try {
					String valor = event.getNewValue().toString();

					if (Double.parseDouble(valor) < 0 || valor.length() > LONGITUD_MAXIMA_ALTURAS) {
						double oldValue = Double.parseDouble(event.getOldValue().toString());
						record.setAttribute(nombreColumna, oldValue);
					} else {
						NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
						Double d = nf.parse(valor);
						String format = nf.format(d);
						record.setAttribute(nombreColumna, new Double(format));
					}

				} catch (NumberFormatException e) {
					record.setAttribute(nombreColumna, event.getOldValue().toString());
					Window.alert(MENSAJE_VALIDACION_ALTURA_VALOR_NUMERICO);
				}

				actualizarMediciones(codigoTipoMedio, record, rowNum);
			}
		};
		return cellSavedHandler;
	}

	/**
	 * Metodo que agrega al grid los registros creando las columnas
	 * dinamicamente
	 * 
	 * @param listaRegistros
	 */
	public void cargarGridDetalle(List<RegistroTablaMedicionDiaDTO> listaRegistros, Long codigoTipoMedio) {

		// Si es Silo:
		if (codigoTipoMedio.longValue() == 1L) {

			// Recorremos la lista de registros recibida
			if ((listaRegistros != null) && (!listaRegistros.isEmpty())) {
				int rowNum = 0;
				for (int index = 0; index < listaRegistros.size(); index++) {
					RegistroTablaMedicionDiaDTO registro = listaRegistros.get(index);

					// Por cada registro de la lista recibida agregamos la
					// columna fecha
					// y creamos un record que sera agregado al grid

					ListGridRecord record = new ListGridRecord();
					record.setAttribute(COLUMNA_CODIGO_MEDICION, registro.getCodigo());
					record.setAttribute(COLUMNA_NOMBRE_SILO, registro.getNombreSilo());
					record.setAttribute(COLUMNA_CODIGO_SILO, registro.getCodigoSilo());
					record.setAttribute(COLUMNA_ALTURA_SILO, registro.getAlturaSilo());
					record.setAttribute(COLUMNA_CANTIDAD_ALTURAS, registro.getNumeroAlturas());
					record.setAttribute(COLUMNA_FACTOR_METROS_CUBICOS, registro.getFactorMetrosCubicos());
					record.setAttribute(COLUMNA_STOCK_SEGURIDAD, registro.getStockSeguridad());
					record.setAttribute(COLUMNA_PRODUCTO, registro.getNombreProducto());
					record.setAttribute(COLUMNA_CODIGO_PRODUCTO, registro.getCodigoProducto());
					record.setAttribute(COLUMNA_CODIGO_PRODUCCION, registro.getCodigoProduccion());
					record.setAttribute(COLUMNA_CAPACIDAD, registro.getCapacidad());
					record.setAttribute(COLUMNA_CANTIDAD, registro.getCantidad());

					List<Double> listaAlturas = registro.getListaAlturas();

					Long codigoMedicion = registro.getCodigo();
					if (codigoMedicion == null) {
						Long cantidadAlturasSilo = new Long(record.getAttribute(COLUMNA_CANTIDAD_ALTURAS));
						for (int i = 1; i <= cantidadAlturasSilo; i++) {
							record.setAttribute(COLUMNA_ALTURA + i, 0D);
						}
						record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, 0D);
						record.setAttribute(COLUMNA_PORCENTAJE_USO, 0D);
					} else {
						for (int j = 1; j <= listaAlturas.size(); j++) {
							String columna = COLUMNA_ALTURA + (j);
							record.setAttribute(columna, listaAlturas.get(j - 1));
						}
						actualizarMediciones(codigoTipoMedio, record, rowNum);
					}
					rowNum++;
					addData(record);
				}
			}

		} else {
			// Recorremos la lista de registros recibida
			if ((listaRegistros != null) && (!listaRegistros.isEmpty())) {
				for (Iterator<RegistroTablaMedicionDiaDTO> iterator = listaRegistros.iterator(); iterator.hasNext();) {

					// Por cada registro de la lista recibida agregamos la
					// columna fecha
					// y creamos un record que sera agregado al grid
					RegistroTablaMedicionDiaDTO registro = iterator.next();

					ListGridRecord record = new ListGridRecord();
					record.setAttribute(COLUMNA_MEDIO_ALMACENAMIENTO, registro.getNombreSilo());
					record.setAttribute(COLUMNA_CODIGO_SILO, registro.getCodigoSilo());
					record.setAttribute(COLUMNA_ALTURA_SILO, registro.getAlturaSilo());
					record.setAttribute(COLUMNA_PRODUCTO, registro.getNombreProducto());
					record.setAttribute(COLUMNA_CODIGO_PRODUCCION, registro.getCodigoProduccion());
					record.setAttribute(COLUMNA_CODIGO_PRODUCTO, registro.getCodigoProducto());
					record.setAttribute(COLUMNA_CANTIDAD, Long.valueOf(0));
					record.setAttribute(COLUMNA_CAPACIDAD, registro.getCapacidad());
					record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, registro.getCantidad());
					record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, 0);
					addData(record);
				}
			}
		}
	}

	public Map<String, ListGridField> getMapaAlturas() {
		return mapaAlturas;
	}

	public void setMapaAlturas(Map<String, ListGridField> mapaAlturas) {
		this.mapaAlturas = mapaAlturas;
	}

	/**
	 * Metodo que exporta el contenido del grid a una lista de objetos
	 * RegistroTablaMedicionDiaDTO
	 * 
	 * @return
	 */
	public List<RegistroTablaMedicionDiaDTO> exportarGrid(Long codigoTipoMedio) {

		List<RegistroTablaMedicionDiaDTO> listaRegistroTablaMedicionDiaDTO = new ArrayList<RegistroTablaMedicionDiaDTO>();
		RecordList listaRecord = this.getRecordList();

		if (codigoTipoMedio.equals(ConstantesGWT.CODIGO_TIPO_MEDIOALMACENAMIENTO_SILO)) {
			for (int i = 0; i < listaRecord.getLength(); i++) {

				RegistroTablaMedicionDiaDTO registro = new RegistroTablaMedicionDiaDTO();
				ListGridRecord record = (ListGridRecord) listaRecord.get(i);

				registro.setCodigoProducto(Long.valueOf(record.getAttribute(COLUMNA_CODIGO_PRODUCTO)));
				registro.setNombreProducto(record.getAttribute(COLUMNA_PRODUCTO));
				registro.setCodigoSilo(Long.valueOf(record.getAttribute(COLUMNA_CODIGO_SILO)));
				registro.setNombreSilo(record.getAttribute(COLUMNA_NOMBRE_SILO));
				registro.setCantidad(Double.valueOf(record.getAttribute(COLUMNA_CANTIDAD_ALMACENADA)));
				registro.setCapacidad(Double.valueOf(record.getAttribute(COLUMNA_CAPACIDAD)));
				registro.setPorcentajeUso(Double.valueOf(record.getAttribute(COLUMNA_PORCENTAJE_USO)));
				registro.setCodigoProduccion(Long.valueOf(record.getAttribute(COLUMNA_CODIGO_PRODUCCION)));

				String numeroAlturasStr = record.getAttribute(COLUMNA_CANTIDAD_ALTURAS);
				Long numeroAlturas = numeroAlturasStr == null ? 0L : Long.valueOf(numeroAlturasStr);
				registro.setNumeroAlturas(numeroAlturas);

				List<Double> listaAlturas = new ArrayList<Double>();

				for (int j = 1; j <= numeroAlturas; j++) {
					String columna = COLUMNA_ALTURA + (j);
					String alturaStr = record.getAttribute(columna);
					if (alturaStr != null && alturaStr != "") {
						Double altura = Double.valueOf(alturaStr);
						listaAlturas.add(altura);
					}
				}

				registro.setListaAlturas(listaAlturas);

				listaRegistroTablaMedicionDiaDTO.add(registro);
			}
		} else {
			for (int i = 0; i < listaRecord.getLength(); i++) {
				try {

					RegistroTablaMedicionDiaDTO registro = new RegistroTablaMedicionDiaDTO();

					registro.setCodigoProducto(Long.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CODIGO_PRODUCTO)));
					registro.setNombreProducto(listaRecord.get(i).getAttribute(COLUMNA_PRODUCTO));
					registro.setCodigoSilo(Long.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CODIGO_SILO)));
					registro.setNombreSilo(listaRecord.get(i).getAttribute(COLUMNA_MEDIO_ALMACENAMIENTO));
					registro.setCodigoProduccion(Long.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CODIGO_PRODUCCION)));

					registro.setListaAlturas(new ArrayList<Double>());
					registro.setPorcentajeUso(0d);
					registro.setNumeroAlturas(Long.valueOf(0));
					registro.setCantidad(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CANTIDAD_ALMACENADA)));
					registro.setCapacidad(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CAPACIDAD)));

					listaRegistroTablaMedicionDiaDTO.add(registro);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return listaRegistroTablaMedicionDiaDTO;
	}

	/**
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {

		if (this.getTotalRows() > 0) {

			for (int i = 0; i < this.getTotalRows(); i++) {
				Record record = this.getRecord(i);
				this.removeData(record);
			}
		}
	}

	private void actualizarMediciones(final Long codigoTipoMedio, final Record record, final int rowNum) {

		final Double capacidad = record.getAttributeAsDouble(COLUMNA_CAPACIDAD);
		Double cantidadAlmacenada = null;

		if (codigoTipoMedio.equals(ConstantesGWT.CODIGO_TIPO_MEDIOALMACENAMIENTO_SILO)) {

			String codigoSiloStr = record.getAttribute(COLUMNA_CODIGO_SILO);
			Long codigoSilo = Long.parseLong(codigoSiloStr);

			String numeroAlturasStr = record.getAttribute(COLUMNA_CANTIDAD_ALTURAS);
			Long numeroAlturas = numeroAlturasStr == null ? 0L : Long.valueOf(numeroAlturasStr);

			List<Double> listaAlturas = obtenerListaAlturas(record, numeroAlturas);

			/**
			 * Obtener Cantidad Almacenada
			 */
			AsyncCallback<Double> callback = new AsyncCallback<Double>() {

				public void onFailure(Throwable arg0) {
					Window.alert("error al calcular la cantidad almacenada");

				}

				public void onSuccess(Double arg0) {
					double cantidadAlmac = arg0;
					Double porcentajeUso = (cantidadAlmac / capacidad) * 100;
					record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, cantidadAlmac);
					record.setAttribute(COLUMNA_PORCENTAJE_USO, porcentajeUso);
					refreshRow(rowNum);

				}

			};

			servicioStock.obtenerCantidadAlmacenadaFormula(codigoSilo, listaAlturas, callback);
			// double cantidadAlmac = obtenerCantidadAlmacenadaSilo(codigoSilo,
			// listaAlturas);
			// Double porcentajeUso = (cantidadAlmac / capacidad) * 100;
			// record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, cantidadAlmac);
			// record.setAttribute(COLUMNA_PORCENTAJE_USO, porcentajeUso);
			// refreshRow(rowNum);
		} else {
			// cantidadAlmacenada = cantidad * capacidadMaxima
			Double cantidad = new Double(record.getAttribute(COLUMNA_CANTIDAD_ALMACENADA));
			cantidadAlmacenada = cantidad * capacidad;
			record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, cantidadAlmacenada);
		}
	}

	public Double cantidadAlmacenada = 0d;

	/**
	 * @return the cantidadAlmacenada
	 */
	public Double getCantidadAlmacenada() {
		return cantidadAlmacenada;
	}

	/**
	 * @param cantidadAlmacenada the cantidadAlmacenada to set
	 */
	public void setCantidadAlmacenada(Double cantidadAlmacenada) {
		this.cantidadAlmacenada = cantidadAlmacenada;
	}

	private double obtenerCantidadAlmacenadaSilo(Long codigoSilo, List<Double> listaAlturas) {
		final long CEM_1 = Long.parseLong(CONSTANTES.codigoSiloCem1());
		final long CEM_2 = Long.parseLong(CONSTANTES.codigoSiloCem2());
		final long CEM_3 = Long.parseLong(CONSTANTES.codigoSiloCem3());
		final long CEM_4 = Long.parseLong(CONSTANTES.codigoSiloCem4());
		final long CEM_5 = Long.parseLong(CONSTANTES.codigoSiloCem5());
		final long CEM_6 = Long.parseLong(CONSTANTES.codigoSiloCem6());
		final long CEM_7 = Long.parseLong(CONSTANTES.codigoSiloCem7());

		final long CRUDO_1 = Long.parseLong(CONSTANTES.codigoSiloCrudo1());
		final long CRUDO_2 = Long.parseLong(CONSTANTES.codigoSiloCrudo2());
		final long CRUDO_3 = Long.parseLong(CONSTANTES.codigoSiloCrudo3());
		final long CRUDO_4 = Long.parseLong(CONSTANTES.codigoSiloCrudo4());
		final long CRUDO_5 = Long.parseLong(CONSTANTES.codigoSiloCrudo5());
		final long CRUDO_6 = Long.parseLong(CONSTANTES.codigoSiloCrudo6());

		final long HOMOGENIZACION_1 = Long.parseLong(CONSTANTES.codigoSiloHomogen1());
		final long HOMOGENIZACION_2 = Long.parseLong(CONSTANTES.codigoSiloHomogen2());
		final long HOMOGENIZACION_3 = Long.parseLong(CONSTANTES.codigoSiloHomogen3());
		final long HOMOGENIZACION_7 = Long.parseLong(CONSTANTES.codigoSiloHomogen7());

		double cantidadAlmacenada = 0d;
		double sumaAlturas = 0d;
		final double cantidadAlmacenada2;
		for (Double altura : listaAlturas) {
			sumaAlturas += altura;
		}

		if (sumaAlturas == 0 || listaAlturas.size() == 0) {
			return 0d;
		}

		double promedioAlturas = sumaAlturas / listaAlturas.size();

		// AsyncCallback<Double> callback = new AsyncCallback<Double>() {
		//
		// public void onFailure(Throwable arg0) {
		// Window.alert("error al calcular la cantidad almacenada");
		//
		// }
		//
		// public void onSuccess(Double arg0) {
		// // cantidadAlmacenada2 = arg0;
		//
		// }
		//
		// };

		// servicioStock.obtenerCantidadAlmacenadaFormula(0d, callback);

		if (codigoSilo.longValue() == CEM_1 || codigoSilo.longValue() == CEM_2 || codigoSilo.longValue() == CEM_3
				|| codigoSilo.longValue() == CEM_4) {
			// (13-(h1+h2)/2)*96.15
			cantidadAlmacenada = (13d - promedioAlturas) * 96.15;
		}

		if (codigoSilo.longValue() == CEM_5) {
			// (21 – (h1+h2)/2) * 254 + 853
			cantidadAlmacenada = (21d - promedioAlturas) * 254d + 853d;
		}

		if (codigoSilo.longValue() == CEM_6) {
			// (22 – (h1+h2)/2) * 259 + 1818
			cantidadAlmacenada = (22d - promedioAlturas) * 259d + 1818d;
		}

		if (codigoSilo.longValue() == CEM_7) {
			// ( SI (h1+h2)/2 > 18.9, ((33 – (h1+h2)/2) * 239.4562 * 1.29885),
			// ((18.9 - (h1+h2)/2) * 3.1416 * 1.29885 + 3390.7) )

			if (promedioAlturas > 18.9d) {
				// (33 – (h1+h2)/2) * 239.4562 * 1.29885)
				cantidadAlmacenada = (33 - promedioAlturas) * 239.4562d * 1.29885d;
			} else {
				// ((18.9 - (h1+h2)/2) * 3.1416 * 1.29885 + 3390.7)
				cantidadAlmacenada = (18.9 - promedioAlturas) * 314.16d * 1.29885d + 3390.7d;
			}

		}

		if (codigoSilo.longValue() == CRUDO_1 || codigoSilo.longValue() == CRUDO_2 || codigoSilo.longValue() == CRUDO_3
				|| codigoSilo.longValue() == CRUDO_4 || codigoSilo.longValue() == CRUDO_5 || codigoSilo.longValue() == CRUDO_6) {
			// (9.85 – h) * 50.2655
			cantidadAlmacenada = (9.85d - listaAlturas.get(0)) * 50.2655d;
		}

		if (codigoSilo.longValue() == HOMOGENIZACION_1) {
			// (13.2 – h) * 116.8987
			cantidadAlmacenada = (13.2d - listaAlturas.get(0)) * 116.8987d;
		}

		if (codigoSilo.longValue() == HOMOGENIZACION_2) {
			// (17.68 – h) * 247.9
			cantidadAlmacenada = (17.68d - listaAlturas.get(0)) * 247.9d;
		}

		if (codigoSilo.longValue() == HOMOGENIZACION_3) {
			// TODO Capacidad de silo * %nivel del Silo (2000 * % nivel del
			// Silo)
		}

		if (codigoSilo.longValue() == HOMOGENIZACION_7) {
			// ((35.85 – (h1+h2)/2) * 197.3 * 1.5582
			cantidadAlmacenada = (35.85d - ((listaAlturas.get(0) + listaAlturas.get(1))) / 2) * 197.3d * 1.5582d;
		}

		return cantidadAlmacenada;
	}

	private List<Double> obtenerListaAlturas(final Record record, Long numeroAlturas) {
		List<Double> listaAlturas = new ArrayList<Double>();

		for (int j = 1; j <= numeroAlturas; j++) {
			String columna = COLUMNA_ALTURA + (j);
			String alturaStr = record.getAttribute(columna);
			if (alturaStr != null && alturaStr != "") {
				Double altura = Double.valueOf(alturaStr);
				listaAlturas.add(altura);
			}
		}
		return listaAlturas;
	}

	/**
	 * Metodo de actualizacion de la cantidad almacenada y el porcentaje de uso
	 * del silo
	 * 
	 * @param columnaAltura
	 */
	private CellSavedHandler obtenerCantidadCellSavedEvent() {
		CellSavedHandler cellSavedHandler = new CellSavedHandler() {
			public void onCellSaved(CellSavedEvent event) {
				Record record = event.getRecord();
				Double capacidad = 0d;
				try {
					String comlumnaCapacidad = record.getAttribute(COLUMNA_CAPACIDAD);
					if (comlumnaCapacidad != null && !comlumnaCapacidad.trim().equals("")) {
						capacidad = Double.valueOf(comlumnaCapacidad);
					}
					String valor = event.getNewValue().toString();
					record.setAttribute(COLUMNA_CANTIDAD_ALMACENADA, Double.valueOf(valor) * capacidad);
				} catch (NumberFormatException e) {

					Window.alert(MENSAJE_VALIDACION_ALTURA_VALOR_NUMERICO);
				}

			}
		};
		return cellSavedHandler;
	}

	private static StockServiceAsync servicioStock;

	public void setServicioStock(StockServiceAsync servicioStock) {
		this.servicioStock = servicioStock;

	}
}