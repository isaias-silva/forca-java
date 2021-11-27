/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.forcapoo;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Util {
    public static void clearScreen() throws IOException, InterruptedException {
            //Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }
    
    //metodos estaticos para auxiliar a forca:
    
     public static String draw(int c, String p,List certos,boolean encontrado,String dic,int pontos){
//forca que vai receber as partes do boneco
String man="          |   \n" +
"          |   \n" ;

//partes do boneco
String[] partes={"          o   \n","         /","|","\\\n", "          /","\\\n","           /","          /"};

if(c<8){
    //se chances sao menores que o valor inicial
    int dif=8-c;//diferença
    //soma as partes por dif vezes. 
    for(int i=0; i<dif;i++){
    man+=partes[i];
    }
}

int t=p.length();
String tb;
String[] space=new String[t];
for(int i=0;i < t;i++){
    //a Array space tem o tamanho da palavra e traços no lugar da letra;
    space[i]="_";
    //se a letra foi encontrada será colocada no traço correspondente a sua posição
    if (encontrado==true){
    for(int y=0; y< certos.size(); y++){
       String index=certos.get(y).toString();
    //a letra será preenchida em todas posições que ocupa na palavra;
       for(int x=0; x< t; x++){
           if(p.charAt(x)==index.charAt(0)){
           space[x]=index;
           }
       }
       
        }
    }
}
   
tb=Arrays.toString(space);
//se não existir mais traços na versão string de tb,
//a palavra está completa, e o jogador venceu.
if(tb.contains("_")==false){
 //retorna Ascii art da vitoria
Forca.vitory=true;
 return "\n\n\n     ganhamo   \n" +
"         0 /            \n" +
"       \\/|\\__\n" +
"         |           \n" +
"        / \\           \n" +
"   ____/___\\___ " + tb +"\n\n VOCÊ GANHOU!!! \n"
        +"a palavra é "+p +"\n";
}
if(c==0){
 //se as chances acabaram, retorna GAME OVER;
    return 
"       GAME OVER\n"
+ "             |   \n" +
"             | estoy muerto  \n" +                
"             o/   \n" +
"            /|\\\n" +
"             /\\\n" +
"            /  \\\n";

}else{
     //Se ainda tem chances retorna ao jogo;
     return"vitorias: "+pontos +"\n" +
             man+"\n"
             +t +" LETRAS\n"+ tb +"\n\n" 
             +"VOCÊ TEM "+c+" chances"
             +"\n dica: "+dic;

}

 }
     public static boolean check(String letter,String palavra_r){
      //retorna se a palavra contem a letra.
         return palavra_r.contains(letter);
 }
  public static boolean isnumber(String letra){
      //retorna se a letra é um numero
      return letra.matches("\\d");
  }
}
