package com.generation.farmacia.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
