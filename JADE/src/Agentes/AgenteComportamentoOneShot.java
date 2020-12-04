package Agentes;

import Comportamentos.ComportamentoOneShot;
import Comportamentos.ImprimeFrase;
import jade.core.Agent;

public class AgenteComportamentoOneShot extends Agent {
    protected void setup() {
        addBehaviour(new ComportamentoOneShot(this));
    }
}