package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Model.Funcionario;
import Service.FuncionarioService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFuncionario {
	Funcionario func, copiado;
	FuncionarioService funcService;
	static int idFuncionario = 0;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o idusuario igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se também que sobrecarregou o equals na classe
	 * User. 
	 * Certifique-se que a fixture user1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		func = new Funcionario();
		func.setidFuncionario(idFuncionario);
		func.setNome("Vinicius");
		func.setFuncao("Sindico");
		func.setDataNasc("25/08/1997");
		func.setRG("123665489");
		func.setCPF("12554789547");
		func.setLogin("Vinicius");
		func.setSenha("123456");
		func.setAcessoCatraca("9:00 as 18:00");
		func.setAcessoSistema(true);
		func.setControleAr(true);
		func.setidEmpresa(1);
		copiado = new Funcionario();
		copiado.setidFuncionario(idFuncionario);
		copiado.setNome("Vinicius");
		copiado.setFuncao("Sindico");
		copiado.setDataNasc("25/08/1997");
		copiado.setRG("123665489");
		copiado.setCPF("12554789547");
		copiado.setLogin("Vinicius");
		copiado.setSenha("123456");
		copiado.setAcessoCatraca("9:00 as 18:00");
		copiado.setAcessoSistema(true);
		copiado.setControleAr(true);
		copiado.setidEmpresa(1);
		funcService = new FuncionarioService();
		System.out.println(func);
		System.out.println(copiado);
		System.out.println(idFuncionario);
	}

	
	@Test
	public void test00Select() {
		System.out.println("selectFuncionarios");
		//para funcionar o funcionario 1 deve ter sido carregado no banco por fora
		Funcionario fixture = new Funcionario();
		fixture.setidFuncionario(idFuncionario);
		fixture.setNome("Vinicius");
		fixture.setFuncao("Sindico");
		fixture.setDataNasc("25/08/1997");
		fixture.setRG("123665489");
		fixture.setCPF("12554789547");
		fixture.setLogin("Vinicius");
		fixture.setSenha("123456");
		fixture.setAcessoCatraca("9:00 as 18:00");
		fixture.setAcessoSistema(true);
		fixture.setControleAr(true);
		fixture.setidEmpresa(1);
		FuncionarioService novoService = new FuncionarioService();
		Funcionario novo = novoService.SelecionarEspecifico(1);
		assertEquals("testa inclusao", novo, fixture);
	}


	@Test
	public void test01Create() {
		System.out.println("createFuncionario");
		idFuncionario = funcService.createFuncionario(func);
		System.out.println(idFuncionario);
		copiado.setidFuncionario(idFuncionario);
		assertEquals("testa criacao", func, copiado);
	}
	
	@Test
	public void test02Update() {
		System.out.println("updateFuncionario");
		func.setSenha("1235678");
		copiado.setSenha("1234678");		
		funcService.updateFuncionario(func);
		func = funcService.SelecionarEspecifico(func.getidFuncionario());
		assertEquals("testa atualizacao", func, copiado);
	}

	@Test
	public void test03Delete() {
		System.out.println("deleteFuncionario");
		copiado.setidFuncionario(-1);
		copiado.setNome(null);
		copiado.setFuncao(null);
		copiado.setDataNasc(null);
		copiado.setRG(null);
		copiado.setCPF(null);
		copiado.setLogin(null);
		copiado.setSenha(null);
		copiado.setAcessoCatraca(null);
		copiado.setAcessoSistema(false);
		copiado.setControleAr(false);
		copiado.setidEmpresa(-1);
		funcService.deleteFuncionario(idFuncionario);
		func = funcService.SelecionarEspecifico(idFuncionario);
		assertEquals("testa exclusao", func, copiado);
	}

}