package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Funcionario;
public class FuncionarioDAO
{
   Funcionario f = new Funcionario();
   public FuncionarioDAO(){
   
   }
   
   public int incluir(Funcionario func)
   {
      String sqlInsert = "INSERT INTO Funcionario(nome,funcao, datanasc, rg, cpf, login, senha, horarioAcessoCatraca,AcessoSistema,ControleAr,cancelado,id_Empresa) VALUES (?,?, ?, ?,?, ?, ?,?, ?, ?,0,?)";
      try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
    	  
         stm.setString(1, func.getNome());
         stm.setString(2, func.getFuncao());
         stm.setString(3, func.getDataNasc());
         stm.setString(4, func.getRG());
         stm.setString(5, func.getCPF());
         stm.setString(6, func.getLogin());
         stm.setString(7, func.getSenha());
         stm.setString(8, func.getAcessoCatraca());
         stm.setBoolean(9,func.getAcessoSistema());
         stm.setBoolean(10,func.getControleAr());
         stm.setInt(11, func.getidEmpresa());
         stm.execute();
         String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if(rs.next()) {
					func.setidFuncionario(rs.getInt(1));
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return func.getidFuncionario();
   }
   
   
   public void alterar(Funcionario func)
   {
      String sqlUpdate = "UPDATE Funcionario SET  nome=?,funcao=?, datanasc=?, rg=?, cpf=?, login=?, senha=?, horarioAcessoCatraca=?, AcessoSistema=?, ControleAr=?  WHERE id=?";
      try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
         stm.setString(1, func.getNome());
         stm.setString(2, func.getFuncao());
         stm.setString(3, func.getDataNasc());
         stm.setString(4, func.getRG());
         stm.setString(5, func.getCPF());
         stm.setString(6, func.getLogin());
         stm.setString(7, func.getSenha());
         stm.setString(8, func.getAcessoCatraca());
         stm.setBoolean(9,func.getAcessoSistema());
         stm.setBoolean(10,func.getControleAr());
         stm.setInt(11, func.getidFuncionario());
         stm.execute();
      } catch (Exception e) {
			e.printStackTrace();
		}
   }
   
