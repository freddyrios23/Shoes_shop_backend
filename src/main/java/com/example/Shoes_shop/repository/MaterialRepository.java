package com.example.Shoes_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Shoes_shop.model.Material;


@Repository
public  interface MaterialRepository  extends JpaRepository<Material,Integer> {
}
