package com.example.Shoes_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Shoes_shop.model.Zapatilla;


@Repository
public interface ZapatillaRepository extends JpaRepository<Zapatilla,Integer>{

}
