package logica;

import interfaces.MenuPrincipal;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Este es la clase FuncionesJuego donde se van a crear y llamar las funciones necesarias para que funcione el juego
 * @author Kendall Rodríguez Camacho 2022049438 y Jocsan Adriel Perez Coto 2022437948 
 */
public class FuncionesJuego 
{
    
    private Partida partidaActual;
    
    public Cuadro listaCuadros[][] = new Cuadro[5][5];
    
    public String dificultad, tiempo, posicion;
    
    public String horas, min, sec;
    
    
    /**
     * Funcion para obtener la lista de cuadros
     * @return lista de cuadros
     */
    public Cuadro [][] getListaCuadros(){
        
        return listaCuadros;
    }
    
    /**
     * Metodo constructor de FuncionesJuego
     */
    public FuncionesJuego()
    {
        
        dificultad = "";
        tiempo = "";
        posicion = "";   
        
        horas = "";
        min = "";
        sec = "";
        
    }
    
    /**
     * Funcion para agregar una partida
     */
    public void agregarPartida(){
        
        partidaActual = MenuPrincipal.getPartidaActual();
        int constante;
        String desigualdad1;
        String desigualdad2;
        
        for(int filas = 0; filas < 5; filas++)
        { 
           
            for(int columnas = 0; columnas < 5; columnas++)
            { 
                
                // Se obtiene la constante de la partida, si no es nula se agrega, si no se agrega 0
                if(partidaActual.getConstantes()[filas][columnas] != null)
                constante = Integer.parseInt(partidaActual.getConstantes()[filas][columnas]);
                else constante = 0;
                // Se agrega la desigualdad1, que son los < y >
                desigualdad1 = partidaActual.getDesigualdades()[filas][columnas][0];
                if(desigualdad1 != null && desigualdad1.equals("a")) desigualdad1 = ">";
                else if(desigualdad1 != null) desigualdad1 = "<";
                // Se agrega la desigualdad1, que son los v y ^
                desigualdad2 = partidaActual.getDesigualdades()[filas][columnas][1];
                if(desigualdad2 != null && desigualdad2.equals("y")) desigualdad2 = "v";
                else if(desigualdad2 != null) desigualdad2 = "^";
                // Se crea un cuadro
                Cuadro cuadro = new Cuadro(constante, desigualdad1, desigualdad2, filas, columnas);
                // Se agrega el cuadro a la lista de cuadros
               
                listaCuadros[filas][columnas] = cuadro;
                
          
            } 
       
        }
    }
    
    /**
     * Funcion para insertar un numero dentro de un cuadro que esta en la lista de cuadros
     * @param num (int)
     * @param fil (int)
     * @param colum (int)
    */
    public void insertarNum(int num, int fil, int colum)
    {        
        listaCuadros[fil][colum].setNum(num);
                    
//            for(int filas = 0; filas < 5; filas++)
//            { 
//                for(int columnas = 0; columnas < 5; columnas++)
//                { 
//                    System.out.print(listaCuadros[filas][columnas]);  // Imprime elemento 
//                } 
//            System.out.println();  // Imprime salto de línea 
//            }          
    }
    
    /**
     * Funcion para verificar si el numero fue escrito en la fila
     * @param num (int)
     * @param colum (int)
     * @return true (No se ha escrito)/ false (fue escrito)
     */
    public boolean verificaColumna(int num, int colum)
    {
        for(int filas = 0; filas < 5; filas++)
        { 
            if(listaCuadros[filas][colum].getNum() == num)//El numero ya fue escrito en esa columna
            {   
                return false;
            }               
        }
        return true;//el numero se encuentra una vez en la columna
    }
    
    /**
     * Funcion para verificar si el numero fue escrito en la columna
     * @param num (int)
     * @param fil (int)
     * @return true (No se ha escrito)/ false (fue escrito)
     */
    public boolean verificaFila(int num, int fil)
    {
        for(int columnas = 0; columnas < 5; columnas++)
        { 
            if(listaCuadros[fil][columnas].getNum() == num)//El numero ya fue escrito en esa fila
            {    
                return false;
            }               
        }
        return true;//el numero se encuentra una vez en la fila
    }
    
    
    /**
     * Método que valida la desigualdad de la izquierda y derecha   
     * @param num (int) numero a verificar
     * @param fila (int)
     * @param columna (int)
     * @param simbolo (int) simbolo para verirficar
     * @return true(no hay desigualdad)/ false (si hay desigualdad)
     */
    public boolean verificaDesigualdadX(int num, int fila, int columna, String simbolo)
    {    
        switch(simbolo)
        {           
            case ">":
                 
               //System.out.println("ACÁ ENTRA Y EL NUMERO ES " + listaCuadros[fila][columna + 1].getNum());
                // Valido el cuadro de la derecha
                if(listaCuadros[fila][columna + 1].getNum() != 0 && 
                        listaCuadros[fila][columna + 1].getNum() >= num){
                    
                 
                    return false; 
                    
                }
                
                break;
               
            case "<":
                 // Valido el cuadro de la izquierda
                if(listaCuadros[fila][columna + 1].getNum() != 0 && 
                        listaCuadros[fila][columna + 1].getNum() <= num){
                    
                    return false;
                }
        }
        return true;
    }
    
