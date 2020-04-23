package br.com.cast.avaliacao.mapper;

import br.com.cast.avaliacao.dto.request.CategoryRequest;
import br.com.cast.avaliacao.dto.response.CategoryResponse;
import br.com.cast.avaliacao.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category to(final CategoryRequest categoryRequest);

    void toUpdate(@MappingTarget Category category, final CategoryRequest categoryRequest);

    CategoryResponse toReponse(final Category category);

    List<CategoryResponse> toResponseList(final List<Category> categoryList);
}
