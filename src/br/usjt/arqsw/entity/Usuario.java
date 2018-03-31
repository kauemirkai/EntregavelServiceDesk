package br.usjt.arqsw.entity;
/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documenta��o:Entidade usuario, representa��o do objeto usuario no sistema
 */
public class Usuario {
	
	private String username;
	private String password;
	
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
