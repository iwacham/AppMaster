package com.example.tiagoiwamoto.appmaster;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

/**
 * Created by Tiago Iwamoto on 01/04/2018.
 */

public class LivroAdapter extends ArrayAdapter<Livro>{

    Context context;
    int layoutResourceId;
    List<Livro> dados;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    public LivroAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Livro> dados) {
        super(context, resource, dados);

        this.context = context;
        this.layoutResourceId = resource;
        this.dados = dados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        firebaseStorage = FirebaseStorage.getInstance();
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(layoutResourceId, parent, false);
        }

        TextView nome = (TextView) view.findViewById(R.id.nomeLivro);
        TextView categoria = (TextView) view.findViewById(R.id.labelCategoria);
        TextView descricao = (TextView) view.findViewById(R.id.descricao);

        Livro livro = dados.get(position);

        nome.setText(livro.getNome());
        categoria.setText(livro.getCategoria());
        descricao.setText(livro.getDescricao());
        return view;
    }

    public void setDados(List<Livro> dados){
        this.clear();
        this.addAll(dados);
        this.dados = dados;
        this.notifyDataSetChanged();
    }
}
