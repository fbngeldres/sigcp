package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridPuestoTrabajoProduccion.java
 * Modificado: Sep 20, 2010 2:28:22 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.ListGridTesteable;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Validaciones;

import com.dbaccess.cellmanager.BunkerCalentFormulaEvaluator;
import com.dbaccess.cellmanager.BunkerProdFormulaEvaluator;
import com.dbaccess.cellmanager.CarCalentFormulaEvaluator;
import com.dbaccess.cellmanager.CarProdFormulaEvaluator;
import com.dbaccess.cellmanager.CellSelector;
import com.dbaccess.cellmanager.CellSelectorGroup;
import com.dbaccess.cellmanager.DivisionFormulaEvaluator;
import com.dbaccess.cellmanager.FixedCellSelector;
import com.dbaccess.cellmanager.KcalFormulaEvaluator;
import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.RowFilter;
import com.dbaccess.cellmanager.RowFilterCellSelector;
import com.dbaccess.cellmanager.SimpleRow;
import com.dbaccess.cellmanager.SumAllFormulaEvaluator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.validator.RequiredIfFunction;
import com.smartgwt.client.widgets.form.validator.RequiredIfValidator;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HeaderSpan;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;

// Este grid se llama en la pantalla "Producción P. Trabajo"
public class GridPuestoTrabajoProduccion extends ListGridTesteable implements ClienteServicioGwt {

	public static final String GRAPH_SOURCE_ID = "gridPuestoTrabajoProduccion";
	public static final String CODIGO_PUESTO_TRABAJO = "codigoPuestoTrabajo";
	public static final String CODIGO_PRODUCTO = "codigoProducto";
	public static final String NOMBRE_PRODUCTO = "nombreProducto";
	private static final String TASA_PRODUCCION_NOMINAL = "tasaProdNominal";
	private static final String COLUMNA_KCAL_PRODUCCION = "kcalProduccion";
	private static final String COLUMNA_KCAL_PRODUCCIONREAL = "kcalProduccionReal";
	private static final String COLUMNA_VALIDACION = "validacion";
	public static final String COLUMNA_PRODUCCION_REAL_TMPH = "produccionRealTMPH";
	public static final String COLUMNA_CARBON_PRODUCCION_REAL = "produccionRealCarProd";
	public static final String COLUMNA_CARBON_CALENTAMIENTO_REAL = "produccionRealCCalent";
	private static final String COLUMNA_PRODUCCION_REAL_HR = "produccionRealHR";
	public static final String COLUMNA_PRODUCCION_REAL_TM = "produccionRealTM";
	private static final String COLUMNA_AJUSTE_HR = "ajusteHR";
	public static final String COLUMNA_AJUSTE_TM = "ajusteTM";
	private static final String COLUMNA_PRODUCCION_TMPH = "produccionTMPH";
	private static final String COLUMNA_CARBON_PRODUCCION = "produccionCarProd";
	private static final String COLUMNA_CARBON_CALENTAMIENTO = "produccionCCalent";
	private static final String COLUMNA_PRODUCCION_HR = "produccionHR";
	public static final String COLUMNA_PRODUCCION_TM = "produccionTM";
	private static final String COLUMNA_MES_ANTERIOR_HR = "mesAnteriorHR";
	private static final String COLUMNA_MES_ANTERIOR_TMPH = "mesAnteriorTMPH";
	private static final String COLUMNA_MES_ANTERIOR_TM = "mesAnteriorTM";
	public static final String COLUMNA_NOMBREPUESTO_TRABAJO = "nombrepuestoTrabajo";
	private static final String COLUMNA_PODER_CALORIFICO_CARBON_PONDERADO = "poderCalorificoCarbonPonderado";
	private static final String COLUMNA_BUNKER_PRODUCCION = "bunkerProduccion";
	private static final String COLUMNA_BUNKER_CALENT = "bunkerCalentamiento";
	private static final String COLUMNA_BUNKER_PRODUCCION_REAL = "bunkerProduccionReal";
	private static final String COLUMNA_BUNKER_CALENT_REAL = "bunkerCalentamientoReal";
	private static final String VALOR_MAX = "valorMax";
	private static final String COLUMNA_CONSUMO_CARBON_SECO_OCULTO = "consumoCarbonSecoOculto";

	public static final String COL_RELACION_CRUDO_CLINKER = "factorCruClk";
	public static final String COL_RELACION_CARBON_CLINKER = "factorCarbonClk";

	public static final String COL_FACTOR_CALIZA_CAL = "factorClzCal";
	public static final String COL_FACTOR_CARBON_CAL = "factorCarbonCal";

	private ListGridField columnaKCALProduccion;
	private ListGridField columnaKCALProduccionReal;
	private MapaFactor factoresActuales;

	private static final String NOMBRE_CLINKER = "CLK -";
	private static final String NOMBRE_CAL_GRANULADA = "CAL- GRANULADA";

	private static final Map<String, MapaFactor> factoresPorGrupo = new HashMap<String, MapaFactor>();
	private static final Map<String, String[]> columnasEspecialesPorGrupo = new HashMap<String, String[]>();
	public static final Map<String, Integer> tamanosPorGrupo = new HashMap<String, Integer>();
	private static final Set<String> gruposQueNOAjustanConsumo = new HashSet<String>();

