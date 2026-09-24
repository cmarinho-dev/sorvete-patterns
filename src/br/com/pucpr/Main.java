package br.com.pucpr;

import static java.lang.IO.readln;

public class Main {
    public static void main(String[] args) {
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
                    3 - Listar pedidos
                    0 - Sair
                    ->\s""");

            switch (opcao) {
                case "1" -> casosDeUso.criarSorvete();
                case "2" -> casosDeUso.receberPagamento();
                case "3" -> casosDeUso.listarPedidos();
                case "0" -> atendimentoStatus = false;
            }
        }
    }
}
