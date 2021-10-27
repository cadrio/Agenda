package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar Aluno" ;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno alunoClicado;
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializaVariaveis();
        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_alunos_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if(itemId == R.id.activity_formularioAluno_menu_Salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {

        Intent dataIn = getIntent();
        if (dataIn.hasExtra("aluno")) {
            preencheCampos(dataIn);
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            Toast.makeText(FormularioAlunoActivity.this, "Tem Extra!" + alunoClicado.getId(), Toast.LENGTH_SHORT).show();

        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            alunoClicado = new Aluno();
            Toast.makeText(FormularioAlunoActivity.this, "Não tem Extra!", Toast.LENGTH_SHORT).show();
        }
    }

    private void preencheCampos(@NonNull Intent dataIn) {
        alunoClicado = (Aluno) dataIn.getSerializableExtra(CHAVE_ALUNO);
        campoNome.setText(alunoClicado.getNome());
        campoTelefone.setText(alunoClicado.getTelefone());
        campoEmail.setText(alunoClicado.getEmail());
    }



    private void finalizaFormulario() {
        registraAlunoNaLista();
        if (alunoClicado.temIdValido()) {
            dao.edita(alunoClicado);
        } else {
            Integer valorDoId = dao.salvar(alunoClicado);
            Toast.makeText(FormularioAlunoActivity.this, valorDoId.toString(), Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private void inicializaVariaveis() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void registraAlunoNaLista() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        alunoClicado.setNome(nome);
        alunoClicado.setEmail(email);
        alunoClicado.setTelefone(telefone);


    }
}