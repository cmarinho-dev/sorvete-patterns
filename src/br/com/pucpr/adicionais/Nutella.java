package br.com.pucpr.adicionais;

import br.com.pucpr.Sorvete;
import br.com.pucpr.SorveteDecorator;

public class Nutella extends SorveteDecorator {
    public Nutella(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", nutella";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 4.67;
    }
}
