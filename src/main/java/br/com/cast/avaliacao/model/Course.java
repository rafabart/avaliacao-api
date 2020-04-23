package br.com.cast.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
@EqualsAndHashCode(of = "id")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Length(min = 5, max = 100)
    @Column(nullable = false, length = 100)
    private String description;


    @Column(nullable = false)
    private LocalDate startDate;


    @Column(nullable = false)
    private LocalDate endDate;


    @Positive
    @Column(nullable = false)
    private Integer numberOfStudents;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
