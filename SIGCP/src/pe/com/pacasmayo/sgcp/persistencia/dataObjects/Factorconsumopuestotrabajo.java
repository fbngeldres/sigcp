package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Factorconsumopuestotrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Factorconsumopuestotrabajo implements java.io.Serializable {

	// Fields

	// de aqui consulto valor del dia anterior
	private Long pkCodigoFactorconsumopuestotrabajo;
	private Consumopuestotrabajo fkCodigoConsumoPuestoTrabajo;
	private Equivalenciasccvarcalidad fkCodigoEquivalenciasccvarcalidad;
	private Double valorFactorconsumopuestotrabajo;

	// Constructors

	/** default constructor */
	public Factorconsumopuestotrabajo() {
	}

	/** full constructor */
	public Factorconsumopuestotrabajo(Consumopuestotrabajo fkCodigoConsumoPuestoTrabajo,
			Equivalenciasccvarcalidad fkCodigoEquivalenciasccvarcalidad, Double valorFactorconsumopuestotrabajo) {
		this.fkCodigoConsumoPuestoTrabajo = fkCodigoConsumoPuestoTrabajo;
		this.fkCodigoEquivalenciasccvarcalidad = fkCodigoEquivalenciasccvarcalidad;
		this.valorFactorconsumopuestotrabajo = valorFactorconsumopuestotrabajo;
	}

	// Property accessors

	public Long getPkCodigoFactorconsumopuestotrabajo() {
		return this.pkCodigoFactorconsumopuestotrabajo;
	}

	public void setPkCodigoFactorconsumopuestotrabajo(Long pkCodigoFactorconsumopuestotrabajo) {
		this.pkCodigoFactorconsumopuestotrabajo = pkCodigoFactorconsumopuestotrabajo;
	}

	public Consumopuestotrabajo getFkCodigoConsumoPuestoTrabajo() {
		return this.fkCodigoConsumoPuestoTrabajo;
	}

	public void setFkCodigoConsumoPuestoTrabajo(Consumopuestotrabajo fkCodigoConsumoPuestoTrabajo) {
		this.fkCodigoConsumoPuestoTrabajo = fkCodigoConsumoPuestoTrabajo;
	}

	public Equivalenciasccvarcalidad getFkCodigoEquivalenciasccvarcalidad() {
		return this.fkCodigoEquivalenciasccvarcalidad;
	}

	public void setFkCodigoEquivalenciasccvarcalidad(Equivalenciasccvarcalidad fkCodigoEquivalenciasccvarcalidad) {
		this.fkCodigoEquivalenciasccvarcalidad = fkCodigoEquivalenciasccvarcalidad;
	}

	public Double getValorFactorconsumopuestotrabajo() {
		return this.valorFactorconsumopuestotrabajo;
	}

	public void setValorFactorconsumopuestotrabajo(Double valorFactorconsumopuestotrabajo) {
		this.valorFactorconsumopuestotrabajo = valorFactorconsumopuestotrabajo;
	}

}