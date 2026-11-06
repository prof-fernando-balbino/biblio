package edu.ifms.emprestimo.dto;

import java.time.LocalDate;

public record EmprestimoDto(

    Long id,

    Long livroId,

    String titulo,

    Long leitorId,

    String nome,

    LocalDate dataEmprestimo

) {}
