package br.com.pucpr;

public class SorveteGrande implements Sorvete {
    @Override
    public String getDescricao() {
        return "Sorvete Grande";
    }

    @Override
    public double getPreco() {
        return 12;
    }
}
