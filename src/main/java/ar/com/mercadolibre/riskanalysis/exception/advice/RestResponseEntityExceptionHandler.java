package ar.com.mercadolibre.riskanalysis.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String GENERAL_APPLICATION_ERROR = "General Application Error";
    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    public static final String PRECONDICITON_FAILED = "PrecondicitonFailed";

//    @ResponseBody
//    @ExceptionHandler(value = { Exception.class })
//    protected ResponseEntity<Object> handleInternalServerError(RuntimeException ex, WebRequest request) {
//        LOG.error(GENERAL_APPLICATION_ERROR, ex);
//        return handleExceptionInternal(ex, GENERAL_APPLICATION_ERROR, new HttpHeaders(),
//                HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = { BusinessException.class })
//    protected ResponseEntity<Object> handlePrecondicitonFailed(RuntimeException ex, WebRequest request) {
//        LOG.error(PRECONDICITON_FAILED, ex);
//        return handlePrecondicitonFailed(ex, request);
//    }
}
