package pe.com.pacasmayo.sgcp.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

public class ReporteParteTecnicoOperacionesSub_A_B_Bean {

	private String producto1;
	private String producto2;
	private String producto3;
	private String producto4;
	private String producto5;
	private String producto6;
	private String producto7;
	private String producto8;
	private String producto9;
	private String producto10;
	private List<ReporteParteTecnicoDetallePuestosTrabajos> detallePuestoTrabajos;
	private String combustible1;
	private String combustible2;
	private String combustible3;
	private String combustible4;
	private String combustible5;
	private String combustible6;

	public ReporteParteTecnicoOperacionesSub_A_B_Bean() {

	}

	public ReporteParteTecnicoOperacionesSub_A_B_Bean(List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list) {

		ArrayList<String> productos = llenarProductos(list);
		ArrayList<String> puestosTrabajos = hallarPuestoTrabajos(list);
		detallePuestoTrabajos = new ArrayList<ReporteParteTecnicoDetallePuestosTrabajos>();
		ReporteParteTecnicoDetallePuestosTrabajos reportePT;
		ParametroSistemaLogicFacade parametro = new ParametroSistemaLogic();
		ParametroSistemaBean parametroBean;
		try {

			for (int i = 0; i < puestosTrabajos.size(); i++) {
				reportePT = new ReporteParteTecnicoDetallePuestosTrabajos();
				reportePT.setPuestoTrabajo(puestosTrabajos.get(i));
				for (int j = 0; j < productos.size(); j++) {
					for (DetalleParteTecnicoPuestoTrabajoComponenteBean bean : list) {
						if (bean.getComponente().compareToIgnoreCase(productos.get(j)) == 0) {
							for (int k = 0; k < bean.getDetallePuestoTrabajo().size(); k++) {
								if (bean.getDetallePuestoTrabajo().get(k).getPuestoTrabajo()
										.compareToIgnoreCase(puestosTrabajos.get(i)) == 0) {
									Integer dato = Integer.parseInt(parametro.obtenerParametroSistema(
											ConstantesParametro.TIPOMEDIDA_TM).getValor());
									setPropiedad(reportePT, "productoTM" + (j + 1), NumberUtil.redondeoReportePT(bean
											.getDetallePuestoTrabajo().get(k).getProduccion(), dato,
											ConstantesParametro.TIPOMEDIDA_TM));
									dato = Integer.parseInt(parametro.obtenerParametroSistema(ConstantesParametro.TIPOMEDIDA_HR)
											.getValor());
									setPropiedad(
											reportePT,
											"productoHR" + (j + 1),
											NumberUtil.redondeoReportePT(bean.getDetallePuestoTrabajo().get(k)
													.getTiempoProduccion(), dato, ConstantesParametro.TIPOMEDIDA_HR));
									if (!bean.getDetallePuestoTrabajo().get(k).getPuestoTrabajo().equals("Total")) {
										if (bean.getDetallePuestoTrabajo().get(k).getDetalleConsumoCombustible() != null) {

											for (int k2 = 0; k2 < bean.getDetallePuestoTrabajo().get(k)
													.getDetalleConsumoCombustible().size(); k2++) {
												String unidadMedida = bean.getDetallePuestoTrabajo().get(k)
														.getDetalleConsumoCombustible().get(k2).getUnidadMedida();
												parametroBean = parametro.obtenerParametroSistema(unidadMedida);
												dato = ConstantesParametro.CANTIDAD_DECIMALES_SGCP_DEFAULT;
												if (parametroBean != null) {
													dato = Integer.parseInt(parametro.obtenerParametroSistema(unidadMedida)
															.getValor());
												}

												setPropiedad(
														reportePT,
														"combustible" + (k2 + 1),
														NumberUtil.redondeoReportePT(bean.getDetallePuestoTrabajo().get(k)
																.getDetalleConsumoCombustible().get(k2)
																.getConsumoComponenteCombustible(), dato, unidadMedida));

											}
										}
									}
								}
							}

						}
					}
				}

				detallePuestoTrabajos.add(reportePT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			llenarCombustibles(productos.get(i), list);
		}

		return productos;
	}

	private void llenarCombustibles(String producto, List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list) {
		try {

			for (DetalleParteTecnicoPuestoTrabajoComponenteBean bean : list) {
				if (bean.getComponente().compareToIgnoreCase(producto) == 0) {
					for (int i = 0; i < bean.getDetallePuestoTrabajo().size(); i++) {

						if (!bean.getDetallePuestoTrabajo().get(i).getPuestoTrabajo().equals("Total")) {
							if (bean.getDetallePuestoTrabajo().get(i).getDetalleConsumoCombustible() != null) {

								for (int j = 0; j < bean.getDetallePuestoTrabajo().get(i).getDetalleConsumoCombustible().size(); j++) {

									// combustible1
									setPropiedad(this, "combustible" + (j + 1), bean.getDetallePuestoTrabajo().get(i)
											.getDetalleConsumoCombustible().get(j).getNombreComponenteCombustible());

								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO: Manejo de Log
			e.printStackTrace();
		}

	}

	private boolean encontrarElementoCadena(ArrayList<String> cadenas, String cadena) {
		for (String cadena2 : cadenas) {
			if (cadena2.compareToIgnoreCase(cadena) == 0) {
				return true;
			}
		}
		return false;
	}

	// private void setPropiedad(String propiedad, String valor) {
	// try {
	// BeanUtils.setProperty(this, propiedad, valor);
	// } catch (IllegalAccessException e) {
	//
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	//
	// e.printStackTrace();
	// }
	// }

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
	 * @return the producto2
	 */
	public String getProducto2() {
		return producto2;
	}

	/**
	 * @param producto2 the producto2 to set
	 */
	public void setProducto2(String producto2) {
		this.producto2 = producto2;
	}

	/**
	 * @return the producto3
	 */
	public String getProducto3() {
		return producto3;
	}

	/**
	 * @param producto3 the producto3 to set
	 */
	public void setProducto3(String producto3) {
		this.producto3 = producto3;
	}

	/**
	 * @return the producto4
	 */
	public String getProducto4() {
		return producto4;
	}

	/**
	 * @param producto4 the producto4 to set
	 */
	public void setProducto4(String producto4) {
		this.producto4 = producto4;
	}

	/**
	 * @return the producto5
	 */
	public String getProducto5() {
		return producto5;
	}

	/**
	 * @param producto5 the producto5 to set
	 */
	public void setProducto5(String producto5) {
		this.producto5 = producto5;
	}

	/**
	 * @return the producto6
	 */
	public String getProducto6() {
		return producto6;
	}

	/**
	 * @param producto6 the producto6 to set
	 */
	public void setProducto6(String producto6) {
		this.producto6 = producto6;
	}

	/**
	 * @return the producto7
	 */
	public String getProducto7() {
		return producto7;
	}

	/**
	 * @param producto7 the producto7 to set
	 */
	public void setProducto7(String producto7) {
		this.producto7 = producto7;
	}

	/**
	 * @return the producto8
	 */
	public String getProducto8() {
		return producto8;
	}

	/**
	 * @param producto8 the producto8 to set
	 */
	public void setProducto8(String producto8) {
		this.producto8 = producto8;
	}

	/**
	 * @return the producto9
	 */
	public String getProducto9() {
		return producto9;
	}

	/**
	 * @param producto9 the producto9 to set
	 */
	public void setProducto9(String producto9) {
		this.producto9 = producto9;
	}

	/**
	 * @return the producto10
	 */
	public String getProducto10() {
		return producto10;
	}

	/**
	 * @param producto10 the producto10 to set
	 */
	public void setProducto10(String producto10) {
		this.producto10 = producto10;
	}

	/**
	 * @return the detallePuestoTrabajos
	 */
	public List<ReporteParteTecnicoDetallePuestosTrabajos> getDetallePuestoTrabajos() {
		return detallePuestoTrabajos;
	}

	/**
	 * @param detallePuestoTrabajos the detallePuestoTrabajos to set
	 */
	public void setDetallePuestoTrabajos(List<ReporteParteTecnicoDetallePuestosTrabajos> detallePuestoTrabajos) {
		this.detallePuestoTrabajos = detallePuestoTrabajos;
	}

	/**
	 * @return the combustible1
	 */
	public String getCombustible1() {
		return combustible1;
	}

	/**
	 * @param combustible1 the combustible1 to set
	 */
	public void setCombustible1(String combustible1) {
		this.combustible1 = combustible1;
	}

	/**
	 * @return the combustible2
	 */
	public String getCombustible2() {
		return combustible2;
	}

	/**
	 * @param combustible2 the combustible2 to set
	 */
	public void setCombustible2(String combustible2) {
		this.combustible2 = combustible2;
	}

	/**
	 * @return the combustible3
	 */
	public String getCombustible3() {
		return combustible3;
	}

	/**
	 * @param combustible3 the combustible3 to set
	 */
	public void setCombustible3(String combustible3) {
		this.combustible3 = combustible3;
	}

	/**
	 * @return the combustible4
	 */
	public String getCombustible4() {
		return combustible4;
	}

	/**
	 * @param combustible4 the combustible4 to set
	 */
	public void setCombustible4(String combustible4) {
		this.combustible4 = combustible4;
	}

	/**
	 * @return the combustible5
	 */
	public String getCombustible5() {
		return combustible5;
	}

	/**
	 * @param combustible5 the combustible5 to set
	 */
	public void setCombustible5(String combustible5) {
		this.combustible5 = combustible5;
	}

	/**
	 * @return the combustible6
	 */
	public String getCombustible6() {
		return combustible6;
	}

	/**
	 * @param combustible6 the combustible6 to set
	 */
	public void setCombustible6(String combustible6) {
		this.combustible6 = combustible6;
	}

	public void setMiAtributo(String nombreAtributo, String valorAtributo) {
		setPropiedad(this, nombreAtributo, valorAtributo);
	}

}
