package org.example;

public class Medico implements Runnable{
    private Respirador resp;
    private int action;
    public Medico(Respirador r){
        this.resp = r;
    }
    @Override
    public void run(){
        while (true) {
            try {
                if (this.action == 1) { // Medico B: USAR
                    this.resp.usar();
                } else if (this.action == 2) { // Medico A: LIBERAR
                    this.resp.liberar();
                }
                // Pausa opcional para que la consola se actualice
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void setAction(int action) {
        this.action = action;
    }
}
