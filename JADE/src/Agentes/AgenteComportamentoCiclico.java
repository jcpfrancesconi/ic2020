package Agentes;

import Comportamentos.ComportamentoCiclico;
import Comportamentos.ComportamentoOneShot;
import jade.core.Agent;

public class AgenteComportamentoCiclico extends Agent {
    protected void setup() {
        addBehaviour(new ComportamentoCiclico(this));
    }
}