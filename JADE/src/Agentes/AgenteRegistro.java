package Agentes;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class AgenteRegistro extends Agent {
    @Override
    protected void setup() {
        super.setup();

        //Cria entrada no DF
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        //Cria um serviço
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Tipo");
        sd.setName("Serviço1");
        dfd.addServices(sd);

        //Cria outro serviço
        sd = new ServiceDescription();
        sd.setType("Tipo de serviço");
        sd.setName("Serviço2");
        dfd.addServices(sd);

        //Registrar o agente no DF
        try{
            DFService.register(this, dfd);
        }catch(FIPAException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        try{
            DFService.deregister(this);
        }catch(FIPAException e){
            e.printStackTrace();
        }
    }
}
