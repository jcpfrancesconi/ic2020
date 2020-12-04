package Agentes;

import jade.core.Agent;

import java.util.Iterator;

public class HelloWorldAgent extends Agent {
    protected void setup() {
        System.out.println("Hello World. Eu sou um agente");
        System.out.println("Todas as minhs informações: \n" + getAID());
        System.out.println("Meu nome local é "+ getLocalName());
        System.out.println("Meu nome global (GUID) é " + getAID().getName());
        System.out.println("Meus endereços são:");
        Iterator it = getAID().getAllAddresses();
        while(it.hasNext()){
            System.out.println("- "+it.next());
        }
    }
}

