package br.com.npml.escola.microsservicos.exception;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AlunoNotFoundException extends RuntimeException {

    private String errorMessage;

    private Throwable err;

    private LocalDateTime localDateTime;
}
