package br.com.senai.livraria.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.senai.livraria.R;
import br.com.senai.livraria.RecycleDrawerActivity;
import br.com.senai.livraria.model.Imagem;
import br.com.senai.livraria.model.Livro;
import br.com.senai.livraria.view.holder.LivroViewHolder;

/**
 * Created by 22853582884 on 04/04/2018.
 */

public class LivroRecicleAdapter extends RecyclerView.Adapter{

    public final Context context;
    public final List<Livro> livros;

    public LivroRecicleAdapter(Context context, List<Livro> livros) {
        this.context = context;
        this.livros = livros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);
        LivroViewHolder holder = new LivroViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        LivroViewHolder meuHolder  = (LivroViewHolder) holder ;
        Livro livro = livros.get(position);
        meuHolder.preencher(livro);

    }

    @Override
    public int getItemCount() {
        return livros.size();
    }
}