   public void excluir(int idFuncionario)
   {
      String sqlDelete = "UPDATE Funcionario SET cancelado=1 where id = ?";
   		try (Connection conn = ConnectionFactory.obtemConexao();
   				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
         stm.setInt(1,idFuncionario);
         stm.execute();
   		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   

   public Funcionario SelecionarEspecifico(int codigo)
   {
	   Funcionario func = new Funcionario();
		func.setidFuncionario(codigo);
      String sqlSelect = "SELECT * FROM Funcionario WHERE id = ? and cancelado=0";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, func.getidFuncionario());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
            func.setidFuncionario(rs.getInt(1));
            func.setNome(rs.getString(2));
            func.setFuncao(rs.getString(3));
            func.setDataNasc(rs.getString(4));
            func.setRG(rs.getString(5));
            func.setCPF(rs.getString(6));
            func.setLogin(rs.getString(7));
            func.setSenha(rs.getString(8));
            func.setAcessoCatraca(rs.getString(9));
            func.setAcessoSistema(rs.getBoolean(10));
            func.setControleAr(rs.getBoolean(11));
            func.setidEmpresa(rs.getInt(12));
				} else {
		            func.setidFuncionario(-1);
		            func.setNome(null);
		            func.setFuncao(null);
		            func.setDataNasc(null);
		            func.setRG(null);
		            func.setCPF(null);
		            func.setLogin(null);
		            func.setSenha(null);
		            func.setAcessoCatraca(null);
		            func.setAcessoSistema(false);
		            func.setControleAr(false);
		            func.setidEmpresa(-1);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (SQLException ex) {
			System.out.print(ex.getStackTrace());
		}
		return func;
	}
   
   /*
     
  // Selecionar Empresa 
   public ArrayList<Funcionario> SelecionarEmpresa(int codigo,Connection conn)
   {
      String sqlSelect = "SELECT * FROM Empresa where id=? and cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      ArrayList<Funcionario> lf = new ArrayList<Funcionario>();
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         stm.setInt(1, codigo);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
            Funcionario f = new Funcionario();
            f.setidEmpresa(rs.getInt(1));
            f.setEmpresa(rs.getString(2));
            lf.add(f);
         }
      
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
      return (lf);
   }
   
   
   // Selecionar Funcionario Especifico (para Detalhes e editar) 
   public ArrayList<Funcionario> SelecionarEspecifico(int codigo, Connection conn)
   {
      String sqlSelect = "SELECT * FROM Funcionario WHERE id = ? and cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      ArrayList<Funcionario> lf = new ArrayList<Funcionario>();
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         stm.setInt(1, codigo);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
            f.setidFuncionario(rs.getInt(1));
            f.setNome(rs.getString(2));
            f.setFuncao(rs.getString(3));
            f.setDataNasc(rs.getString(4));
            f.setRG(rs.getString(5));
            f.setCPF(rs.getString(6));
            f.setLogin(rs.getString(7));
            f.setSenha(rs.getString(8));
            f.setAcessoCatraca(rs.getString(9));
            f.setAcessoSistema(rs.getBoolean(10));
            f.setControleAr(rs.getBoolean(11));
            f.setidEmpresa(rs.getInt(12));
            lf.add(f);
         }
      
      
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
     return(lf);
   }
   
   // Selecionar Funcionario 
   public ArrayList<Funcionario> SelecionarTudo(Connection conn)
   {
      String sqlSelect = "SELECT * FROM Funcionario where cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      ArrayList<Funcionario> lf = new ArrayList<Funcionario>();
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
            Funcionario f = new Funcionario();
            f.setidFuncionario(rs.getInt(1));
            f.setNome(rs.getString(2));
            f.setFuncao(rs.getString(3));
            f.setDataNasc(rs.getString(4));
            f.setRG(rs.getString(5));
            f.setCPF(rs.getString(6));
            f.setLogin(rs.getString(7));
            f.setSenha(rs.getString(8));
            f.setAcessoCatraca(rs.getString(9));
            f.setAcessoSistema(rs.getBoolean(10));
            f.setControleAr(rs.getBoolean(11));
            f.setidEmpresa(rs.getInt(12));
            lf.add(f);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
      return (lf);
   }
   
  

   
   // Selecionar Funcionario e nome da Empresa Junto
   public ArrayList<Funcionario> SelecionarEmpresaFuncionario(Connection conn)
   {
      String sqlSelect = "select * from Funcionario f INNER JOIN Empresa e on f.id_Empresa=e.id where f.cancelado=0 and e.cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      ArrayList<Funcionario> lf = new ArrayList<Funcionario>();
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
            Funcionario f = new Funcionario();
            f.setidFuncionario(rs.getInt(1));
            f.setNome(rs.getString(2));
            f.setFuncao(rs.getString(3));
            f.setDataNasc(rs.getString(4));
            f.setRG(rs.getString(5));
            f.setCPF(rs.getString(6));
            f.setLogin(rs.getString(7));
            f.setSenha(rs.getString(8));
            f.setAcessoCatraca(rs.getString(9));
            f.setAcessoSistema(rs.getBoolean(10));
            f.setControleAr(rs.getBoolean(11));            
            f.setidEmpresa(rs.getInt(12));
            f.setEmpresa(rs.getString(14));
            lf.add(f);
         }
         for(int i=0;i<lf.size();i++){
            System.out.println(lf.get(i).dados());
         }
      
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
      return (lf);
   }
   
   
  
   
   public void excluir(Connection conn,Funcionario func)
   {
      String sqlDelete = "UPDATE Funcionario SET cancelado=1 where id = ?";
      PreparedStatement stm = null;
      try
      {
         stm = conn.prepareStatement(sqlDelete);
         stm.setInt(1, func.getidFuncionario());
         stm.execute();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
   }
   
   
    public int Selecionarid(int i,Connection conn)
   {
      String sqlSelect = "SELECT id FROM Funcionario WHERE id=? and cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      int cont=0;
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         stm.setInt(1,i);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
            cont=1;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
     return(cont);
   }
   
   
   
    public int ContarFuncionario(Connection conn)
   {
     String sqlSelect = "SELECT id FROM Funcionario WHERE cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      int cont=0;
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
            cont++;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
     return(cont);
   }


 

   public ArrayList<Funcionario> SelecionarNomeEmpresa(String n,Connection conn)
   {
      String sqlSelect = "select * from Funcionario f INNER JOIN Empresa e on f.id_Empresa=e.id where f.login=? and f.cancelado=0 and e.cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      ArrayList<Funcionario> lf = new ArrayList<Funcionario>();
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         stm.setString(1,n);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
             Funcionario f = new Funcionario();
            f.setidFuncionario(rs.getInt(1));
            f.setNome(rs.getString(2));
            f.setFuncao(rs.getString(3));
            f.setDataNasc(rs.getString(4));
            f.setRG(rs.getString(5));
            f.setCPF(rs.getString(6));
            f.setLogin(rs.getString(7));
            f.setSenha(rs.getString(8));
            f.setAcessoCatraca(rs.getString(9));
            f.setAcessoSistema(rs.getBoolean(10));
            f.setControleAr(rs.getBoolean(11));            
            f.setidEmpresa(rs.getInt(12));
            f.setEmpresa(rs.getString(15));
            f.setTemperatura(rs.getString(21));
            lf.add(f);
         }
      
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
      return (lf);
   }
   
   
   public ArrayList<Funcionario> BuscarControleFuncionario(String n , Connection conn)
   {
      String sqlSelect = "SELECT * FROM Funcionario WHERE nome like '"+n+"%' and cancelado=0";
      PreparedStatement stm = null;
      ResultSet rs = null;
      ArrayList<Funcionario> lf = new ArrayList<Funcionario>();
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         rs = stm.executeQuery();
      
         while (rs.next())
         {
            Funcionario f = new Funcionario();
            f.setidFuncionario(rs.getInt(1));
            f.setNome(rs.getString(2));
            f.setFuncao(rs.getString(3));
            f.setDataNasc(rs.getString(4));
            f.setRG(rs.getString(5));
            f.setCPF(rs.getString(6));
            f.setLogin(rs.getString(7));
            f.setSenha(rs.getString(8));
            f.setAcessoCatraca(rs.getString(9));
            f.setAcessoSistema(rs.getBoolean(10));
            f.setControleAr(rs.getBoolean(11));
            f.setidEmpresa(rs.getInt(12));
            lf.add(f);
         }
      
      
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
     return(lf);
   } */
}