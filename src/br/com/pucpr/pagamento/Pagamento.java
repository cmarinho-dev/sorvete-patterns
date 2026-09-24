package br.com.pucpr.pagamento;

import br.com.pucpr.pagamento.metodos.definicaoStrategy.PagamentoStrategy;
public class Pagamento {
    private PagamentoStrategy strategy;
    private boolean pago;

    public Pagamento(PagamentoStrategy strategy) {
        this.strategy = strategy;
    }

    public void pagar(double valor) {
        strategy.pagar(valor);
        pago = true;
    }

    public boolean isPago() {
        return pago;
    }
}
