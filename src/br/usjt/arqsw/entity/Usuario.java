package br.usjt.arqsw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documentação:Entidade usuario, representação do usuario no sistema
 */
@Entity
public class Usuario {
	
	@Id
	private int id;
	
	private String username;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + "]";
	}

}
