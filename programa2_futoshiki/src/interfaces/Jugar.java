package interfaces;

import logica.FuncionesListaEnlazada;
import logica.Cuadro;
import logica.JUEGO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.xml.transform.TransformerException;
import logica.CargarPartida;
import logica.GuardarPartida;
import logica.Partida;
import logica.XML;



public class Jugar extends javax.swing.JFrame {
    
    private Partida partidaActual;
     
    int numeroJugada;//para guardar el numero de los botones
   
    FuncionesListaEnlazada<String> listaJugadasHechas;//para guardar las jugadas hechas 
    
    private static ArrayList<JLabel> listaCuadros;//para guardar los cuadros utilizados, es para poder utilizar/modificar los cuadros
    private static ArrayList<JLabel> listaCuadrosRehacer;//para guardar los cuadros utilizados, es para poder utilizar/modificar los cuadros
   
    private static JLabel matrizCuadros[][] = new JLabel[5][5];
    private static JLabel matrizLabels[][][] = new JLabel[5][5][2];
    private static ArrayList<Cuadro> listaMatrizCuadros;//para guardar las jugadas hechas, para luego utilizar/modificar la matriz
    private static ArrayList<Cuadro> listaMatrizCuadrosRehacer;
    
    private Timer t;
    private int horas, min, sec, cs;
    
    public Jugar(boolean indicadorCargarPartida) 
    {
        initComponents();
        setLocationRelativeTo(null);
        
        if(indicadorCargarPartida){
            
            nombreJugador.setText(CargarPartida.getNombre());
            partidaActual = MenuPrincipal.getPartidaActual();
            horas = Integer.parseInt(CargarPartida.getHoras());
            min = Integer.parseInt(CargarPartida.getMinutos());
            sec = Integer.parseInt(CargarPartida.getSegundos());
            cs = Integer.parseInt(CargarPartida.getMilisegundos());
            JUEGO.claseJuego.setTiempo(CargarPartida.getTiempo());
            JUEGO.claseJuego.setPosicion(CargarPartida.getPosicionPanel());
            t = new Timer(10, acciones);
            //System.out.println("EL TIEMPO EN TOTAL ES " + horas +" " + min + " " + sec + " " + cs);
            
        }
        
        else partidaActual = MenuPrincipal.getPartida();
        
        labelNivel.setText("NIVEL " + MenuPrincipal.getPartidaActual().getNivel());
        
        // Se inicia la matriz de los cuadros
        
        boton1.setEnabled(false);
        boton2.setEnabled(false);
        boton3.setEnabled(false);
        boton4.setEnabled(false);
        boton5.setEnabled(false);
        terminaJuegoBoton.setEnabled(false);
        borrarJuegoBoton.setEnabled(false);
        
        JUEGO.claseJuego.agregarPartida();
        
        matrizCuadros[0][0] = cuadro1; matrizCuadros[0][1] = cuadro2; matrizCuadros[0][2] = cuadro3;
        matrizCuadros[0][3] = cuadro4; matrizCuadros[0][4] = cuadro5; matrizCuadros[1][0] = cuadro6;
        matrizCuadros[1][1] = cuadro7; matrizCuadros[1][2] = cuadro8;  matrizCuadros[1][3] = cuadro9;
        matrizCuadros[1][4] = cuadro10; matrizCuadros[2][0] = cuadro11; matrizCuadros[2][1] = cuadro12;
        matrizCuadros[2][2] = cuadro13; matrizCuadros[2][3] = cuadro14; matrizCuadros[2][4] = cuadro15;
        matrizCuadros[3][0] = cuadro16; matrizCuadros[3][1] = cuadro17; matrizCuadros[3][2] = cuadro18;
        matrizCuadros[3][3] = cuadro19; matrizCuadros[3][4] = cuadro20; matrizCuadros[4][0] = cuadro21;
        matrizCuadros[4][1] = cuadro22; matrizCuadros[4][2] = cuadro23; matrizCuadros[4][3] = cuadro24;
        matrizCuadros[4][4] = cuadro25;
        
        // Se inicia la matriz de los labels
        
        matrizLabels[0][0][0] = label00D; matrizLabels[0][0][1] = label00A; matrizLabels[0][1][0] = label01D;
        matrizLabels[0][1][1] = label01A; matrizLabels[0][2][0] = label02D; matrizLabels[0][2][1] = label02A;
        matrizLabels[0][3][0] = label03D; matrizLabels[0][3][1] = label03A; matrizLabels[0][4][0] = null;
        matrizLabels[0][4][1] = label04A; matrizLabels[1][0][0] = label10D; matrizLabels[1][0][1] = label10A;
        matrizLabels[1][1][0] = label11D; matrizLabels[1][1][1] = label11A; matrizLabels[1][2][0] = label12D;
        matrizLabels[1][2][1] = label10A; matrizLabels[1][3][0] = label13D; matrizLabels[1][3][1] = label13A;
        matrizLabels[1][4][0] = null; matrizLabels[1][4][1] = label14A; matrizLabels[2][0][0] = label20D;
        matrizLabels[2][0][1] = label20A; matrizLabels[2][1][0] = label21D; matrizLabels[2][1][1] = label21A;
        matrizLabels[2][2][0] = label22D; matrizLabels[2][2][1] = label22A; matrizLabels[2][3][0] = label23D;
        matrizLabels[2][3][1] = label23A; matrizLabels[2][4][0] = null; matrizLabels[2][4][1] = label24A;
        matrizLabels[3][0][0] = label30D; matrizLabels[3][0][1] = label30A; matrizLabels[3][1][0] = label31D;
        matrizLabels[3][1][1] = label31A; matrizLabels[3][2][0] = label32D; matrizLabels[3][2][1] = label32A;
        matrizLabels[3][3][0] = label33D; matrizLabels[3][3][1] = label33A; matrizLabels[3][4][0] = null;
        matrizLabels[3][4][1] = label34A; matrizLabels[4][0][0] = label40D; matrizLabels[4][0][1] = null;
        matrizLabels[4][1][0] = label41D; matrizLabels[4][1][1] = null; matrizLabels[4][2][0] = label42D;
        matrizLabels[4][2][1] = null; matrizLabels[4][3][0] = label43D; matrizLabels[4][3][1] = null;
        matrizLabels[4][4][0] = null; matrizLabels[4][4][1] = null; 
        
        setConstantes();
        
        numeroJugada = 0;  
        listaJugadasHechas = new FuncionesListaEnlazada<>();
        
        listaCuadros = new ArrayList();
        listaCuadrosRehacer = new ArrayList();
        
        listaMatrizCuadros = new ArrayList();
        listaMatrizCuadrosRehacer = new ArrayList();
        
        //----------- Configura de la dificultdad --------  
        
        String dificultad = JUEGO.claseJuego.getDificultad();
           
        //----------- Configura el tiempo/cronometro --------    
        
        if(!indicadorCargarPartida)
        {
            if (JUEGO.claseJuego.getTiempo().equals("No"))//NO quiere tiempo
            {          
                etiquetaTiempo.setEnabled(false);
                t = null;
            }
            else if(JUEGO.claseJuego.getTiempo().equals("Timer"))//quiere temporizador
            {
                if(JUEGO.claseJuego.getHoras().equals(""))//por si el usuario no da horas
                {
                    horas = 0;
                }
                else
                {
                    horas = Integer.parseInt(JUEGO.claseJuego.getHoras());
                }
                cs = 100;
                sec = Integer.parseInt(JUEGO.claseJuego.getSec());
                min = Integer.parseInt(JUEGO.claseJuego.getMin());
                t = new Timer(10, acciones);
            }
          
            else // Tiempo normal
            {
                min = 0;
                cs = 0;
                sec = 0;
                horas = 0;
                t = new Timer(10, acciones);
            }
        }
        
        

        //----------- Configura la posisicion de las casillas -------
        
       if(JUEGO.claseJuego.getPosicion().equals("Izquierda"))
        {
           
            iniciarJuegobot.setLocation(750, 180);
            borrarJugadaBot.setLocation(750, 240);
            rehacerBoton.setLocation(750, 300);
            top10Boton.setLocation(750, 360);

            PanelPrincipal.add(iniciarJuegobot);
            PanelPrincipal.add(borrarJugadaBot);
            PanelPrincipal.add(rehacerBoton);
            PanelPrincipal.add(top10Boton);

            boton1.setLocation(100, 200);
            boton2.setLocation(100, 260);
            boton3.setLocation(100, 320);
            boton4.setLocation(100, 380);
            boton5.setLocation(100, 440);

            PanelPrincipal.add(boton1);
            PanelPrincipal.add(boton2);
            PanelPrincipal.add(boton3);
            PanelPrincipal.add(boton4);
            PanelPrincipal.add(boton5);
            
            cargarJuegoBoton.setLocation(50, 570);
            salirJuegoBoton.setLocation(750, 460);
            guardarJuegoBoton.setLocation(275, 570);
            terminaJuegoBoton.setLocation(500, 570);
            borrarJuegoBoton.setLocation(725, 570);
            PanelPrincipal.add(cargarJuegoBoton);
            PanelPrincipal.add(salirJuegoBoton);
            PanelPrincipal.add(guardarJuegoBoton);
            PanelPrincipal.add(terminaJuegoBoton);
            PanelPrincipal.add(borrarJuegoBoton);
            
            PanelPrincipal.setSize(931, 636);
            PanelPrincipal.setLocation(0, 0);

        }             

        rehacerBoton.setEnabled(false);
     }
    
