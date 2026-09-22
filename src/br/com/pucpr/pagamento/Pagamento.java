package br.com.pucpr.pagamento;

public class Pagamento {
    private PagamentoStrategy strategy;

    public Pagamento(PagamentoStrategy strategy) {
        this.strategy = strategy;
    }

    public void pagar(double valor) {
        strategy.pagar(valor);
    }
}
