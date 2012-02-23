/* 
 * - Vers�o 1.0 - Oficial 
 * - Desenvolvedor - Wagner de Sousa
 * - Empresa - Fortes Inform�tica
 * - Data - 12/05/2011
 */  

package biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

public class Programa {
	public static void main(String[] args) throws SQLException, IOException, ParseException {
		System.out.println("                   -------- Bem-Vindo --------");
		try {
			menu();			
		} catch (Exception e) {
			System.out.println("Digite uma opção de menu válida...");
			main(args);	
		}
	}
	private static void menu() throws IOException, SQLException, ParseException {
		int decisao = 0;
		Biblioteca biblioteca = new Biblioteca();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (decisao != 7) {
			System.out.println("                      O que deseja fazer? ");
			System.out.println("1 - Adicionar novo livro ou usuúrio.        2 - Pesquisar usuário.\n3 - Pesquisar livro.                        4 - Emprestar livro.\n5 - Pesquisar empr�stimos.                  6 - Devolver livro.\n7 - Sair.");
			System.out.print("Digite um número para fazer uma ação: ");
			decisao = Integer.parseInt(in.readLine());

			switch (decisao) {
			case 1:
				int adicionar = 0;
				System.out.println("1 - Adicionar novo livro \n2 - Adicionar novo usuário");
				adicionar = Integer.parseInt(in.readLine());
				if (adicionar == 1) {
					System.out.print("Digite o nome do livro: ");
					String nomeLivro = in.readLine();
					System.out.print("Digite o nome do autor: ");
					String autor = in.readLine();
					biblioteca.cadastraLivro(nomeLivro, autor);
				} else if (adicionar == 2) {
					System.out.print("Digite o nome do usuário: ");
					String nomeUsuario = in.readLine();
					System.out.print("Digite o email do usu�rio: ");
					String email = in.readLine();
					biblioteca.cadastraUsuario(nomeUsuario, email);
				}
				break;
			case 2:
				System.out.print("Pesquisar por: ");
				String pesquisaPorNome = in.readLine();
				Collection<Usuario> usuarios = biblioteca.pesquisarUsuarioPorNome(pesquisaPorNome);
				for (Usuario usuario : usuarios) {
					System.out.println("- ID: " + usuario.getId() + "      - NOME: " + usuario.getNome() + "      - EMAIL: " + usuario.getEmail() + "\n");
				}
				break;
			case 3:
				int pesquisa = 0;
				System.out.println("1 - Pesquisar por nome \n2 - Pesquisar por autor");
				pesquisa = Integer.parseInt(in.readLine());
				if (pesquisa == 1) {
					System.out.print("Pesquisar: ");
					String pesquisaLivroPorNome = in.readLine();
					Collection<Livro> livros = biblioteca.pesquisarLivroPorNome(pesquisaLivroPorNome);
					for (Livro livro : livros) {
						System.out.println("- ID: " + livro.getId() + "      - NOME: " + livro.getNome() + "      - AUTOR: " + livro.getAutor() + "\n");
					}
				} else if (pesquisa == 2) {
					System.out.print("Pesquisar:  ");
					String pesquisaLivroPorAutor = in.readLine();
					Collection<Livro> livros = biblioteca.pesquisarLivroPorAutor(pesquisaLivroPorAutor);
					for (Livro livro : livros) {
						System.out.println("- ID: " + livro.getId() + "      - NOME: " + livro.getNome() + "      - AUTOR: " + livro.getAutor() + "\n");
					}
				}
				break;
			case 4:
				System.out.print("Digite o ID do livro: ");
				Long idLivro = Long.parseLong(in.readLine());
				Livro livro = new Livro();
				livro.setId(idLivro);

				System.out.print("Digite o ID do usu�rio: ");
				Long idUsuario = Long.parseLong(in.readLine());
				Usuario usuario = new Usuario();
				usuario.setId(idUsuario);

				System.out.print("Digite a data de empr�stimo: ");
				String data = in.readLine();
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.setTime(formatador.parse(data));
				biblioteca.emprestaLivro(livro, usuario, cal);
				break;
			case 5:
				int pesquisarEmprestimo = 0;
				System.out.println("1 - Pesquisar por usu�rio\n2 - Pesquisar por livro\n3 - Pequisar por autor do livro");
				pesquisarEmprestimo = Integer.parseInt(in.readLine());

				if (pesquisarEmprestimo == 1) {
					System.out.print("Pesquisar: ");
					String pesquisarPorUsuario = in.readLine();
					Collection<Emprestimo> emprestimo = biblioteca.pesquisaEmprestimosPorUsuario(pesquisarPorUsuario);
					for (Emprestimo resultadoPesquisaPorUsuario : emprestimo) {
						System.out.println("- ID: "	+ resultadoPesquisaPorUsuario.getId() + "      - NOME: " + resultadoPesquisaPorUsuario.getUsuario().getNome()+ "           - LIVRO: " + resultadoPesquisaPorUsuario.getLivro().getNome() + "\n");
					}
				} else if (pesquisarEmprestimo == 2) {
					System.out.print("Pesquisar: ");
					String pesquisarPorLivro = in.readLine();
					Collection<Emprestimo> emprestimo = biblioteca.pesquisaEmprestimosPorLivro(pesquisarPorLivro);
					for (Emprestimo resultadoPesquisaPorNome : emprestimo) {
						System.out.println("- ID: "	+ resultadoPesquisaPorNome.getId() + "      - NOME: " + resultadoPesquisaPorNome.getUsuario().getNome() + "           - LIVRO: " + resultadoPesquisaPorNome.getLivro().getNome()+ "\n");
					}
				} else if (pesquisarEmprestimo == 3) {
					System.out.print("Pesquisar: ");
					String pesquisarPorAutor = in.readLine();
					Collection<Emprestimo> emprestimo = biblioteca.pesquisaEmprestimoPorAutor(pesquisarPorAutor);
					for (Emprestimo resultadoPesquisaPorAutor : emprestimo) {
						System.out.println("- ID: "	+ resultadoPesquisaPorAutor.getId() + "      - NOME: " + resultadoPesquisaPorAutor.getUsuario().getNome() + "           - LIVRO: " + resultadoPesquisaPorAutor.getLivro().getNome() + "\n");
					}
				}
				break;
			case 6:
				long id = 0;
				System.out.print("Digite o ID do empr�stimo: ");
				id = Integer.parseInt(in.readLine());
				
				System.out.print("Digite a data de entrega: ");
				String dataEntrega = in.readLine();
				formatador = new SimpleDateFormat("dd/MM/yyyy");
				cal = Calendar.getInstance();
				cal.setTime(formatador.parse(dataEntrega));
				biblioteca.devolveLivro(id, cal);
				break;
			case 7:
				System.out.println("Saindo...");
				break;
			}
		}
	}
}