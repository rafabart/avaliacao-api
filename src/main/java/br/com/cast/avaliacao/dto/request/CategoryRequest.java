package br.com.cast.avaliacao.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryRequest {

    @NotNull(message = "Campo obrigatório")
    @Length(min = 3, max = 50, message = "Deve conter de {min} a {max} caracteres")
    private String description;
}
