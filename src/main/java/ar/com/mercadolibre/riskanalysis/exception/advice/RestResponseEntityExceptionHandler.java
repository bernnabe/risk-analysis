package ar.com.mercadolibre.riskanalysis.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String GENERAL_APPLICATION_ERROR = "General Application Error";
    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

}
