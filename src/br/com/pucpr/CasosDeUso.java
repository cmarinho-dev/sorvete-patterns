package br.com.pucpr;

import br.com.pucpr.adicionais.CaldaChocolate;
import br.com.pucpr.adicionais.CaldaMorango;
import br.com.pucpr.adicionais.Granulado;
import br.com.pucpr.adicionais.Nutella;
import br.com.pucpr.adicionais.definicaoDecorator.SorveteDecorator;
import br.com.pucpr.pagamento.Pagamento;
import br.com.pucpr.produtos.Sorvete;
import br.com.pucpr.produtos.SorveteComum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.IO.readln;

public class CasosDeUso {
    private HashMap<Sorvete, Pagamento> sorvetes;
    private final Consumer<Sorvete> selecionarAdicionais = (s) -> {
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

            opcao = readln("- Deseja pôr mais adicionais (sim/nao)? ");
        } while (opcao.equals("sim"));
    };

    public CasosDeUso() {
        this.sorvetes = new HashMap<>();
    }

    public void criarProduto() {
        Sorvete produto = new SorveteComum();

        var opcao = readln("- Deseja pôr adicionais (sim/nao)? ");

        if (opcao.equals("sim")) {
            selecionarAdicionais.run();
        }

        sorvetes.put(produto, null);
    }

    public void receberPagamento() {
    }
}
