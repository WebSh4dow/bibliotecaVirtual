package com.biblioteca.aluguel.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.aluguel.model.Emprestimo;
import com.biblioteca.aluguel.model.EmprestimoRepository;
import com.biblioteca.aluguel.model.Livro;
import com.biblioteca.aluguel.model.LivroRepository;
import com.biblioteca.aluguel.model.Usuario;
import com.biblioteca.aluguel.model.UsuarioRepository;

import java.util.List;

@Service
public class EmprestimoServicoI implements EmprestimoServico {
	@Autowired
	EmprestimoRepository emprestimoRepository;
	@Autowired
	LivroRepository livroRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	Logger logger = LogManager.getLogger(EmprestimoServicoI.class);

	public Iterable<Emprestimo> findAll() {
		return emprestimoRepository.findAll();
	}

	@Override
	public List<Emprestimo> findAllEmprestimos() {
		return emprestimoRepository.findAll();
	}

	@Override
	public Emprestimo findById(Long id) {
		return emprestimoRepository.findById(id).orElse(null);
	}

	@Override
	public Emprestimo save(Emprestimo emprestimo) {
		emprestimoRepository.save(emprestimo);
		return emprestimo;
	}

	@Override
	public String registraEmprestimo(Emprestimo emprestimo) {
		String message = "";
		Livro livro = null;
		Usuario usuario = null;
		livro = livroRepository.findByIsbn(emprestimo.getIsbn());
		usuario = usuarioRepository.findByRa(emprestimo.getRa());
		if (livro != null && usuario != null) { // verfica se o livro existe e se o usuário existe.
			logger.info("registra emprestimo => " + emprestimo.getRa());
			DateTime dataAtual = new DateTime();
			emprestimo.setDataEmprestimo(dataAtual);
			emprestimoRepository.save(emprestimo);
		} else {
			message = "fail";
		}
		return message;
	}


	@Override
	public String registraDevolucao(Emprestimo emprestimo) {
		String message = "";
		Livro emprestimos = emprestimoRepository.findByIsbn(emprestimo.getIsbn());
		if (emprestimo != null) {
			logger.info("registra devolucao => ISBN: " + emprestimos.getIsbn());
			DateTime dataAtual = new DateTime();
			emprestimo.setDataDevolucao(String.valueOf(dataAtual));
			emprestimoRepository.save(emprestimo);
		} else {
			message = "fail";
		}
		return message;
	}

	@Override
	public void deleteById(Long id) {
		emprestimoRepository.deleteById(id);
	}
}