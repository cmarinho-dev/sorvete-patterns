package br.com.pucpr.produtos;

import br.com.pucpr.pagamento.Pagamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SorveteComum implements Sorvete{
    protected LocalDateTime dataHoraCriacao;
    protected Pagamento pagamento;

    public SorveteComum() {
        dataHoraCriacao = LocalDateTime.now();
    }

    public String getDataHoraCriacao() {
        DateTimeFormatter formatador =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHoraCriacao.format(formatador);
    }

    @Override
    public String getDescricao() {
        return "Sorvete";
    }

    @Override
    public double getPreco() {
        return 5;
    }

    @Override
    public Pagamento getPagamento() {
        return pagamento;
    }

    @Override
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
