package br.com.pucpr.produtos;

import br.com.pucpr.pagamento.Pagamento;

public interface Sorvete {
    String getDescricao();
    double getPreco();
    String getDataHoraCriacao();
    Pagamento getPagamento();
    void setPagamento(Pagamento pagamento);
}
