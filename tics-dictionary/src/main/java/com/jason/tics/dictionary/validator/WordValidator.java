package com.jason.tics.dictionary.validator;

import com.jason.tics.dictionary.constraints.IsWord;
import com.jason.tics.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Jason
 */
@Component
public class WordValidator implements ConstraintValidator<IsWord, String> {
    private final DictionaryService dictionaryService;

    @Autowired
    public WordValidator(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return dictionaryService.isWord(value);
    }
}
