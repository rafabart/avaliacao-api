package br.com.cast.avaliacao.exception;

public class UniquenessDatesException extends RuntimeException {

    private final static String MESSAGE = "Existe(m) curso(s) planejados(s) dentro do período informado.";

    public UniquenessDatesException() {
        super(MESSAGE);
    }

    public UniquenessDatesException(final Throwable cause) {

        super(MESSAGE, cause);
    }
}
