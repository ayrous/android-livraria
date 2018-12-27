package br.com.senai.livraria.model;

/**
 * Created by 22853582884 on 11/04/2018.
 */

public class Imagem {
    private Long id;
    private String caminhoDaImagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminhoDaImagem() {
        return caminhoDaImagem;
    }

    public void setCaminhoDaImagem(String caminhoDaImagem) {
        this.caminhoDaImagem = caminhoDaImagem;
    }
}