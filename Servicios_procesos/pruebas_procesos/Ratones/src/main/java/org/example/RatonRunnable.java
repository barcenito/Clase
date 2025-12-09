package org.example;

public class RatonRunnable implements Runnable{
    private String nombre;
    private int tAlim;

    public RatonRunnable(String s, int t){
        this.nombre = s;
        this.tAlim = t;
    }
    public void comer(){
        try{
            System.out.println("alcachofo -> " + this.nombre + " empiza movida");
            Thread.sleep(this.tAlim * 1000);
            System.out.println("alcachofo -> " + this.nombre + " termina movida");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        this.comer();
    }
}
