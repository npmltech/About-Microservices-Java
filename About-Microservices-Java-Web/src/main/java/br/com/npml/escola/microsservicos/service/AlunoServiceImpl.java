package br.com.npml.escola.microsservicos.service;

import br.com.npml.escola.microsservicos.exception.AlunoNotFoundException;
import br.com.npml.escola.microsservicos.impl.AlunoService;
import br.com.npml.escola.microsservicos.model.Aluno;
import br.com.npml.escola.microsservicos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public List<Aluno> getAll() {
        return repository.findAll();
    }

    @Override
    public Aluno getById(long id) {
        return repository.findById(id).orElseThrow(AlunoNotFoundException::new);
    }

    @Override
    public Aluno create(Aluno aluno) {
        return repository.save(aluno);
    }

    @Override
    public Aluno update(long id, Aluno aluno) {
        aluno.setIdAluno(id);
        return create(aluno);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
