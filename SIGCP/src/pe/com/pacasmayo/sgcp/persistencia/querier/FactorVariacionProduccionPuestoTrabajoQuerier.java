package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.Query;

public class FactorVariacionProduccionPuestoTrabajoQuerier extends Querier {

	public static void eliminarPorCodigoProductoGenerado(List<Long> codigos) {
		StringBuilder querystr = new StringBuilder("DELETE  FROM  Factorvariacionproduccionpuestotrabajo fvpt ");
		querystr.append(" WHERE fvpt.productogenerado.pkCodigoProductogenerado in(:listaCodigos) ");
		Query query = Querier.query(querystr.toString());
		query.setParameterList("listaCodigos", codigos);

		query.executeUpdate();

	}

}
