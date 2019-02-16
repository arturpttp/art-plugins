package net.dev.art.maquinas;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.ArtItem;

public class MaquinaCommand extends ArtCommand {

	public MaquinaCommand() {
		super("maquinas");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (isPlayer(sender)) {
			return true;
		}
		Player p = (Player) sender;
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.getInventory().addItem(new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		p.updateInventory();
		return false;
	}

}
