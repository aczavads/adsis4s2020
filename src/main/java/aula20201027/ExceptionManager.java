package aula20201027;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import aula20201027.produto.RegistroDuplicadoException;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNoSuchElementException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<Void> handleRegistroDuplicadoException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    
}
