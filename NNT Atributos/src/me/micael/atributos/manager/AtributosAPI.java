package me.micael.atributos.manager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.micael.atributos.Main;

public class AtributosAPI {

	public static boolean tem_conta(String nome) {
		return Main.db.contains("Atributos", "nome = ?", nome);
	}

	public static void criar_conta(String nome, Integer forca, Integer vida, Integer defesa) {
		Main.defesa.put(nome, defesa);
		Main.vida.put(nome, vida);
		Main.forca.put(nome, forca);
		Main.db.insert("Atributos", nome, 500, 500, 500);
	}

	public static void deletar_conta(String nome) {
		Main.db.delete("Atributos", "nome = ?", nome);
	}

	public static int pegar_forca(String forca) {
		return Main.forca.get(forca);
	}

	public static int pegar_vida(String vida) {
		return Main.forca.get(vida);
	}

	public static int pegar_defesa(String defesa) {

		return Main.forca.get(defesa);
	}

	public static void setaratributos(String nome, Integer forca, Integer vida, Integer defesa) {
		deletar_conta(nome);
		Main.db.insert("Atributos", new Object[] { nome, forca, vida, defesa });
	}

	public static void adicionaratributos(String nome, Integer forca, Integer vida, Integer defesa) {
		deletar_conta(nome);
		Main.db.insert("Atributos", new Object[] { nome, forca, vida, defesa });
	}

}
