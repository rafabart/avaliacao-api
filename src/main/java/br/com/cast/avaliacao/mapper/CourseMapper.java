package br.com.cast.avaliacao.mapper;

import br.com.cast.avaliacao.dto.request.CourseRequest;
import br.com.cast.avaliacao.dto.response.CourseResponse;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CategoryMapper.class})
public interface CourseMapper {

    Course to(final CourseRequest courseRequest);

    void toUpdate(@MappingTarget Course course, final CourseRequest courseRequest);

    @Mapping(target = "category_id", source = "category.id")
    CourseResponse toReponse(final Course course);

    List<CourseResponse> toResponseList(final List<Course> courseList);
}
