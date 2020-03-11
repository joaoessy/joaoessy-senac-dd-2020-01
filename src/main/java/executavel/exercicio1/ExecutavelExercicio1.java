package executavel.exercicio1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.bo.ClienteBO;
import model.dao.exercicio1.ClienteDAO;
import model.dao.exercicio1.EnderecoDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ExecutavelExercicio1 {

	public static void main(String[] argumentosLinhaDeComando) {
		
		// TODO criar e SALVAR telefones
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();

		// Exercício 2
		Cliente cliente1 = obterClienteDaTela();

		// - Salvar no banco (APENAS TESTES, AINDA VIOLANDO O MVC)
		ClienteBO clienteBO = new ClienteBO();
		String mensagem = clienteBO.salvar(cliente1);

		JOptionPane.showMessageDialog(null, mensagem);
		// 2
		// - todos clientes
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		//
		Telefone tell = obterTelefoneDaTela(); 
		

	}

	private static Cliente obterClienteDaTela() {
		String nome = JOptionPane.showInputDialog("Informe o nome");
		String sobrenome = JOptionPane.showInputDialog("Informe o sobrenome");
		String cpf = JOptionPane.showInputDialog("Informe o CPF");

		EnderecoDAO endDAO = new EnderecoDAO();
		ArrayList<Endereco> listaEnderecos = endDAO.consultarTodos();

		Object[] enderecos = listaEnderecos.toArray();
		Endereco enderecoSelecionado = (Endereco) JOptionPane.showInputDialog(null, 
				"Selecione um endereço", "Endereço", 
				JOptionPane.QUESTION_MESSAGE, 
				null, enderecos, null);

		Cliente novoCliente = new Cliente(nome, sobrenome, cpf, 
				new ArrayList<Telefone>(), enderecoSelecionado);

		return novoCliente;
	}
	private static Telefone obterTelefoneDaTela() {
		int opcaoSelecionada = JOptionPane.showConfirmDialog(null,"Telefone possui dono?", "Verificação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
		String codigoPais = JOptionPane.showInputDialog("Informe o codigo do país");
		String ddd = JOptionPane.showInputDialog("Informe o DDD");
		String numero = JOptionPane.showInputDialog("Informe o Número");
		int movel = JOptionPane.showConfirmDialog(null, "Telefone móvel?","Verificação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
		int ativo = JOptionPane.showConfirmDialog(null, "Telefone Ativo?","Verificação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);

		Telefone novoTelefone = new Telefone();
		novoTelefone.setCodigoPais(codigoPais);
		novoTelefone.setDdd(ddd);
		novoTelefone.setNumero(numero);
		if(movel == 0) {
		novoTelefone.setMovel(true);
			
		}else {
		novoTelefone.setMovel(false);	
		}
		if(ativo == 0) {
		novoTelefone.setAtivo(true);
		}else {
		novoTelefone.setAtivo(false);
		}
		
		
		
		if (opcaoSelecionada == JOptionPane.YES_OPTION) {

			ClienteDAO cliDAO = new ClienteDAO();
			ArrayList<Cliente> listaClientes = cliDAO.consultarTodos();

			Object[] clientes = listaClientes.toArray();
			Cliente clienteSelecionado = (Cliente) JOptionPane.showInputDialog(null, 
					"Selecione um cliente", "Cliente", 
					JOptionPane.QUESTION_MESSAGE, 
					null, clientes, null);
			
			novoTelefone.setDono(clienteSelecionado);

			
		}


		return novoTelefone;


	}
}
