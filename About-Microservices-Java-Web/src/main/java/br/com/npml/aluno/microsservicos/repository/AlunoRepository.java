package br.com.npml.aluno.microsservicos.repository;

import br.com.npml.aluno.microsservicos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
