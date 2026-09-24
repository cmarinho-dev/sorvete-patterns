package br.com.pucpr;

import br.com.pucpr.adicionais.CaldaChocolate;
import br.com.pucpr.adicionais.CaldaMorango;
import br.com.pucpr.adicionais.Granulado;
import br.com.pucpr.adicionais.Nutella;
import br.com.pucpr.adicionais.definicaoDecorator.SorveteDecorator;
import br.com.pucpr.pagamento.Pagamento;
import br.com.pucpr.pagamento.metodos.CartaoCredito;
import br.com.pucpr.pagamento.metodos.CartaoDebito;
import br.com.pucpr.pagamento.metodos.Dinheiro;
import br.com.pucpr.pagamento.metodos.Pix;
import br.com.pucpr.pagamento.metodos.definicaoStrategy.PagamentoStrategy;
import br.com.pucpr.produtos.Sorvete;
import br.com.pucpr.produtos.SorveteComum;

import java.util.ArrayList;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class CasosDeUso {
    private final ArrayList<Sorvete> sorvetes;

    public CasosDeUso() {
        this.sorvetes = new ArrayList<>();
    }

    public void listarPedidos() {
        for (int i = 0; i < sorvetes.size(); i++) {
            imprimirPedido(i, sorvetes.get(i));
        }
    }

    public void listarPedidosNaoPagos() {
        for (int i = 0; i < sorvetes.size(); i++) {
            Sorvete sorvete = sorvetes.get(i);
            if (sorvete.getPagamento() == null || !sorvete.getPagamento().isPago()) {
                imprimirPedido(i, sorvete);
            }
        }
    }

    public void criarSorvete() {
        Sorvete sorvete = new SorveteComum();

        var opcao = readln("> Deseja pôr adicionais (sim/nao)? ");

        if (opcao.equals("sim")) {
            sorvete = colocarAdicionais(sorvete);
        }

        println("> Sorvete criado!\n");
        sorvetes.add(sorvete);
    }

    public void receberPagamento() {
        listarPedidosNaoPagos();
        String pedidoStr = readln("> Informe o número do pedido: ");
        int indice;
        try {
            indice = Integer.parseInt(pedidoStr) - 1;
        } catch (NumberFormatException e) {
            println("> Número de pedido inválido.\n");
            return;
        }

        if (indice < 0 || indice >= sorvetes.size()) {
            println("> Pedido não encontrado.\n");
            return;
        }

        Sorvete sorvete = sorvetes.get(indice);
        if (sorvete.getPagamento() != null && sorvete.getPagamento().isPago()) {
            println("> Este pedido já foi pago.\n");
            return;
        }

        String metodo = readln("""
                Escolha a forma de pagamento:
                1 - Cartão de crédito
                2 - Cartão de débito
                3 - Dinheiro
                4 - PIX
                ->\s""");
        PagamentoStrategy strategy = switch (metodo) {
            case "1" -> new CartaoCredito();
            case "2" -> new CartaoDebito();
            case "3" -> new Dinheiro();
            case "4" -> new Pix();
            default -> null;
        };

        if (strategy == null) {
            println("> Forma de pagamento inválida.\n");
            return;
        }

        Pagamento pagamento = new Pagamento(strategy);
        sorvete.setPagamento(pagamento);
        pagamento.pagar(sorvete.getPreco());
        println("> Pagamento realizado!\n");
    }

    private void imprimirPedido(int indice, Sorvete sorvete) {
        System.out.println(indice + 1
                + " " + sorvete.getDataHoraCriacao()
                + " " + sorvete.getPreco()
                + " " + sorvete.getDescricao()
                + " - " + (sorvete.getPagamento() != null && sorvete.getPagamento().isPago()
                ? "pago" : "não pago"));
    }


    // utilidades (private):

    private Sorvete colocarAdicionais(Sorvete s) {
        String opcao = "";
        do {
            String adicionalStr = readln("""
                    Selecione uma opcao de adicional:
                    1 - CaldaChocolate
                    2 - CaldaMorango
                    3 - Granulado
                    4 - Nutella
                    ->\s""");

            SorveteDecorator adicional = switch (adicionalStr) {
                case "1" -> new CaldaChocolate(s);
                case "2" -> new CaldaMorango(s);
                case "3" -> new Granulado(s);
                case "4" -> new Nutella(s);
                default -> null;
            };

            if (adicional != null) {
                s = adicional;
            }

            opcao = readln("> Deseja pôr mais adicionais (sim/nao)? ");
        } while (opcao.equals("sim"));

        return s;
    }
}
