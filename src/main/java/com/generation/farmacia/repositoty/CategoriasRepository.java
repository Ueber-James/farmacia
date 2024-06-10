package com.generation.farmacia.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.farmacia.model.Categorias;


public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
	
	public List<Categorias> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
