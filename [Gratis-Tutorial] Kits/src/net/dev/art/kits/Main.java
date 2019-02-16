package net.dev.art.kits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.dev.art.kits.objetos.ArtItem;
import net.dev.art.kits.objetos.Kit;
import net.dev.art.tuto.ArtPlugin;
import net.dev.art.tuto.utils.Configs;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public List<Kit> kits = new ArrayList<>();

	public Configs kitscf = new Configs("kits.yml", this);

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoAbilitar() {
		instance = this;
		autoRegister(instance, "net.dev.art.kits");
		kitscf.saveDefaultConfig();
		loadKits();
	}

	private void loadKits() {
//		/fakecommand

		ItemStack[] lista1 = (ItemStack[]) Arrays.asList(new ArtItem(Material.EMERALD).nome("§2ESMERALDA DA SORTE"),
				new ArtItem(Material.DIAMOND).nome("§bDIAMATE DA SORTE")).toArray();

		new Kit("Batata", 10000, lista1, "", new ArtItem(Material.DIAMOND).nome("§bDIAMATE DA SORTE"));

	}

	@Override
	public void aoDesabilitar() {
		saveKits();
	}

	private void saveKits() {

	}

	@Override
	public void register() {

	}

	@Override
	public String getPrefix() {
		return "§bArtKits§8 » ";
	}

}
