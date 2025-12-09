package org.example;

public class ClaseNotify implements Runnable{
    static volatile boolean m1run = false; // STATIC VOLATILE SINO... HAY RACISMO
    public synchronized void m1(){ //BIEN SINCRONIZADOS
        for(int i=0; i<10; i++){
            System.out.println("m1 Ejecucion: " + i);
            if(i == 5){
                try{
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void m2(){ //MAS SINCRONIZACION
        for(int i=10; i<20; i++){
            System.out.println("m2 ejecucion: " + i);
        }
        this.notifyAll();
    }
    @Override
    public synchronized void run(){
            if (m1run == false) {
                System.out.println("metodo 1 no ejecutandose");
                m1run = true;
                m1();
            } else {
                m2();
            }
    }
}
