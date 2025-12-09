package org.example;

public class Urgencias {
    public void main(){
        Respirador r1 = new Respirador();
        Medico m1 = new Medico(r1);
        Medico m2 = new Medico(r1);
        m1.setAction(2);
        m2.setAction(1);
        Thread th1 = new Thread(m1);
        Thread th2 = new Thread(m2);
        th1.start();
        th2.start();
    }
}
