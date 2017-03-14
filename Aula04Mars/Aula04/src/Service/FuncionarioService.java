package Service;

import DAO.FuncionarioDAO;
import Model.Funcionario;


public class FuncionarioService {
	
	FuncionarioDAO dao = new FuncionarioDAO();
	
	public int createFuncionario(Funcionario func) {
		return dao.incluir(func);
	}
	
	public void updateFuncionario(Funcionario func){
		dao.alterar(func);
	}
	
	public void deleteFuncionario(int idFuncionario){
		dao.excluir(idFuncionario);
	}
	
	public Funcionario SelecionarEspecifico(int idFuncionario){
		return dao.SelecionarEspecifico(idFuncionario);
	}
}