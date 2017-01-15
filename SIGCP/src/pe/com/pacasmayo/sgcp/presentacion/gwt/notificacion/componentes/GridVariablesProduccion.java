package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.componentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificacionProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.ConstantesModuloNotificaciones;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.NotificacionService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.NotificacionServiceAsync;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Validaciones;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.RecordSummaryFunctionType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.ListGridSummaryField;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.events.ChangedHandler;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridVariablesProduccion.java
 * Modificado: Aug 4, 2010 10:20:55 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class GridVariablesProduccion extends ListGrid {

	private static final String HORAS_DE_OPER = "horas de oper";
	private static final String MENSAJE_VALORES_DEBEN_SER_NUMERICOS = "Los valores deben ser montos numéricos.";
	public static final String COLUMNA_OBSERVACION = "observacion";
	public static final String COLUMNA_CODIGO_NOTIFICACION_PRODUCCION = "codigoNotificacionProduccion";
	public static final String COLUMNA_CAMBIO_PRODUCCION_NORMAL = "cambioProduccionNormal";
	public static final String COLUMNA_CAMBIO_PRODUCCION_LAVADO = "cambioProduccionLavado";
	public static final String COLUMNA_CAMBIO_PRODUCCION_HORA = "cambioProduccionHora";
	public static final String COLUMNA_CODIGO_ORDEN_ANT_CAMBIO_PRODUCCION = "codigoOrdenAntCambProduc";
	public static final String COLUMNA_CODIGO_TURNO = "codigoTurno";
	public static final String COLUMNA_SILO = "silo";
	public static final String COLUMNA_CODIGO_SILO = "codigoSilo";
	public static final String COLUMNA_CODIGO_PRODUCTO = "codigoProducto";
	public static final String COLUMNA_ORDEN = "orden";
	public static final String COLUMNA_CODIGO_ORDEN = "codigoOrden";
	public static final String COLUMNA_HORA = "hora";
	public static final String COLUMNA_CODIGO_HORA = "codigoHora";
	public static final String COLUMNA_SELECCION = "seleccion";
	public static final String COLUMNA_CODIGO_LINEA_NEGOCIO = "codigoLineaNegocio";
	public static final String COLUMNA_NOMBRE_LINEA_NEGOCIO = "nombreLineaNegocio";

	public static final String COLUMNA_CODIGO_PLANTILLA = "codigoPlantillaProducto";
	public static final String COLUMNA_PLANTILLA = "nombrePlantillaProducto";

	private static final String COLUMNA_TOTAL = "total";

	private static ConstantesModuloNotificaciones CONSTANTES = GWT.create(ConstantesModuloNotificaciones.class);

	private static NotificacionServiceAsync servicioNotificacion = GWT.create(NotificacionService.class);

	protected Map<String, ListGridField> mapaColumnas = null;

	public GridVariablesProduccion() {

		setHeight(580);

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
		setShowGridSummary(true);
		setLoadingDataMessage(CONSTANTES.cargandoDatos());
		setEmptyMessage(CONSTANTES.noExistenDatosParaMostrar());

		mapaColumnas = new HashMap<String, ListGridField>();
	}

	public void agregarColumnasVariables(List<ColumnareporteDTO> columnasDTO) {
		if (columnasDTO.size() != 0) {
			int totalWidth = 10; // inicial
			int numeroColFijas = 17;
			ListGridField[] columnas = new ListGridField[columnasDTO.size() + numeroColFijas];

			int j = 0;

			ListGridField columnaCodigoHora = new ListGridField(COLUMNA_CODIGO_HORA);
			columnaCodigoHora.setHidden(true);
			columnas[j++] = columnaCodigoHora;

			ListGridField columnaCodigoProducto = new ListGridField(COLUMNA_CODIGO_PRODUCTO);
			columnaCodigoProducto.setType(ListGridFieldType.TEXT);
			columnaCodigoProducto.setHidden(true);
			columnas[j++] = columnaCodigoProducto;

			ListGridField columnaOrden = new ListGridField(COLUMNA_ORDEN, CONSTANTES.ordenDeProduccionGridVp());
			columnaOrden.setWidth(115);
			totalWidth += 115;
			columnaOrden.setAlign(Alignment.LEFT);
			columnaOrden.setShowGridSummary(false);
			columnaOrden.setType(ListGridFieldType.TEXT);
			columnas[j++] = columnaOrden;

			ListGridField columnaCodigoOrden = new ListGridField(COLUMNA_CODIGO_ORDEN);
			columnaCodigoOrden.setType(ListGridFieldType.INTEGER);
			columnaCodigoOrden.setHidden(true);
			columnas[j++] = columnaCodigoOrden;

			ListGridField columnaSilo = new ListGridField(COLUMNA_SILO, CONSTANTES.siloGridVp());
			columnaSilo.setWidth(90);
			totalWidth += 90;
			columnaSilo.setAlign(Alignment.LEFT);
			columnaSilo.setShowGridSummary(false);
			columnaSilo.setType(ListGridFieldType.TEXT);
			columnaSilo.setHidden(false);

			columnas[j++] = columnaSilo;

			ListGridField columnaCodigoSilo = new ListGridField(COLUMNA_CODIGO_SILO);
			columnaCodigoSilo.setType(ListGridFieldType.INTEGER);
			columnaCodigoSilo.setHidden(true);
			columnas[j++] = columnaCodigoSilo;

			ListGridField columnaPlantilla = new ListGridField(COLUMNA_PLANTILLA, CONSTANTES.plantillaProducto());
			columnaPlantilla.setWidth(150);
			totalWidth += 150;
			columnaPlantilla.setAlign(Alignment.LEFT);
			columnaPlantilla.setShowGridSummary(false);
			columnaPlantilla.setType(ListGridFieldType.TEXT);
			columnas[j++] = columnaPlantilla;

			ListGridField columnaCodigoPlantilla = new ListGridField(COLUMNA_CODIGO_PLANTILLA);
			columnaCodigoPlantilla.setType(ListGridFieldType.INTEGER);
			columnaCodigoPlantilla.setHidden(true);
			columnas[j++] = columnaCodigoPlantilla;

			ListGridField columnaCodigoNotificacion = new ListGridField(COLUMNA_CODIGO_NOTIFICACION_PRODUCCION);
			columnaCodigoNotificacion.setHidden(true);
			columnas[j++] = columnaCodigoNotificacion;

			ListGridField columnaCodigoLineaNegocio = new ListGridField(COLUMNA_CODIGO_LINEA_NEGOCIO);
			columnaCodigoLineaNegocio.setHidden(true);
			columnas[j++] = columnaCodigoLineaNegocio;

			ListGridField columnaNombreLineaNegocio = new ListGridField(COLUMNA_NOMBRE_LINEA_NEGOCIO);
			columnaNombreLineaNegocio.setHidden(true);
			columnas[j++] = columnaNombreLineaNegocio;

			ListGridField columnaCambioProduccionLavado = new ListGridField(COLUMNA_CAMBIO_PRODUCCION_LAVADO);
			columnaCambioProduccionLavado.setHidden(true);
			columnas[j++] = columnaCambioProduccionLavado;

			ListGridField columnaCodigoOrdenAntCambioProduc = new ListGridField(COLUMNA_CODIGO_ORDEN_ANT_CAMBIO_PRODUCCION);
			columnaCodigoOrdenAntCambioProduc.setHidden(true);
			columnas[j++] = columnaCodigoOrdenAntCambioProduc;

			ListGridField columnaCodigoTurno = new ListGridField(COLUMNA_CODIGO_TURNO);
			columnaCodigoTurno.setHidden(true);
			columnas[j++] = columnaCodigoTurno;

			ListGridField columnaCambioProduccionNormal = new ListGridField(COLUMNA_CAMBIO_PRODUCCION_NORMAL);
			columnaCambioProduccionNormal.setHidden(true);
			columnas[j++] = columnaCambioProduccionNormal;

			ListGridField columnaCambioProduccionHora = new ListGridField(COLUMNA_CAMBIO_PRODUCCION_HORA);
			columnaCambioProduccionHora.setHidden(true);
			columnas[j++] = columnaCambioProduccionHora;

			// Este for permite crear las 15 columnas correspondientes a las
			// variables del grid

			for (int i = 0; i < columnasDTO.size(); i++) {
				ColumnareporteDTO columnareporteDTO = columnasDTO.get(i);
				String nombre = columnareporteDTO.getNombreColumnareporte();
				ListGridField columnaVariable = new ListGridField(nombre, nombre);
				columnaVariable.setType(ListGridFieldType.FLOAT);
				columnaVariable.setAlign(Alignment.CENTER);
				columnaVariable.setCellAlign(Alignment.RIGHT);
				if (nombre.toLowerCase().equals(COLUMNA_HORA)) {
					columnaVariable.setWidth(38);
					totalWidth += 38;
					columnaVariable.setShowGridSummary(false);
					columnaVariable.setCanEdit(false);
				} else if (nombre.toLowerCase().startsWith(HORAS_DE_OPER, 0)) {
					columnaVariable.setWidth(50);
					totalWidth += 50;
					columnaVariable.setSummaryFunction(obtenerSumaColumnas(nombre));
					columnaVariable.setCanEdit(true);
				} else {
					columnaVariable.setCanEdit(true);
					columnaVariable.setWidth(55);
					totalWidth += 55;
					columnaVariable.setSummaryFunction(obtenerSumaColumnas(nombre));
					columnaVariable.setCellFormatter(obtenerFormatoCeldaConDosdecimales());
				}

				mapaColumnas.put(nombre, columnaVariable);

				addValorEditorHandler(columnaVariable);
				columnas[j++] = columnaVariable;
			}

			ListGridField columnaObservacion = new ListGridField(COLUMNA_OBSERVACION, CONSTANTES.observacionGridVp());
			columnaObservacion.setWidth(87);
			totalWidth += 87;
			columnaObservacion.setType(ListGridFieldType.TEXT);
			columnaObservacion.setAlign(Alignment.LEFT);
			columnaObservacion.setShowGridSummary(false);
			columnaObservacion.setCanEdit(true);
			columnaObservacion.setHidden(false);
			columnas[j++] = columnaObservacion;

			setFields(columnas);
			setWidth(totalWidth);
			setSelectionType(SelectionStyle.MULTIPLE);
			setShowGridSummary(true);
		} else {
			Window.alert(CONSTANTES.noExisteReporteEcsGridVp());
		}
	}

	private SummaryFunction obtenerSumaColumnas(final String columna) {
		SummaryFunction summaryFunction = new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				Double suma = 0d;
				String cantidad;
				for (int i = 0; i < records.length; i++) {
					Record record = records[i];
					try {
						cantidad = record.getAttribute(columna);
						if (cantidad != null && !cantidad.trim().equals("") && !cantidad.trim().equals("-")
								&& !cantidad.trim().equals("&nbsp;")) {
							suma += Double.parseDouble(cantidad);
						}
					} catch (Exception e) {
						// es null
						continue;
					}
				}

				return suma;
			}
		};
		return summaryFunction;
	}

	public void agregarColumnasVariablesSubtotal(List<ColumnareporteDTO> columnasDTO) {
		if (columnasDTO.size() != 0) {
			int totalWidth = 10; // inicial
			int numeroColFijas = 17;
			ListGridField[] columnas = new ListGridField[columnasDTO.size() + numeroColFijas];

			int j = 0;

			ListGridField columnaOrden = new ListGridField(COLUMNA_ORDEN, CONSTANTES.ordenDeProduccionGridVp());
			columnaOrden.setWidth(130);
			totalWidth += 130;
			columnaOrden.setAlign(Alignment.LEFT);
			columnaOrden.setShowGridSummary(false);
			columnaOrden.setType(ListGridFieldType.TEXT);
			columnas[j++] = columnaOrden;

			// Este for permite crear las 15 columnas correspondientes a las
			// variables del grid

			for (int i = 0; i < columnasDTO.size(); i++) {
				ColumnareporteDTO columnareporteDTO = columnasDTO.get(i);
				String nombre = columnareporteDTO.getNombreColumnareporte();
				ListGridField columnaVariable = new ListGridField(nombre, nombre);
				columnaVariable.setType(ListGridFieldType.FLOAT);
				columnaVariable.setAlign(Alignment.CENTER);
				columnaVariable.setCellAlign(Alignment.RIGHT);
				if (nombre.toLowerCase().equals(COLUMNA_HORA)) {
					continue;
				} else if (nombre.toLowerCase().startsWith(HORAS_DE_OPER, 0)) {
					columnaVariable.setWidth(50);
					totalWidth += 50;
					columnaVariable.setCanEdit(false);
				} else {
					columnaVariable.setCanEdit(false);
					columnaVariable.setWidth(55);
					totalWidth += 55;
					columnaVariable.setSummaryFunction(obtenerSumaColumnas(nombre));
					columnaVariable.setCellFormatter(obtenerFormatoCeldaConDosdecimales());
				}
				addValorEditorHandler(columnaVariable);
				columnas[j++] = columnaVariable;
			}

			ListGridField total = new ListGridSummaryField(COLUMNA_TOTAL, CONSTANTES.tituloColTotal());
			total.setWidth(60);
			total.setRecordSummaryFunction(RecordSummaryFunctionType.SUM);
			total.setAlign(Alignment.CENTER);
			total.setCellAlign(Alignment.RIGHT);
			total.setSummaryFunction(obtenerSumaColumnas(COLUMNA_TOTAL));
			total.setCellFormatter(obtenerFormatoCeldaConDosdecimales());
			// addValorEditorHandler(total);
			columnas[j++] = total;
			totalWidth += 70;

			setFields(columnas);
			setWidth(totalWidth);
			setShowGridSummary(true);
		} else {
			Window.alert(CONSTANTES.noExisteReporteEcsGridVp());
		}
	}

	public static CellFormatter obtenerFormatoCeldaConDosdecimales() {
		CellFormatter cellFormatter = new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
					double doubleValue = ((Number) value).doubleValue();

					return nf.format(doubleValue);
				} catch (Exception e) {
					return value.toString();
				}
			}
		};
		return cellFormatter;
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

	public void cargarGrid(List<ColumnareporteDTO> columnas, List<DatoReporteDTO> datos) {
		limpiarGrid();

		if (datos.size() != 0) {
			for (DatoReporteDTO datoreporteDTO : datos) {
				ListGridRecord record = new ListGridRecord();

				record.setAttribute(COLUMNA_HORA, datoreporteDTO.getHora());
				record.setAttribute(COLUMNA_CODIGO_HORA, datoreporteDTO.getPkCodigoHora());
				record.setAttribute(COLUMNA_CODIGO_SILO, datoreporteDTO.getCodigoSilo());
				record.setAttribute(COLUMNA_CODIGO_LINEA_NEGOCIO, datoreporteDTO.getCodigoLineaNegocio());
				record.setAttribute(COLUMNA_NOMBRE_LINEA_NEGOCIO, datoreporteDTO.getNombreLineaNegocio());
				record.setAttribute(COLUMNA_CODIGO_NOTIFICACION_PRODUCCION, datoreporteDTO.getCodigoNotificacionPlanta());
				record.setAttribute(COLUMNA_CODIGO_TURNO, datoreporteDTO.getCodigoTurno());
				record.setAttribute(COLUMNA_CAMBIO_PRODUCCION_NORMAL, datoreporteDTO.getCambioProduccionNormal());
				record.setAttribute(COLUMNA_CAMBIO_PRODUCCION_LAVADO, datoreporteDTO.getCambioProduccionLavado());
				record.setAttribute(COLUMNA_CODIGO_ORDEN_ANT_CAMBIO_PRODUCCION, datoreporteDTO.getCodigoSiloAntCambioProduc());
				record.setAttribute(COLUMNA_CAMBIO_PRODUCCION_HORA, datoreporteDTO.getCambioProduccionHora());
				record.setAttribute(COLUMNA_SILO, datoreporteDTO.getNombreSilo());
				record.setAttribute(COLUMNA_CODIGO_ORDEN, datoreporteDTO.getCodigoOrden());
				record.setAttribute(COLUMNA_CODIGO_PRODUCTO, datoreporteDTO.getCodigoProducto());
				record.setAttribute(COLUMNA_ORDEN, datoreporteDTO.getNumeroOrden());
				record.setAttribute(COLUMNA_OBSERVACION, datoreporteDTO.getObservaciones());

				record.setAttribute(COLUMNA_CODIGO_PLANTILLA, datoreporteDTO.getCodigoPlantilla());
				record.setAttribute(COLUMNA_PLANTILLA, datoreporteDTO.getPlantilla());

				for (ColumnareporteDTO columnareporteDTO : columnas) {
					short pos = columnareporteDTO.getPosicionColumnareporte();
					String nombre = columnareporteDTO.getNombreColumnareporte();

					Double textoVariable = obtenerTextoVariable(pos, datoreporteDTO);
					double valorDatoReporte = textoVariable == null ? 0d : textoVariable;

					if (valorDatoReporte == 0) {
						record.setAttribute(nombre, "-");
					} else {
						record.setAttribute(nombre, valorDatoReporte);
					}
				}

				addData(record);
			}

			setShowGridSummary(true);
		} else {
			Window.alert(CONSTANTES.noExisteReporteEcsGridVp());
		}
	}

	protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
		for (String nombreHeader : mapaColumnas.keySet()) {
			if (getFieldName(colNum).equals(nombreHeader)) {
				String nombreComponente = record.getAttribute(nombreHeader);
				if (nombreComponente.equals("-")) {
					return "font-weight:bold; color:red;";
				}
			}
		}
		return super.getCellCSSText(record, rowNum, colNum);
	}

	public void cargarGridSubtotales(List<ColumnareporteDTO> columnas, List<DatoReporteDTO> datos) {
		limpiarGrid();

		if (datos.size() != 0) {
			for (DatoReporteDTO datoreporteDTO : datos) {
				ListGridRecord record = new ListGridRecord();

				record.setAttribute(COLUMNA_ORDEN, datoreporteDTO.getNumeroOrden());

				for (ColumnareporteDTO columnareporteDTO : columnas) {
					short pos = columnareporteDTO.getPosicionColumnareporte();
					String nombre = columnareporteDTO.getNombreColumnareporte();

					Double textoVariable = obtenerTextoVariable(pos, datoreporteDTO);
					double valorDatoReporte = textoVariable == null ? 0d : textoVariable;

					record.setAttribute(nombre, valorDatoReporte);

				}
				addData(record);
			}
			// setShowGridSummary(true);
		} else {
			Window.alert(CONSTANTES.noExisteReporteEcsGridVp());
		}
	}

	public List<DatoReporteDTO> obtenerListaSubtotales(List<ColumnareporteDTO> columnas) {
		List<DatoReporteDTO> datos = exportarGrid(columnas);
		Map<String, DatoReporteDTO> mapaSubtotales = new HashMap<String, DatoReporteDTO>();
		for (DatoReporteDTO datoreporteDTO : datos) {
			DatoReporteDTO datoReporteSubtotal = mapaSubtotales.get(datoreporteDTO.getNumeroOrden());
			if (datoReporteSubtotal == null) {
				datoReporteSubtotal = datoreporteDTO;
				mapaSubtotales.put(datoreporteDTO.getNumeroOrden(), datoReporteSubtotal);
			} else {
				sumarValoresDatoReporte(datoReporteSubtotal, datoreporteDTO);
			}
		}

		return new ArrayList<DatoReporteDTO>(mapaSubtotales.values());
	}

	/**
	 * Suma los valores dos dos datosreporte y los almacena el en objeto
	 * subtotal
	 * 
	 * @param subtotal DatoReporteDTO
	 * @param datoreporte DatoReporteDTO
	 */
	private void sumarValoresDatoReporte(DatoReporteDTO subtotal, DatoReporteDTO datoreporte) {
		subtotal.setVariable1Datoreporte(subtotal.getVariable1Datoreporte() + datoreporte.getVariable1Datoreporte());
		subtotal.setVariable2Datoreporte(subtotal.getVariable2Datoreporte() + datoreporte.getVariable2Datoreporte());
		subtotal.setVariable3Datoreporte(subtotal.getVariable3Datoreporte() + datoreporte.getVariable3Datoreporte());
		subtotal.setVariable4Datoreporte(subtotal.getVariable4Datoreporte() + datoreporte.getVariable4Datoreporte());
		subtotal.setVariable5Datoreporte(subtotal.getVariable5Datoreporte() + datoreporte.getVariable5Datoreporte());
		subtotal.setVariable6Datoreporte(subtotal.getVariable6Datoreporte() + datoreporte.getVariable6Datoreporte());
		subtotal.setVariable7Datoreporte(subtotal.getVariable7Datoreporte() + datoreporte.getVariable7Datoreporte());
		subtotal.setVariable8Datoreporte(subtotal.getVariable8Datoreporte() + datoreporte.getVariable8Datoreporte());
		subtotal.setVariable9Datoreporte(subtotal.getVariable9Datoreporte() + datoreporte.getVariable9Datoreporte());
		subtotal.setVariable10Datoreporte(subtotal.getVariable10Datoreporte() + datoreporte.getVariable10Datoreporte());
		subtotal.setVariable11Datoreporte(subtotal.getVariable11Datoreporte() + datoreporte.getVariable11Datoreporte());
		subtotal.setVariable12Datoreporte(subtotal.getVariable12Datoreporte() + datoreporte.getVariable12Datoreporte());
		subtotal.setVariable13Datoreporte(subtotal.getVariable13Datoreporte() + datoreporte.getVariable13Datoreporte());
		subtotal.setVariable14Datoreporte(subtotal.getVariable14Datoreporte() + datoreporte.getVariable14Datoreporte());
		subtotal.setVariable15Datoreporte(subtotal.getVariable15Datoreporte() + datoreporte.getVariable15Datoreporte());
	}

	/**
	 * retorna el valor de una celda de una columna dada la possicion de la
	 * misma dentro del reporte
	 * 
	 * @param pos posiscion de la columna
	 * @param datoreporte objeto que contiene el valor de los registros del grid
	 * @return double
	 */
	private Double obtenerTextoVariable(Short pos, DatoReporteDTO datoreporte) {

		switch (pos) {
		case 0:
			return datoreporte.getHora().doubleValue();
		case 1:
			return datoreporte.getVariable1Datoreporte();
		case 2:
			return datoreporte.getVariable2Datoreporte();
		case 3:
			return datoreporte.getVariable3Datoreporte();
		case 4:
			return datoreporte.getVariable4Datoreporte();
		case 5:
			return datoreporte.getVariable5Datoreporte();
		case 6:
			return datoreporte.getVariable6Datoreporte();
		case 7:
			return datoreporte.getVariable7Datoreporte();
		case 8:
			return datoreporte.getVariable8Datoreporte();
		case 9:
			return datoreporte.getVariable9Datoreporte();
		case 10:
			return datoreporte.getVariable10Datoreporte();
		case 11:
			return datoreporte.getVariable11Datoreporte();
		case 12:
			return datoreporte.getVariable12Datoreporte();
		case 13:
			return datoreporte.getVariable13Datoreporte();
		case 14:
			return datoreporte.getVariable14Datoreporte();
		case 15:
			return datoreporte.getVariable15Datoreporte();
		}
		return 0d;
	}

	/**
	 * permite almcenar un valor de una celda del grid dada la posicion de la
	 * columna del reporte
	 * 
	 * @param pos posicion de la columna
	 * @param valor valor a almacenar
	 * @param datoreporte objeto donde se almacenara el valor de la celda
	 */
	public void setValorVariable(Short pos, double valor, DatoReporteDTO datoreporte) {

		switch (pos) {
		case 1:
			datoreporte.setVariable1Datoreporte(valor);
			break;
		case 2:
			datoreporte.setVariable2Datoreporte(valor);
			break;
		case 3:
			datoreporte.setVariable3Datoreporte(valor);
			break;
		case 4:
			datoreporte.setVariable4Datoreporte(valor);
			break;
		case 5:
			datoreporte.setVariable5Datoreporte(valor);
			break;
		case 6:
			datoreporte.setVariable6Datoreporte(valor);
			break;
		case 7:
			datoreporte.setVariable7Datoreporte(valor);
			break;
		case 8:
			datoreporte.setVariable8Datoreporte(valor);
			break;
		case 9:
			datoreporte.setVariable9Datoreporte(valor);
			break;
		case 10:
			datoreporte.setVariable10Datoreporte(valor);
			break;
		case 11:
			datoreporte.setVariable11Datoreporte(valor);
			break;
		case 12:
			datoreporte.setVariable12Datoreporte(valor);
			break;
		case 13:
			datoreporte.setVariable13Datoreporte(valor);
			break;
		case 14:
			datoreporte.setVariable14Datoreporte(valor);
			break;
		case 15:
			datoreporte.setVariable15Datoreporte(valor);
			break;
		}
	}

	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			removeData(listGridRecord);
		}
	}

	/**
	 * Dada la lista de columnas, retorna un lista con los datos que almacena el
	 * grid
	 * 
	 * @param columnas lista de col del reporte ecs
	 * @return List<DatoReporteDTO>
	 */
	public List<DatoReporteDTO> exportarGrid(List<ColumnareporteDTO> columnas) {

		List<DatoReporteDTO> listaDTO = new ArrayList<DatoReporteDTO>();
		RecordList listaRecord = getRecordList();

		for (int i = 0; i < listaRecord.getLength(); i++) {

			DatoReporteDTO datoreporte = new DatoReporteDTO();
			Record record = listaRecord.get(i);

			if (record.getAttribute(COLUMNA_CODIGO_ORDEN) == null) {
				return null;
			}

			if (record.getAttribute(COLUMNA_CODIGO_PLANTILLA) == null) {
				return null;
			}

			datoreporte.setPkCodigoHora(Long.valueOf(record.getAttribute(COLUMNA_CODIGO_HORA)));

			datoreporte.setHora(Short.valueOf(record.getAttribute(COLUMNA_HORA)));

			datoreporte.setCodigoOrden(Integer.valueOf(record.getAttribute(COLUMNA_CODIGO_ORDEN)));
			datoreporte.setNumeroOrden(String.valueOf(record.getAttribute(COLUMNA_ORDEN)));

			datoreporte.setCodigoPlantilla(Integer.valueOf(record.getAttribute(COLUMNA_CODIGO_PLANTILLA)));
			datoreporte.setPlantilla(String.valueOf(record.getAttribute(COLUMNA_PLANTILLA)));

			String codigoSiloStr = record.getAttribute(COLUMNA_CODIGO_SILO);
			if (codigoSiloStr != null) {
				datoreporte.setCodigoSilo(Integer.valueOf(codigoSiloStr));
				datoreporte.setNombreSilo(String.valueOf(record.getAttribute(COLUMNA_SILO)));
			}

			datoreporte.setCambioProduccionHora(record.getAttribute(COLUMNA_CAMBIO_PRODUCCION_HORA));
			datoreporte.setCambioProduccionNormal(new Boolean(record.getAttribute(COLUMNA_CAMBIO_PRODUCCION_NORMAL)));
			datoreporte.setCambioProduccionLavado(new Boolean(record.getAttribute(COLUMNA_CAMBIO_PRODUCCION_LAVADO)));
			String val = record.getAttribute(COLUMNA_CODIGO_ORDEN_ANT_CAMBIO_PRODUCCION);
			if (val != null) {
				datoreporte.setCodigoSiloAntCambioProduc(Long.valueOf(val));
			}

			String turno = record.getAttribute(COLUMNA_CODIGO_TURNO);
			if (turno != null) {
				datoreporte.setCodigoTurno(Long.valueOf(turno));
			}

			if (record.getAttribute(COLUMNA_OBSERVACION) != null) {
				datoreporte.setObservaciones(String.valueOf(record.getAttribute(COLUMNA_OBSERVACION)));
			}

			for (ColumnareporteDTO columna : columnas) {
				short pos = columna.getPosicionColumnareporte();

				String nombre = columna.getNombreColumnareporte();

				if (!record.getAttribute(nombre).equals("-")) {
					double valor = Double.valueOf(record.getAttribute(nombre));
					setValorVariable(pos, valor, datoreporte);
				}
			}

			if (record.getAttribute(COLUMNA_CODIGO_PRODUCTO) != null) {
				datoreporte.setCodigoProducto(Integer.valueOf(record.getAttribute(COLUMNA_CODIGO_PRODUCTO)));
			}

			listaDTO.add(datoreporte);
		}
		return listaDTO;
	}

	/**
	 * Metodo que carga los codigos de notificaciones de produccion en el grid,
	 * despues de haberse realizado el registro. Esto para poder realizar el
	 * cambio de produccion.
	 * 
	 * @param codigoNotificacionDiaria
	 */
	public static void cargarCodigosNotificaciones(Long codigoNotificacionDiaria, final GridVariablesProduccion gridVarProd) {

		servicioNotificacion.obtenerNotificacionesProduccion(codigoNotificacionDiaria,
				new AsyncCallback<List<NotificacionProduccionDTO>>() {

					public void onFailure(Throwable throwable) {
						Window.alert(CONSTANTES.errorInesperado());
						GWT.log("Error en metodo: cargarCodigosNotificaciones", throwable);
					}

					public void onSuccess(List<NotificacionProduccionDTO> notificacionesProduccionDTO) {
						ListGridRecord[] records = gridVarProd.getRecords();

						for (int i = 0; i < 24; i++) {

							ListGridRecord record = records[i];
							NotificacionProduccionDTO notificacionProduccionDTO = notificacionesProduccionDTO.get(i);

							Integer codigoNotificacionProduccion = notificacionProduccionDTO.getPkCodigoNotificacionproduccio()
									.intValue();

							record.setAttribute(COLUMNA_CODIGO_NOTIFICACION_PRODUCCION, codigoNotificacionProduccion.toString());
						}
					}
				});
	}

	/**
	 * Evento para validar los valores de las celdas editables cuando estas
	 * cambian
	 * 
	 * @param listGridField compa del grid al que se le agregara la validacion
	 */
	private void addValorEditorHandler(ListGridField listGridField) {
		listGridField.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {

				if (event.getValue() != null) {
					String celda = (String) event.getValue();

					if (!Validaciones.isDouble(celda)) {
						Window.alert(MENSAJE_VALORES_DEBEN_SER_NUMERICOS);
						cancelEditing();
					}

					if (Double.parseDouble(celda.toString()) < 0) {
						Window.alert(MENSAJE_VALORES_DEBEN_SER_NUMERICOS);
						cancelEditing();
					}
				} else {
					cancelEditing();
				}

			}

		});

	}
}