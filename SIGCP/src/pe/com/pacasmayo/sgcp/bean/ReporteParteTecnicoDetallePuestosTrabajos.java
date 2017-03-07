package pe.com.pacasmayo.sgcp.bean;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class ReporteParteTecnicoDetallePuestosTrabajos {

	private String puestoTrabajo;
	private String productoTM1;
	private String productoTM2;
	private String productoTM3;
	private String productoTM4;
	private String productoTM5;
	private String productoTM6;
	private String productoTM7;
	private String productoTM8;
	private String productoTM9;
	private String productoTM10;
	private String productoHR1;
	private String productoHR2;
	private String productoHR3;
	private String productoHR4;
	private String productoHR5;
	private String productoHR6;
	private String productoHR7;
	private String productoHR8;
	private String productoHR9;
	private String productoHR10;

	private String combustible1;
	private String combustible2;
	private String combustible3;
	private String combustible4;
	private String combustible5;
	private String combustible6;

	/**
	 * @return the puestoTrabajo
	 */
	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	/**
	 * @param puestoTrabajo the puestoTrabajo to set
	 */
	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	/**
	 * @return the productoTM1
	 */
	public String getProductoTM1() {
		return productoTM1;
	}

	/**
	 * @param productoTM1 the productoTM1 to set
	 */
	public void setProductoTM1(String productoTM1) {
		this.productoTM1 = productoTM1;
	}

	/**
	 * @return the productoTM2
	 */
	public String getProductoTM2() {
		return productoTM2;
	}

	/**
	 * @param productoTM2 the productoTM2 to set
	 */
	public void setProductoTM2(String productoTM2) {
		this.productoTM2 = productoTM2;
	}

	/**
	 * @return the productoTM3
	 */
	public String getProductoTM3() {
		return productoTM3;
	}

	/**
	 * @param productoTM3 the productoTM3 to set
	 */
	public void setProductoTM3(String productoTM3) {
		this.productoTM3 = productoTM3;
	}

	/**
	 * @return the productoTM4
	 */
	public String getProductoTM4() {
		return productoTM4;
	}

	/**
	 * @param productoTM4 the productoTM4 to set
	 */
	public void setProductoTM4(String productoTM4) {
		this.productoTM4 = productoTM4;
	}

	/**
	 * @return the productoTM5
	 */
	public String getProductoTM5() {
		return productoTM5;
	}

	/**
	 * @param productoTM5 the productoTM5 to set
	 */
	public void setProductoTM5(String productoTM5) {
		this.productoTM5 = productoTM5;
	}

	/**
	 * @return the productoTM6
	 */
	public String getProductoTM6() {
		return productoTM6;
	}

	/**
	 * @param productoTM6 the productoTM6 to set
	 */
	public void setProductoTM6(String productoTM6) {
		this.productoTM6 = productoTM6;
	}

	/**
	 * @return the productoTM7
	 */
	public String getProductoTM7() {
		return productoTM7;
	}

	/**
	 * @param productoTM7 the productoTM7 to set
	 */
	public void setProductoTM7(String productoTM7) {
		this.productoTM7 = productoTM7;
	}

	/**
	 * @return the productoTM8
	 */
	public String getProductoTM8() {
		return productoTM8;
	}

	/**
	 * @param productoTM8 the productoTM8 to set
	 */
	public void setProductoTM8(String productoTM8) {
		this.productoTM8 = productoTM8;
	}

	/**
	 * @return the productoTM9
	 */
	public String getProductoTM9() {
		return productoTM9;
	}

	/**
	 * @param productoTM9 the productoTM9 to set
	 */
	public void setProductoTM9(String productoTM9) {
		this.productoTM9 = productoTM9;
	}

	/**
	 * @return the productoTM10
	 */
	public String getProductoTM10() {
		return productoTM10;
	}

	/**
	 * @param productoTM10 the productoTM10 to set
	 */
	public void setProductoTM10(String productoTM10) {
		this.productoTM10 = productoTM10;
	}

	/**
	 * @return the productoHR1
	 */
	public String getProductoHR1() {
		return productoHR1;
	}

	/**
	 * @param productoHR1 the productoHR1 to set
	 */
	public void setProductoHR1(String productoHR1) {
		this.productoHR1 = productoHR1;
	}

	/**
	 * @return the productoHR2
	 */
	public String getProductoHR2() {
		return productoHR2;
	}

	/**
	 * @param productoHR2 the productoHR2 to set
	 */
	public void setProductoHR2(String productoHR2) {
		this.productoHR2 = productoHR2;
	}

	/**
	 * @return the productoHR3
	 */
	public String getProductoHR3() {
		return productoHR3;
	}

	/**
	 * @param productoHR3 the productoHR3 to set
	 */
	public void setProductoHR3(String productoHR3) {
		this.productoHR3 = productoHR3;
	}

	/**
	 * @return the productoHR4
	 */
	public String getProductoHR4() {
		return productoHR4;
	}

	/**
	 * @param productoHR4 the productoHR4 to set
	 */
	public void setProductoHR4(String productoHR4) {
		this.productoHR4 = productoHR4;
	}

	/**
	 * @return the productoHR5
	 */
	public String getProductoHR5() {
		return productoHR5;
	}

	/**
	 * @param productoHR5 the productoHR5 to set
	 */
	public void setProductoHR5(String productoHR5) {
		this.productoHR5 = productoHR5;
	}

	/**
	 * @return the productoHR6
	 */
	public String getProductoHR6() {
		return productoHR6;
	}

	/**
	 * @param productoHR6 the productoHR6 to set
	 */
	public void setProductoHR6(String productoHR6) {
		this.productoHR6 = productoHR6;
	}

	/**
	 * @return the productoHR7
	 */
	public String getProductoHR7() {
		return productoHR7;
	}

	/**
	 * @param productoHR7 the productoHR7 to set
	 */
	public void setProductoHR7(String productoHR7) {
		this.productoHR7 = productoHR7;
	}

	/**
	 * @return the productoHR8
	 */
	public String getProductoHR8() {
		return productoHR8;
	}

	/**
	 * @param productoHR8 the productoHR8 to set
	 */
	public void setProductoHR8(String productoHR8) {
		this.productoHR8 = productoHR8;
	}

	/**
	 * @return the productoHR9
	 */
	public String getProductoHR9() {
		return productoHR9;
	}

	/**
	 * @param productoHR9 the productoHR9 to set
	 */
	public void setProductoHR9(String productoHR9) {
		this.productoHR9 = productoHR9;
	}

	/**
	 * @return the productoHR10
	 */
	public String getProductoHR10() {
		return productoHR10;
	}

	/**
	 * @param productoHR10 the productoHR10 to set
	 */
	public void setProductoHR10(String productoHR10) {
		this.productoHR10 = productoHR10;
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

	private void setPropiedad(Object claseObjeto, String nombreAtributo, String valorAtributo) {
		try {
			BeanUtils.setProperty(claseObjeto, nombreAtributo, valorAtributo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
