package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocomponenteajuste;

public class TipoComponenteAjusteQuerier extends Querier {
	public static final String NOMBRE_TIPO_COMPONENTE_AJUSTE = "nombreTipoComponenteAjuste";

	public static Tipocomponenteajuste getbyNombre(String nombre) {
		List<Tipocomponenteajuste> listaComponentes = getByLikeStringPropertie(Tipocomponenteajuste.class,
				NOMBRE_TIPO_COMPONENTE_AJUSTE, nombre);
		if (listaComponentes != null && listaComponentes.size() > 0) {
			return listaComponentes.get(0);
		}
		return null;
	}

}
