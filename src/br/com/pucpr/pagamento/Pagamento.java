package br.com.pucpr.pagamento;

import br.com.pucpr.pagamento.metodos.definicaoStrategy.PagamentoStrategy;

public class Pagamento {
    private PagamentoStrategy strategy;

    public Pagamento(PagamentoStrategy strategy) {
        this.strategy = strategy;
    }

    public void pagar(double valor) {
        strategy.pagar(valor);
    }
}
