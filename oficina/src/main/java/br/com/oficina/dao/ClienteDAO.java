package br.com.oficina.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.oficina.entity.Cliente;
import br.com.oficina.util.JPAUtil;

public class ClienteDAO {

	public Cliente cadastrarCliente(Cliente cliente) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return cliente;
	}

	public Cliente editarCliente(Cliente cliente) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();

			em.merge(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return cliente;
	}

	public Cliente removerCliente(Cliente cliente) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			cliente = em.merge(cliente);
			em.remove(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return cliente;
	}

	public List<Cliente> buscarDadosDosClientes() {
		EntityManager em = JPAUtil.getConnection();
		List<Cliente> listaClientes = null;
		try {
			listaClientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
			em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return listaClientes;
	}

	public List<Cliente> buscarDadosDosClientes(Cliente cliente) {
		EntityManager em = JPAUtil.getConnection();
		List<Cliente> listaClientes = null;
		try {
			String jpql = "select c from Cliente c where 1=1 ";
			if (!"".equals(cliente.getNome())) {
				jpql += " and upper(c.nome) like :nome";

			}
			if (!"".equals(cliente.getCpf())) {
					jpql += " and c.cpf like :cpf";
				}
			
			if (!"".equals(cliente.getEmail())) {
				jpql += " and upper(c.email) like :email";
			}
			if (!"".equals(cliente.getTelefone())) {
				jpql += " and c.telefone like :telefone";
			}

			if (!"".equals(cliente.getEndereco())) {
				jpql += " and upper(c.endereco) like :endereco";
			}
			TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);

			if (!"".equals(cliente.getNome())) {
				query.setParameter("nome", "%" + cliente.getNome().toUpperCase() + "%");
			}
			if (!"".equals(cliente.getCpf())) {
				query.setParameter("cpf", "%" + cliente.getCpf() + "%");
			}
			if (!"".equals(cliente.getEmail())) {
				query.setParameter("email", "%" + cliente.getEmail().toUpperCase() + "%");
			}
			if (!"".equals(cliente.getTelefone())) {
				query.setParameter("telefone", "%" + cliente.getTelefone() + "%");
			}
			if (!"".equals(cliente.getEndereco())) {
				query.setParameter("endereco", "%" + cliente.getEndereco().toUpperCase() + "%");
			}
			return query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaClientes;
	}

	
	public List<Cliente> buscarCliente(String nomeCliente) {
		EntityManager em = JPAUtil.getConnection();
		List<Cliente> listaClientes = null;
		try {
			String jpql = "select c from Cliente c  where c.nome = :nome";
			listaClientes = em.createQuery(jpql, Cliente.class).setParameter("nome", nomeCliente).getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaClientes;
	}

}
