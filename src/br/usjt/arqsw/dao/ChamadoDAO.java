package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Kaue Mirkai - 81613004
 * Professor:Bonato
 * Turma:CCP3AN-MCA
 * documentação:DAO de chamados utilizando @repository e utilizando a connection factory do spring.
 */
@Repository
public class ChamadoDAO {
	@Autowired
	@PersistenceContext
	EntityManager manager;
	
	
	@SuppressWarnings("unchecked")
    public List<Chamado> listarChamados(Fila fila) throws IOException {        
        String jpql = "select c from Chamado c where c.fila = :fila";
        
        Query query = manager.createQuery(jpql);
        query.setParameter("fila", fila);

        List<Chamado> result = query.getResultList();
        return result;
    }
    
    public int criarChamado(Chamado chamado) throws IOException {
        manager.persist(chamado);
        return chamado.getId();
    }

	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados() throws IOException {
		// TODO Auto-generated method stub
		String jpql = "select c from Chamado c";
		return manager.createQuery(jpql).getResultList();
	}
}
