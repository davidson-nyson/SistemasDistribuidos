
import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread  {

  private static List<String> listaServerArit = new ArrayList<String>();
  private static List<String> listaServerEsp = new ArrayList<String>();
  //private static List<Integer> listaServerArit = new ArrayList<Integer>();

  private Integer auxArit = 0;
  private Integer auxEsp = 0;
  
  public static void main(String args[]) {
		listaServerArit.add("127.0.0.1"); //Inicialmente testando com portas diferentes
		listaServerArit.add("22.0.3.56");
		//listaServerArit.add(12347); //Teste com Portas
		//listaServerArit.add(12348);
		
		listaServerEsp.add("127.0.0.1"); //Inicialmente testando com portas diferentes
		listaServerEsp.add("22.0.2.113");
		//listaServerEsp.add("IP3");
		//Enumeration serverArit = listaServerArit.elements();
 
		try {
//			criando um socket 

			System.out.println("### Servidor Iniciado!");									
			ServerSocket s = new ServerSocket(12345); //Porta Servidor Intermediario			
			while (true) {				
				//System.out.print("Esperando alguem se conectar...");
				Socket conexao = s.accept(); //retorna o objeto
				System.out.println(" Nova Conexão!");
//				cria uma nova thread 
				Thread t = new Server(conexao);
				t.start();
//				voltando ao loop, esperando mais alguém se conectar.
			}
		}
		catch (IOException e) {
//			mensagem de erro
			System.out.println("IOException: " + e);
		}
	}
//	socket deste cliente
	private Socket conexao;	
	private Socket conexaoS;	
//	construtor que recebe o socket deste cliente
	public Server(Socket s) {
            conexao = s;
	}
	private Server(Socket s, Boolean a){
		conexaoS = s;
	}
	public String num1;
	public String num2;
	public String opcao;
	public String teste = "teste";
	public Integer Search = 0;
	
	
//	execução da thread
	public void run() {
		try {		  
		
			  System.out.println("ENTROU CLIENTE");
			BufferedReader entrada = new BufferedReader(new
				InputStreamReader(conexao.getInputStream()));
			PrintStream saida = new PrintStream(conexao.getOutputStream());
			num1 = null;
			num2 = null;
			opcao = null;
			

			String linha = "x";
			
			
			while (true && linha != null) {
				
				try{
					linha = entrada.readLine();
				}catch (IOException e){
					//System.out.println("...FIM CONEXAO CLIENTE");
					conexao.close();
					break;
				}

				System.out.println(".> " + linha);
				
				if(num1 != null && num2 == null)
					num2 = linha;				
				if(num1 == null && opcao != null)
					num1 = linha;
				if(num1 == null && num2 == null) {
					opcao = linha;
					try{
						if(Integer.parseInt(opcao) >= 8){num1 = "0"; num2="0";} //Caso a opcao seja invalida ou sair, ele pula o programa
					}catch (Exception e){
						System.out.println("D");
						conexao.close();
						break;
					}									
				}
				
				//Escreve a operação no Servidor para Testes
				if(num1 != null && num2 != null && opcao != null){
					if(Integer.parseInt(opcao) <= 4){	// Conexão Servidor de Operações Básicas						
						try {
							//System.out.println("A");
							Socket conexaoS = new Socket(listaServerArit.get(auxArit),12347); //Conexao Servidor Intermediario com Servidor Operacoes Basicas
							
							BufferedReader entradaS = new BufferedReader(new	InputStreamReader(conexaoS.getInputStream()));						
							PrintStream saidaS = new PrintStream(conexaoS.getOutputStream());
							System.out.println("CONEXAO SERVIDOR("+Integer.toString(auxArit) +") ESCRAVO TIPO A..."  );
							auxArit++;													
							if(auxArit > listaServerArit.size() - 1) auxArit = 0;
															
							//System.out.println("VALORES: " + opcao + ", " + num1 +", " + num2);
							saidaS.println(opcao);
							saidaS.println(num1);
							saidaS.println(num2);
							linha = entradaS.readLine();	
							System.out.println("[retorno]: " + linha);
							saida.println(linha);
							conexaoS.close();
							num1 = null;
							num2 = null;
							opcao = null;
							System.out.println("...FIM CONEXAO SERVIDOR ESCRAVO");
							
						}catch (IOException e){
							//System.out.println("W");
							conexaoS = null;
							if(conexaoS == null){ 								
								while (conexaoS == null){ //Testando a conexão com todos os servidores
									System.out.println("ERROR: Conexao Servidor("+Integer.toString(auxArit)+") Inexistente.");							
									auxArit++;
									if(auxArit > (listaServerArit.size() - 1)){auxArit = 0;	}

									try{
										Search++;										
										conexaoS = new Socket(listaServerArit.get(auxArit),12347);	
										
									}catch (IOException j){										
										if(Search == listaServerArit.size()){
											Search = 0;
											saida.println("ERROR: Todos Servidores Indisponiveis!");
											num1 = null;
											num2 = null;
											opcao = null;
											conexaoS = null;
											break;
										}
									}									
								}
							}
							if(opcao == null){continue;} //Caso não exista conexões com os servidores, ele continua a iteraçao
							
							BufferedReader entradaS = new BufferedReader(new	InputStreamReader(conexaoS.getInputStream()));						
							PrintStream saidaS = new PrintStream(conexaoS.getOutputStream());
							System.out.println("CONEXAO SERVIDOR("+Integer.toString(auxArit) +") ESCRAVO TIPO A..."  );
							//System.out.println("Z");
							auxArit++;													
							if(auxArit > listaServerArit.size() - 1) auxArit = 0;
															
							//System.out.println("VALORES: " + opcao + ", " + num1 +", " + num2);
							saidaS.println(opcao);
							saidaS.println(num1);
							saidaS.println(num2);
							linha = entradaS.readLine();	
							System.out.println("[retorno]: " + linha);
							saida.println(linha);
							conexaoS.close();
							num1 = null;
							num2 = null;
							opcao = null;
							System.out.println("...FIM CONEXAO SERVIDOR ESCRAVO");	
							
						}	
												
					}else if(Integer.parseInt(opcao) < 8){ //Conexão Servidor de Operações Especiais
						
						try {
							//System.out.println("A");
							Socket conexaoS = new Socket(listaServerEsp.get(auxEsp),12348); //Conexao Servidor Intermediario com Servidor Operacoes Basicas
							
							BufferedReader entradaS = new BufferedReader(new	InputStreamReader(conexaoS.getInputStream()));						
							PrintStream saidaS = new PrintStream(conexaoS.getOutputStream());
							System.out.println("CONEXAO SERVIDOR("+Integer.toString(auxEsp) +") ESCRAVO TIPO B..."  );
							auxEsp++;													
							if(auxEsp > listaServerEsp.size() - 1) auxEsp = 0;
															
							//System.out.println("VALORES: " + opcao + ", " + num1 +", " + num2);
							saidaS.println(opcao);
							saidaS.println(num1);
							saidaS.println(num2);
							linha = entradaS.readLine();	
							System.out.println("[retorno]: " + linha);
							saida.println(linha);
							conexaoS.close();
							num1 = null;
							num2 = null;
							opcao = null;
							System.out.println("...FIM CONEXAO SERVIDOR ESCRAVO");
							
						}catch (IOException e){
							//System.out.println("W");
							conexaoS = null;
							if(conexaoS == null){ 								
								while (conexaoS == null){ //Testando a conexão com todos os servidores
									System.out.println("ERROR: Conexao Servidor("+Integer.toString(auxArit)+") Inexistente.");							
									auxEsp++;
									if(auxEsp > (listaServerEsp.size() - 1)){auxEsp = 0;	}

									try{
										Search++;										
										conexaoS = new Socket(listaServerEsp.get(auxEsp),12348);	
										
									}catch (IOException j){										
										if(Search == listaServerEsp.size()){
											Search = 0;
											saida.println("ERROR: Todos Servidores Indisponiveis!");
											num1 = null;
											num2 = null;
											opcao = null;
											conexaoS = null;
											break;
										}
									}									
								}
							}
							if(opcao == null){continue;} //Caso não exista conexões com os servidores, ele continua a iteraçao
							
							BufferedReader entradaS = new BufferedReader(new	InputStreamReader(conexaoS.getInputStream()));						
							PrintStream saidaS = new PrintStream(conexaoS.getOutputStream());
							System.out.println("CONEXAO SERVIDOR("+Integer.toString(auxEsp) +") ESCRAVO TIPO A..."  );
							//System.out.println("Z");
							auxEsp++;													
							if(auxEsp > listaServerEsp.size() - 1) auxEsp = 0;
															
							//System.out.println("VALORES: " + opcao + ", " + num1 +", " + num2);
							saidaS.println(opcao);
							saidaS.println(num1);
							saidaS.println(num2);
							linha = entradaS.readLine();	
							System.out.println("[retorno]: " + linha);
							saida.println(linha);
							conexaoS.close();
							num1 = null;
							num2 = null;
							opcao = null;
							System.out.println("...FIM CONEXAO SERVIDOR ESCRAVO");	
							
						}	

							
					}else if(Integer.parseInt(opcao) == 8){
						//System.out.println("...FIM CONEXAO CLIENTE");
						conexao.close();
					}else{
						System.out.println("Opcao Inválida!");
						num1 = null;
						num2 = null;
						opcao = null;
					}
				}
			}
			
		System.out.println("...FIM CONEXAO CLIENTE");			  
		//if(Integer.parseInt(opcao) != 8)conexao.close();
		}
		catch (IOException e) {
			
			System.out.println("IOException: " + e);
		}
	}

}