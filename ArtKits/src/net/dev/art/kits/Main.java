package net.dev.art.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.core.ArtPlugin;
import net.dev.art.kits.objects.Kit;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public List<Kit> kits = new ArrayList<>();

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		autoRegister(instance, "net.dev.art.kits");
		saveDefaultConfig();
		carrgarKits();
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
	}

	@Override
	public String getPrefix() {
		return "§bArtKits §8» ";
	}

	public void carrgarKits() {
		this.kits.clear();
		for (String kits : this.getConfig().getConfigurationSection("Kits").getKeys(false)) {
			long delay = this.getConfig().getLong("Kits." + kits + ".Delay");
			String group = this.getConfig().getString("Kits." + kits + ".Grupo");
			List<ItemStack> dados = new ArrayList<>();
			for (String item : this.getConfig().getStringList("Kits." + kits + ".Itens")) {
				try {
					String itemid = item.split(";")[0].replace("&", "§");
					String nome = item.split(";")[1].replace("&", "§");
					String encantamentos = item.split(";")[2].replace("&", "§");
					String quantidade = item.split(";")[3].replace("&", "§");
					String lore = item.split(";")[4].replace("&", "§");
					boolean contemnome = !nome.equalsIgnoreCase("null");
					boolean contemencantamentos = !encantamentos.equalsIgnoreCase("null");
					boolean contemlore = !lore.equalsIgnoreCase("null");
					int data = 0;
					if (itemid.contains(":")) {
						data = Integer.parseInt(itemid.split(":")[1]);
						itemid = itemid.split(":")[0];
					}
					ItemStack is = new ItemStack(Material.getMaterial(Integer.parseInt(itemid)),
							Integer.parseInt(quantidade), (short) data);
					ItemMeta im = is.getItemMeta();
					if (contemnome) {
						im.setDisplayName(nome);
					}
					if (contemencantamentos) {
						if (encantamentos.contains("-")) {
							String[] split;
							for (int length = (split = encantamentos.split("-")).length, j = 0; j < length; ++j) {
								String en = split[j];
								String n = en.split(":")[0];
								int level = Integer.parseInt(en.split(":")[1]);
								Enchantment enc = KitsAPI.traduzirEncantamento(n);
								if (enc != null) {
									im.addEnchant(enc, level, true);
								}
							}
						} else {
							String n2 = encantamentos.split(":")[0];
							int level2 = Integer.parseInt(encantamentos.split(":")[1]);
							Enchantment enc2 = KitsAPI.traduzirEncantamento(n2);
							if (enc2 != null) {
								im.addEnchant(enc2, level2, true);
							}
						}
					}
					if (contemlore) {
						ArrayList<String> itemlore = new ArrayList<String>();
						String[] split2;
						for (int length2 = (split2 = lore.split(":")).length, k = 0; k < length2; ++k) {
							String lores = split2[k];
							itemlore.add(lores);
						}
						im.setLore(itemlore);
					}
					is.setItemMeta(im);
					dados.add(is);
				} catch (Exception e) {
					console(" ");
					console("§4 »§c UM ERRO OCORREU ");
					console("§4 »§c Causa:§f " + e.getClass().getSimpleName());
					console("§4 »§c Classe:§f " + e.getStackTrace()[0].getClassName());
					console("§4 »§c Linha:§f " + e.getStackTrace()[0].getLineNumber());
					console("§4 »§c ");
					console(" ");
					Bukkit.getPluginManager().disablePlugin(getPlugin());
					return;
				}
			}
			Kit k = new Kit(kits, group, dados, delay);
			this.kits.add(k);
			console("§eCarregando kit: '" + k.getName() + "'");
		}
	}

}
