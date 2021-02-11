package Agentes;

import Classes.Musico;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteContador extends Agent {

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Agente Contador inicializado.\n " + "Aguardando informações...");

        addBehaviour(new CyclicBehaviour() {
            Musico[] musicos = new Musico[5];
            int cont = 0;

            @Override
            public void action() {
                ACLMessage msg = receive();
                if(msg!=null){
                    try{
                        musicos[cont] = (Musico) msg.getContentObject();
                        musicos[cont].Imprimir();
                        cont = cont + 1;
                    }catch (Exception e){

                    }
                }else{
                    block();
                }
            }
        });
    }
}
