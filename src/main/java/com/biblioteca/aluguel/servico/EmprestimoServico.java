package com.biblioteca.aluguel.servico;

import com.biblioteca.aluguel.model.Emprestimo;

import java.util.List;

public interface EmprestimoServico {
	public Iterable<Emprestimo> findAll();

	List<Emprestimo> findAllEmprestimos();

	Emprestimo findById(Long id);

	Emprestimo save(Emprestimo emprestimo);

	public String registraEmprestimo(Emprestimo emprestimo);

	String registraDevolucao(Emprestimo emprestimo);

	public void deleteById(Long id);
}
