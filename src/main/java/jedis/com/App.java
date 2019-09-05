package jedis.com;
import java.awt.image.ColorConvertOp;
import java.util.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple; 

public class App 
{
	public static void main(String[] args) { 
		//Conectando no Redis server -  localhost 
		Jedis jedis = new Jedis("localhost"); 
		String result = "";
Long pontuacaoExiste;
		Boolean existe;
		int pontua = 0;
		int timeint = 0;
		String jogo = "";
		String rodada = "";
		String time = "";
		String a = "5";
		String b = "5";
		String usuario = "";
		String vencedor = "";
		String apelido = "";
		String Nome = "";
		String DataNascimento = "";
		String Genero = "";
		String Pais = "";
		String Estado = "";
		String Cidade = "";
		String Rua = "";
		String Complemento = "";
		String CodigoPostal = "";

		existe = false;
		int op = 6;
		//	      jedis.hset("RODADA:" + Integer.toString(x), " " + Integer.toString(z) , " " + res); 
		// jedis.hset("rodada:" + a, b, res);

		System.out.println("1 - Cadastar usuário");
		System.out.println("2 - Exibir Resultado dos Jogos");
		System.out.println("3 - Realizar Jogos");
		System.out.println("4 - Realizar Aposta");
		System.out.println("5 - Ranking");
		System.out.println("6 - Adicionar Novo Endereço");
		System.out.println("0 - Sair");
		//		System.out.println(jedis.zrangeWithScores("rank", 0, -1).getClass());
	//Set<Tuple> elements = jedis.zrevrangeWithScores("rank", 0, -1);
	//	for(Tuple tuple: elements){
	//		System.out.println(tuple.getElement() + " " + tuple.getScore());
	//	}
		Scanner tc = new Scanner(System.in);
		op = tc.nextInt();

		do {
			switch (op) {
			case 1:
				System.out.println("Digite o apelido do usuário(unico)!");
				apelido = tc.next();
				existe = jedis.sismember("apelido", apelido);
				if (existe == false) {
					jedis.sadd("apelido", apelido);
					System.out.println("Digite o nome:");
					Nome = tc.next();
					jedis.hset("apelido:" + apelido , "nome", Nome);
					System.out.println("Digite a data de nascimento:");
					DataNascimento = tc.next();
					jedis.hset("apelido:" + apelido , "datanascimento", DataNascimento);
					System.out.println("Digite seu genero (M ou F):");
					Genero = tc.next();
					jedis.hset("apelido:" + apelido , "genero:", Genero);
					System.out.println("Pais:");
					Pais = tc.next();
					//jedis.hset("apelido:" + apelido , "pais", Pais);
					jedis.rpush("pais:"+apelido, Pais);
					System.out.println("Estado:");
					Estado = tc.next();
					//jedis.hset("apelido:" + apelido , "estado", Estado);
					jedis.rpush("estado:"+apelido, Estado);
					System.out.println("Cidade:");
					Cidade = tc.next();
					//jedis.hset("apelido:" + apelido , "cidade", Cidade);
					jedis.rpush("cidade:"+apelido, Cidade);
					System.out.println("Rua:");
					Rua = tc.next();
					//jedis.hset("apelido:" + apelido , "rua", Rua);
					jedis.rpush("rua:"+apelido, Rua);
					System.out.println("Complemento:");
					Complemento = tc.next();
					//jedis.hset("apelido:" + apelido , "complemento", Complemento);
					jedis.rpush("complemento:"+apelido, Complemento);
					System.out.println("Código Postal:");
					CodigoPostal = tc.next();
					//jedis.hset("apelido:" + apelido , "codigoPostal", CodigoPostal);
					jedis.rpush("codigopostal:"+apelido, CodigoPostal);
					System.out.println("1 - Cadastar usuário");
					System.out.println("2 - Exibir Resultado dos Jogos");
					System.out.println("3 - Realizar Jogos");
					System.out.println("4 - Realizar Aposta");
					System.out.println("5 - Ranking");
					System.out.println("6 - Adicionar Novo Endereço");
					System.out.println("0 - Sair");
					op = tc.nextInt();	
				}
				else {
					System.out.println("Apelido já existente!");
				}
				break;
			case 2:
				for (int i = 1; i < 39; i++) {
					for (int j = 1; j < 11; j++) {
						System.out.println("Rodada:" + i + " Jogo:" + j + " Resultado: " + jedis.hget("rodada:" + Integer.toString(i), Integer.toString(j)));
					}
				}
				System.out.println("1 - Cadastar usuário");
				System.out.println("2 - Exibir Resultado dos Jogos");
				System.out.println("3 - Realizar Jogos");
				System.out.println("4 - Realizar Aposta");
				System.out.println("5 - Ranking");
				System.out.println("6 - Adicionar Novo Endereço");
				System.out.println("0 - Sair");
				op = tc.nextInt();
				break;
			case 3:
				for (int i = 1; i < 39; i++) {
					for (int j = 1; j < 11; j++) {
						Random r = new Random();
						int resultado = r.nextInt(2) ;
						if (resultado == 0) {
							vencedor = "Empate";
						}
						if (resultado == 1) {
							vencedor = "Time A";
						}
						if (resultado == 2) {
							vencedor = "Time B";
						}
						jedis.hset("rodada:" + Integer.toString(i), Integer.toString(j), vencedor);
					}
				}
				System.out.println("1 - Cadastar usuário");
				System.out.println("2 - Exibir Resultado dos Jogos");
				System.out.println("3 - Realizar Jogos");
				System.out.println("4 - Realizar Aposta");
				System.out.println("5 - Ranking");
				System.out.println("6 - Adicionar Novo Endereço");
				System.out.println("0 - Sair");
				op = tc.nextInt();
				break;
			case 4:
				System.out.println("Informe o apelido do usuario que ira realizar a aposta:");
				usuario = tc.next();
				existe = jedis.sismember("apelido", usuario);
				if (existe == true) {
					System.out.println("Informe a rodada que deseja apostar:");
					rodada = tc.next();
					System.out.println("Informe o jogo que deseja apostar:");
					jogo = tc.next();
					System.out.println("Informe o time que deseja apostar:");
					System.out.println("0 - Empate | 1 - Time A | 2 - Time B");
					timeint = tc.nextInt();
					if (timeint == 1) {
						time = "Time A";
					}
					if (timeint == 2) {
						time = "Time B";
					}
					if (timeint == 0) {
						time = "Empate";
					}

					int guarda = 0;
					result = jedis.hget("rodada:" + rodada, jogo);
					System.out.println("Resultado: " + result);
					System.out.println("Time Escolhido: " + time);
					if (result.equals(time)) {
						pontua = pontua + 1;
				//		System.out.println(jedis.zrangeWithScores("rank", 0, -1));
						System.out.println("Pontuou");
						//						jedis.zadd("rank", pontua, usuario);
						Set<Tuple> elemento = jedis.zrevrangeWithScores("rank", 0, -1);
						for(Tuple tuple: elemento){
							System.out.println(tuple.getElement() + " " + tuple.getScore());
							pontuacaoExiste = jedis.zrank("rank", usuario);
							if (pontuacaoExiste == null) {
								guarda = 1;
								jedis.zadd("rank", guarda, usuario);
								guarda = 0;
							}
							if (tuple.getElement().equals(usuario)) {
								guarda = (int) tuple.getScore() + 1;
								jedis.zadd("rank", guarda, usuario);
							}
						}
					}
					else {
						pontua = pontua;
						System.out.println("Não Pontuou");
					}
//					jedis.hmset(1, hash)
					System.out.println("1 - Cadastar usuário");
					System.out.println("2 - Exibir Resultado dos Jogos");
					System.out.println("3 - Realizar Jogos");
					System.out.println("4 - Realizar Aposta");
					System.out.println("5 - Ranking");
					System.out.println("6 - Adicionar Novo Endereço");
					System.out.println("0 - Sair");
					op = tc.nextInt();
				}
				else {
					System.out.println("Usuário inválido!");
				}
				existe = false;
				pontuacaoExiste = null;
				break;
			case 5:
				Set<Tuple> elemento = jedis.zrevrangeWithScores("rank", 0, -1);
				System.out.println("-- RANKING --");
				for(Tuple tuple: elemento){

					System.out.println(tuple.getElement() + " | " + tuple.getScore());
					}
				System.out.println("1 - Cadastar usuário");
				System.out.println("2 - Exibir Resultado dos Jogos");
				System.out.println("3 - Realizar Jogos");
				System.out.println("4 - Realizar Aposta");
				System.out.println("5 - Ranking");
				System.out.println("6 - Adicionar Novo Endereço");
				System.out.println("0 - Sair");
				op = tc.nextInt();
				break;
			case 6:
				System.out.println("Digite o apelido do usuário(unico)!");
				apelido = tc.next();
				existe = jedis.sismember("apelido", apelido);
				if (existe == true) {
					System.out.println("Pais:");
					Pais = tc.next();
					//jedis.hset("apelido:" + apelido , "pais", Pais);
					jedis.rpush("pais:"+apelido, Pais);
					System.out.println("Estado:");
					Estado = tc.next();
					//jedis.hset("apelido:" + apelido , "estado", Estado);
					jedis.rpush("estado:"+apelido, Estado);
					System.out.println("Cidade:");
					Cidade = tc.next();
					//jedis.hset("apelido:" + apelido , "cidade", Cidade);
					jedis.rpush("cidade:"+apelido, Cidade);
					System.out.println("Rua:");
					Rua = tc.next();
					//jedis.hset("apelido:" + apelido , "rua", Rua);
					jedis.rpush("rua:"+apelido, Rua);
					System.out.println("Complemento:");
					Complemento = tc.next();
					//jedis.hset("apelido:" + apelido , "complemento", Complemento);
					jedis.rpush("complemento:"+apelido, Complemento);
					System.out.println("Código Postal:");
					CodigoPostal = tc.next();
					//jedis.hset("apelido:" + apelido , "codigoPostal", CodigoPostal);
					jedis.rpush("codigopostal:"+apelido, CodigoPostal);
					System.out.println("1 - Cadastar usuário");
					System.out.println("2 - Exibir Resultado dos Jogos");
					System.out.println("3 - Realizar Jogos");
					System.out.println("4 - Realizar Aposta");
					System.out.println("5 - Ranking");
					System.out.println("6 - Adicionar Novo Endereço");
					System.out.println("0 - Sair");
					op = tc.nextInt();	
				}
				else {
					System.out.println("Apelido já existente!");
				}
				break;
			case 0:
				break;

			default:
				break;
			}
		}while (op != 0);
		/*System.out.println("Connecção com o servidor"); 
	      //Testando se o servidor está execuando 
	      System.out.println("Servidor está executando: "+jedis.ping()); 
	      //Inserindo os dados em uma estrutura do tipo "string" 
	      jedis.set("ChaveExemplo", "Criad o primeiro conjunto chave-valor !"); 
	      // Recuperando os dados e mostrando na tela 
	      System.out.println("Lendo o valor guardado na chave : "+ jedis.get("ChaveExemplo")); 
	      //Inserindo os dados em uma estrutura do tipo Lista "list"
	      jedis.lpush("ListaExemplo", "Redis"); 
	      jedis.lpush("ListaExemplo", "Aula"); 
	      jedis.lpush("ListaExemplo", "Trabalho de BD"); 
	      //  Recuperando os dados e mostrando na tela 
	      List<String> list = jedis.lrange("ListaExemplo", 0 ,100); 

	      for(int i = 0; i<list.size(); i++) { 
	         System.out.println("Valor guardado na lista : "+list.get(i)); 
	      } */




	}  
}