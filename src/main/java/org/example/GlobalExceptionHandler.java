package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //Класс будет обрабатывать исключения для всех контроллеров
@RestController //методы возвращают данные в формате JSON
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class) //указывает какой тип исключений будет обработан
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //указывает, какой тип HTTP статус кода для ответа
    public ResponseEntity <String> handException (Exception e){ //метод логирует ошибку и возвращает сообщение в формате JSON
        logger.error("An unexpected error occurred: ", e);
        return new ResponseEntity<>("An error occured " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
