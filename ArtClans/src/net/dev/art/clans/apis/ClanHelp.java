package net.dev.art.clans.apis;

import org.bukkit.entity.Player;

public class ClanHelp {

	public static void sendHelp(Player p) {

	}

	public static enum Help {
		criar, sair, banco, expulsar, kickar, info, promover, rebaixar, avisos, base, setbase, convidar, top, listar,
		aceitar, recusar;

		public String getComando() {
			switch (this) {
			case aceitar:
				return "/clan " + this.toString() + "";
			case avisos:
				return "/clan " + this.toString();
			case banco:
				return "/clan " + this.toString();
			case base:
				return "/clan " + this.toString();
			case convidar:
				return "/clan " + this.toString();
			case criar:
				return "/clan " + this.toString();
			case expulsar:
				return "/clan " + this.toString();
			case info:
				return "/clan " + this.toString();
			case kickar:
				return "/clan " + this.toString();
			case listar:
				return "/clan " + this.toString();
			case promover:
				return "/clan " + this.toString();
			case rebaixar:
				return "/clan " + this.toString();
			case recusar:
				return "/clan " + this.toString();
			case sair:
				return "/clan " + this.toString();
			case setbase:
				return "/clan " + this.toString();
			case top:
				return "/clan " + this.toString();
			default:
				return "/clan " + this.toString();

			}
		}

		public String getDescrição() {
			switch (this) {
			case aceitar:
				return "aceitar entrar para um clan";
			case avisos:
				return "";
			case banco:
				return "";
			case base:
				return "";
			case convidar:
				return "";
			case criar:
				return "";
			case expulsar:
				return "";
			case info:
				return "";
			case kickar:
				return "";
			case listar:
				return "";
			case promover:
				return "";
			case rebaixar:
				return "";
			case recusar:
				return "";
			case sair:
				return "";
			case setbase:
				return "";
			case top:
				return "";
			default:
				return "";

			}
		}

	}

}
