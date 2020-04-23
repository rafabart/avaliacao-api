package br.com.cast.avaliacao.mapper;

import br.com.cast.avaliacao.dto.request.CategoryRequest;
import br.com.cast.avaliacao.dto.response.CategoryResponse;
import br.com.cast.avaliacao.model.Category;
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
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category to(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category category = new Category();

        category.setDescription( categoryRequest.getDescription() );

        return category;
    }

    @Override
    public void toUpdate(Category category, CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return;
        }

        category.setDescription( categoryRequest.getDescription() );
    }

    @Override
    public CategoryResponse toReponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        if ( category.getId() != null ) {
            categoryResponse.setId( category.getId() );
        }
        categoryResponse.setDescription( category.getDescription() );

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> toResponseList(List<Category> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categoryList.size() );
        for ( Category category : categoryList ) {
            list.add( toReponse( category ) );
        }

        return list;
    }
}
