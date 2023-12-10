package br.com.npml.aluno.microsservicos;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.System.out;

@SpringBootTest
public class AlunoBackendApplicationTest {

    @Test
    void contextLoads() {
        out.println(MobyNamesGenerator.getRandomName());
    }
}
