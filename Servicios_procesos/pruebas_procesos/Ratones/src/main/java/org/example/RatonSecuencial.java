package org.example;

public class RatonSecuencial {
    private String nombre;
    private int tAlim;

    public RatonSecuencial(String n, int t){
        this.nombre = n;
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
}
