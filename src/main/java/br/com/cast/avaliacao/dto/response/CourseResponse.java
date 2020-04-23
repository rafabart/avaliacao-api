package br.com.cast.avaliacao.dto.response;

import br.com.cast.avaliacao.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CourseResponse {

    private Long id;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer numberOfStudents;

    private Category category;

    private Long category_id;
}
