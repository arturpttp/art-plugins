package net.dev.art.core.objects;

import net.md_5.bungee.api.ChatColor;

public class ProgressBar {

	double max;
	double haved;
	String character = "▎";
	String HasColor = "§a";
	String NeedColor = "§c";
	int totalBars = 10;

	public double getHaved() {
		return haved;
	}

	public double getMax() {
		return max;
	}

	public ProgressBar(double tem, double maximo) {
		this.max = maximo;
		this.haved = tem;
	}

	public String getPorcetagem() {
		int pct = (int) ((int) haved / max);
		String color = "§c";
		if (pct > 100) {
			color = "§a";
			pct = 100;
		} else if (pct > 50) {
			color = "§e";
		} else if (pct < 50) {
			color = "§c";
		}
		return color + pct + "%";
	}

	public String getBar() {
		double porcento = (float) haved / max;
		int progressBars = (int) ((int) totalBars * porcento);
		int falta = (totalBars - progressBars);
		StringBuilder sb = new StringBuilder();
		sb.append(HasColor);
		for (int i = 0; i < progressBars; i++) {
			sb.append("▎");
		}
		sb.append(NeedColor);
		for (int i = 0; i < falta; i++) {
			sb.append("▎");
		}
		return sb.toString();

	}

	public String getProgressBar() {
		int n = (int) getHaved();
		if (n >= getMax()) {
			return ChatColor.translateAlternateColorCodes('&', "§a/rankup");
		}
		int n2 = 0;
		if (n > 0) {
			n2 = (int) (n / (getMax() / 10));
		}
		final StringBuilder sb = new StringBuilder(String.valueOf("§c") + "");
		if (n2 > 0) {
			sb.append("§a");
		}
		for (int i = 0; i < 10; ++i) {
			if (i == n2) {
				sb.append("§c");
			}
			sb.append("▎");
		}
		sb.append(String.valueOf("") + "");
		return ChatColor.translateAlternateColorCodes('&', sb.toString());
	}

}
