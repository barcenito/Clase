package org.example;

public class Respirador{
    private static volatile Boolean enUso = false;
    public synchronized void liberar(){
        while(this.enUso == false){
            try{
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        enUso = false;
        System.out.println("Respirador Libre");
        this.notifyAll();
    }
    public synchronized void usar(){
        enUso = true;
        System.out.println("Respirador Ocupado");
    }
}
