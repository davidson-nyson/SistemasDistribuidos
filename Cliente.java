
import java.io.*;
import java.net.*;
public class Cliente extends Thread {
//	Flag que indica quando se deve terminar a execução.
	private static boolean done = false;
	public static void main(String args[]) {
		try {						
			
			//Socket conexao = new Socket("22.0.3.113", 12345);					
				Socket conexao = new Socket("127.0.0.1", 12345);		
				Thread t = new Cliente(conexao);
			
				PrintStream saida = new PrintStream(conexao.getOutputStream());
				BufferedReader entrada = new BufferedReader(new	InputStreamReader(conexao.getInputStream()));		
				BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
				t.start();			
									
			String linha;
			Double aux;
			while (true) {
				System.out.println(" ");				
				System.out.println("Qual operacao deseja realizar? ");
				System.out.println(" 1 - Adicao ");
				System.out.println(" 2 - Subtracao ");
				System.out.println(" 3 - Multiplicacao");
				System.out.println(" 4 - Divisao");
				System.out.println(" 5 - Raiz Quadrada");	
				System.out.println(" 6 - Porcentagem");
				System.out.println(" 7 - Potenciacao");
				System.out.println(" 8 - Sair");
				System.out.print(" >> ");
				linha = teclado.readLine();
				try{				    			
					if(Integer.parseInt(linha) > 8){
						System.out.println("Opcao Invalida!");	
						continue;
					}
				
				
				
				
				
				}catch (Exception e){
					System.out.println("Opcao Invalida!");	
					continue;
				}
				saida.println(linha);
				
				if(Integer.parseInt(linha) <= 4 ){
					System.out.print("Digite o primeiro numero: ");
					linha = teclado.readLine();
					try{ aux = Double.parseDouble(linha);}catch(Exception e){System.out.println("Numero Invalido!");continue;}
					saida.println(linha);
					System.out.print("Digite o segundo numero: ");
					linha = teclado.readLine();
					try{ aux = Double.parseDouble(linha);}catch(Exception e){System.out.println("Numero Invalido!");continue;}
					saida.println(linha);
				}else if( Integer.parseInt(linha) == 6){
					System.out.print("Digite o numero: ");
					linha = teclado.readLine();
					saida.println(linha);
					System.out.print("Digite o percentual: ");
					linha = teclado.readLine();
					saida.println(linha);					
				}else if(Integer.parseInt(linha) == 7){
					System.out.print("Digite o numero: ");
					linha = teclado.readLine();
					saida.println(linha);
					System.out.print("Digite o expoente: ");
					linha = teclado.readLine();
					saida.println(linha);					
				}else if(Integer.parseInt(linha) == 8){
					conexao.close();
					done = true;
					break;
				}else{
					System.out.print("Digite o numero: ");
					linha = teclado.readLine();
					saida.println(linha);					
					linha = "2";
					saida.println(linha); //Envia automaticamente o 2 para saber que é raiz quadrada.
				}

				linha = entrada.readLine();
				System.out.println(">>> RESULTADO: " + linha);
				System.out.println("+----------------------------------------------------------+");
//				antes de enviar, verifica se a conexão não foi fechada
				//if (done) {break;}
										
			}
		}
		catch (IOException e) {
//			Caso ocorra alguma excessão de E/S, mostre qual foi.
			System.out.println("Servidor Indisponivel");			
			
		}
		
	}
//	parte que controla a recepção de mensagens deste cliente
	private Socket conexao;

//	construtor que recebe o socket deste cliente
	public Cliente(Socket s) {
		conexao = s;
	}
		
	
//	execução da thread
	public void run() {	}
}