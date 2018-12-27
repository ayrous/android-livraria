package br.com.senai.livraria;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.senai.livraria.model.Imagem;
import br.com.senai.livraria.model.Livro;

/**
 * Created by 22853582884 on 04/04/2018.
 */

public class FormularioHelp {
    private EditText nome;
    private EditText autor;
    private EditText genero;
    private EditText editora;
    private EditText anoPublicacao;
    private Livro livro;
    private ImageView imagemCarregada;
    private Imagem imagem;

    public FormularioHelp(FormularioLivrosActivity activity) {
        nome = activity.findViewById(R.id.editNome);
        autor = activity.findViewById(R.id.editTAutor);
        genero = activity.findViewById(R.id.editGenero);
        editora  =activity.findViewById(R.id.editEditora);
        anoPublicacao = activity.findViewById(R.id.editAnoPublicacao);

        livro = new Livro();
    }

    public Livro pegarLivro(){
        livro.setNome(nome.getText().toString());
        livro.setAutor(autor.getText().toString());
        livro.setEditora(editora.getText().toString());
        livro.setGenero(genero.getText().toString());
        livro.setAnoPublicacao(anoPublicacao.getText().toString());

        return livro;
    }

    public void preencherFormulario(Livro livro){
        nome.setText(livro.getNome());
        autor.setText(livro.getAutor());
        genero.setText(livro.getGenero());
        editora.setText(livro.getEditora());
        anoPublicacao.setText(livro.getAnoPublicacao());
        Bitmap imagemConvertida = BitmapFactory.decodeFile(livro.getCaminhoDaImagem());
        imagemCarregada.setImageBitmap(imagemConvertida);
        this.livro = livro;
    }


}
