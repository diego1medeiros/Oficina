package br.com.oficina.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.oficina.controller.VeiculoController;
import br.com.oficina.entity.Veiculo;

@FacesConverter(value = "veiculoConverter", forClass = Veiculo.class)
public class VeiculoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	
	VeiculoController veiculoController = new VeiculoController();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigo) {

		if (codigo != null && !codigo.isEmpty()) {
			System.out.println(codigo);
			return (Veiculo) veiculoController.buscarVeiculoPorId(new Long(codigo));
			
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {

		if (object != null) {
			Long idCliente = (Long) object;
			return idCliente != null && idCliente > 0
					? idCliente.toString()
					: null;
		}
		return null;
	}
}
