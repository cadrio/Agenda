package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.adapters.Adapters;
import com.example.agenda.model.Aluno;

public class ListaAlunos extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();
    public static final String TITULO_APPBAR = "Lista de Alunos";
    private Adapters adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_listaalunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.activity_listaAlunos_menu_remove) {

            new AlertDialog.Builder(this)
                    .setTitle("Remoção de Aluno")
                    .setMessage("Tem certeza que deseja remover o aluno?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            AdapterView.AdapterContextMenuInfo menuInfo =
                                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                            removeAluno(alunoEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();


        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        findViewById(R.id.activity_listaalunos_botaoadd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(ListaAlunos.this, FormularioAlunoActivity.class));
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_listaalunos_listview);
        adapter = new Adapters(this);
        listaDeAlunos.setAdapter(adapter);
        configuraListenerCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);

    }

    private void configuraListenerCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                abreFormularioModoEdicao(posicao);
            }
        });
    }

    private void abreFormularioModoEdicao(int posicao) {

        Aluno alunoEscolhido = dao.todos().get(posicao);
        Intent intentEdicao = new Intent(ListaAlunos.this, FormularioAlunoActivity.class);
        intentEdicao.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(intentEdicao);


    }

    private void removeAluno(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }
}
