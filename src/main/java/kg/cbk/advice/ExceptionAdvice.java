package kg.cbk.advice;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public void pSQLException(RuntimeException e, HttpServletResponse response) throws IOException {
        LOGGER.error(e.getMessage(), e);
        response.sendError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Невозможно выполнить действие. Выполнение приведёт к нарушению целостности базы данных"
        );
    }

    @ExceptionHandler(value = {NonUniqueResultException.class})
    public void nonUniqueResultException(RuntimeException e, HttpServletResponse response) throws IOException {
        LOGGER.error(e.getMessage(), e);
        response.sendError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Нарушение уникальности возвращаемой записи"
        );
    }

}
