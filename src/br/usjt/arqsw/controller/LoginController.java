package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;
/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documentação:Essa classe corresponde ao controller de login, ao clickar no botão de login caimos no metodo efetuar login que ao chamar
 * service.validar() returna uma session pro usuário.
 */
@Controller
@Transactional
public class LoginController {
	private UsuarioService usuarioService;

	@Autowired
	public LoginController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@RequestMapping("loginForm")
	public String login() {
		return "loginForm";
	}
	
	@RequestMapping("/efetuar_login")
	public String efetuarLogin(Usuario usuario, Model model, HttpServletRequest request) {
		try {
			if(usuarioService.validar(usuario)){
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogado", usuario);
				return "index";
			}
			return "loginForm";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/efetuar_logout")
	public String efetuarLogin(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
	}

}
