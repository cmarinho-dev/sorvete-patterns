package br.com.pucpr.pagamento;

public class Pix implements PagamentoStrategy {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de " + valor + " via PIX");
    }
}
