package com.example.tiagoiwamoto.appmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    LivroAdapter livroAdapter;
    List<Livro> livroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("livro");
        livroList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        livroAdapter = new LivroAdapter(getApplicationContext(), R.layout.activity_livro_item, new ArrayList<Livro>());

        ListView listView = (ListView) findViewById(R.id.listViewLivro);
        listView.setAdapter(livroAdapter);

        databaseReference.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            livroList.clear();
            Livro livro;

            for(DataSnapshot x : dataSnapshot.getChildren()){
                livro = x.getValue(Livro.class);
                livroList.add(livro);
            }

            atualizarListView(livroList);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void atualizarListView(List<Livro> list){

        livroAdapter.setDados(list);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Log.d("aqui", "Action add");
                startActivity(new Intent(this.getBaseContext(), LivroInserir.class));
                return true;
            default:
                Log.d("aqui", "Action add");
                Log.d("aqui", "X: " + item.getItemId());
                Log.d("aqui", "ADD: " + R.id.action_add);
                Log.d("aqui", "SAVE: " + R.id.action_save);
                return super.onOptionsItemSelected(item);
        }
    }
}
