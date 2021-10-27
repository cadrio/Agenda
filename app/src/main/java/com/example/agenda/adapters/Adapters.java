package com.example.agenda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class Adapters extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public Adapters(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        View viewCriada;

        if (view == null) {
            viewCriada = LayoutInflater.from(context).inflate(R.layout.component_layout_itemaluno, viewGroup, false);
        } else {
            viewCriada = view;
        }

        Aluno alunoDevolvido = alunos.get(posicao);

        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNome());

        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolvido.getTelefone());

        return viewCriada;
    }


    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
