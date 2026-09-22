package br.com.pucpr;

import br.com.pucpr.adicionais.CaldaChocolate;
import br.com.pucpr.adicionais.CaldaMorango;
import br.com.pucpr.adicionais.Granulado;
import br.com.pucpr.adicionais.Nutella;
import br.com.pucpr.utilidades.FormatadorBRL;

public class Main {
    public static void main(String[] args) {
        Sorvete nb = new SorveteGrande();
        nb = new CaldaMorango(nb);

        System.out.println(nb.getDescricao());
        System.out.println(FormatadorBRL.getInstance().format(nb.getPreco()));

    }
}