package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;
/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documentação:DAO de Usuários utilizando @repository e utilizando a connection factory do spring.
 */
@Repository
@PersistenceContext
public class UsuarioDAO {
	
	private Connection conn;
	
	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException{
		try{
			this.conn = dataSource.getConnection();
		} catch (SQLException e){
			throw new IOException(e);
		}
	}
	
	public Boolean validar(Usuario usuario) throws IOException {
		String query = "SELECT username, password FROM usuario WHERE username = ? and password= ?";
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setString(1, usuario.getUsername());
			pst.setString(2, usuario.getPassword());
			
			try(ResultSet rs = pst.executeQuery();) {
				if(rs.next()) {
					return true;
				}				
			}			
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return false;		
	}
}
