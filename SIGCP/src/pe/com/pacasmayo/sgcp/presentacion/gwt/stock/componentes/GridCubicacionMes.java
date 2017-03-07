package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.componentes;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TablacubicacionDatasource.java
 * Modificado: Jun 14, 2010 5:06:43 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class GridCubicacionMes extends ListGrid {

	private static final String FECHA = "fecha";
	private static final String CODIGO_LINEA_NEGOCIO = "codigoLineaNegocio";
	private static final String CODIGO_PRODUCTO = "codigoProducto";
	private static final String CODIGO_PROCESO = "codigoProceso";
	private static final String CODIGO_ESTADO = "codigoEstado";
	private static final String HUMEDAD = "humedad";

	private static final String PATRON_NUMERICO_INTERFAZ = "###,###.##";

	private static final String COLUMNA_TIPO_MEDIO = "tipoMedio";
	private static final String TITULO_TIPO_MEDIO = "T. Medio";

	private static final String COLUMNA_CODIGO_MEDIO_ALMACENAMIENTO = "codigoMedioAlmacenamiento";
	private static final String COLUMNA_MEDIO_ALMACENAMIENTO = "medioAlmacenamiento";
	private static final String TITULO_MEDIO_ALMACENAMIENTO = "Medio Almac.";

	private static final String COLUMNA_AREA_CRESTA = "areaCresta";
	private static final String TITULO_AREA_CRESTA = "&Aacute; Cresta(m2)";

	private static final String COLUMNA_AREA_PIE = "areaPie";
	private static final String TITULO_AREA_PIE = "&Aacute; Pie(m2)";

	private static final String COLUMNA_DIF_NIVEL = "difNivel";
	private static final String TITULO_DIF_NIVEL = "Dif Nivel(mts)";

	private static final String COLUMNA_VOLUMEN = "volumen";
	private static final String TITULO_VOLUMEN = "Volumen(m3)";

	private static final String TITULO_UNIDAD_KG = "Unidad Kg(m)";
	private static final String COLUMNA_UNIDAD_KG = "unidadKg";

	private static final String TITULO_ALT_LIBRE_PROMEDIO = "Alt Libre Prom(m)";
	private static final String COLUMNA_ALT_LIBRE_PROMEDIO = "altLibrePromedio";

	private static final String TITULO_ALT_LADO_CLINKER = "Alt L. Clk(m)";
	private static final String COLUMNA_ALT_LADO_CLINKER = "altLadoClinker";

	private static final String TITULO_ALT_CENTRAL = "Alt Central(m)";
	private static final String COLUMNA_ALT_CENTRAL = "altCentral";

	private static final String TITULO_ALT_LADO_CARBON = "Alt L. Carb&oacute;n(m)";
	private static final String COLUMNA_ALT_LADO_CARBON = "altLadoCarbon";

	private static final String TITULO_RELACION = "Relaci&oacute;n";
	private static final String COLUMNA_RELACION = "relacion";

	private static final String COLUMNA_AREA_OCUPADA = "areaOcupada";
	private static final String TITULO_AREA_OCUPADA = "&Aacute; Ocupada(m2)";

	private static final String TITULO_OBSERVACIONES = "Obs";
	private static final String COLUMNA_OBSERVACIONES = "observaciones";

	private static final String TITULO_TM = "Cant. TM";
	private static final String COLUMNA_TM = "cantTm";

	private static final String TITULO_DENSIDAD = "Densidad";
	private static final String COLUMNA_DENSIDAD = "densidad";

	/**
	 * Constructor del grid de programas con su estructura de columnas
	 */
	public GridCubicacionMes(boolean esModificar) {

		setSaveLocally(true);
		setCanRemoveRecords(true);
		setAutoFetchData(true);
		setWidth100();
		setHeaderHeight(35);
		setHeight(185);
		setShowGridSummary(true);
		setModalEditing(false);
		setCanReorderFields(false);
		setCanGroupBy(false);
		setCanResizeFields(true);
		setCanFreezeFields(false);
		setShowHeaderContextMenu(false);

		ListGridField codigoMedioAlmacenamiento = new ListGridField(COLUMNA_CODIGO_MEDIO_ALMACENAMIENTO);
		codigoMedioAlmacenamiento.setType(ListGridFieldType.INTEGER);
		codigoMedioAlmacenamiento.setHidden(true);

		ListGridField codigoLineanegocio = new ListGridField(CODIGO_LINEA_NEGOCIO);
		codigoLineanegocio.setType(ListGridFieldType.INTEGER);
		codigoLineanegocio.setHidden(true);

		ListGridField codigoProducto = new ListGridField(CODIGO_PRODUCTO);
		codigoProducto.setType(ListGridFieldType.INTEGER);
		codigoProducto.setHidden(true);

		ListGridField codigoProceso = new ListGridField(CODIGO_PROCESO);
		codigoProceso.setType(ListGridFieldType.INTEGER);
		codigoProceso.setHidden(true);

		ListGridField codigoEstado = new ListGridField(CODIGO_ESTADO);
		codigoEstado.setType(ListGridFieldType.INTEGER);
		codigoEstado.setHidden(true);

		ListGridField fecha = new ListGridField(FECHA);
		fecha.setType(ListGridFieldType.DATE);
		fecha.setHidden(true);

		ListGridField humedad = new ListGridField(HUMEDAD);
		humedad.setType(ListGridFieldType.FLOAT);
		humedad.setHidden(true);

		int tamanoAreas = 69;
		int tamanoObs = 50;
		int tamanoMedioAlmac = 145;
		int tamanoHidden = 50;

		ListGridField tipoMedio = new ListGridField(COLUMNA_TIPO_MEDIO, TITULO_TIPO_MEDIO);
		tipoMedio.setWidth(tamanoAreas);
		tipoMedio.setAlign(Alignment.CENTER);
		tipoMedio.setCellAlign(Alignment.LEFT);

		ListGridField medioAlmacenamiento = new ListGridField(COLUMNA_MEDIO_ALMACENAMIENTO, TITULO_MEDIO_ALMACENAMIENTO);
		medioAlmacenamiento.setWidth(tamanoMedioAlmac);
		medioAlmacenamiento.setAlign(Alignment.CENTER);
		medioAlmacenamiento.setCellAlign(Alignment.LEFT);

		ListGridField areaCresta = new ListGridField(COLUMNA_AREA_CRESTA, TITULO_AREA_CRESTA);
		areaCresta.setType(ListGridFieldType.FLOAT);
		areaCresta.setShowGridSummary(false);
		addNumberCellFormatter(areaCresta);
		areaCresta.setWidth(tamanoAreas);
		areaCresta.setAlign(Alignment.CENTER);
		areaCresta.setCellAlign(Alignment.RIGHT);

		ListGridField areaPie = new ListGridField(COLUMNA_AREA_PIE, TITULO_AREA_PIE);
		areaPie.setType(ListGridFieldType.FLOAT);
		areaPie.setShowGridSummary(false);
		addNumberCellFormatter(areaPie);
		areaPie.setWidth(tamanoAreas);
		areaPie.setAlign(Alignment.CENTER);
		areaPie.setCellAlign(Alignment.RIGHT);

		ListGridField difNivel = new ListGridField(COLUMNA_DIF_NIVEL, TITULO_DIF_NIVEL);
		difNivel.setType(ListGridFieldType.FLOAT);
		difNivel.setShowGridSummary(false);
		addNumberCellFormatter(difNivel);
		difNivel.setWidth(tamanoAreas);
		difNivel.setAlign(Alignment.CENTER);
		difNivel.setCellAlign(Alignment.RIGHT);

		ListGridField volumen = new ListGridField(COLUMNA_VOLUMEN, TITULO_VOLUMEN);
		volumen.setType(ListGridFieldType.FLOAT);
		volumen.setShowGridSummary(true);
		volumen.setSummaryFunction(SummaryFunctionType.SUM);
		addNumberCellFormatter(volumen);
		volumen.setWidth(tamanoAreas);
		volumen.setAlign(Alignment.CENTER);
		volumen.setCellAlign(Alignment.RIGHT);

		ListGridField densidad = new ListGridField(COLUMNA_DENSIDAD, TITULO_DENSIDAD);
		densidad.setType(ListGridFieldType.FLOAT);
		densidad.setHidden(!esModificar);
		addNumberCellFormatter(densidad);
		densidad.setShowGridSummary(false);
		densidad.setWidth(tamanoAreas);
		densidad.setAlign(Alignment.CENTER);
		densidad.setCellAlign(Alignment.RIGHT);

		ListGridField cantTm = new ListGridField(COLUMNA_TM, TITULO_TM);
		cantTm.setType(ListGridFieldType.FLOAT);
		cantTm.setShowGridSummary(true);
		cantTm.setHidden(!esModificar);
		cantTm.setSummaryFunction(SummaryFunctionType.SUM);
		addNumberCellFormatter(cantTm);
		cantTm.setWidth(tamanoAreas);
		cantTm.setAlign(Alignment.CENTER);
		cantTm.setCellAlign(Alignment.RIGHT);

		ListGridField areaOcupada = new ListGridField(COLUMNA_AREA_OCUPADA, TITULO_AREA_OCUPADA);
		areaOcupada.setType(ListGridFieldType.FLOAT);
		areaOcupada.setShowGridSummary(true);
		areaOcupada.setSummaryFunction(SummaryFunctionType.SUM);
		addNumberCellFormatter(areaOcupada);
		areaOcupada.setWidth(tamanoAreas);
		areaOcupada.setAlign(Alignment.CENTER);
		areaOcupada.setCellAlign(Alignment.RIGHT);

		ListGridField relacion = new ListGridField(COLUMNA_RELACION, TITULO_RELACION);
		relacion.setType(ListGridFieldType.FLOAT);
		relacion.setShowGridSummary(true);
		relacion.setSummaryFunction(getSummmaryColRelacion());
		addNumberCellFormatter(relacion);
		relacion.setWidth(tamanoAreas);
		relacion.setAlign(Alignment.CENTER);
		relacion.setCellAlign(Alignment.RIGHT);

		ListGridField altLadoCarbon = new ListGridField(COLUMNA_ALT_LADO_CARBON, TITULO_ALT_LADO_CARBON);
		altLadoCarbon.setType(ListGridFieldType.FLOAT);
		altLadoCarbon.setShowGridSummary(false);
		addNumberCellFormatter(altLadoCarbon);
		altLadoCarbon.setWidth(tamanoAreas);
		altLadoCarbon.setAlign(Alignment.CENTER);
		altLadoCarbon.setCellAlign(Alignment.RIGHT);

		ListGridField altCentral = new ListGridField(COLUMNA_ALT_CENTRAL, TITULO_ALT_CENTRAL);
		altCentral.setType(ListGridFieldType.FLOAT);
		altCentral.setShowGridSummary(false);
		addNumberCellFormatter(altCentral);
		altCentral.setWidth(tamanoAreas);
		altCentral.setAlign(Alignment.CENTER);
		altCentral.setCellAlign(Alignment.RIGHT);

		ListGridField altLadoClinker = new ListGridField(COLUMNA_ALT_LADO_CLINKER, TITULO_ALT_LADO_CLINKER);
		altLadoClinker.setType(ListGridFieldType.FLOAT);
		altLadoClinker.setShowGridSummary(false);
		addNumberCellFormatter(altLadoClinker);
		altLadoClinker.setWidth(tamanoAreas);
		altLadoClinker.setAlign(Alignment.CENTER);
		altLadoClinker.setCellAlign(Alignment.RIGHT);

		ListGridField altLibreProm = new ListGridField(COLUMNA_ALT_LIBRE_PROMEDIO, TITULO_ALT_LIBRE_PROMEDIO);
		altLibreProm.setType(ListGridFieldType.FLOAT);
		altLibreProm.setShowGridSummary(false);
		addNumberCellFormatter(altLibreProm);
		altLibreProm.setWidth(tamanoAreas);
		altLibreProm.setAlign(Alignment.CENTER);
		altLibreProm.setCellAlign(Alignment.RIGHT);

		ListGridField unidad = new ListGridField(COLUMNA_UNIDAD_KG, TITULO_UNIDAD_KG);
		unidad.setType(ListGridFieldType.FLOAT);
		unidad.setShowGridSummary(false);
		addNumberCellFormatter(unidad);
		unidad.setWidth(tamanoAreas);
		unidad.setAlign(Alignment.CENTER);
		unidad.setCellAlign(Alignment.RIGHT);

		ListGridField observacion = new ListGridField(COLUMNA_OBSERVACIONES, TITULO_OBSERVACIONES);
		observacion.setWidth(tamanoObs);
		observacion.setAlign(Alignment.CENTER);
		observacion.setCellAlign(Alignment.LEFT);

		setWidth((tamanoAreas * 12) + tamanoMedioAlmac + tamanoObs + tamanoHidden);

		setPropiedadesGeneralesColumna(tipoMedio);
		setPropiedadesGeneralesColumna(codigoMedioAlmacenamiento);
		setPropiedadesGeneralesColumna(medioAlmacenamiento);
		setPropiedadesGeneralesColumna(areaCresta);
		setPropiedadesGeneralesColumna(areaPie);
		setPropiedadesGeneralesColumna(difNivel);
		setPropiedadesGeneralesColumna(volumen);
		setPropiedadesGeneralesColumna(observacion);
		setPropiedadesGeneralesColumna(areaOcupada);
		setPropiedadesGeneralesColumna(relacion);
		setPropiedadesGeneralesColumna(altLadoCarbon);
		setPropiedadesGeneralesColumna(altCentral);
		setPropiedadesGeneralesColumna(altLadoClinker);
		setPropiedadesGeneralesColumna(altLibreProm);
		setPropiedadesGeneralesColumna(unidad);

		// El cliente solicito el cambio del orden en que aparecen
		// las columnas AreaOcupada,Volumen,Densidad,CantTm
		setFields(tipoMedio, codigoMedioAlmacenamiento, medioAlmacenamiento, areaCresta, areaPie, difNivel, areaOcupada, volumen,
				densidad, cantTm, relacion, altLadoCarbon, altCentral, altLadoClinker, altLibreProm, unidad, observacion,
				codigoEstado, codigoLineanegocio, codigoProceso, codigoProducto, fecha);
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

	private SummaryFunction getSummmaryColRelacion() {
		return new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				Double totalVolumen = 0D;
				Double totalAreaOcupada = 0D;
				for (int i = 0; i < records.length; i++) {
					Record record = records[i];
					totalVolumen += record.getAttributeAsDouble(COLUMNA_VOLUMEN);
					totalAreaOcupada += record.getAttributeAsDouble(COLUMNA_AREA_OCUPADA);
				}

				if (records.length == 0) {
					return 0D;
				}
				return (totalVolumen / totalAreaOcupada);
			}
		};
	}

	private void setPropiedadesGeneralesColumna(ListGridField field) {
		field.setCanEdit(false);
		field.setCanExport(false);
		field.setCanFilter(false);
		field.setCanFreeze(false);
		field.setCanGroupBy(false);
		field.setCanSort(false);
		field.setCanSortClientOnly(false);
		field.setCanToggle(false);
		field.setWrap(true);
		field.setShowDefaultContextMenu(false);
	}

	private void addNumberCellFormatter(ListGridField field) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;

				String val = null;
				try {
					NumberFormat nf = NumberFormat.getFormat(PATRON_NUMERICO_INTERFAZ);
					val = nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}
				return val;
			}
		});

	}

	/**
	 * Metodo que carga el grid a partir de una lista de registros: lista de
	 * objetos RegistroTablaProgramaDTO
	 * 
	 * @param cubicacionDTO
	 */
	public void agregarRegistro(TablaCubicacionDTO cubicacionDTO) {

		ListGridRecord record = new ListGridRecord();

		record.setAttribute(COLUMNA_TIPO_MEDIO, cubicacionDTO.getTipoMedioAlmacenamiento());
		record.setAttribute(COLUMNA_CODIGO_MEDIO_ALMACENAMIENTO, cubicacionDTO.getCodigoMedioalmacenamiento());
		record.setAttribute(COLUMNA_MEDIO_ALMACENAMIENTO, cubicacionDTO.getMedioAlmacenamiento());
		record.setAttribute(COLUMNA_AREA_CRESTA, cubicacionDTO.getAreaCresta());
		record.setAttribute(COLUMNA_AREA_PIE, cubicacionDTO.getAreaPie());
		record.setAttribute(COLUMNA_DIF_NIVEL, cubicacionDTO.getDiferenciaNivel());
		record.setAttribute(COLUMNA_VOLUMEN, cubicacionDTO.getVolumen());

		record.setAttribute(COLUMNA_DENSIDAD, cubicacionDTO.getDensidadMedioAlmc());
		record.setAttribute(COLUMNA_TM, cubicacionDTO.getCantTM());

		record.setAttribute(COLUMNA_UNIDAD_KG, cubicacionDTO.getUnidad());
		record.setAttribute(COLUMNA_ALT_LIBRE_PROMEDIO, cubicacionDTO.getAlturaLibrePromedio());
		record.setAttribute(COLUMNA_ALT_LADO_CLINKER, cubicacionDTO.getAlturaLadoClinker());
		record.setAttribute(COLUMNA_ALT_CENTRAL, cubicacionDTO.getAlturaCentral());
		record.setAttribute(COLUMNA_ALT_LADO_CARBON, cubicacionDTO.getAlturaLadoCarboni());
		record.setAttribute(COLUMNA_RELACION, cubicacionDTO.getRelacionCubicacion());
		record.setAttribute(COLUMNA_AREA_OCUPADA, cubicacionDTO.getAreaOcupada());
		record.setAttribute(COLUMNA_OBSERVACIONES, cubicacionDTO.getObservacionCubicacion());

		record.setAttribute(CODIGO_LINEA_NEGOCIO, cubicacionDTO.getCodigoLineaNegocio());
		record.setAttribute(CODIGO_PRODUCTO, cubicacionDTO.getCodigoProducto());
		record.setAttribute(CODIGO_PROCESO, cubicacionDTO.getCodigoProceso());
		record.setAttribute(CODIGO_ESTADO, cubicacionDTO.getCodigoEstado());

		record.setAttribute(FECHA, cubicacionDTO.getFecha());
		record.setAttribute(HUMEDAD, cubicacionDTO.getHumedadPonderada());
		addData(record);
	}

	/**
	 * Metodo que exporta el contenido del grid a una lista de objetos
	 * RegistroTablaProgramaDTO
	 * 
	 * @return
	 */
	public List<TablaCubicacionDTO> exportarGrid() {

		List<TablaCubicacionDTO> listaCubicancionDTO = new ArrayList<TablaCubicacionDTO>();
		RecordList listaRecord = this.getRecordList();
		for (int i = 0; i < listaRecord.getLength(); i++) {
			TablaCubicacionDTO cubicacionDTO = new TablaCubicacionDTO();
			Record record = listaRecord.get(i);

			cubicacionDTO.setFecha(record.getAttributeAsDate(FECHA));

			cubicacionDTO.setTipoMedioAlmacenamiento(record.getAttribute(COLUMNA_TIPO_MEDIO));

			cubicacionDTO.setMedioAlmacenamiento(record.getAttribute(COLUMNA_MEDIO_ALMACENAMIENTO));

			cubicacionDTO.setAreaCresta(record.getAttributeAsDouble(COLUMNA_AREA_CRESTA));

			cubicacionDTO.setAreaPie(record.getAttributeAsDouble(COLUMNA_AREA_PIE));

			cubicacionDTO.setDiferenciaNivel(record.getAttributeAsDouble(COLUMNA_DIF_NIVEL));

			cubicacionDTO.setVolumen(record.getAttributeAsDouble(COLUMNA_VOLUMEN));

			cubicacionDTO.setDensidadMedioAlmc(record.getAttributeAsDouble(COLUMNA_DENSIDAD));

			Double unidad = record.getAttributeAsDouble(COLUMNA_UNIDAD_KG);
			if (unidad != null) {
				cubicacionDTO.setUnidad(unidad);
			}

			Double altLibreProm = record.getAttributeAsDouble(COLUMNA_ALT_LIBRE_PROMEDIO);
			if (altLibreProm != null) {
				cubicacionDTO.setAlturaLibrePromedio(altLibreProm);
			}

			Double altClinker = record.getAttributeAsDouble(COLUMNA_ALT_LADO_CLINKER);
			if (altClinker != null) {
				cubicacionDTO.setAlturaLadoClinker(altClinker);
			}

			Double altCentral = record.getAttributeAsDouble(COLUMNA_ALT_CENTRAL);
			if (altCentral != null) {
				cubicacionDTO.setAlturaCentral(altCentral);
			}

			Double ladoCarbon = record.getAttributeAsDouble(COLUMNA_ALT_LADO_CARBON);
			if (ladoCarbon != null) {
				cubicacionDTO.setAlturaLadoCarboni(ladoCarbon);
			}

			Double relacion = record.getAttributeAsDouble(COLUMNA_RELACION);
			if (relacion != null) {
				cubicacionDTO.setRelacionCubicacion(relacion);
			}

			cubicacionDTO.setAreaOcupada(record.getAttributeAsDouble(COLUMNA_AREA_OCUPADA));

			String observacion = record.getAttribute(COLUMNA_OBSERVACIONES);
			if (observacion != null) {
				cubicacionDTO.setObservacionCubicacion(observacion);
			}

			cubicacionDTO.setCodigoMedioalmacenamiento(record.getAttributeAsInt(COLUMNA_CODIGO_MEDIO_ALMACENAMIENTO));

			cubicacionDTO.setCodigoLineaNegocio(record.getAttributeAsInt(CODIGO_LINEA_NEGOCIO));

			cubicacionDTO.setCodigoProducto(record.getAttributeAsInt(CODIGO_PRODUCTO));

			cubicacionDTO.setCodigoProceso(record.getAttributeAsInt(CODIGO_PROCESO));

			cubicacionDTO.setCodigoEstado(record.getAttributeAsInt(CODIGO_ESTADO));

			cubicacionDTO.setHumedadPonderada(record.getAttributeAsDouble(HUMEDAD));

			listaCubicancionDTO.add(cubicacionDTO);

		}

		return listaCubicancionDTO;
	}

	/**
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			removeData(listGridRecord);
		}
	}

}
