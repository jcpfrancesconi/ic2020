package Agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class AgenteAlarmado extends Agent {
    protected void setup() {
        addBehaviour(new OneShotBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID("Bombeiro", AID.ISLOCALNAME));
                msg.setLanguage("Português");
                msg.setOntology("Emergência");
                msg.setContent("Fogo");
                myAgent.send(msg);

            }
        });
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive();
                if(msg!=null){
                    String content = msg.getContent();
                    System.out.println("--> " + msg.getSender().getName() + ": " + content);
                }else{
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