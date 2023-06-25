package com.biblioteca.aluguel.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "Livro")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	private byte[] imagem;


	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@NotNull
	@Size(min = 4, max = 13, message = "ISBN deve ter 4 caracteres")
	private String isbn;
	@NotNull
	@Size(min = 1, max = 50, message = "Titulo deve ter entre 1 e 50 caracteres")
	private String titulo;
	@NotNull
	@Size(min = 1, max = 50, message = "Autor deve ter entre 1 e 50 caracteres")
	private String autor;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Livro() {
	}

	public Livro(String i, String t, String a,String descricao,byte[] imagem) {
		this.isbn = i;
		this.titulo = t;
		this.autor = a;
		this.descricao = descricao;
		this.imagem = imagem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
}