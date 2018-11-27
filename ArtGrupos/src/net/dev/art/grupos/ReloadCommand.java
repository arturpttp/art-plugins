package net.dev.art.grupos;

import org.bukkit.command.CommandSender;

import net.dev.art.core.managers.ArtCommand;

public class ReloadCommand extends ArtCommand {

	public ReloadCommand() {
		super("gruporl");
		addAliases("gruporeload", "artgruporeload", "artgruporl");
		setDescription("Dar reload no plugin ArtTemplate");

	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!hasPermission(sender, "perm.ver.reload")) {
			return false;
		}
		broadcast(
				"§7(" + sender.getName()
						+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!",
				"recive.artreload");
		broadcast("§7(" + sender.getName() + ")§8 » §aRecarregamento completo.", "recive.artreload");
		Main.getInstance().reload();
		return false;
	}

}
