package aepSegundoBimestre;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import aula20201027.produto.RegistroDuplicadoException;

@ControllerAdvice
public class ExceptionManager {
	
	/* @ExceptionHandler(ChaveDeRegistroInexistente.class)
	    public ResponseEntity<Void> handleConflict() {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }
	*/
}
