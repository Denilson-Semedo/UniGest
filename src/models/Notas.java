package models;

public class Notas {
    private String nome;
    private int numero;
    private String curso;
    private int ano;
    private double nota;

    public Notas(String nome, int numero, String curso, int ano, double nota) {
        this.nome = nome;
        this.numero = numero;
        this.curso = curso;
        this.ano = ano;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

}
