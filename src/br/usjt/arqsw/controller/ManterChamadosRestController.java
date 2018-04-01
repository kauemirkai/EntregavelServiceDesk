package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;


/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documentação:Rest controller para controlar as requisições rest por url
 */

@RestController
public class ManterChamadosRestController {
	private ChamadoService cService;
	private FilaService fService;
	
	
	@Autowired
	public ManterChamadosRestController(ChamadoService cs, FilaService fs) {
		cService  = cs;
		fService = fs;
		
		
		
	}
	@RequestMapping(method=RequestMethod.GET, value="rest/chamados")
	public @ResponseBody List<Chamado> listarChamados(){
		try {
			return cService.listarChamados();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="rest/chamado")
	public ResponseEntity<Chamado> inserirChamado(Chamado chamado) {
		
		try {
			int id = cService.criarChamado(chamado);
			chamado.setId(id);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	
	
}
