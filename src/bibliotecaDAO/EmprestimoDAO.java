package bibliotecaDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import biblioteca.Emprestimo;
import biblioteca.Livro;
import biblioteca.Usuario;

public class EmprestimoDAO {
	private Connection conexao = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public void insere(Emprestimo emprestimo) throws SQLException {
		String sql = "insert into emprestimo (idUsuario, idLivro, dataDeEmprestimo) values(?,?,?)";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, emprestimo.getUsuario().getId());
			stmt.setLong(2, emprestimo.getLivro().getId());
			stmt.setDate(3, new Date(emprestimo.getDataDeEmprestimo().getTimeInMillis()));
			stmt.execute();

			System.out.println("\nGravado com sucesso...");

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar fazer empr�stimo..." + e);
		} finally {
			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}
		}
	}
	public void atualiza(Long id, Calendar dataDeEntrega) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setDataDeDevolucao(dataDeEntrega);
		Date novaData = new java.sql.Date(dataDeEntrega.getTimeInMillis());
		String sql = "update emprestimo set dataDeEntrega = '" + novaData + "' where id = " + id ;
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			stmt = conexao.prepareStatement(sql);
			stmt.execute();
			System.out.println("\nDevolução feita com sucesso...");
		} catch (SQLException e) {
			throw new RuntimeException("Erro na devolução...");
		} finally {
			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}
		}
	}
	private Emprestimo constroiEmprestimo(ResultSet rs) throws SQLException{
		Emprestimo emprestimo = new Emprestimo();
		Usuario usuario = new Usuario();
		Livro livro = new Livro();
		
		usuario.setNome(rs.getString("nomeUsuario"));
		livro.setNome(rs.getString("nomeLivro"));
		emprestimo.setId(rs.getLong("id"));

		emprestimo.setUsuario(usuario);
		emprestimo.setLivro(livro);
		return emprestimo;
	}	
	public List<Emprestimo> consultaEmprestimoUsuario(String nomeUsuario) throws SQLException{
		String sql = "SELECT u.nome as nomeUsuario,l.nome as nomeLivro,e.id FROM emprestimo e, livro l, usuario u where e.dataDeEntrega is null and e.idLivro = l.id and e.idUsuario = u.id and u.nome like '%" + nomeUsuario + "%'";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				emprestimos.add(constroiEmprestimo(rs));
			}
			return emprestimos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao pesquisar por nome...");
		}finally{
			if (rs != null && rs.isClosed()) {
				rs.close();
			}
			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}
		}
	}	
	public List<Emprestimo> consultaEmprestimoLivro(String nomeLivro) throws SQLException {
		String sql = "SELECT u.nome as nomeUsuario,l.nome as nomeLivro,e.id FROM emprestimo e, livro l, usuario u where e.dataDeEntrega is null and e.idLivro = l.id and e.idUsuario = u.id and l.nome like '%" + nomeLivro + "%'";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				emprestimos.add(constroiEmprestimo(rs));
			}
			return emprestimos;
		} catch (SQLException e) {
			
			throw new RuntimeException("Erro na pesquisa por nome... ");
		} finally {
			if (rs != null && rs.isClosed()) {
				rs.close();
			}
			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}
		}
	}
	public List<Emprestimo> consultaLivroAutor (String nomeAutor) throws SQLException{
		String sql = "SELECT u.nome as nomeUsuario,l.nome as nomeLivro,e.id FROM emprestimo e, livro l, usuario u where e.dataDeEntrega is null and e.idLivro = l.id and e.idUsuario = u.id and l.autor like '%" + nomeAutor + "%'";
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "root");
			List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				emprestimos.add(constroiEmprestimo(rs));
			}
			return emprestimos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao pesquisar por autor...");
		}finally{
			if(rs != null && rs.isClosed()){
				rs.close();
			}
			if(stmt != null && stmt.isClosed()){
				stmt.close();
			}
		}
	}
}