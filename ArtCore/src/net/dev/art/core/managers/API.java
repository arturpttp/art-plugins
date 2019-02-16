package net.dev.art.core.managers;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.dev.art.core.utils.CalendarioAPI;

public class API {

	public static int getColumn(int index) {
		if (index < 9) {
			return index + 1;
		}
		return (index % 9) + 1;
	}

	public static boolean isColumn(int index, int colunm) {
		return getColumn(index) == colunm;
	}

	public static boolean isInt(String arg1) {
		if (arg1.equalsIgnoreCase("NaN")) {
			return false;
		}
		try {
			Integer.valueOf(arg1);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isFloat(String arg1) {
		if (arg1.equalsIgnoreCase("NaN")) {
			return false;
		}
		try {
			Float.valueOf(arg1);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isDouble(String arg1) {
		if (arg1.equalsIgnoreCase("NaN")) {
			return false;
		}
		try {
			Double.valueOf(arg1);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static int toInt(String arg1) {
		if (isInt(arg1)) {
			try {
				return Integer.valueOf(arg1);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}

	public static float toFloat(String arg1) {
		if (isInt(arg1)) {
			try {
				return Float.valueOf(arg1);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}

	public static double toDouble(String arg1) {
		if (isInt(arg1)) {
			try {
				return Double.valueOf(arg1);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}

	public static String formatarDouble(double quantidade) {
		DecimalFormat n = new DecimalFormat("#,###");
		String formatado = n.format(quantidade).replace(".", ",");
		String v = formatado.split(",")[0];
		String sla = quantidade >= 1000 && quantidade <= 999999 ? v + "K"
				: quantidade >= 1000000 && quantidade <= 999999999 ? v + "M"
						: quantidade >= 1000000000 && quantidade <= 999999999999L ? v + "B"
								: quantidade >= 1000000000000L && quantidade <= 999999999999999L ? v + "T"
										: quantidade >= 1000000000000000L && quantidade <= 999999999999999999L ? v + "Q"
												: quantidade >= 1000000000000000000L
														&& String.valueOf(quantidade).length() <= 21
																? v + "Z"
																: String.valueOf(quantidade).length() > 18 ? "999Z"
																		: String.valueOf(quantidade).length() < 7
																				? formatado
																				: formatado;

		return sla;
	}

	public static String formatarInt(Integer quantidade) {

		DecimalFormat n = new DecimalFormat("#,###");
		String formatado = n.format(quantidade).replace(".", ",");
		String v = formatado.split(",")[0];
		String sla = quantidade >= 1000 && quantidade <= 999999 ? v + "K"
				: quantidade >= 1000000 && quantidade <= 999999999 ? v + "M"
						: quantidade >= 1000000000 && quantidade <= 999999999999L ? v + "B"
								: quantidade >= 1000000000000L && quantidade <= 999999999999999L ? v + "T"
										: quantidade >= 1000000000000000L && quantidade <= 999999999999999999L ? v + "Q"
												: quantidade >= 1000000000000000000L
														&& String.valueOf(quantidade).length() <= 21
																? v + "Z"
																: String.valueOf(quantidade).length() > 18 ? "999Z"
																		: String.valueOf(quantidade).length() < 7
																				? formatado
																				: formatado;

		return sla;
	}

	public static String formatarData(long data) {
		return CalendarioAPI.getData(data);
	}

	public static String formatarHora(long hora) {
		return CalendarioAPI.getHoras(hora);
	}

}
