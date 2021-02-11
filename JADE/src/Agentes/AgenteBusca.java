package Agentes;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.util.leap.Iterator;

public class AgenteBusca extends Agent {
    @Override
    protected void setup() {
        super.setup();
        //Entrada no DF
        DFAgentDescription template = new DFAgentDescription();

        //Objeto com descrição do serviço
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Tipo");

        //Adiciona serviço na entrada
        template.addServices(sd);
        try{
            DFAgentDescription[] result = DFService.search(this, template);

            for(int i = 0; i<result.length; i++){
                String out = result[i].getName().getLocalName() + " provê ";

                Iterator iter = result[i].getAllServices();

                while(iter.hasNext()){
                    ServiceDescription SD = (ServiceDescription) iter.next();
                    out += " " + SD.getName();
                }
                System.out.println(out);
            }

        }catch(FIPAException e){
            e.printStackTrace();
        }


    }

}