    public void setBordeRojo(JLabel label){
        
        label.setBorder(BorderFactory.createMatteBorder(
                                   2, 2, 2, 2, Color.red));
        
    }
    
    public void setBordeNegro(JLabel label){
        
        label.setBorder(BorderFactory.createMatteBorder(
                                   2, 2, 2, 2, Color.black));
        
    }
    
    public void setConstantes(){
      
        int num;
        String simbolo;
        for(int i = 0; i < 5; i ++){
            
            for(int e = 0; e < 5; e ++){
                num = JUEGO.claseJuego.getListaCuadros()[i][e].getNum();
                if(num == 0) matrizCuadros[i][e].setText("");
                else matrizCuadros[i][e].setText(Integer.toString(num));
                simbolo = JUEGO.claseJuego.getListaCuadros()[i][e].getSimbolo1();
             
                if(simbolo != null &&  matrizLabels[i][e][0] != null)
                    matrizLabels[i][e][0].setText(simbolo);
               
                simbolo = JUEGO.claseJuego.getListaCuadros()[i][e].getSimbolo2();
                if(simbolo != null && matrizLabels[i][e][1] != null) 
                    matrizLabels[i][e][1].setText(simbolo);
            }
        }
    }
    /**
     * Metodo para el timer/tiempo del juego
     */
    private ActionListener acciones = new ActionListener()
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if (JUEGO.claseJuego.getTiempo().equals("Timer"))//temporizador
            { 
                --cs; 
                if(cs == 0)
                {                
                    if((horas == 0) && (min == 0) && (sec == 0) && (cs == 0))//punto de salida
                    {
                        actualizarLabel();
                        t.stop();
                        JOptionPane.showMessageDialog(null, "Se ha quedado sin tiempo");
                        return;
                    }
                    if(sec >= 1)//cuando es 0, no se le quita
                    { 
                        --sec;
                    }                   
                    cs = 100; 
                }
                if(sec == 0) 
                {
                    if(min >= 1)//cuando es 0, no se le quita
                    {
                        sec = 60;  
                        --min;
                    }
                    else//significa que no hay mas minutos que restar
                    {
                        sec = 0;
                    }                 
                }
                if(min == 0)
                {   
                    if(horas >= 1)//cuando es 0, no se le quita
                    {
                        --horas;
                        min = 60;
                    }   
                }
                actualizarLabel();
            }
            else//Cronometro Normal
            {
                ++cs; 
                if(cs==100){
                    cs = 0;
                    ++sec;
                }
                if(sec==60) 
                {
                    sec = 0;
                    ++min;
                }
                if(min==60)
                {
                    min = 0;
                    ++horas;
                }
                actualizarLabel();  
            }
        }       
    };
    
    private void actualizarLabel() 
    {
        String tiempo = (horas <= 9?"0":"")+ horas + ":"+ (min<=9?"0":"")+ min+":"+(sec<=9?"0":"")+sec+":"+(cs<=9?"0":"")+cs;
        etiquetaTiempo.setText(tiempo);
    }
   
    public void ganoPartida()
    {
        for (int i = 0; i < matrizCuadros.length; i++)
        {   
            for (int x = 0; x < matrizCuadros[i].length; x++)
            {
                if(matrizCuadros[i][x].getText().equals(""))
                {
                    return;
                }
            }
        }
        //significa que todos los cuadros han sido llenados
        JOptionPane.showMessageDialog(null, "Felicidades, Ha ganado la partida");
        guardarNombreTiempo();
        
        this.dispose();
        MenuPrincipal nuevoMenu = new MenuPrincipal();
        nuevoMenu.setVisible(true);     
    }
    
    public void guardarNombreTiempo()
    {
        if(JUEGO.claseJuego.getTiempo().equals("Timer"))
        {
            return;
        }
        
        if (JUEGO.claseJuego.getDificultad().equals("Dificil"))
        {
            File archivoD = new File("TiemposDificil.dat");

            JUEGO.claseJuego.crearArchivoTop10(archivoD);//si esta creado, no se vuelve a crear

            String tiempoTotal = String.valueOf(horas) + String.valueOf(min) + String.valueOf(sec);    

            JUEGO.claseJuego.guardarArchivoTop10(archivoD, nombreJugador.getText(), tiempoTotal, horas, min, sec);//guarda el nombre y tiempo 

        }
        if (JUEGO.claseJuego.getDificultad().equals("Intermedio"))
        {
            File archivoD = new File("TiemposIntermedio.dat");

            JUEGO.claseJuego.crearArchivoTop10(archivoD);//si esta creado, no se vuelve a crear

            String tiempoTotal = String.valueOf(horas) + String.valueOf(min) + String.valueOf(sec);    

            JUEGO.claseJuego.guardarArchivoTop10(archivoD, nombreJugador.getText(), tiempoTotal, horas, min, sec);//guarda el nombre y tiempo 

        }
        if (JUEGO.claseJuego.getDificultad().equals("Facil"))
        {
            File archivoD = new File("TiemposFacil.dat");

            JUEGO.claseJuego.crearArchivoTop10(archivoD);//si esta creado, no se vuelve a crear

            String tiempoTotal = String.valueOf(horas) + String.valueOf(min) + String.valueOf(sec);    

            JUEGO.claseJuego.guardarArchivoTop10(archivoD, nombreJugador.getText(), tiempoTotal, horas, min, sec);//guarda el nombre y tiempo 

        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelPrincipal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cuadro6 = new javax.swing.JLabel();
        cuadro11 = new javax.swing.JLabel();
        cuadro16 = new javax.swing.JLabel();
        cuadro21 = new javax.swing.JLabel();
        cuadro2 = new javax.swing.JLabel();
        cuadro7 = new javax.swing.JLabel();
        cuadro1 = new javax.swing.JLabel();
        cuadro12 = new javax.swing.JLabel();
        cuadro17 = new javax.swing.JLabel();
        cuadro23 = new javax.swing.JLabel();
        cuadro18 = new javax.swing.JLabel();
        cuadro22 = new javax.swing.JLabel();
        cuadro8 = new javax.swing.JLabel();
        cuadro13 = new javax.swing.JLabel();
        cuadro4 = new javax.swing.JLabel();
        cuadro9 = new javax.swing.JLabel();
        cuadro14 = new javax.swing.JLabel();
        cuadro3 = new javax.swing.JLabel();
        cuadro24 = new javax.swing.JLabel();
        cuadro5 = new javax.swing.JLabel();
        cuadro10 = new javax.swing.JLabel();
        cuadro15 = new javax.swing.JLabel();
        cuadro19 = new javax.swing.JLabel();
        cuadro25 = new javax.swing.JLabel();
        cuadro20 = new javax.swing.JLabel();
        label00D = new javax.swing.JLabel();
        label02D = new javax.swing.JLabel();
        label03D = new javax.swing.JLabel();
        label01D = new javax.swing.JLabel();
        label13D = new javax.swing.JLabel();
        label12D = new javax.swing.JLabel();
        label10D = new javax.swing.JLabel();
        label11D = new javax.swing.JLabel();
        label20D = new javax.swing.JLabel();
        label30D = new javax.swing.JLabel();
        label40D = new javax.swing.JLabel();
        label32D = new javax.swing.JLabel();
        label21D = new javax.swing.JLabel();
        label22D = new javax.swing.JLabel();
        label23D = new javax.swing.JLabel();
        label31D = new javax.swing.JLabel();
        label42D = new javax.swing.JLabel();
        label33D = new javax.swing.JLabel();
        label43D = new javax.swing.JLabel();
        label41D = new javax.swing.JLabel();
        label00A = new javax.swing.JLabel();
        label01A = new javax.swing.JLabel();
        label02A = new javax.swing.JLabel();
        label03A = new javax.swing.JLabel();
        label04A = new javax.swing.JLabel();
        label10A = new javax.swing.JLabel();
        label11A = new javax.swing.JLabel();
        label13A = new javax.swing.JLabel();
        label12A = new javax.swing.JLabel();
        label14A = new javax.swing.JLabel();
        label20A = new javax.swing.JLabel();
        label21A = new javax.swing.JLabel();
        label22A = new javax.swing.JLabel();
        label23A = new javax.swing.JLabel();
        label24A = new javax.swing.JLabel();
        label30A = new javax.swing.JLabel();
        label31A = new javax.swing.JLabel();
        label32A = new javax.swing.JLabel();
        label33A = new javax.swing.JLabel();
        label34A = new javax.swing.JLabel();
        labelNivel = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        nombreJugador = new javax.swing.JTextPane();
        terminaJuegoBoton = new javax.swing.JButton();
        iniciarJuegobot = new javax.swing.JButton();
        guardarJuegoBoton = new javax.swing.JButton();
        borrarJuegoBoton = new javax.swing.JButton();
        rehacerBoton = new javax.swing.JButton();
        top10Boton = new javax.swing.JButton();
        borrarJugadaBot = new javax.swing.JButton();
        cargarJuegoBoton = new javax.swing.JButton();
        boton2 = new javax.swing.JButton();
        boton3 = new javax.swing.JButton();
        boton4 = new javax.swing.JButton();
        boton5 = new javax.swing.JButton();
        boton1 = new javax.swing.JButton();
        etiquetaTiempo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        salirJuegoBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        PanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        PanelPrincipal.setForeground(new java.awt.Color(255, 51, 0));
        PanelPrincipal.setMaximumSize(new java.awt.Dimension(936, 636));
        PanelPrincipal.setMinimumSize(new java.awt.Dimension(936, 636));
        PanelPrincipal.setPreferredSize(new java.awt.Dimension(936, 636));

        jPanel3.setBackground(new java.awt.Color(255, 51, 0));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FUTOSHIKI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        cuadro6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro6MouseClicked(evt);
            }
        });

        cuadro11.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro11MouseClicked(evt);
            }
        });

        cuadro16.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro16.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro16MouseClicked(evt);
            }
        });

        cuadro21.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro21.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro21MouseClicked(evt);
            }
        });

        cuadro2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro2MouseClicked(evt);
            }
        });

        cuadro7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro7MouseClicked(evt);
            }
        });

        cuadro1.setBackground(new java.awt.Color(0, 0, 0));
        cuadro1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro1.setForeground(new java.awt.Color(0, 0, 0));
        cuadro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro1MouseClicked(evt);
            }
        });

        cuadro12.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro12.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro12MouseClicked(evt);
            }
        });

        cuadro17.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro17.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro17MouseClicked(evt);
            }
        });

        cuadro23.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro23.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro23MouseClicked(evt);
            }
        });

        cuadro18.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro18.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro18MouseClicked(evt);
            }
        });

        cuadro22.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro22.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro22MouseClicked(evt);
            }
        });

        cuadro8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro8MouseClicked(evt);
            }
        });

        cuadro13.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro13MouseClicked(evt);
            }
        });

        cuadro4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro4MouseClicked(evt);
            }
        });

        cuadro9.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro9MouseClicked(evt);
            }
        });

        cuadro14.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro14.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro14MouseClicked(evt);
            }
        });

        cuadro3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro3MouseClicked(evt);
            }
        });

        cuadro24.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro24.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro24MouseClicked(evt);
            }
        });

        cuadro5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro5MouseClicked(evt);
            }
        });

        cuadro10.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro10MouseClicked(evt);
            }
        });

        cuadro15.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro15.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro15MouseClicked(evt);
            }
        });

        cuadro19.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro19.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro19MouseClicked(evt);
            }
        });

        cuadro25.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro25.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro25MouseClicked(evt);
            }
        });

        cuadro20.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        cuadro20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuadro20.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cuadro20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuadro20MouseClicked(evt);
            }
        });

        label00D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label00D.setText("   ");

        label02D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label02D.setText("   ");

        label03D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label03D.setText("   ");

        label01D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label01D.setText("   ");

        label13D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label13D.setText("   ");

        label12D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label12D.setText("   ");

        label10D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label10D.setText("   ");

        label11D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label11D.setText("   ");

        label20D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label20D.setText("   ");

        label30D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label30D.setText("   ");

        label40D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label40D.setText("   ");

        label32D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label32D.setText("   ");

        label21D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label21D.setText("   ");

        label22D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label22D.setText("   ");

        label23D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label23D.setText("   ");

        label31D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label31D.setText("   ");

        label42D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label42D.setText("   ");

        label33D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label33D.setText("   ");

        label43D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label43D.setText("   ");

        label41D.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label41D.setText("   ");

        label00A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label00A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label00A.setText("   ");

        label01A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label01A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label01A.setText("   ");

        label02A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label02A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label02A.setText("   ");

        label03A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label03A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label03A.setText("   ");

        label04A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label04A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label04A.setText("   ");

        label10A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label10A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label10A.setText("   ");

        label11A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label11A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label11A.setText("   ");

        label13A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label13A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label13A.setText("   ");

        label12A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label12A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12A.setText("   ");

        label14A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label14A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label14A.setText("   ");

        label20A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label20A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label20A.setText("   ");

        label21A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label21A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label21A.setText("   ");

        label22A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label22A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label22A.setText("   ");

        label23A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label23A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label23A.setText("   ");

        label24A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label24A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label24A.setText("   ");

        label30A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label30A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label30A.setText("   ");

        label31A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label31A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label31A.setText("   ");

        label32A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label32A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label32A.setText("   ");

        label33A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label33A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label33A.setText("   ");

        label34A.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label34A.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label34A.setText("   ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cuadro21, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label40D)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cuadro22, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cuadro16, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label30D)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label31A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cuadro17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label31D)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(label41D)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cuadro23, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label42D)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cuadro24, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label43D)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cuadro25, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(label32A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cuadro6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label00A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cuadro11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                                        .addComponent(label10D)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cuadro7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(label01A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(label20D)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cuadro12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(label10A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label11A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label21A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label11D)
                                    .addComponent(label21D))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label12A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(cuadro8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label12D)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cuadro9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label13D)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cuadro10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label33A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(cuadro18, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(label32D)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cuadro19, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(label22A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(label23A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(cuadro13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(label22D)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label13A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cuadro14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(label23D)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(label24A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label14A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cuadro15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(label33D)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(label34A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cuadro20, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cuadro1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label00D, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cuadro2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label01D, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cuadro3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label02D, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                                    .addComponent(label02A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cuadro4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label03D))
                                    .addComponent(label03A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label04A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cuadro5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(label20A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label30A, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label00D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label02D, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(label03D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label01D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label00A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label01A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label02A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label03A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label04A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(label11D, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addComponent(cuadro7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label10D, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cuadro8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cuadro6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label12D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label10A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label11A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label12A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label13A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label14A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label22D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cuadro11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cuadro12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cuadro13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label21D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label20D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cuadro10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label13D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cuadro9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cuadro15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label23D, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cuadro14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label20A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label21A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(label22A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cuadro17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cuadro16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cuadro18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cuadro19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label33D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label31D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cuadro20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label30D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label32D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label30A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label31A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label32A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label33A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label34A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label23A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label24A, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label42D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuadro23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuadro21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label40D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label41D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label43D, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(cuadro25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNivel.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        labelNivel.setForeground(new java.awt.Color(255, 0, 0));
        labelNivel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNivel.setText("NIVEL FÁCIL");

        jLabel66.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 51, 0));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Nombre del Jugador");

        nombreJugador.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                nombreJugadorInputMethodTextChanged(evt);
            }
        });
        jScrollPane2.setViewportView(nombreJugador);

        terminaJuegoBoton.setBackground(new java.awt.Color(255, 0, 153));
        terminaJuegoBoton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        terminaJuegoBoton.setForeground(new java.awt.Color(255, 255, 255));
        terminaJuegoBoton.setText("TERMINAR JUEGO");
        terminaJuegoBoton.setBorder(null);
        terminaJuegoBoton.setFocusPainted(false);
        terminaJuegoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminaJuegoBotonActionPerformed(evt);
            }
        });

        iniciarJuegobot.setBackground(new java.awt.Color(0, 153, 0));
        iniciarJuegobot.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        iniciarJuegobot.setForeground(new java.awt.Color(255, 255, 255));
        iniciarJuegobot.setText("INICIAR JUEGO");
        iniciarJuegobot.setBorder(null);
        iniciarJuegobot.setFocusPainted(false);
        iniciarJuegobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarJuegobotActionPerformed(evt);
            }
        });

        guardarJuegoBoton.setBackground(new java.awt.Color(153, 153, 153));
        guardarJuegoBoton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        guardarJuegoBoton.setForeground(new java.awt.Color(255, 255, 255));
        guardarJuegoBoton.setText("Guardar Juego");
        guardarJuegoBoton.setBorder(null);
        guardarJuegoBoton.setFocusPainted(false);
        guardarJuegoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarJuegoBotonActionPerformed(evt);
            }
        });

        borrarJuegoBoton.setBackground(new java.awt.Color(51, 153, 255));
        borrarJuegoBoton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        borrarJuegoBoton.setForeground(new java.awt.Color(255, 255, 255));
        borrarJuegoBoton.setText("BORRAR JUEGO");
        borrarJuegoBoton.setBorder(null);
        borrarJuegoBoton.setFocusPainted(false);
        borrarJuegoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarJuegoBotonActionPerformed(evt);
            }
        });

        rehacerBoton.setBackground(new java.awt.Color(51, 204, 255));
        rehacerBoton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rehacerBoton.setForeground(new java.awt.Color(255, 255, 255));
        rehacerBoton.setText("REHACER JUGADA");
        rehacerBoton.setBorder(null);
        rehacerBoton.setFocusPainted(false);
        rehacerBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rehacerBotonActionPerformed(evt);
            }
        });

        top10Boton.setBackground(new java.awt.Color(255, 204, 0));
        top10Boton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        top10Boton.setForeground(new java.awt.Color(255, 255, 255));
        top10Boton.setText("TOP 10");
        top10Boton.setBorder(null);
        top10Boton.setFocusPainted(false);
        top10Boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                top10BotonActionPerformed(evt);
            }
        });

        borrarJugadaBot.setBackground(new java.awt.Color(255, 51, 51));
        borrarJugadaBot.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        borrarJugadaBot.setForeground(new java.awt.Color(255, 255, 255));
        borrarJugadaBot.setText("BORRAR JUGADA");
        borrarJugadaBot.setBorder(null);
        borrarJugadaBot.setFocusPainted(false);
        borrarJugadaBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarJugadaBotActionPerformed(evt);
            }
        });

        cargarJuegoBoton.setBackground(new java.awt.Color(153, 153, 153));
        cargarJuegoBoton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cargarJuegoBoton.setForeground(new java.awt.Color(255, 255, 255));
        cargarJuegoBoton.setText("Cargar Juego");
        cargarJuegoBoton.setBorder(null);
        cargarJuegoBoton.setFocusPainted(false);
        cargarJuegoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarJuegoBotonActionPerformed(evt);
            }
        });

        boton2.setBackground(new java.awt.Color(204, 204, 204));
        boton2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        boton2.setText("2");
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });

        boton3.setBackground(new java.awt.Color(204, 204, 204));
        boton3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        boton3.setText("3");
        boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });

        boton4.setBackground(new java.awt.Color(204, 204, 204));
        boton4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        boton4.setText("4");
        boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });

        boton5.setBackground(new java.awt.Color(204, 204, 204));
        boton5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        boton5.setText("5");
        boton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton5ActionPerformed(evt);
            }
        });

        boton1.setBackground(new java.awt.Color(204, 204, 204));
        boton1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        boton1.setText("1");
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        etiquetaTiempo.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        etiquetaTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTiempo.setText("00:00:00:00");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel3.setText("Tiempo:");

        salirJuegoBoton.setBackground(new java.awt.Color(153, 153, 153));
        salirJuegoBoton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        salirJuegoBoton.setForeground(new java.awt.Color(255, 255, 255));
        salirJuegoBoton.setText("Salir");
        salirJuegoBoton.setBorder(null);
        salirJuegoBoton.setFocusPainted(false);
        salirJuegoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirJuegoBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(borrarJugadaBot, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iniciarJuegobot, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rehacerBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(top10Boton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cargarJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salirJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188)
                        .addComponent(jLabel3)
                        .addGap(57, 57, 57))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(etiquetaTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(boton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(boton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(boton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(boton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(boton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(61, 61, 61))))
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addComponent(guardarJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(terminaJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(borrarJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(38, Short.MAX_VALUE))))))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaTiempo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(iniciarJuegobot, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(borrarJugadaBot, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(rehacerBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(top10Boton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(salirJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(boton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(boton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boton5)
                                .addGap(59, 59, 59)))
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cargarJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(guardarJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(terminaJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrarJuegoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        t.start();
        numeroJugada = 1;

        //System.out.println("Se presiono" + boton1.getText());

        boton1.setBackground(Color.green);
        boton2.setBackground(Color.lightGray);
        boton3.setBackground(Color.lightGray);
        boton4.setBackground(Color.lightGray);
        boton5.setBackground(Color.lightGray);
    }//GEN-LAST:event_boton1ActionPerformed

    private void boton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton5ActionPerformed
        t.start();
        
        numeroJugada = 5;

        //System.out.println("Se presiono" + boton5.getText());

        boton1.setBackground(Color.lightGray);
        boton2.setBackground(Color.lightGray);
        boton3.setBackground(Color.lightGray);
        boton4.setBackground(Color.lightGray);
        boton5.setBackground(Color.green);
    }//GEN-LAST:event_boton5ActionPerformed

    private void boton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton4ActionPerformed
        t.start();
        
        numeroJugada = 4;

        //System.out.println("Se presiono" + boton4.getText());

        boton1.setBackground(Color.lightGray);
        boton2.setBackground(Color.lightGray);
        boton3.setBackground(Color.lightGray);
        boton4.setBackground(Color.green);
        boton5.setBackground(Color.lightGray);
    }//GEN-LAST:event_boton4ActionPerformed

    private void boton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton3ActionPerformed
       t.start();
        
        numeroJugada = 3;

        //System.out.println("Se presiono" + boton3.getText());

        boton1.setBackground(Color.lightGray);
        boton2.setBackground(Color.lightGray);
        boton3.setBackground(Color.green);
        boton4.setBackground(Color.lightGray);
        boton5.setBackground(Color.lightGray);
    }//GEN-LAST:event_boton3ActionPerformed

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        t.start();
        
        numeroJugada = 2;

        //System.out.println("Se presiono" + boton2.getText());

        boton1.setBackground(Color.lightGray);
        boton2.setBackground(Color.green);
        boton3.setBackground(Color.lightGray);
        boton4.setBackground(Color.lightGray);
        boton5.setBackground(Color.lightGray);
    }//GEN-LAST:event_boton2ActionPerformed

    private void borrarJugadaBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarJugadaBotActionPerformed

        if(listaJugadasHechas.empty() == true)//por si esta vacia la lista
        {

            JOptionPane.showMessageDialog(null, "No hay jugadas por borrar");
            return;
        }
        //System.out.println("LA LISTA ESTA  MALA ");
        rehacerBoton.setEnabled(true);
        //        if(listaMatrizCuadros.isEmpty() == true)//por si esta vacia la lista
        //        {
            //            return;
            //        }
        //
        //        if(listaCuadros.isEmpty() == true)//por si esta vacia la lista
        //        {
            //            return;
            //        }

        //Internamente en la matriz del juego
        //---------------------Elimina dentro de la matriz interna-------------------------
        int ultimo1 = listaMatrizCuadros.size() - 1; //obtiene el ultimo del indice

        int fila = listaMatrizCuadros.get(ultimo1).getFila();
        int columna = listaMatrizCuadros.get(ultimo1).getColumna();

        listaMatrizCuadrosRehacer.add(listaMatrizCuadros.get(ultimo1)); //para luego rehacerlo

        listaMatrizCuadros.remove(ultimo1);//elimina el ultimo cuadro utilizado

        JUEGO.claseJuego.insertarNum(0, fila, columna);//para hacer la matriz de nuevo en 0

        //Externamente en la matriz del juego
        //--------------------Elimina y hace push de la lista de los cuadros utilizados------

        int ultimo2 = listaCuadros.size() - 1; //obtiene el ultimo del indice

        listaCuadros.get(ultimo2).setText("");//pone el cuadro en 0

        listaCuadrosRehacer.add(listaCuadros.get(ultimo2));//se agrega para rehacerlo

        listaCuadros.remove(ultimo2);

        listaJugadasHechas.pop();

    }//GEN-LAST:event_borrarJugadaBotActionPerformed

    private void top10BotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_top10BotonActionPerformed
        Top10 top10 = new Top10();
        
        t.stop();
        top10.setVisible(true);
        
    }//GEN-LAST:event_top10BotonActionPerformed

    private void rehacerBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rehacerBotonActionPerformed
        rehacerBoton.setEnabled(false);

        //--------------- Internamente dentro de la matriz del juego
        int ultimo1 = listaMatrizCuadrosRehacer.size() - 1; //obtiene el ultimo del indice

        int num = listaMatrizCuadrosRehacer.get(ultimo1).getNum();
        int fila = listaMatrizCuadrosRehacer.get(ultimo1).getFila();
        int columna = listaMatrizCuadrosRehacer.get(ultimo1).getColumna();

        JUEGO.claseJuego.insertarNum(num, fila, columna);//para hacer la matriz de nuevo en 0

        //------------ Externamente en la matriz del juego

        int ultimo2 = listaCuadrosRehacer.size() - 1; //obtiene el ultimo del indice

        String numero = String.valueOf(num);//utiliza el numero de la matriz interna para luego ponerlo en la externa

        listaCuadrosRehacer.get(ultimo2).setText(numero);//pone el cuadro en 0

        listaCuadrosRehacer.remove(ultimo2);

    }//GEN-LAST:event_rehacerBotonActionPerformed

    private void borrarJuegoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarJuegoBotonActionPerformed
        t.stop(); 
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar el juego?", "Alerta!",
        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        
        if(JOptionPane.YES_OPTION == resp)
        {
            JUEGO.claseJuego.agregarPartida();
            setConstantes();    
        }
        t.start();
        
        

    }//GEN-LAST:event_borrarJuegoBotonActionPerformed

    private void iniciarJuegobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarJuegobotActionPerformed
        
        if(nombreJugador.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "DEBE DE INGRESAR SU NOMBRE");
            return;
        }
        terminaJuegoBoton.setEnabled(true);
        
        boton1.setEnabled(true);
        boton2.setEnabled(true);
        boton3.setEnabled(true);
        boton4.setEnabled(true);
        boton5.setEnabled(true);  
        nombreJugador.setEnabled(false);
        iniciarJuegobot.setEnabled(false);
        
        borrarJuegoBoton.setEnabled(true);
        
        if (t != null)//significa que no quiere cronometro y que quiere cronometro normal
        {
            t.start();
        }
        //else: no quiere cronometro
        //el otro caso se pone temporizador

    }//GEN-LAST:event_iniciarJuegobotActionPerformed

    private void terminaJuegoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminaJuegoBotonActionPerformed
        t.stop();
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea terminar el juego?", "Alerta!",
            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if(JOptionPane.YES_OPTION == resp)
        {                
            this.dispose();
            MenuPrincipal.iniciarNuevaPartida();          
        }
        t.start();
    }//GEN-LAST:event_terminaJuegoBotonActionPerformed

    private void cuadro20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro20MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro20.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 4) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 3) == true)//numero, fila
                {
                    
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 4, true)){
                        
                        setBordeRojo(cuadro20);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro20);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 4, false)){
                        setBordeRojo(cuadro20);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro20);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 3, 4);

                    String num = String.valueOf(numeroJugada);
                    cuadro20.setText(num);
                    
                    listaCuadros.add(cuadro20);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 0, 0);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro20);
                JOptionPane.showMessageDialog(null, "El numero ya fue escrito en la fila");
                setBordeNegro(cuadro20);
                return;
            }
            setBordeRojo(cuadro20);
            JOptionPane.showMessageDialog(null, "El numero ya fue escrito en la columna");
        }   setBordeNegro(cuadro20);
    }//GEN-LAST:event_cuadro20MouseClicked

    private void cuadro25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro25MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro25.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 4) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 4) == true)//numero, fila
                {
                    
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 4, true)){
            
                        setBordeRojo(cuadro25);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro25);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 4, false)){

                        setBordeRojo(cuadro25);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro25);
                        return;
                    }
                    JUEGO.claseJuego.insertarNum(numeroJugada, 4, 4);

                    String num = String.valueOf(numeroJugada);
                    cuadro25.setText(num);
                    
                    listaCuadros.add(cuadro25);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 4, 4);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro25);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro25);
                return;
            }
            setBordeRojo(cuadro25);
            JOptionPane.showMessageDialog(null, "El numero ya fue escrito en la columna");
            setBordeNegro(cuadro25);
            return;
        }
    }//GEN-LAST:event_cuadro25MouseClicked

    private void cuadro19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro19MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro19.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 3) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 3) == true)//numero, fila
                {
                    
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 3, true)){
            
                        setBordeRojo(cuadro19);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro19);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 3, false)){

                        setBordeRojo(cuadro19);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro19);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 3, 3);

                    String num = String.valueOf(numeroJugada);
                    cuadro19.setText(num);
                    
                    listaCuadros.add(cuadro19);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 3, 3);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro19);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro19);
                return;
            }
            setBordeRojo(cuadro19);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro19);
            return;
        }
    }//GEN-LAST:event_cuadro19MouseClicked

    private void cuadro15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro15MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro15.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 4) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 2) == true)//numero, fila
                {
                    
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 4, true)){
            
                        setBordeRojo(cuadro15);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro15);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 4, false)){

                        setBordeRojo(cuadro15);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro15);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 2, 4);

                    String num = String.valueOf(numeroJugada);
                    cuadro15.setText(num);
                    
                    listaCuadros.add(cuadro15);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 2, 4);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro15);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro15);
                return;
            }
            setBordeRojo(cuadro15);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro15);
            return;
        }
    }//GEN-LAST:event_cuadro15MouseClicked

    private void cuadro10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro10MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro10.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 4) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 1) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 4, true)){
            
                        setBordeRojo(cuadro10);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro10);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 4, false)){

                        setBordeRojo(cuadro10);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro10);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 1, 4);

                    String num = String.valueOf(numeroJugada);
                    cuadro10.setText(num);
                    
                    listaCuadros.add(cuadro10);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 1, 4);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro10);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro10);
                return;
            }
            setBordeRojo(cuadro10);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro10);
            return;
        }
    }//GEN-LAST:event_cuadro10MouseClicked

    private void cuadro5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro5MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro5.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 4) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 0) == true)//numero, fila
                {
                    
                    
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 4, true)){
            
                        setBordeRojo(cuadro5);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro5);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 4, false)){

                        setBordeRojo(cuadro5);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro5);
                        return;
                    }
                    JUEGO.claseJuego.insertarNum(numeroJugada, 0, 4);

                    String num = String.valueOf(numeroJugada);
                    cuadro5.setText(num);
                    
                    listaCuadros.add(cuadro5);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 0, 4);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro5);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro5);
                return;
            }
            setBordeRojo(cuadro5);
            JOptionPane.showMessageDialog(null, "El número ya fue esrito en la columna");
            setBordeNegro(cuadro5);
            return;
        }
    }//GEN-LAST:event_cuadro5MouseClicked

    private void cuadro24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro24MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro24.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 3) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 4) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 3, true)){
            
                        setBordeRojo(cuadro24);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro24);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 3, false)){

                        setBordeRojo(cuadro24);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro24);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 4, 3);

                    String num = String.valueOf(numeroJugada);
                    cuadro24.setText(num);
                    
                    listaCuadros.add(cuadro24);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 4, 3);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro24);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro24);
                return;
            }
            setBordeRojo(cuadro24);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro24);
            return;
        }
    }//GEN-LAST:event_cuadro24MouseClicked

    private void cuadro3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro3MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }
        
        if(cuadro3.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 2) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 0) == true)//numero, fila
                {   
                     if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 2, true)){
            
                        setBordeRojo(cuadro3);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro3);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 2, false)){

                        setBordeRojo(cuadro3);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro3);
                        return;
                    }
                    JUEGO.claseJuego.insertarNum(numeroJugada, 0, 2);

                    String num = String.valueOf(numeroJugada);
                    cuadro3.setText(num);
                    
                    listaCuadros.add(cuadro3);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 0, 2);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro3);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro3);
                return;
            }
            setBordeRojo(cuadro3);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro3);
            return;
        }
    }//GEN-LAST:event_cuadro3MouseClicked

    private void cuadro14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro14MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro14.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 3) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 2) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 3, true)){
            
                        setBordeRojo(cuadro14);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro14);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 3, false)){

                        setBordeRojo(cuadro14);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro14);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 2, 3);

                    String num = String.valueOf(numeroJugada);
                    cuadro14.setText(num);
                    
                    listaCuadros.add(cuadro14);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 2, 3);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro14);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro14);
                return;
            }
            setBordeRojo(cuadro14);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro14);
            return;
        }
    }//GEN-LAST:event_cuadro14MouseClicked

    private void cuadro9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro9MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro9.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 3) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 1) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 3, true)){
            
                        setBordeRojo(cuadro9);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro9);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 3, false)){

                        setBordeRojo(cuadro9);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro9);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 1, 3);

                    String num = String.valueOf(numeroJugada);
                    cuadro9.setText(num);
                    
                    listaCuadros.add(cuadro9);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 1, 3);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro9);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro9);
                return;
            }
            setBordeRojo(cuadro9);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro9);
            return;
        }
    }//GEN-LAST:event_cuadro9MouseClicked

    private void cuadro4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro4MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }
        
        if(cuadro4.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 3) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 0) == true)//numero, fila
                {
                    
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 3, true)){
            
                        setBordeRojo(cuadro4);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro4);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 3, false)){

                        setBordeRojo(cuadro4);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro4);
                        return;
                    }
        
                    JUEGO.claseJuego.insertarNum(numeroJugada, 0, 3);

                    String num = String.valueOf(numeroJugada);
                    cuadro4.setText(num);
                    
                    listaCuadros.add(cuadro4);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 0, 3);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro4);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro4);
                return;
            }
            setBordeRojo(cuadro4);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro4);
            return;
        }
    }//GEN-LAST:event_cuadro4MouseClicked

    private void cuadro13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro13MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro13.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 2) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 2) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 2, true)){
            
                        setBordeRojo(cuadro13);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro13);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 2, false)){

                        setBordeRojo(cuadro13);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro13);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 2, 2);

                    String num = String.valueOf(numeroJugada);
                    cuadro13.setText(num);
                    
                    listaCuadros.add(cuadro13);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 2, 2);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro13);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro13);
                return;
            }
            setBordeRojo(cuadro13);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro13);
            return;
        }
    }//GEN-LAST:event_cuadro13MouseClicked

    private void cuadro8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro8MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro8.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 2) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 1) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 2, true)){
            
                        setBordeRojo(cuadro8);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro8);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 2, false)){

                        setBordeRojo(cuadro8);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro8);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 1, 2);

                    String num = String.valueOf(numeroJugada);
                    cuadro8.setText(num);
                    
                    listaCuadros.add(cuadro8);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 1, 2);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro8);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro8);
                return;
            }
            setBordeRojo(cuadro8);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro8);
            return;
        }
    }//GEN-LAST:event_cuadro8MouseClicked

    private void cuadro22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro22MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro22.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 1) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 4) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 1, true)){
            
                        setBordeRojo(cuadro22);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro22);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 1, false)){

                        setBordeRojo(cuadro22);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro22);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 4, 1);

                    String num = String.valueOf(numeroJugada);
                    cuadro22.setText(num);
                    
                    listaCuadros.add(cuadro22);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 4, 1);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro22);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro22);
                return;
            }
            setBordeRojo(cuadro22);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro22);
            return;
        }
    }//GEN-LAST:event_cuadro22MouseClicked

    private void cuadro18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro18MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro18.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 2) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 3) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 2, true)){
            
                        setBordeRojo(cuadro18);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro18);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 2, false)){

                        setBordeRojo(cuadro18);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro18);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 3, 2);

                    String num = String.valueOf(numeroJugada);
                    cuadro18.setText(num);
                    
                    listaCuadros.add(cuadro18);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 3, 2);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro18);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro18);
                return;
            }
            setBordeRojo(cuadro18);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro18);
            return;
        }
    }//GEN-LAST:event_cuadro18MouseClicked

    private void cuadro23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro23MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro23.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 2) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 4) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 2, true)){
            
                        setBordeRojo(cuadro23);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro23);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 2, false)){

                        setBordeRojo(cuadro23);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro23);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 4, 2);

                    String num = String.valueOf(numeroJugada);
                    cuadro23.setText(num);
                    listaCuadros.add(cuadro23);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 4, 2);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro23);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro23);
                return;
            }
            setBordeRojo(cuadro23);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro23);
            return;
        }
    }//GEN-LAST:event_cuadro23MouseClicked

    private void cuadro17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro17MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro17.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 1) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 3) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 1, true)){
            
                        setBordeRojo(cuadro17);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro17);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 1, false)){

                        setBordeRojo(cuadro17);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro17);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 3, 1);

                    String num = String.valueOf(numeroJugada);
                    cuadro17.setText(num);
                    
                    listaCuadros.add(cuadro17);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 3, 1);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro17);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro17);
                return;
            }
            setBordeRojo(cuadro17);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro17);
            return;
        }
    }//GEN-LAST:event_cuadro17MouseClicked

    private void cuadro12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro12MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro12.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 1) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 2) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 1, true)){
            
                        setBordeRojo(cuadro12);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro12);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 1, false)){

                        setBordeRojo(cuadro12);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro12);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 2, 1);

                    String num = String.valueOf(numeroJugada);
                    cuadro12.setText(num);
                    
                    listaCuadros.add(cuadro12);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 2, 1);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro12);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro12);
                return;
            }
            setBordeRojo(cuadro20);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro20);
            return;
        }
    }//GEN-LAST:event_cuadro12MouseClicked

    private void cuadro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro1MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }
        
        if(cuadro1.getText().equals("")) //para que no repita cuadro
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 0) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 0) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 0, true)){
            
                        setBordeRojo(cuadro1);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro1);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 0, false)){

                        setBordeRojo(cuadro1);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro1);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 0, 0);

                    String num = String.valueOf(numeroJugada);
                    cuadro1.setText(num);

                    listaCuadros.add(cuadro1);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 0, 0);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    
                    ganoPartida();
                    
                    return;
                }
                setBordeRojo(cuadro1);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro1);
                return;
            }
            setBordeRojo(cuadro1);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro1);
            return;
        }
    }//GEN-LAST:event_cuadro1MouseClicked

    private void cuadro7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro7MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro7.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 1) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 1) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 1, true)){
            
                        setBordeRojo(cuadro7);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro7);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 1, false)){

                        setBordeRojo(cuadro7);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro7);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 1, 1);

                    String num = String.valueOf(numeroJugada);
                    cuadro7.setText(num);
                    
                    listaCuadros.add(cuadro7);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 2, 1);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro7);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro7);
                return;
            }
            setBordeRojo(cuadro7);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro7);
            return;
        }
    }//GEN-LAST:event_cuadro7MouseClicked

    private void cuadro2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro2MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro2.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 1) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 0) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 1, true)){
            
                        setBordeRojo(cuadro2);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro2);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 0, 1, false)){

                        setBordeRojo(cuadro2);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro2);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 0, 1);

                    String num = String.valueOf(numeroJugada);
                    cuadro2.setText(num);
                    listaCuadros.add(cuadro2);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "",  0, 1);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro2);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro2);
                return;
            }
            setBordeRojo(cuadro2);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro2);
            return;
        }
    }//GEN-LAST:event_cuadro2MouseClicked

    private void cuadro21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro21MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro21.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 0) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 4) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 0, true)){
            
                        setBordeRojo(cuadro21);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro21);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 4, 0, false)){

                        setBordeRojo(cuadro21);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro21);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 4, 0);

                    String num = String.valueOf(numeroJugada);
                    cuadro21.setText(num);
                    
                    listaCuadros.add(cuadro21);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 4, 0);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro21);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro21);
                return;
            }
            setBordeRojo(cuadro21);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro21);
            return;
        }
    }//GEN-LAST:event_cuadro21MouseClicked

    private void cuadro16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro16MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro16.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 0) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 3) == true)//numero, fila
                {
                    
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 0, true)){
            
                        setBordeRojo(cuadro16);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro16);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 3, 0, false)){

                        setBordeRojo(cuadro16);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro16);
                        return;
                    }
                    JUEGO.claseJuego.insertarNum(numeroJugada, 3, 0);

                    String num = String.valueOf(numeroJugada);
                    cuadro16.setText(num);
                    
                    listaCuadros.add(cuadro16);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 3, 0);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro16);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro16);
                return;
            }
            setBordeRojo(cuadro16);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro16);
            return;
        }
    }//GEN-LAST:event_cuadro16MouseClicked

    private void cuadro11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro11MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro11.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 0) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 2) == true)//numero, fila
                {   
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 0, true)){
            
                        setBordeRojo(cuadro11);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro11);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 2, 0, false)){

                        setBordeRojo(cuadro11);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro11);
                        return;
                    }
                    JUEGO.claseJuego.insertarNum(numeroJugada, 2, 0);
                  
                    String num = String.valueOf(numeroJugada);
                    cuadro11.setText(num);
                    
                    listaCuadros.add(cuadro11);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 2, 0);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro11);
                JOptionPane.showMessageDialog(null, "Este número ya fue escrito en la fila");
                setBordeNegro(cuadro11);
                return;
            }
            setBordeRojo(cuadro11);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la columna");
            setBordeNegro(cuadro11);
            return;
        }
    }//GEN-LAST:event_cuadro11MouseClicked

    private void cuadro6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuadro6MouseClicked
        if(numeroJugada == 0)//para que no de error antes de presionar los botones
        {
            return;
        }

        if(cuadro6.getText().equals(""))
        {
            if(JUEGO.claseJuego.verificaColumna(numeroJugada, 0) == true)//numero, columna
            {
                if(JUEGO.claseJuego.verificaFila(numeroJugada, 1) == true)//numero, fila
                {
                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 0, true)){
            
                        setBordeRojo(cuadro6);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro6);
                        return;
                    }

                    if(! JUEGO.claseJuego.verificaDesigualdad2(numeroJugada, 1, 0, false)){

                        setBordeRojo(cuadro6);
                        JOptionPane.showMessageDialog(null, "La desigualdad no se cumple");
                        setBordeNegro(cuadro6);
                        return;
                    }
                    
                    JUEGO.claseJuego.insertarNum(numeroJugada, 1, 0);

                    String num = String.valueOf(numeroJugada);
                    cuadro6.setText(num);
                    
                    listaCuadros.add(cuadro6);//se guarda el cuadro

                    listaJugadasHechas.push(num);//se guarda el numero que fue jugado

                    Cuadro cuadrito = new Cuadro(numeroJugada, "", "", 1, 0);//guarda el numero, la fila y la columna
                    listaMatrizCuadros.add(cuadrito);

                    boton1.setBackground(Color.lightGray);
                    boton2.setBackground(Color.lightGray);
                    boton3.setBackground(Color.lightGray);
                    boton4.setBackground(Color.lightGray);
                    boton5.setBackground(Color.lightGray);

                    numeroJugada = 0;
                    ganoPartida();
                    return;
                }
                setBordeRojo(cuadro6);
                JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
                setBordeNegro(cuadro6);
                return;
            }
            setBordeRojo(cuadro6);
            JOptionPane.showMessageDialog(null, "El número ya fue escrito en la fila");
            setBordeNegro(cuadro6);
            return;
        }
    }//GEN-LAST:event_cuadro6MouseClicked

    private void cargarJuegoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarJuegoBotonActionPerformed
        // TODO add your handling code here:
        
        MenuPrincipal.CargarNuevaPartida();
        
        this.dispose();
    }//GEN-LAST:event_cargarJuegoBotonActionPerformed

    private void guardarJuegoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarJuegoBotonActionPerformed
        // TODO add your handling code here:
        try {
     
            String nivel = partidaActual.getNivel();
            GuardarPartida nuevo = new GuardarPartida(JUEGO.claseJuego.getListaCuadros(), nivel,
            horas, min, sec, cs, nombreJugador.getText(), JUEGO.claseJuego.getTiempo(), 
            JUEGO.claseJuego.getPosicion());
            
            
        } catch (TransformerException ex) {
            
            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardarJuegoBotonActionPerformed

    private void nombreJugadorInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nombreJugadorInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreJugadorInputMethodTextChanged

    private void salirJuegoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirJuegoBotonActionPerformed
        // TODO add your handling code here:
        
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salirse?", "Alerta!",
            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if(JOptionPane.YES_OPTION == resp)
        {                
            
            MenuPrincipal nuevo = new MenuPrincipal();
            this.dispose();
            nuevo.setVisible(true);
        }
      
    }//GEN-LAST:event_salirJuegoBotonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {               
                new Jugar(false).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton borrarJuegoBoton;
    private javax.swing.JButton borrarJugadaBot;
    private javax.swing.JButton boton1;
    private javax.swing.JButton boton2;
    private javax.swing.JButton boton3;
    private javax.swing.JButton boton4;
    private javax.swing.JButton boton5;
    private javax.swing.JButton cargarJuegoBoton;
    private javax.swing.JLabel cuadro1;
    private javax.swing.JLabel cuadro10;
    private javax.swing.JLabel cuadro11;
    private javax.swing.JLabel cuadro12;
    private javax.swing.JLabel cuadro13;
    private javax.swing.JLabel cuadro14;
    private javax.swing.JLabel cuadro15;
    private javax.swing.JLabel cuadro16;
    private javax.swing.JLabel cuadro17;
    private javax.swing.JLabel cuadro18;
    private javax.swing.JLabel cuadro19;
    private javax.swing.JLabel cuadro2;
    private javax.swing.JLabel cuadro20;
    private javax.swing.JLabel cuadro21;
    private javax.swing.JLabel cuadro22;
    private javax.swing.JLabel cuadro23;
    private javax.swing.JLabel cuadro24;
    private javax.swing.JLabel cuadro25;
    private javax.swing.JLabel cuadro3;
    private javax.swing.JLabel cuadro4;
    private javax.swing.JLabel cuadro5;
    private javax.swing.JLabel cuadro6;
    private javax.swing.JLabel cuadro7;
    private javax.swing.JLabel cuadro8;
    private javax.swing.JLabel cuadro9;
    private javax.swing.JLabel etiquetaTiempo;
    private javax.swing.JButton guardarJuegoBoton;
    private javax.swing.JButton iniciarJuegobot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label00A;
    private javax.swing.JLabel label00D;
    private javax.swing.JLabel label01A;
    private javax.swing.JLabel label01D;
    private javax.swing.JLabel label02A;
    private javax.swing.JLabel label02D;
    private javax.swing.JLabel label03A;
    private javax.swing.JLabel label03D;
    private javax.swing.JLabel label04A;
    private javax.swing.JLabel label10A;
    private javax.swing.JLabel label10D;
    private javax.swing.JLabel label11A;
    private javax.swing.JLabel label11D;
    private javax.swing.JLabel label12A;
    private javax.swing.JLabel label12D;
    private javax.swing.JLabel label13A;
    private javax.swing.JLabel label13D;
    private javax.swing.JLabel label14A;
    private javax.swing.JLabel label20A;
    private javax.swing.JLabel label20D;
    private javax.swing.JLabel label21A;
    private javax.swing.JLabel label21D;
    private javax.swing.JLabel label22A;
    private javax.swing.JLabel label22D;
    private javax.swing.JLabel label23A;
    private javax.swing.JLabel label23D;
    private javax.swing.JLabel label24A;
    private javax.swing.JLabel label30A;
    private javax.swing.JLabel label30D;
    private javax.swing.JLabel label31A;
    private javax.swing.JLabel label31D;
    private javax.swing.JLabel label32A;
    private javax.swing.JLabel label32D;
    private javax.swing.JLabel label33A;
    private javax.swing.JLabel label33D;
    private javax.swing.JLabel label34A;
    private javax.swing.JLabel label40D;
    private javax.swing.JLabel label41D;
    private javax.swing.JLabel label42D;
    private javax.swing.JLabel label43D;
    private javax.swing.JLabel labelNivel;
    private javax.swing.JTextPane nombreJugador;
    private javax.swing.JButton rehacerBoton;
    private javax.swing.JButton salirJuegoBoton;
    private javax.swing.JButton terminaJuegoBoton;
    private javax.swing.JButton top10Boton;
    // End of variables declaration//GEN-END:variables
}
