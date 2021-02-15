package Agentes.PaginasAmarelas;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.SubscriptionInitiator;

public class Solicitante2 extends Agent {

    @Override
    protected void setup() {
        super.setup();
        //Captura argumentos
        Object[] args = getArguments();
        if(args!=null && args.length>0){
            String argumento = (String) args[0];
            //Argumento é fogo
            if(argumento.equalsIgnoreCase("fogo")){
                ServiceDescription servico = new ServiceDescription();
                servico.setType("apaga fogo");
                pedeNotificacao(servico, "fogo");

            }else if(argumento.equalsIgnoreCase("ladrão")){
                ServiceDescription servico = new ServiceDescription();
                servico.setType("prende ladrão");
                pedeNotificacao(servico, "ladrão");

            }else if(argumento.equalsIgnoreCase("doente")){
                ServiceDescription servico = new ServiceDescription();
                servico.setType("salva vidas");
                pedeNotificacao(servico, "doente");
            }
            addBehaviour(new CyclicBehaviour() {
                MessageTemplate filtro = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
                MessageTemplate filtro2 = MessageTemplate.not(filtro);

                @Override
                public void action() {
                    ACLMessage msg = receive(filtro2);
                    if(msg !=null){
                        System.out.println(msg.getSender() + " : " + msg.getContent());
                    }else{
                        block();
                    }
                }
            });
        }
    }

    protected void pedeNotificacao(final ServiceDescription sd, final String Pedido){
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);

        ACLMessage msg = DFService.createSubscriptionMessage(this, getDefaultDF(), dfd, null);

        //Comportamento que espera notificação do DF
        addBehaviour(new SubscriptionInitiator(this, msg){

            @Override
            protected void handleInform(ACLMessage inform) {
                super.handleInform(inform);
                try{
                    //Retorna array de AIDs dos Agentes
                    DFAgentDescription[] dfds = DFService.decodeNotification(inform.getContent());
                    ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
                    mensagem.addReceiver(dfds[0].getName());
                    mensagem.setContent(Pedido);
                    myAgent.send(mensagem);
                }catch(FIPAException e){
                    e.printStackTrace();
                }
            }
        });




    }
}
