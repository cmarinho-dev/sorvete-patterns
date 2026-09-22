package br.com.pucpr;

public abstract class SorveteDecorator implements Sorvete {
    protected Sorvete sorvete;

    public SorveteDecorator(Sorvete sorvete) {
        this.sorvete = sorvete;
    }

    @Override
    public String getDescricao() {
        return sorvete.getDescricao();
    }

    @Override
    public double getPreco() {
        return sorvete.getPreco();
    }
}
