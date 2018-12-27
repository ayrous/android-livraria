package br.com.senai.livraria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.senai.livraria.dao.ImagemDAO;
import br.com.senai.livraria.dao.LivroDAO;
import br.com.senai.livraria.model.Imagem;
import br.com.senai.livraria.model.Livro;

public class FormularioLivrosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Button botaoCadastrar;
    private LivroDAO dao;
    private EditText nome;
    private EditText autor;
    private EditText editora;
    private  EditText anoPublicacao;
    private EditText genero;

    private static final int PERMISSAO_REQUEST = 1;
    public static final int GALERIA_CODE = 1;
    private ImageView suaFoto;
    private Button colocarFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_livros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Você já está nessa tela", Toast.LENGTH_LONG).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nome = findViewById(R.id.editNome);
        autor = findViewById(R.id.editTAutor);
        editora = findViewById(R.id.editEditora);
        anoPublicacao = findViewById(R.id.editAnoPublicacao);
        genero = findViewById(R.id.editGenero);

        botaoCadastrar = findViewById(R.id.btnCadastrar);

        suaFoto = findViewById(R.id.imgSuaFoto);
        colocarFoto = findViewById(R.id.btnCarregarImagem);

        dao = new LivroDAO(this);

        final FormularioHelp help = new FormularioHelp(this);

        Bundle extras = getIntent().getExtras();
        Long livroId = (extras != null) ? extras.getLong("livroId") : null;

        if (livroId == null) {
            Livro livro = new Livro();
        } else {
            Livro livro = dao.localizar(livroId);
            help.preencherFormulario(livro);
        }


        colocarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_CODE);
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Imagem imagem = new Imagem();
                imagem.setCaminhoDaImagem(suaFoto.getTag().toString());
                ImagemDAO imgDao = new ImagemDAO(FormularioLivrosActivity.this);
                imgDao.inserir(imagem);

                if (nome.getText().length() != 0 && autor.getText().length() != 0 && editora.getText().length() != 0 && anoPublicacao.getText().length() != 0 && genero.getText().length() != 0) {
                    Livro livro = help.pegarLivro();
                    LivroDAO dao = new LivroDAO(FormularioLivrosActivity.this);

                    if (livro.getId() != null) {
                        dao.alterar(livro);
                    } else {
                        dao.salvar(livro);
                    }

                    dao.close();

                    Toast.makeText(getApplicationContext(), livro.getNome() + " salvo com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Insira valores em todos os campos", Toast.LENGTH_LONG).show();
                }

            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.formulario_livros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(FormularioLivrosActivity.this, MainActivity.class);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == GALERIA_CODE){
            Uri imagemSelecionada = data.getData();
            String[] caminhoDoDiretorio = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(imagemSelecionada,caminhoDoDiretorio,null,null,null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(caminhoDoDiretorio[0]);
            String caminhoDaImagem = c.getString(columnIndex);
            c.close();
            Bitmap imagemRetornada = (BitmapFactory.decodeFile(caminhoDaImagem));
            suaFoto.setImageBitmap(imagemRetornada);
            suaFoto.setTag(caminhoDaImagem);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // A permissão foi concedida. Pode continuar
            } else{
                // A permissão foi negada. Precisa ver o que deve ser desabilitado

            }
            return;
        }
    }

}