    /**
     *  Método que valida la desigualdad de arriba y abajo   
     * @param num (int) numero a verificar
     * @param fila (int)
     * @param columna (int)
     * @param simbolo (int) simbolo para verirficar
     * @return true(no hay desigualdad)/ false (si hay desigualdad) 
     */
    public boolean verificaDesigualdadY(int num, int fila, int columna, String simbolo)
    {
       
        //System.out.println("AQUÍ SE FIJA EN EL SIMBOLO " + simbolo);
        switch(simbolo){
            
            case "v":
                
                
                if(listaCuadros[fila + 1][columna].getNum() != 0 && 
                        listaCuadros[fila + 1][columna].getNum() >= num){
                    
                    return false;
                }
                break;
            
            case "^":
                
                if(listaCuadros[fila + 1][columna].getNum() != 0 && 
                        listaCuadros[fila + 1][columna].getNum() <= num){
                    
                    return false;
                }
        }
        
        return true;
    }
    

    /**
     * Funcion que Llama a los metodos de verificarX y verificarY, Recibe un indicador para ver si valida el símbolo 1 o el simbolo 2
     * 0 para el simbolo 1 y 1 para el simbolo 2
     * @param num (int) numero a verificar
     * @param fila (int)
     * @param columna (int)
     * @param indicador true/false
     * @return true(no hay desigualdad)/ false (si hay desigualdad) 
     */
    public boolean verificaDesigualdad2(int num, int fila, int columna, boolean indicador)
    {
        
        String simbolo;
                
        // Si el cuadro tiene un simbolo
        
        if(indicador) simbolo = listaCuadros[fila][columna].getSimbolo2();
        else simbolo = listaCuadros[fila][columna].getSimbolo1();
        
        if(simbolo != null){
            
            // Valida izquierda o derecha
            
            if(simbolo.equals(">") || simbolo.equals("<")){
                
                if(verificaDesigualdadX(num, fila, columna, simbolo) == false) return false;
                
            }
            
            // Valida arriba o abajo
            
            else{
              
                if(verificaDesigualdadY(num, fila, columna, simbolo) == false) return false;
            
            }
            
        }
        
        // Valida el cuadro de la izquierda con el actual, manda el numero al cuadro actual
        
        if(columna > 0) simbolo = listaCuadros[fila][columna - 1].getSimbolo1();
        else simbolo = null;
         
        if(simbolo != null && columna - 1 >= 0){
            
            listaCuadros[fila][columna].setNum(num);
           
            num = listaCuadros[fila][columna - 1].getNum();
            
            if(num != 0 && verificaDesigualdadX(num, fila, columna - 1, simbolo) == false){
                
                listaCuadros[fila][columna].setNum(0);
                return false;
            }
         
        }
        
        // Valida el cuadro de arriba con el actual
        
        if(fila > 0) simbolo = listaCuadros[fila - 1][columna].getSimbolo2();
        else simbolo = null;
        
        if(simbolo != null && fila - 1 >= 0){
            
            listaCuadros[fila][columna].setNum(num);
            
            num = listaCuadros[fila - 1][columna].getNum();
            
            if(num != 0 && verificaDesigualdadY(num, fila - 1, columna, simbolo) == false){
                
                listaCuadros[fila][columna].setNum(num);
                return false;
            }
        }
        
        return true;
    }
  
    //----Funciones para la configuracion del juego

    /**
     * Funcion para establecer el tipo de dificultad 
     * @param pDificultad (string)
     */
    public void setDificultad(String pDificultad)
    {
        dificultad = pDificultad;
    }
    
    /**
     * Funcion para establecer el tipo de tiempo
     * @param pTiempo (string)
     */
    public void setTiempo(String pTiempo)
    {
        tiempo = pTiempo;
    }
    
    /**
     * Funcio para establecer el tipo de posicion
     * @param pPosicion (string)
     */
    public void setPosicion(String pPosicion)
    {
        posicion = pPosicion;
    }
    
    /**
     * Funcion para obtener el tipo de dificultad
     * @return (string)
     */
    public String getDificultad()
    {
        return dificultad;
    }
    
    /**
     * Funcion para obtener el tipo de Tiempo
     * @return (string)
     */
    public String getTiempo()
    {
        return tiempo;
    }
    
    /**
     * Funcion para obtener el tipo de Posicion
     * @return (string)
     */
    public String getPosicion()
    {
        return posicion;
    }
    
