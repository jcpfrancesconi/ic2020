package Agentes.ExemploProtocolo;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class FIPARequestAlarmado extends Agent {
    @Override
    protected void setup() {
        super.setup();

        Object[] args = getArguments();
        if(args!=null && args.length>0){
            System.out.println("Solicitando ajuda a várias centrais de bombeiros...");
            //montando mensagem a ser enviada
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            for(int i = 0; i<args.length; i++){
                msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
            }
            msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
            msg.setContent("Fogo a 5 km");
            /* A classe Iniciador estende a classe AchieveREInitiator, ela atua como iniciador do protocolo.
            Seu construtor enviar automaticamente a mensagem que está no objeto msg */
             addBehaviour(new Iniciador(this, msg));
        }else{
            System.out.println("Especifique o nome de pelo menos uma central de bombeiros");
        }
    }

    class Iniciador extends AchieveREInitiator {
        //envia a mensagem request para os receptores que foram especificados no objeto msg
        public Iniciador(Agent a, ACLMessage msg){
            super(a, msg); //parâmetros = agente que está enviando, mensagem a ser enviada
        }
    //Métodos a seguir tratam a resposta do participante
        //Se o participante concordar, enviando uma mensagem AGREE
        @Override
        protected void handleAgree(ACLMessage agree) {
            System.out.println("Central de bombeiros " + agree.getSender().getName()
            + "informa que saiu para apagar o fogo");
        }

        //Se o participante negar, enviando uma mensagem REFUSE

        @Override
        protected void handleRefuse(ACLMessage refuse) {
            System.out.println("Central de bombeiros " + refuse.getSender().getName()
            + " responde que o fogo está muito longe " + "e não pode apagá-lo");
        }

        //Se o participante não entendeu, enviando uma mensagem NOT-UNDERSTOOD

        @Override
        protected void handleNotUnderstood(ACLMessage notUnderstood) {
            System.out.println("Central de bombeiros " + notUnderstood.getSender().getName()
            + "por algum motivo não entendeu a solicitação");
        }

        //Se houve uma falha na execução do pedido

        @Override
        protected void handleFailure(ACLMessage failure) {
            //verifica se foi um erro nas paginas brancas
            if(failure.getSender().equals(getAMS())){
                System.out.println("Alguma das centrais de bombeiro não existe");
            }
            // O conteúdo de uma mensagem desse protocolo é automaticamente colocado entre parenteses.
            // Então podemos usar o método substring() para ler
            else{
                System.out.println("Falha na central de bombeiros " + failure.getSender().getName()
                + ": " + failure.getContent().substring(1, failure.getContent().length() - 1));
            }
        }

        //Ao finalizar o protocolo, o participante envia uma mensagem inform

        @Override
        protected void handleInform(ACLMessage inform) {
            System.out.println("Central de bombeiros" + inform.getSender().getName() + " informa que apagou o fogo");
        }
    }

}
