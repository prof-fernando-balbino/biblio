package edu.ifms.emprestimo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifms.emprestimo.entity.Emprestimo;
import edu.ifms.emprestimo.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

  private final EmprestimoService emprestimoService;

  public EmprestimoController(EmprestimoService emprestimoService) {
    this.emprestimoService = emprestimoService;
  }

  @PostMapping 
  public ResponseEntity<Emprestimo> create(@RequestBody Emprestimo emprestimo) {
    Emprestimo novoEmprestimo = emprestimoService.cadastrar(emprestimo);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(novoEmprestimo);
  }

  @GetMapping 
  public List<Emprestimo> getAll() {
    return emprestimoService.buscarTodos();
  }

}
