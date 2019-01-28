package net.dev.art.aut2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.aut2.objetos.Conta;
import net.dev.art.core.ArtPlugin;
import net.dev.art.core.utils.Configs;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

    public static List<String> logados=new ArrayList<>();
	public ArrayList<String> weiting = new ArrayList<>();
    public HashMap<String,Conta> contas= new HashMap<>();
    public static List<String> getLogados() {
        return logados;
    }

    @Override
	public void aoCarregar() {

    }

    public Configs config = new Configs("config.yml", this);

	@Override
	public void aoIniciar() {
		instance = this;
		autoRegister(instance, "net.dev.art.aut2");
		saveDefaultConfig();
		carregarContas();

	}

	public void carregarContas(){
        config.getConfig().getConfigurationSection("Contas").getKeys(false).forEach(a -> {
            String senha = config.getString("Contas." + a + ".Senha");
            String ip = config.getString("Contas." + a + ".IP");
            contas.put(a, new Conta(a, senha, ip));
        });
    }

	@Override
	public void aoDisabilitar() {
        Conta.getAll().forEach(a->a.save());
        config.saveConfig();
	}

	@Override
	public void Register() {
	}

	public void reload() {
		debug("§aRecarregando plugin §bArtAutenticador");
		aoDisabilitar();

		new BukkitRunnable() {
			@Override
			public void run() {
				aoCarregar();
				aoIniciar();
			}
		}.runTaskTimer(this, 5, 5);
		debug("§aCarregamento do plugin §bArtAutenticador§a Completo");
	}

	@Override
	public String getPrefix() {
		return "§bArtAutenticador §8 » ";
	}

}
