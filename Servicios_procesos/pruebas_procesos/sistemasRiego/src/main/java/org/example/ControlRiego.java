package org.example;

public class ControlRiego {
    public void main(){
        SistemaRiego s1 = new SistemaRiego("zona-z1",3);
        SistemaRiego s2 = new SistemaRiego("zona-z2",4);
        SistemaRiego s3 = new SistemaRiego("zona-z3",2);
        Thread h1 = new Thread(s1);
        Thread h2 = new Thread(s2);
        s3.setDependencia(h2);
        Thread h3 = new Thread(s3);
        h1.start();
        h2.start();
        h3.start();
    }
}
