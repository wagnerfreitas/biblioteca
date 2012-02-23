package bibliotecaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biblioteca.Livro;

public class LivroDAO {
	private Connection conexao = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	public void insere(Livro livro) {
		String sql = "insert into livro(nome, autor) values (?,?)";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, livro.getNome());
			stmt.setString(2, livro.getAutor());
			stmt.execute();
			System.out.println("\nGravado com sucesso...");
			} catch (SQLException e) {
			throw new RuntimeException("Não foi possível inserir o livro... ");
		}
	}
	private Livro constroiLivro(ResultSet rs) throws SQLException{
		Livro livro = new Livro();
		livro.setId(rs.getLong("id"));
		livro.setNome(rs.getString("nome"));
		livro.setAutor(rs.getString("autor"));
		return livro;
	}
	public List<Livro> consultaNome(String nome) throws SQLException {
		String sql = "select * from biblioteca.livro where nome like '%" + nome + "%'";
		List<Livro> livros = new ArrayList<Livro>();
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				livros.add(constroiLivro(rs));
			}
			return livros;
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
	public List<Livro> consultaAutor(String autor) throws SQLException {
		String sql = "select * from biblioteca.livro where autor like '%" + autor + "%'";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			List<Livro> livros = new ArrayList<Livro>();
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				livros.add(constroiLivro(rs));
			}
			return livros;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao pesquisar...");
		} finally {
			if (rs != null && rs.isClosed()) {
				rs.close();
			}
			if (stmt != null && rs.isClosed()) {
				stmt.close();
			}
		}
	}
}