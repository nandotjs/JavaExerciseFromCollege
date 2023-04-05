package br.edu.up.front;

import br.edu.up.persistencia.*;
import br.edu.up.entidades.*;
import br.edu.up.negocio.*;

public class Principal {
	public static void main(String[] args) {

		int opt;
		Objeto objeto = null;

		do {

			printMenu();
			opt = Console.readInt("Escolha uma opção: ");
			switch (opt) {
				case 1:
					// SALVAR OBJETO NO ARRAY //
					objeto = new Objeto();
					objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
					if (Persistencia.buscarObjeto(objeto) == null) {
						objeto.setcTotal(Console.readDouble("Informe a quantidade de GB disponíveis: "));					
						Persistencia.incluirObjeto(objeto);
						System.out.println("Objeto incluido.");
						System.out.println(objeto.getTipo() + " com " + objeto.getcTotal() + " GB de armazenamento.");
					} else {
						System.out.println("Objeto já cadastrado.");
					}
					break;
				case 2:
					// ADICIONAR DADOS //
					objeto = new Objeto();
					objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
					objeto = Persistencia.buscarObjeto(objeto);
					if (objeto != null) {
						String unidade = Console.readString("Digite a unidade de medida (kbyte, mbyte, gbyte): ");
						double quantidade = Console.readDouble("Digite a quantidade de informação a ser GRAVADA: ");
						if (Negocio.conversor(unidade, quantidade) == 0){
							System.out.println("Unidade inválida: " + unidade + "."); 
						} else {
							if (Negocio.gravarDados(objeto, unidade, quantidade)) {
								System.out.println("Dados gravados com sucesso!");
								System.out.println(objeto.getTipo());
								System.out.println("Total: " + objeto.getcTotal());
								System.out.println("Disponivel: " + objeto.getcDisponivel());
								System.out.println("Ocupado: " + objeto.getcOcupada());
							} else {
								System.out.println("Não foi possível gravar os dados. Armazenamento insuficiente.");
							}
						}
							
					} else {
						System.out.println("Objeto não encontrado.");
					}
					break;
				case 3:
					// EXCLUIR DADOS //
					objeto = new Objeto();
					objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
					objeto = Persistencia.buscarObjeto(objeto);
					if (objeto != null) {
						String unidade = Console.readString("Digite a unidade de medida (kbyte, mbyte, gbyte): ");
						double quantidade = Console.readDouble("Espaço ocupado: " + objeto.getcOcupada() +
															   "GB. Digite a quantidade de informação a ser EXCLUIDA: ");
						if (Negocio.conversor(unidade, quantidade) == 0){
							System.out.println("Unidade inválida: " + unidade + "."); 
						} else {
							if (Negocio.excluirDados(objeto, unidade, quantidade)) {
								System.out.println("Dados excluidos com sucesso!");
								System.out.println(objeto.getTipo());
								System.out.println("Total: " + objeto.getcTotal());
								System.out.println("Disponivel: " + objeto.getcDisponivel());
								System.out.println("Ocupado: " + objeto.getcOcupada());
							} else {
								System.out.println("Não foi possível excluir os dados." + 
												   " Você esta tentando excluir mais dados do que tem armazenado.");
							}
						}
					} else {
						System.out.println("Objeto não encontrado.");
					}
					break;
				case 4:
					// MOSTRAR OBJETOS //
					System.out.println(" 1 - Escolher objeto \n" +
									   " 2 - Mostrar todos os objetos");
					opt = Console.readInt("Escolha uma opção: ");
					switch (opt) {
						case 1:
							// ESCOLHER OBJETO //
							objeto = new Objeto();
							objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
							objeto = Persistencia.buscarObjeto(objeto);
							if (objeto != null) {
								System.out.println(objeto.getTipo());
								System.out.println("Total: " + objeto.getcTotal());
								System.out.println("Disponivel: " + objeto.getcDisponivel());
								System.out.println("Ocupado: " + objeto.getcOcupada());
							} else {
								System.out.println("Objeto não encontrado.");
							}
							break;
						case 2:
							// MOSTRAR TODOS OS OBJETOS //
							if (Persistencia.listaObjetos.contains(objeto)) {
								Persistencia.mostrarObjetos();		
							} else {
								System.out.println("Nenhum objeto encontrado.");
							}
							break;
						default:
							System.out.println("Opção inválida.");
					}
					break;
				case 5:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (opt != 5);
	}

	// menu 1
	static void printMenu() {
		System.out.println(" - ARMAZENADOR DE DADOS - \n" +
						   " 1 - Armazenar \n" +
						   " 2 - Gravar dados \n" +
						   " 3 - apagar dados \n"+ 	
						   " 4 - Verifficar espaço disponível \n" + 
						   " 5 - Sair");
	}
}
