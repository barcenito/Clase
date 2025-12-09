package org.example;

public class SistemaRiego implements Runnable{
    private String zona;
    private int duracionRiego;
    private Thread dependencia;
    public SistemaRiego(){
        this.zona = "zonaGeneral";
        this.duracionRiego = 5;
        this.dependencia = null;
    }
    public SistemaRiego(String s, int t){
        this.zona = s;
        this.duracionRiego = t;
        this.dependencia = null;
    }
    public void regar(){
            try {
                if(this.dependencia != null) {
                    this.dependencia.join();
                }
                System.out.println("se ha comenzado a regar en la zona " + this.zona);
                Thread.sleep(this.duracionRiego * 1000);
                System.out.println("se ha terminado de regar la zona " + this.zona);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    @Override
    public void run(){
        this.regar();
    }

    public void setDependencia(Thread dependencia) {
        this.dependencia = dependencia;
    }
}
