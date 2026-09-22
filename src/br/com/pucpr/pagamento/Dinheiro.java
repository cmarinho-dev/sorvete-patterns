package br.com.pucpr.pagamento;

public class Dinheiro implements PagamentoStrategy {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de " + valor + " com dinheiro");
    }
}
