package org.example;

public class Hospital {
    public void main(){
        Medico med = new Medico("alfonso");
        Coordinador cor = new Coordinador("genmaro", med);
        med.start();
        cor.start();
    }
}
