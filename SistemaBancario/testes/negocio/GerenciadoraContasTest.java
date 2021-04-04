
package negocio;

/*
 * ALUNO: Luan Silva dos Santos
 * DATA: 27-03-2021
 */
import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraContasTest {

	private GerenciadoraContas gerContas;
	int idConta01 = 1, idConta02 = 2;

	@Test
	public void test_TransfereValor() {
		ContaCorrente conta01 = new ContaCorrente(idConta01, 200, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 100, idConta02);

		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(100.0));
		assertThat(conta02.getSaldo(), is(100.0));
	}

	@Test
	public void test_Transfere_Valor_Saldo_Final_Negativo() {
		ContaCorrente conta01 = new ContaCorrente(idConta01, 100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);

		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-100.0));
		assertThat(conta02.getSaldo(), is(200.0));

	}

	@Test
	public void test_Transfere_Valor_Saldo_Inicial_Negativo() {
		ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);

		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(200.0));

	}

	@Test
	public void test_TransfereValor_Origem_E_Destino_Com_Saldo_Inicial_Negativo() {
		ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, -100, true);

		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);

		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(100.0));

	}

}
