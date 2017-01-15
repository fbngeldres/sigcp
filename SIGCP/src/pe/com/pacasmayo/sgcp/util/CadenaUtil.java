package pe.com.pacasmayo.sgcp.util;

public class CadenaUtil {

	public static String removerCaracteresEspeciales(String cadenaInput) {
		// Cadena de caracteres original a sustituir.
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		// Cadena de caracteres ASCII que reemplazarán los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String cadenaOutput = cadenaInput;
		for (int i = 0; i < original.length(); i++) {
			// Reemplazamos los caracteres especiales.
			cadenaOutput = cadenaOutput.replace(original.charAt(i), ascii.charAt(i));
		}
		return cadenaOutput;
	}

}
