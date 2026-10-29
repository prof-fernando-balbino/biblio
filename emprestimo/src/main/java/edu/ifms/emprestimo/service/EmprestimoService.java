package edu.ifms.emprestimo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import edu.ifms.emprestimo.entity.Emprestimo;
import edu.ifms.emprestimo.repository.EmprestimoRepository;

@Service 
public class EmprestimoService {

  private final EmprestimoRepository emprestimoRepository;

  public EmprestimoService(EmprestimoRepository emprestimoRepository) {
    this.emprestimoRepository = emprestimoRepository;
  }

  public Emprestimo cadastrar(Emprestimo emprestimo) {
    return emprestimoRepository.save(emprestimo);
  }

  public List<Emprestimo> buscarTodos() {
    return emprestimoRepository.findAll();
  }

}
