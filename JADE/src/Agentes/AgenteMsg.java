package Agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteMsg extends Agent {
    protected void setup() {

        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        msg.setOntology("PC-Ontology");
        msg.setLanguage("Jess");
        msg.addReceiver(new AID("pcseller", AID.ISLOCALNAME));
        msg.setContent("(pc-offer (mb  256) (processor celeron) (price ?p))");
        send(msg);
    }

    @Override
    protected void takeDown() {
        System.out.println("Fui finalizado com sucesso");
    }
}