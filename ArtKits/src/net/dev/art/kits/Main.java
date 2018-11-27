package net.dev.art.kits;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.kits.apis.KitsAPI;
import net.dev.art.kits.objects.Kit;

public class Main extends JavaPlugin {

	public static Main instance;

	public static String prefix = "§bArtKits§8 » ";

	public static Main getInstance() {
		return instance;
	}

	public static File duvidas;
	public static YamlConfiguration d;

	public static List<Kit> kits;

	public static void saveKits() {
		try {
			d.save(duvidas);
		} catch (IOException e) {
			erro("Não foi possivel salvar a config KITS");
		}
	}

	@Override
	public void onEnable() {
		duvidas = new File(getDataFolder(), "kits.yml");
		if (!duvidas.exists()) {
			saveResource("kits.yml", false);
			mensagem("§eCriando `§bkits.yml§e`");
		}
		duvidas = new File(getDataFolder(), "kits.yml");
		d = YamlConfiguration.loadConfiguration(duvidas);
		mensagem("§eCarregando `§bkits.yml§e`");
		Register();
		try {
			kits = new ArrayList<>();
		} catch (Exception e) {
			erro("Não foi possivel carregar as variaveis de HashMap e ArrayList");
		}
		lerKits();

	}

	@Override
	public void onDisable() {

	}

	public void Register() {
		getCommand("kit").setExecutor(new net.dev.art.kits.commands.Kit());
	}

	@SuppressWarnings("all")
	public void lerKits() {
		this.kits.clear();
		for (String kit : d.getConfigurationSection("Kits").getKeys(false)) {
			int delay = d.getInt("Kits." + kit + ".Tempo");
			String grupo = d.getString("Kits." + kit + ".Grupo");
//			String displayname = d.getString("Kits." + kit + ".DisplayName").replace("&", "§");

			List<ItemStack> dados = new ArrayList();
			for (String item : d.getStringList("Kits." + kit + ".Itens")) {
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
					String n;
					Enchantment enc;
					if (contemencantamentos) {
						if (encantamentos.contains("-")) {
							String[] arrayOfString;
							int j = (arrayOfString = encantamentos.split("-")).length;
							for (int i = 0; i < j; i++) {
								String en = arrayOfString[i];
								n = en.split(":")[0];
								int level = Integer.parseInt(en.split(":")[1]);
								enc = KitsAPI.traduzirEncantamento(n);
								if (enc != null) {
									im.addEnchant(enc, level, true);
								}
							}
						} else {
							n = encantamentos.split(":")[0];
							int level = Integer.parseInt(encantamentos.split(":")[1]);
							enc = KitsAPI.traduzirEncantamento(n);
							if (enc != null) {
								im.addEnchant(enc, level, true);
							}
						}
					}
					if (contemlore) {
						ArrayList<String> itemlore = new ArrayList();
						for (String lores : lore.split(":")) {
							itemlore.add(lores);
						}
						im.setLore(itemlore);
					}
					is.setItemMeta(im);
					dados.add(is);
				} catch (Exception e) {
					erro("Ocorreu um erro na linha: " + item);
					Bukkit.getPluginManager().disablePlugin(instance);
					return;
				}
			}
			Kit k = new Kit(kit, delay, dados, grupo);
			try {
				this.kits.add(k);
			} catch (Exception e) {
				erro("Erro Ao Adicionar Os Kits a Lista");
			}

			mensagem("Carregado kit : '§b" + k.getName() + "§e'");
		}
	}

	public static void erro(String string) {
		Bukkit.getConsoleSender().sendMessage("§bArtKits§8 » §c" + string);
	}

	public static void mensagem(String string) {
		Bukkit.getConsoleSender().sendMessage("§bArtKits§8 » §e" + string);
	}

}
