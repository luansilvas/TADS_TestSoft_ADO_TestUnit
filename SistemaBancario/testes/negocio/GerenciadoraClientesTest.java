package negocio;

/*
 * ALUNO: Luan Silva dos Santos
 * DATA: 27-03-2021
 */
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GerenciadoraClientesTest {

	private GerenciadoraClientes gerClientes;
	private int idCliente01 = 1;
	private int idCliente02 = 2;
	private int idadeCliente;

	@Before
	public void setUp() {

		Cliente cliente01 = new Cliente(idCliente01, "João", 31, "joao@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Maria", 34, "maria@gmail.com", 1, true);

		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
		assertThat(gerClientes.getClientesDoBanco().size(), is(0));
	}

	@Test
	public void testPesquisaCliente() {
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		assertTrue(cliente.getId() == idCliente01);
		assertThat(cliente.getId(), is(idCliente01));
	}

	@Test
	public void testRemoveCliente() {
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);

		assertTrue(clienteRemovido);
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente02));
	}

	@Test
	public void test_Valida_Idade_Valida_Dezoito() throws IdadeNaoPermitidaException {
		idadeCliente = 18;
		Cliente cliente18 = new Cliente(3, "Joana Santos", idadeCliente, "jojosanto@outlook.com", 03, true);
		boolean idadeValida = gerClientes.validaIdade(cliente18.getIdade());

		assertTrue(idadeValida);
	}

	@Test
	public void test_Valida_Idade_Valida_Dezenove() throws IdadeNaoPermitidaException {
		idadeCliente = 19;
		Cliente cliente19 = new Cliente(4, "João Pereira", idadeCliente, "jocapera@bol.com", 04, true);
		boolean idadeValida = gerClientes.validaIdade(cliente19.getIdade());

		assertTrue(idadeValida);
	}

	@Test
	public void test_Valida_Idade_Valida_Sessenta_Quatro() throws IdadeNaoPermitidaException {
		idadeCliente = 64;
		Cliente cliente64 = new Cliente(5, "Fabiano Melo", idadeCliente, "fab_melo@gmail.com", 05, true);

		boolean idadeValida = gerClientes.validaIdade(cliente64.getIdade());

		assertTrue(idadeValida);
	}

	@Test
	public void test_Valida_Idade_Valida_Sessenta_Cinco() throws IdadeNaoPermitidaException {
		idadeCliente = 65;
		Cliente cliente65 = new Cliente(6, "Gabriela Pimentel", idadeCliente, "pimentel_gab@hotmail.com", 06, true);

		boolean idadeValida = gerClientes.validaIdade(cliente65.getIdade());

		assertTrue(idadeValida);
	}

	@Test(expected = IdadeNaoPermitidaException.class)
	public void test_Valida_Idade_Invalida_Menor() throws IdadeNaoPermitidaException {
		idadeCliente = 17;
		Cliente cliente17 = new Cliente(7, "Enzo Gabriel", idadeCliente, "enzo2004@hotmail.com", 7, true);

		gerClientes.validaIdade(cliente17.getIdade());
	}

	@Test(expected = IdadeNaoPermitidaException.class)
	public void test_Valida_Idade_Invalida_Maior() throws IdadeNaoPermitidaException {
		idadeCliente = 66;
		Cliente cliente66 = new Cliente(8, "Neusa Rodrigues", idadeCliente, "neusinha@hotmail.com", 8, true);

		gerClientes.validaIdade(cliente66.getIdade());
	}
}
