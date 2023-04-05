package br.edu.up.entidades;

public class Objeto {
	
	private String tipo;
	private double cTotal;
	private double cOcupada;
    private double cDisponivel;
    
    // CONSTRUTOR PADRÃO
    public Objeto() {
    	
    }
   
    //
    // GETS SETS
    //
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getcTotal() {
		return cTotal;
	}
	public void setcTotal(double cTotal) {
		this.cTotal = cTotal;
	}
	public double getcOcupada() {
		return cOcupada;
	}
	public void setcOcupada(double cOcupada) {
		this.cOcupada = cOcupada;
	}
	public double getcDisponivel() {
		return cDisponivel;
	}
	public void setcDisponivel(double cDisponivel) {
		this.cDisponivel = cDisponivel;
	}

}
