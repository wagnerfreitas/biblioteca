package biblioteca;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import bibliotecaDAO.EmprestimoDAO;
import bibliotecaDAO.LivroDAO;
import bibliotecaDAO.UsuarioDAO;

public class Biblioteca {
	LivroDAO livroDAO;
	EmprestimoDAO emprestimoDAO;
	UsuarioDAO usuarioDAO;

	public Biblioteca(){
		livroDAO = new LivroDAO();
		emprestimoDAO = new EmprestimoDAO();
		usuarioDAO = new UsuarioDAO();
	}
	
	public void cadastraUsuario(String nome, String email) throws IOException{
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuarioDAO.insere(usuario);
	}	
	public void cadastraLivro(String nome, String autor) throws IOException{
		Livro livro = new Livro();
		livro.setNome(nome);
		livro.setAutor(autor);
		livroDAO.insere(livro);
	}
	public void emprestaLivro(Livro livro, Usuario usuario, Calendar dataDeEmprestimo) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setLivro(livro);
		emprestimo.setUsuario(usuario);
		emprestimo.setDataDeEmprestimo(dataDeEmprestimo);
		emprestimoDAO.insere(emprestimo);
	}	
	public void devolveLivro(Long id, Calendar dataDaDevolucao) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setDataDeDevolucao(dataDaDevolucao);
		emprestimoDAO.atualiza(id, dataDaDevolucao);
	}
	public List<Usuario> pesquisarUsuarioPorNome(String pesquisaPorNome) throws IOException, SQLException{
		return usuarioDAO.consultaUsuario(pesquisaPorNome);
	}
	public List<Livro> pesquisarLivroPorNome(String pesquisaPorNome) throws IOException, SQLException{
		return livroDAO.consultaNome(pesquisaPorNome);
	}
	public List<Livro> pesquisarLivroPorAutor(String pesquisaPorAutor) throws IOException, SQLException{
		return livroDAO.consultaAutor(pesquisaPorAutor);
	}
	public List<Emprestimo> pesquisaEmprestimosPorLivro(String livro) throws SQLException{
		return emprestimoDAO.consultaEmprestimoLivro(livro);
	}
	public List<Emprestimo> pesquisaEmprestimosPorUsuario(String usuario) throws SQLException{
		return emprestimoDAO.consultaEmprestimoUsuario(usuario);
	}
	public List<Emprestimo> pesquisaEmprestimoPorAutor(String autor) throws SQLException{
		return emprestimoDAO.consultaLivroAutor(autor);
	}
}