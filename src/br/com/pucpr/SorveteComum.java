package br.com.pucpr;

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
