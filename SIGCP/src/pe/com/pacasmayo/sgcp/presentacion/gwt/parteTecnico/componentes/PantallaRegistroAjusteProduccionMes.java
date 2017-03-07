package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PantallaRegistroAjusteProduccionMes.java
 * Modificado: Sep 20, 2010 2:28:22 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoAjusteProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.GrupoAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ParteTecnicoService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ParteTecnicoServiceAsync;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Combos;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.UtilConverter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PantallaRegistroAjusteProduccionMes implements ClienteServicioGwt {

	public static final String DIV_PANEL_BASE = "dock-panel";

	// Formulario para colocar los combos
	private static DynamicForm formulario = new DynamicForm();
	// Combo de linea de negocio
	private static SelectItem lineaNegocioItem = new SelectItem();
	// Combo de estados
	private static SelectItem estadosItem = new SelectItem();
	// Combo de meses
	private static SelectItem mesesItem = new SelectItem();
	// Combo de años
	private static SelectItem aniosItem = new SelectItem();
	// Combo de grupo de ajustes
	private static SelectItem grupoAjusteItem = new SelectItem();

	private static ParteTecnicoServiceAsync servicioParteTecnico = GWT.create(ParteTecnicoService.class);

	/**
	 * Método de construcción dela pantalla
	 */
	public PantallaRegistroAjusteProduccionMes() {
	}

	/**
	 * Método para generar el panel general de la pantalla de
	 * RegistroAjusteProduccionMes
	 */
	public void generarPanelGeneral() {

		DockPanel panelGeneral = new DockPanel();
		panelGeneral.setSpacing(5);
		panelGeneral.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		VerticalPanel northPanel = generarPanelLateralNorte();
		panelGeneral.add(northPanel, DockPanel.NORTH);

		VerticalPanel centerPanel = generarPanelCentral("<b>Tabla Balance<br/>CENTER</b>");
		panelGeneral.add(centerPanel, DockPanel.CENTER);

		VerticalPanel southPanel = generarPanelLateral("<b>This is<br/>SOUTH</b>", 4);
		panelGeneral.add(southPanel, DockPanel.SOUTH);

		RootPanel.get(DIV_PANEL_BASE).add(panelGeneral);

	}

	/**
	 * Método para generar el panel del lado norte - combos de selección
	 * 
	 * @param text
	 * @return
	 */
	private VerticalPanel generarPanelLateralNorte() {

		// 1. Se crea el panel
		VerticalPanel panelVerticalNorte = new VerticalPanel();
		panelVerticalNorte.setSpacing(5);
		// panelVerticalNorte.add(new HTML(text));

		// 2. Se crea capa de diseño
		VLayout disenoGrid = new VLayout();

		// 3. Se crea grid 2 de contenido
		DynamicForm formularioCombos = new DynamicForm();
		formularioCombos = initFormularioCombos();
		// 4. se cargan de datos los combos
		cargarComboLineaNegocio();
		cargarComboEstados();
		cargarComboMeses();
		cargarComboAnios();
		// 5. Se establecen eventos
		agregarEventosFormularioCombos();

		disenoGrid.addMember(formularioCombos);
		formularioCombos.draw();

		// 4. Se agrega la capa de diseño al panel
		panelVerticalNorte.add(disenoGrid);
		disenoGrid.draw();

		return (panelVerticalNorte);

	}

	/**
	 * Método para generar el panel central de la pantalla
	 * RegistroAjusteProduccionMes
	 * 
	 * @param text
	 * @return
	 */
	private VerticalPanel generarPanelCentral(String text) {

		// ------------------------------------------------------- GRID NORTE

		// 1. Se crea el panel
		VerticalPanel panelVerticalCentral = new VerticalPanel();
		panelVerticalCentral.setSpacing(5);
		panelVerticalCentral.add(new HTML(text));

		// 2. Se crea capa de diseÃ±o
		VLayout disenoGrid = new VLayout();

		// 3. Se crea grid 2 de contenido
		GridTablaBalance2 tablaBalance2 = new GridTablaBalance2();
		// cargarGridTablaBalance(tablaBalance2);

		// 3. Se crea grid 1 de contenido
		GridTablaBalance tablaBalance = new GridTablaBalance(tablaBalance2);
		cargarGridTablaBalance(tablaBalance);

		disenoGrid.addMember(tablaBalance);
		tablaBalance.draw();

		disenoGrid.addMember(tablaBalance2);
		tablaBalance2.draw();

		// 4. Se agrega la capa de diseÃ±o al panel
		panelVerticalCentral.add(disenoGrid);
		disenoGrid.draw();

		panelVerticalCentral.add(disenoGrid);
		disenoGrid.draw();

		return (panelVerticalCentral);

	}

	private VerticalPanel generarPanelLateral(String text, int numButtons) {
		VerticalPanel panelHorizontal = new VerticalPanel();
		panelHorizontal.setSpacing(5);
		panelHorizontal.add(new HTML(text));
		for (int i = 1; i <= numButtons; i++) {
			panelHorizontal.add(new Button("Button " + i));
		}
		return (panelHorizontal);
	}

	private void cargarGridTablaBalance(GridTablaBalance tablaBalance) {
		List<RegistroTablaBalanceDTO> registrosTablaBalanceDTO = new ArrayList<RegistroTablaBalanceDTO>();

		RegistroTablaBalanceDTO registroTablaBalanceDTO = new RegistroTablaBalanceDTO();
		registroTablaBalanceDTO.setTipoConcepto("Balance");
		registroTablaBalanceDTO.setSaldoFinal(0);
		registroTablaBalanceDTO.setSaldoInicial(25);
		registroTablaBalanceDTO.setConsumo(12);
		registroTablaBalanceDTO.setIngreso(0);

		registrosTablaBalanceDTO.add(registroTablaBalanceDTO);

		registroTablaBalanceDTO = new RegistroTablaBalanceDTO();
		registroTablaBalanceDTO.setTipoConcepto("Ajuste");
		registroTablaBalanceDTO.setSaldoFinal(0);
		registroTablaBalanceDTO.setSaldoInicial(0);
		registroTablaBalanceDTO.setConsumo(12);
		registroTablaBalanceDTO.setIngreso(18);

		registrosTablaBalanceDTO.add(registroTablaBalanceDTO);

		tablaBalance.cargaGridTablaBalance(registrosTablaBalanceDTO);
	}

	/**
	 * Metodo que carga los combos del formulario del registro de ajuste de
	 * produccion
	 */
	private DynamicForm initFormularioCombos() {

		formulario.setNumCols(6);
		formulario.setWidth(700);
		formulario.setColWidths(25, 25, 25, 25, 25, 25);
		formulario.setAlign(Alignment.LEFT);

		// Carga las lineas de negocio segun el usuario en sesion
		SelectItem lineaNegocioItem = Combos.cargarComboLineasNegocio();

		// Carga el combo de estado del ajuste
		SelectItem estadoItem = Combos.cargarComboEstados();

		// Carga el combo de meses del ajuste
		SelectItem mesesItem = Combos.cargarComboMesesPeriodoContable();

		// Carga el combo de años del ajuste
		SelectItem aniosItem = Combos.cargarComboAniosPeriodoContable();

		// Carga el combo de grupo de ajuste
		SelectItem grupoAjusteItem = Combos.cargarComboGrupoAjuste();

		formulario.setItems(lineaNegocioItem, estadoItem, mesesItem, aniosItem, grupoAjusteItem);

		return formulario;
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
	private static void agregarEventosFormularioCombos() {

		lineaNegocioItem = (SelectItem) formulario.getField(Combos.COMBO_LINEA_NEGOCIO);
		grupoAjusteItem = (SelectItem) formulario.getField(Combos.COMBO_GRUPO_AJUSTE);

		addLineaNegocioItemChangeHandler(lineaNegocioItem, grupoAjusteItem);

	}

	public static void addLineaNegocioItemChangeHandler(SelectItem lineaNegocioItem, final SelectItem grupoAjusteItem) {
		lineaNegocioItem.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				String valorLineaNegocio = (String) event.getValue();

				if (valorLineaNegocio != null) {
					cargarComboGrupoAjuste(Long.parseLong(valorLineaNegocio));

				}
			}
		});
	}

	/**
	 * Método parar cargar las lineas de negocio en el combo de presentación
	 */
	private void cargarComboLineaNegocio() {
		lineaNegocioItem = (SelectItem) formulario.getField(Combos.COMBO_LINEA_NEGOCIO);

		if (lineaNegocioItem != null) {
			servicioComunicacion.cargarLineaNegocioUsuario(new AsyncCallback<List<LineaNegocioDTO>>() {
				public void onFailure(Throwable arg0) {
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}

				public void onSuccess(List<LineaNegocioDTO> lineasNegocioDTO) {

					// Se establecen valores para el combo
					LinkedHashMap<String, String> mapaLineaNegocio = UtilConverter.obtenerMapaLineaNegocioDTO(lineasNegocioDTO);
					lineaNegocioItem.setValueMap(mapaLineaNegocio);

					// Se habilita el combo
					lineaNegocioItem.setDisabled(false);
				}
			});
		}
	}

	/**
	 * Método para cargar los valores de los estados de ajuste de produccion
	 */
	private void cargarComboEstados() {
		estadosItem = (SelectItem) formulario.getField(Combos.COMBO_ESTADO);

		if (estadosItem != null) {
			servicioParteTecnico.cargarEstadosAjusteProduccion(new AsyncCallback<List<EstadoAjusteProduccionDTO>>() {
				public void onFailure(Throwable arg0) {
					Window.alert(ConstantesGWT.SERVER_ERROR);

				}

				public void onSuccess(List<EstadoAjusteProduccionDTO> estadoAjusteProduccionDTO) {

					// Se establecen valores para el combo
					LinkedHashMap<String, String> mapaEstadoAjusteProduccion = UtilConverter
							.obtenerMapaEstadoAjusteProduccionDTO(estadoAjusteProduccionDTO);
					estadosItem.setValueMap(mapaEstadoAjusteProduccion);

					// Se habilita el combo
					estadosItem.setDisabled(false);

				}
			});

		}
	}

	/**
	 * Método para cargar los meses del periodo contable abierto
	 */
	private void cargarComboMeses() {

		mesesItem = (SelectItem) formulario.getField(Combos.COMBO_MES);

		if (mesesItem != null) {
			servicioParteTecnico.cargarMeses(new AsyncCallback<List<String>>() {
				public void onFailure(Throwable arg0) {
					Window.alert(ConstantesGWT.SERVER_ERROR);

				}

				public void onSuccess(List<String> meses) {

					// Se establecen valores para el combo
					LinkedHashMap<String, String> mapaMeses = UtilConverter.obtenerMapaMesesDTO(meses);
					mesesItem.setValueMap(mapaMeses);

					// Se habilita el combo
					mesesItem.setDisabled(false);

				}
			});

		}
	}

	/**
	 * Método para cargar los años del periodo contable abierto
	 */
	private void cargarComboAnios() {

		aniosItem = (SelectItem) formulario.getField(Combos.COMBO_ANNO);

		if (aniosItem != null) {
			servicioParteTecnico.cargarAnios(new AsyncCallback<List<Integer>>() {
				public void onFailure(Throwable arg0) {
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}

				public void onSuccess(List<Integer> anios) {

					// Se establecen valores para el combo
					LinkedHashMap<String, String> mapaAnios = UtilConverter.obtenerMapaAniosDTO(anios);
					aniosItem.setValueMap(mapaAnios);

					// Se habilita el combo
					aniosItem.setDisabled(false);
				}
			});

		}
	}

	/**
	 * Método para cargar los valores de los grupos de ajuste de produccion
	 */
	private static void cargarComboGrupoAjuste(Long codigoLineaNegocio) {
		grupoAjusteItem = (SelectItem) formulario.getField(Combos.COMBO_GRUPO_AJUSTE);

		if (grupoAjusteItem != null) {

			servicioParteTecnico.cargarGrupoAjuste(codigoLineaNegocio, new AsyncCallback<List<GrupoAjusteDTO>>() {
				public void onFailure(Throwable arg0) {
					Window.alert(ConstantesGWT.SERVER_ERROR);
				}

				public void onSuccess(List<GrupoAjusteDTO> gruposAjusteDTO) {

					// Se establecen valores para el combo
					LinkedHashMap<String, String> mapaGruposAjuste = UtilConverter.obtenerMapaGrupoAjusteDTO(gruposAjusteDTO);
					grupoAjusteItem.setValueMap(mapaGruposAjuste);

					// Se habilita el combo
					grupoAjusteItem.setDisabled(false);
				}
			});
		}
	}
}