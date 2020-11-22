package aepSegundoBimestreVeiculo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {
	
	@ExceptionHandler(ChaveDuplicadoException.class)
    public ResponseEntity<Void> handleConflict() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
	
	@ExceptionHandler(NotFoundOnPutObject.class)
    public ResponseEntity<Void> notFoundId() {
        return ResponseEntity.notFound().build();
    }


}
