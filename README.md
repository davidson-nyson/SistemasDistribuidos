# SistemasDistribuidos
# SistemasDistribuidos
Projetos da Matéria Sistemas Distribuídos do Curso de Graduação em Ciência da Computação

O Projeto conta com 4 arquivos:
 * Cliente: Arquivo para o programa cliente que solicita os cálculos. É a interface com a usuário;
 * Server: Servidor intermediári que recebe as solicitações do cliente e decide para qual servidor(slave) será enviado a requisição;
 * ServerArit(Slave): Servidor que calcula as operações básicas (Adição, Subtração, Multiplicação e Divisão)
 * ServerEsp(Slave):Servidor que calcula as operações especiais (Raiz Quadrada, Percentual e Potência)
 
 
## Cliente.java
Configurado na porta 12345 e sempre rodando no localhost.
Aqui é apresentado o menu de opções para o usuário e feito os tratamentos iniciais nas informações como:
* Números inválidos, opçõse inválida e conexões finalizadas com o servidor(Server.java).

## Server.java
Configurado na porta 12345 e sempre rodando no localhost.
Aqui foi utilizado uma lista do tipo String para armezar o IP dos servidores slave, sendo que cada tipo de servidor possui sua própria lista. No servidor é feito o tratamento caso os servidores slave estejam desconectados realizando 5 tentativas de conexão com cada até que retorna para o cliente que não existem servidores de cálculo disponíveis.
A lista de servidores é utiliza para simular uma distribuição de carga, para que um só servidor não receba todas as solicitações.

## ServerArit.java
Configurado na porta 12347 e sempre rodando no localhost.
Recebe as requisições do servidor principalm, realiza os cálculos e retorna o resultado.
Calcula:
* Adição; 
* Subtração; 
* Multiplicação; 
* Divisão.

## ServerEsp.java
Configurado na porta 12347 e sempre rodando no localhost.
Recebe as requisições do servidor principalm, realiza os cálculos e retorna o resultado.
 Calcula:
 * Raiz Quadrada;
 * Percentual;
 * Potência.
