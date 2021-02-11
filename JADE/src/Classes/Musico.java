package Classes;

import jade.util.leap.Serializable;

public class Musico implements Serializable {
    String nome;
    int idade;
    String banda;

    public Musico(String nome, int idade, String banda){
        this.nome = nome;
        this.idade = idade;
        this.banda = banda;
    }

    public void Imprimir(){
        System.out.println("--------------------");
        System.out.println("Nome...: " + nome);
        System.out.println("Idade...: " + idade);
        System.out.println("Banda...: " + banda);
        System.out.println("--------------------\n");
    }

}
