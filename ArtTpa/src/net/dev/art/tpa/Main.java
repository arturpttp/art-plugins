package net.dev.art.tpa;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}
	private Map<UUID, UUID> tpa;
    private Map<UUID, Long> cooldown;
	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	@Override
	public void onEnable() {
		instance = this;

		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");
		getServer().getPluginManager().registerEvents(this, this);
		Register();
		  new CooldownCleaner().start();
	        
	        tpa = new HashMap<>();
	        cooldown = new HashMap<>();
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {
		tpa = null;
        cooldown = null;
        instance = null;
        
        HandlerList.unregisterAll((JavaPlugin) this);
	}

	 @Override
	    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	        if (!(sender instanceof Player)) return true;
	        Player p = (Player) sender;
	        
	        if (args.length < 1) {
	            p.sendMessage("§cUse /tpa <jogador>.");
	            return true;
	        }
	        
	        UUID pid = p.getUniqueId();
	        
	        if (args[0].equalsIgnoreCase("aceitar")) {
	            UUID tid = tpa.remove(pid);
	            if (tid == null) {
	                p.sendMessage("§cVocê não recebeu nenhuma solicitação de tpa.");
	                return true;
	            }
	            
	            Player t = Bukkit.getPlayer(tid);
	            if (t == null) {
	                p.sendMessage("§cEste jogador está offline!");
	                return true;
	            }
	            
	            t.teleport(p.getLocation());
	            t.sendMessage("§a" + p.getName() + " aceitou seu pedido de teleporte.");
	            p.sendMessage("§aVocê aceitou o pedido de teleporte de " + t.getName() + ".");
	            return true;
	        } else if (args[0].equalsIgnoreCase("negar")) {
	            UUID tid = tpa.remove(pid);
	            if (tid == null) {
	                p.sendMessage("§cVocê não recebeu nenhuma solicitação de tpa.");
	            	return true;
	            }
	            
	            Player t = Bukkit.getPlayer(tid);
	            if (t == null) return true;
	            
	            t.sendMessage("§c" + p.getName() + " negou seu pedido de teleporte.");
	            p.sendMessage("§cVocê negou o pedido de teleporte de " + t.getName() + ".");
	            return true;
	        }
	        
	        Long ptime = cooldown.get(pid);
	        
	        if (ptime != null) {
	            int falta = (int) (ptime - System.currentTimeMillis()) / 1000;
	            if (falta > 0) {
	                p.sendMessage("§cVocê deve esperar §f" + falta + "s §cpara mandar outro pedido de teleporte.");
	                return true;
	            } else {
	                cooldown.remove(pid);
	            }
	        }
	        
	        Player t = Bukkit.getPlayer(args[0]);
	        if (t == null) {
	        	p.sendMessage("§cEste jogador está offline!");
	        	return true;
	        }
	        if (t.getUniqueId().equals(pid)) {
	        	p.sendMessage("§cEste jogador é você.");
	        	return true;
	        }
	        tpa.put(t.getUniqueId(), pid);
	        cooldown.put(pid, System.currentTimeMillis() + 15000);
	        
	        p.sendMessage("§aVocê enviou um pedido de teleporte para " + t.getName() + ".");
	        
	        t.sendMessage(" ");
	        t.sendMessage("§e" + p.getName() + " enviou um pedido de teleporte a você.");
	        t.spigot().sendMessage(getAceitar());
	        t.spigot().sendMessage(getNegar());
	        t.sendMessage(" ");
	        return false;
	    }
	    
	    @EventHandler(priority = EventPriority.HIGHEST)
	    public void onPlayerQuit(PlayerQuitEvent e) {
	        tpa.remove(e.getPlayer().getUniqueId());
	        cooldown.remove(e.getPlayer().getUniqueId());
	    }

	    public Map<UUID, Long> getCooldown() {
	        return cooldown;
	    }
	    
	    private TextComponent getAceitar() {
	        TextComponent text = new TextComponent("CLIQUE AQUI PARA ACEITAR!");
	        text.setColor(ChatColor.GREEN);
	        text.setBold(true);
	        
	        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] { new TextComponent("§aClique aqui para aceitar!") }));
	        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa aceitar"));
	        
	        return text;
	    }
	    
	    private TextComponent getNegar() {
	        TextComponent text = new TextComponent("CLIQUE AQUI PARA NEGAR!");
	        text.setColor(ChatColor.RED);
	        text.setBold(true);
	        
	        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] { new TextComponent("§cClique aqui para negar!") }));
	        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa negar"));
	        
	        return text;
	    }
	
}
