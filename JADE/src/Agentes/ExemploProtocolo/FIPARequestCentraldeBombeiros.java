package Agentes.ExemploProtocolo;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

import java.util.StringTokenizer;

public class FIPARequestCentraldeBombeiros extends Agent {
    public double DISTANCIA_MAX;

    @Override
    protected void setup() {
        DISTANCIA_MAX = (Math.random() * 10);
        System.out.println("Central " + getLocalName() + ": Aguardando alarmes...");
        //agente conversa sob o protocolo FIPA
        MessageTemplate protocolo = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        MessageTemplate performativa = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        MessageTemplate padrao = MessageTemplate.and(protocolo, performativa);

        addBehaviour(new Participante(this, padrao));
    }

    class Participante extends AchieveREResponder {
        public Participante(Agent a, MessageTemplate mt){
            //define agente e protocolo de comunicação
            super(a, mt);
        }

        /* Método que aguarda uma mensagem REQUEST, definida com o uso do objeto mt,
        utilizado no consturutor dessa classe
        O retorno desse método é uma mensagem que é enviada automaticamente para o iniciador
         */

        @Override
        protected ACLMessage prepareResponse(ACLMessage request) throws NotUnderstoodException, RefuseException {
            System.out.println("Central " + getLocalName() + ": Recebemos uma chamada de " + request.getSender().getName()
            + " dizendo que observou um incêndio");
            //A classe StringTokenizer permite que você separe ou encontre palavras (tokens em qualquer formato
            StringTokenizer st = new StringTokenizer(request.getContent());
            String conteudo = st.nextToken(); //pega primeiro token
            if(conteudo.equalsIgnoreCase("fogo")){//se for fogo
                st.nextToken();//pula o segundo
                int distancia = Integer.parseInt(st.nextToken());//captura DIST
                if(distancia<DISTANCIA_MAX){
                    System.out.println("Central " + getLocalName() + ": Saímos correndo!");
                    ACLMessage agree = request.createReply();
                    agree.setPerformative(ACLMessage.AGREE);
                    return agree; //envia mensagem AGREE
                }else{
                    //Fogo está longe. Envia mensagem Refuse com o motivo
                    System.out.println("Central " + getLocalName() +
                            ": Fogo está longe demais. Não podemos atender a solicitação.");
                    throw new RefuseException("Fogo está muito longe");
                }
            }else{
                throw new NotUnderstoodException("Central de Bombeiros não entendeu sua mensagem");
            }
        }

        //Prepara resultado final, caso tenha aceitado


        protected ACLMessage registerPrepareResultNotification(ACLMessage request, ACLMessage response)
        throws FailureException {
            if(Math.random()>0.2){
                System.out.println("Central " + getLocalName() + "" +
                        ": Voltamos de apagar o fogo.");
                ACLMessage inform = request.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                return inform;//envia mensagem INFORM
            }else{
                System.out.println("Central " + getLocalName() + ": Ficamos sem água");
                throw new FailureException("Ficamos sem água");
            }
        }
    }
}
