package org.example;

public class Coordinador extends Thread{
    private String nombre;
    private Medico medico;
    public Coordinador(String s, Medico m){
        this.nombre = s;
        this.medico = m;
    }
    @Override
    public void run(){
        try{
            Thread.sleep(5000);
            medico.interrupt();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
