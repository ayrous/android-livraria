package br.com.senai.livraria.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.senai.livraria.R;
import br.com.senai.livraria.model.Imagem;
import br.com.senai.livraria.model.Livro;

/**
 * Created by 22853582884 on 04/04/2018.
 */

public class LivroAdapter extends BaseAdapter{

    private final List<Livro> livros;
    private final Context context;

    public LivroAdapter(List<Livro> livros, Imagem imagem, Context context) {
        this.livros = livros;
        this.context = context;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int i) {
        return livros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return livros.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Livro livro = livros.get(i);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, null);

        TextView campoNome = view.findViewById(R.id.textNome);
        campoNome.setText(livro.getNome());

        TextView campoAutor = view.findViewById(R.id.textAutor);
        campoAutor.setText(livro.getAutor());

        ImageView imgCarregada = view.findViewById(R.id.imgCarregada);
        Bitmap imagemConvertida = BitmapFactory.decodeFile(livro.getCaminhoDaImagem());
        imgCarregada.setImageBitmap(imagemConvertida);

        return view;

    }
}
