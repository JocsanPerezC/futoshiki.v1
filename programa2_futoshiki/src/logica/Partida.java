package logica;

public class Partida {
    
    private String constantes[][] = new String[5][5];
    private String desigualdades[][][] = new String[5][5][2];
    private String nivel;
    private boolean activo = true;
    
    public void setActivo(boolean pActivo){
        
        activo = pActivo;
    }
    
    public String[][] getConstantes(){
        
        return constantes;
    }
    
    public String[][][] getDesigualdades(){
        
        return desigualdades;
    }
    
    public String getNivel(){
        
        return nivel;
    }
    
    public boolean getActivo(){
        
        return activo;
    }
    
    
    
    
    public Partida(String pConstantes[][], String pDesigualdades[][][], String pNivel){
        
        nivel = pNivel;
     
        for(int i = 0; i < 5; i ++){
            
            for(int e = 0; e < 5; e ++){
                 // En desigualdades [0] se guardan los > y <
                if(pDesigualdades[i][e][0] != null){
                    
                    switch(pDesigualdades[i][e][0]){

                        case "a": desigualdades[i][e][0] = ">"; break;
                        case "b": desigualdades[i][e][0] = "<"; break;
                       
                    }
                } else desigualdades[i][e][0] = pDesigualdades[i][e][0];
                 // En desigualdades [1] se guardan los v y ^ 
                if(pDesigualdades[i][e][1] != null){
                    
                    switch(pDesigualdades[i][e][1]){

                        case "y": desigualdades[i][e][1] = "v"; break;
                        case "z": desigualdades[i][e][1] = "^"; break;
                       
                    }
                } else desigualdades[i][e][0] = pDesigualdades[i][e][0];
                
                 constantes[i][e] = pConstantes[i][e];
                
            }
              
        }
    }
 
//    public void getDatos(){
//        
//        System.out.println("NUEVA PARTIDA " + nivel);
//        
//        for(int i = 0; i < 5; i ++){
//
//            for(int e = 0; e < 5; e ++ ){
//                
//               
//                System.out.println("DESIGUALDAD " + desigualdades[i][e][0] + " " + 
//                       desigualdades[i][e][1] + " " + i + " " + e);
//              
//                System.out.println("CONSTANTE " + constantes[i][e] + " " + i + " " + e);
//               
//            }
//        }
//    }
}
