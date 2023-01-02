package br.com.oficina.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.oficina.controller.ServicoController;
import br.com.oficina.dao.ServicoDAO;
import br.com.oficina.entity.Funcionario;
import br.com.oficina.entity.Servico;


	@FacesConverter(value = "servicoConverter", forClass = Servico.class)
	public class ServicoConverter implements Converter, Serializable {

		private static final long serialVersionUID = 1L;
		
	ServicoDAO dao = new ServicoDAO(); 
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String codigo) {

			if (codigo != null && !codigo.isEmpty()) {
				return (Servico) dao.buscarServicoPorId(new Long(codigo));
				
			}
			return codigo;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object object) {

			if (object != null) {
				Long idFuncionario = (Long) object;
				return idFuncionario != null && idFuncionario > 0
						? idFuncionario.toString()
						: null;
			}
			return null;
		}
	
}
