package edu.ifms.emprestimo.dto;

public record LivroDto(

    Long id,
    String titulo,
    String autor,
    Integer anoPublicacao

) {}
