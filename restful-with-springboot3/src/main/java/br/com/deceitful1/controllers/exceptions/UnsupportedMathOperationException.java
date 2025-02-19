package br.com.deceitful1.controllers.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public UnsupportedMathOperationException(String msg)
    {
        super(msg);
    }


}
