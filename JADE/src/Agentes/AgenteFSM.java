package Agentes;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SimpleBehaviour;

public class AgenteFSM extends Agent {
    protected void setup() {
        FSMBehaviour compFSM = new FSMBehaviour(this){
            @Override
            public int onEnd() {
                System.out.println("Comportamento FSM finalizado com sucesso!");
                return 0;
            }
        };
        //registra primeiro comportamento - X
        compFSM.registerFirstState(new OneShotBehaviour(this) {
            int c = 0;
            @Override
            public void action() {
                System.out.println("Executando Comportamento X");
                c++;
            }

            @Override
            public int onEnd() {
                return (c>4?1:0);
            }
        }, "X");
        //registra outro estado - Z
        compFSM.registerState(new OneShotBehaviour(this) {
            @Override
            public void action() {
                System.out.println("Executando comportamento Z");
            }

            @Override
            public int onEnd() {
                return 2;
            }
        }, "Z");

        //registra o último estado - Y
        compFSM.registerLastState(new OneShotBehaviour(this) {
            @Override
            public void action() {
                System.out.println("Executando meu último comportamento");
            }
        }, "Y");

        //define as transições
        compFSM.registerTransition("X", "Z", 0);// X->Z, caso onEnd() do X retorne 0
        compFSM.registerTransition("X", "Y", 1);// X->Y, caso onEnd() do X retorne 1

        //define transição padrão (não importa tipo de retorno)
        //como a máquina é finita, temos que zerar os estados X e Z -> new String[]{"X", "Z"}
        compFSM.registerDefaultTransition("Z", "X", new String[]{"X", "Z"});//Esse programa funciona igual sem o 3º parâmetro
        //Em vez disso pode registrar transição de quando o estado Z retorna 2:
        //compFSM.registerTransition("Z", "X", 2);

        //aciona o comportamento
        addBehaviour(compFSM);


    }

}