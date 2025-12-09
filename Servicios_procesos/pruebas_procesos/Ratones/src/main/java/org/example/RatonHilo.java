package org.example;

public class RatonHilo extends Thread{
    private String nombre;
    private int tAlim;
    public RatonHilo(String s, int t){
        this.nombre = s;
        this.tAlim = t;
    }
    public void comer(){
        try{
            System.out.println("El raton " + this.nombre + "ha comenzado a alimentarse");
            Thread.sleep(this.tAlim * 1000);
            System.out.println("El raton " + this.nombre + "ha terminado de alimentarse");
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void run() {this.comer();}
}
