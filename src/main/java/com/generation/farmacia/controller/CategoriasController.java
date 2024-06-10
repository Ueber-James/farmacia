package com.generation.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Categorias;
import com.generation.farmacia.repositoty.CategoriasRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {
	
	 @Autowired
	    private CategoriasRepository categoriaRepository;
	    
	    @GetMapping
	    public ResponseEntity<List<Categorias>> getAll(){
	        return ResponseEntity.ok(categoriaRepository.findAll());
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Categorias> getById(@PathVariable Long id){
	        return categoriaRepository.findById(id)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	    
	    @GetMapping("/categoria/{nome}")
	    public ResponseEntity<List<Categorias>> getByTitle(@PathVariable 
	    String nome){
	        return ResponseEntity.ok(categoriaRepository
	            .findAllByNomeContainingIgnoreCase(nome));
	    }
	    
	    @PostMapping
	    public ResponseEntity<Categorias> post(@Valid @RequestBody Categorias categoria){
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(categoriaRepository.save(categoria));
	    }
	    
	    @PutMapping
	    public ResponseEntity<Categorias> put(@Valid @RequestBody Categorias categoria){
	        return categoriaRepository.findById(categoria.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
	            .body(categoriaRepository.save(categoria)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	    
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        Optional<Categorias> categoria = categoriaRepository.findById(id);
	        
	        if(categoria.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        
	        categoriaRepository.deleteById(id);              
	    }

}
