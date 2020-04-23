package br.com.cast.avaliacao.exception;

public class CategoryNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Categoria não encontrada";

    public CategoryNotFoundException() {
        super(MESSAGE);
    }

    public CategoryNotFoundException(final Throwable cause) {

        super(MESSAGE, cause);
    }
}
