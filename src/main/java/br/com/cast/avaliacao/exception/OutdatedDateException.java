package br.com.cast.avaliacao.exception;

public class OutdatedDateException extends RuntimeException {

    private final static String MESSAGE = "Data de inicio e/ou conclusão do curso inferior(es) a data atual";

    public OutdatedDateException() {
        super(MESSAGE);
    }

    public OutdatedDateException(final Throwable cause) {

        super(MESSAGE, cause);
    }
}
