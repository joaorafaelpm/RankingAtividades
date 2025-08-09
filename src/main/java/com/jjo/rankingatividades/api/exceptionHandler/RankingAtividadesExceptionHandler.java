package com.jjo.rankingatividades.api.exceptionHandler;

import com.jjo.rankingatividades.domain.exceptions.EmailEmUsoException;
import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class RankingAtividadesExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource ;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais itens inválidos");
        problemDetail.setType(URI.create("https://rankingatividades/erros/invalid-components"));


        Map<String , String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField() ,
                        objectError -> messageSource.getMessage(objectError , LocaleContextHolder.getLocale()))) ;

        problemDetail.setProperty("campos com erro" , fields);

        return this.handleExceptionInternal(ex, problemDetail , headers, status, request);
    }

//      TODO : Fazer o exceptionHandler desta exceção, ela cuida de elementos que não podem ser nulos, porém não são do tipo MethodArgumentNotValidException

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
//        String errorMessage = "Erro de validação em algum capo do Objeto!";
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(EmailEmUsoException.class)
    public ProblemDetail handleNegocioAtividade (EmailEmUsoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("https://rankingatividades/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNaoEncontrado (NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("https://rankingatividades/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }




}

