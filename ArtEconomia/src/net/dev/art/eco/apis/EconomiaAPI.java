package net.dev.art.eco.apis;

import net.dev.art.eco.utils.Mensagens;

public class EconomiaAPI extends Mensagens {

	public static String getInvalidArgs(String arg, String cmd) {
		return "§cAgurmento Invalido `§r" + arg + "§c` Digite §c/" + cmd + " help";
	}

}
