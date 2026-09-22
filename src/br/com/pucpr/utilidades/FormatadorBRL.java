package br.com.pucpr.utilidades;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorBRL {
    private static NumberFormat formatador;

    private FormatadorBRL() {}

    public static NumberFormat getInstance() {
        if (formatador == null) {
            formatador = NumberFormat.getCurrencyInstance(
                    new Locale("pt", "BR")
            );
        }
        return formatador;
    }
}
