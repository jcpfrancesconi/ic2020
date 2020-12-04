package Agentes;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class AgenteComportamentoWaker extends Agent {
    protected void setup() {
        System.out.println("Adicionando waker behavior");
        addBehaviour(new WakerBehaviour(this,1000) {
            @Override
            protected void onWake() {

            }
        });
    }
}