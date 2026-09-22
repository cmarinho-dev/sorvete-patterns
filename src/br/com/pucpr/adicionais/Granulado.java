package br.com.pucpr.adicionais;

import br.com.pucpr.Sorvete;
import br.com.pucpr.SorveteDecorator;

public class Granulado extends SorveteDecorator {
    public Granulado(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", granulado";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 0.30;
    }
}
