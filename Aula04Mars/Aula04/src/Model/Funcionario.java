package Model;

public class Funcionario
{
private int idFuncionario=0;
private String nome="";
private String funcao="";
private String datanasc="";
private String rg="";
private String cpf="";
private String login="";
private String senha="";
private String horarioAcessoCatraca="";
private int idEmpresa=0;
private String Empresa="";
private boolean AcessoSistema=false;
private boolean ControleAr=false;
private String temperatura="";
   private Object result;  
   //Método Construtor
   public Funcionario(String nome, String funcao, String datanasc, String rg, String cpf, String login, String senha, String horarioAcessoCatraca,boolean AcessoSistema,boolean ControleAr,int idEmpresa)
   {
      setNome(nome);
      setFuncao(funcao);
      setDataNasc(datanasc);
      setRG(rg);
      setCPF(cpf);
      setLogin(login);
      setSenha(senha);
      setAcessoCatraca(horarioAcessoCatraca);
      setAcessoSistema(AcessoSistema);
      setControleAr(ControleAr);
      setidEmpresa(idEmpresa);
   } 
   
   public Funcionario()
   {
   }
   
    public Funcionario(int idFuncionario)
   {
    setidFuncionario(idFuncionario);
   }
   
   public Funcionario(int idFuncionario,String nome, String funcao, String datanasc, String rg, String cpf, String login, String senha, String horarioAcessoCatraca,boolean AcessoSistema,boolean ControleAr,int idEmpresa)
   {
      setidFuncionario(idFuncionario);
      setNome(nome);
      setFuncao(funcao);
      setDataNasc(datanasc);
      setRG(rg);
      setCPF(cpf);
      setLogin(login);
      setSenha(senha);
      setAcessoCatraca(horarioAcessoCatraca);
      setAcessoSistema(AcessoSistema);
      setControleAr(ControleAr);
      setidEmpresa(idEmpresa);
   } 
   
   
   
   //Método Modificador
   public void setidFuncionario(int idFuncionario)
   {
      this.idFuncionario = idFuncionario;
   } 
   
   public void setNome(String nome)
   {
      this.nome = nome;
   }
   
   public void setFuncao(String funcao)
   {
      this.funcao = funcao;
   }
   
   public void setDataNasc(String datanasc)
   {
      this.datanasc = datanasc;
   }   
   
   public void setRG(String rg)
   {
      this.rg = rg;
   }
   
   public void setCPF(String cpf)
   {
      this.cpf = cpf;
   }
   
   public void setLogin(String login)
   {
      this.login = login;
   }
   
   public void setSenha(String senha)
   {
      this.senha = senha;
   }
   
   public void setAcessoCatraca(String horarioAcessoCatraca)
   {
      this.horarioAcessoCatraca = horarioAcessoCatraca;
   }
   
   public void setAcessoSistema(boolean AcessoSistema)
   {
      this.AcessoSistema = AcessoSistema;
   }
   
   public void setControleAr(boolean ControleAr)
   {
      this.ControleAr = ControleAr;
   }
   
   
   public void setidEmpresa(int idEmpresa)
   {
      this.idEmpresa = idEmpresa;
   } 
   
   public void setEmpresa(String Empresa)
   {
      this.Empresa = Empresa;
   }
   
      
   //Método De Acesso
   public int getidFuncionario()
   {
      return this.idFuncionario;   
   } 
   
   public String getNome()
   {
      return this.nome;   
   } 
   
   public String getFuncao()
   {
      return this.funcao;
   }
   
   public String getDataNasc()
   {
      return this.datanasc;
   }   
   
   public String getRG()
   {
      return this.rg;
   }
   
   public String getCPF()
   {
      return this.cpf;
   }
   
   public String getLogin()
   {
      return this.login;
   }
   
   public String getSenha()
   {
      return this.senha;
   }
   
   public String getAcessoCatraca()
   {
      return this.horarioAcessoCatraca;
   }
   
   public boolean getAcessoSistema()
   {
    return this.AcessoSistema;
   }
   
   public boolean getControleAr()
   {
     return this.ControleAr;
   }
   
    public int getidEmpresa()
   {
      return this.idEmpresa;   
   } 
   
   public String getEmpresa()
   {
     return this.Empresa;
   }
   
     //Temperatura
   public void setTemperatura(String temp)
   {
      temperatura = temp;
   }
   public String getTemperatura()
   {
      return temperatura;   
   }

      
   // Mostrar Tudo dos Funcionario(Para Teste)
   public String dados()
   {
      String msg = "         " + getidFuncionario()+ "          "+"             "+getNome()+"                   "+getRG()+"                      "+getCPF()+"                        "+getDataNasc()+"\n";
      return msg;
   }
   
   public String dadosEmpresa()
   {
      String msg = getidEmpresa()+" - "+getEmpresa()+"\n";
      return msg;
   }
   
   public String IdFuncionario()
   {
      String msg = ""+getidFuncionario();
      return msg;
   }
   
       public Object Campos(int i){
         result="";
      if(i==0){
      
        result=getidFuncionario();
      }
      if(i==1){
      
        result=getNome();
      }
      if(i==2){
      
        result=getRG();
      }
      if(i==3){
      
        result=getCPF();
      }
      if(i==4){
      
        result=getDataNasc();;
      }
      return result;
      
   }
} 