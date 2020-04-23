package br.com.cast.avaliacao.exception;

public class InvertedDatesException extends RuntimeException {

    private final static String message = "Data de inicio do curso não pode ser inferior a data de conclusão";

    public InvertedDatesException() {
        super(message);
    }

    public InvertedDatesException(final Throwable cause) {

        super(message, cause);
    }
}
