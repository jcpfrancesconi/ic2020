package Agentes.PaginasAmarelas;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Medico extends Agent {

    @Override
    protected void setup() {
        super.setup();

        ServiceDescription servico = new ServiceDescription();
        servico.setType("salva vidas");
        servico.setName(this.getLocalName());
        registraServico(servico);
        RecebeMensagens("doente", "Vou salvar o doente");
    }

    protected void registraServico(ServiceDescription sd){
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);
        try{
            DFService.register(this, dfd);
        }catch(FIPAException e){
            e.printStackTrace();
        }
    }

    protected void RecebeMensagens(final String mensagem, final String resp){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if(msg != null){
                    if(msg.getContent().equalsIgnoreCase(mensagem)){
                        ACLMessage reply = msg.createReply();
                        reply.setContent(resp);
                        myAgent.send(reply);
                    }
                }else{
                    block();
                }
            }
        });
    }
}
