package br.com.cast.avaliacao.service.impl;

import br.com.cast.avaliacao.dto.request.CourseRequest;
import br.com.cast.avaliacao.dto.response.CourseResponse;
import br.com.cast.avaliacao.exception.CourseNotFoundException;
import br.com.cast.avaliacao.exception.InvertedDatesException;
import br.com.cast.avaliacao.exception.OutdatedDateException;
import br.com.cast.avaliacao.exception.UniquenessDatesException;
import br.com.cast.avaliacao.mapper.CourseMapper;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.model.Course;
import br.com.cast.avaliacao.repository.CourseRepository;
import br.com.cast.avaliacao.service.CategoryService;
import br.com.cast.avaliacao.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseMapper courseMapper;


    public List<CourseResponse> findAllWithFilter(final CourseRequest courseRequest) {

        Course course = courseMapper.to(courseRequest);

        if (courseRequest.getCategory_id() != null) {
            final Category category = categoryService.getOne(courseRequest.getCategory_id());
            course.setCategory(category);
        }

        final Example exampleAccount = Example.of(course,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        final List<Course> courseList = courseRepository.findAll(exampleAccount);

        return courseMapper.toResponseList(courseList);
    }


    public CourseResponse findById(final Long id) {

        final Course course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        return courseMapper.toReponse(course);
    }


    @Transactional
    public Long create(final CourseRequest courseRequest) {

        dateValidationFormat(courseRequest.getStartDate(), courseRequest.getEndDate());

        dateValidationUniquenessCreate(courseRequest.getStartDate(), courseRequest.getEndDate());

        final Category category = categoryService.getOne(courseRequest.getCategory_id());

        Course course = courseMapper.to(courseRequest);
        course.setCategory(category);

        return courseRepository.save(course).getId();
    }

    @Transactional
    public void delete(final Long id) {

        findById(id);

        courseRepository.deleteById(id);
    }


    @Transactional
    public void update(final Long id, final CourseRequest courseRequest) {

        dateValidationFormat(courseRequest.getStartDate(), courseRequest.getEndDate());

        findById(id);

        dateValidationUniquenessUpdate(courseRequest.getStartDate(), courseRequest.getEndDate(), id);

        final Category category = categoryService.getOne(courseRequest.getCategory_id());
        Course courseFromBase = courseRepository.getOne(id);

        courseMapper.toUpdate(courseFromBase, courseRequest);
        courseFromBase.setCategory(category);

        courseRepository.save(courseFromBase);
    }


    void dateValidationFormat(final LocalDate startDate, final LocalDate endDate) {

        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new OutdatedDateException();
        }

        if (startDate.isAfter(endDate)) {
            throw new InvertedDatesException();
        }
    }


    void dateValidationUniquenessUpdate(final LocalDate startDate, final LocalDate endDate, final long id) {

        Course course = new Course();
        course.setId(id);

        List<Course> courseList = new ArrayList<>();

        courseList = courseRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
        if (courseList.size() > 0 && !courseList.contains(course)) {
            throw new UniquenessDatesException();
        }

        courseList = courseRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
        if (courseList.size() > 0 && !courseList.contains(course)) {
            throw new UniquenessDatesException();
        }

        courseList = courseRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, startDate);
        if (courseList.size() > 0 && !courseList.contains(course)) {
            throw new UniquenessDatesException();
        }

        courseList = courseRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(endDate, endDate);
        if (courseList.size() > 0 && !courseList.contains(course)) {
            throw new UniquenessDatesException();
        }
    }


    void dateValidationUniquenessCreate(final LocalDate startDate, final LocalDate endDate) {

        List<Course> courseList = new ArrayList<>();

        courseList = courseRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
        if (courseList.size() > 0) {
            throw new UniquenessDatesException();
        }

        courseList = courseRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
        if (courseList.size() > 0) {
            throw new UniquenessDatesException();
        }

        courseList = courseRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, startDate);
        if (courseList.size() > 0) {
            throw new UniquenessDatesException();
        }

        courseList = courseRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(endDate, endDate);
        if (courseList.size() > 0) {
            throw new UniquenessDatesException();
        }
    }
}
