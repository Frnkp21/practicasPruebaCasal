package com.example.Dao;

import com.example.Entities.ActividadesTipo;
import com.example.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActividadesTipoDAO extends JpaRepository<ActividadesTipo, Integer> {
    List<ActividadesTipo> findAll();
    Optional<ActividadesTipo> findById(Integer id);

}
