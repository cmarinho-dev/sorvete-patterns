package br.com.pucpr.produtos;

public class SorveteComum implements Sorvete{
    @Override
    public String getDescricao() {
        return "Sorvete";
    }

    @Override
    public double getPreco() {
        return 5;
    }
}
