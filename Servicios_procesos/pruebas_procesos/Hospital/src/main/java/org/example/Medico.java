package org.example;

public class Medico extends Thread{
    private String nombre;
    public Medico(String s){
        this.nombre = s;
    }
    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("Estoy atendendo");
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            System.out.println("me han llamado para una movda");
        }
    }
}
