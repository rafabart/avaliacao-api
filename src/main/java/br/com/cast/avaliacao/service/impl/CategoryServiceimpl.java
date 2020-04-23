package br.com.cast.avaliacao.service.impl;

import br.com.cast.avaliacao.dto.request.CategoryRequest;
import br.com.cast.avaliacao.dto.response.CategoryResponse;
import br.com.cast.avaliacao.exception.CategoryNotFoundException;
import br.com.cast.avaliacao.mapper.CategoryMapper;
import br.com.cast.avaliacao.model.Category;
import br.com.cast.avaliacao.repository.CategoryRepository;
import br.com.cast.avaliacao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceimpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;


    public List<CategoryResponse> findAll() {
        return categoryMapper.toResponseList(categoryRepository.findAll());
    }


    public CategoryResponse findById(final Long id) {

        final Category category = getOne(id);

        return categoryMapper.toReponse(category);
    }


    public List<CategoryResponse> findByDescription(final String description) {

        final List<Category> categoryList = categoryRepository.findAllByDescriptionContainingIgnoreCase(description);

        return categoryMapper.toResponseList(categoryList);
    }


    @Transactional
    public Long create(final CategoryRequest categoryRequest) {

        final Category category = categoryMapper.to(categoryRequest);

        return categoryRepository.save(category).getId();
    }


    @Transactional
    public void delete(final Long id) {

        getOne(id);

        categoryRepository.deleteById(id);
    }


    @Transactional
    public void update(final Long id, final CategoryRequest categoryRequest) {

        Category categoryFromBase = getOne(id);

        categoryMapper.toUpdate(categoryFromBase, categoryRequest);

        categoryRepository.save(categoryFromBase);
    }


    public Category getOne(final Long id) {

        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
