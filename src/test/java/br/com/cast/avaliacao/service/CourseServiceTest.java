package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.dto.response.CourseResponse;
import br.com.cast.avaliacao.exception.CourseNotFoundException;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.model.Course;
import br.com.cast.avaliacao.repository.CourseRepository;
import br.com.cast.avaliacao.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseServiceTest {

    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @MockBean
    private CourseRepository courseRepository;

    @SpyBean
    private CourseServiceImpl courseService;

    Category category = new Category();

    Course course = new Course();


    @BeforeEach
    public void setUp() throws Exception {

        category.setId(1L);
        category.setDescription("Spring");

        course.setCategory(category);
        course.setId(1L);
        course.setDescription("Curso de Testes Unitários");
        course.setStartDate(LocalDate.parse("2020-10-20"));
        course.setEndDate(LocalDate.parse("2020-10-24"));
        course.setNumberOfStudents(30);
    }


    @Test
    public void must_find_cource_by_id() {

        when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));

        final CourseResponse courseResponse = courseService.findById(Mockito.anyLong());

        verify(courseRepository).findById(Mockito.anyLong());

        assertThat(courseResponse).isNotNull();
        assertThat(courseResponse.getId()).isEqualTo(1L);
        assertThat(courseResponse.getNumberOfStudents()).isEqualTo(30);
        assertThat(courseResponse.getDescription()).isEqualTo("Curso de Testes Unitários");
    }


    @Test
    public void should_not_find_cource_by_nonexistent_id() {

        final Exception exception = assertThrows(CourseNotFoundException.class,
                () -> courseService.findById(Mockito.anyLong())
        );

        assertTrue(exception.getMessage().contains("Curso"));
    }
}