    /**
     * Funcion para establecer las horas
     * @param pHoras (string)
     */
    public void setHoras(String pHoras)
    {
        horas = pHoras;
    }
    
    /**
     * Funcion para establecer los minutos
     * @param pMin (string)
     */
    public void setMin(String pMin)
    {
        min = pMin;
    }
    
    /**
     * Funcion para establecer los segundos
     * @param pSec (string)
     */
    public void setSec(String pSec)
    {
        sec = pSec;
    }
    
    /**
     * Funcion para obtener las horas
     * @return (string)
     */
    public String getHoras()
    {
        return horas;
    }
    
    /**
     * Funcion para obtener los minutos
     * @return (string)
     */
    public String getMin()
    {
        return min;
    }
    
    /**
     * Funcion para obtener los segundos
     * @return (string)
     */
    public String getSec()
    {
        return sec;
    }
    
    
    //----Funciones para la creacion de archivos y encontrar los mejores 10 tiempos de cada dificultad
    
    /**
     * Funcion para crear un archivo con el nombre que se elija predeterminadamente antes
     * @param archivo (File)
     */
    public void crearArchivoTop10(File archivo)
    {     
        try{     
                if(archivo.exists() && !archivo.isDirectory())// si existe entonces no hace nada     
                {
                    return; 
                }
                if (archivo.createNewFile())//crea un archivo
                {
                    System.out.println("El fichero se ha creado correctamente");
                }               
                else//en caso de error
                {
                    System.out.println("No ha podido ser creado el fichero");
                }
                
            }catch (IOException e) 
            {          //en caso de error
                  
            }
    }
    
    /**
     * Funcion para guardar en un archivo existente los datos obtenidos del juego del usuario
     * @param archivo (File) nombre del archivo
     * @param nombre (String) nombre del usuario
     * @param TiempoTotal (String) tiempo total obtenido del usuario
     * @param horas (int) horas del usuario
     * @param min (int) minutos del usuario
     * @param sec (int) segundos del usuario
     */
    public void guardarArchivoTop10(File archivo, String nombre, String TiempoTotal, int horas, int min, int sec)//Este guardar archivo es para las partidas
    {
    try{                  
            FileWriter escribir = new FileWriter(archivo, true);//se utiliza el import de FileWriter para escribir en el archivo, se le manda el archivo
           
            escribir.write(nombre);
            escribir.write("\n");
            escribir.write(TiempoTotal);
            escribir.write("\n");
            escribir.write(String.valueOf(horas));
            escribir.write("\n");
            escribir.write(String.valueOf(min));
            escribir.write("\n");
            escribir.write(String.valueOf(sec));
            escribir.write("\n");
            escribir.close();//se cierra
            
            System.out.println("Se ha escrito en el archivo");
        }
        catch(IOException e){
            
            //imprime el error si no existe un archivo donde escribir
            System.out.println("NO SE PUEDE ESCRIBIR EN EL ARCHIVO");
        }    
    }
    
    /**
     * Funcion para crear una lista con la informacion de un archivo
     * @param archivo1 (File) archivo a buscar
     * @param lista (ArrayList) lista vacia para modificar
     * @return lista(ArrayLista) lista modificada
     */
    public ArrayList<String> leerArchivoTop10(File archivo1, ArrayList<String> lista)
    {
        try
        {                                  
            if(archivo1.exists() && !archivo1.isDirectory())//busca que exista
            {      
                System.out.println("EL ARCHIVO SE ABRIÓ CORRECTAMENTE");
                                           
                Scanner obj = new Scanner(archivo1);//si existe, este objeto lee todo lo que tenga
 
                while (obj.hasNextLine())//lo lee por lineas
                {                   
                    lista.add(obj.nextLine());
                } 
            }
                
        }catch(Exception e)
        {
            //imprime el error si no existe un archivo donde escribir
            System.out.println("NO SE PUEDE ESCRIBIR EN EL ARCHIVO...");
        }           
        return lista;//devuelve la lista
    }
    
    /**
     * Funcion para crear una lista con los mejores tiempos de otra lista
     * @param nuevaLista (ArrayList) lista vacia para modificar
     * @param lista (ArrayList) lista con la informacion
     * @return nuevaLista (ArrayList) lista modificada
     */
    public  ArrayList<Integer> obtenerMejorTop10(ArrayList<Integer> nuevaLista, ArrayList<String> lista)
    {       
        int i = 0;
        for (String elemento: lista)
        {       
            if(i == 1)
            {
                nuevaLista.add(Integer.parseInt(elemento)); //tiempo Total
            }
            if(i == 4)//para hacer el ciclo
            {
                i = 0;
            }
            else
            {
                i++;
            }
        }
              
        Collections.sort(nuevaLista);//ordena los elementos de menor a mayor
               
        return nuevaLista;
    }
}
