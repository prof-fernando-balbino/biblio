package edu.ifms.leitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifms.leitor.entity.Leitor;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {

  // vários métodos úteis para implementar CRUD já são automaticamente herdados

}
