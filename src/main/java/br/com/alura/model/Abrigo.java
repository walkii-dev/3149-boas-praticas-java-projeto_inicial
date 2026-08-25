package br.com.alura.model;

import java.util.ArrayList;
import java.util.List;

public class Abrigo {

    public Abrigo (String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;

    }

    public Abrigo() {
    }

    private long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Pet[] getPets() {
        return pets;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
