package br.com.oficina.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.oficina.entity.Funcionario;
import br.com.oficina.util.JPAUtil;
import br.com.oficina.util.Utils;

public class FuncionarioDAO {

	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.persist(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return funcionario;
	}

	public Funcionario editarFuncionario(Funcionario funcionario) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.merge(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return funcionario;
	}

	public Funcionario removerFuncionario(Funcionario funcionario) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			funcionario = em.merge(funcionario);
			em.remove(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return funcionario;
	}
	
	public Long removerFuncionario(Long id) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			id = em.merge(id);
			em.remove(id);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return id ;
	}
	
	public Funcionario buscarFuncionarioPeloId(Long id) {
		EntityManager em = JPAUtil.getConnection();
		return em.find(Funcionario.class, id) ;
	
	}

	public List<Funcionario> buscarDadosDoFuncionarios() {
		EntityManager em = JPAUtil.getConnection();
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		try { 
		 listaFuncionarios = em.createQuery("from Funcionario f",Funcionario.class).getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaFuncionarios;
	}

	public List<Funcionario> buscarDadosDosFuncionarios(Funcionario funcionario) {
		EntityManager em = JPAUtil.getConnection();
		List<Funcionario> listaFuncionarios = null;
		try {
			String jpql = "select f from Funcionario f where 1=1 ";
			if (!"".equals(funcionario.getFuncao())) {
				jpql += " and upper(f.funcao) like :funcao";
			}
			
			if (!"".equals(funcionario.getNome())) {
				jpql += " and upper(f.nome) like :nome";
			}
			if (!"".equals(funcionario.getCpf())) {
				if (Utils.isCpfValido(funcionario.getCpf())) {
					jpql += " and f.cpf like :cpf";
				}
			}
			if (!"".equals(funcionario.getEmail())) {
				jpql += " and upper(f.email) like :email";
			}
			if (!"".equals(funcionario.getTelefone())) {
				if (Utils.isTelefoneValido(funcionario.getTelefone())) {
					jpql += " and f.telefone like :telefone";
				}
			}
			if (!"".equals(funcionario.getEndereco())) {
				jpql += " and upper(f.endereco) like :endereco";

			}
			TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);

			if (!"".equals(funcionario.getFuncao())) {
				query.setParameter("funcao", "%" + funcionario.getFuncao().toUpperCase() + "%");
			}

			if (!"".equals(funcionario.getNome())) {
				query.setParameter("nome", "%" + funcionario.getNome().toUpperCase() + "%");
			}
			if (!"".equals(funcionario.getCpf())) {
				if (Utils.isCpfValido(funcionario.getCpf())) {
				query.setParameter("cpf", "%" + funcionario.getCpf() + "%");
			}
			}
			if (!"".equals(funcionario.getEmail())) {
				query.setParameter("email", "%" + funcionario.getEmail().toUpperCase() + "%");
			}
			if (!"".equals(funcionario.getTelefone())) {
				if (Utils.isTelefoneValido(funcionario.getTelefone())) {
				query.setParameter("telefone", "%" + funcionario.getTelefone() + "%");
			}
			}
			if (!"".equals(funcionario.getEndereco())) {
				query.setParameter("endereco", "%" + funcionario.getEndereco().toUpperCase() + "%");
			}
			return query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaFuncionarios;
	}

}