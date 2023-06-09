package br.edu.up.negocio;

import br.edu.up.entidades.*;

public class Negocio {

	// GRAVAR DADOS //
	public static boolean gravarDados(Objeto objeto, String unidade, double quantidade) {
        double valor = conversor(unidade, quantidade);
        if (objeto.getcDisponivel() >= valor) {
            objeto.setcOcupada(objeto.getcOcupada() + valor);
            objeto.setcDisponivel(objeto.getcTotal() - objeto.getcOcupada());
            return true;
        }
        return false;
    }
	
	// EXCLUIR DADOS //
	public static boolean excluirDados(Objeto objeto, String unidade, double quantidade) {
        double valor = conversor(unidade, quantidade);
        if (valor <= objeto.getcOcupada()) {
            objeto.setcOcupada(objeto.getcOcupada() - valor);
            objeto.setcDisponivel(objeto.getcTotal() - objeto.getcOcupada());
            return true;
        }
        return false;
    }
	
	
	// CONVERSOR
	public static double conversor(String unidade, double quantidade) {
        return switch (unidade) {
            case "kbyte" -> quantidade / (1024 * 1024);
            case "mbyte" -> quantidade / 1024;
            case "gbyte" -> quantidade;
            default -> 0;
        };
	}
}
