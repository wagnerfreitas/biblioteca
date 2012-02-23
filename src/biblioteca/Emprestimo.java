package biblioteca;

import java.util.Calendar;

public class Emprestimo {
	private Long id;
	private Usuario usuario;
	private Livro livro;
	private Calendar dataDeEmprestimo;
	private Calendar dataDeDevolucao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDataDeEmprestimo() {
		return dataDeEmprestimo;
	}
	public void setDataDeEmprestimo(Calendar dataDeEmprestimo) {
		this.dataDeEmprestimo = dataDeEmprestimo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Calendar getDataDeDevolucao() {
		return dataDeDevolucao;
	}
	public void setDataDeDevolucao(Calendar dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
	}
}