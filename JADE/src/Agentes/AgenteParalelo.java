package Agentes;

import jade.core.Agent;
import jade.core.behaviours.*;

import static jade.core.behaviours.ParallelBehaviour.WHEN_ALL;
import static jade.core.behaviours.ParallelBehaviour.WHEN_ANY;

public class AgenteParalelo extends Agent {
    protected void setup() {
        System.out.println("Olá! Meu nome é " + getLocalName());
        System.out.println("Vou executar três comportamentos concorrentemente");

        ParallelBehaviour comportamento = new ParallelBehaviour(this, 2){
            public int onEnd(){
                System.out.println("Comportamento Composto Finalizado com Sucesso!");
                return 0;
            }
        };
        addBehaviour(comportamento);

        comportamento.addSubBehaviour(new SimpleBehaviour(this) {
            int qtd = 1;
            @Override
            public void action() {
                System.out.println("Comportamento 1: Executando pela " + qtd + " vez");
                qtd++;
            }

            @Override
            public boolean done() {
                if (qtd==4){
                    System.out.println("Comportamento 1 - Finalizado");
                    return true;
                }else{
                    return false;
                }
            }
        });
        comportamento.addSubBehaviour(new SimpleBehaviour(this) {
            int qtd = 1;
            @Override
            public void action() {
                System.out.println("Comportamento 2: Executando pela " + qtd + " vez");
                qtd++;
            }

            @Override
            public boolean done() {
                if(qtd==8){
                    System.out.println("Comportamento 2 - Finalizado");
                    return true;
                }else {
                    return false;
                }
            }
        });
        comportamento.addSubBehaviour(new SimpleBehaviour(this) {
            int qtd = 1;
            @Override
            public void action() {
                System.out.println("Comportamento 3: Executando pela " + qtd + " vez");
                qtd++;
            }

            @Override
            public boolean done() {
                if(qtd==10){
                    System.out.println("Comportamento 3 - Finalizado");
                    return true;
                }else {
                    return false;
                }
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("Fui finalizado com sucesso");
    }
}