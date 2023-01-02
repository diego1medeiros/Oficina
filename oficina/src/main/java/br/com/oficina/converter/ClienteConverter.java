package br.com.oficina.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.oficina.controller.ClienteController;
import br.com.oficina.dao.ClienteDAO;
import br.com.oficina.entity.Cliente;
import br.com.oficina.entity.Veiculo;


@FacesConverter(value="clienteConverter", forClass = ClienteController.class)
public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
	ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.buscarCliente(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value!= null && !value.equals("")){
		Cliente cliente = (Cliente)  value;
			if(cliente.getIdCliente()!= null) {
				return String.valueOf(cliente.getIdCliente());
			}
		}
		return null;
	}

	
	
}
