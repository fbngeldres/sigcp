package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Puestotrabajoproduccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Puestotrabajoproduccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoPuestotrabajoproducci;
	private Ajusteproducto ajusteproducto;
	private Puestotrabajo puestotrabajo;
	private Double tmPuestotrabajoproduccion;
	private Double hrPuestotrabajoproduccion;
	private Double tmphPuestotrabajoproduccion;
	private Double hrAjustePuestotrabajoproducci;
	private Double tmAjustePuestotrabajoproducci;
	private Double tmRealPuestotrabajoproduccion;
	private Double hrRealPuestotrabajoproduccion;
	private Double tmphRealPuestotrabajoproducci;
	private Double kcalPuestotrabajoproduccion;
	private Double kcalRealPuestotrabajoproducci;

	private Double carprodPuestotrabajoproduccion;
	private Double carcalentPuestotrabajoproduccion;
	private Double carprodRealPuestotrabajoproduccion;
	private Double carcalentRealPuestotrabajoproduccion;

	private Double bunkprodPuestotrabajoproduccion;
	private Double bunkcalentPuestotrabajoproduccion;
	private Double bunkprodRealPuestotrabajoproduccion;
	private Double bunkcalentRealPuestotrabajoproduccion;

	private Set consumocomponenteajustes = new HashSet(0);
	private Set movimientosajusteproducto = new HashSet(0);
	private Set consumocombustible = new HashSet(0);

	// Constructors

	/** default constructor */
	public Puestotrabajoproduccion() {
	}

	/** minimal constructor */
	public Puestotrabajoproduccion(Ajusteproducto ajusteproducto, Double tmPuestotrabajoproduccion,
			Double hrPuestotrabajoproduccion, Double tmphPuestotrabajoproduccion, Double hrAjustePuestotrabajoproducci,
			Double tmAjustePuestotrabajoproducci, Double tmRealPuestotrabajoproduccion, Double hrRealPuestotrabajoproduccion,
			Double tmphRealPuestotrabajoproducci) {
		this.ajusteproducto = ajusteproducto;
		this.tmPuestotrabajoproduccion = tmPuestotrabajoproduccion;
		this.hrPuestotrabajoproduccion = hrPuestotrabajoproduccion;
		this.tmphPuestotrabajoproduccion = tmphPuestotrabajoproduccion;
		this.hrAjustePuestotrabajoproducci = hrAjustePuestotrabajoproducci;
		this.tmAjustePuestotrabajoproducci = tmAjustePuestotrabajoproducci;
		this.tmRealPuestotrabajoproduccion = tmRealPuestotrabajoproduccion;
		this.hrRealPuestotrabajoproduccion = hrRealPuestotrabajoproduccion;
		this.tmphRealPuestotrabajoproducci = tmphRealPuestotrabajoproducci;
	}

	/** full constructor */
	public Puestotrabajoproduccion(Ajusteproducto ajusteproducto, Puestotrabajo puestotrabajo, Double tmPuestotrabajoproduccion,
			Double hrPuestotrabajoproduccion, Double tmphPuestotrabajoproduccion, Double hrAjustePuestotrabajoproducci,
			Double tmAjustePuestotrabajoproducci, Double tmRealPuestotrabajoproduccion, Double hrRealPuestotrabajoproduccion,
			Double tmphRealPuestotrabajoproducci, Double kcalPuestotrabajoproduccion, Double kcalRealPuestotrabajoproducci) {
		this.ajusteproducto = ajusteproducto;
		this.puestotrabajo = puestotrabajo;
		this.tmPuestotrabajoproduccion = tmPuestotrabajoproduccion;
		this.hrPuestotrabajoproduccion = hrPuestotrabajoproduccion;
		this.tmphPuestotrabajoproduccion = tmphPuestotrabajoproduccion;
		this.hrAjustePuestotrabajoproducci = hrAjustePuestotrabajoproducci;
		this.tmAjustePuestotrabajoproducci = tmAjustePuestotrabajoproducci;
		this.tmRealPuestotrabajoproduccion = tmRealPuestotrabajoproduccion;
		this.hrRealPuestotrabajoproduccion = hrRealPuestotrabajoproduccion;
		this.tmphRealPuestotrabajoproducci = tmphRealPuestotrabajoproducci;
		this.kcalPuestotrabajoproduccion = kcalPuestotrabajoproduccion;
		this.kcalRealPuestotrabajoproducci = kcalRealPuestotrabajoproducci;
	}

	// Property accessors

	public Long getPkCodigoPuestotrabajoproducci() {
		return this.pkCodigoPuestotrabajoproducci;
	}

	public void setPkCodigoPuestotrabajoproducci(Long pkCodigoPuestotrabajoproducci) {
		this.pkCodigoPuestotrabajoproducci = pkCodigoPuestotrabajoproducci;
	}

	public Ajusteproducto getAjusteproducto() {
		return this.ajusteproducto;
	}

	public void setAjusteproducto(Ajusteproducto ajusteproducto) {
		this.ajusteproducto = ajusteproducto;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Double getTmPuestotrabajoproduccion() {
		return this.tmPuestotrabajoproduccion;
	}

	public void setTmPuestotrabajoproduccion(Double tmPuestotrabajoproduccion) {
		this.tmPuestotrabajoproduccion = tmPuestotrabajoproduccion;
	}

	public Double getHrPuestotrabajoproduccion() {
		return this.hrPuestotrabajoproduccion;
	}

	public void setHrPuestotrabajoproduccion(Double hrPuestotrabajoproduccion) {
		this.hrPuestotrabajoproduccion = hrPuestotrabajoproduccion;
	}

	public Double getTmphPuestotrabajoproduccion() {
		return this.tmphPuestotrabajoproduccion;
	}

	public void setTmphPuestotrabajoproduccion(Double tmphPuestotrabajoproduccion) {
		this.tmphPuestotrabajoproduccion = tmphPuestotrabajoproduccion;
	}

	public Double getHrAjustePuestotrabajoproducci() {
		return this.hrAjustePuestotrabajoproducci;
	}

	public void setHrAjustePuestotrabajoproducci(Double hrAjustePuestotrabajoproducci) {
		this.hrAjustePuestotrabajoproducci = hrAjustePuestotrabajoproducci;
	}

	public Double getTmAjustePuestotrabajoproducci() {
		return this.tmAjustePuestotrabajoproducci;
	}

	public void setTmAjustePuestotrabajoproducci(Double tmAjustePuestotrabajoproducci) {
		this.tmAjustePuestotrabajoproducci = tmAjustePuestotrabajoproducci;
	}

	public Double getTmRealPuestotrabajoproduccion() {
		return this.tmRealPuestotrabajoproduccion;
	}

	public void setTmRealPuestotrabajoproduccion(Double tmRealPuestotrabajoproduccion) {
		this.tmRealPuestotrabajoproduccion = tmRealPuestotrabajoproduccion;
	}

	public Double getHrRealPuestotrabajoproduccion() {
		return this.hrRealPuestotrabajoproduccion;
	}

	public void setHrRealPuestotrabajoproduccion(Double hrRealPuestotrabajoproduccion) {
		this.hrRealPuestotrabajoproduccion = hrRealPuestotrabajoproduccion;
	}

	public Double getTmphRealPuestotrabajoproducci() {
		return this.tmphRealPuestotrabajoproducci;
	}

	public void setTmphRealPuestotrabajoproducci(Double tmphRealPuestotrabajoproducci) {
		this.tmphRealPuestotrabajoproducci = tmphRealPuestotrabajoproducci;
	}

	public Double getKcalPuestotrabajoproduccion() {
		return this.kcalPuestotrabajoproduccion;
	}

	public void setKcalPuestotrabajoproduccion(Double kcalPuestotrabajoproduccion) {
		this.kcalPuestotrabajoproduccion = kcalPuestotrabajoproduccion;
	}

	public Double getKcalRealPuestotrabajoproducci() {
		return this.kcalRealPuestotrabajoproducci;
	}

	public void setKcalRealPuestotrabajoproducci(Double kcalRealPuestotrabajoproducci) {
		this.kcalRealPuestotrabajoproducci = kcalRealPuestotrabajoproducci;
	}

	public Set getConsumocomponenteajustes() {
		return consumocomponenteajustes;
	}

	public void setConsumocomponenteajustes(Set consumocomponenteajustes) {
		this.consumocomponenteajustes = consumocomponenteajustes;
	}

	public Set getMovimientosajusteproducto() {
		return movimientosajusteproducto;
	}

	public void setMovimientosajusteproducto(Set movimientosajusteproducto) {
		this.movimientosajusteproducto = movimientosajusteproducto;
	}

	/**
	 * @return the carprodPuestotrabajoproduccion
	 */
	public Double getCarprodPuestotrabajoproduccion() {
		return carprodPuestotrabajoproduccion;
	}

	/**
	 * @param carprodPuestotrabajoproduccion the carprodPuestotrabajoproduccion
	 *            to set
	 */
	public void setCarprodPuestotrabajoproduccion(Double carprodPuestotrabajoproduccion) {
		this.carprodPuestotrabajoproduccion = carprodPuestotrabajoproduccion;
	}

	/**
	 * @return the carcalentPuestotrabajoproduccion
	 */
	public Double getCarcalentPuestotrabajoproduccion() {
		return carcalentPuestotrabajoproduccion;
	}

	/**
	 * @param carcalentPuestotrabajoproduccion the
	 *            carcalentPuestotrabajoproduccion to set
	 */
	public void setCarcalentPuestotrabajoproduccion(Double carcalentPuestotrabajoproduccion) {
		this.carcalentPuestotrabajoproduccion = carcalentPuestotrabajoproduccion;
	}

	/**
	 * @return the carprodRealPuestotrabajoproduccion
	 */
	public Double getCarprodRealPuestotrabajoproduccion() {
		return carprodRealPuestotrabajoproduccion;
	}

	/**
	 * @param carprodRealPuestotrabajoproduccion the
	 *            carprodRealPuestotrabajoproduccion to set
	 */
	public void setCarprodRealPuestotrabajoproduccion(Double carprodRealPuestotrabajoproduccion) {
		this.carprodRealPuestotrabajoproduccion = carprodRealPuestotrabajoproduccion;
	}

	/**
	 * @return the carcalentRealPuestotrabajoproduccion
	 */
	public Double getCarcalentRealPuestotrabajoproduccion() {
		return carcalentRealPuestotrabajoproduccion;
	}

	/**
	 * @param carcalentRealPuestotrabajoproduccion the
	 *            carcalentRealPuestotrabajoproduccion to set
	 */
	public void setCarcalentRealPuestotrabajoproduccion(Double carcalentRealPuestotrabajoproduccion) {
		this.carcalentRealPuestotrabajoproduccion = carcalentRealPuestotrabajoproduccion;
	}

	/**
	 * @return the consumocombustible
	 */
	public Set getConsumocombustible() {
		return consumocombustible;
	}

	/**
	 * @param consumocombustible the consumocombustible to set
	 */
	public void setConsumocombustible(Set consumocombustible) {
		this.consumocombustible = consumocombustible;
	}

	/**
	 * @return the bunkprodPuestotrabajoproduccion
	 */
	public Double getBunkprodPuestotrabajoproduccion() {
		return bunkprodPuestotrabajoproduccion;
	}

	/**
	 * @param bunkprodPuestotrabajoproduccion the
	 *            bunkprodPuestotrabajoproduccion to set
	 */
	public void setBunkprodPuestotrabajoproduccion(Double bunkprodPuestotrabajoproduccion) {
		this.bunkprodPuestotrabajoproduccion = bunkprodPuestotrabajoproduccion;
	}

	/**
	 * @return the bunkcalentPuestotrabajoproduccion
	 */
	public Double getBunkcalentPuestotrabajoproduccion() {
		return bunkcalentPuestotrabajoproduccion;
	}

	/**
	 * @param bunkcalentPuestotrabajoproduccion the
	 *            bunkcalentPuestotrabajoproduccion to set
	 */
	public void setBunkcalentPuestotrabajoproduccion(Double bunkcalentPuestotrabajoproduccion) {
		this.bunkcalentPuestotrabajoproduccion = bunkcalentPuestotrabajoproduccion;
	}

	/**
	 * @return the bunkprodRealPuestotrabajoproduccion
	 */
	public Double getBunkprodRealPuestotrabajoproduccion() {
		return bunkprodRealPuestotrabajoproduccion;
	}

	/**
	 * @param bunkprodRealPuestotrabajoproduccion the
	 *            bunkprodRealPuestotrabajoproduccion to set
	 */
	public void setBunkprodRealPuestotrabajoproduccion(Double bunkprodRealPuestotrabajoproduccion) {
		this.bunkprodRealPuestotrabajoproduccion = bunkprodRealPuestotrabajoproduccion;
	}

	/**
	 * @return the bunkcalentRealPuestotrabajoproduccion
	 */
	public Double getBunkcalentRealPuestotrabajoproduccion() {
		return bunkcalentRealPuestotrabajoproduccion;
	}

	/**
	 * @param bunkcalentRealPuestotrabajoproduccion the
	 *            bunkcalentRealPuestotrabajoproduccion to set
	 */
	public void setBunkcalentRealPuestotrabajoproduccion(Double bunkcalentRealPuestotrabajoproduccion) {
		this.bunkcalentRealPuestotrabajoproduccion = bunkcalentRealPuestotrabajoproduccion;
	}

}