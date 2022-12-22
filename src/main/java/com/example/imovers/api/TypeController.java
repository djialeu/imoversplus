package com.example.imovers.api;

import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import com.example.imovers.annonces.Type.Type;
import com.example.imovers.annonces.Type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TypeController {

    // Annotation
    @Autowired
    private TypeService service;

    // Save operation
    @PostMapping("/types")
    public Type saveType(
            @RequestBody Type Type)
    {

        return service.createType(Type);
    }

    // Read operation
    @GetMapping("/types")
    public List<Type> fetchTypeList()
    {
        return service.getTypes();
    }

    // Update operation
    @PutMapping("/types/{id}")
    public Type
    updateType(@RequestBody Type Type,
                     @PathVariable("id") Long id)
    {

        return null;
    }

    // Delete operation
    @DeleteMapping("/types/{id}")
    public String deleteTypeById(@PathVariable("id")
                                               Long TypeId)
    {
        return null;
    }

}
