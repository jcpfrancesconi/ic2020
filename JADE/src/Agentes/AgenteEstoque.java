package Agentes;

import Classes.Musico;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class AgenteEstoque extends Agent {
    Musico[] mus = new Musico[5];

    @Override
    protected void setup() {
        super.setup();

        mus[0] = new Musico("Claudia Leite", 30, "Babado Novo");
        mus[1] = new Musico("Paula Toller", 45, "Kid Abelha");
        mus[2] = new Musico("Rogerio Flausino", 37, "Jota Quest");
        mus[3] = new Musico("Laura Pausini", 33, null);
        mus[4] = new Musico("Bono Vox", 47, "U2");

        addBehaviour(new SimpleBehaviour() {
            int cont = 0;
            @Override
            public void action() {
                try{
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("Contador", AID.ISLOCALNAME));
                    msg.setContentObject(mus[cont]);
                    myAgent.send(msg);
                    cont++;
                } catch (IOException e) {
                    System.out.println("Erro no envio de mensagem");
                }
            }

            @Override
            public boolean done() {
                if(cont>4){
                    myAgent.doDelete();
                    return true;
                }else {
                    return false;
                }
            }
        });

    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("Todas informações foram enviadas");
    }
}
