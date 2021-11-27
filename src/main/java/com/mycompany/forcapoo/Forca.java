/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.forcapoo;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isaias
 */
public abstract class Forca extends Game{
    //###########VARIAVEIS GLOBAIS#############
   
    //variavel booleana que controla se o jogador venceu
    public static boolean vitory;
    //variavel que pode ser 0 ou 1 para verificar no carregamento se o ultimo jogo foi perdido
    public static int loser=0;
    //Strings que serão a palavra selecionada e a dica dessa palavra
    public static String palavra; 
    public  static String dica; 
//chances que o jogoador tem
    public static int chances;
 //variavel que computa as vitorias
    public static int pontos;
    //arrayslist´s que guardam as letras certas e erradas
    private ArrayList<Object> letrasc;
     private ArrayList<Object> letraer;
    //numero aleatorio que será a palavra sorteada
     public static int pn;
    //numero de palavras
    private static int np=24;
    
    public void criaNovo(){
       
     //carregar palavras   
    String[] select=new String[np];
    String[] dicas=new String[np];
        try {
            String all = new Scanner(new File("data.txt"))
                    .useDelimiter("\\Z").next();
            select=all.split(";");
            String allt = new Scanner(new File("dicas.txt"))
                    .useDelimiter("\\Z").next();
            dicas=allt.split(";");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
        } 
   //sortear numero da palavra
    Random random = new Random();
pn = random. nextInt(Forca.np);

//definindo vars do novo jogo
    Forca.vitory=false;
    Forca.palavra=select[pn];
    Forca.chances=8;
    Forca.dica=dicas[pn];
    Forca.pontos=0;
   this.letrasc = new ArrayList<>();
   this.letraer= new ArrayList<>();
    };

    public void executa(){
            
//desenhar forca
        String dr=Util.draw(chances,palavra,null,false,dica,pontos);
        System.out.println(dr);
        
        //enquanto vitory for falso e as chances forem maiores que 0:
        while(vitory==false && chances>0){
         System.out.println("letra(apenas uma letra): ");   
         Scanner letra_entrada=new Scanner(System.in);
       String letra=letra_entrada.next();
       //se for digitado mais de uma letra:
       if(letra.length()>1){
           System.out.printf("apenas uma letra permitida! \n primeira letra digitada selecionada\n\n");
           letra=""+letra.charAt(0);
       }
       //se for LETRA MAIUSCULA:   
       if( letra==letra.toUpperCase()){
       letra=letra.toLowerCase();
       }
       //testa se a letra é um numero:
        boolean tester=Util.isnumber(letra);
       if(tester==true){
           System.out.println("digite apenas LETRAS!");
       }else{
          //testa se a letra esta correta:
           boolean respost=Util.check(letra,palavra);
        if(respost==false){
            if(this.letraer.contains(letra)){
                System.out.println("letra já digitada!");
            }else{
         System.out.println("letra incorreta!");
         chances-=1;
         this.letraer.add(letra);}
     String game_reso1=Util.draw(chances,palavra,letrasc,true,dica,pontos);
        System.out.println(game_reso1);
     }else{
            if(this.letrasc.contains(letra)){
              System.out.println("letra já digitada!");
            }else{
         System.out.println("LETRA "+letra +" CORRETA!");
         letrasc.add(letra);}
         String game_reso=Util.draw(chances,palavra, letrasc ,true,dica,pontos);
         System.out.println(game_reso);
     }
        }
        if(chances==0){
            Forca.loser=1;
       //salvar
        FileWriter arq;
        try {
            arq = new FileWriter("save.txt");
      PrintWriter gravarArq = new PrintWriter(arq);
            System.out.println(palavra);
      gravarArq.printf(pn+";"+pontos+";"+loser);
       arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
        } 
            

       //
            String[] args = null;
            try {
               Main.main(args);
           } catch (IOException ex) {
               Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        if(vitory==true){
            Forca.pontos++;
            System.out.println(palavra);
            
            //salvar
                   FileWriter arq;
        try {
            arq = new FileWriter("save.txt");
      PrintWriter gravarArq = new PrintWriter(arq);
      gravarArq.printf(pn+";"+pontos+";"+loser);
       arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
        } 
            //
        String[] args = null;
            try {
                Main.main(args);
            } catch (IOException ex) {
                Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
         
        }
    };
   public void carrega(){
      //carregar o arquivo salvo
       String[] load=new String[3];
       try {
            String all = new Scanner(new File("save.txt"))
                    .useDelimiter("\\Z").next();
               load=all.split(";");
        } catch (FileNotFoundException ex) {
            System.out.println("erro ao carregar arquivo!");
            Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
        }
   
Forca.vitory=false;
//checa se na ultima partida o jogador perdeu
    Forca.loser=Integer.parseInt(load[2]);
    Forca.chances=8;
    //carrega os pontos que jogador fez;
    Forca.pontos=Integer.parseInt(load[1]);
   //esvazia as arraylist
    this.letrasc = new ArrayList<>();
     this.letraer=new ArrayList<>();
 
     //carregar as palavras 
        String[] select=new String[Forca.np];
    String[] dicas=new String[Forca.np];
      
     try {
            String all = new Scanner(new File("data.txt"))
                    .useDelimiter("\\Z").next();
            select=all.split(";");
            String allt = new Scanner(new File("dicas.txt"))
                    .useDelimiter("\\Z").next();
            dicas=allt.split(";");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
        } 
   //se loser for 1, significa que jogador perdeu a ultima partida,
   //logo a palavra da ultima partida vai ser carregada
  if(Forca.loser==1){ 
  pn=Integer.parseInt(load[0]);
      Forca.palavra=select[pn];
     Forca.dica=dicas[pn];
     Forca.loser=0;
     
  }
  //se não, carrega uma palavra aleátoria
  else{
        Random random = new Random();
pn = random. nextInt(Forca.np);
//se a palavra aleatoria for a mesma que a anterior
if(pn==Integer.parseInt(load[0]) ){
if(pn>np && pn!=0)
    pn-=1;
}else{
pn+=1;
}
Forca.palavra=select[pn];
     Forca.dica=dicas[pn];
}
  
   };
}
