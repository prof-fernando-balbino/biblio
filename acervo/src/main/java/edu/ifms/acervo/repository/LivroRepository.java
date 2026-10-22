package edu.ifms.acervo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifms.acervo.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

  // vários métodos úteis para implementar CRUD já são automaticamente herdados

}
