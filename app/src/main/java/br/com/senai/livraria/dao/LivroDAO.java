package br.com.senai.livraria.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.livraria.model.Livro;

/**
 * Created by 22853582884 on 04/04/2018.
 */

public class LivroDAO extends SQLiteOpenHelper {


    public LivroDAO(Context context) {
        super(context, "LivrariaDois", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql="CREATE TABLE livro(" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT," +
                "autor TEXT," +
                "genero TEXT," +
                "editora TEXT," +
                "ano_publicacao TEXT," +
                "caminhoFoto TEXT" +
                ");";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS livro";
        sqLiteDatabase.execSQL(sql);
    }

    public void  salvar(Livro livro){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(livro);
        db.insert("livro", null, dados);
    }


    @NonNull
    private ContentValues getContentValues(Livro livro) {
        ContentValues dados = new ContentValues();

        dados.put("nome", livro.getNome());
        dados.put("autor", livro.getAutor());
        dados.put("genero", livro.getGenero());
        dados.put("editora", livro.getEditora());
        dados.put("ano_publicacao", livro.getAnoPublicacao());
        dados.put("caminhoFoto", livro.getCaminhoDaImagem());

        return dados;
    }

    public List<Livro> buscaLivro(){
        String sql = "SELECT * FROM livro";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);
        List<Livro> livros = new ArrayList<Livro>();

        while(c.moveToNext()){
            Livro livro = new Livro();
            livro.setId(c.getLong(c.getColumnIndex("id")));
            livro.setNome(c.getString(c.getColumnIndex("nome")));
            livro.setAutor(c.getString(c.getColumnIndex("autor")));
            livro.setGenero(c.getString(c.getColumnIndex("genero")));
            livro.setEditora(c.getString(c.getColumnIndex("editora")));
            livro.setAnoPublicacao(c.getString(c.getColumnIndex("ano_publicacao")));
            livro.setCaminhoDaImagem(c.getString(c.getColumnIndex("caminhoFoto")));

            livros.add(livro);

        }
        return livros;
    }


    public void deletar(Livro livro){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {
                String.valueOf(livro.getId())
        };

        db.delete("livro", "id = ?", parametros);
    }

    public void alterar(Livro livro){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(livro);
        String[] parametros = {livro.getId().toString()};
        db.update("livro", dados, "id = ?", parametros);
    }

    public Livro localizar(Long livroId){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM livro WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(livroId)});

        c.moveToFirst();
        Livro livroRetornado = new Livro();
        livroRetornado.setId(c.getLong(c.getColumnIndex("id")));
        livroRetornado.setNome(c.getString(c.getColumnIndex("nome")));
        livroRetornado.setAutor(c.getString(c.getColumnIndex("autor")));
        livroRetornado.setGenero(c.getString(c.getColumnIndex("genero")));
        livroRetornado.setEditora(c.getString(c.getColumnIndex("editora")));
        livroRetornado.setAnoPublicacao(c.getString(c.getColumnIndex("ano_publicacao")));
        livroRetornado.setCaminhoDaImagem(c.getString(c.getColumnIndex("caminhoFoto")));

        db.close();

        return livroRetornado;

    }
}