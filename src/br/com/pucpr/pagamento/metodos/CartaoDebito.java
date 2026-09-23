package br.com.pucpr.pagamento.metodos;

import br.com.pucpr.pagamento.metodos.definicaoStrategy.PagamentoStrategy;

public class CartaoDebito implements PagamentoStrategy {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de " + valor + " pelo cartão de débito");
    }
}
