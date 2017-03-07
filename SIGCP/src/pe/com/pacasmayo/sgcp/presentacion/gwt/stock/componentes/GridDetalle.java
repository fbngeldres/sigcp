package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.componentes;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridDetalleDiaSilo.java
 * Modificado: Apr 15, 2010 3:45:27 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class GridDetalle extends ListGrid {

	private static final String COLUMNA_SILO = "Silo";
	private static final String COLUMNA_PRODUCTO = "Producto";
	private static final String COLUMNAS_ALTURAS = "Altura de Silos (m)";
	private static final String COLUMNA_ALTURA = "H";
	private static Integer cantidadMaximaAlturas = 0;
	private static final String COLUMNA_CANTIDAD = "Cantidad (Ton)";
	private static final String COLUMNA_CAPACIDAD = "Capacidad (Ton)";
	private static final String COLUMNA_PORCENTAJE_USO = "% de Uso";

	// Este mapa representa las columnas de las alturas, y tiene como clave el
	// num de altura,
	// y como valor a cada uno de los objetos ListGridField que seran columna de
	// altura
	private Map<Long, ListGridField> mapaAlturas = new LinkedHashMap<Long, ListGridField>();

	// Este mapa representa las columnas del grid, y tiene como clave el num
	// columna, y como valor a cada uno de los objetos ListGridField
	private Map<String, ListGridField> mapaColumnas = new LinkedHashMap<String, ListGridField>();

	/**
	 * Constructor del grid de detalle del silo por dia
	 * 
	 * @param listaRegistros: Esta lista contiene la lista de
	 *            RegistroTablaMedicionDiaDTO que permitiran costruir las
	 *            columnas
	 */
	public GridDetalle(List<RegistroTablaMedicionDiaDTO> listaRegistros) {
		cantidadMaximaAlturas = 0;
		ListGridField silo = new ListGridField();
		silo.setType(ListGridFieldType.INTEGER);
		silo.setCanEdit(false);
		silo.setName(COLUMNA_SILO);
		silo.setAlign(Alignment.CENTER);
		setAutoFitData(Autofit.VERTICAL);
		mapaColumnas.put(COLUMNA_SILO, silo); // Agregamos la columna al
		// mapa de columnas

		ListGridField producto = new ListGridField();
		producto.setType(ListGridFieldType.TEXT);
		producto.setCanEdit(false);
		producto.setName(COLUMNA_PRODUCTO);
		producto.setAlign(Alignment.CENTER);
		mapaColumnas.put(COLUMNA_PRODUCTO, producto);// Agregamos la columna
		// al mapa de columnas

		// Iteramos sobre la lista de registros para averiguar la cantidad
		// maxima de medidas de alturas, y poder construir N columnas de alturas
		// para todo el grid
		for (Iterator<RegistroTablaMedicionDiaDTO> iterator = listaRegistros.iterator(); iterator.hasNext();) {
			RegistroTablaMedicionDiaDTO registro = iterator.next();

			List<Double> listaAlturas = registro.getListaAlturas();
			if (listaAlturas != null) {

				// En la variable cantidadMaximaAlturas capturamos la cantidad
				// de
				// columnas de altura de silo que tendra el grid
				int size = listaAlturas.size();
				GWT.log("alturas: " + size);
				if (size > cantidadMaximaAlturas) {
					cantidadMaximaAlturas = size;
				}
			}
		}

		// En este for agregamos dinamicamente las columnas al mapa de columnas
		for (int i = 1; i <= cantidadMaximaAlturas; i++) {
			ListGridField columnaAltura = new ListGridField(COLUMNA_ALTURA + i);
			columnaAltura.setWidth(50);
			columnaAltura.setAlign(Alignment.CENTER);
			mapaColumnas.put(COLUMNA_ALTURA + i, columnaAltura);

		}

		ListGridField cantidad = new ListGridField();
		cantidad.setType(ListGridFieldType.FLOAT);
		cantidad.setCanEdit(false);
		cantidad.setName(COLUMNA_CANTIDAD);
		cantidad.setAlign(Alignment.CENTER);
		mapaColumnas.put(COLUMNA_CANTIDAD, cantidad);
		cantidad.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat("###,###.##");
					return nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}
			}
		});

		ListGridField capacidad = new ListGridField();
		capacidad.setType(ListGridFieldType.FLOAT);
		capacidad.setCanEdit(false);
		capacidad.setName(COLUMNA_CAPACIDAD);
		capacidad.setAlign(Alignment.CENTER);
		mapaColumnas.put(COLUMNA_CAPACIDAD, capacidad);
		capacidad.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat("###,###.##");
					return nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}
			}
		});

		ListGridField porcentaje = new ListGridField();
		porcentaje.setType(ListGridFieldType.FLOAT);
		porcentaje.setCanEdit(false);
		porcentaje.setName(COLUMNA_PORCENTAJE_USO);
		porcentaje.setAlign(Alignment.CENTER);
		mapaColumnas.put(COLUMNA_PORCENTAJE_USO, porcentaje);
		porcentaje.setCellFormatter(new CellFormatter() {
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
		});

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
		setCanEdit(false);
		setWidth100();
	}

	@Override
	protected String getGridSummary(ListGridField field) {
		String valueStr = super.getGridSummary(field);
		try {
			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			Double d = nf.parse(valueStr);
			String format = nf.format(d);
			GWT.log(format);
			return format;
		} catch (Exception e) {
			return valueStr;
		}
	}

	/**
	 * Metodo que agrega al grid los registros creando las columnas
	 * dinamicamente
	 * 
	 * @param listaRegistros
	 */
	public void cargarGridDetalle(List<RegistroTablaMedicionDiaDTO> listaRegistros) {

		// Recorremos la lista de registros recibida
		for (Iterator<RegistroTablaMedicionDiaDTO> iterator = listaRegistros.iterator(); iterator.hasNext();) {

			// Por cada registro de la lista recibida agregamos la columna fecha
			// y creamos un record que sera agregado al grid
			RegistroTablaMedicionDiaDTO registro = iterator.next();

			List<Double> listaAlturas = registro.getListaAlturas();

			ListGridRecord record = new ListGridRecord();

			record.setAttribute(COLUMNA_SILO, registro.getNombreSilo());

			record.setAttribute(COLUMNA_PRODUCTO, registro.getNombreProducto());

			// Recorremos esa lista y agregamos las columnas dinamicamente, para
			// finalmente agregar el registro al grid
			for (int i = 0; i < listaAlturas.size(); i++) {

				String columna = COLUMNA_ALTURA + (i + 1);
				record.setAttribute(columna, listaAlturas.get(i));
			}

			record.setAttribute(COLUMNA_CANTIDAD, registro.getCantidad());
			record.setAttribute(COLUMNA_CAPACIDAD, registro.getCapacidad());
			record.setAttribute(COLUMNA_PORCENTAJE_USO, registro.getPorcentajeUso());

			addData(record);
		}
	}

	public Map<Long, ListGridField> getMapaAlturas() {
		return mapaAlturas;
	}

	public void setMapaAlturas(Map<Long, ListGridField> mapaAlturas) {
		this.mapaAlturas = mapaAlturas;
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
}