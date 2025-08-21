package com.jjo.rankingatividades.api.exceptionHandler;

import com.jjo.rankingatividades.api.models.ErrorResponse;
import com.jjo.rankingatividades.domain.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

/**
 * Classe responsável por capturar e tratar exceções de forma centralizada.
 *
 * A anotação @RestControllerAdvice indica ao Spring que esta classe será
 * aplicada globalmente a todos os controladores REST da aplicação.
 *
 * Isso garante respostas consistentes para erros e evita duplicação
 * de código de tratamento em cada controlador.
 *
 * O uso do ProblemDetail segue a especificação RFC 7807,
 * que define um formato padronizado para representar erros HTTP.
 * Ele já é suportado nativamente no Spring 6+ e facilita a serialização
 * de respostas estruturadas para o cliente.
 */
@AllArgsConstructor
@RestControllerAdvice // Indica que esta classe centraliza o tratamento de exceções para os controladores REST
public class RankingAtividadesExceptionHandler extends ResponseEntityExceptionHandler {

    // Usado para resolver mensagens de erro internacionalizadas (i18n)
    private final MessageSource messageSource;

    /**
     * Trata erros de validação de campos quando a anotação @Valid falha.
     * Isso ocorre quando o corpo da requisição viola regras como @NotNull, @Size, etc.
     *
     * Aqui, além da mensagem genérica, adicionamos uma propriedade extra
     * ("camposComErro") contendo um mapa campo -> mensagem de erro.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//        Classe padrão para responder os erros
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais itens inválidos");
        problemDetail.setType(URI.create("https://rankingatividades/erros/invalid-components"));


//        Pegando os campos com erro
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


    /**
     * Trata EmailEmUsoException retornando ProblemDetail com status 400 (Bad Request).
     * O type define um link para referência de regra de negócio.
     */
    @ExceptionHandler(EmailEmUsoException.class)
    public ProblemDetail handleEmailEmUso (EmailEmUsoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("https://rankingatividades/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }



    /**
     * Trata NotFoundException retornando ProblemDetail com status 404 (Not Found).
     */
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNaoEncontrado (NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("https://rankingatividades/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }
    /**
     * Trata tentativas de cadastro ou acesso por usuários com números no nome.
     */
    @ExceptionHandler(NomeIncorretoException.class)
    public ProblemDetail handleNomeIncorreto (NomeIncorretoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("https://rankingatividades/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }

    /**
     * Trata tentativas de cadastro ou acesso por usuários menores de 13 anos.
     */
    @ExceptionHandler(AnoNascimentoException.class)
    public ProblemDetail handleNomeIncorreto (AnoNascimentoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("https://rankingatividades/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }


    public ResponseEntity<ErrorResponse> handleNotFoundWithErrorResponse(NotFoundException e) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    public ResponseEntity<ErrorResponse> handleMenorDeTrezeWithErrorResponse(AnoNascimentoException e) {
        ErrorResponse error = new ErrorResponse("BAD_REQUEST", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    public ResponseEntity<ErrorResponse> handleNumeroNoNome(NomeIncorretoException e) {
        ErrorResponse error = new ErrorResponse("BAD_REQUEST", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public ResponseEntity<ErrorResponse> handleEmailEmUsoWithErrorResponse(EmailEmUsoException e) {
        ErrorResponse error = new ErrorResponse("CONFLICT", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    /**
     * Tratamento genérico para exceções não mapeadas.
     * Retorna 500 (Internal Server Error) com mensagem genérica para o cliente.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "Erro interno do servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
