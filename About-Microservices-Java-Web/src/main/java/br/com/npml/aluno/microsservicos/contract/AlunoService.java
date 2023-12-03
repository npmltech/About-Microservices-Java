package br.com.npml.escola.microsservicos.contract;

import br.com.npml.escola.microsservicos.model.Aluno;

import java.util.List;

public interface AlunoService {

    List<Aluno> getAll();

    Aluno getById(long id);

    Aluno create(Aluno aluno);

    Aluno update(long id, Aluno aluno);

    void deleteById(long id);
}
