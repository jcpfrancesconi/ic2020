package Agentes;

import jade.core.Agent;
import jade.core.AID;

public class CompradorDeLivros extends Agent {
    private String livros;

    protected void setup() {
        System.out.println("Olá, sou o agente " + getLocalName() + " e estou pronto para comprar!");
        Object[] args = getArguments();
        if (args !=null && args.length > 0){
            livros = (String) args[0];
            System.out.println("Pretendo comprar o livro " + livros);
        }else{
            System.out.println("Não tenho livros para comprar!");
            doDelete();
        }
    }

    protected void takeDown() {
        System.out.println("Agente comprador " + getAID().getName() + " está finalizado!");
    }
}
