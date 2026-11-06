package edu.ifms.emprestimo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import edu.ifms.emprestimo.client.AcervoClient;
import edu.ifms.emprestimo.client.LeitorClient;
import edu.ifms.emprestimo.dto.EmprestimoDto;
import edu.ifms.emprestimo.dto.LeitorDto;
import edu.ifms.emprestimo.dto.LivroDto;
import edu.ifms.emprestimo.entity.Emprestimo;
import edu.ifms.emprestimo.repository.EmprestimoRepository;

@Service 
public class EmprestimoService {

  private final EmprestimoRepository emprestimoRepository;
  private final AcervoClient acervoClient;
  private final LeitorClient leitorClient;

  public EmprestimoService(EmprestimoRepository emprestimoRepository, AcervoClient acervoClient,
      LeitorClient leitorClient
  ) {
    this.emprestimoRepository = emprestimoRepository;
    this.acervoClient = acervoClient;
    this.leitorClient = leitorClient;
  }

  public Emprestimo cadastrar(Emprestimo emprestimo) {
    buscarLivro(emprestimo.getLivroId());

    buscarLeitor(emprestimo.getLeitorId());

    return emprestimoRepository.save(emprestimo);
  }

  public List<EmprestimoDto> buscarTodos() {
    List<Emprestimo> emprestimos = emprestimoRepository.findAll();

    List<EmprestimoDto> emprestimosDto = new ArrayList<>();

    for (Emprestimo emprestimo : emprestimos) {
      LivroDto livroDto = acervoClient.buscarLivro(emprestimo.getLivroId());
      LeitorDto leitorDto = leitorClient.buscarLeitor(emprestimo.getLeitorId());

      EmprestimoDto emprestimoDto = new EmprestimoDto(
          emprestimo.getId(),
          livroDto.id(),
          livroDto.titulo(),
          leitorDto.id(),
          leitorDto.nome(),
          emprestimo.getDataEmprestimo()
      );

      emprestimosDto.add(emprestimoDto);
    }

    return emprestimosDto;
  }

  // Verifica se o livro existe
  private LivroDto buscarLivro(Long id) {
    try {
      return acervoClient.buscarLivro(id);
    } catch (HttpClientErrorException.NotFound e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O livro informado não existe.");
    } catch (ResourceAccessException e) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "O serviço de acervo está indisponível.");
    }
  }

  // Verifica se o leitor existe
  private LeitorDto buscarLeitor(Long id) {
    try {
      return leitorClient.buscarLeitor(id);
    } catch (HttpClientErrorException.NotFound e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O leitor informado não existe.");
    } catch (ResourceAccessException e) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "O serviço de leitores está indisponível.");
    }
  }

}