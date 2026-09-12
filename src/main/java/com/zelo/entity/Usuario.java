package com.zelo.entity;

import java.time.LocalDate;

public class Usuario {

    private long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    private String tipoUsuario;
    private String telefone;
    private String fotoUrl;
    private String tipoSanguineo;
    private String alergias;
    private String condicaoSaude;
    private String observacoesImportantes;
    private Usuario cuidador;
}
