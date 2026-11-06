package edu.ifms.emprestimo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import edu.ifms.emprestimo.dto.LeitorDto;

@Component 
public class LeitorClient {

  private final RestClient restClient;

  public LeitorClient(@Value("${services.leitor.url}") String baseUrl) {
    restClient = RestClient.builder()
        .baseUrl(baseUrl)
        .build();
  }

  public LeitorDto buscarLeitor(Long id) {
    return restClient.get()
        .uri("/leitores/{id}", id)
        .retrieve()
        .body(LeitorDto.class);
  }

}
