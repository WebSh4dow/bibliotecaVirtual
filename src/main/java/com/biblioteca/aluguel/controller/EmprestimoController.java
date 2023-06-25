package com.biblioteca.aluguel.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.biblioteca.aluguel.model.Emprestimo;
import com.biblioteca.aluguel.servico.EmprestimoServico;

@RestController
@RequestMapping(path = "/emprestimos")
public class EmprestimoController {
	@Autowired
	private EmprestimoServico servico;
	Logger logger = LogManager.getLogger(EmprestimoController.class);

	@GetMapping("/cadastrar")
	public ModelAndView registrarEmprestimo(Emprestimo emprestimo) {
		ModelAndView mv = new ModelAndView("registrarEmprestimo");
		mv.addObject("emprestimo", emprestimo);
		return mv;
	}

	@GetMapping("/consultar")
	public ModelAndView retornaFormDeConsultaTodosLivros() {
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		modelAndView.addObject("emprestimos", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Emprestimo emprestimo, BindingResult result) {
		logger.info("emprestimo ==> " + emprestimo.getRa());
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		if (result.hasErrors()) {
			logger.info("emprestimo ==> ERRO NO RESULTADO");
			modelAndView.setViewName("registrarEmprestimo");
		}
		try {
			String message = servico.registraEmprestimo(emprestimo);
			modelAndView.addObject("emprestimos", servico.findAll());
			if (message.equals("fail")) {
				modelAndView.setViewName("registrarEmprestimo");
				modelAndView.addObject("fail", "Dados invalidos."); // fail nao eh nulo a msg aparece na tela
			}
		} catch (Exception e) {
			logger.error("========> Exceçao nao prevista - save() cadastra emprestimo");
			modelAndView.setViewName("registrarEmprestimo");
			modelAndView.addObject("fail", "Exceçao nao prevista - consulte o administrador =>" + e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id, Emprestimo emprestimo) {
		ModelAndView modelAndView = new ModelAndView("EditarEmprestimo");

		try {
			Emprestimo emprestimoExistente = servico.findById(id);
			if (emprestimoExistente != null) {
				emprestimo.setId(emprestimoExistente.getId());
				emprestimo.setIsbn(emprestimoExistente.getIsbn());
				emprestimo.setRa(emprestimoExistente.getRa());
				emprestimo.setDataDevolucao(emprestimoExistente.getDataDevolucao());
				Emprestimo message = servico.save(emprestimo);
				modelAndView.addObject("emprestimo", emprestimo);

			} else {
				modelAndView.addObject("fail", "Empréstimo não encontrado.");
			}
		} catch (Exception e) {
			logger.error("Exceção não prevista - edit() editar empréstimo: " + e.getMessage());
			modelAndView.addObject("fail", "Exceção não prevista - consulte o administrador: " + e.getMessage());
		}

		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		servico.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		modelAndView.addObject("emprestimos", servico.findAll());
		return modelAndView;
	}


}