package br.edu.up.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.edu.up.entidades.*;

public class Persistencia {

	// ARRAY
	public static List<Objeto> listaObjetos = new ArrayList<>();

	
	
	// BUSCAR OBJETO
	public static Objeto buscarObjeto(Objeto objeto) {
		for (Objeto obj : listaObjetos) {
			if (obj.getTipo().equals(objeto.getTipo()) && obj.getcTotal() == objeto.getcTotal()) {
				return obj;
			} else if (obj.getTipo().equals(objeto.getTipo())) {
				return obj;
			}
		}
		return null;

	}
	
	// MOSTRAR OBJETOS //
	public static void mostrarObjetos() {
		for (Objeto obj : listaObjetos) {
			System.out.println("--------------------");
			System.out.println("Objeto: " + obj.getTipo());
			System.out.println("Capacidade Total: " + obj.getcTotal() + " GB ou 100%");
			System.out.println("Capacidade Disponivel: " + obj.getcDisponivel() + " GB ou " + Math.round((obj.getcDisponivel() * 100) / obj.getcTotal()) + "%");
			System.out.println("Capacidade Ocupada: " + obj.getcOcupada() + " GB ou " + Math.round((obj.getcOcupada() * 100) / obj.getcTotal()) + "%");
			System.out.println("--------------------");
		}
	}

	// incluir a folha no array
	public static void incluirObjeto(Objeto obj) {
		obj.setcDisponivel(obj.getcTotal());
		listaObjetos.add(obj);
	}
}
