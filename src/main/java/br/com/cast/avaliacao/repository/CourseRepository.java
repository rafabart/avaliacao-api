package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(final LocalDate startDate, final LocalDate endDate);

    List<Course> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(final LocalDate startDate, final LocalDate endDate);

}
