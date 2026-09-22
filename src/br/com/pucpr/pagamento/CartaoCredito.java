package br.com.pucpr.pagamento;

public class CartaoCredito implements PagamentoStrategy {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de " + valor + " pelo cartão de crédito");
    }
}