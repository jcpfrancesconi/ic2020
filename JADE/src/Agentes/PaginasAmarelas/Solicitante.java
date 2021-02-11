package Agentes.PaginasAmarelas;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Solicitante extends Agent {

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
                busca(servico, "fogo");

            }else if(argumento.equalsIgnoreCase("ladrão")){
                ServiceDescription servico = new ServiceDescription();
                servico.setType("prende ladrão");
                busca(servico, "ladrão");

            }else if(argumento.equalsIgnoreCase("doente")){
                ServiceDescription servico = new ServiceDescription();
                servico.setType("salva vidas");
                busca(servico, "doente");
            }
            addBehaviour(new CyclicBehaviour() {
                @Override
                public void action() {
                    ACLMessage msg = receive();
                    if(msg !=null){
                        System.out.println(msg.getSender() + " : " + msg.getContent());
                    }else{
                        block();
                    }
                }
            });
        }
    }

protected void busca(final ServiceDescription sd, final String Pedido){

        //A cada minuto busca agentes que fornecem o serviço
        addBehaviour(new TickerBehaviour(this, 6000) {
            @Override
            protected void onTick() {
                DFAgentDescription dfd = new DFAgentDescription();
                dfd.addServices(sd);

                try{
                    DFAgentDescription[] resultado = DFService.search(myAgent, dfd);
                    if(resultado.length!=0){
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(resultado[0].getName());
                        msg.setContent(Pedido);
                        myAgent.send(msg);
                        stop();//Finaliza comportamento
                    }
                }
                catch(FIPAException e){
                    e.printStackTrace();
                }
            }


        });



}
}
