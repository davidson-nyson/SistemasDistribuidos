
import java.io.*;
import java.net.*;
import java.util.*;
import java.math.*;

public class ServerEsp extends Thread  {
   public static void main(String args[]) {
		
		try {
//			criando um socket 
			ServerSocket s = new ServerSocket(12348); //Porta do Servidor Aritmetico
			System.out.println("### Servidor Iniciado!");
			while (true) {
//				
				//System.out.print("Aguardando request...");
				Socket conexao = s.accept(); //retorna o objeto
				System.out.println("Nova Conexao!");
//				cria uma nova thread 
				Thread t = new ServerEsp(conexao);
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
//	construtor que recebe o socket deste cliente
	public ServerEsp(Socket s) {
            conexao = s;
	}
//	execução da thread
	public void run() {
		try {
//			objetos que permitem controlar fluxo de comunicação
			BufferedReader entrada = new BufferedReader(new
					InputStreamReader(conexao.getInputStream()));
			PrintStream saida = new PrintStream(conexao.getOutputStream());
			

			String linha = "x";
			String num1 = null;
			String num2 = null;
			String opcao = null;
			Double aux2 =0.0;
			
			while (linha != null && !(linha.trim().equals(""))) {				
				linha = entrada.readLine();
				if(linha == null) break;
				System.out.println(".> " + linha);					
				if(num1 != null && num2 == null)
					num2 = linha;				
				if(num1 == null && opcao != null)
					num1 = linha;
				if(num1 == null && num2 == null) 
					opcao = linha;				
				
				//Escreve a operação no Servidor, para Testes
				if(num1 != null && num2 != null && opcao != null){
					System.out.println("Operacao " + opcao + " com numeros: " +  num1 +" e " + num2);
					//System.out.println("VALORES: " + opcao + ", " + num1 +", " + num2);					
					
					
					if(Integer.parseInt(opcao) == 5){
						System.out.println("#RAIZ ");
						aux2 = Math.sqrt(Double.parseDouble(num1));
						System.out.println("=> Raiz Quadrada de " +  num1 + " = " + Double.toString(aux2));						
					}
					if(Integer.parseInt(opcao) == 6){
						System.out.println("#PORCENTAGEM ");
						aux2 = Double.parseDouble(num1) * (Double.parseDouble(num2)/100);
						System.out.println("=> " +  num2 +"% de " + num1 + " = " + Double.toString(aux2));						
					}
					if(Integer.parseInt(opcao) == 7){
						System.out.println("#POTENCIA ");
						aux2 = Math.pow(Double.parseDouble(num1), Double.parseDouble(num2));
						System.out.println("=> " +  num1 +" ^ " + num2 + " = " + Double.toString(aux2));						
					}					
					
					saida.println(Double.toString(aux2));
					System.out.println("...RESULTADO ENVIADO");
					System.out.println(" ");
					num1 = null;
					num2 = null;
					opcao = null;
				}
			}

			conexao.close();
		}
		catch (IOException e) {
//			exceção 
			System.out.println("IOException: " + e);
		}
	}

}