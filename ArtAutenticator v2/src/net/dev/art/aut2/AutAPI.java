package net.dev.art.aut2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AutAPI {

	private static List<String> logados = new ArrayList<>();

	public static void logar(String p) {
		getLogados().add(p);
	}

	public static void desLogar(String p) {
		getLogados().add(p);
	}


	public static List<String> getLogados() {
		return logados;
	}

	public static void setLogados(List<String> logados) {
		AutAPI.logados = logados;
	}

}
