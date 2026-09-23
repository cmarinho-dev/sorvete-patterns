package br.com.pucpr;

import br.com.pucpr.adicionais.CaldaMorango;
import br.com.pucpr.pagamento.metodos.CartaoCredito;
import br.com.pucpr.pagamento.Pagamento;
import br.com.pucpr.produtos.Sorvete;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class Main {
    public static void main(String[] args) {
        Sorvete nb = new SorveteGrande();
        nb = new CaldaMorango(nb);

        Pagamento pagamentoNB = new Pagamento(new CartaoCredito());
//        System.out.println(nb.getDescricao());
//        System.out.println(FormatadorBRL.getInstance().format(nb.getPreco()));
//
//        pagamentoNB.pagar(nb.getPreco());
        test();
    }

    static void test() {
        var atendimentoStatus = true;
        var casosDeUso = new CasosDeUso();

        while (atendimentoStatus) {
            String opcao = readln("""
                    Bem vindo a SorveteriaPatterns
                    1 - Criar pedido
                    2 - Receber pagamento do pedido
                    0 - Sair
                    ->\s""");

            switch (opcao) {
                case "1" -> casosDeUso.criarProduto();
                case "2" -> casosDeUso.receberPagamento();
                case "0" -> atendimentoStatus = false;
            }
        }
    }
}