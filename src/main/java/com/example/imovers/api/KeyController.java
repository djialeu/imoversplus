package com.example.imovers.api;

import com.example.imovers.annonces.Key.Key;
import com.example.imovers.annonces.Key.KeyService;
import com.example.imovers.annonces.Type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class KeyController {
    @Autowired
    private KeyService service;

    @PostMapping("/keys")
    public Key saveKey()
    {
        return service.generateKey();
    }

    @GetMapping("/keys")
    public Key getKey(@RequestParam() String cle){
        Key k = service.getKey(cle);
        if( k != null){
            service.deleteKey(k.getKey());
        }
        return k;
    }

    @DeleteMapping("/keys/{key}")
    public void deleteKey(@PathVariable String key){
       service.deleteKey(key);
    }


}
