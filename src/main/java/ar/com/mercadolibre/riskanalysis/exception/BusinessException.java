package ar.com.mercadolibre.riskanalysis.exception;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
    }
}
