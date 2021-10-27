package com.example.agenda;

import android.app.Application;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {

        criaAlunosTeste();

        super.onCreate();
    }
    private void criaAlunosTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salvar(new Aluno("Carlos", "crmjrx@gmail.com", "997474527"));
    }
}
