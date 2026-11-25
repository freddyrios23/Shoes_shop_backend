package com.example.Shoes_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shoes_shop.model.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
