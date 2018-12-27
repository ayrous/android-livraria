package br.com.senai.livraria.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.senai.livraria.model.Imagem;

public class ImagemDAO extends SQLiteOpenHelper{

    public ImagemDAO(Context context) {
        super(context, "galeriaDeFotos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE imagem(id INTEGER PRIMARY KEY, caminhoFot TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS imagem;";
        db.execSQL(sql);
    }

    public void inserir(Imagem imagem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("caminhoFot", imagem.getCaminhoDaImagem());
        db.insert("imagem",null, dados);
    }
}
