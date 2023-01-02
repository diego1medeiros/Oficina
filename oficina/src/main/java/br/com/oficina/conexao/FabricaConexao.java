package br.com.oficina.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexao {

	private static final String URL="jdbc:postgresql://localhost/Oficina-Medeiros";
	private static final String USER= "postgres";
	private static final String PASSWORD="1234";

	public Connection getConexao() throws SQLException {
		
		String url = URL;
		Properties props = new Properties();
		props.setProperty("user",USER );
		props.setProperty("password", PASSWORD);
		Connection conexao;
		conexao = DriverManager.getConnection(url, props);
		return conexao;
		
	}
	
}
