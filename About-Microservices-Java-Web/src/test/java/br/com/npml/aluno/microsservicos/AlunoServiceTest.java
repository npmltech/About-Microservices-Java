package br.com.npml.aluno.microsservicos;

import br.com.npml.aluno.microsservicos.exception.AlunoNotFoundException;
import br.com.npml.aluno.microsservicos.contract.AlunoService;
import br.com.npml.aluno.microsservicos.model.Aluno;
import br.com.npml.aluno.microsservicos.repository.AlunoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles(value = "test")
public class AlunoServiceTest {

    private final Logger LOGGER = LoggerFactory.getLogger(AlunoServiceTest.class);

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoRepository repository;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Before Each");
        Aluno maria = Aluno.builder()
            .nomeAluno("Maria Eduarda")
            .numMatricula(1234)
            .numSalaAula(123)
            .ativo(true)
            .build();

        Aluno jose = Aluno.builder()
            .nomeAluno("José Augusto Mello")
            .numMatricula(4321)
            .numSalaAula(123)
            .ativo(false)
            .build();

        List<Aluno> alunos = List.of(maria, jose);
        repository.saveAll(alunos);
    }

    @Test
    @Order(1)
    public void testaGetAll() {
        LOGGER.info("Testa Get All");
        List<Aluno> all = alunoService.getAll();
        Aluno aluno = all.get(0);
        assertEquals(2, all.size());
        assertEquals("Maria Eduarda", aluno.getNomeAluno());
    }

    @Test
    @Order(2)
    public void testaGetById() {
        LOGGER.info("Testa Get By Id");
        Aluno aluno = alunoService.getById(1);
        assertEquals("Maria Eduarda", aluno.getNomeAluno());
        assertEquals(1234, aluno.getNumMatricula());
        assertThrows(AlunoNotFoundException.class, () -> {
            Aluno alunoException = alunoService.getById(1000);
        });
    }

    @Test
    @Order(3)
    public void testaDelete() {
        LOGGER.info("Testa Delete");
        List<Aluno> all = alunoService.getAll();
        alunoService.deleteById(1);
        assertEquals(2, all.size());
    }

    @Test
    @Order(4)
    public void testaCreate() {
        LOGGER.info("Testa Create");
        Aluno bruno = Aluno.builder()
            .nomeAluno("Bruno Garcia")
            .numMatricula(7890)
            .numSalaAula(234)
            .ativo(false)
            .build();
        Aluno novoAluno = alunoService.create(bruno);
        assertEquals(5, novoAluno.getIdAluno());
        List<Aluno> all = alunoService.getAll();
        assertEquals(3, all.size());
    }

    @Test
    @Order(5)
    public void testaUpdate() {
        LOGGER.info("Testa Update");
        Aluno bruno = Aluno.builder()
            .nomeAluno("Bruno Garcia Oliveira")
            .numMatricula(7890)
            .numSalaAula(234)
            .ativo(false)
            .build();
        Aluno alunoAtualizado = alunoService.update(3, bruno);
        alunoService.deleteById(1);
        assertEquals("Bruno Garcia Oliveira", alunoAtualizado.getNomeAluno());
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("After Each");
        repository.deleteAll();
    }
}
