package pe.com.pacasmayo.sgcp.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n)
 * Archivo: ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean.java
 * Modificado: Sep 27, 2012 6:27:52 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean {
	private String producto1;
	private String componente1;
	private String componente2;
	private String componente3;
	private String componente4;
	private String componente5;
	private String componente6;
	private String componente7;
	private String componente8;
	private String componente9;
	private String componente10;
	private String componente11;
	private String componente12;
	private String componente13;
	private String componente14;
	private List<ReporteParteTecnicoDetalleConsumoPuestosTrabajos> detalleConsumoPuestoTrabajos;

	private String FACTOR = "Factor";
	private String ANTRACITA = "Antracita";
	private String BITUMIMOSO = "Bituminoso";
	private String COMPONENTE = "componente";
	private String COMPONENTE_TM = "componenteTM";
	private String DIFERENCIA = "Diferencia";

	public ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean(
			List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list, String[] listaNombresClinker, String[] listanombreCrudo,
			String[] listaNombreBituminoso, String[] listaNombreAntracita, String tiporeporte) {

		ArrayList<String> productos = llenarProductos(list);
		Map<String, Integer> numeroColumnaComponente = hallarComponentes(list);

		ArrayList<String> puestosTrabajos = hallarPuestoTrabajos(list);
		detalleConsumoPuestoTrabajos = new ArrayList<ReporteParteTecnicoDetalleConsumoPuestosTrabajos>();
		ReporteParteTecnicoDetalleConsumoPuestosTrabajos reportePT;
		ParametroSistemaLogicFacade parametro = new ParametroSistemaLogic();
		try {

			for (int i = 0; i < puestosTrabajos.size(); i++) {

				reportePT = new ReporteParteTecnicoDetalleConsumoPuestosTrabajos();
				reportePT.setPuestoTrabajo(puestosTrabajos.get(i));
				for (int j = 0; j < productos.size(); j++) {
					for (DetalleParteTecnicoPuestoTrabajoComponenteBean bean : list) {
						if (bean.getComponente().compareToIgnoreCase(productos.get(j)) == 0) {
							for (int k = 0; k < bean.getDetallePuestoTrabajo().size(); k++) {

								if (bean.getDetallePuestoTrabajo().get(k).getPuestoTrabajo()
										.compareToIgnoreCase(puestosTrabajos.get(i)) == 0) {

									Integer dato = Integer.parseInt(parametro.obtenerParametroSistema(
											ConstantesParametro.TIPOMEDIDA_TM).getValor());
									reportePT.setProductoTM1(NumberUtil.redondeoReportePT(bean.getDetallePuestoTrabajo().get(k)
											.getProduccion(), dato, ConstantesParametro.TIPOMEDIDA_TM));
									dato = Integer.parseInt(parametro.obtenerParametroSistema(ConstantesParametro.TIPOMEDIDA_HR)
											.getValor());
									reportePT.setProductoHR1(NumberUtil.redondeoReportePT(bean.getDetallePuestoTrabajo().get(k)
											.getTiempoProduccion(), dato, ConstantesParametro.TIPOMEDIDA_HR));

									if (!bean.getDetallePuestoTrabajo().get(k).getPuestoTrabajo().equals("Total")) {
										if (bean.getDetallePuestoTrabajo().get(k).getDetalleConsumoCombustible() != null) {
											for (int k2 = 0; k2 < bean.getDetallePuestoTrabajo().get(k)
													.getDetalleConsumoCombustible().size(); k2++) {
												DetallePuestoTrabajoConsumoCombustibleBean detallecombustible = bean
														.getDetallePuestoTrabajo().get(k).getDetalleConsumoCombustible().get(k2);
												dato = Integer.parseInt(parametro.obtenerParametroSistema(
														ConstantesParametro.TIPOMEDIDA_TM).getValor());

												setPropiedad(
														reportePT,
														COMPONENTE_TM
																+ numeroColumnaComponente.get(detallecombustible
																		.getNombreComponenteCombustible()),
														NumberUtil.redondeoReportePT(bean.getDetallePuestoTrabajo().get(k)
																.getDetalleConsumoCombustible().get(k2)
																.getConsumoComponenteCombustible(), dato,
																ConstantesParametro.TIPOMEDIDA_TM));

											}
										}
									}
								}
							}

						}
					}
				}

				detalleConsumoPuestoTrabajos.add(reportePT);
			}
			if (tiporeporte.equals("XLS")) {
				sumarCantidadesFilas();
				//
				// String[] listanombres = new String[2];
				// listanombres[0] = "C.ANTR. 905-00101";
				// listanombres[1] = "C.ANTR.905-00241";
				// String[] listanombres1 = new String[1];
				// listanombres1[0] = "C.BITUM.905-00014";

				sumarFactores(parametro, listaNombresClinker, listanombreCrudo, listaNombreAntracita, listaNombreBituminoso);

			}
			sumarCantidadesColumnas();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean hallarFactor(String[] listanombres, String titulo) {
		Boolean halloFactor = false;
		ArrayList<Integer> columnasPorducto = new ArrayList<Integer>();

		for (int i = 0; i < 14; i++) {
			String vconsumo = getPropiedad(this, (COMPONENTE + (i + 1)));

			for (int j = 0; j < listanombres.length; j++) {

				if (vconsumo != null && vconsumo.equals(listanombres[j])) {
					halloFactor = true;
					columnasPorducto.add((i + 1));
				}

			}

		}

		Double acumulador;
		String consumo;
		int col = -1;
		for (int i = 0; i < 14; i++) {
			String vconsumo = getPropiedad(this, (COMPONENTE + (i + 1)));

			if (vconsumo == null) {
				col = i;

				break;
			}

		}
		for (ReporteParteTecnicoDetalleConsumoPuestosTrabajos bean : detalleConsumoPuestoTrabajos) {
			acumulador = 0d;
			for (int i = 0; i < columnasPorducto.size(); i++) {
				consumo = getPropiedad(bean, (COMPONENTE_TM + columnasPorducto.get(i)));
				acumulador += NumberUtil.convertirStringToDouble(consumo);

			}

			if (col != -1) {

				Double cantidad = NumberUtil.dividirSinRedondear(acumulador,
						NumberUtil.convertirStringToDouble(bean.getProductoTM1()));
				cantidad = NumberUtil.multiplicar(cantidad, 100d);
				if (cantidad > 0d) {
					setPropiedad(this, (COMPONENTE + (col + 1)), titulo);
					setPropiedad(bean, COMPONENTE_TM + (col + 1), cantidad + " %");

				}
			}

		}

		return halloFactor;
	}

	private void sumarFactores(ParametroSistemaLogicFacade parametro, String[] listaNombresClinker, String[] listanombreCrudo,
			String[] nombresCarbonesAntracita, String[] nombresCarbonesBituminoso) {
		try {
			Boolean factor = hallarFactor(listaNombresClinker, FACTOR);
			if (!factor) {
				factor = hallarFactor(listanombreCrudo, FACTOR);
			}
			if (!factor) {
				hallarFactor(nombresCarbonesBituminoso, BITUMIMOSO);
				hallarFactor(nombresCarbonesAntracita, ANTRACITA);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sumarCantidadesFilas() {

		String consumo;
		Double consumoC;
		Double acumulador;

		int col = -1;
		for (int i = 0; i < 14; i++) {
			String vconsumo = getPropiedad(this, (COMPONENTE + (i + 1)));
			if (vconsumo == null) {
				col = i;
				setPropiedad(this, (COMPONENTE + (i + 1)), "Suma Componentes");
				break;
			}

		}

		for (ReporteParteTecnicoDetalleConsumoPuestosTrabajos bean : detalleConsumoPuestoTrabajos) {
			if (bean.getPuestoTrabajo().equals("Total")) {
				continue;
			}
			acumulador = 0d;
			for (int i = 0; i < 14; i++) {
				consumo = getPropiedad(bean, (COMPONENTE_TM + (i + 1)));
				if (consumo == null) {
					consumo = "0";
				}
				consumoC = Double.valueOf(consumo);
				if (consumoC > 0d) {

					acumulador += consumoC;
				}
			}

			for (int i = 0; i < 14; i++) {
				if (col != -1) {
					// ultimo registro
					setPropiedad(bean, COMPONENTE_TM + (col + 1), acumulador + "");

					setPropiedad(this, COMPONENTE + (col + 2), DIFERENCIA);
					Double diferenciaTotal = NumberUtil.sumar(acumulador,
							(NumberUtil.convertirStringToDouble(bean.getProductoTM1()) * -1));
					setPropiedad(bean, COMPONENTE_TM + (col + 2), diferenciaTotal + "");

					break;
				}

			}
		}

	}

	private void sumarCantidadesColumnas() {
		Double[] suma = new Double[14];
		String consumo;
		String titulo;
		Double consumoC;
		Double acumulador;
		ReporteParteTecnicoDetalleConsumoPuestosTrabajos reportePT = null;
		for (ReporteParteTecnicoDetalleConsumoPuestosTrabajos bean : detalleConsumoPuestoTrabajos) {
			if (bean.getPuestoTrabajo().equals("Total")) {
				reportePT = bean;
			}
			for (int i = 0; i < 14; i++) {
				consumo = getPropiedad(bean, (COMPONENTE_TM + (i + 1)));
				if (consumo == null) {
					consumo = "0";
				}
				titulo = getPropiedad(this, (COMPONENTE + (i + 1)));
				if (titulo != null) {
					if (titulo.equals(FACTOR) || titulo.equals(ANTRACITA) || titulo.equals(BITUMIMOSO)) {
						continue;
					}
				}

				consumoC = Double.valueOf(consumo);
				if (consumoC > 0d) {
					acumulador = NumberUtil.validateDouble(suma[i]);
					acumulador += consumoC;
					suma[i] = NumberUtil.Redondear2Decimales(acumulador);
				}
			}
		}

		// Asignar registro

		if (reportePT == null) {
			return;
		}

		for (int i = 0; i < 14; i++) {
			consumoC = suma[i];
			if (consumoC == null) {
				continue;
			}
			if (consumoC == 0d) {
				continue;
			}

			setPropiedad(reportePT, COMPONENTE_TM + (i + 1), consumoC + "");
		}
		detalleConsumoPuestoTrabajos.add(reportePT);

	}

	private ArrayList<String> llenarProductos(List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list) {
		ArrayList<String> productos = new ArrayList<String>();

		for (DetalleParteTecnicoPuestoTrabajoComponenteBean detalle : list) {
			if (!encontrarElementoCadena(productos, detalle.getComponente())) {
				productos.add(detalle.getComponente());
			}
		}
		Collections.sort(productos);

		for (int i = 0; i < productos.size(); i++) {
			setPropiedad(this, "producto" + (i + 1), productos.get(i));
		}

		return productos;
	}

	private boolean encontrarElementoCadena(ArrayList<String> cadenas, String cadena) {
		for (String cadena2 : cadenas) {
			if (cadena2.compareToIgnoreCase(cadena) == 0) {
				return true;
			}
		}
		return false;
	}

	private void setPropiedad(Object reportePT, String string, String string2) {
		try {
			BeanUtils.setProperty(reportePT, string, string2);
		} catch (IllegalAccessException e) {
			// TODO Manejo de Log
			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}

	}

	private String getPropiedad(Object reportePT, String nombre) {
		String consumo = "0";

		try {
			consumo = BeanUtils.getSimpleProperty(reportePT, nombre);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return consumo;

	}

	private ArrayList<String> hallarPuestoTrabajos(List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list) {
		ArrayList<String> puestosTrabajos = new ArrayList<String>();
		for (DetalleParteTecnicoPuestoTrabajoComponenteBean detalle : list) {

			for (int i = 0; i < detalle.getDetallePuestoTrabajo().size(); i++) {

				if (!encontrarElementoCadena(puestosTrabajos, detalle.getDetallePuestoTrabajo().get(i).getPuestoTrabajo())) {
					puestosTrabajos.add(detalle.getDetallePuestoTrabajo().get(i).getPuestoTrabajo());
				}

			}
		}
		Collections.sort(puestosTrabajos);
		return puestosTrabajos;
	}

	private Map<String, Integer> hallarComponentes(List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list) {
		ArrayList<String> nombreComponentes = new ArrayList<String>();
		Map<String, Integer> numeroColumnaComponente = new HashMap<String, Integer>();
		for (DetalleParteTecnicoPuestoTrabajoComponenteBean detalle : list) {

			for (DetallePuestoTrabajoComponenteBean produccionPuestoTrabajo : detalle.getDetallePuestoTrabajo()) {
				if (!produccionPuestoTrabajo.getPuestoTrabajo().equals("Total")) {
					for (DetallePuestoTrabajoConsumoCombustibleBean consumoComponente : produccionPuestoTrabajo
							.getDetalleConsumoCombustible()) {
						if (!encontrarElementoCadena(nombreComponentes, consumoComponente.getNombreComponenteCombustible())) {
							nombreComponentes.add(consumoComponente.getNombreComponenteCombustible());
						}
					}
				}

			}
		}

		Collections.sort(nombreComponentes);
		for (int i = 0; i < nombreComponentes.size(); i++) {
			setPropiedad(this, COMPONENTE + (i + 1), nombreComponentes.get(i));
			numeroColumnaComponente.put(nombreComponentes.get(i), (i + 1));
		}
		return numeroColumnaComponente;
	}

	/**
	 * @return the producto1
	 */
	public String getProducto1() {
		return producto1;
	}

	/**
	 * @param producto1 the producto1 to set
	 */
	public void setProducto1(String producto1) {
		this.producto1 = producto1;
	}

	/**
	 * @return the componente1
	 */
	public String getComponente1() {
		return componente1;
	}

	/**
	 * @param componente1 the componente1 to set
	 */
	public void setComponente1(String componente1) {
		this.componente1 = componente1;
	}

	/**
	 * @return the componente2
	 */
	public String getComponente2() {
		return componente2;
	}

	/**
	 * @param componente2 the componente2 to set
	 */
	public void setComponente2(String componente2) {
		this.componente2 = componente2;
	}

	/**
	 * @return the componente3
	 */
	public String getComponente3() {
		return componente3;
	}

	/**
	 * @param componente3 the componente3 to set
	 */
	public void setComponente3(String componente3) {
		this.componente3 = componente3;
	}

	/**
	 * @return the componente4
	 */
	public String getComponente4() {
		return componente4;
	}

	/**
	 * @param componente4 the componente4 to set
	 */
	public void setComponente4(String componente4) {
		this.componente4 = componente4;
	}

	/**
	 * @return the componente5
	 */
	public String getComponente5() {
		return componente5;
	}

	/**
	 * @param componente5 the componente5 to set
	 */
	public void setComponente5(String componente5) {
		this.componente5 = componente5;
	}

	/**
	 * @return the componente6
	 */
	public String getComponente6() {
		return componente6;
	}

	/**
	 * @param componente6 the componente6 to set
	 */
	public void setComponente6(String componente6) {
		this.componente6 = componente6;
	}

	/**
	 * @return the componente7
	 */
	public String getComponente7() {
		return componente7;
	}

	/**
	 * @param componente7 the componente7 to set
	 */
	public void setComponente7(String componente7) {
		this.componente7 = componente7;
	}

	/**
	 * @return the componente8
	 */
	public String getComponente8() {
		return componente8;
	}

	/**
	 * @param componente8 the componente8 to set
	 */
	public void setComponente8(String componente8) {
		this.componente8 = componente8;
	}

	/**
	 * @return the componente9
	 */
	public String getComponente9() {
		return componente9;
	}

	/**
	 * @param componente9 the componente9 to set
	 */
	public void setComponente9(String componente9) {
		this.componente9 = componente9;
	}

	/**
	 * @return the componente10
	 */
	public String getComponente10() {
		return componente10;
	}

	/**
	 * @param componente10 the componente10 to set
	 */
	public void setComponente10(String componente10) {
		this.componente10 = componente10;
	}

	/**
	 * @return the componente11
	 */
	public String getComponente11() {
		return componente11;
	}

	/**
	 * @param componente11 the componente11 to set
	 */
	public void setComponente11(String componente11) {
		this.componente11 = componente11;
	}

	/**
	 * @return the componente12
	 */
	public String getComponente12() {
		return componente12;
	}

	/**
	 * @param componente12 the componente12 to set
	 */
	public void setComponente12(String componente12) {
		this.componente12 = componente12;
	}

	/**
	 * @return the componente13
	 */
	public String getComponente13() {
		return componente13;
	}

	/**
	 * @param componente13 the componente13 to set
	 */
	public void setComponente13(String componente13) {
		this.componente13 = componente13;
	}

	/**
	 * @return the componente14
	 */
	public String getComponente14() {
		return componente14;
	}

	/**
	 * @param componente14 the componente14 to set
	 */
	public void setComponente14(String componente14) {
		this.componente14 = componente14;
	}

	/**
	 * @return the detalleConsumoPuestoTrabajos
	 */
	public List<ReporteParteTecnicoDetalleConsumoPuestosTrabajos> getDetalleConsumoPuestoTrabajos() {
		return detalleConsumoPuestoTrabajos;
	}

	/**
	 * @param detalleConsumoPuestoTrabajos the detalleConsumoPuestoTrabajos to
	 *            set
	 */
	public void setDetalleConsumoPuestoTrabajos(
			List<ReporteParteTecnicoDetalleConsumoPuestosTrabajos> detalleConsumoPuestoTrabajos) {
		this.detalleConsumoPuestoTrabajos = detalleConsumoPuestoTrabajos;
	}

}
