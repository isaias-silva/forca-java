/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.forcapoo;

import java.util.Scanner;

/**
 * Esta classe NÃO DEVE SER MODIFICADA. Seu jogo deve ser criado a partir de uma
 * nova classe que é FILHA desta.
 * @author Professor Gabriel de Carvalho
 */
public abstract class Game {
    
    abstract public void criaNovo();
    abstract public void executa();
    abstract public void carrega();
    public void sai(){
        System.out.println("Obrigado por jogar!");
        new Scanner(System.in).next();
        System.exit(0);
    }
}
