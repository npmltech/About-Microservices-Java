package br.com.npml.aluno.microsservicos.controller;

import br.com.npml.aluno.microsservicos.contract.AlunoService;
import br.com.npml.aluno.microsservicos.exception.AlunoNotFoundException;
import br.com.npml.aluno.microsservicos.model.Aluno;
import br.com.npml.aluno.microsservicos.payload.ResponsePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Value("${spring.application.serverNick}")
    private String nickname;

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoController.class);

    private final LocalDateTime localDateTime = LocalDateTime.now();

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(@RequestHeader Map<String, String> headers) {
        List<Aluno> all = alunoService.getAll();
        LOGGER.info("GET ALL: " + all);
        LOGGER.info("All Headers: " + headers.toString());
        // return ResponseEntity.ok(all);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("server-name", nickname);

        return ResponseEntity.ok().headers(httpHeaders).body(all);
    }

    /*
    * Uso o wildcard pois, posso retornar os dados de aluno OU
    * Retornar o ResponsePayload (exception).
    */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        ResponseEntity<?> result;
        try {
            Aluno aluno = alunoService.getById(id);
            LOGGER.info(
                "GET BY ID: Id do aluno: %d, Nome do Aluno: %s - Matrícula: %d"
                    .formatted(
                        aluno.getIdAluno(),
                        aluno.getNomeAluno(),
                        aluno.getNumMatricula()
                    )
                //
            );
            result = ResponseEntity.ok(aluno);
            //
        } catch (AlunoNotFoundException ex) {
            ex.printStackTrace();

            /*
                EX.: throw new RuntimeException(
                    "O Aluno com o id %d não foi encontrado!"
                        .formatted(id),
                        ex
                    //
                );
            */

            // return ResponseEntity.notFound().build();

            ResponsePayload notFound =
                ResponsePayload
                    .builder()
                    .errorMessage("O Aluno com o id %d não foi encontrado!".formatted(id))
                    .dateAndHour(localDateTime.format(dateFormatter))
                    .build();
                    //
                //
            //
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }
        return (ResponseEntity<Object>) result;
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.create(aluno);
        LOGGER.info(
            "CREATE: Nome do Aluno: %s - Matrícula: %d"
                .formatted(
                    novoAluno.getNomeAluno(),
                    novoAluno.getNumMatricula()
                )
            //
        );
        //
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable long id, @RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.update(id, aluno);
        LOGGER.info(
            "UPDATE BY ID: Id do aluno: %d, Nome do Aluno: %s - Matrícula: %d"
                .formatted(
                    alunoAtualizado.getIdAluno(),
                    alunoAtualizado.getNomeAluno(),
                    alunoAtualizado.getNumMatricula()
                )
            //
        );
        //
        return ResponseEntity.status(HttpStatus.OK).body(alunoAtualizado);
    }

    /*
     * Ao remover, envio uma mensagem junto ao Status ACCEPTED...
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        LOGGER.info("DELETE BY ID: %d".formatted(id));
        alunoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("Aluno com o id: %d foi deletado!".formatted(id));
    }
}