	private static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);
	private GridConsumoComponentesPuestoTrabajo gridConsumoComponentesPuestoTrabajo;

	public static final int TAMANO_ESTANDAR = 680;

	private final GridTablaBalance2 gridTablaBalance2;

	private final GridConsumoComponentesPuestoTrabajo gridConsumoComponentes;

	private boolean seAjustaConsumo;

	// private boolean esClinker;

	// private boolean esClinkerCalGranulada;

	static {
		// Clinker
		MapaFactor mapaClinker = new MapaFactor();
		mapaClinker.put(ConstantesGWT.GRUPO_CARBON, COL_RELACION_CARBON_CLINKER);
		mapaClinker.put(ConstantesGWT.GRUPO_CRUDO, COL_RELACION_CRUDO_CLINKER);
		factoresPorGrupo.put(ConstantesGWT.GRUPO_CLINKER, mapaClinker);

		columnasEspecialesPorGrupo.put(ConstantesGWT.GRUPO_CLINKER, new String[] { COL_RELACION_CRUDO_CLINKER,
				COL_RELACION_CARBON_CLINKER });

		tamanosPorGrupo.put(ConstantesGWT.GRUPO_CLINKER, 940);
		gruposQueNOAjustanConsumo.add(ConstantesGWT.GRUPO_CLINKER);

		// Cal granulada
		MapaFactor mapaCalGranulada = new MapaFactor();
		mapaCalGranulada.put(ConstantesGWT.GRUPO_CARBON, COL_FACTOR_CARBON_CAL);
		mapaCalGranulada.put(ConstantesGWT.GRUPO_CALIZA, COL_FACTOR_CALIZA_CAL);
		factoresPorGrupo.put(ConstantesGWT.GRUPO_CAL_GRANULADA, mapaCalGranulada);

		columnasEspecialesPorGrupo.put(ConstantesGWT.GRUPO_CAL_GRANULADA, new String[] { COL_FACTOR_CALIZA_CAL,
				COL_FACTOR_CARBON_CAL });
		tamanosPorGrupo.put(ConstantesGWT.GRUPO_CAL_GRANULADA, 940);
		gruposQueNOAjustanConsumo.add(ConstantesGWT.GRUPO_CAL_GRANULADA);
	}

	public GridPuestoTrabajoProduccion(GridConsumoComponentesPuestoTrabajo gridConsumoComponentesPuestoTrabajo,
			GridTablaBalance2 gridTablaBalance2, GridConsumoComponentesPuestoTrabajo gridConsumoComponentes) {

		super();
		this.setID(GRAPH_SOURCE_ID);
		this.gridConsumoComponentesPuestoTrabajo = gridConsumoComponentesPuestoTrabajo;
		this.gridTablaBalance2 = gridTablaBalance2;
		this.gridConsumoComponentes = gridConsumoComponentes;

		ListGridField columnaCodigoPuestoTrabajo = new ListGridField(CODIGO_PUESTO_TRABAJO);
		columnaCodigoPuestoTrabajo.setType(ListGridFieldType.INTEGER);
		columnaCodigoPuestoTrabajo.setHidden(true);

		ListGridField columnaNombreProducto = new ListGridField(NOMBRE_PRODUCTO);
		columnaNombreProducto.setType(ListGridFieldType.TEXT);
		columnaNombreProducto.setHidden(true);

		ListGridField tasaProduccionNominal = new ListGridField(TASA_PRODUCCION_NOMINAL);
		tasaProduccionNominal.setType(ListGridFieldType.FLOAT);
		tasaProduccionNominal.setHidden(true);

		ListGridField columnaPuestoTrabajo = new ListGridField(COLUMNA_NOMBREPUESTO_TRABAJO,
				CONSTANTES.tituloColPuestoTrabajoAbrev());
		columnaPuestoTrabajo.setType(ListGridFieldType.TEXT);
		columnaPuestoTrabajo.setCanEdit(false);
		columnaPuestoTrabajo.setAlign(Alignment.CENTER);
		columnaPuestoTrabajo.setIncludeInRecordSummary(false);
		columnaPuestoTrabajo.setShowGridSummary(true);
		columnaPuestoTrabajo.setWidth(80);
		columnaPuestoTrabajo.setSummaryFunction(new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				return "Total";
			}
		});

		// Mes anterior
		ListGridField columnaMesAnteriorTM = new ListGridField(COLUMNA_MES_ANTERIOR_TM, CONSTANTES.tituloColTM());
		columnaMesAnteriorTM.setType(ListGridFieldType.FLOAT);
		columnaMesAnteriorTM.setCanEdit(false);
		columnaMesAnteriorTM.setHidden(true);
		columnaMesAnteriorTM.setShowGridSummary(true);
		columnaMesAnteriorTM.setAlign(Alignment.CENTER);
		columnaMesAnteriorTM.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaMesAnteriorTM);

		ListGridField columnaMesAnteriorHR = new ListGridField(COLUMNA_MES_ANTERIOR_HR, CONSTANTES.tituloColumnaHr());
		columnaMesAnteriorHR.setType(ListGridFieldType.FLOAT);
		columnaMesAnteriorHR.setCanEdit(false);
		columnaMesAnteriorHR.setShowGridSummary(true);
		columnaMesAnteriorHR.setAlign(Alignment.CENTER);
		columnaMesAnteriorHR.setHidden(true);
		columnaMesAnteriorHR.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(columnaMesAnteriorHR);
		asignarFormato(columnaMesAnteriorHR);

		ListGridField columnaMesAnteriorTMPH = new ListGridField(COLUMNA_MES_ANTERIOR_TMPH, CONSTANTES.tituloColumnaTMPH());
		columnaMesAnteriorTMPH.setType(ListGridFieldType.FLOAT);
		columnaMesAnteriorTMPH.setCanEdit(false);
		columnaMesAnteriorTMPH.setWidth(60);
		columnaMesAnteriorTMPH.setShowGridSummary(true);
		columnaMesAnteriorTMPH.setAlign(Alignment.CENTER);
		columnaMesAnteriorTMPH.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaMesAnteriorTMPH);

		// Produccion
		ListGridField columnaProduccionHR = new ListGridField(COLUMNA_PRODUCCION_HR, CONSTANTES.tituloColumnaHr());
		columnaProduccionHR.setType(ListGridFieldType.FLOAT);
		columnaProduccionHR.setCanEdit(false);
		columnaProduccionHR.setWidth(60);
		columnaProduccionHR.setShowGridSummary(true);
		columnaProduccionHR.setAlign(Alignment.CENTER);
		columnaProduccionHR.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaProduccionHR);

		ListGridField columnaProduccionTM = new ListGridField(COLUMNA_PRODUCCION_TM, CONSTANTES.tituloColTM());
		columnaProduccionTM.setType(ListGridFieldType.FLOAT);
		columnaProduccionTM.setCanEdit(false);
		columnaProduccionTM.setShowGridSummary(true);
		columnaProduccionTM.setWidth(60);
		columnaProduccionTM.setAlign(Alignment.CENTER);
		columnaProduccionTM.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(columnaProduccionTM);
		asignarFormato(columnaProduccionTM);

		ListGridField columnaProduccionCarProd = new ListGridField(COLUMNA_CARBON_PRODUCCION, CONSTANTES.tituloColumnaCarProd());
		columnaProduccionCarProd.setType(ListGridFieldType.FLOAT);
		columnaProduccionCarProd.setCanEdit(false);
		columnaProduccionCarProd.setWidth(60);
		columnaProduccionCarProd.setShowGridSummary(true);
		columnaProduccionCarProd.setHidden(true);
		columnaProduccionCarProd.setDefaultValue("0");
		columnaProduccionCarProd.setAlign(Alignment.CENTER);
		columnaProduccionCarProd.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaProduccionCarProd);

		ListGridField columnaProduccionCCalent = new ListGridField(COLUMNA_CARBON_CALENTAMIENTO,
				CONSTANTES.tituloColumnaCCalent());
		columnaProduccionCCalent.setType(ListGridFieldType.FLOAT);
		columnaProduccionCCalent.setCanEdit(false);
		columnaProduccionCCalent.setWidth(60);
		columnaProduccionCCalent.setShowGridSummary(true);
		columnaProduccionCCalent.setDefaultValue("0");
		columnaProduccionCCalent.setHidden(true);
		columnaProduccionCCalent.setAlign(Alignment.CENTER);
		columnaProduccionCCalent.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaProduccionCCalent);

		ListGridField columnaProduccionTMPH = new ListGridField(COLUMNA_PRODUCCION_TMPH, CONSTANTES.tituloColumnaTMPH());
		columnaProduccionTMPH.setType(ListGridFieldType.FLOAT);
		columnaProduccionTMPH.setCanEdit(false);
		columnaProduccionTMPH.setWidth(60);
		columnaProduccionTMPH.setShowGridSummary(false);
		columnaProduccionTMPH.setAlign(Alignment.CENTER);
		columnaProduccionTMPH.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaProduccionTMPH);

		columnaKCALProduccion = new ListGridField(COLUMNA_KCAL_PRODUCCION, CONSTANTES.tituloColKcal());
		columnaKCALProduccion.setType(ListGridFieldType.FLOAT);
		columnaKCALProduccion.setShowGridSummary(true);
		columnaKCALProduccion.setCanEdit(false);
		columnaKCALProduccion.setHidden(true);
		columnaKCALProduccion.setWidth(60);
		columnaKCALProduccion.setAlign(Alignment.CENTER);
		columnaKCALProduccion.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaKCALProduccion);

		RequiredIfValidator ifValidator = new RequiredIfValidator();
		ifValidator.setErrorMessage(CONSTANTES.campoRequerido());
		ifValidator.setExpression(new RequiredIfFunction() {
			public boolean execute(FormItem formItem, Object value) {
				return true;
			}
		});

		// Ajuste
		ListGridField columnaAjusteTM = new ListGridField(COLUMNA_AJUSTE_TM, CONSTANTES.tituloColTM());
		columnaAjusteTM.setType(ListGridFieldType.FLOAT);
		columnaAjusteTM.setValidators(ifValidator);
		columnaAjusteTM.setShowGridSummary(true);
		columnaAjusteTM.setSummaryFunction(SummaryFunctionType.SUM);
		columnaAjusteTM.setAlign(Alignment.CENTER);
		columnaAjusteTM.setWidth(50);
		addAjusteCellEditorHandler(columnaAjusteTM);
		asignarFormato(columnaAjusteTM);
		gridConsumoComponentes.getField(GridConsumoComponentesPuestoTrabajo.DIFERENCIA);

		ListGridField columnaAjusteHR = new ListGridField(COLUMNA_AJUSTE_HR, CONSTANTES.tituloColumnaHr());
		columnaAjusteHR.setType(ListGridFieldType.FLOAT);
		columnaAjusteHR.setAlign(Alignment.CENTER);
		columnaAjusteHR.setShowGridSummary(true);
		columnaAjusteHR.setValidators(ifValidator);
		columnaAjusteHR.setWidth(40);
		columnaAjusteHR.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(columnaAjusteHR);
		asignarFormato(columnaAjusteHR);

		// Produccion real
		ListGridField columnaProduccionRealHR = new ListGridField(COLUMNA_PRODUCCION_REAL_HR, CONSTANTES.tituloColumnaHr());
		columnaProduccionRealHR.setType(ListGridFieldType.FLOAT);
		columnaProduccionRealHR.setShowGridSummary(true);
		columnaProduccionRealHR.setCanEdit(false);
		columnaProduccionRealHR.setWidth(50);
		columnaProduccionRealHR.setAlign(Alignment.CENTER);
		columnaProduccionRealHR.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(columnaProduccionRealHR);
		asignarFormato(columnaProduccionRealHR);

		ListGridField columnaProduccionRealTM = new ListGridField(COLUMNA_PRODUCCION_REAL_TM, CONSTANTES.tituloColTM());
		columnaProduccionRealTM.setType(ListGridFieldType.FLOAT);
		columnaProduccionRealTM.setCanEdit(false);
		columnaProduccionRealTM.setShowGridSummary(true);
		columnaProduccionRealTM.setWidth(60);
		columnaProduccionRealTM.setSummaryFunction(SummaryFunctionType.SUM);
		columnaProduccionRealTM.setAlign(Alignment.CENTER);
		asignarFormato(columnaProduccionRealTM);

		ListGridField columnaProduccionRealCarProd = new ListGridField(COLUMNA_CARBON_PRODUCCION_REAL,
				CONSTANTES.tituloColumnaCarProd());
		columnaProduccionRealCarProd.setType(ListGridFieldType.FLOAT);
		columnaProduccionRealCarProd.setShowGridSummary(true);
		columnaProduccionRealCarProd.setCanEdit(false);
		columnaProduccionRealCarProd.setWidth(60);
		columnaProduccionRealCarProd.setDefaultValue("0");
		columnaProduccionRealCarProd.setHidden(true);
		columnaProduccionRealCarProd.setAlign(Alignment.CENTER);
		columnaProduccionRealCarProd.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaProduccionRealCarProd);

		ListGridField columnaProduccionRealCCalent = new ListGridField(COLUMNA_CARBON_CALENTAMIENTO_REAL,
				CONSTANTES.tituloColumnaCCalent());
		columnaProduccionRealCCalent.setType(ListGridFieldType.FLOAT);
		columnaProduccionRealCCalent.setShowGridSummary(true);
		columnaProduccionRealCCalent.setCanEdit(false);
		columnaProduccionRealCCalent.setWidth(60);
		columnaProduccionRealCCalent.setDefaultValue("0");
		columnaProduccionRealCCalent.setHidden(true);
		columnaProduccionRealCCalent.setAlign(Alignment.CENTER);
		columnaProduccionRealCCalent.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaProduccionRealCCalent);

		ListGridField columnaProduccionRealTMPH = new ListGridField(COLUMNA_PRODUCCION_REAL_TMPH, CONSTANTES.tituloColumnaTMPH());
		columnaProduccionRealTMPH.setType(ListGridFieldType.FLOAT);
		columnaProduccionRealTMPH.setShowGridSummary(true);
		columnaProduccionRealTMPH.setCanEdit(false);
		columnaProduccionRealTMPH.setWidth(60);
		columnaProduccionRealTMPH.setAlign(Alignment.CENTER);
		columnaProduccionRealTMPH.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(columnaProduccionRealTMPH);
		asignarFormato(columnaProduccionRealTMPH);

		columnaKCALProduccionReal = new ListGridField(COLUMNA_KCAL_PRODUCCIONREAL, CONSTANTES.tituloColKcal());
		columnaKCALProduccionReal.setType(ListGridFieldType.FLOAT);
		columnaKCALProduccionReal.setShowGridSummary(true);
		columnaKCALProduccionReal.setHidden(true);
		columnaKCALProduccionReal.setWidth(60);
		columnaKCALProduccionReal.setCanEdit(false);
		columnaKCALProduccionReal.setAlign(Alignment.CENTER);
		columnaKCALProduccionReal.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(columnaKCALProduccionReal);

		ListGridField validacion = new ListGridField(COLUMNA_VALIDACION, CONSTANTES.tituloColValidacion());
		validacion.setType(ListGridFieldType.FLOAT);
		validacion.setShowGridSummary(false);
		validacion.setAlign(Alignment.CENTER);
		validacion.setCanEdit(false);
		validacion.setWidth(90);
		asignarFormato(validacion);

		ListGridField colFactorCrudoClinker = new ListGridField(COL_RELACION_CRUDO_CLINKER, CONSTANTES.tituloColFactCruClk());
		colFactorCrudoClinker.setType(ListGridFieldType.FLOAT);
		colFactorCrudoClinker.setCanEdit(false);
		colFactorCrudoClinker.setDefaultValue("0");
		colFactorCrudoClinker.setShowGridSummary(true);
		colFactorCrudoClinker.setWidth(60);
		colFactorCrudoClinker.setHidden(true);
		colFactorCrudoClinker.setAlign(Alignment.CENTER);
		asignarFormato(colFactorCrudoClinker);

		ListGridField colFactorCarbonClinker = new ListGridField(COL_RELACION_CARBON_CLINKER, CONSTANTES.tituloColFactCarbonClk());
		colFactorCarbonClinker.setType(ListGridFieldType.FLOAT);
		colFactorCarbonClinker.setCanEdit(false);
		colFactorCarbonClinker.setDefaultValue("0");
		colFactorCarbonClinker.setShowGridSummary(true);
		colFactorCarbonClinker.setWidth(80);
		colFactorCarbonClinker.setHidden(true);
		colFactorCarbonClinker.setAlign(Alignment.CENTER);
		asignarFormato(colFactorCarbonClinker);

		ListGridField colFactorCarbonCalizaCal = new ListGridField(COL_FACTOR_CALIZA_CAL, CONSTANTES.tituloColFactClzCal());
		colFactorCarbonCalizaCal.setType(ListGridFieldType.FLOAT);
		colFactorCarbonCalizaCal.setCanEdit(false);
		colFactorCarbonCalizaCal.setDefaultValue("0");
		colFactorCarbonCalizaCal.setShowGridSummary(true);
		colFactorCarbonCalizaCal.setWidth(70);
		colFactorCarbonCalizaCal.setHidden(true);
		colFactorCarbonCalizaCal.setAlign(Alignment.CENTER);
		asignarFormato(colFactorCarbonCalizaCal);

		ListGridField colFactorCarbonCarbonCal = new ListGridField(COL_FACTOR_CARBON_CAL, CONSTANTES.tituloColFactCarbonCal());
		colFactorCarbonCarbonCal.setType(ListGridFieldType.FLOAT);
		colFactorCarbonCarbonCal.setCanEdit(false);
		colFactorCarbonCarbonCal.setDefaultValue("0");
		colFactorCarbonCarbonCal.setShowGridSummary(true);
		colFactorCarbonCarbonCal.setWidth(70);
		colFactorCarbonCarbonCal.setHidden(true);
		colFactorCarbonCarbonCal.setAlign(Alignment.CENTER);
		asignarFormato(colFactorCarbonCarbonCal);

		// COLUMNAS OCULTAS
		ListGridField colPoderCalorificoCarbonPond = new ListGridField(COLUMNA_PODER_CALORIFICO_CARBON_PONDERADO, "POND");
		colPoderCalorificoCarbonPond.setType(ListGridFieldType.FLOAT);
		colPoderCalorificoCarbonPond.setWidth(60);
		colPoderCalorificoCarbonPond.setHidden(true);

		ListGridField colValorMax = new ListGridField(VALOR_MAX, "MAX");
		colValorMax.setType(ListGridFieldType.INTEGER);
		colValorMax.setWidth(60);
		colValorMax.setHidden(true);

		ListGridField colBunkerProduccion = new ListGridField(COLUMNA_BUNKER_PRODUCCION, "Bun.Prod");
		colBunkerProduccion.setType(ListGridFieldType.FLOAT);
		colBunkerProduccion.setWidth(60);
		colBunkerProduccion.setCanEdit(false);
		colBunkerProduccion.setHidden(true);

		ListGridField colBunkerCalent = new ListGridField(COLUMNA_BUNKER_CALENT, "Bun.Calent");
		colBunkerCalent.setType(ListGridFieldType.FLOAT);
		colBunkerCalent.setWidth(60);
		colBunkerCalent.setCanEdit(false);
		colBunkerCalent.setHidden(true);

		ListGridField colBunkerProduccionReal = new ListGridField(COLUMNA_BUNKER_PRODUCCION_REAL, " ");
		colBunkerProduccionReal.setType(ListGridFieldType.FLOAT);
		colBunkerProduccionReal.setWidth(1);
		colBunkerProduccionReal.setCanEdit(false);

		ListGridField colBunkerCalentReal = new ListGridField(COLUMNA_BUNKER_CALENT_REAL, " ");
		colBunkerCalentReal.setType(ListGridFieldType.FLOAT);
		colBunkerCalentReal.setWidth(1);
		colBunkerCalentReal.setCanEdit(false);

		ListGridField colConsumoCarbonSecoOculto = new ListGridField(COLUMNA_CONSUMO_CARBON_SECO_OCULTO, " ");
		colConsumoCarbonSecoOculto.setType(ListGridFieldType.FLOAT);
		colConsumoCarbonSecoOculto.setCanEdit(false);
		colConsumoCarbonSecoOculto.setWidth(1);

		// Se setean los headers
		HeaderSpan headerPT = new HeaderSpan("", new String[] { COLUMNA_NOMBREPUESTO_TRABAJO });

		HeaderSpan headerMesAnterior = new HeaderSpan(CONSTANTES.tituloHeaderMesAnt(), new String[] { COLUMNA_MES_ANTERIOR_TMPH });

		HeaderSpan headerProduccion = new HeaderSpan(CONSTANTES.tituloHeaderProduccion(), new String[] { COLUMNA_PRODUCCION_TM,
				COLUMNA_PRODUCCION_HR, COLUMNA_CARBON_PRODUCCION, COLUMNA_CARBON_CALENTAMIENTO, COLUMNA_PRODUCCION_TMPH,
				COLUMNA_KCAL_PRODUCCION });

		HeaderSpan headerAjuste = new HeaderSpan(CONSTANTES.titleHeaderAjustes(), new String[] { COLUMNA_AJUSTE_TM,
				COLUMNA_AJUSTE_HR });

		HeaderSpan headerProduccionReal = new HeaderSpan(CONSTANTES.tituloHeaderProduccionReal(), new String[] {
				COLUMNA_PRODUCCION_REAL_TM, COLUMNA_PRODUCCION_REAL_HR, COLUMNA_CARBON_PRODUCCION_REAL,
				COLUMNA_CARBON_CALENTAMIENTO_REAL, COLUMNA_PRODUCCION_REAL_TMPH, COLUMNA_KCAL_PRODUCCIONREAL });

		HeaderSpan headerValidacion = new HeaderSpan("", new String[] { COLUMNA_VALIDACION });

		HeaderSpan headerFactorCrudoClk = new HeaderSpan("", new String[] { COL_RELACION_CRUDO_CLINKER });
		HeaderSpan headerFactorCarbonClk = new HeaderSpan("", new String[] { COL_RELACION_CARBON_CLINKER });

		HeaderSpan headerFactorClzCal = new HeaderSpan("", new String[] { COL_FACTOR_CALIZA_CAL });
		HeaderSpan headerFactorCarbonCal = new HeaderSpan("", new String[] { COL_FACTOR_CARBON_CAL });

		setHeaderSpans(headerPT, headerMesAnterior, headerProduccion, headerAjuste, headerProduccionReal, headerValidacion,
				headerFactorCrudoClk, headerFactorCarbonClk, headerFactorClzCal, headerFactorCarbonCal);
		setHeaderHeight(50);

		setWidth(TAMANO_ESTANDAR);
		setHeight(250);

		setCellHeight(20);

		// Se definen caracteristicas generales del grig
		setShowAllRecords(true);
		setCanEdit(true);
		setEditEvent(ListGridEditEvent.CLICK);
		setEditByCell(true);
		setShowGridSummary(true);

		// Se establecen los valores de las columnas del grid
		setFields(columnaCodigoPuestoTrabajo, columnaNombreProducto, tasaProduccionNominal, columnaPuestoTrabajo,
				columnaMesAnteriorTMPH, columnaMesAnteriorHR, columnaMesAnteriorTM, columnaProduccionHR, columnaProduccionTM,
				columnaProduccionCarProd, columnaProduccionCCalent, columnaProduccionTMPH, columnaKCALProduccion,
				columnaAjusteHR, columnaAjusteTM, columnaProduccionRealHR, columnaProduccionRealTM, columnaProduccionRealCarProd,
				columnaProduccionRealCCalent, columnaProduccionRealTMPH, columnaKCALProduccionReal, validacion,
				colFactorCrudoClinker, colFactorCarbonClinker, colFactorCarbonCalizaCal, colFactorCarbonCarbonCal,
				colBunkerProduccion, colBunkerCalent, colBunkerProduccionReal, colBunkerCalentReal, colPoderCalorificoCarbonPond,
				colValorMax, colConsumoCarbonSecoOculto);
	}

	@Override
	protected String getGridSummary(ListGridField field) {
		String valueStr = super.getGridSummary(field);
		try {
			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			if (field.getName().equals(COLUMNA_PRODUCCION_REAL_TMPH)) {
				double tm = getTotal(COLUMNA_PRODUCCION_REAL_TM);
				double hr = getTotal(COLUMNA_PRODUCCION_REAL_HR);

				return nf.format(tm / hr);
			}

			Double d = nf.parse(valueStr);
			String format = nf.format(d);
			return format;
		} catch (Exception e) {
			return valueStr;
		}
	}

	public Double getTotalTmAjuste() {
		return getTotal(COLUMNA_AJUSTE_TM);
	}

	private double getTotal(String fieldStr) {
		ListGridField field = getField(fieldStr);
		String summaryStr = getGridSummary(field);
		NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
		return nf.parse(summaryStr);
	}

	private void asignarFormato(ListGridField columna) {
		columna.setCellFormatter(new CellFormatter() {
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
		});
	}

	/**
	 * Método que redefine el evento ante un cambio de valor en las celdas
	 * 
	 * @param ajuste
	 */
	private void addAjusteCellEditorHandler(ListGridField ajuste) {
		ajuste.addCellSavedHandler(new CellSavedHandler() {

			public void onCellSaved(CellSavedEvent event) {
				String celda = event.getNewValue().toString();

				if (!Validaciones.isDouble(celda)) {
					com.google.gwt.user.client.Window.alert(CONSTANTES.validacionAjusteDebeSerUnMontoNumerico());
					cancelEditing();
				}
			}
		});
	}

	/**
	 * Método para cargar los datos del mes actual
	 * 
	 * @param registrosDTO
	 */
	public void cargaGridPuestoTrabajoProduccionMesActual(List<RegistroPuestoTrabajoProduccionDTO> registrosDTO) {

		for (RegistroPuestoTrabajoProduccionDTO registroDTO : registrosDTO) {

			ListGridRecord registro = new ListGridRecord();

			registro.setAttribute(CODIGO_PUESTO_TRABAJO, registroDTO.getCodigoPuestoTrabajo());
			registro.setAttribute(COLUMNA_NOMBREPUESTO_TRABAJO, registroDTO.getNombrePuestoTrabajo());
			registro.setAttribute(NOMBRE_PRODUCTO, registroDTO.getNombreProducto());

			Double tmAnterior = registroDTO.getMesAnteriorTM() == null ? 0D : registroDTO.getMesAnteriorTM();
			Double hrAnterior = registroDTO.getMesAnteriorHR() == null ? 0D : registroDTO.getMesAnteriorHR();
			Double tmActual = registroDTO.getProduccionTM() == null ? 0D : registroDTO.getProduccionTM();
			Double hrActual = registroDTO.getProduccionHR() == null ? 0D : registroDTO.getProduccionHR();
			Double tmphActual = hrActual == 0D ? 0D : tmActual / hrActual;
			Double kcalActual = registroDTO.getProduccionKCAL() == null ? 0D : registroDTO.getProduccionKCAL();
			Double tmAjuste = registroDTO.getAjusteTM() == null ? 0D : registroDTO.getAjusteTM();
			Double hrAjuste = registroDTO.getAjusteHR() == null ? 0D : registroDTO.getAjusteHR();

			registro.setAttribute(COLUMNA_MES_ANTERIOR_TM, tmAnterior);
			registro.setAttribute(COLUMNA_MES_ANTERIOR_HR, hrAnterior);

			if (hrAnterior == null || hrAnterior.doubleValue() == 0d) {
				registro.setAttribute(COLUMNA_MES_ANTERIOR_TMPH, 0d);
			} else {
				registro.setAttribute(COLUMNA_MES_ANTERIOR_TMPH, tmAnterior / hrAnterior);
			}

			registro.setAttribute(COLUMNA_PRODUCCION_HR, hrActual);
			registro.setAttribute(COLUMNA_PRODUCCION_TM, tmActual);
			registro.setAttribute(COLUMNA_CARBON_PRODUCCION, registroDTO.getProduccionCarProd());
			registro.setAttribute(COLUMNA_CARBON_CALENTAMIENTO, registroDTO.getProduccionCarCalent());
			registro.setAttribute(COLUMNA_PRODUCCION_TMPH, tmphActual);
			registro.setAttribute(COLUMNA_KCAL_PRODUCCION, kcalActual);

			registro.setAttribute(COLUMNA_AJUSTE_TM, tmAjuste);
			registro.setAttribute(COLUMNA_AJUSTE_HR, hrAjuste);

			registro.setAttribute(TASA_PRODUCCION_NOMINAL, registroDTO.getTasaProduccionNominal());
			registro.setAttribute(VALOR_MAX, registroDTO.getValorMax());

			registro.setAttribute(COLUMNA_PODER_CALORIFICO_CARBON_PONDERADO, registroDTO.getPoderCalorificoPonderado());
			registro.setAttribute(COLUMNA_BUNKER_PRODUCCION, registroDTO.getBunkerProduccion());
			registro.setAttribute(COLUMNA_BUNKER_CALENT, registroDTO.getBunkerCalent());

			Double tasaProdNominalMax = registroDTO.getTasaProduccionNominal() * 1.05d;
			Double tasaProdNominalMin = registroDTO.getTasaProduccionNominal() * 0.95d;
			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			String validacion = nf.format(tasaProdNominalMin) + " - " + nf.format(tasaProdNominalMax);
			registro.setAttribute(COLUMNA_VALIDACION, validacion);

			// TODO: IMPORTANTE. VOLVER A PONER ESTO
			// chequeoProduccionPuestoTrabajo(registro, tmphReal);
			this.addData(registro);
		}
	}

	/**
	 * Método para exportar datos del grid
	 * 
	 * @return
	 */
	public List<RegistroPuestoTrabajoProduccionDTO> exportarGridPuestotrabajoProduccion() {
		List<RegistroPuestoTrabajoProduccionDTO> registrosDTO = new ArrayList<RegistroPuestoTrabajoProduccionDTO>();
		RecordList listaRecord = this.getRecordList();

		for (int i = 0; i < listaRecord.getLength(); i++) {

			RegistroPuestoTrabajoProduccionDTO registroDTO = new RegistroPuestoTrabajoProduccionDTO();

			registroDTO.setCodigoPuestoTrabajo(Long.valueOf(listaRecord.get(i).getAttribute(CODIGO_PUESTO_TRABAJO)));
			registroDTO.setNombrePuestoTrabajo(listaRecord.get(i).getAttribute(COLUMNA_NOMBREPUESTO_TRABAJO));

			registroDTO.setMesAnteriorTM(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_MES_ANTERIOR_TM)));
			registroDTO.setMesAnteriorHR(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_MES_ANTERIOR_HR)));

			// TODO: cambiar por getAttributeAsDouble
			registroDTO.setProduccionTM(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_PRODUCCION_TM)));
			registroDTO.setProduccionHR(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_PRODUCCION_HR)));
			registroDTO.setProduccionTMPH(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_PRODUCCION_TMPH)));
			registroDTO.setProduccionKCAL(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_KCAL_PRODUCCION)));
			registroDTO.setProduccionCarProd(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CARBON_PRODUCCION)));
			registroDTO.setProduccionCarCalent(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CARBON_CALENTAMIENTO)));
			registroDTO.setBunkerProduccion(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_BUNKER_PRODUCCION)));
			registroDTO.setBunkerCalent(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_BUNKER_CALENT)));

			registroDTO.setAjusteTM(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_AJUSTE_TM)));
			registroDTO.setAjusteHR(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_AJUSTE_HR)));

			registroDTO.setProduccionRealTM(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_PRODUCCION_REAL_TM)));
			registroDTO.setProduccionRealHR(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_PRODUCCION_REAL_HR)));
			registroDTO.setProduccionRealTMPH(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_PRODUCCION_REAL_TMPH)));
			registroDTO.setValorMax(Long.valueOf(listaRecord.get(i).getAttribute(VALOR_MAX)));

			Long valorMax = Long.valueOf(listaRecord.get(i).getAttribute(VALOR_MAX));
			if (valorMax == -1 || valorMax == null) {
				registroDTO.setBunkerProduccion(0d);
				registroDTO.setBunkerCalent(0d);
				registroDTO.setProduccionCarProd(0d);
				registroDTO.setProduccionCarCalent(0d);

				registroDTO.setBunkerProduccionReal(0d);
				;
				registroDTO.setBunkerCalentReal(0d);
				registroDTO.setProduccionRealCarProd(0d);
				registroDTO.setProduccionRealCarCalent(0d);
				registroDTO.setProduccionRealKCAL(0d);
			} else {
				registroDTO.setBunkerProduccion(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_BUNKER_PRODUCCION)));
				registroDTO.setBunkerCalent(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_BUNKER_CALENT)));
				registroDTO.setProduccionCarProd(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CARBON_PRODUCCION)));
				registroDTO.setProduccionCarCalent(Double.valueOf(listaRecord.get(i).getAttribute(COLUMNA_CARBON_CALENTAMIENTO)));

				// Validando KCAL produccion Real
				Double columnaProduccionRealKCAL = listaRecord.get(i).getAttributeAsDouble(COLUMNA_KCAL_PRODUCCIONREAL);
				if (columnaProduccionRealKCAL != null) {
					registroDTO.setProduccionRealKCAL(columnaProduccionRealKCAL);
				} else {
					registroDTO.setProduccionRealKCAL(0d);
				}

				// Validando carbon en producción real
				String columnaCarbonProduccionReal = listaRecord.get(i).getAttribute(COLUMNA_CARBON_PRODUCCION_REAL);
				if (columnaCarbonProduccionReal != null) {
					registroDTO.setProduccionRealCarProd(Double.valueOf(columnaCarbonProduccionReal));
				} else {
					registroDTO.setProduccionRealCarProd(0d);
				}

				// Validando carbon en calentamiento real
				String columnaCarbonCalentamientoReal = listaRecord.get(i).getAttribute(COLUMNA_CARBON_CALENTAMIENTO_REAL);
				if (columnaCarbonCalentamientoReal != null) {
					registroDTO.setProduccionRealCarCalent(Double.valueOf(columnaCarbonCalentamientoReal));
				} else {
					registroDTO.setProduccionRealCarCalent(0d);
				}

				// Validando bunker en producción real
				String columnaBunkerProduccionReal = listaRecord.get(i).getAttribute(COLUMNA_BUNKER_PRODUCCION_REAL);
				if (columnaBunkerProduccionReal != null) {
					registroDTO.setBunkerProduccionReal(Double.valueOf(columnaBunkerProduccionReal));
				} else {
					registroDTO.setBunkerProduccionReal(0d);
				}

				// Validando bunker en calentamiento real
				String columnaBunkerCalentReal = listaRecord.get(i).getAttribute(COLUMNA_BUNKER_CALENT_REAL);
				if (columnaBunkerCalentReal != null) {
					registroDTO.setBunkerCalentReal(Double.valueOf(columnaBunkerCalentReal));
				} else {
					registroDTO.setBunkerCalentReal(0d);
				}
			}

			registrosDTO.add(registroDTO);
		}

		return registrosDTO;
	}

	private void chequeoProduccionPuestoTrabajo(ListGridRecord record, Double tmphReal) {
		Double tmphMesAnt = record.getAttributeAsDouble(COLUMNA_MES_ANTERIOR_TMPH);

		Double tmphMesAntMax = tmphMesAnt * 1.05;
		Double tmphMesAntMin = tmphMesAnt * 0.95;

		// Validacion de las tmph del mes anterior
		boolean valido = tmphReal >= tmphMesAntMin && tmphReal <= tmphMesAntMax;

		// validacion en formato "Double - Double"
		String[] validacion = record.getAttribute(COLUMNA_VALIDACION).split(" - ");
		Double minimo = new Double(validacion[0]);
		Double maximo = new Double(validacion[1]);

		// Validacion de las tmph con respecto a la tasa nominal del puesto de
		// trabajo
		if (!valido) {
			valido = tmphReal >= minimo && tmphReal <= maximo;
			if (!valido) {
				record.set_baseStyle(ConstantesGWT.CSS_LIGHT_RED_BG);
			} else {
				record.set_baseStyle(null);
			}
		}
	}

	/**
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		if (records.length > 0) {
			for (ListGridRecord listGridRecord : records) {
				removeData(listGridRecord);
			}
		}
	}

	public LinkedHashMap<String, String> obtenerPuestosTrabajo() {

		LinkedHashMap<String, String> mapaPuestos = new LinkedHashMap<String, String>();
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			String codigoPuestoTrabajo = listGridRecord.getAttribute(CODIGO_PUESTO_TRABAJO);
			String nombrePuestoTrabajo = listGridRecord.getAttribute(COLUMNA_NOMBREPUESTO_TRABAJO);

			mapaPuestos.put(codigoPuestoTrabajo, nombrePuestoTrabajo);
		}

		return mapaPuestos;
	}

	/**
	 * Muestra y esconde columnas "especiales" (que solo aparecen para un grupo
	 * en específico) segun el grupo actual (clinker, caliza, otros). Ajusta el
	 * ancho del grid también.
	 * 
	 * @param grupoActual Grupo del producto actualmente seleccionado en la
	 *            pantalla
	 */
	public void configurarColumnas(String grupoActual) {
		try {
			// Ciclar por todas las columnas especiales
			for (Map.Entry<String, String[]> entry : columnasEspecialesPorGrupo.entrySet()) {
				String grupo = entry.getKey();
				String[] columnas = entry.getValue();

				// Esto hace que se escondan todas las columnas especiales
				// de todos los grupos que no sean el actual.
				// Si el grupo actual es null, se esconderán todas
				// las columnas especiales.
				if (grupo.equals(grupoActual)) {
					showFields(columnas);
				} else {
					hideFields(columnas);
				}
			}

			Integer width = tamanosPorGrupo.get(grupoActual);
			if (width != null) {
				setWidth(width);
			} else {
				setWidth(TAMANO_ESTANDAR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void configurarGridSegunGrupoClkCal(String nombreProducto) {
		if (nombreProducto.startsWith(NOMBRE_CLINKER) || nombreProducto.startsWith(NOMBRE_CAL_GRANULADA)) {
			showFields(new String[] { COLUMNA_CARBON_PRODUCCION, COLUMNA_CARBON_CALENTAMIENTO, COLUMNA_CARBON_PRODUCCION_REAL,
					COLUMNA_CARBON_CALENTAMIENTO_REAL, COLUMNA_KCAL_PRODUCCION, COLUMNA_KCAL_PRODUCCIONREAL });
		} else {
			hideFields(new String[] { COLUMNA_CARBON_PRODUCCION, COLUMNA_CARBON_CALENTAMIENTO, COLUMNA_CARBON_PRODUCCION_REAL,
					COLUMNA_CARBON_CALENTAMIENTO_REAL, COLUMNA_KCAL_PRODUCCION, COLUMNA_KCAL_PRODUCCIONREAL });
		}
	}

	private void hideFields(String[] clinkerFields) {
		for (String fieldName : clinkerFields) {
			hideField(fieldName);
		}
	}

	private void showFields(String[] clinkerFields) {
		for (String fieldName : clinkerFields) {
			showField(fieldName, true);
		}
	}

	/**
	 * Retorna 0 si valor es null, o valor en caso contrario.
	 * 
	 * @param valor Valor a chequear
	 * @return 0 si valor es null, o valor en caso contrario.
	 */
	public Double ceroSiNull(Double valor) {
		if (valor == null) {
			return 0d;
		} else {
			return valor;
		}
	}

	public void configurarGridSegunGrupoProducto(String grupoProducto) {
		factoresActuales = factoresPorGrupo.get(grupoProducto);
		configurarColumnas(grupoProducto);
		seAjustaConsumo = !gruposQueNOAjustanConsumo.contains(grupoProducto);
	}

	public boolean isSeAjustaConsumo() {
		return seAjustaConsumo;
	}

	public List<Row> getGraphRows() {
		final String[] columnasFuente = { CODIGO_PUESTO_TRABAJO, COLUMNA_NOMBREPUESTO_TRABAJO, NOMBRE_PRODUCTO,
				COLUMNA_AJUSTE_HR, COLUMNA_AJUSTE_TM, COLUMNA_PRODUCCION_TM, COLUMNA_PRODUCCION_HR, VALOR_MAX,
				COLUMNA_KCAL_PRODUCCION, COLUMNA_CARBON_CALENTAMIENTO, COLUMNA_CONSUMO_CARBON_SECO_OCULTO,
				COLUMNA_CARBON_PRODUCCION_REAL, COLUMNA_PRODUCCION_REAL_TM, COLUMNA_PODER_CALORIFICO_CARBON_PONDERADO,
				COLUMNA_BUNKER_PRODUCCION, COLUMNA_CARBON_PRODUCCION, COLUMNA_BUNKER_CALENT };

		List<Row> rows = new LinkedList<Row>();
		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		int rowNum = 0;
		for (Record record : this.getRecords()) {
			SimpleRow row = CellGraphUtil.convertToRow(this, record, columnasFuente, rowNum, colNumbers);

			CellSelector nombrePuestoTrabajoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_NOMBREPUESTO_TRABAJO, row);
			CellSelector nombreProductoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, NOMBRE_PRODUCTO, row);
			CellSelector poderCalorificoCarbonSelector = new FixedCellSelector(GRAPH_SOURCE_ID,
					COLUMNA_PODER_CALORIFICO_CARBON_PONDERADO, row);
			CellSelector bunkerProduccionSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_BUNKER_PRODUCCION, row);
			CellSelector TMRealPTSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_PRODUCCION_REAL_TM, row);

			CellSelector valorMaxSelector = new FixedCellSelector(GRAPH_SOURCE_ID, VALOR_MAX, row);
			CellSelector consumoCarbonSecoOcultoSelector = new FixedCellSelector(GRAPH_SOURCE_ID,
					COLUMNA_CONSUMO_CARBON_SECO_OCULTO, row);

			// ------------------CALCULAR NUEVO VALOR DE KCAL-REAL
			if (colNumbers.containsKey(COLUMNA_KCAL_PRODUCCIONREAL)) {
				CellSelectorGroup ajusteGroupKCalReal = new CellSelectorGroup(KcalFormulaEvaluator.NOMBRE_PUESTOTRABAJO,
						nombrePuestoTrabajoSelector, KcalFormulaEvaluator.NOMBRE_PRODUCTO, nombreProductoSelector,
						KcalFormulaEvaluator.PODER_CALORIFICO_CARBON_PONDERADO, poderCalorificoCarbonSelector,
						KcalFormulaEvaluator.CONSUMO_PETROLEO_BUNKER, bunkerProduccionSelector,
						KcalFormulaEvaluator.PRODUCCION_REAL_PT, TMRealPTSelector, KcalFormulaEvaluator.VALOR_MAX,
						valorMaxSelector, KcalFormulaEvaluator.CONSUMO_CARBON_SECO, consumoCarbonSecoOcultoSelector);
				row.putCell(COLUMNA_KCAL_PRODUCCIONREAL, new SmartGWTCell(ajusteGroupKCalReal, new KcalFormulaEvaluator(),
						record, COLUMNA_KCAL_PRODUCCIONREAL, colNumbers.get(COLUMNA_KCAL_PRODUCCIONREAL), rowNum, this));
			}

			// ------------------CALCULAR NUEVO VALOR DE CARPRODREAL
			if (colNumbers.containsKey(COLUMNA_CARBON_PRODUCCION)) {
				CellSelectorGroup ajusteGroupCarProdReal = new CellSelectorGroup(CarProdFormulaEvaluator.NOMBRE_PUESTOTRABAJO,
						nombrePuestoTrabajoSelector, CarProdFormulaEvaluator.NOMBRE_PRODUCTO, nombreProductoSelector,
						CarProdFormulaEvaluator.PODER_CALORIFICO_CARBON_PONDERADO, poderCalorificoCarbonSelector,
						CarProdFormulaEvaluator.CONSUMO_PETROLEO_BUNKER, bunkerProduccionSelector,
						CarProdFormulaEvaluator.PRODUCCION_REAL_PT, TMRealPTSelector, CarProdFormulaEvaluator.VALOR_MAX,
						valorMaxSelector, CarProdFormulaEvaluator.CONSUMO_CARBON_SECO, consumoCarbonSecoOcultoSelector);
				row.putCell(COLUMNA_CARBON_PRODUCCION_REAL,
						new SmartGWTCell(ajusteGroupCarProdReal, new CarProdFormulaEvaluator(), record,
								COLUMNA_CARBON_PRODUCCION_REAL, colNumbers.get(COLUMNA_CARBON_PRODUCCION_REAL), rowNum, this));
			}

			// ------------------CALCULAR NUEVO VALOR DE CARBON CALENTAMIENTO
			// REAL
			CellSelector carbonCalentSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_CARBON_CALENTAMIENTO, row);
			if (colNumbers.containsKey(COLUMNA_CARBON_CALENTAMIENTO)) {
				CellSelectorGroup ajusteGroupCarProdReal = new CellSelectorGroup(CarCalentFormulaEvaluator.NOMBRE_PUESTOTRABAJO,
						nombrePuestoTrabajoSelector, CarCalentFormulaEvaluator.NOMBRE_PRODUCTO, nombreProductoSelector,
						CarCalentFormulaEvaluator.PODER_CALORIFICO_CARBON_PONDERADO, poderCalorificoCarbonSelector,
						CarCalentFormulaEvaluator.CONSUMO_PETROLEO_BUNKER, bunkerProduccionSelector,
						CarCalentFormulaEvaluator.PRODUCCION_REAL_PT, TMRealPTSelector, CarCalentFormulaEvaluator.VALOR_MAX,
						valorMaxSelector, CarCalentFormulaEvaluator.CONSUMO_CARBON_SECO, consumoCarbonSecoOcultoSelector,
						CarCalentFormulaEvaluator.CARBON_CALENTAMIENTO, carbonCalentSelector);
				row.putCell(COLUMNA_CARBON_CALENTAMIENTO_REAL,
						new SmartGWTCell(ajusteGroupCarProdReal, new CarCalentFormulaEvaluator(), record,
								COLUMNA_CARBON_CALENTAMIENTO_REAL, colNumbers.get(COLUMNA_CARBON_CALENTAMIENTO_REAL), rowNum,
								this));
			}

			// ------------------CALCULAR BUNKER PRODUCCION REAL
			if (colNumbers.containsKey(COLUMNA_KCAL_PRODUCCION)) {
				CellSelectorGroup ajusteGroupBunkerProdReal = new CellSelectorGroup(
						BunkerProdFormulaEvaluator.NOMBRE_PUESTOTRABAJO, nombrePuestoTrabajoSelector,
						BunkerProdFormulaEvaluator.NOMBRE_PRODUCTO, nombreProductoSelector,
						BunkerProdFormulaEvaluator.PODER_CALORIFICO_CARBON_PONDERADO, poderCalorificoCarbonSelector,
						BunkerProdFormulaEvaluator.CONSUMO_PETROLEO_BUNKER, bunkerProduccionSelector,
						BunkerProdFormulaEvaluator.PRODUCCION_REAL_PT, TMRealPTSelector, BunkerProdFormulaEvaluator.VALOR_MAX,
						valorMaxSelector, BunkerProdFormulaEvaluator.CONSUMO_CARBON_SECO, consumoCarbonSecoOcultoSelector);
				row.putCell(COLUMNA_BUNKER_PRODUCCION_REAL,
						new SmartGWTCell(ajusteGroupBunkerProdReal, new BunkerProdFormulaEvaluator(), record,
								COLUMNA_BUNKER_PRODUCCION_REAL, colNumbers.get(COLUMNA_BUNKER_PRODUCCION_REAL), rowNum, this));
			}

			CellSelector bunkerCalentSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_BUNKER_CALENT, row);
			if (colNumbers.containsKey(COLUMNA_KCAL_PRODUCCION)) {
				CellSelectorGroup ajusteGroupBunkerProdReal = new CellSelectorGroup(
						BunkerCalentFormulaEvaluator.NOMBRE_PUESTOTRABAJO, nombrePuestoTrabajoSelector,
						BunkerCalentFormulaEvaluator.NOMBRE_PRODUCTO, nombreProductoSelector,
						BunkerCalentFormulaEvaluator.PODER_CALORIFICO_CARBON_PONDERADO, poderCalorificoCarbonSelector,
						BunkerCalentFormulaEvaluator.CONSUMO_PETROLEO_BUNKER, bunkerProduccionSelector,
						BunkerCalentFormulaEvaluator.PRODUCCION_REAL_PT, TMRealPTSelector,
						BunkerCalentFormulaEvaluator.VALOR_MAX, valorMaxSelector,
						BunkerCalentFormulaEvaluator.CONSUMO_CARBON_SECO, consumoCarbonSecoOcultoSelector,
						BunkerCalentFormulaEvaluator.BUNKER_CALENT, bunkerCalentSelector);
				row.putCell(COLUMNA_BUNKER_CALENT_REAL,
						new SmartGWTCell(ajusteGroupBunkerProdReal, new BunkerCalentFormulaEvaluator(), record,
								COLUMNA_BUNKER_CALENT_REAL, colNumbers.get(COLUMNA_BUNKER_CALENT_REAL), rowNum, this));
			}

			// prod_real_tm = produccion_tm + ajuste_tm
			CellSelector ajusteTMSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_AJUSTE_TM, row);
			CellSelector prodTMSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_PRODUCCION_TM, row);
			CellSelectorGroup ajusteGroup = new CellSelectorGroup("uno", ajusteTMSelector, "dos", prodTMSelector);
			row.putCell(COLUMNA_PRODUCCION_REAL_TM, new SmartGWTCell(ajusteGroup, new SumAllFormulaEvaluator(), record,
					COLUMNA_PRODUCCION_REAL_TM, colNumbers.get(COLUMNA_PRODUCCION_REAL_TM), rowNum, this));

			// prod_real_hr = prod_hr + ajuste_hr
			CellSelector prodHRSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_PRODUCCION_HR, row);
			CellSelector ajusteHRSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_AJUSTE_HR, row);
			CellSelectorGroup realHrGroup = new CellSelectorGroup("uno", prodHRSelector, "dos", ajusteHRSelector);
			row.putCell(COLUMNA_PRODUCCION_REAL_HR, new SmartGWTCell(realHrGroup, new SumAllFormulaEvaluator(), record,
					COLUMNA_PRODUCCION_REAL_HR, colNumbers.get(COLUMNA_PRODUCCION_REAL_HR), rowNum, this));

			// prod_real_tmph = prod_real_tm / prod_real_hr
			CellSelector realTmSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_PRODUCCION_REAL_TM, row);
			CellSelector realHrSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_PRODUCCION_REAL_HR, row);
			CellSelectorGroup tmphGroup = new CellSelectorGroup(DivisionFormulaEvaluator.DIVIDENDO, realTmSelector,
					DivisionFormulaEvaluator.DIVISOR, realHrSelector);
			row.putCell(COLUMNA_PRODUCCION_REAL_TMPH, new SmartGWTCell(tmphGroup, new DivisionFormulaEvaluator(), record,
					COLUMNA_PRODUCCION_REAL_TMPH, colNumbers.get(COLUMNA_PRODUCCION_REAL_TMPH), rowNum, this));

			// -----------------columna produccion real carbon produccion
			if (colNumbers.containsKey(COLUMNA_CARBON_PRODUCCION)) {
				CellSelector carbonProduccionSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_CARBON_PRODUCCION, row);
				agregarSumaCarbonProduccion(colNumbers, rowNum, record, row, carbonProduccionSelector,
						ConstantesGWT.GRUPO_CARBON_MIX, COLUMNA_CONSUMO_CARBON_SECO_OCULTO);
			}

			// crudo/clinker = [sum(ccpt.consumoreal) where crudo] /
			// this.prod_real_tm
			// TODO: manejar carbon startswith, no solo mix
			agregarFormulaFactor(colNumbers, rowNum, record, row, realTmSelector, ConstantesGWT.GRUPO_CRUDO,
					COL_RELACION_CRUDO_CLINKER);

			// carbon/clinker = [sum(ccpt.consumoreal) where carbon] /
			// this.prod_real_tm
			agregarFormulaFactor(colNumbers, rowNum, record, row, realTmSelector, ConstantesGWT.GRUPO_CARBON_MIX,
					COL_RELACION_CARBON_CLINKER);

			// caliza/cal = [sum(ccpt.consumoreal) where caliza] /
			// this.prod_real_tm
			agregarFormulaFactor(colNumbers, rowNum, record, row, realTmSelector, ConstantesGWT.GRUPO_CALIZA,
					COL_FACTOR_CALIZA_CAL);

			// carbon/cal = [sum(ccpt.consumoreal) where caliza] /
			// this.prod_real_tm
			agregarFormulaFactor(colNumbers, rowNum, record, row, realTmSelector, ConstantesGWT.GRUPO_CARBON_MIX,
					COL_FACTOR_CARBON_CAL);

			rows.add(row);
			rowNum++;
		}
		return rows;
	}

	private void agregarSumaCarbonProduccion(Map<String, Integer> colNumbers, int rowNum, Record record, SimpleRow row,
			CellSelector realTmSelector, String grupo, String col) {
		if (!colNumbers.containsKey(col)) {
			return;
		}

		CellSelector elementoSelector = new RowFilterCellSelector(new RowFilter(
				GridConsumoComponentesPuestoTrabajo.CODIGO_PUESTO_TRABAJO, record.getAttribute(CODIGO_PUESTO_TRABAJO),
				GridConsumoComponentesPuestoTrabajo.GRUPO_COMPONENTE, grupo),
				GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID, GridConsumoComponentesPuestoTrabajo.DIFERENCIA);

		CellSelectorGroup factorGroup = new CellSelectorGroup("uno", elementoSelector, "dos", realTmSelector);

		row.putCell(col, new SmartGWTCell(factorGroup, new SumAllFormulaEvaluator(), record, col, colNumbers.get(col), rowNum,
				this));
	}

	private void agregarFormulaFactor(Map<String, Integer> colNumbers, int rowNum, Record record, SimpleRow row,
			CellSelector realTmSelector, String grupo, String col) {
		if (!colNumbers.containsKey(col)) {
			return;
		}

		CellSelector elementoSelector = new RowFilterCellSelector(new RowFilter(
				GridConsumoComponentesPuestoTrabajo.CODIGO_PUESTO_TRABAJO, record.getAttribute(CODIGO_PUESTO_TRABAJO),
				GridConsumoComponentesPuestoTrabajo.GRUPO_COMPONENTE, grupo),
				GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID, GridConsumoComponentesPuestoTrabajo.CONSUMOREAL_TONELADAS);
		CellSelectorGroup factorGroup = new CellSelectorGroup(DivisionFormulaEvaluator.DIVIDENDO, elementoSelector,
				DivisionFormulaEvaluator.DIVISOR, realTmSelector);
		row.putCell(col, new SmartGWTCell(factorGroup, new DivisionFormulaEvaluator(), record, col, colNumbers.get(col), rowNum,
				this));
	}
}
