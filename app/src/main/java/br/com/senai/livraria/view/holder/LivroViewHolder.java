package br.com.senai.livraria.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.senai.livraria.FormularioLivrosActivity;
import br.com.senai.livraria.R;
import br.com.senai.livraria.model.Imagem;
import br.com.senai.livraria.model.Livro;
import br.com.senai.livraria.view.adapter.LivroRecicleAdapter;

/**
 * Created by 22853582884 on 04/04/2018.
 */

public class LivroViewHolder extends RecyclerView.ViewHolder {

    private final LivroRecicleAdapter adapter;
    private TextView campoNome;
    private TextView campoAutor;
    private TextView campoGenero;
    private TextView campoEditora;
    private TextView campoAnoPublicacao;
    private ImageView imagemCarregada;
    private Long livroId;

    public LivroViewHolder(View itemView, LivroRecicleAdapter adapter) {
        super(itemView);
        this.adapter = adapter;

        campoNome = itemView.findViewById(R.id.textNome);
        campoAutor = itemView.findViewById(R.id.textAutor);
        imagemCarregada = itemView.findViewById(R.id.imgCarregada);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity context = (Activity) view.getContext();
                final Intent intent = new Intent(context, FormularioLivrosActivity.class);

                intent.putExtra("livroId", livroId);
                context.startActivityForResult(intent, 1);
            }
        });
    }

    public void preencher(Livro livro) {
        livroId = livro.getId();
        campoNome.setText(livro.getNome());
        campoAutor.setText(livro.getAutor());
        Bitmap imagemConvertida = BitmapFactory.decodeFile(livro.getCaminhoDaImagem());
        imagemCarregada.setImageBitmap(imagemConvertida);
    }
}