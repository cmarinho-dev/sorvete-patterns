package br.com.pucpr.adicionais;

import br.com.pucpr.produtos.Sorvete;
import br.com.pucpr.adicionais.definicaoDecorator.SorveteDecorator;

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
