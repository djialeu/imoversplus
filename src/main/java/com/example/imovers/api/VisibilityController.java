package com.example.imovers.api;

import com.example.imovers.annonces.Visibility.Visibility;
import com.example.imovers.annonces.Visibility.VisibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class VisibilityController {

    // Annotation
    @Autowired
    private VisibilityService service;

    // Save operation
    @PostMapping("/visibilities")
    public Visibility saveVisibility(
            @RequestBody Visibility Visibility)
    {

        return service.createVisibility(Visibility);
    }

    // Read operation
    @GetMapping("/visibilities")
    public List<Visibility> fetchVisibilityList()
    {
        return service.getVisibilities();
    }

    // Update operation
    @PutMapping("/visibilitys/{id}")
    public Visibility
    updateVisibility(@RequestBody Visibility Visibility,
               @PathVariable("id") Long id)
    {

        return null;
    }

    // Delete operation
    @DeleteMapping("/visibilities/{id}")
    public String deleteVisibilityById(@PathVariable("id")
                                         Long VisibilityId)
    {
        return null;
    }
    
}
