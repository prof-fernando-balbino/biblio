package edu.ifms.acervo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifms.acervo.entity.Livro;
import edu.ifms.acervo.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

  private final LivroService livroService;

  public LivroController(LivroService livroService) {
    this.livroService = livroService;
  }

  @PostMapping
  public ResponseEntity<Livro> create(@RequestBody Livro livro) {
    Livro livroSalvo = livroService.cadastrar(livro);

    return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
  }

  @GetMapping
  public ResponseEntity<List<Livro>> readAll() {
    return ResponseEntity.ok(livroService.buscarTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Livro> readById(@PathVariable Long id) {
    Livro livro = livroService.buscarPorId(id);

    if (livro == null)
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(livro);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livro) {
    Livro livroAtualizado = livroService.atualizar(id, livro);

    if (livroAtualizado == null)
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(livroAtualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluir(@PathVariable Long id) {
    boolean excluido = livroService.excluir(id);

    if (! excluido)
      return ResponseEntity.notFound().build();

    return ResponseEntity.noContent().build();
  }

}
