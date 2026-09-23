package br.com.pucpr.pagamento.metodos;

import br.com.pucpr.pagamento.metodos.definicaoStrategy.PagamentoStrategy;

public class Pix implements PagamentoStrategy {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de " + valor + " via PIX");
    }
}
