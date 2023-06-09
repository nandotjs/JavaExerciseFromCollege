package br.edu.up.front;

import br.edu.up.persistencia.*;
import br.edu.up.entidades.*;
import br.edu.up.negocio.*;

import java.text.DecimalFormat;
// import java.util.Scanner;


public class Principal {
	public static void main(String[] args) {
		// Scanner só para eu lembrar que existe. Ele faz eu escrever as coisas em 2 linhas, não gostei.
		// Scanner scanner = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("#.##");
		int opt;
		Objeto objeto = null;

		do {

			printMenu();
			opt = Console.readInt("Escolha uma opção: ");
			switch (opt) {
				case 1 -> {
					// SALVAR OBJETO NO ARRAY //
					objeto = new Objeto();
					objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
					if (Persistencia.buscarObjeto(objeto) == null) {
						objeto.setcTotal(Console.readDouble("Informe a quantidade de GB disponíveis: "));
						Persistencia.incluirObjeto(objeto);
						System.out.println("Objeto incluido.");
						System.out.println(objeto.getTipo() + " com " + df.format(objeto.getcTotal()) + " GB de armazenamento.");
					} else {
						System.out.println("Objeto já cadastrado.");
					}
				}
				case 2 -> {
					// ADICIONAR DADOS //
					objeto = new Objeto();
					objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
					objeto = Persistencia.buscarObjeto(objeto);
					if (objeto != null) {
						String unidade = Console.readString("Digite a unidade de medida (kbyte, mbyte, gbyte): ");
						double quantidade = Console.readDouble("Digite a quantidade de informação a ser GRAVADA: ");
						if (Negocio.conversor(unidade, quantidade) == 0) {
							System.out.println("Unidade inválida: " + unidade + ".");
						} else {
							if (Negocio.gravarDados(objeto, unidade, quantidade)) {
								System.out.printf("""
													Dados gravados com sucesso!
													Tipo: %s
													Total: %s GB
													Disponivel: %s GB
													Ocupado: %s GB
													%n""", objeto.getTipo(), df.format(objeto.getcTotal()), df.format(objeto.getcDisponivel()), df.format(objeto.getcOcupada()));

							} else {
								System.out.println("Não foi possível gravar os dados. Armazenamento insuficiente.");
							}
						}

					} else {
						System.out.println("Objeto não encontrado.");
					}
				}
				case 3 -> {
					// EXCLUIR DADOS //
					objeto = new Objeto();
					objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
					objeto = Persistencia.buscarObjeto(objeto);
					if (objeto != null) {
						String unidade = Console.readString("Digite a unidade de medida (kbyte, mbyte, gbyte): ");
						double quantidade = Console.readDouble("Espaço ocupado: " + df.format(objeto.getcOcupada()) +
								" GB. Digite a quantidade de informação a ser EXCLUIDA: ");
						if (Negocio.conversor(unidade, quantidade) == 0) {
							System.out.println("Unidade inválida: " + unidade + ".");
						} else {
							if (Negocio.excluirDados(objeto, unidade, quantidade)) {
								System.out.printf("""
													Dados excluidos com sucesso!
													Tipo: %s
													Total: %s GB
													Disponivel: %s GB
													Ocupado: %s GB
													%n""", objeto.getTipo(), df.format(objeto.getcTotal()), df.format(objeto.getcDisponivel()), df.format(objeto.getcOcupada()));
							} else {
								System.out.println("Não foi possível excluir os dados." +
										" Você esta tentando excluir mais dados do que tem armazenado.");
							}
						}
					} else {
						System.out.println("Objeto não encontrado.");
					}
				}
				case 4 -> {
					// MOSTRAR OBJETOS //
					System.out.println("""
										1 - Escolher objeto
										2 - Mostrar todos os objetos
										""");
					opt = Console.readInt("Escolha uma opção: ");
					switch (opt) {
						case 1 -> {
							// ESCOLHER OBJETO //
							objeto = new Objeto();
							objeto.setTipo(Console.readString("Informe o tipo de armazenamento: "));
							objeto = Persistencia.buscarObjeto(objeto);
							if (objeto != null) {
								System.out.printf("""
												Tipo: %s
												Total: %s GB
												Disponivel: %s GB
												Ocupado: %s GB
												%n""", objeto.getTipo(), df.format(objeto.getcTotal()), df.format(objeto.getcDisponivel()), df.format(objeto.getcOcupada()));

							} else {
								System.out.println("Objeto não encontrado.");
							}
						}
						case 2 -> {
							// MOSTRAR TODOS OS OBJETOS //
							if (Persistencia.listaObjetos.contains(objeto)) {
								Persistencia.mostrarObjetos();
							} else {
								System.out.println("Nenhum objeto encontrado.");
							}
						}
						default -> System.out.println("Opção inválida.");
					}
				}
				case 5 -> System.out.println("Saindo...");
				default -> System.out.println("Opção inválida.");
			}
		} while (opt != 5);
	}

	// menu 1
	static void printMenu() {
		System.out.println("""
							- ARMAZENADOR DE DADOS -
							1 - Armazenar
							2 - Gravar dados
							3 - Apagar dados
							4 - Verificar espaço disponível
							5 - Sair
							""");
	}
}
