package bibliotecaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biblioteca.Usuario;

public class UsuarioDAO {
	private Connection conexao = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public void insere(Usuario usuario) {
		String sql = "insert into usuario(nome, email) values (?,?)";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.execute(); 
			System.out.println("\nGravado com sucesso...");
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível inserir o usuário... ");
		}
	}
	public List<Usuario> consultaUsuario(String nome) throws SQLException {
		Usuario usuario = null;
		String sql = "select * from biblioteca.usuario where nome like '%" + nome + "%'";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			List<Usuario> usuarios = new ArrayList<Usuario>();
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao pesquisar...");
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		}
	}
}