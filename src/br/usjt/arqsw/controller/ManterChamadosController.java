package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;
/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documenta��o:Essa classe corresponde ao controller de Chamados, aqui instanciamos os metodos e os services pra poder executar os metodos
 * tudo com inje��o de dependencia
 *
 */
@Controller
@Transactional
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;

	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException{
			return filaService.listarFilas();
	}
	
	
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				//model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				//return "ChamadoListar";
				return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);

			// TODO Código para carregar os chamados
			model.addAttribute("chamados", chamadoService.listarChamados(fila));
			
			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/criar_novo_chamado")
	public String criarNovoChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoCriar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/salvar_chamado")
	public String listarChamadosExibir(@Valid Chamado chamado, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				System.out.println("Deu erro " + result.toString());
				return "redirect:criar_novo_chamado";
			}
			
			int id = chamadoService.criarChamado(chamado);
			model.addAttribute("id_chamado", id);
			return "ChamadoSalvo";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	@RequestMapping("/criar_fila")
	public String criarFila(@Valid Fila fila, Model model) {
		model.addAttribute("fila", fila);
		return "FilaCriar";
		
	}
	@RequestMapping("/salvar_fila")
	public String listarFilaExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				System.out.println("Deu erro " + result.toString());
				return "redirect:criar";
			}
			
			int id = filaService.criar(fila);
			model.addAttribute("id_fila", id);
			return "FilaSalva";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
}
