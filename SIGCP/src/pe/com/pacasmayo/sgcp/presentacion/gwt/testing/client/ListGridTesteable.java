package pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client;

import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;

public class ListGridTesteable extends ListGrid implements Testeable {

	// json
	private static final String PREFIJO_LISTA_JSON = "[\n";
	private static final String SEPARADOR_LISTA_JSON = ",\n";
	private static final String POSFIJO_LISTA_JSON = "\n]";
	private static final String PREFIJO_JSON = "{\n";
	private static final String SEPARADOR_JSON = ",\n";
	private static final String POSTFIJO_JSON = "\n}";

	// csv
	private static final String PREFIJO_LISTA_CSV = "";
	private static final String SEPARADOR_LISTA_CSV = "";
	private static final String POSFIJO_LISTA_CSV = "";
	private static final String PREFIJO_CSV = "\n";
	private static final String SEPARADOR_CSV = ",";
	private static final String POSTFIJO_CSV = "";
	private static final int VALUES_ONLY = 1;
	private static final int TITLES_ONLY = 2;
	private static final int BOTH = 3;

	public String exportarComoJson() {
		return exportarListGridJson(this.getRecords());
	}

	public String exportarComoCsv() {
		return exportarListGridCsv(this.getRecords());
	}

	private static String exportarListGridJson(Record[] records) {
		return exportarListGrid(records, PREFIJO_LISTA_JSON, SEPARADOR_LISTA_JSON, POSFIJO_LISTA_JSON, PREFIJO_JSON,
				SEPARADOR_JSON, POSTFIJO_JSON, false, BOTH);
	}

	private static String exportarListGridCsv(Record[] records) {
		return exportarListGrid(records, PREFIJO_LISTA_CSV, SEPARADOR_LISTA_CSV, POSFIJO_LISTA_CSV, PREFIJO_CSV, SEPARADOR_CSV,
				POSTFIJO_CSV, true, VALUES_ONLY);
	}

	private static String exportarListGrid(Record[] records, String prefijoLista, String separadorLista, String postFijoLista,
			String prefijoElem, String separadorElem, String postFijoElem, boolean titleHeader, int recordMode) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(prefijoLista);

		// HACK: hizo falta tomar los properties del primer record como guía,
		// porque los demas records pueden reportarlos en desorden con respecto
		// al header!.
		String[] properties = null;
		boolean primero = true;
		for (Record record : records) {
			if (!primero) {
				buffer.append(separadorLista);
			} else {
				primero = false;
				properties = record.getAttributes();
				if (titleHeader) {
					buffer.append(serializeRecord(record, properties, prefijoElem, separadorElem, postFijoElem, TITLES_ONLY));
				}
			}
			buffer.append(serializeRecord(record, properties, prefijoElem, separadorElem, postFijoElem, recordMode));
		}
		buffer.append(postFijoLista);
		return buffer.toString();
	}

	private static String serializeRecord(Record record, String[] properties, String prefijo, String separador, String postfijo,
			int mode) {
		final String DBLQUOTE = "\"";
		final String INDENT = "  ";
		StringBuffer buffer = new StringBuffer();
		buffer.append(prefijo);
		boolean primero = true;
		for (String property : properties) {
			if (property.startsWith("_")) {
				// saltar propiedad especial
				continue;
			}
			// Desde gwt no hay acceso a StringUtils, que ya hace esto
			if (!primero) {
				buffer.append(separador);
			} else {
				primero = false;
			}
			switch (mode) {
			case VALUES_ONLY:
				buffer.append(DBLQUOTE + format(record.getAttribute(property)) + DBLQUOTE);
				break;
			case TITLES_ONLY:
				buffer.append(DBLQUOTE + format(property) + DBLQUOTE);
				break;
			case BOTH:
				buffer.append(INDENT + DBLQUOTE + format(property) + DBLQUOTE + ":" + DBLQUOTE
						+ format(record.getAttribute(property)) + DBLQUOTE);
				break;
			}
		}
		buffer.append(postfijo);
		return buffer.toString();
	}

	public static String format(Object value) {
		if (value == null) {
			return "<null>";
		}
		try {
			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			// HACK: cambio de punto por coma
			return nf.format(((Number) value).doubleValue()).replace(".", ",");
		} catch (Exception e) {
			// HACK: cualquier otra cosa directo a string con comas forzadas
			return value.toString().replace(".", ",");
		}
	}
}
