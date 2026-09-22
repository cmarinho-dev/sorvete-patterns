package br.com.pucpr.adicionais;

import br.com.pucpr.Sorvete;
import br.com.pucpr.SorveteDecorator;

public class CaldaMorango extends SorveteDecorator {
    public CaldaMorango(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", calda de morango";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 0.50;
    }
}
