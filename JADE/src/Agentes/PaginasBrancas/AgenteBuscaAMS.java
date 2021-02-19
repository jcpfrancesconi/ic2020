package Agentes.PaginasBrancas;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAException;

public class AgenteBuscaAMS extends Agent {

    @Override
    protected void setup() {
        super.setup();

        try{
            //Buscar quais agentes estão na plataforma
            AMSAgentDescription[] agentes = null;
            //Objeto SearchConstraints define condições de pesquisa
            SearchConstraints c = new SearchConstraints();
            //Número de resultados desejados. -1 = todos
            c.setMaxResults(new Long(-1));
            //busca agentes
            agentes = AMSService.search(this, new AMSAgentDescription(), c);
            //Própria AID desse agente
            AID myAID = getAID();
            for(int i = 0; i<agentes.length; i++){
                AID agenteID = agentes[i].getName();
                //Imprime todos agentes
                System.out.println((agenteID.equals(myAID)? "***" : "  ")
                + i + ": " + agenteID.getName());
            }


        }catch(FIPAException e){
            e.printStackTrace();
        }
        //Finaliza agente e aplicação
        doDelete();
        System.exit(0);
    }
}
