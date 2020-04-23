package br.com.cast.avaliacao.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CourseRequest {

    @NotNull(message = "Campo 'Descrição' é obrigatório")
    @Length(min = 5, max = 100, message = "Deve conter de {min} a {max} caracteres")
    private String description;

    @NotNull(message = "Campo 'Data de Inicio' é obrigatório")
    private LocalDate startDate;

    @NotNull(message = "Campo 'Data de Conclusão' é obrigatório")
    private LocalDate endDate;

    @NotNull(message = "Campo 'Qtd de Alunos' é obrigatório")
    @Positive(message = "Digite um valor maior que zero")
    private Integer numberOfStudents;

    @NotNull(message = "Campo 'Categoria' é obrigatório")
    private Long category_id;
}
