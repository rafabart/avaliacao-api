package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByDescriptionContainingIgnoreCase(final String description);
}
