package com.jjo.rankingatividades.api.exceptionHandler;

import com.jjo.rankingatividades.api.models.ErrorResponse;
import com.jjo.rankingatividades.domain.exceptions.AlunoEAtividadeException;
import com.jjo.rankingatividades.domain.exceptions.AnoNascimentoException;
import com.jjo.rankingatividades.domain.exceptions.EmailEmUsoException;
import com.jjo.rankingatividades.domain.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
//    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable           (HttpMessageNotReadableException ex) {
//        ErrorResponse error = new ErrorResponse("INVALID_FORMAT", "Formato de dados inválido no corpo da requisição");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }


    @ExceptionHandler(EmailEmUsoException.class)
    public ProblemDetail handleEmailEmUso (EmailEmUsoException e) {
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

    // Método alternativo usando ErrorResponse para APIs que preferem esse formato
    // Mantido o método original (handleNaoEncontrado) para compatibilidade
    public ResponseEntity<ErrorResponse> handleNotFoundWithErrorResponse(NotFoundException e) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    public ResponseEntity<ErrorResponse> handleMenorDeTrezeWithErrorResponse(AnoNascimentoException e) {
        ErrorResponse error = new ErrorResponse("BAD_REQUEST", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public ResponseEntity<ErrorResponse> handleEmailEmUsoWithErrorResponse(EmailEmUsoException e) {
        ErrorResponse error = new ErrorResponse("CONFLICT", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Método alternativo usando ErrorResponse para exceções de negócio
    // Mantido o método original (handleNegocioAtividade) para compatibilidade
    public ResponseEntity<ErrorResponse> handleBusinessWithErrorResponse(AlunoEAtividadeException e) {
        ErrorResponse error = new ErrorResponse("BUSINESS_ERROR", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //Tratamento genérico para exceções não mapeadas
    // Aqui usamos o método genérico com a classe padrão de exceções
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "Erro interno do servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // Esse UnsupportedOperationException representa um método de tratamento de exceção não implementado

    // public static String isNomeValido(Object object) {
    //     throw new UnsupportedOperationException("Unimplemented method 'isNomeValido'");
    // }



}

