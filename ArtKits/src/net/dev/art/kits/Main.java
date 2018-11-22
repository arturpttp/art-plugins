package net.dev.art.kits;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Entity.Spigot;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import net.dev.art.kits.apis.KitsAPI;
import net.dev.art.kits.objects.Kit;
import net.dev.green.grupos.APIs.GruposAPI.GruposTipos;

public class Main extends JavaPlugin {

	public static Main instance;

	public static String prefix = "§bArtKits§8 » ";

	public static Main getInstance() {
		return instance;
	}

	public static File duvidas;
	public static YamlConfiguration d;

	public static List<Kit> kits;
	public static HashMap<GruposTipos, String> gperms;

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
			gperms = new HashMap<>();
			kits = new ArrayList<>();
		} catch (Exception e) {
			erro("Não foi possivel carregar as variaveis de HashMap e ArrayList");
		}
		carregarGruposPerms();
		lerKits();

	}

	public void carregarGruposPerms() {
		gperms.put(GruposTipos.Dono, "membro");
		gperms.put(GruposTipos.Dono, "diretor");
		gperms.put(GruposTipos.Dono, "dono");
		gperms.put(GruposTipos.Dono, "gerente");
		gperms.put(GruposTipos.Dono, "admin");
		gperms.put(GruposTipos.Dono, "moderador");
		gperms.put(GruposTipos.Dono, "ajudante");
		gperms.put(GruposTipos.Dono, "youtuber");
		gperms.put(GruposTipos.Dono, "miniyt");
		gperms.put(GruposTipos.Dono, "folhagem");
		gperms.put(GruposTipos.Dono, "leaf");
		gperms.put(GruposTipos.Dono, "tree");
		gperms.put(GruposTipos.Dono, "green");

		gperms.put(GruposTipos.Diretor, "membro");
		gperms.put(GruposTipos.Diretor, "diretor");
		gperms.put(GruposTipos.Diretor, "gerente");
		gperms.put(GruposTipos.Diretor, "admin");
		gperms.put(GruposTipos.Diretor, "moderador");
		gperms.put(GruposTipos.Diretor, "ajudante");
		gperms.put(GruposTipos.Diretor, "youtuber");
		gperms.put(GruposTipos.Diretor, "miniyt");
		gperms.put(GruposTipos.Diretor, "folhagem");
		gperms.put(GruposTipos.Diretor, "leaf");
		gperms.put(GruposTipos.Diretor, "tree");
		gperms.put(GruposTipos.Diretor, "green");

		gperms.put(GruposTipos.Gerente, "gerente");
		gperms.put(GruposTipos.Gerente, "admin");
		gperms.put(GruposTipos.Gerente, "moderador");
		gperms.put(GruposTipos.Gerente, "ajudante");
		gperms.put(GruposTipos.Gerente, "youtuber");
		gperms.put(GruposTipos.Gerente, "miniyt");
		gperms.put(GruposTipos.Gerente, "folhagem");
		gperms.put(GruposTipos.Gerente, "leaf");
		gperms.put(GruposTipos.Gerente, "tree");
		gperms.put(GruposTipos.Gerente, "green");
		gperms.put(GruposTipos.Gerente, "membro");

		gperms.put(GruposTipos.Moderador, "admin");
		gperms.put(GruposTipos.Moderador, "moderador");
		gperms.put(GruposTipos.Moderador, "ajudante");
		gperms.put(GruposTipos.Moderador, "youtuber");
		gperms.put(GruposTipos.Moderador, "miniyt");
		gperms.put(GruposTipos.Moderador, "folhagem");
		gperms.put(GruposTipos.Moderador, "leaf");
		gperms.put(GruposTipos.Moderador, "tree");
		gperms.put(GruposTipos.Moderador, "green");
		gperms.put(GruposTipos.Moderador, "membro");

		gperms.put(GruposTipos.Admin, "admin");
		gperms.put(GruposTipos.Admin, "ajudante");
		gperms.put(GruposTipos.Admin, "youtuber");
		gperms.put(GruposTipos.Admin, "miniyt");
		gperms.put(GruposTipos.Admin, "folhagem");
		gperms.put(GruposTipos.Admin, "leaf");
		gperms.put(GruposTipos.Admin, "tree");
		gperms.put(GruposTipos.Admin, "green");
		gperms.put(GruposTipos.Admin, "membro");

		gperms.put(GruposTipos.Ajudante, "ajudante");
		gperms.put(GruposTipos.Ajudante, "youtuber");
		gperms.put(GruposTipos.Ajudante, "miniyt");
		gperms.put(GruposTipos.Ajudante, "folhagem");
		gperms.put(GruposTipos.Ajudante, "leaf");
		gperms.put(GruposTipos.Ajudante, "tree");
		gperms.put(GruposTipos.Ajudante, "green");
		gperms.put(GruposTipos.Ajudante, "membro");

		gperms.put(GruposTipos.Youtube, "youtuber");
		gperms.put(GruposTipos.Youtube, "miniyt");
		gperms.put(GruposTipos.Youtube, "folhagem");
		gperms.put(GruposTipos.Youtube, "leaf");
		gperms.put(GruposTipos.Youtube, "tree");
		gperms.put(GruposTipos.Youtube, "green");
		gperms.put(GruposTipos.Youtube, "membro");

		gperms.put(GruposTipos.Folhagem, "folhagem");
		gperms.put(GruposTipos.Folhagem, "leaf");
		gperms.put(GruposTipos.Folhagem, "miniyt");
		gperms.put(GruposTipos.Folhagem, "tree");
		gperms.put(GruposTipos.Folhagem, "green");
		gperms.put(GruposTipos.Folhagem, "membro");

		gperms.put(GruposTipos.Leaf, "leaf");
		gperms.put(GruposTipos.Leaf, "miniyt");
		gperms.put(GruposTipos.Leaf, "tree");
		gperms.put(GruposTipos.Leaf, "green");
		gperms.put(GruposTipos.Leaf, "membro");

		gperms.put(GruposTipos.MiniYT, "miniyt");
		gperms.put(GruposTipos.MiniYT, "tree");
		gperms.put(GruposTipos.MiniYT, "green");
		gperms.put(GruposTipos.MiniYT, "membro");

		gperms.put(GruposTipos.Tree, "tree");
		gperms.put(GruposTipos.Tree, "green");
		gperms.put(GruposTipos.Tree, "membro");

		gperms.put(GruposTipos.Green, "green");
		gperms.put(GruposTipos.Green, "membro");

		gperms.put(GruposTipos.Membro, "membro");

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
