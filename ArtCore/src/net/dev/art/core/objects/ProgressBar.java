package net.dev.art.core.objects;

import net.md_5.bungee.api.ChatColor;

public class ProgressBar {

	double max;
	double haved;
	String character = "|";
	String HasColor = "§a";
	String NeedColor = "§c";
	int totalBars=10;

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
			sb.append("|");
		}
		sb.append(NeedColor);
		for (int i = 0; i < falta; i++) {
			sb.append("|");
		}
		return sb.toString();

	}

}
