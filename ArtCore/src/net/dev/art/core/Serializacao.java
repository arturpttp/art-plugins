package net.dev.art.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Serializacao {
	/*
	 * ArrayList<Fruta> frutas = new ArrayList<Fruta>(); Fruta f1 = new
	 * Fruta("Laranja", "Amarela"); Fruta f2 = new Fruta("Abacate", "Verde"); Fruta
	 * f3 = new Fruta("Morango", "Vermelho"); frutas.add(f1); frutas.add(f2);
	 * frutas.add(f3);
	 * 
	 * try { s.serializar("/home/gustavo/frutas", frutas); frutas = null; frutas =
	 * (ArrayList<Fruta>) d.deserializar("/home/gustavo/frutas"); for(Fruta f :
	 * frutas){ System.out.println("ArrayList: " + f.getNome() + " - " +
	 * f.getCor()); } } catch (Exception ex) {
	 * System.err.println("Falha ao serializar ou deserializar! - " +
	 * ex.toString()); }
	 */

	public static Serializador Serializador() {
		return new Serializador();
	}

	public static Deserializador Deserializador() {
		return new Deserializador();
	}

	public static class Serializador {

		public void serializar(String path, Object obj) throws Exception {
			FileOutputStream outFile = new FileOutputStream(path);
			ObjectOutputStream s = new ObjectOutputStream(outFile);
			s.writeObject(obj);
			s.close();
		}

	}

	public static class Deserializador {

		public Object deserializar(String path) throws Exception {
			FileInputStream inFile = new FileInputStream(path);
			ObjectInputStream d = new ObjectInputStream(inFile);
			Object o = d.readObject();
			d.close();
			return o;
		}

	}

}