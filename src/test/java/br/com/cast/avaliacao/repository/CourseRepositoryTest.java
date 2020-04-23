package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void must_delete_course_by_id() {

        courseRepository.deleteById(1L);

        List<Course> courseList = courseRepository.findAll();

        assertThat(courseList.size()).isEqualTo(2);
    }


    @Test
    public void must_find_all_course() {

        List<Course> courseList = courseRepository.findAll();

        assertThat(courseList.size()).isEqualTo(3);
    }


    @Test
    public void must_update_description_of_course() {

        Course course = courseRepository.getOne(1L);
        course.setDescription("Nova Descrição");

        final Course courseSaved = courseRepository.save(course);

        assertThat(courseSaved.getId()).isEqualTo(1L);
        assertThat(courseSaved.getDescription()).isEqualTo("Nova Descrição");
    }
}
