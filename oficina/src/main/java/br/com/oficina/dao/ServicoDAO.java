package br.com.oficina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oficina.Vo.ServicoVo;
import br.com.oficina.conexao.FabricaConexao;
import br.com.oficina.entity.Servico;
import br.com.oficina.util.JPAUtil;

public class ServicoDAO {


	public Servico cadastrarServico(Servico servico) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.persist(servico);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return servico;
	}

	public Servico editarServico(Servico servico) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.merge(servico);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return servico;
	}

	public Servico removerServico(Servico servico) {
		EntityManager em = JPAUtil.getConnection();
		try {
			em.getTransaction().begin();
			servico = em.merge(servico);
			em.remove(servico);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return servico;
	}

	public List<Servico> buscaDadosdosServicos() {
		EntityManager em = JPAUtil.getConnection();
		List<Servico> listaServicos = new ArrayList<Servico>();
		try {
			listaServicos = em.createQuery(" SELECT s FROM Servico s  ", Servico.class).getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaServicos;
	}
	
	public Servico buscarServicoPeloId(Long id) {
		EntityManager em = JPAUtil.getConnection();
		return em.find(Servico.class, id) ;
	
	}
	

	public List<Servico> buscarServicosPorNomeFuncionario(String nomeFuncionario) {
		List<Servico> listaServicos = new ArrayList<Servico>();
		EntityManager em = JPAUtil.getConnection();
		try {
			String jpql = " SELECT s FROM Servico s INNER JOIN  s.funcionario f where f.nome = :nome ";
			listaServicos = em.createQuery(jpql, Servico.class).setParameter("nome", nomeFuncionario).getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaServicos;
	}

	public List<Servico> buscarDadosDosServicos(Servico servico) {
		EntityManager em = JPAUtil.getConnection();
		List<Servico> listaServicos = new ArrayList<Servico>();
		try {
			String jpql = "select s from Servico s where 1=1 ";
			if (!"".equals(servico.getFuncionario().getNome()) && servico.getFuncionario().getNome()!= null) {
				jpql += " and upper(s.funcionario.nome) like :nome";
			}
			if (!"".equals(servico.getCliente().getNome())  && servico.getCliente().getNome()!= null) {
				jpql += " and upper(s.cliente.nome) like :nome";
			}
			if (!"".equals(servico.getVeiculo().getModelo()) && servico.getVeiculo().getModelo()!= null) {
				jpql += " and upper(s.veiculo.modelo) like :modelo";
			}
			if (!"".equals(servico.getDescricao())) {
				jpql += " and upper(s.descricao) like :descricao";
			}
			if (!"".equals(servico.getObservacoes())) {
				jpql += " and upper(s.observacoes) like :observacoes";
			}
			if (!"".equals(servico.getStatus())) {
				jpql += " and upper(s.status) like :status";
			}
			if (servico.getData() != null) {
				jpql += " and s.data = :data";
			}
			if (servico.getDataEntrega() != null) {
				jpql += " and s.dataEntrega = :dataEntrega";
			}

			TypedQuery<Servico> query = em.createQuery(jpql, Servico.class);
			
			if (!"".equals(servico.getFuncionario().getNome()) && servico.getFuncionario().getNome()!= null) {
				query.setParameter("nome", "%" + servico.getFuncionario().getNome().toUpperCase() + "%");
			}
			if (!"".equals(servico.getCliente().getNome())  && servico.getCliente().getNome()!= null) {
				query.setParameter("nome", "%" + servico.getCliente().getNome().toUpperCase() + "%");
			}
			if (!"".equals(servico.getVeiculo().getModelo()) && servico.getVeiculo().getModelo()!= null) {
				query.setParameter("modelo", "%" + servico.getVeiculo().getModelo().toUpperCase() + "%");
			}
			if (!"".equals(servico.getDescricao())) {
				query.setParameter("descricao", "%" + servico.getDescricao().toUpperCase() + "%");
			}
			if (!"".equals(servico.getObservacoes())) {
				query.setParameter("observacoes", "%" + servico.getObservacoes().toUpperCase() + "%");
			}
			if (!"".equals(servico.getStatus())) {
				query.setParameter("status", "%" + servico.getStatus().toUpperCase() + "%");
			}
			if (servico.getData() != null) {
				query.setParameter("data", servico.getData());
			}
			if (servico.getDataEntrega() != null) {
				query.setParameter("dataEntrega", servico.getDataEntrega());
			}

			return query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaServicos;
	}

	public Servico atualizarStatusServico(Servico servico) {
		FabricaConexao conexao = new FabricaConexao();
		try {
			Connection conn = conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement("UPDATE public.\"SERVICO\"\r\n"
					+ "	SET \"STATUS\"=?\r\n , \"OBSERVACOES\"=?\r\n" + "	WHERE \"ID_SERVICO\"=? ");

			pstm.setString(1, servico.getStatus());
			pstm.setLong(3, servico.getIdServico());
			pstm.setString(2, servico.getObservacoes());

			System.out.println(pstm);
			pstm.executeLargeUpdate();

			pstm.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return servico;
	}

	public List<ServicoVo> buscaQdtDosServicosPorFuncionarios() {
		EntityManager em = JPAUtil.getConnection();
		List<ServicoVo> listaServicos = null;
		try {
			String jpql = "select new br.com.oficina.Vo.ServicoVo( s.funcionario.nome, count(s.idServico))from Servico s group by s.funcionario.nome";
			listaServicos = em.createQuery(jpql).getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaServicos;
	}
	

	public List<ServicoVo> relatorioValorTotalDeServicos(Servico servico) {
		EntityManager em = JPAUtil.getConnection();
		List<ServicoVo> listaServicos = null;
		try {
			String jpql = "select new br.com.oficina.Vo.ServicoVo" + "(s.funcionario.nome,s.cliente.nome,"
					+ "s.veiculo.modelo,s.descricao,s.observacoes,s.status,s.data,s.dataEntrega,s.valor,s.pago,s.idServico,";
			jpql += "(select sum(s.valor) as valorTotal from Servico s inner join s.funcionario f  inner join s.veiculo v inner join s.cliente c where ";
			String jpql1 = "1=1";
			String jpql3 = "))from Servico s where 1=1 ";

			;
			if (servico.getData() != null && servico.getDataEntrega() != null) {
				jpql1 += " and s.data between :data and :dataEntrega";
			}
			if (servico.getPago() == 0 || servico.getPago() == 1) {
				jpql1 += " and s.pago = :pago";
			}
			if (servico.getData() != null && servico.getDataEntrega() != null) {
				jpql3 += " and s.data between :data and :dataEntrega";
			}
			if (servico.getPago() == 0 || servico.getPago() == 1) {
				jpql3 += " and s.pago = :pago";
			}
			System.out.print("Query " + jpql + jpql1 + jpql3);

			Query query = em.createQuery(jpql + jpql1 + jpql3);

			if (servico.getData() != null) {
				query.setParameter("data", servico.getData());
			}
			if (servico.getDataEntrega() != null) {
				query.setParameter("dataEntrega", servico.getDataEntrega());
			}
			if (servico.getPago() == 0 || servico.getPago() == 1) {
				query.setParameter("pago", servico.getPago());
			}
			return query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaServicos;
	}

	public List<ServicoVo> relatorioValorTotalDeServico(Servico servico) {
		EntityManager em = JPAUtil.getConnection();
		List<ServicoVo> listaServicos = null;
		try {
			String jpql = "select sum(s.valor) as valorTotal from Servico s where 1=1 ";
			if (servico.getData() != null && servico.getDataEntrega() != null) {
				jpql += " and s.data between :data and :dataEntrega";
			}
			if (servico.getPago() == 0 || servico.getPago() == 1) {
				jpql += " and s.pago = :pago";
			}
			System.out.print("Query " + jpql);

			Query query = em.createQuery(jpql);

			if (servico.getData() != null) {
				query.setParameter("data", servico.getData());
			}
			if (servico.getDataEntrega() != null) {
				query.setParameter("dataEntrega", servico.getDataEntrega());
			}
			if (servico.getPago() == 0 || servico.getPago() == 1) {
				query.setParameter("pago", servico.getPago());
			}
			return query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaServicos;
	}

	public List<Servico> relatorioValorTotalDeServicos1(Servico servico) {

		EntityManager em = JPAUtil.getConnection();
		List<Servico> listaServicos = null;
		try {
			String jpql = "select new br.com.oficina.Vo.ServicoVo(sum(s.valor) as valorTotal) from Servico s ";
			listaServicos = em.createQuery(jpql).getResultList();

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return listaServicos;
	}

	public Servico buscarServicoPorId(Long id) {
		EntityManager em = JPAUtil.getConnection();
		Servico servico = em.find(Servico.class, id);
		return servico;

	}
}
