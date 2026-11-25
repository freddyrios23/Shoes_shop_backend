package com.example.Shoes_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shoes_shop.model.Boleta;



public interface BoletaRepository extends JpaRepository<Boleta,Integer> {

    Optional<Boleta> findFirstByBoletas_Id(Integer zapatillaId);
}
