package br.com.pucpr.adicionais;

import br.com.pucpr.produtos.Sorvete;
import br.com.pucpr.adicionais.definicaoDecorator.SorveteDecorator;

public class CaldaChocolate extends SorveteDecorator {
    public CaldaChocolate(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", calda de chocolate";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 0.50;
    }
}
