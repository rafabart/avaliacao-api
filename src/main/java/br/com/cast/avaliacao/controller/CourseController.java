package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.dto.request.CourseRequest;
import br.com.cast.avaliacao.dto.response.CourseResponse;
import br.com.cast.avaliacao.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<CourseResponse>> findAll(
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(value = "category_id", required = false) Long category_id
    ) {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setDescription(description);
        courseRequest.setStartDate(startDate);
        courseRequest.setEndDate(endDate);
        courseRequest.setCategory_id(category_id);

        List<CourseResponse> courseResponseList = courseService.findAllWithFilter(courseRequest);

        return ResponseEntity.ok(courseResponseList);
    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<CourseResponse> findById(@PathVariable("id") final Long id) {

        final CourseResponse courseResponse = courseService.findById(id);

        return ResponseEntity.ok(courseResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<URI> create(@Valid @RequestBody final CourseRequest courseRequest) {

        final Long id = courseService.create(courseRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Void> update(@PathVariable("id") final Long id,
                                       @Valid @RequestBody final CourseRequest courseRequest) {

        courseService.update(id, courseRequest);

        return ResponseEntity.noContent().build();
    }
}
