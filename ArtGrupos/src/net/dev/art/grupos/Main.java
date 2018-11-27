package net.dev.art.grupos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.utils.Configs;
import net.dev.art.core.utils.Mine;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.grupos.api.NamesAPI;
import net.dev.art.grupos.objetos.Grupo;

public class Main extends ArtPlugin implements Listener {

	public static Main instance;
	public Configs gruposCF;
	public Configs config;
	public static List<Grupo> grupos = new ArrayList<>();
	public static HashMap<String, Grupo> gruposPlayers = new HashMap<>();

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		gruposCF = new Configs("grupos.yml", this);
		config = new Configs("config.yml", this);
		autoRegister(instance, "net.dev.art.grupos");
		gruposCF.saveDefaultConfig();
		saveDefaultConfig();
		CarregarGrupos();

		new BukkitRunnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					GruposAPI.setGrupo(p.getName(), GruposAPI.getGrupo(p.getName()));
				}
			}
		}.runTaskTimer(instance, 0, 40);

		for (Player p : Mine.getPlayers()) {
			try {
				GruposAPI.setGrupo(p.getName(), Main.getInstance().config.getString(p.getName()));
			} catch (Exception e2) {
				GruposAPI.setGrupo(p.getName(), new Grupo("§8§lMEMBRO", "Membro", Arrays.asList("membro")));
			}
		}

		new BukkitRunnable() {

			@Override
			public void run() {
				NamesAPI.update();
			}
		}.runTaskTimer(this, 20, 20);

	}

	@Override
	public void aoDisabilitar() {
		int i = 0;
		for (Entry<String, Grupo> map : gruposPlayers.entrySet()) {
			String p = map.getKey();
			Grupo grupo = map.getValue();
			GruposAPI.setGrupo(p, grupo);
			getConfig().set(p, grupo.getName());
			saveConfig();
			i++;
		}

		String msg = i != 0 ? "§eSalvos §b" + i + " §eGrupos de players no arquivo: §e`§bconfig.yml§e`"
				: "§eSem grupos players para salvar na config";

		console(msg);

		for (Player p : Mine.getPlayers()) {
			try {
				GruposAPI.setGrupo(p.getName(), Main.getInstance().config.getString(p.getName()));
			} catch (Exception e2) {
				GruposAPI.setGrupo(p.getName(), new Grupo("§8§lMEMBRO", "Membro", Arrays.asList("membro")));
			}
		}

	}

	@Override
	public void Register() {
	}

	public void reload() {
		debug("§aRecarregando plugin §bArtGrupos");
		aoDisabilitar();

		new BukkitRunnable() {
			@Override
			public void run() {
				aoCarregar();
				aoIniciar();
			}
		}.runTaskTimer(this, 5, 5);
		debug("§aCarregamento do plugin §bArtGrupos§a Completo");
	}

	@Override
	public String getPrefix() {
		return "§bArtGrupos §8» ";
	}

	public void CarregarGrupos() {

		for (String g : gruposCF.getSection("Grupos").getKeys(false)) {

			String prefix = gruposCF.getString("Grupos." + g + ".Prefix").replace("&", "§");
			List<String> permissions = gruposCF.getStringList("Grupos." + g + ".Permissions");

			Grupo grupo = new Grupo(prefix, g, permissions);

			grupos.add(grupo);
			console("§eCarregando Grupo: `" + prefix + "§e`");
		}

	}

}
