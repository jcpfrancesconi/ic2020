package Agentes;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class AgenteComportamentoTicker extends Agent {
    protected void setup() {
        System.out.println("Adicionando ticker behavior");
        addBehaviour(new TickerBehaviour(this,1000) {
            @Override
            protected void onTick() {
                if(getTickCount()>5){
                    stop();
                }else{
                    System.out.println("Estou realizando meu " + getTickCount() + " tick");
                }
                
            }
        });
    }
}