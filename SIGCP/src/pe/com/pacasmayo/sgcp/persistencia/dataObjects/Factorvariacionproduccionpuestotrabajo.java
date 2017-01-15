package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Factorkardex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Factorvariacionproduccionpuestotrabajo implements java.io.Serializable {

	// Fields

	private Long pkCodigoFactorvariacionproduccion;
	private Productogenerado productogenerado;
	private Productovariablevariacion productovariablevariacion;
	private Double valorVariablevariacionFactorv;

	public Factorvariacionproduccionpuestotrabajo() {
		// TODO Auto-generated constructor stub
	}

	public Factorvariacionproduccionpuestotrabajo(Long pkCodigoFactorvariacionproduccion, Productogenerado productogenerado,
			Productovariablevariacion productovariablevariacion, Double valorVariablevariacionFactorv) {
		super();
		this.pkCodigoFactorvariacionproduccion = pkCodigoFactorvariacionproduccion;
		this.productogenerado = productogenerado;
		this.productovariablevariacion = productovariablevariacion;
		this.valorVariablevariacionFactorv = valorVariablevariacionFactorv;
	}

	public Factorvariacionproduccionpuestotrabajo(Productogenerado productogenerado,
			Productovariablevariacion productovariablevariacion, Double valorVariablevariacionFactorv) {
		super();

		this.productogenerado = productogenerado;
		this.productovariablevariacion = productovariablevariacion;
		this.valorVariablevariacionFactorv = valorVariablevariacionFactorv;
	}

	/**
	 * @return the pkCodigoFactorvariacionproduccion
	 */
	public Long getPkCodigoFactorvariacionproduccion() {
		return pkCodigoFactorvariacionproduccion;
	}

	/**
	 * @param pkCodigoFactorvariacionproduccion the
	 *            pkCodigoFactorvariacionproduccion to set
	 */
	public void setPkCodigoFactorvariacionproduccion(Long pkCodigoFactorvariacionproduccion) {
		this.pkCodigoFactorvariacionproduccion = pkCodigoFactorvariacionproduccion;
	}

	/**
	 * @return the productogenerado
	 */
	public Productogenerado getProductogenerado() {
		return productogenerado;
	}

	/**
	 * @param productogenerado the productogenerado to set
	 */
	public void setProductogenerado(Productogenerado productogenerado) {
		this.productogenerado = productogenerado;
	}

	/**
	 * @return the productovariablevariacion
	 */
	public Productovariablevariacion getProductovariablevariacion() {
		return productovariablevariacion;
	}

	/**
	 * @param productovariablevariacion the productovariablevariacion to set
	 */
	public void setProductovariablevariacion(Productovariablevariacion productovariablevariacion) {
		this.productovariablevariacion = productovariablevariacion;
	}

	/**
	 * @return the valorVariablevariacionFactorv
	 */
	public Double getValorVariablevariacionFactorv() {
		return valorVariablevariacionFactorv;
	}

	/**
	 * @param valorVariablevariacionFactorv the valorVariablevariacionFactorv to
	 *            set
	 */
	public void setValorVariablevariacionFactorv(Double valorVariablevariacionFactorv) {
		this.valorVariablevariacionFactorv = valorVariablevariacionFactorv;
	}

}