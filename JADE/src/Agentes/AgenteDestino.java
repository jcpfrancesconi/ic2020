package Agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteDestino extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if(msg!=null){
                    System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent());
                //Interrompe comportamento até chegar uma nova mensagem
                    block();
                }
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("Fui finalizado com sucesso");
    }
}