package br.com.cast.avaliacao.exception;

public class CourseNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Curso não encontrado";

    public CourseNotFoundException() {
        super(MESSAGE);
    }

    public CourseNotFoundException(final Throwable cause) {

        super(MESSAGE, cause);
    }
}
