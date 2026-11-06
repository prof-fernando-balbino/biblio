package edu.ifms.emprestimo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import edu.ifms.emprestimo.dto.LivroDto;

@Component 
public class AcervoClient {

  private final RestClient restClient;

  public AcervoClient(@Value("${services.acervo.url}") String baseUrl) {
    System.out.println("Value = " + baseUrl);
    restClient = RestClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public LivroDto buscarLivro(Long id) {
    return restClient.get()
        .uri("/livros/{id}", id)
        .retrieve()
        .body(LivroDto.class);
  }

}
