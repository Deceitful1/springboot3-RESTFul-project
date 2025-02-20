package br.com.deceitful1.exceptions;


import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details)
{

}
