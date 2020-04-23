package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.dto.request.CourseRequest;
import br.com.cast.avaliacao.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse findById(final Long id);

    Long create(final CourseRequest courseRequest);

    void delete(final Long id);

    void update(final Long id, final CourseRequest courseRequest);

    List<CourseResponse> findAllWithFilter(final CourseRequest courseRequest);
}
