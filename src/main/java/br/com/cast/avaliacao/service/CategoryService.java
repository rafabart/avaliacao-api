package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.dto.request.CategoryRequest;
import br.com.cast.avaliacao.dto.response.CategoryResponse;
import br.com.cast.avaliacao.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    CategoryResponse findById(final Long id);

    List<CategoryResponse> findByDescription(final String description);

    Long create(final CategoryRequest categoryRequest);

    void delete(final Long id);

    void update(final Long id, final CategoryRequest categoryRequest);

    Category getOne(final Long id);
}
