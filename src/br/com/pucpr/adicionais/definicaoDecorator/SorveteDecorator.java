package br.com.pucpr.adicionais.definicaoDecorator;

import br.com.pucpr.produtos.Sorvete;
import br.com.pucpr.pagamento.Pagamento;

public abstract class SorveteDecorator implements Sorvete {
    protected Sorvete sorvete;

    public SorveteDecorator(Sorvete sorvete) {
        this.sorvete = sorvete;
    }

    @Override
    public String getDescricao() {
        return sorvete.getDescricao();
    }

    @Override
    public double getPreco() {
        return sorvete.getPreco();
    }

    @Override
    public String getDataHoraCriacao() {
        return sorvete.getDataHoraCriacao();
    }

    @Override
    public Pagamento getPagamento() {
        return sorvete.getPagamento();
    }

    @Override
    public void setPagamento(Pagamento pagamento) {
        sorvete.setPagamento(pagamento);
    }
}
