package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.GrupoAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ParteTecnicoService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ParteTecnicoServiceAsync;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.Testeable;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.TestingAsyncCallback;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.TestingService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.TestingServiceAsync;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Combos;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.UtilConverter;

import com.dbaccess.cellmanager.RowSources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PantallaAjusteProduccion implements ClienteServicioGwt, RefrescadorGrids {

	private static final String BARRA_PROGRESO = "barraProgreso";

	private static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);

	private static DynamicForm formularioCombos = new DynamicForm();

	private AjusteProductoDTO ajusteProductoDTOultimo;

	// TODO: static ????. suena a dos sesiones del browser sobreescribiendose
	private static SelectItem lineaNegocioItem;
	private static SelectItem mesesItem;
	private static SelectItem aniosItem;
	private static SelectItem grupoAjusteItem;
	private static SelectItem productoItem;
	public static Label etiquetaValorAjuste;
	public static Label etiquetaFL;
	public static Label etiquetaF;

	// Etiqueta Combustible
	private TextItem txtDiaFactura;
	private TextItem txtFactura;
	private TextItem txtConsumoRestante;
	private TextItem txtTotal;
	private static TextItem txtMerma;
	private boolean estaEnTransaccion = false;

	private ParteTecnicoServiceAsync servicioParteTecnico = GWT.create(ParteTecnicoService.class);
	private TestingServiceAsync servicioTesting = GWT.create(TestingService.class);

	private VLayout layoutTablaBalance = new VLayout();
	private VLayout layoutComponentesAjuste = new VLayout();

	private VLayout layoutTablaCombustible = new VLayout();

	private Canvas canvasTablaBalance = new Canvas();
	private Canvas canvasTablaAjuste = new Canvas();
	private Canvas canvasTablaPTProduccion = new Canvas();

	private Canvas canvasTablaCombustible = new Canvas();

	private GridTablaBalance2 gridTablaBalance;
	private GridMovimientosDeAjuste gridMovimientosDeAjuste;
	private GridPuestoTrabajoProduccion gridPuestoTrabajoProduccion;

	private GridAjustePuestoTrabajo gridAjusteCombustible;
	private GridMovimientosDeAjuste gridMovimientosDeAjusteCombustible;

	private static final String NOMBRE_IMAGEN_CONSULTAR = "imagenConsultar";
	private static final String NOMBRE_ETIQUETA_CONSULTAR = "etiquetaConsultar";
	private static final String NOMBRE_ESTILO_CONSULTAR = "consultar";
	private static final String CONSULTAR = "Consultar";

	private static final String NOMBRE_IMAGEN_PRUEBA = "imagenPrueba";
	private static final String NOMBRE_ETIQUETA_PRUEBA = "etiquetaPrueba";
	private static final String NOMBRE_ESTILO_PRUEBA = "prueba";
	private static final String PRUEBA = "Prueba";

	private static final String NOMBRE_IMAGEN_PRUEBA_SU = "imagenPruebaSu";
	private static final String NOMBRE_ETIQUETA_PRUEBA_SU = "etiquetaPruebaSu";
	private static final String NOMBRE_ESTILO_PRUEBA_SU = "pruebaSu";
	private static final String PRUEBA_SU = "p. setup";

	public static final String FORMULARIO_COMBOS = "formularioCombos";
	public static final String FORMULARIO_OBSERVACIONES = "observaciones";

	public static final String DIV_GRID_BALANCE = "gridBalance";
	public static final String DIV_GRIDS_AJUSTES = "gridsAjustes";
	public static final String DIV_GRIDS_AJUSTES_COMBUSTIBLE = "gridsAjustesCombustible";

	private TabSet tabSetConmponentesAjuste;
	private TabSet tabSetAjusteCombustible;

	private GridMovimientoComponentes gridMovimientoComponentes;
	private GridConsumoComponentesPuestoTrabajo gridConsumoPuestoTrabajo;
	private GridConsumoComponentesPuestoTrabajo gridConsumoComponentes;

	private Label lblFactorAntracita;
	private Double ajuste;

	private boolean movimientosAjusteGenerados = false;

	private Map<String, String> mapaPropiedades = new HashMap<String, String>();
	private Map<String, String> mapaParametrosSistema = new HashMap<String, String>();

	private Img descargarCubicacion;

	private TextArea txtObservaciones;

	private Label etiquetaGrabar;

	private Img imagenGrabar;

	private Img imagenGenerar;

	private Label etiquetaGenerar;

	private Img imagenNuevo;

	private Label etiquetaNuevo;

	private StaticTextItem etiquetaEstado;

	private boolean esClinker;

	private boolean esClinkerCalGranulada;

	private Map<Long, ProductoDTO> mapaProductos;

	private DialogoCrearMovManual dialogoCrearMovManual;

	public static final int MARGEN = 20;

	private Boolean ejecutarMovBunker = Boolean.FALSE;

	public static final int codCemetoI = 172;
	public static final int codCemetoMS = 171;
	public static final int codCemeto1co = 170;
	public static final int codCemetoV = 173;
	public static final Long codClinker = 157L;
	public static final Long codClinkerV = 156L;

	private String DENSIDAD_PETROLEO = "DENSIDAD_PETROLEO_KCAL";
	private String DENSIDAD_PETROLEO_CAL = "DENSIDAD_PETROLEO_KCAL_CAL";
	private String PODER_CALORIFICO_PETROLEO = "RENDIMIENTO_TERMICO_PETROLEO_KCAL";
	private String PODER_CALORIFICO_PETROLEO_CAL = "RENDIMIENTO_TERMICO_PETROLEO_KCAL_CAL";
	private String PROGRESION_CARBON_KCAL = "PROGRESION_CARBON_KCAL";
	private String PROGRESION_BUNKER_KCAL = "PROGRESION_BUNKER_KCAL";
	public static String DENSIDAD_PETROLEO_VALOR;
	public static String DENSIDAD_PETROLEO_CAL_VALOR;
	public static String PODER_CALORIFICO_PETROLEO_VALOR;
	public static String PODER_CALORIFICO_PETROLEO_CAL_VALOR;
	public static String PROGRESION_CARBON_KCAL_VALOR;
	public static String PROGRESION_BUNKER_KCAL_VALOR;
	public static String MERMA;

	/**
	 * Método para generar el panel general de la pantalla de
	 * RegistroAjusteProduccionMes
	 */
	public void generarPanelGeneral() {

		// Se buscan ciertas propiedades en el resource.properties que se van a
		// utilizar para ciertas validaciones y ejecucion de formulas
		List<String> listaClaves = Arrays.asList(ConstantesGWT.PARTE_DIARIO_CODIGOS_CLINKER, ConstantesGWT.NOMBRE_BUNKER_6,
				ConstantesGWT.CAR_MIX_S1, ConstantesGWT.CAR_MIX_S2, ConstantesGWT.CODIGO_PRODUCTOS_CLINKER_O_CALGRANULADA);
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
				cargarParametroSistema();

			}

		});
	}

	private void cargarParametroSistema() {
		servicioComunicacion.obtenerMapaParametroSistema(new AsyncCallback<Map<String, String>>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);

			}

			public void onSuccess(Map<String, String> mapa) {
				Iterator<String> iterator = mapa.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next().toString();
					String value = mapa.get(key).toString();
					mapaParametrosSistema.put(key, value);
				}
				DENSIDAD_PETROLEO_VALOR = mapaParametrosSistema.get(DENSIDAD_PETROLEO);
				DENSIDAD_PETROLEO_CAL_VALOR = mapaParametrosSistema.get(DENSIDAD_PETROLEO_CAL);
				PODER_CALORIFICO_PETROLEO_VALOR = mapaParametrosSistema.get(PODER_CALORIFICO_PETROLEO);
				PODER_CALORIFICO_PETROLEO_CAL_VALOR = mapaParametrosSistema.get(PODER_CALORIFICO_PETROLEO_CAL);
				PROGRESION_CARBON_KCAL_VALOR = mapaParametrosSistema.get(PROGRESION_CARBON_KCAL);
				PROGRESION_BUNKER_KCAL_VALOR = mapaParametrosSistema.get(PROGRESION_BUNKER_KCAL);
				initGUI();

			}
		});
	}

	private void initGUI() {

		try {

			dibujarBandaIconos();

			VerticalPanel northPanel = initFormularioFiltros();
			RootPanel.get(FORMULARIO_COMBOS).add(northPanel);

			// formularioObservaciones = initFormularioObservaciones();
			RootPanel.get(FORMULARIO_OBSERVACIONES).add(initFormularioObservaciones());

			TabSet tabSetBalance = new TabSet();
			tabSetBalance.setTabBarPosition(Side.TOP);
			tabSetBalance.setWidth100();
			tabSetBalance.setHeight(150);

			Tab tabTablaBalance = new Tab(CONSTANTES.titleTablaBalance());
			tabSetBalance.addTab(tabTablaBalance);

			layoutTablaBalance.setWidth(700);
			layoutTablaBalance.setHeight(170);

			gridTablaBalance = new GridTablaBalance2();
			canvasTablaBalance.addChild(gridTablaBalance);
			HLayout hLayout = new HLayout();
			canvasTablaBalance.addChild(hLayout);

			gridMovimientosDeAjuste = new GridMovimientosDeAjuste(this);
			canvasTablaAjuste.addChild(gridMovimientosDeAjuste);

			tabTablaBalance.setPane(canvasTablaBalance);

			layoutTablaBalance.addMember(tabSetBalance);

			RootPanel.get(DIV_GRID_BALANCE).add(layoutTablaBalance);
			// Aqui empieza el formulario de ajustes para los productos
			tabSetConmponentesAjuste = new TabSet();
			tabSetConmponentesAjuste.setTabBarPosition(Side.TOP);
			tabSetConmponentesAjuste.setHeight(450);

			Tab tabTablaPuestoTrabajoProduccion = new Tab(CONSTANTES.titleTablaPuestoTrabajoProduccion());
			tabSetConmponentesAjuste.addTab(tabTablaPuestoTrabajoProduccion);

			String nombreBunker = mapaPropiedades.get(ConstantesGWT.NOMBRE_BUNKER_6);

			gridMovimientoComponentes = new GridMovimientoComponentes(this, nombreBunker);
			gridConsumoComponentes = new GridConsumoComponentesPuestoTrabajo(null, false, null, mapaPropiedades,
					gridMovimientosDeAjuste);
			gridConsumoPuestoTrabajo = new GridConsumoComponentesPuestoTrabajo(gridMovimientoComponentes, true,
					gridConsumoComponentes, mapaPropiedades, gridMovimientosDeAjuste);

			canvasTablaPTProduccion.setWidth100();
			gridPuestoTrabajoProduccion = new GridPuestoTrabajoProduccion(gridConsumoPuestoTrabajo, gridTablaBalance,
					gridConsumoComponentes);

			tabSetConmponentesAjuste.setWidth(gridPuestoTrabajoProduccion.getWidth() + MARGEN);

			gridConsumoPuestoTrabajo.setGridPuestoTrabajoProduccion(gridPuestoTrabajoProduccion);
			gridMovimientoComponentes.setGridMovimientosDeAjuste(gridMovimientosDeAjuste);
			gridMovimientoComponentes.setGridConsumoPuestoTrabajo(gridConsumoPuestoTrabajo);
			gridConsumoComponentes.setGridConsumoPuestoTrabajo(gridConsumoPuestoTrabajo);

			canvasTablaPTProduccion.addChild(gridPuestoTrabajoProduccion);

			tabTablaPuestoTrabajoProduccion.setPane(canvasTablaPTProduccion);

			Tab tabComponentesPorPuestoTrab = new Tab(CONSTANTES.titleConsumoComponentesPorPuestoTrabajo());
			tabSetConmponentesAjuste.addTab(tabComponentesPorPuestoTrab);

			Tab tabConsumoComponentes = new Tab(CONSTANTES.titleConsumoComponentes());
			tabSetConmponentesAjuste.addTab(tabConsumoComponentes);

			Tab tabMovimientoComponentes = new Tab(CONSTANTES.titleMovimientoComponentes());
			tabSetConmponentesAjuste.addTab(tabMovimientoComponentes);

			Tab tabMovimientosAjuste = new Tab(CONSTANTES.titleMovimientosAjustes());
			tabMovimientosAjuste.setPane(canvasTablaAjuste);

			tabSetConmponentesAjuste.addTab(tabMovimientosAjuste);

			Canvas canvasConsumoComponentesPorPuestotrab = new Canvas();
			canvasConsumoComponentesPorPuestotrab.addChild(gridConsumoPuestoTrabajo);
			tabComponentesPorPuestoTrab.setPane(canvasConsumoComponentesPorPuestotrab);

			Canvas canvasConsumoComponentes = new Canvas();
			canvasConsumoComponentes.addChild(gridConsumoComponentes);
			tabConsumoComponentes.setPane(canvasConsumoComponentes);

			Canvas canvasMovimientoComponentes = new Canvas();
			canvasMovimientoComponentes.addChild(gridMovimientoComponentes);
			tabMovimientoComponentes.setPane(canvasMovimientoComponentes);

			lblFactorAntracita = new Label();
			lblFactorAntracita.setWidth(180);
			lblFactorAntracita.setHeight(40);
			lblFactorAntracita.setVisible(false);

			layoutComponentesAjuste.setWidth(700);
			layoutComponentesAjuste.setHeight(450);
			layoutComponentesAjuste.addMember(lblFactorAntracita);
			layoutComponentesAjuste.addMember(tabSetConmponentesAjuste);
		
			RootPanel.get(DIV_GRIDS_AJUSTES).add(layoutComponentesAjuste);

			// Hoja de Ajuste del Bunker

			tabSetAjusteCombustible = new TabSet();
			tabSetAjusteCombustible.setTabBarPosition(Side.TOP);
			tabSetAjusteCombustible.setHeight(450);

			Tab tabAjusteCombustible = new Tab(CONSTANTES.titleMovimientoComponentes());
			tabSetAjusteCombustible.addTab(tabAjusteCombustible);

			Tab tabMovimentoAjusteCombustible = new Tab(CONSTANTES.titleMovimientosAjustes());
			tabSetAjusteCombustible.addTab(tabMovimentoAjusteCombustible);

			// MOV AJUSTE
			gridMovimientosDeAjusteCombustible = new GridMovimientosDeAjuste(this, "GRIDaJUSTEcOM");

			Canvas canvarMovAjuste = new Canvas();
			canvarMovAjuste.setWidth100();
			canvarMovAjuste.addChild(gridMovimientosDeAjusteCombustible);
			tabMovimentoAjusteCombustible.setPane(canvarMovAjuste);

			gridAjusteCombustible = new GridAjustePuestoTrabajo();
			canvasTablaCombustible.setWidth100();
			tabSetAjusteCombustible.setWidth(gridAjusteCombustible.getWidth() + MARGEN);

			canvasTablaCombustible.addChild(gridAjusteCombustible);
			tabAjusteCombustible.setPane(canvasTablaCombustible);

			layoutTablaCombustible.setWidth(700);
			layoutTablaCombustible.setHeight(450);

			layoutTablaCombustible.addMember(initFormularioCombustible());
			layoutTablaCombustible.addMember(tabSetAjusteCombustible);

		
			RootPanel.get(DIV_GRIDS_AJUSTES_COMBUSTIBLE).add(layoutTablaCombustible);
			RootPanel.get(DIV_GRIDS_AJUSTES_COMBUSTIBLE).setStyleName(ConstantesGWT.CSS_OCULTAR_GRID);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que crea la banda de iconos del menu superior
	 */
	private void dibujarBandaIconos() {

		// Refrescar
		Img imagenRefrescar = new Img("", 18, 20);
		imagenRefrescar.setName(ConstantesGWT.NOMBRE_IMAGEN_REFRESCAR);
		imagenRefrescar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRefrescar();
			}
		});
		Label etiquetaRefrescar = new Label(ConstantesGWT.REFRESCAR);
		etiquetaRefrescar.setCursor(Cursor.HAND);
		etiquetaRefrescar.setHeight(15);
		etiquetaRefrescar.setID(ConstantesGWT.NOMBRE_ETIQUETA_REFRESCAR);
		etiquetaRefrescar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRefrescar();
			}

		});
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REFRESCAR).add(imagenRefrescar);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REFRESCAR).setStyleName(ConstantesGWT.NOMBRE_ESTILO_REFRESCAR);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_REFRESCAR).add(etiquetaRefrescar);

		// Nuevo
		imagenNuevo = new Img("", 18, 20);
		imagenNuevo.setName(ConstantesGWT.NOMBRE_IMAGEN_NUEVO);
		imagenNuevo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickNuevo();
			}

		});
		etiquetaNuevo = new Label(ConstantesGWT.NUEVO);
		etiquetaNuevo.setCursor(Cursor.HAND);
		etiquetaNuevo.setHeight(15);
		etiquetaNuevo.setDisabled(true);
		etiquetaNuevo.setID(ConstantesGWT.NOMBRE_ETIQUETA_NUEVO);
		etiquetaNuevo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickNuevo();
			}
		});
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_NUEVO).add(imagenNuevo);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_NUEVO).setStyleName(ConstantesGWT.NOMBRE_ESTILO_NUEVO);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_NUEVO).add(etiquetaNuevo);

		// Generar Movimientos
		imagenGenerar = new Img("", 18, 20);
		imagenGenerar.setName(ConstantesGWT.NOMBRE_IMAGEN_GENERAR);
		imagenGenerar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGenerar();
			}
		});
		etiquetaGenerar = new Label(ConstantesGWT.GENERAR);
		etiquetaGenerar.setCursor(Cursor.HAND);
		etiquetaGenerar.setHeight(15);
		etiquetaGenerar.setID(ConstantesGWT.NOMBRE_ETIQUETA_GENERAR);
		etiquetaGenerar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGenerar();
			}
		});
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GENERAR).add(imagenGenerar);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GENERAR).setStyleName(ConstantesGWT.NOMBRE_ESTILO_GENERAR);
		RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GENERAR).add(etiquetaGenerar);
		// Grabar
		imagenGrabar = new Img("", 18, 20);
		imagenGrabar.setName(ConstantesGWT.NOMBRE_IMAGEN_GRABAR);
		imagenGrabar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGrabar();
			}

		});
		etiquetaGrabar = new Label(ConstantesGWT.GRABAR);
		etiquetaGrabar.setCursor(Cursor.HAND);
		etiquetaGrabar.setHeight(15);
		etiquetaGrabar.setID(ConstantesGWT.NOMBRE_ETIQUETA_GRABAR);
		etiquetaGrabar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickGrabar();
			}
		});

		if (RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GRABAR) != null) {
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GRABAR).add(imagenGrabar);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GRABAR).setStyleName(ConstantesGWT.NOMBRE_ESTILO_GRABAR);
			RootPanel.get(ConstantesGWT.NOMBRE_ESTILO_GRABAR).add(etiquetaGrabar);
		}

		// Regresar a consultar
		Img imagenConsultar;
		imagenConsultar = new Img("", 18, 20);
		imagenConsultar.setName(NOMBRE_IMAGEN_CONSULTAR);
		imagenConsultar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickRegresar();
			}
		});
		Label etiquetaConsultar;
		etiquetaConsultar = new Label(CONSULTAR);
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

		// Prueba automatizada set up
		Img imagenPruebaSu;
		imagenPruebaSu = new Img("", 18, 20);
		imagenPruebaSu.setName(NOMBRE_IMAGEN_PRUEBA_SU);
		imagenPruebaSu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickPruebaSu();
			}
		});
		Label etiquetaPruebaSu;
		etiquetaPruebaSu = new Label(PRUEBA_SU);
		etiquetaPruebaSu.setCursor(Cursor.HAND);
		etiquetaPruebaSu.setHeight(15);
		etiquetaPruebaSu.setID(NOMBRE_ETIQUETA_PRUEBA_SU);
		etiquetaPruebaSu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickPruebaSu();
			}
		});
		RootPanel.get(NOMBRE_ESTILO_PRUEBA_SU).add(imagenPruebaSu);
		// RootPanel.get(NOMBRE_ESTILO_PRUEBA).setStyleName(NOMBRE_ESTILO_PRUEBA);
		RootPanel.get(NOMBRE_ESTILO_PRUEBA_SU).add(etiquetaPruebaSu);

		// Prueba automatizada
		Img imagenPrueba;
		imagenPrueba = new Img("", 18, 20);
		imagenPrueba.setName(NOMBRE_IMAGEN_PRUEBA);
		imagenPrueba.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickPrueba();
			}
		});
		Label etiquetaPrueba;
		etiquetaPrueba = new Label(PRUEBA);
		etiquetaPrueba.setCursor(Cursor.HAND);
		etiquetaPrueba.setHeight(15);
		etiquetaPrueba.setID(NOMBRE_ETIQUETA_PRUEBA);
		etiquetaPrueba.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventoClickPrueba();
			}
		});
		RootPanel.get(NOMBRE_ESTILO_PRUEBA).add(imagenPrueba);
		// RootPanel.get(NOMBRE_ESTILO_PRUEBA).setStyleName(NOMBRE_ESTILO_PRUEBA);
		RootPanel.get(NOMBRE_ESTILO_PRUEBA).add(etiquetaPrueba);

	}

	protected void eventoClickPruebaSu() {
		final String ID_LINEA_CEMENTO = "1";
		final String ID_LINEA_CAL = "2";
		final String ID_GRUPO_LINEA_CEMENTO = "1";
		final String ID_GRUPO_LINEA_CAL = "2";
		final String ID_PROD_CRU_I = "158";
		final String ID_PROD_CAL_GRANULADA = "153";
		final String ID_PROD_CLK_I = "157";
		final String ID_PROD_TP_I = "172";
		final String ID_PROD_TP_V = "173";
		final String ID_CAL_MOLIDA = "169";

		String lineaNegocio = ID_LINEA_CEMENTO;
		String mes = "Marzo";
		String grupo = ID_GRUPO_LINEA_CEMENTO;
		String producto = ID_PROD_TP_V;

		mesesItem.setValue(mes);
		lineaNegocioItem.setValue(lineaNegocio);
		cargarComboGrupoAjuste(Long.parseLong(lineaNegocio));

		Window.alert("antes de grupo");
		grupoAjusteItem.setValue(grupo);
		cargarComboProducto(Long.parseLong(grupo));

		Window.alert("antes de producto");
		productoItem.setValue(producto);
		manejarCambioProducto(producto);
	}

	protected void eventoClickPrueba() {
		try {
			Window.alert("esta ajustado:" + (ajusteProductoDTOultimo != null));

			String productoActual = productoItem.getValue().toString();
			checkTesteable(productoActual, gridPuestoTrabajoProduccion);
			exportarCsv(productoActual, gridPuestoTrabajoProduccion);
			checkTesteable(productoActual, gridConsumoPuestoTrabajo);
			exportarCsv(productoActual, gridConsumoPuestoTrabajo);
			checkTesteable(productoActual, gridConsumoComponentes);
			exportarCsv(productoActual, gridConsumoComponentes);
			checkTesteable(productoActual, gridMovimientoComponentes);
			exportarCsv(productoActual, gridMovimientoComponentes);
			checkTesteable(productoActual, gridMovimientosDeAjuste);
			exportarCsv(productoActual, gridMovimientosDeAjuste);
			checkTesteable(productoActual, gridTablaBalance);
			exportarCsv(productoActual, gridTablaBalance);
			Window.alert("Ver resultado de pruebas en el log de gwt devmode");
		} catch (Exception e) {
			e.printStackTrace();
			GWT.log(e.getMessage());
		}
	}

	private void checkTesteable(String prefijoNombre, Testeable testeable) throws Exception {
		String nombreArchivo = prefijoNombre + "_" + testeable.getID() + ".json";
		servicioTesting.assertFileEqualsExpected(testeable.exportarComoJson(), nombreArchivo, new TestingAsyncCallback<Void>(
				nombreArchivo));
	}

	private void exportarCsv(String prefijoNombre, Testeable testeable) throws Exception {
		String nombreArchivo = prefijoNombre + "_" + testeable.getID() + ".csv";
		servicioTesting.grabarArchivo(testeable.exportarComoCsv(), nombreArchivo, new TestingAsyncCallback<Void>(nombreArchivo));
	}

	private void generarGrafo() {

		RowSources sources = new RowSources();
		sources.put(GridPuestoTrabajoProduccion.GRAPH_SOURCE_ID, gridPuestoTrabajoProduccion.getGraphRows());
		sources.put(GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID, gridConsumoPuestoTrabajo.getGraphRows());
		sources.put(GridMovimientoComponentes.GRAPH_SOURCE_ID, gridMovimientoComponentes.getGraphRows());
		sources.put(GridMovimientosDeAjuste.GRAPH_SOURCE_ID, gridMovimientosDeAjuste.getGraphRows());
		sources.put(GridTablaBalance2.GRAPH_SOURCE_ID, gridTablaBalance.getGraphRows());

		gridConsumoComponentes.cargarGridConsumoAgrupado();

		sources.put(GridConsumoComponentesPuestoTrabajo.GROUPED_GRAPH_SOURCE_ID, gridConsumoComponentes.getGroupedGraphRows());

		SmartGWTCellManager cellManager = new SmartGWTCellManager(sources);
		cellManager.setGridsToRefresh(new ListGrid[] { gridPuestoTrabajoProduccion, gridConsumoPuestoTrabajo,
				gridConsumoComponentes, gridMovimientoComponentes, gridMovimientosDeAjuste, gridTablaBalance });

		gridConsumoPuestoTrabajo.setCellManager(cellManager);
		gridMovimientoComponentes.setCellManager(cellManager);

		// GWT.log(cellManager.asGraphViz());

		gridMovimientosDeAjuste.setCellManager(cellManager);

		// Window.alert("listo");

		// HACK: Para los campos de "ingeniería inversa":
		// - Se llenan con cero antes de calcular, para evitar nulls
		// - Se calcula todo
		// - Se llenan con datos iniciales resultantes del cálculo
		// Hace falta una solución más limpia. Ej. "etapas" donde se calculan
		// unas
		// formulas y otras no.
		gridConsumoPuestoTrabajo.copiarDatosIniciales(cellManager, null, GridConsumoComponentesPuestoTrabajo.ETAPA_CON_CERO);

		cellManager.recalcAll();
		int codigoProducto = obtenerCodigoProducto().intValue();

		switch (codigoProducto) {
		case codCemetoI:
			gridConsumoPuestoTrabajo.agregarFormulasSegunProducto(
					cellManager.getSources().get(GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID), codClinker);
			break;
		case codCemetoMS:
			gridConsumoPuestoTrabajo.agregarFormulasSegunProducto(
					cellManager.getSources().get(GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID), codClinker);
			break;
		case codCemeto1co:
			gridConsumoPuestoTrabajo.agregarFormulasSegunProducto(
					cellManager.getSources().get(GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID), codClinker);
			break;
		case codCemetoV:
			gridConsumoPuestoTrabajo.agregarFormulasSegunProducto(
					cellManager.getSources().get(GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID), codClinkerV);
			break;

		default:
			// HACK: el grid pide sus propios rows en esta llamada, no es limpio
			gridConsumoPuestoTrabajo.agregarFormulasAdicionales(cellManager.getSources().get(
					GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID));
		}

		cellManager.rebuildGraphAndDependencies();

		if (ajusteProductoDTOultimo == null) {
			// Si no hay ajuste, sugerir uno en los porcentajes
			gridConsumoPuestoTrabajo.copiarDatosIniciales(cellManager, GridConsumoComponentesPuestoTrabajo.ETAPA_CON_CERO,
					GridConsumoComponentesPuestoTrabajo.ETAPA_SUGERIR);
		}

		gridConsumoPuestoTrabajo.copiarDatosIniciales(cellManager, GridConsumoComponentesPuestoTrabajo.ETAPA_CON_CERO,
				GridConsumoComponentesPuestoTrabajo.ETAPA_DE_INICIAL);

		// HACK:
		gridMovimientoComponentes.cerrarIniciales(cellManager);

		// HACK: casos borde como formulas sin celda dependiente necesitan este
		// recalc duplicado
		cellManager.recalcAll();

		GWT.log(cellManager.asGraphViz());

	}

	private void assertNotNull(Object objeto, String msg) {
		if (objeto == null) {
			throw new IllegalArgumentException(msg);
		}
	}

	public void assertEquals(int a, int b, String msg) {
		if (a != b) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * En este assert null != null
	 * 
	 * @param a
	 * @param b
	 * @param msg
	 */
	public void assertEquals(Object a, Object b, String msg) {
		if (a == null || b == null) {
			throw new IllegalArgumentException(msg + ". Un valor es null");
		}
		if (!a.equals(b)) {
			throw new IllegalArgumentException(msg + ". [" + a + "!=" + b + "]");
		}
	}

	/**
	 * Se asume que vienen en pares
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map argsToMap(Object... args) {
		Map mapa = new HashMap();

		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("Numero de argumentos debe ser par.");
		}
		int total = args.length;
		for (int i = 0; i < total; i += 2) {
			mapa.put(args[i], args[i + 1]);
		}
		return mapa;
	}

	public static void mostrarBarraProgreso() {
		RootPanel.get(BARRA_PROGRESO).setVisible(true);
	}

	public static void ocultarBarraProgreso() {
		RootPanel.get(BARRA_PROGRESO).setVisible(false);
	}

	private void eventoClickGrabar() {
		if (movimientosAjusteGenerados == false) {
			Window.alert("Antes de grabar, primero debe Generar los movimiento de Ajuste");
			return;
		}

		if (estaEnTransaccion) {
			Window.alert(CONSTANTES.mensajeParteTecnicoTransaccionenProceso());
			return;
		}
		estaEnTransaccion = true;
		habilitarDeshabilitarGrabar(estaEnTransaccion);
		mostrarBarraProgreso();

		try {
			Long codigoLineaNegocio = Long.parseLong(lineaNegocioItem.getValue().toString());
			Integer anioContable = Integer.parseInt(aniosItem.getValue().toString());

			String mesContable = mesesItem.getValue().toString();
			Long codigoProducto = Long.parseLong(productoItem.getValue().toString());
			Long codigoPlantillaGrupoAjuste = Long.parseLong(grupoAjusteItem.getValue().toString());

			String observaciones = txtObservaciones.getText();
			// Validar esta info
			Double saldoInicialLibroBalance = gridTablaBalance.getSaldoInicialLibroBalance();
			Double produccionLibroBalance = gridTablaBalance.getProduccionLibroBalance();
			Double saldoFinalLibroBalance = gridTablaBalance.getSaldoFinalLibroBalance();
			Double consumoLibroBalance = gridTablaBalance.getConsumoLibroBalance();
			Double consumoAjusteBalance = gridTablaBalance.getConsumoAjusteBalance();
			Double produccionAjusteBalance = gridTablaBalance.getProduccionAjusteBalance();
			setValorAjuste(produccionAjusteBalance);

			if (ejecutarMovBunker) {
				List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponentePuestoTrabajoAjuste = gridAjusteCombustible
						.exportarGridAjusteCombustible();
				List<RegistroTablaAjusteDTO> detallesMovimientoAjuste = gridMovimientosDeAjusteCombustible
						.exportarGridMovimientosAjuste();

				Object fechaFacturaObj = txtDiaFactura.getValue();
				if (fechaFacturaObj == null) {
					Window.alert("Debe ingresar fecha factura");
					return;
				}

				Object cantidadFacturaobj = txtFactura.getValue();

				if (cantidadFacturaobj == null) {
					Window.alert("Debe ingresar cantidad factura Primax");
					return;
				}

				Object consumoRestanteobj = txtConsumoRestante.getValue();
				if (consumoRestanteobj == null) {
					Window.alert("Debe ingresar cantidad consumo");
					return;
				}

				Object mermaobj = txtMerma.getValue();
				if (mermaobj == null) {
					Window.alert("Debe ingresar cantidad merma");
					return;
				}

				String fechaFactura = fechaFacturaObj.toString();
				String cantidadFactura = cantidadFacturaobj.toString();
				Double mermaIngresada = Double.valueOf(mermaobj.toString());
				String cantidadRestante = consumoRestanteobj.toString();

				AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
					public void onFailure(Throwable throwable) {
						ocultarBarraProgreso();
						if (throwable instanceof ServicioGWTGlobalException) {
							ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
							e.printStackTrace();
							Window.alert(e.getMensaje());
						} else {
							Window.alert(CONSTANTES.falloRegistroAjusteProduccion());
						}
						estaEnTransaccion = false;
						habilitarDeshabilitarGrabar(estaEnTransaccion);
					}

					public void onSuccess(Boolean estaInsertado) {
						ocultarBarraProgreso();
						if (!estaInsertado) {
							Window.alert(CONSTANTES.errorRegistroAjusteProduccion());
							return;
						}
						Window.alert(CONSTANTES.exitoRegistroAjusteProduccion());
						setEtiquetaEstado(true);
						estaEnTransaccion = false;
						habilitarDeshabilitarGrabar(estaEnTransaccion);
					}
				};

				servicioParteTecnico.registrarParteTecnicoCombustible(mermaIngresada, ajuste, codigoLineaNegocio, anioContable,
						mesContable, codigoPlantillaGrupoAjuste, codigoProducto, saldoInicialLibroBalance,
						produccionLibroBalance, saldoFinalLibroBalance, consumoLibroBalance, consumoAjusteBalance,
						produccionAjusteBalance, detallesConsumoComponentePuestoTrabajoAjuste, detallesMovimientoAjuste,
						fechaFactura, cantidadFactura, cantidadRestante, observaciones, callback);

				return;
			}

			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo = gridPuestoTrabajoProduccion
					.exportarGridPuestotrabajoProduccion();

			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste = gridMovimientosDeAjuste.exportarGridMovimientosAjuste();
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponentePuestoTrabajoAjuste = gridConsumoPuestoTrabajo
					.exportarGridConsumoComponentePuestoTrab();

			final NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

			servicioParteTecnico.obtenerStockFisico(codigoProducto, codigoLineaNegocio, mesContable, anioContable,
					new AsyncCallback<Double>() {
						public void onFailure(Throwable throwable) {
							if (throwable instanceof ServicioGWTGlobalException) {
								ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
								Window.alert(e.getMensaje());
							} else {
								Window.alert(ConstantesGWT.SERVER_ERROR);
							}
							estaEnTransaccion = false;
							habilitarDeshabilitarGrabar(estaEnTransaccion);
						}

						public void onSuccess(Double stockFisico) {
							double stockFinalAjuste = gridTablaBalance.getProduccionAjusteBalance()
									- gridTablaBalance.getConsumoAjusteBalance();
							double stockFinalBalance = gridTablaBalance.getSaldoFinalLibroBalance();
							double fL = stockFisico.doubleValue() - stockFinalAjuste - stockFinalBalance;
							double fLIni = stockFisico.doubleValue() - stockFinalBalance;
							double fisico = stockFisico.doubleValue();
							String fLStr = nf.format(fL);
							String fLIniStr = nf.format(fLIni);
							String fisicoStr = nf.format(fisico);
							etiquetaFL.setContents("F-L Ini: " + fLIniStr);
							etiquetaValorAjuste.setContents("F-L Aju: " + fLStr);
							etiquetaF.setContents("Fisico: " + fisicoStr);
						}
					});

			// Se verifican horas de ajuste desviadas
			servicioParteTecnico.obtenerDesviacionHoras(codigoProducto, detallesProduccionPuestoTrabajo, codigoLineaNegocio,
					mesContable, anioContable, new AsyncCallback<Map<String, Double>>() {
						public void onFailure(Throwable throwable) {
							ocultarBarraProgreso();
							if (throwable instanceof ServicioGWTGlobalException) {
								ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
								Window.alert(e.getMensaje());
							} else {
								Window.alert(CONSTANTES.errorComunicacionServidor());
							}
							estaEnTransaccion = false;
							habilitarDeshabilitarGrabar(estaEnTransaccion);
						}

						public void onSuccess(Map<String, Double> desviacionesHoras) {
							if (desviacionesHoras != null && desviacionesHoras.size() > 0) {
								Set<Entry<String, Double>> entrySet = desviacionesHoras.entrySet();
								StringBuilder smsDesv = new StringBuilder();
								for (Entry<String, Double> entry : entrySet) {
									Double desvHorasajustadas = entry.getValue();
									smsDesv.append("El puesto: " + entry.getKey() + " posee una desviacion de "
											+ desvHorasajustadas + " horas ajustadas \n");
								}
								Window.alert(smsDesv.toString());
							}
						}

					});

			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
				public void onFailure(Throwable throwable) {
					ocultarBarraProgreso();
					if (throwable instanceof ServicioGWTGlobalException) {
						ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
						e.printStackTrace();
						Window.alert(e.getMensaje());
					} else {
						Window.alert(CONSTANTES.falloRegistroAjusteProduccion());
					}
					estaEnTransaccion = false;
					habilitarDeshabilitarGrabar(estaEnTransaccion);
				}

				public void onSuccess(Boolean estaInsertado) {
					ocultarBarraProgreso();
					if (!estaInsertado) {
						Window.alert(CONSTANTES.errorRegistroAjusteProduccion());
						return;
					}
					Window.alert(CONSTANTES.exitoRegistroAjusteProduccion());
					setEtiquetaEstado(true);
					estaEnTransaccion = false;
					habilitarDeshabilitarGrabar(estaEnTransaccion);
				}
			};
			servicioParteTecnico.registrarParteTecnico(ajuste, codigoLineaNegocio, anioContable, mesContable,
					codigoPlantillaGrupoAjuste, codigoProducto, saldoInicialLibroBalance, produccionLibroBalance,
					saldoFinalLibroBalance, consumoLibroBalance, consumoAjusteBalance, produccionAjusteBalance,
					detallesProduccionPuestoTrabajo, detallesConsumoComponentePuestoTrabajoAjuste, detallesMovimientoAjuste,
					observaciones, callback);
		} catch (Exception e) {
			GWT.log("Error interno (" + e.getMessage());
			e.printStackTrace();
		}
	}

	private void eventoClickRefrescar() {
		Window.Location.assign(ConstantesGWT.LINK_CREAR_AJUSTE);
	}

	private void eventoClickNuevo() {
		if (!this.gridMovimientosDeAjuste.isMovimientosGenerados()) {
			Window.alert(CONSTANTES.validacionMovimientosManuales());
		} else {
			LinkedHashMap<String, String> componentes = gridMovimientoComponentes.obtenerComponentes();
			LinkedHashMap<String, String> puestosTrabajo = gridPuestoTrabajoProduccion.obtenerPuestosTrabajo();
			dialogoCrearMovManual = new DialogoCrearMovManual(gridMovimientosDeAjuste, gridMovimientoComponentes, componentes,
					puestosTrabajo, gridConsumoPuestoTrabajo, gridConsumoComponentes, esClinker);
			dialogoCrearMovManual.show();
		}
	}

	/**
	 * Método para generar el panel del lado norte - combos de selección
	 * 
	 * @param text
	 * @return
	 */
	private VerticalPanel initFormularioFiltros() {

		VerticalPanel panelVerticalNorte = new VerticalPanel();
		panelVerticalNorte.setSpacing(5);

		VLayout disenoGrid = new VLayout();

		formularioCombos = new DynamicForm();
		formularioCombos = initFormularioCombos();

		cargarComboLineaNegocio();

		disenoGrid.addMember(formularioCombos);
		formularioCombos.draw();

		panelVerticalNorte.add(disenoGrid);
		disenoGrid.draw();

		return (panelVerticalNorte);
	}

	/**
	 * Construye la GUI del formulario obeservacion (parte donde esta el F- L,
	 * las observaciones y boton de cubicaciones)
	 * 
	 * @return HLayout
	 */
	public HLayout initFormularioObservaciones() {

		HLayout layoutObservaciones = new HLayout();
		layoutObservaciones.setMembersMargin(15);

		VLayout layoutFLs = new VLayout();
		layoutFLs.setWidth(100);
		etiquetaFL = new Label("F-L Ini: ");
		etiquetaFL.setHeight(2);
		layoutFLs.addMember(etiquetaFL);
		etiquetaValorAjuste = new Label("F-L Aju: ");
		etiquetaValorAjuste.setHeight(2);
		layoutFLs.addMember(etiquetaValorAjuste);
		etiquetaF = new Label("Fisico: ");
		layoutFLs.addMember(etiquetaF);
		layoutObservaciones.addMember(layoutFLs);

		HLayout textAreaLayout = new HLayout();
		textAreaLayout.setMembersMargin(2);

		Label etiquetaObs = new Label("Observaciones:");
		etiquetaObs.setWidth(60);
		textAreaLayout.addMember(etiquetaObs);

		txtObservaciones = new TextArea();
		txtObservaciones.setWidth("350");
		txtObservaciones.setHeight("50");
		textAreaLayout.addMember(txtObservaciones);

		layoutObservaciones.addMember(textAreaLayout);

		HLayout cubicajeLayout = new HLayout();
		cubicajeLayout.setMembersMargin(3);

		Label etiquetaCubicaje = new Label("Cubicaje:");
		etiquetaCubicaje.setWidth(60);
		cubicajeLayout.addMember(etiquetaCubicaje);
		// Grabar
		descargarCubicacion = new Img("", 16, 20);
		descargarCubicacion.setName(ConstantesGWT.NOMBRE_IMAGEN_DESCARGAR);
		descargarCubicacion.setCursor(Cursor.HAND);
		descargarCubicacion.setStyleName(ConstantesGWT.NOMBRE_ESTILO_DESCARGAR);
		cubicajeLayout.addMember(descargarCubicacion);

		//layoutObservaciones.addMember(cubicajeLayout);

		HLayout formLayout = new HLayout();
		final DynamicForm uploadForm = new DynamicForm();
		uploadForm.setWidth(10);

		String context = obtenerContexto();
		String action = context + "downloadServlet";
		uploadForm.setAction(action);

		HiddenItem anioHidden = new HiddenItem("anio");
		HiddenItem mesHidden = new HiddenItem("mes");
		HiddenItem lineaHidden = new HiddenItem("linea");
		uploadForm.setItems(anioHidden, mesHidden, lineaHidden);

		descargarCubicacion.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				descargarcubicacionClicked(uploadForm);
			}
		});

		formLayout.addMember(uploadForm);
		layoutObservaciones.addMember(formLayout);

		return layoutObservaciones;
	}

	/**
	 * Obtiene el contexto de la aplicacion(EJ en tomcat:
	 * http://127.0.0.1:8080/SGCP/)
	 * 
	 * @return String
	 */
	private String obtenerContexto() {
		String[] split = GWT.getHostPageBaseURL().split("/");
		String context = "/";
		for (int i = 0; i < split.length; i++) {
			if (split[i].equals("parteTecnico")) {
				if (split[i - 1].indexOf(":") < 0) {
					context = "/" + split[i - 1] + "/";
				}
			}
		}
		return context;
	}

	/**
	 * Funcion que se ejecuta a hacer clink en el boton de descargar
	 * cubicaciones
	 * 
	 * @param uploadForm
	 */
	private void descargarcubicacionClicked(final DynamicForm uploadForm) {

		final Object anio = aniosItem.getValue();
		final Object mes = mesesItem.getValue();
		final Object linea = lineaNegocioItem.getValue();

		if (anio == null) {
			Window.alert("Debe seleccionar  una valor para el campo: A\u00f1o");
			return;
		}

		if (mes == null) {
			Window.alert("Debe seleccionar  una valor para el campo: Mes");
			return;
		}

		if (linea == null) {
			Window.alert("Debe seleccionar  una valor para el campo: Linea de Negocio");
			return;
		}

		servicioParteTecnico.validarSiexistencubicaciones(linea.toString(), anio.toString(), mes.toString(),
				new AsyncCallback<Boolean>() {

					public void onFailure(Throwable throwable) {
						if (throwable instanceof ServicioGWTGlobalException) {
							ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
							Window.alert(e.getMensaje());
						} else {
							Window.alert(CONSTANTES.falloRegistroAjusteProduccion());
						}

					}

					public void onSuccess(Boolean exitenCubicaciones) {

						if (exitenCubicaciones) {
							validarParteTecnicoCreado(uploadForm, anio.toString(), mes.toString(), linea.toString());

						} else {
							Window.alert("No  existen cubicaciones o no se encuentran aprobadas");
						}

					}
				});
	}

	protected void validarParteTecnicoCreado(final DynamicForm uploadForm, final String anio, final String mes, final String linea) {
		servicioParteTecnico.validarSiExisteAjustes(linea.toString(), anio.toString(), mes.toString(),
				new AsyncCallback<Boolean>() {

					public void onFailure(Throwable throwable) {

						if (throwable instanceof ServicioGWTGlobalException) {
							ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
							Window.alert(e.getMensaje());
						} else {
							Window.alert(CONSTANTES.falloRegistroAjusteProduccion());
						}
					}

					public void onSuccess(Boolean existeRegistro) {
						if (existeRegistro) {
							uploadForm.setValue("anio", anio);
							uploadForm.setValue("mes", mes);
							uploadForm.setValue("linea", linea);
							uploadForm.submitForm();
						} else {
							Window.alert("No  existen Ajustes Productos Registrados");
						}

					}

				});

	}

	/**
	 * Metodo que carga los combos del formulario del registro de ajuste de
	 * produccion
	 */
	private DynamicForm initFormularioCombos() {

		formularioCombos.setNumCols(6);
		formularioCombos.setWidth(700);
		formularioCombos.setColWidths(25, 25, 25, 25, 25, 25);
		formularioCombos.setAlign(Alignment.LEFT);

		lineaNegocioItem = Combos.cargarComboLineasNegocio();

		mesesItem = Combos.cargarComboMeses();
		aniosItem = Combos.cargarComboAnnos();

		grupoAjusteItem = Combos.cargarComboGrupoAjuste();
		productoItem = Combos.cargarComboProducto();
		productoItem.setDisabled(true);

		etiquetaEstado = new StaticTextItem("labelEstado", "Estado");
		etiquetaEstado.setValue("Generado");
		etiquetaEstado.setWidth(60);

		formularioCombos.setItems(lineaNegocioItem, mesesItem, aniosItem, grupoAjusteItem, productoItem, etiquetaEstado);

		agregarEventosFormularioCombos();

		return formularioCombos;
	}

	/**
	 * Metodo para agregar los eventos de actualizacion de los combos del
	 * registro de produccion semanal: division, sociedad,unidad,linea de
	 * negocio, proceso
	 * 
	 * @param formularioDiaProduccion
	 * @param divisionItem
	 * @param sociedadItem
	 * @param unidadItem
	 * @param lineaNegocioItem
	 * @param procesoItem
	 * @param fechaInicio
	 * @param fechaFin
	 */
	private void agregarEventosFormularioCombos() {

		lineaNegocioItem = (SelectItem) formularioCombos.getField(Combos.COMBO_LINEA_NEGOCIO);
		grupoAjusteItem = (SelectItem) formularioCombos.getField(Combos.COMBO_GRUPO_AJUSTE);

		addLineaNegocioItemChangeHandler(lineaNegocioItem, grupoAjusteItem);
		addGrupoAjusteItemChangeHandler(grupoAjusteItem, productoItem);

		addProductoItemChangeHandler(productoItem);

	}

	/**
	 * Metodo para capturar los valores de los filtros cuando el usuario cambie
	 * el combo de producto y cargar los datos en la pantalla
	 * 
	 * @param productoItem
	 */
	private void addProductoItemChangeHandler(SelectItem productoItem) {

		productoItem.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				final String valorProducto = (String) event.getValue();

				// Validar Si Pd se encuentra Cerrado
				manejarCambioProducto(valorProducto);

			}

		});
	}

	private void manejarCambioProducto(String valorProducto) {
		if (valorProducto != null) {
			mostrarBarraProgreso();
			if (mesesItem.getValue() == null || aniosItem.getValue() == null) {
				Window.alert(CONSTANTES.requisitoAnioMesContable());
				return;
			}
			final Long codigoProducto = Long.parseLong(valorProducto);
			ProductoDTO prodDTO = mapaProductos.get(codigoProducto);
			gridMovimientosDeAjuste.limpiarGrid();
			this.txtObservaciones.setText("");
			final Long codigoLinea = Long.parseLong(lineaNegocioItem.getValue().toString());
			final String mesContable = mesesItem.getValue().toString();
			final Integer anioContable = Integer.parseInt(aniosItem.getValue().toString());
			// TODO: cambiar esto por el grupo producto
			setProductoClinker(codigoProducto);
			setProductoClinkerOCalGranulada(codigoProducto);

			if (prodDTO.getNombreProducto().equals(mapaPropiedades.get(ConstantesGWT.NOMBRE_BUNKER_6))) {
				RootPanel.get(DIV_GRIDS_AJUSTES).setStyleName(ConstantesGWT.CSS_OCULTAR_GRID);
				RootPanel.get(DIV_GRIDS_AJUSTES_COMBUSTIBLE).setStyleName(ConstantesGWT.CSS_MOSTRAR_GRID);
				ejecutarMovBunker = Boolean.TRUE;
				cargarTablaAjusteCombuslibleLiquido(codigoProducto, codigoLinea, mesContable, anioContable);

				return;
			} else {
				RootPanel.get(DIV_GRIDS_AJUSTES).setStyleName(ConstantesGWT.CSS_MOSTRAR_GRID);
				RootPanel.get(DIV_GRIDS_AJUSTES_COMBUSTIBLE).setStyleName(ConstantesGWT.CSS_OCULTAR_GRID);
			}

			ejecutarMovBunker = Boolean.FALSE;

			gridPuestoTrabajoProduccion.configurarGridSegunGrupoProducto(prodDTO.getGrupoProducto());
			gridPuestoTrabajoProduccion.configurarGridSegunGrupoClkCal(prodDTO.getNombreProducto());
			tabSetConmponentesAjuste.setWidth(gridPuestoTrabajoProduccion.getWidth() + MARGEN);

			servicioParteTecnico.verificarSiExisteAjusteBd(codigoProducto, codigoLinea, mesContable, anioContable,
					new AsyncCallback<AjusteProductoDTO>() {

						public void onFailure(Throwable throwable) {
							ocultarBarraProgreso();
							if (throwable instanceof ServicioGWTGlobalException) {
								ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
								Window.alert(e.getMensaje());
							} else {
								Window.alert(CONSTANTES.errorComunicacionServidor());
							}
						}

						public void onSuccess(AjusteProductoDTO ajusteProductoDTO) {
							try {
								ajusteProductoDTOultimo = ajusteProductoDTO;

								boolean ajustado = ajusteProductoDTO != null && ajusteProductoDTO.getEstado().equals("Aprobado");

								cargarValorAjuste(ajusteProductoDTO, codigoProducto, codigoLinea, mesContable, anioContable);

								habilitarODesHabilitarControles(ajustado);

								if (ajusteProductoDTO == null) {
									setEtiquetaEstadoIncial(codigoLinea, mesContable, anioContable);
								} else {

									setEtiquetaEstado(ajusteProductoDTO.getEstado());
								}

								cargarTablaPuestoTrabajoProduccion(ajusteProductoDTO, codigoProducto, codigoLinea, mesContable,
										anioContable);
							} catch (Exception e) {
								e.printStackTrace();

								Window.alert("Error interno:3 " + e.getMessage());
							}
						}

					});
		}
	}

	private void setProductoClinker(Long codigoProducto) {
		// TODO: eliminar por completo todo esto de esClinker,
		// esClinkerocalocalizaopiedras,
		// sustituir por uso de grupoProducto

		String[] codigosClk = mapaPropiedades.get(ConstantesGWT.PARTE_DIARIO_CODIGOS_CLINKER).split("\\-");
		Set<String> setCodigosClk = new HashSet<String>(Arrays.asList(codigosClk));
		esClinker = setCodigosClk.contains(codigoProducto.toString());
	}

	private void setProductoClinkerOCalGranulada(Long codigoProducto) {
		// TODO: eliminar por completo todo esto de esClinker,
		// esClinkerocalocalizaopiedras,
		// sustituir por uso de grupoProducto
		String codigosClkCalGranulada = mapaPropiedades.get(ConstantesGWT.CODIGO_PRODUCTOS_CLINKER_O_CALGRANULADA);
		esClinkerCalGranulada = codigosClkCalGranulada.indexOf(codigoProducto.toString()) >= 0;
	}

	private void setEtiquetaEstado(boolean ajustado) {
		if (ajustado) {
			etiquetaEstado.setValue("Ajustado");
		} else {
			etiquetaEstado.setValue("Generado");
		}

	}

	private void setEtiquetaEstado(String ajustado) {

		etiquetaEstado.setValue(ajustado);

	}

	private void setEtiquetaEstadoIncial(Long codigoLinea, String mesContable, Integer anioContable) {
		// 296_47637

		servicioParteTecnico.validarParteDiarioCerrado(codigoLinea, mesContable, anioContable, new AsyncCallback<Boolean>() {

			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.errorComunicacionServidor());
				}

			}

			public void onSuccess(Boolean arg0) {
				if (!arg0) {
					etiquetaEstado.setValue("Inicial");
					habilitarODesHabilitarControles(false);
				}

			}

		});
	}

	private void habilitarDeshabilitarGrabar(boolean disabled) {
		imagenGrabar.setDisabled(disabled);
		etiquetaGrabar.setDisabled(disabled);

	}

	/**
	 * Habilita o deshabilita los controles de la GUI
	 * 
	 * @param disable boolean
	 */
	private void habilitarODesHabilitarControles(boolean disabled) {
		txtObservaciones.setEnabled(!disabled);

		gridConsumoComponentes.setDisabled(disabled);

		gridConsumoPuestoTrabajo.setDisabled(disabled);
		gridMovimientoComponentes.setDisabled(disabled);
		gridMovimientosDeAjuste.setDisabled(disabled);
		// gridPuestoTrabajoProduccion.setDisabled(disabled);

		gridPuestoTrabajoProduccion.setCanEdit(!disabled);
		gridTablaBalance.setDisabled(disabled);

		imagenNuevo.setDisabled(disabled);
		imagenGenerar.setDisabled(disabled);
		imagenGrabar.setDisabled(disabled);

		etiquetaNuevo.setDisabled(disabled);
		etiquetaGenerar.setDisabled(disabled);
		etiquetaGrabar.setDisabled(disabled);
	}

	/**
	 * Metodo para imprimir el valor del ajuste en la pantalla
	 * 
	 * @param ajusteProductoDTO
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarValorAjuste(AjusteProductoDTO ajusteProductoDTO, final Long codigoProducto, final Long codigoLinea,
			final String mesContable, final Integer anioContable) {

		final NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

		AsyncCallback<Double> callback = new AsyncCallback<Double>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				if (throwable instanceof ServicioGWTGlobalException) {
					Window.alert(throwable.getMessage());
				} else {
					Window.alert(CONSTANTES.valorAjusteNoCalculado());
				}
			}

			public void onSuccess(Double ajuste) {
				String ajusteStr = nf.format(ajuste);
				setValorAjuste(nf.parse(ajusteStr));
				cargarTablaBalance(ajuste, codigoProducto, codigoLinea, mesContable, anioContable);
			}
		};

		if (ajusteProductoDTO != null) {
			String ajusteStr = nf.format(ajusteProductoDTO.getAjuste());
			this.ajuste = nf.parse(ajusteStr);
			this.txtObservaciones.setText(ajusteProductoDTO.getObservacion());
			cargarTablaBalance(ajuste, codigoProducto, codigoLinea, mesContable, anioContable);
		} else {
			// revisa este metodo por que tiene cubicaciones
			servicioParteTecnico.obtenerAjuste(codigoLinea, codigoProducto, mesContable, anioContable, callback);
		}

	}

	private void setValorAjuste(Double ajuste) {
		this.ajuste = ajuste;
	}

	/**
	 * Metodo para cargar la tabla de PT de produccion
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarTablaPuestoTrabajoProduccion(final AjusteProductoDTO ajusteProductoDTO, final Long codigoProducto,
			final Long codigoLinea, final String mesContable, final Integer anioContable) {
		gridPuestoTrabajoProduccion.limpiarGrid();

		lblFactorAntracita.setVisible(esClinker);

		AsyncCallback<List<RegistroPuestoTrabajoProduccionDTO>> callBack = new AsyncCallback<List<RegistroPuestoTrabajoProduccionDTO>>() {
			public void onFailure(Throwable throwable) {
				Exception exception = (Exception) throwable;
				exception.printStackTrace();
				ocultarBarraProgreso();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {

					Window.alert(CONSTANTES.faltanDatosMesActualTablaPuestoTrabajo());
				}
			}

			public void onSuccess(List<RegistroPuestoTrabajoProduccionDTO> registrosDTO) {
				if (registrosDTO.size() > 0) {
					// solo el ultimo elemento (horno vertical), ya que cuando
					// es clinker todos
					// tienen el mismo factor antracita
					// RegistroPuestoTrabajoProduccionDTO registro =
					// registrosDTO.get(registrosDTO.size() - 1);
					double sumaFactores = 0;
					int cantPuestos = 0;
					for (RegistroPuestoTrabajoProduccionDTO registro : registrosDTO) {
						if (registro.getFactAntrac() != null) {
							double factorAntrc = registro.getFactAntrac().doubleValue();
							if (factorAntrc != 0) {
								sumaFactores += factorAntrc;
								cantPuestos++;
							}
						}
					}

					double factorAntrcProm = 0d;
					if (cantPuestos != 0) {
						factorAntrcProm = sumaFactores / cantPuestos;
					}

					NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
					lblFactorAntracita.setContents(CONSTANTES.porcentajeAntracitaEnCrudo() + " " + nf.format(factorAntrcProm));
				}

				gridPuestoTrabajoProduccion.cargaGridPuestoTrabajoProduccionMesActual(registrosDTO);
				cargarTablaConsumoComponentesPuestoTrabajo(ajusteProductoDTO, codigoProducto, codigoLinea, mesContable,
						anioContable);
			}
		};

		if (ajusteProductoDTO != null) {
			servicioParteTecnico.obtenerProduccionPuestoTrabajoMesBD(ajusteProductoDTO.getCodigo(), callBack);
		} else {
			servicioParteTecnico.obtenerProduccionPuestoTrabajoMes(codigoProducto, codigoLinea, mesContable, anioContable,
					callBack);
		}
	}

	/**
	 * Metodo para cargar la tabla de consumo de componentes
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarTablaConsumoComponentesPuestoTrabajo(final AjusteProductoDTO ajusteProductoDTO, final Long codigoProducto,
			final Long codigoLinea, final String mesContable, final Integer anioContable) {

		gridConsumoPuestoTrabajo.mostrarUOcultarColumnas(esClinker);
		gridConsumoPuestoTrabajo.mostrarUOcultarColumnaPorcentajeConsumo(esClinkerCalGranulada);
		gridConsumoComponentes.mostrarUOcultarColumnaPorcentajeConsumo(esClinkerCalGranulada);

		AsyncCallback<Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>> callback = new AsyncCallback<Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>>() {

			public void onFailure(Throwable throwable) {

				ocultarBarraProgreso();
				throwable.printStackTrace();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.faltanDatosConsumoComponentes());
				}
			}

			public void onSuccess(Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> registroTablaConsumoPuestoTrabajoDTO) {
				boolean existeEnBD = ajusteProductoDTO != null;
				gridConsumoPuestoTrabajo.limpiarGrid();
				String nombreBunker = mapaPropiedades.get(ConstantesGWT.NOMBRE_BUNKER_6);

				String codigosClk = mapaPropiedades.get(ConstantesGWT.PARTE_DIARIO_CODIGOS_CLINKER);
				final boolean esClinker = codigosClk.indexOf(codigoProducto.toString()) >= 0;

				gridConsumoPuestoTrabajo.mostarConsumoComponentesPuestoTrabajo(registroTablaConsumoPuestoTrabajoDTO, existeEnBD,
						nombreBunker, esClinker);
				if (existeEnBD) {
					cargarGridMovimientoAjustesBD(ajusteProductoDTO, codigoProducto, codigoLinea, mesContable, anioContable);
				} else {
					cargarTablaMovimientoComponentes(ajusteProductoDTO, codigoProducto, codigoLinea, mesContable, anioContable);
				}
			}
		};
		if (ajusteProductoDTO != null) {
			servicioParteTecnico.obtenerConsumoComponentesPuestoTrabajoBD(ajusteProductoDTO.getCodigo(), callback);
		} else {
			servicioParteTecnico.obtenerConsumoComponentesPuestoTrabajo(codigoProducto, codigoLinea, mesContable, anioContable,
					callback);
		}
	}

	private void cargarGridMovimientoAjustesBD(final AjusteProductoDTO ajusteProductoDTO, final Long codigoProducto,
			final Long codigoLinea, final String mesContable, final Integer anioContable) {
		AsyncCallback<List<RegistroTablaAjusteDTO>> callback = new AsyncCallback<List<RegistroTablaAjusteDTO>>() {

			public void onFailure(Throwable throwable) {
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.errorComunicacionServidor());
				}
			}

			public void onSuccess(List<RegistroTablaAjusteDTO> registrosTablaAjusteDTO) {
				gridMovimientosDeAjuste.limpiarGrid();
				gridMovimientosDeAjuste.cargaGridMovimientosAjuste(registrosTablaAjusteDTO);
				cargarTablaMovimientoComponentes(ajusteProductoDTO, codigoProducto, codigoLinea, mesContable, anioContable);
			}
		};

		servicioParteTecnico.obtenerDatosGridMovimientoAjustesBD(ajusteProductoDTO.getCodigo(), callback);
	}

	/**
	 * Metodo para cargar la tabla de consumo de componentes
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	public void cargarTablaMovimientoComponentes(final AjusteProductoDTO ajusteProductoDTO, Long codigoProducto,
			Long codigoLinea, String mesContable, Integer anioContable) {

		AsyncCallback<List<RegistroTablaConsumoComponentesDTO>> callback = new AsyncCallback<List<RegistroTablaConsumoComponentesDTO>>() {

			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				throwable.printStackTrace();
				if (throwable instanceof ServicioGWTGlobalException) {
					GWT.log("error en cargarTablaMovimientoComponentes", throwable);
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					GWT.log("error en cargarTablaMovimientoComponentes", throwable);
					Window.alert(CONSTANTES.faltanDatosConsumoComponentes());
				}
			}

			public void onSuccess(List<RegistroTablaConsumoComponentesDTO> consumoComponentesDTOs) {
				try {
					gridMovimientoComponentes.limpiarGrid();

					gridMovimientoComponentes.cargaGridConsumoComponentes(consumoComponentesDTOs);
					if (ajusteProductoDTO == null) {
						sugerirAjusteTm();
					} else {
						Double totalTmAjuste = gridPuestoTrabajoProduccion.getTotalTmAjuste();
						// TODO: borrar esto
						// gridTablaBalance.getRecord(1).setAttribute(CONSTANTES.tituloColIngreso(),
						// 0);
						// gridTablaBalance.setProduccionAjusteBalance(totalTmAjuste);
						// gridTablaBalance.getRecord(1).setAttribute(CONSTANTES.tituloStockFinal(),
						// 0);
						// gridTablaBalance.refreshFields();
					}
					gridMovimientosDeAjuste.completarInsert();
					generarGrafo();
				} catch (Exception e) {
					GWT.log("error en cargarTablaMovimientoComponentes", e);
					Window.alert("Error interno:2 " + e.getMessage());
				}
				ocultarBarraProgreso();
			}
		};
		// TODO: Tengo la impresión de que obtenerConceptosComponentesProducto y
		// obtenerDatosGridConsumoComponentesBD
		// son al final un gran copy y paste uno del otro, y todo se pudo
		// mezclar en
		// un solo metodo con HQL que dice "left join ajustes". Revisar algun
		// dia. Mientras sean dos metodos que aparentemente son diferentes pero
		// al final son iguales,
		// seguirán encontrandose bugs "misteriosos" de funcionalidad que servía
		// pero de
		// repente no sirve. En las pruebas siempre se tomará frecuentemente uno
		// de los dos caminos
		// sin saber que hay otro, y en el otro estará el bug escondido.
		Set<String> otrosProductosAjuste = gridMovimientosDeAjuste.getCodProductoCompDeMovimientoAjusteProducto();

		// TODO: Ordenar este desorden de llamadas entre grids. Esta hace que se
		// borren
		// las diferencias que el usuario tipeo, cuando se llama
		// indirectamente desde dialogocrear.
		if (ajusteProductoDTO == null) {

			servicioParteTecnico.obtenerConceptosComponentesProducto(codigoProducto, codigoLinea, mesContable, anioContable,
					otrosProductosAjuste, callback);
		} else {

			servicioParteTecnico.obtenerDatosGridConsumoComponentesBD(ajusteProductoDTO.getCodigo(), codigoProducto, codigoLinea,
					mesContable, anioContable, otrosProductosAjuste, callback);
		}

		final NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

		servicioParteTecnico.obtenerStockFisico(codigoProducto, codigoLinea, mesContable, anioContable,
				new AsyncCallback<Double>() {
					public void onFailure(Throwable throwable) {
						if (throwable instanceof ServicioGWTGlobalException) {
							ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
							Window.alert(e.getMensaje());
						} else {
							Window.alert(ConstantesGWT.SERVER_ERROR);
						}
					}

					public void onSuccess(Double stockFisico) {
						double stockFinalAjuste = gridTablaBalance.getProduccionAjusteBalance()
								- gridTablaBalance.getConsumoAjusteBalance();
						double stockFinalBalance = gridTablaBalance.getSaldoFinalLibroBalance();
						double fL = stockFisico.doubleValue() - stockFinalAjuste - stockFinalBalance;
						double fLIni = stockFisico.doubleValue() - stockFinalBalance;
						double fisico = stockFisico.doubleValue();
						String fLStr = nf.format(fL);
						String fLIniStr = nf.format(fLIni);
						String fisicoStr = nf.format(fisico);
						etiquetaFL.setContents("F-L Ini: " + fLIniStr);
						etiquetaValorAjuste.setContents("F-L Aju: " + fLStr);
						etiquetaF.setContents("Fisico: " + fisicoStr);
					}
				});
	}

	/**
	 * Carga la tabla segun los valores actuales en los combos
	 */
	public void cargarTablaMovimientoComponentes() {

		final Long codigoProducto = Long.parseLong(productoItem.getValue().toString());
		final Long codigoLinea = Long.parseLong(lineaNegocioItem.getValue().toString());
		final String mesContable = mesesItem.getValue().toString();
		final Integer anioContable = Integer.parseInt(aniosItem.getValue().toString());

		cargarTablaMovimientoComponentes(this.ajusteProductoDTOultimo, codigoProducto, codigoLinea, mesContable, anioContable);
		gridMovimientoComponentes.markForRedraw();
	}

	/**
	 * Sugiere las cantidades para hacer el ajuste, colocando el F-L distribuido
	 * entre los puestos de trabajo porcentualmente de acuerdo a la producción
	 */
	private void sugerirAjusteTm() {
		ListGridRecord[] records = gridPuestoTrabajoProduccion.getRecords();
		double totalTmProducidas = 0d;
		for (ListGridRecord record : records) {
			totalTmProducidas += record.getAttributeAsDouble(GridPuestoTrabajoProduccion.COLUMNA_PRODUCCION_TM);
		}

		for (ListGridRecord record : records) {
			Double produccionTmPorPuesto = record.getAttributeAsDouble(GridPuestoTrabajoProduccion.COLUMNA_PRODUCCION_TM);
			double porcentaje = (produccionTmPorPuesto * 100) / totalTmProducidas;
			double cantidadAjuste = (ajuste * porcentaje) / 100;
			record.setAttribute(GridPuestoTrabajoProduccion.COLUMNA_AJUSTE_TM, cantidadAjuste);
			// OJO: revivir esto
			// gridPuestoTrabajoProduccion.actualizarTablasSegunAjuste(record);
		}

		gridPuestoTrabajoProduccion.refreshFields();

		Double totalTmAjuste = gridPuestoTrabajoProduccion.getTotalTmAjuste();
		if (gridTablaBalance.getRecord(1) != null) {
			gridTablaBalance.getRecord(1).setAttribute(CONSTANTES.tituloColIngreso(), totalTmAjuste);
			gridTablaBalance.refreshFields();
		}

	}

	/**
	 * Metodo para cargar la tabla de balance
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarTablaBalance(final Double ajuste, Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable) {

		AsyncCallback<RegistroTablaBalanceDTO> callback = new AsyncCallback<RegistroTablaBalanceDTO>() {

			public void onFailure(Throwable throwable) {
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.faltanDatosTablaBalance());
				}
			}

			public void onSuccess(RegistroTablaBalanceDTO datosServidor) {
				gridTablaBalance.cargarGridTablaBalance(datosServidor, ajuste);
			}
		};
		servicioParteTecnico.obtenerConceptosLibro(codigoProducto, codigoLinea, mesContable, anioContable, callback);
	}

	/**
	 * Metodo ejecutado cuando el usuario selecciona una linea de negocio
	 * 
	 * @param lineaNegocioItem
	 * @param grupoAjusteItem
	 */
	private void addLineaNegocioItemChangeHandler(SelectItem lineaNegocioItem, final SelectItem grupoAjusteItem) {
		lineaNegocioItem.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				String valorLineaNegocio = (String) event.getValue();

				if (valorLineaNegocio != null) {
					cargarComboGrupoAjuste(Long.parseLong(valorLineaNegocio));
				} else {
					LinkedHashMap<String, String> valor = null;
					grupoAjusteItem.clearValue();
					grupoAjusteItem.setValueMap(valor);
					productoItem.clearValue();
					productoItem.setDisabled(true);
				}
			}
		});
	}

	/**
	 * Metodo ejecutado cuando el usuario selecciona un grupo de ajuste
	 * 
	 * @param grupoAjusteItem
	 * @param productoItem
	 */
	private void addGrupoAjusteItemChangeHandler(final SelectItem grupoAjusteItem, SelectItem productoItem) {

		grupoAjusteItem.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				String valorGrupoAjuste = (String) event.getValue();
				if (valorGrupoAjuste != null) {
					cargarComboProducto(Long.parseLong(valorGrupoAjuste));
				}
			}
		});
	}

	/**
	 * Metodo para cargar los productos segun el grupo de ajuste
	 * 
	 * @param codigoGrupoAjuste
	 */
	private void cargarComboProducto(long codigoGrupoAjuste) {

		productoItem = (SelectItem) formularioCombos.getField(Combos.COMBO_PRODUCTO);

		if (productoItem != null) {

			servicioParteTecnico.cargarProductosporCodigoGrupoAjuste(codigoGrupoAjuste, new AsyncCallback<List<ProductoDTO>>() {
				public void onFailure(Throwable throwable) {
					if (throwable instanceof ServicioGWTGlobalException) {
						ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
						Window.alert(e.getMensaje());
					} else {
						Window.alert(ConstantesGWT.SERVER_ERROR);
					}
				}

				public void onSuccess(List<ProductoDTO> productosDTO) {
					// Guarda en el combo un valueMap con pk -> nombre,
					// y en mapaProductos un Map con pk -> todo el dto
					mapaProductos = UtilConverter.obtenerMapProductos(productosDTO);
					LinkedHashMap<String, String> valueMapProductos = UtilConverter.obtenerValueMapProductos(productosDTO);
					productoItem.setValueMap(valueMapProductos);
					productoItem.enable();
				}
			});
		}

	}

	/**
	 * Método parar cargar las lineas de negocio en el combo
	 */
	private void cargarComboLineaNegocio() {
		lineaNegocioItem = (SelectItem) formularioCombos.getField(Combos.COMBO_LINEA_NEGOCIO);

		if (lineaNegocioItem != null) {
			servicioComunicacion.cargarLineaNegocioUsuario(new AsyncCallback<List<LineaNegocioDTO>>() {
				public void onFailure(Throwable arg0) {
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}

				public void onSuccess(List<LineaNegocioDTO> lineasNegocioDTO) {
					LinkedHashMap<String, String> mapaLineaNegocio = UtilConverter.obtenerMapaLineaNegocioDTO(lineasNegocioDTO);
					lineaNegocioItem.setValueMap(mapaLineaNegocio);
					lineaNegocioItem.setDisabled(false);
				}
			});
		}
	}

	/**
	 * Método para cargar los valores de los grupos de ajuste de produccion
	 */
	private void cargarComboGrupoAjuste(Long codigoLineaNegocio) {
		grupoAjusteItem = (SelectItem) formularioCombos.getField(Combos.COMBO_GRUPO_AJUSTE);

		if (grupoAjusteItem != null) {

			servicioParteTecnico.cargarGrupoAjuste(codigoLineaNegocio, new AsyncCallback<List<GrupoAjusteDTO>>() {
				public void onFailure(Throwable arg0) {
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}

				public void onSuccess(List<GrupoAjusteDTO> gruposAjusteDTO) {
					grupoAjusteItem.clearValue();
					productoItem.clearValue();
					productoItem.disable();
					LinkedHashMap<String, String> mapaGruposAjuste = UtilConverter.obtenerMapaGrupoAjusteDTO(gruposAjusteDTO);
					grupoAjusteItem.setValueMap(mapaGruposAjuste);
					grupoAjusteItem.setDisabled(false);
				}
			});
		}
	}

	private void eventoClickGenerar() {

		Object productoVal = productoItem.getValue();

		if (productoVal == null) {

			Window.alert(CONSTANTES.debeSeleccionarUnValorEnElCampoProducto());
			return;
		}
		String valorProductoStr = productoVal.toString();
		if (valorProductoStr == "") {

			Window.alert(CONSTANTES.debeSeleccionarUnValorEnElCampoProducto());
			return;
		}
		final Long codigoProducto = new Long(valorProductoStr);

		servicioComunicacion.verificarSiEsProductoTerminado(codigoProducto, new AsyncCallback<Boolean>() {

			public void onFailure(Throwable arg0) {
				Window.alert(ConstantesGWT.SERVER_ERROR);
			}

			public void onSuccess(Boolean esProductoTerminado) {

				Double totalTmAjuste = gridPuestoTrabajoProduccion.getTotalTmAjuste();
				gridTablaBalance.setProduccionAjusteBalance(totalTmAjuste);
				boolean validarAjusteProduccion = true;
				if (esProductoTerminado) {
					// Si es PT se realiza la validacion del ajuste
					validarAjusteProduccion = validarAjusteProduccion();
				}

				if (validarAjusteProduccion) {
					if (ejecutarMovBunker) {
						ArrayList<RegistroTablaAjusteDTO> registrosAjuste = gridAjusteCombustible.obtenerMovimientoDeAjuste();

						gridMovimientosDeAjusteCombustible.limpiarMovimientosAutomaticos();
						gridMovimientosDeAjusteCombustible.cargaGridMovimientosAjuste(registrosAjuste);
						movimientosAjusteGenerados = true;
						Window.alert(CONSTANTES.movimientosAjusteCreadosConExito());
						return;
					}
					Boolean validarStock = validarStockMovimientoComponente();
					if (!validarStock) {
						return;
					}
					ArrayList<RegistroTablaAjusteDTO> registrosAjuste = gridConsumoPuestoTrabajo.obtenerMovimientoDeAjuste();

					gridMovimientosDeAjuste.limpiarMovimientosAutomaticos();
					gridMovimientosDeAjuste.cargaGridMovimientosAjuste(registrosAjuste);
					Window.alert(CONSTANTES.movimientosAjusteCreadosConExito());

					movimientosAjusteGenerados = true;
				} else {
					Window.alert(CONSTANTES.violacionAjusteProduccion());
				}
			}
		});

	}

	protected Boolean validarStockMovimientoComponente() {
		Boolean validacion = true;
		String cadena = "";
		List<RegistroTablaConsumoComponentesDTO> listaComponentes = gridMovimientoComponentes.exportarGridConsumoComponentes();
		for (RegistroTablaConsumoComponentesDTO registroTablaConsumoComponentesDTO : listaComponentes) {
			if (registroTablaConsumoComponentesDTO.getFisico() != null) {

				if (registroTablaConsumoComponentesDTO.getLibros() < 0d) {
					// Window.alert("No se puede poseer Stock menor o igual a
					// 0");

					validacion = false;
					cadena += "\n -" + registroTablaConsumoComponentesDTO.getNombreComponente();
				}
			}
		}
		if (!validacion) {
			Window.alert("Revisar stock de los componentes inferiores a cero " + cadena);
		}
		return validacion;
	}

	/**
	 * Metodo para regresar a la pagina de consulta
	 */
	private void eventoClickRegresar() {
		Window.Location.assign(ConstantesGWT.LINK_CONSULTAR_AJUSTE);
	}

	private boolean validarAjusteProduccion() {
		Double ajusteProduccion = gridPuestoTrabajoProduccion.getTotalTmAjuste();

		GWT.log("ajusteProduccion: " + ajusteProduccion);
		GWT.log("ajuste: " + ajuste);

		return ajusteProduccion.doubleValue() == this.ajuste.doubleValue();
	}

	public GridMovimientosDeAjuste getGridMovimientosDeAjuste() {

		return gridMovimientosDeAjuste;
	}

	public GridPuestoTrabajoProduccion getGridPuestoTrabajoProduccion() {
		return gridPuestoTrabajoProduccion;
	}

	public GridMovimientoComponentes getGridMovimientoComponentes() {
		return gridMovimientoComponentes;
	}

	public GridConsumoComponentesPuestoTrabajo getGridConsumoPuestoTrabajo() {
		return gridConsumoPuestoTrabajo;
	}

	public void refrescarGrids() {
		gridPuestoTrabajoProduccion.markForRedraw();
		gridConsumoPuestoTrabajo.markForRedraw();
		gridConsumoComponentes.markForRedraw();
		gridMovimientoComponentes.markForRedraw();

		gridAjusteCombustible.markForRedraw();
	}

	public AjusteProductoDTO getAjusteProductoDTOultimo() {
		return ajusteProductoDTOultimo;
	}

	public DialogoCrearMovManual getDialogoCrearMovManual() {
		return dialogoCrearMovManual;
	}

	// Generar Grafo Combustibles
	private void generarGrafoCombustible() {
		RowSources sources = new RowSources();
		sources.put(GridAjustePuestoTrabajo.GRAPH_SOURCE_ID, gridAjusteCombustible.getGraphRows());
		sources.put(GridTablaBalance2.GRAPH_SOURCE_ID, gridTablaBalance.getGraphRowsCombustible());

		SmartGWTCellManager cellManager = new SmartGWTCellManager(sources);
		cellManager.setGridsToRefresh(new ListGrid[] { gridAjusteCombustible, gridTablaBalance });

		GWT.log(cellManager.asGraphViz());

		cellManager.recalcAll();

		cellManager.rebuildGraphAndDependencies();

		cellManager.recalcAll();

	}

	private void calcularSumaComustible() {

		try {

			Object cadena2obj = txtFactura.getValue();
			if (cadena2obj == null) {
				cadena2obj = "0";
			}

			Object cadena1obj = txtConsumoRestante.getValue();
			if (cadena1obj == null) {
				cadena1obj = "0";
			}
			String cadena1 = cadena1obj.toString();
			String cadena2 = cadena2obj.toString();
			final NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			Double numero = 0D;
			Double numero2 = 0D;

			if (cadena1 != null && !cadena1.equals("")) {
				numero = Double.valueOf(cadena1);
			}
			if (cadena2 != null && !cadena2.equals("")) {
				numero2 = Double.valueOf(cadena2);
			}
			numero = numero + numero2;
			txtTotal.setValue(nf.format(numero));
			gridTablaBalance.modificarIngresoBalance((numero));

		} catch (Exception e) {
			Window.alert("2." + e.getMessage());
		}
	}

	public void guardarMerma() {
		try {

			mostrarBarraProgreso();

			Object cadena1obj = txtMerma.getValue();
			if (cadena1obj == null) {
				cadena1obj = "0";
			}
			String cadena1 = cadena1obj.toString();
			Double cantMerma = 0d;
			if (cadena1 != null) {
				cantMerma = Double.valueOf(cadena1);
			}

			List<RegistroTablaConsumosPuestoTrabajoDTO> registroConsumoPuestoTrabajo = gridAjusteCombustible
					.exportarGridAjusteCombustibleMermaAutomatica();

			Double stockFinal = traerStockFinalBalance();

			Double consumoPt = 0d;
			Double consumoTotal = 0d;
			Double formula = 0d;

			for (RegistroTablaConsumosPuestoTrabajoDTO lista : registroConsumoPuestoTrabajo) {
				consumoTotal += lista.getMontoConsumido();
			}

			for (RegistroTablaConsumosPuestoTrabajoDTO lista : registroConsumoPuestoTrabajo) {

				consumoPt = lista.getMontoConsumido();
				if (consumoPt != null && consumoTotal > 0.00) {
					formula = ((stockFinal - cantMerma) * consumoPt) / consumoTotal;

					lista.setAjusteTM(reducirAdosDecimales(formula));
				}
			}

			for (RegistroTablaConsumosPuestoTrabajoDTO lista : registroConsumoPuestoTrabajo) {

				consumoPt = lista.getMontoConsumido();
				if (consumoPt != null && consumoTotal > 0.00) {
					formula = (cantMerma * consumoPt) / consumoTotal;

					lista.setPorcetanjeCarbones(reducirAdosDecimales(formula));
				}
			}
			gridAjusteCombustible.limpiarGrid();
			gridAjusteCombustible.cargarGridAjusteCombustible(registroConsumoPuestoTrabajo);
			generarGrafoCombustible();

		} catch (Exception e) {
			Window.alert("3." + e.getMessage());
		} finally {
			ocultarBarraProgreso();
		}
	}

	/**
	 * se cogio del NumberUtil como ayuda para que se arregle el problema de la
	 * suma en la columna saldo Final
	 * 
	 * @param dblnum
	 * @return
	 */
	private double reducirAdosDecimales(double dblnum) {

		double numero = dblnum * 100;
		numero = Math.round(numero);
		dblnum = numero / 100;

		return dblnum;

	}

	private Double traerStockFinalBalance() {
		return gridTablaBalance.obtenerStockFinalBalance();
	}

	/**
	 * Construye la GUI del formulario del ingreso del Combustible
	 * 
	 * @return HLayout
	 */
	public VLayout initFormularioCombustible() {
		try {

			DynamicForm form = new DynamicForm();

			form.setNumCols(1);
			form.setWidth(400);
			form.setColWidths(25);
			form.setAlign(Alignment.LEFT);

			VLayout layoutObservaciones = new VLayout();
			layoutObservaciones.setMembersMargin(5);

			//
			// // --1
			txtDiaFactura = new TextItem();
			txtDiaFactura.setTitle("Dia Factura (dd/mm/AAAA)");
			txtDiaFactura.setName("factura");

			txtDiaFactura.addBlurHandler(new com.smartgwt.client.widgets.form.fields.events.BlurHandler() {

				@Override
				public void onBlur(com.smartgwt.client.widgets.form.fields.events.BlurEvent event) {
					cargarConsumoHastaFinMes(txtDiaFactura.getValue() + "");

				}
			});

			// // --2

			txtFactura = new TextItem();
			txtFactura.setTitle("Factura Primax");
			txtFactura.setName("FacturaPrimax");
			txtFactura.addBlurHandler(new com.smartgwt.client.widgets.form.fields.events.BlurHandler() {

				public void onBlur(com.smartgwt.client.widgets.form.fields.events.BlurEvent arg0) {
					calcularSumaComustible();
				}

			});

			//
			// // --3

			txtConsumoRestante = new TextItem();
			txtConsumoRestante.setTitle("Consumo");
			txtConsumoRestante.setName("Consumo");
			txtConsumoRestante.setDisabled(true);

			// // --4

			txtTotal = new TextItem();
			txtTotal.setTitle("total Mes");
			txtTotal.setName("totalMes");
			txtTotal.setDisabled(true);

			final NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			// // --5

			txtMerma = new TextItem();
			txtMerma.setTitle("Merma");
			txtMerma.setName("Merma");
			txtMerma.addBlurHandler(new com.smartgwt.client.widgets.form.fields.events.BlurHandler() {
				public void onBlur(com.smartgwt.client.widgets.form.fields.events.BlurEvent arg0) {
					guardarMerma();
				}

			});
			txtMerma.setDisabled(true);
			txtMerma.setValue((nf.format(0)));

			form.setItems(txtDiaFactura, txtFactura, txtConsumoRestante, txtTotal, txtMerma);
			layoutObservaciones.addMember(form);
			return layoutObservaciones;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void cargarConsumoHastaFinMes(String fecha) {
		Long codigoLineaNegocio = Long.parseLong(lineaNegocioItem.getValue().toString());
		Long codigoProducto = Long.parseLong(productoItem.getValue().toString());

		AsyncCallback<String[]> callback = new AsyncCallback<String[]>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				if (throwable instanceof ServicioGWTGlobalException) {
					Window.alert(throwable.getMessage());
				} else {
					Window.alert(CONSTANTES.valorAjusteNoCalculado());
				}
			}

			public void onSuccess(String[] ajuste) {

				if (ajuste == null) {
					return;
				}
				if (ajuste.length == 3) {
					// etiquetaConsumo.setContents("Consumo del " + ajuste[0] +
					// " Al " + ajuste[1]);
					txtConsumoRestante.setTitle("Consumo del " + ajuste[0] + " Al " + ajuste[1]);
					txtConsumoRestante.redraw();

					txtConsumoRestante.setValue(ajuste[2]);

					calcularSumaComustible();
				}

			}
		};
		if (fecha != null && !fecha.trim().equals("")) {
			servicioParteTecnico.obtenerConsumoDesdeFecha(codigoLineaNegocio, codigoProducto, fecha, callback);
		}

	}

	public Long obtenerCodigoProducto() {
		if (productoItem.getValue() == null) {
			return 0L;
		}
		return Long.parseLong(productoItem.getValue().toString());
	}

	/**
	 * Este metodo se encarga de cargar la informacion del bunker desde la base
	 * de datos
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarTablaAjusteCombuslibleLiquido(final Long codigoProducto, final Long codigoLinea, final String mesContable,
			final Integer anioContable) {

		AsyncCallback<AjusteProductoDTO> callback = new AsyncCallback<AjusteProductoDTO>() {
			public void onFailure(Throwable throwable) {
				ocultarBarraProgreso();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.errorComunicacionServidor());
				}
			}

			public void onSuccess(AjusteProductoDTO ajusteProductoDTO) {
				try {
					cargarValorAjusteCombustible(ajusteProductoDTO, codigoProducto, codigoLinea, mesContable, anioContable);
				} catch (Exception e) {
					e.printStackTrace();
					Window.alert("Error interno:1 " + e.getMessage());
				}
			}
		};

		servicioParteTecnico.verificarSiExisteAjusteBd(codigoProducto, codigoLinea, mesContable, anioContable, callback);

	}

	/**
	 * Metodo para imprimir el valor del ajuste en la pantalla
	 * 
	 * @param ajusteProductoDTO
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarValorAjusteCombustible(AjusteProductoDTO ajusteProductoDTO, final Long codigoProducto,
			final Long codigoLinea, final String mesContable, final Integer anioContable) {

		final NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

		AsyncCallback<Double> callback = new AsyncCallback<Double>() {
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				ocultarBarraProgreso();
				if (throwable instanceof ServicioGWTGlobalException) {
					Window.alert(throwable.getMessage());
				} else {
					Window.alert(CONSTANTES.valorAjusteNoCalculado());
				}
			}

			public void onSuccess(Double ajuste) {

				String ajusteStr = nf.format(ajuste);
				setValorAjuste(nf.parse(ajusteStr));
				cargarTablaBalanceCombustible(ajuste, codigoProducto, codigoLinea, mesContable, anioContable);
				gridMovimientosDeAjusteCombustible.limpiarGrid();
			}
		};

		boolean ajustado = ajusteProductoDTO != null && ajusteProductoDTO.getEstado().equals("Aprobado");
		habilitarODesHabilitarControlesCombustible(ajustado);
		setEtiquetaEstado(ajustado);

		// valido si el ajuste de puesto de trabajo existe
		if (ajusteProductoDTO != null) {
			String ajusteStr = nf.format(ajusteProductoDTO.getAjuste());
			this.ajuste = nf.parse(ajusteStr);
			this.txtObservaciones.setText(ajusteProductoDTO.getObservacion());
			cargarTablaBalanceCombustible(ajuste, codigoProducto, codigoLinea, mesContable, anioContable);
			cargarGridMovimientoAjustesCombustibleBD(ajusteProductoDTO);
		} else {
			servicioParteTecnico.obtenerAjuste(codigoLinea, codigoProducto, mesContable, anioContable, callback);
		}

	}

	/**
	 * Metodo para cargar la tabla de balance
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarTablaBalanceCombustible(final Double ajuste, final Long codigoProducto, final Long codigoLinea,
			final String mesContable, final Integer anioContable) {
		AsyncCallback<RegistroTablaBalanceDTO> callback = new AsyncCallback<RegistroTablaBalanceDTO>() {

			public void onFailure(Throwable throwable) {
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.faltanDatosTablaBalance());
				}
			}

			public void onSuccess(RegistroTablaBalanceDTO datosServidor) {
				try {
					gridTablaBalance.cargarGridTablaBalance(datosServidor, ajuste);
					cargarRegistrosFacturaCombustible(codigoProducto, codigoLinea, mesContable, anioContable);
					cargarGridAjusteCombustible(codigoProducto, codigoLinea, mesContable, anioContable);
				} catch (Exception e) {
					e.printStackTrace();
					Window.alert(e.getMessage());
				}

			}

		};
		servicioParteTecnico.obtenerConceptosLibro(codigoProducto, codigoLinea, mesContable, anioContable, callback);

	}

	/**
	 * Metodo para cargar la tabla de Movimiento Ajuste
	 * 
	 * @param ajusteProductoDTO
	 */
	private void cargarGridMovimientoAjustesCombustibleBD(final AjusteProductoDTO ajusteProductoDTO) {
		AsyncCallback<List<RegistroTablaAjusteDTO>> callback = new AsyncCallback<List<RegistroTablaAjusteDTO>>() {

			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.errorComunicacionServidor());
				}
			}

			public void onSuccess(List<RegistroTablaAjusteDTO> registrosTablaAjusteDTO) {
				gridMovimientosDeAjusteCombustible.limpiarGrid();
				gridMovimientosDeAjusteCombustible.cargaGridMovimientosAjuste(registrosTablaAjusteDTO);
			}
		};

		servicioParteTecnico.obtenerDatosGridMovimientoAjustesCombustibleBD(ajusteProductoDTO.getCodigo(), callback);
	}

	/**
	 * Metodo para cargar la tabla de ajustes del consumo del bunker
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarGridAjusteCombustible(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable) {

		servicioParteTecnico.obtenerDatosCombustible(codigoProducto, codigoLinea, mesContable, anioContable,
				new AsyncCallback<List<RegistroTablaConsumosPuestoTrabajoDTO>>() {

					public void onFailure(Throwable throwable) {
						ocultarBarraProgreso();
						throwable.printStackTrace();
						if (throwable instanceof ServicioGWTGlobalException) {
							ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
							Window.alert(e.getMensaje());
						} else {
							Window.alert(CONSTANTES.errorComunicacionServidor());
						}

					}

					public void onSuccess(List<RegistroTablaConsumosPuestoTrabajoDTO> listaCom) {
						try {
							// for (RegistroTablaConsumosPuestoTrabajoDTO lista
							// : listaCom) {
							// lista.setStockFinalIngresado(gridTablaBalance.getConsumoAjusteBalance());
							// lista.setMermaIngresada(Double.valueOf(txtMerma.getText().trim()));
							// }

							gridAjusteCombustible.limpiarGrid();
							gridAjusteCombustible.cargarGridAjusteCombustible(listaCom);

							generarGrafoCombustible();
							ocultarBarraProgreso();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

	}

	/**
	 * Metodo para cargar la informacion adicinal del bunker como la factura,
	 * ingreso
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 */
	private void cargarRegistrosFacturaCombustible(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable) {
		AsyncCallback<String[]> callbakDatosFactura = new AsyncCallback<String[]>() {

			public void onFailure(Throwable throwable) {
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.faltanDatosTablaBalance());
				}
			}

			public void onSuccess(String[] datosServidor) {
				try {

					if (datosServidor.length == 4) {
						Double factura = 0d;
						Double merma = 0d;
						Double consumoRestante = 0d;
						if (datosServidor[1] != null) {
							factura = Double.valueOf(datosServidor[1]);
						}
						if (datosServidor[2] != null) {
							consumoRestante = Double.valueOf(datosServidor[2]);
						}
						if (datosServidor[3] != null) {
							merma = Double.valueOf(datosServidor[3]);
						}
						txtDiaFactura.setValue(datosServidor[0]);
						txtFactura.setValue(factura.toString());
						txtMerma.setValue(merma.toString());
						txtConsumoRestante.setValue(consumoRestante.toString());
						calcularSumaComustible();
					}
				} catch (NumberFormatException e) {
					Window.alert(CONSTANTES.errorFormatoNumero());

				} catch (Exception e) {
					Window.alert(e.getMessage());
				}
			}
		};

		servicioParteTecnico.obtenerDatosComprobante(codigoProducto, codigoLinea, mesContable, anioContable, callbakDatosFactura);

	}

	/**
	 * habilitar o deshabilitar los controles del Parte Tecnico
	 * 
	 * @param ajustado
	 */
	private void habilitarODesHabilitarControlesCombustible(boolean disabled) {
		txtObservaciones.setEnabled(!disabled);

		gridMovimientosDeAjusteCombustible.setDisabled(disabled);
		gridAjusteCombustible.setDisabled(disabled);
		gridTablaBalance.setDisabled(disabled);

		imagenNuevo.setDisabled(disabled);
		imagenGenerar.setDisabled(disabled);
		imagenGrabar.setDisabled(disabled);

		etiquetaNuevo.setDisabled(disabled);
		etiquetaGenerar.setDisabled(disabled);
		etiquetaGrabar.setDisabled(disabled);

		txtDiaFactura.setDisabled(disabled);
		txtFactura.setDisabled(disabled);
		txtMerma.setDisabled(disabled);

	}

	/**
	 * @return the txtMerma
	 */
	public static TextItem getTxtMerma() {
		return txtMerma;
	}

}
