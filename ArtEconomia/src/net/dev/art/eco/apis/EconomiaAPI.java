package net.dev.art.eco.apis;

import net.dev.art.core.utils.ArtLib;

public class EconomiaAPI implements ArtLib {

	public static String getInvalidArgs(String arg, String cmd) {
		return "§cAgurmento Invalido `§r" + arg + "§c` Digite §c/" + cmd + " help";
	}

}
