package Agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgenteBombeiro extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                //Seleção de mensagem - criação do filtro
                MessageTemplate MT1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                MessageTemplate MT2 = MessageTemplate.MatchLanguage("Português");
                MessageTemplate MT3 = MessageTemplate.and(MT1, MT2);
                ACLMessage msg = myAgent.receive(MT3);
                //ACLMessage msg = myAgent.receive(); //Sem filtro
                if(msg!=null){
                    ACLMessage reply = msg.createReply();
                    String content = msg.getContent();
                    if(content.equalsIgnoreCase("Fogo")){
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent("Recebi seu aviso! Obrigado por auxiliar meu serviço");
                        myAgent.send(reply);
                        System.out.println("O agente " + msg.getSender().getName() + " avisou de um incêndio");
                        System.out.println("Vou ativar os procedimentos de combate ao incêndio!");
                    }
                }else{
                    block();
                }
            }
        });
    }

    }