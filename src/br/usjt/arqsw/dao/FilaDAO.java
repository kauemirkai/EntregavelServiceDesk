package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documentação:DAO de Filas utilizando @repository e utilizando a connection factory do spring.
 */
@Repository
public class FilaDAO {
	@PersistenceContext
	@Autowired
	EntityManager manager;
	
	public Fila carregar(int id) throws IOException {
        return manager.find(Fila.class, id);    
    }

    @SuppressWarnings("unchecked")
    public List<Fila> listarFilas() throws IOException {        
        String jpql = "select f from Fila f";
        Query query = manager.createQuery(jpql);

        List<Fila> result = query.getResultList();
        return result;
    }

	public int criar(Fila fila) {
        manager.persist(fila);
        return fila.getId();
	}
	
	public void atualizar(Fila fila) {
		manager.merge(fila);
				
	}
	public void excluir(Fila fila) {
		manager.remove(fila);	
	}
	
}
