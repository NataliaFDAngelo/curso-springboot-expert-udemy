package br.com.curso.springboot.sistemavendas.exception;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super(message);
    }
}
