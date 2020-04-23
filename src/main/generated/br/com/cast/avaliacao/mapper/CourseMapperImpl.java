package br.com.cast.avaliacao.mapper;

import br.com.cast.avaliacao.dto.request.CourseRequest;
import br.com.cast.avaliacao.dto.response.CourseResponse;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.model.Course;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-21T13:20:24-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (Ubuntu)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course to(CourseRequest courseRequest) {
        if ( courseRequest == null ) {
            return null;
        }

        Course course = new Course();

        course.setDescription( courseRequest.getDescription() );
        course.setStartDate( courseRequest.getStartDate() );
        course.setEndDate( courseRequest.getEndDate() );
        course.setNumberOfStudents( courseRequest.getNumberOfStudents() );

        return course;
    }

    @Override
    public void toUpdate(Course course, CourseRequest courseRequest) {
        if ( courseRequest == null ) {
            return;
        }

        course.setDescription( courseRequest.getDescription() );
        course.setStartDate( courseRequest.getStartDate() );
        course.setEndDate( courseRequest.getEndDate() );
        course.setNumberOfStudents( courseRequest.getNumberOfStudents() );
    }

    @Override
    public CourseResponse toReponse(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setCategory_id( courseCategoryId( course ) );
        courseResponse.setId( course.getId() );
        courseResponse.setDescription( course.getDescription() );
        courseResponse.setStartDate( course.getStartDate() );
        courseResponse.setEndDate( course.getEndDate() );
        courseResponse.setNumberOfStudents( course.getNumberOfStudents() );
        courseResponse.setCategory( course.getCategory() );

        return courseResponse;
    }

    @Override
    public List<CourseResponse> toResponseList(List<Course> courseList) {
        if ( courseList == null ) {
            return null;
        }

        List<CourseResponse> list = new ArrayList<CourseResponse>( courseList.size() );
        for ( Course course : courseList ) {
            list.add( toReponse( course ) );
        }

        return list;
    }

    private Long courseCategoryId(Course course) {
        if ( course == null ) {
            return null;
        }
        Category category = course.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
