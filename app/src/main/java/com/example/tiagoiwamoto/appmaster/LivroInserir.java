package com.example.tiagoiwamoto.appmaster;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class LivroInserir extends Activity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_inserir);


        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("livro");
    }

    public void save(){
        String id = UUID.randomUUID().toString();

        EditText txtNome = (EditText) findViewById(R.id.nomeLivro);
        Spinner txtCategoria = (Spinner) findViewById(R.id.categoria);
        EditText txtDescricao = (EditText) findViewById(R.id.descricao);

        String nome = txtNome.getText().toString();
        String categoria = txtCategoria.getSelectedItem().toString();
        String descricao = txtDescricao.getText().toString();

        Livro livro = new Livro();
        livro.setNome(nome);
        livro.setCategoria(categoria);
        livro.setDescricao(descricao);

        databaseReference.child(id).setValue(livro);
        startActivity(new Intent(this.getBaseContext(), MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_inserir_livro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                save();
                return true;
            case android.R.id.home:
                finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
