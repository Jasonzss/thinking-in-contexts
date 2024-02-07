package com.jason.tics.dictionary.resource;

import com.jason.tics.common.core.response.ServerResponseEntity;
import com.jason.tics.dictionary.domain.extension.Variant;
import com.jason.tics.dictionary.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary/variant")
public class VariantResource {
    @Autowired
    private VariantRepository variantRepository;

    @GetMapping("/{word}")
    public ServerResponseEntity<List<Variant>> listVariants(@PathVariable String word){
        Variant byId = variantRepository.getById(word);
        return ServerResponseEntity.success(variantRepository.findAllByVariantsGroupId(byId.getVariantsGroupName()));
    }

    @PostMapping
    public ServerResponseEntity<Variant> addVariant(@Validated Variant variant){
        return ServerResponseEntity.success(variantRepository.save(variant));
    }

    @DeleteMapping("/{word}")
    public ServerResponseEntity<Void> deleteVariant(@PathVariable String word){
        variantRepository.deleteById(word);
        return ServerResponseEntity.success();
    }

    @PutMapping("/{word}")
    public ServerResponseEntity<Variant> updateVariant(@Validated Variant variant){
        return ServerResponseEntity.success(variant);
    }
}
