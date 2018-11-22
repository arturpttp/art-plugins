package net.dev.art.essentials.apis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import net.dev.art.essentials.objetos.Duvida;

public class DuvidasAPI {

	public static List<Duvida> duvidas = new ArrayList<>();
	
	public static String gerarID() {
		return ThreadLocalRandom.current().nextInt(100000, 999999)+"";
	}
	
	public static List<Duvida> getDuvidas() {
		return duvidas;
	}
	
}
