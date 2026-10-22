package edu.ifms.acervo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ifms.acervo.entity.Livro;
import edu.ifms.acervo.repository.LivroRepository;

@Service
public class LivroService {

  private final LivroRepository livroRepository;

  public LivroService(LivroRepository livroRepository) {
    this.livroRepository = livroRepository;
  }

  public Livro cadastrar(Livro livro) {
    return livroRepository.save(livro);
  }

  public List<Livro> buscarTodos() {
    return livroRepository.findAll();
  }

  public Livro buscarPorId(Long id) {
    return livroRepository.findById(id).orElse(null);

    // Optional<Livro> livro = livroRepository.findById(id);

    // if (livro.isPresent())
    //   return livro.get();

    // return null;
  }

  public Livro atualizar(Long id, Livro livro) {
    Livro livroExistente = buscarPorId(id);

    if (livroExistente == null)
      return null;

    livroExistente.setTitulo(livro.getTitulo());
    livroExistente.setAutor(livro.getAutor());
    livroExistente.setAnoPublicacao(livro.getAnoPublicacao());

    return livroRepository.save(livroExistente);
  }

  public boolean excluir(Long id) {
    if (! livroRepository.existsById(id))
      return false;

    livroRepository.deleteById(id);

    return true;
  }

}
