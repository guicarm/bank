package br.com.fiap.bank.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoContaValidator.class)
public @interface TipoConta {
    
    String message() default "{movimentacao.tipo.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
