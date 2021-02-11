package Agentes;

import Comportamentos.ComportamentoTeste;
import Comportamentos.ImprimeFrase;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import java.util.Iterator;

public class AgenteImpressor extends Agent {
    protected void setup() {
        System.out.println("Olá! Eu sou um agente impressor");
        System.out.println("# Vou executar meu comportamento");
        Behaviour b = new ImprimeFrase(this, 5000);
        addBehaviour(b);
    }

}