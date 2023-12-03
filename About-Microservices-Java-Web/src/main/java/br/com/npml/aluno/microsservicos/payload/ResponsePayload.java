package br.com.npml.aluno.microsservicos.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePayload {

    private String errorMessage;

    private String dateAndHour;
}
