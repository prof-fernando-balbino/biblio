package edu.ifms.emprestimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifms.emprestimo.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
