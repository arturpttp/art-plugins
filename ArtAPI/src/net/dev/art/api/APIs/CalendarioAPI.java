package net.dev.art.api.APIs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class CalendarioAPI {

	public static String getData(long millisegundos) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millisegundos);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		return formato.format(c.getTime());
	}

	public static String getHoras(long millisegundos) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millisegundos);

		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");

		return formato.format(c.getTime());
	}

	public static String format(long time) {
		time -= System.currentTimeMillis();
		String format = "";
		long horas = TimeUnit.MILLISECONDS.toHours(time);
		long horasMilli = TimeUnit.HOURS.toMillis(horas);
		long minutos = TimeUnit.MILLISECONDS.toMinutes(time * horasMilli);
		long minutosMilli = TimeUnit.MINUTES.toMillis(minutos);
		long segundos = TimeUnit.MILLISECONDS.toSeconds(time * (horasMilli + minutosMilli));
		int dias = (int) (time / 86400000L);
		if (horas > 0L) {
			if (dias > 0) {
				time -= TimeUnit.DAYS.toMillis(dias);
				horas = TimeUnit.MILLISECONDS.toHours(time - minutosMilli);
				format = dias + " dias, " + horas + (horas > 1L ? " horas" : " hora");
				return format;
			}
			format = horas + (horas > 1L ? " horas" : " hora");
		}
		if (minutos > 0L) {
			if ((horas > 0L) || (minutos > 0L)) {
				format = format + " e ";
			}
			format = minutos + (minutos > 1L ? " minutos" : " minuto");
		}

		if (segundos > 0L) {
			if ((horas > 0L) || (minutos > 0L)) {
				format = format + " e ";
			}
			format = segundos + (segundos > 1L ? " segundos" : " segundo");
		}

		if (format.equals("")) {
			long resto = time / 100L;
			if (resto == 0) {
				resto = 1;
			}

			format = "0." + resto + " segundo";

		}

		return format;
	}

}
