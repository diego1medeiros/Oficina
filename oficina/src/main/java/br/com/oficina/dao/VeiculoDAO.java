package br.com.oficina.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.oficina.entity.Veiculo;
import br.com.oficina.util.JPAUtil;

public class VeiculoDAO {


	public Veiculo cadastrarVeiculo(Veiculo veiculo) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.persist(veiculo);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return veiculo;
	}
	
	public Veiculo editarVeiculo( Veiculo veiculo) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();

			em.merge(veiculo);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return veiculo;
	}

	public Veiculo removerVeiculo(Veiculo veiculo) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			veiculo = em.merge(veiculo);
			em.remove(veiculo);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return veiculo;
	}

	
	
	
	public List<Veiculo>  buscarDadosDosVeiculos() {
		EntityManager em = JPAUtil.getConnection();
		List<Veiculo> listaVeiculos = null;
		try {
			listaVeiculos = em.createQuery("select v from Veiculo v", Veiculo.class).getResultList();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaVeiculos;
	}

	public List<Veiculo> buscarDadosDosVeiculos(Veiculo veiculo) {
		EntityManager em = JPAUtil.getConnection();
		List<Veiculo> listaVeiculos = null;
		try {
			String jpql = "select v from Veiculo v  where 1=1 ";
			if (!"".equals(veiculo.getCliente().getNome()) && veiculo.getCliente().getNome()!=null) {
				jpql += " and upper(v.cliente.nome) like :nome";
			}
			if (!"".equals(veiculo.getTipo())) {	
					jpql += " and upper(v.tipo) like :tipo";
			}
			if (!"".equals(veiculo.getMarca())) {
				jpql += " and upper(v.marca) like :marca";
			}
			if (!"".equals(veiculo.getModelo())) {
					jpql += " and upper (v.modelo) like :modelo";
			}
			if (!"".equals(veiculo.getAno())) {
				jpql += " and upper(v.ano) like :ano";
			}
			TypedQuery<Veiculo> query = em.createQuery(jpql, Veiculo.class);

			if (!"".equals(veiculo.getCliente().getNome()) && veiculo.getCliente().getNome()!=null) { 
				query.setParameter("nome", "%" +veiculo.getCliente().getNome().toUpperCase() + "%");
			}
			if (!"".equals(veiculo.getTipo())) {
				query.setParameter("tipo", "%" + veiculo.getTipo().toUpperCase()+ "%");
			}
			
			if (!"".equals(veiculo.getMarca())) {
				query.setParameter("marca", "%" + veiculo.getMarca().toUpperCase() + "%");
			}
			if (!"".equals(veiculo.getModelo())) {
				query.setParameter("modelo", "%" + veiculo.getModelo().toUpperCase() + "%");
			}
			
			if (!"".equals(veiculo.getAno())) {
				query.setParameter("ano", "%" + veiculo.getAno().toUpperCase() + "%");
			}
			return query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaVeiculos;
	}

	public List<Veiculo> buscarVeiculos(String  nomeCliente) {
		EntityManager em = JPAUtil.getConnection();
		List<Veiculo> listaVeiculos = null;
		try {
			String jpql = "select v from Veiculo v INNER JOIN v.cliente c where c.nome = :nome";
			listaVeiculos = em.createQuery(jpql, Veiculo.class).setParameter("nome", nomeCliente).getResultList();	
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaVeiculos;
	}

	public Veiculo buscarVeiculoPorId(Long id) {
		EntityManager em = JPAUtil.getConnection();
		Veiculo Veiculo = em.find(Veiculo.class, id);
		return Veiculo ;

	}
	
	public Veiculo excluirBonusVeiculoPorId(Long id) {
		EntityManager em = JPAUtil.getConnection();
		Veiculo veiculo= null;
		try {
	    veiculo = em.find(Veiculo.class, id);
	  
	    em.getTransaction().begin();
		em.remove(veiculo);
		
		em.getTransaction().commit();
	} catch (Exception e) {
		em.getTransaction().rollback();
	} finally {
		em.close();
	}
		return veiculo ;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
