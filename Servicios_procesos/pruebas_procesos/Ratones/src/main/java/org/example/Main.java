package org.example;

import java.util.ArrayList;


public class Main {
    static void main() {
        ClaseNotify patata = new ClaseNotify();
        new Thread(patata).start();
        new Thread(patata).start();
        new Thread(patata).start();
        new Thread(patata).start();
    }
}
