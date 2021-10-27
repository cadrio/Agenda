package com.example.agenda.DAO;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadordeIds = 1;


    public int salvar(Aluno aluno) {
        aluno.setId(contadordeIds);
        alunos.add(aluno);
        contadordeIds++;
        return contadordeIds;
        }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = null;

        alunoEncontrado = buscaAlunoPeloID(aluno);

        if (alunoEncontrado != null) {
                int posicao = alunos.indexOf(alunoEncontrado);
                alunos.set(posicao, aluno);
                }

    }

    private Aluno buscaAlunoPeloID(Aluno aluno) {
        for (Aluno a : alunos) {
            if (aluno.getId() == a.getId()) {
                return a;
            }
            }
        return null;
        }


    public List<Aluno> todos() {

        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoRecebido) {

//        Aluno alunoParaRemover = buscaAlunoPeloID(alunoRecebido);
        alunos.remove(alunoRecebido);

    }
}
