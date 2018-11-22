package net.dev.rpg.APIs;

import java.text.DecimalFormat;

public class FormatarAPI {

	public static String intFormatado(Integer quantidade) {

        DecimalFormat n = new DecimalFormat("#,###");
        String formatado = n.format(quantidade).replace(".", ",");
        String v = formatado.split(",")[0];
        String sla = quantidade >= 1000 && quantidade <= 999999 ?
                v + "K" : quantidade >= 1000000 && quantidade <= 999999999 ?
                v + "M" : quantidade >= 1000000000 && quantidade <= 999999999999L ?
                v + "B" : quantidade >= 1000000000000L && quantidade <= 999999999999999L ?
                v + "T": quantidade >= 1000000000000000L && quantidade <= 999999999999999999L ?
                v + "Q": quantidade >= 1000000000000000000L && String.valueOf(quantidade).length() <= 21?
                v + "Z" : String.valueOf(quantidade).length() > 18 ? "999Z" : String.valueOf(quantidade).length() < 7 ?
                formatado : formatado;

        return sla;
    }

    public static String doubleFormatado(Double quantidade) {

        DecimalFormat n = new DecimalFormat("#,###");
        String formatado = n.format(quantidade).replace(".", ",");
        String v = formatado.split(",")[0];
        String sla = quantidade >= 1000 && quantidade <= 999999 ?
                v + "K" : quantidade >= 1000000 && quantidade <= 999999999 ?
                v + "M" : quantidade >= 1000000000 && quantidade <= 999999999999L ?
                v + "B" : quantidade >= 1000000000000L && quantidade <= 999999999999999L ?
                v + "T": quantidade >= 1000000000000000L && quantidade <= 999999999999999999L ?
                v + "Q": quantidade >= 1000000000000000000L && String.valueOf(quantidade).length() <= 21?
                v + "Z" : String.valueOf(quantidade).length() > 18 ? "999Z" : String.valueOf(quantidade).length() < 7 ?
                formatado : formatado;

        return sla;
}
	
}
