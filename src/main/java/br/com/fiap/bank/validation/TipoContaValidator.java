package br.com.fiap.bank.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoContaValidator implements ConstraintValidator<TipoConta, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("CORRENTE") || value.equals("POUPANCA") || value.equals("SALARIO");
    }

}