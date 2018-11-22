package net.dev.art.trab;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.ArtItem;
import net.dev.art.core.ArtPlugin;
import net.dev.art.core.ItemBuilder;

public class TItem {

	public static ArtItem Minerador;
	public static ArtItem Assassino;
	public static ArtItem Caçador;
	public static ArtItem Lenhador;
	public static ArtItem Escavador;
	public static ArtItem Fazendeiro;
	public static ArtItem Policial;
	public static ArtItem Empresario;
	public static ArtItem Demição;

	protected String[] getLore(String salario, String descrição) {
		String[] lore = { "§eInformações: ", "§8» §bSalario: §aR$§f" + salario, "", "§eDescrição: ",
				"§8» §b" + descrição };
		return lore;
	}

	public TItem(ArtPlugin pl) {
		String[] MinerLore = getLore("35.000", "Ao quebrar 1000 blocos você ganha seu salario!");
		String[] ChopperLore = getLore("38.000", "Ao quebrar 750 madeiras você ganha seu salario!");
		String[] DiggerLore = getLore("50.000", "Ao quebrar 1300 blocos você ganha seu salario!");
		String[] FarmerLore = getLore("63.000", "Ao colhetar 300 items plantados você ganha seu salario!");
		String[] AssaLore = { "§eInformações: ", "§8» §bSalario: §aR$§f97.000", "", "§eDescrição: ",
				"§8» §b" + "Ao assassinar 100 vitimas você recebera seu salario!", "", "§eObservação:",
				"§8» §fse um policial te matar você perde de 1 a 5 abates!" };
		String[] HunterLore = getLore("74.000", "Ao matar 4000 mobs você ganha seu salario!");
		String[] OffierLore = { "§eInformações: ", "§8» §bSalario: §aR$§f200.000", "", "§eDescrição: ",
				"§8» §b" + "Ao capturar/matar 80 vitimas você recebera seu salario!", "", "§eObservação:",
				"§8» §fseus abates so contaram se a vitima for um assassino!" };
		String[] EmpreLore = getLore("250.000", "Tenha escravos... A cada 2 Horas você ganha seu salario!");

		Minerador = new ArtItem(Material.DIAMOND_PICKAXE, 1, (short) 0).nome("§aMinerador").lore(MinerLore);
		Assassino = new ArtItem(Material.DIAMOND_SWORD, 1, (short) 0).nome("§aAssassino").lore(AssaLore);
		Caçador = new ArtItem(Material.GOLD_SWORD, 1, (short) 0).nome("§aCaçador").lore(HunterLore);
		Lenhador = new ArtItem(Material.DIAMOND_AXE, 1, (short) 0).nome("§aLenhador").lore(ChopperLore);
		Escavador = new ArtItem(Material.DIAMOND_SPADE, 1, (short) 0).nome("§aEscavador").lore(DiggerLore);
		Fazendeiro = new ArtItem(Material.DIAMOND_HOE, 1, (short) 0).nome("§aFazendeiro").lore(FarmerLore);
		Policial = new ArtItem(Material.IRON_FENCE, 1, (short) 0).nome("§aPolicial").lore(OffierLore);
		Empresario = new ArtItem(Material.CHEST, 1, (short) 0).nome("§aEmpresario").lore(EmpreLore);
		Demição = new ArtItem(Material.STAINED_GLASS_PANE, 1, (short) 14).nome("§cPedir Demição");
	}

}
