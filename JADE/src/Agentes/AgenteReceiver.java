package Agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteReceiver extends Agent {
    @Override
    protected void setup() {
        super.setup();
        CyclicBehaviour c1 = new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if(msg!=null){
                    System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent());
                }

                block();//Interrompe comportamento até chegar nova mensagem
            }
        };
    }


}
