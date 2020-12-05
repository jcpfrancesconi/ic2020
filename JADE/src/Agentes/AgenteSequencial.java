package Agentes;

import Comportamentos.ComportamentoCiclico;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class AgenteSequencial extends Agent {
    protected void setup() {
        System.out.println("Olá! Meu nome é " + getLocalName());
        System.out.println("Vou executar três comportamentos");

        SequentialBehaviour comportamento = new SequentialBehaviour(this){
            public int onEnd(){
                myAgent.doDelete();
                return 1;
            }
        };

        comportamento.addSubBehaviour(new WakerBehaviour(this,50) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        });
        addBehaviour(new ComportamentoCiclico(this));
    }
}