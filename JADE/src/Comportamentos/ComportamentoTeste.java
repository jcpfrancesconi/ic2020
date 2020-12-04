package Comportamentos;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class ComportamentoTeste extends Behaviour {
    int i = 0;

    public ComportamentoTeste(Agent a){
        super(a);
    }

    public void action(){
        System.out.println("Testando : "+i);
        i++;
    }

    public boolean done() {
        return i>=5;
    }
}
