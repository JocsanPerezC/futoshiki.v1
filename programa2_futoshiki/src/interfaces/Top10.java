
package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.JUEGO;


public class Top10 extends javax.swing.JFrame {

    DefaultTableModel dificilAux = new DefaultTableModel();
    DefaultTableModel intermedioAux = new DefaultTableModel();
    DefaultTableModel facilAux = new DefaultTableModel();
    
    ArrayList<String> PersonasMasInfo = new ArrayList();//lista con las personas y sus tiempos
    ArrayList<Integer> MejoresTiempos = new ArrayList();//los SOLO con los mejores tiempos de difiicil
    
       
    public Top10() 
    {
        initComponents();
        setLocationRelativeTo(null);
        
        setModelos();

        listarDificil();
        listarIntermedio();
        listarFacil();
   
    }

    public void setModelos()
    {
        String[] cabecera = {"Nombre", "Tiempo"};
        
        dificilAux.setColumnIdentifiers(cabecera);
        intermedioAux.setColumnIdentifiers(cabecera);
        facilAux.setColumnIdentifiers(cabecera);
        
        tablaDificil.setModel(dificilAux);    
        tablaIntermedio.setModel(intermedioAux);  
        tablaFacil.setModel(facilAux);       
    }

    public void listarDificil()
    {      
        File archivoD = new File("TiemposDificil.dat");
             
        PersonasMasInfo = JUEGO.claseJuego.leerArchivoTop10(archivoD, PersonasMasInfo);//se crea una lista (a base del archivo guardado) con todos los tiempos y el nombre de las personas

        MejoresTiempos = JUEGO.claseJuego.obtenerMejorTop10(MejoresTiempos, PersonasMasInfo);//se crea una lista con los mejores tiempos ordenados
              
        dificilAux.setRowCount(0);
        String [] info = new String [2];
        
        String tiempoTotal;
        
        int posicion = 1;
        for (Integer mejor: MejoresTiempos)//lista con los mejores tiempos oirdenados del menor al mayor
        {
            int tiempoTotalLista = 1;

            for(int g = 1; g <= PersonasMasInfo.size(); g++)//para hacer el ciclo en la lista con el elemento de arriba, se busca ese elemento en las posiciones de la lista de personas
            {
                String indiceT = PersonasMasInfo.get(tiempoTotalLista);//obtengo el elemento que esta en indice del tiempo en la lista de toda la informacion
                indiceT = indiceT.replaceAll("^0+", "");//se quitan los 0 ya que daba un problema

                //System.out.println("========");
                //System.out.println(mejor + " posicion:");
                //System.out.println(indiceT);

                if(String.valueOf(mejor).equals(indiceT))//significa que coinciden los tiempos del mejor con el que se busca en la lista de personas
                {
                    //horas, minutos, segundos
                    tiempoTotal =  PersonasMasInfo.get(tiempoTotalLista + 1) + " : " + PersonasMasInfo.get(tiempoTotalLista + 2) + " : " +  PersonasMasInfo.get(tiempoTotalLista + 3);
                    
                    
                    //System.out.println(tiempoTotal);

                    String nombreP = "" + posicion + "- ";//para la impresion en la tabla
                    nombreP += PersonasMasInfo.get(tiempoTotalLista - 1);//nombre en la lista
                    //System.out.println(nombreP);

                    info[0] = nombreP;
                    info[1] = tiempoTotal; 

                    dificilAux.addRow(info);
                    
                    posicion ++;
                    break;//vuelve a buscar con el siguiente mejor tiempo
                }
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
            }
        }
        MejoresTiempos.clear();
        PersonasMasInfo.clear();
        
    }
    
    public void listarIntermedio()
    {
        File archivoD = new File("TiemposIntermedio.dat");
             
        PersonasMasInfo = JUEGO.claseJuego.leerArchivoTop10(archivoD, PersonasMasInfo);//se crea una lista (a base del archivo guardado) con todos los tiempos y el nombre de las personas

        MejoresTiempos = JUEGO.claseJuego.obtenerMejorTop10(MejoresTiempos, PersonasMasInfo);//se crea una lista con los mejores tiempos ordenados
              
        intermedioAux.setRowCount(0);
        String [] info = new String [2];
        
        String tiempoTotal;
        
        int posicion = 1;
        for (Integer mejor: MejoresTiempos)//lista con los mejores tiempos oirdenados del menor al mayor
        {
            int tiempoTotalLista = 1;

            for(int g = 1; g <= PersonasMasInfo.size(); g++)//para hacer el ciclo en la lista con el elemento de arriba, se busca ese elemento en las posiciones de la lista de personas
            {
                String indiceT = PersonasMasInfo.get(tiempoTotalLista);//obtengo el elemento que esta en indice del tiempo en la lista de toda la informacion
                indiceT = indiceT.replaceAll("^0+", "");//se quitan los 0 ya que daba un problema

                //System.out.println("========");
                //System.out.println(mejor + " posicion:");
                //System.out.println(indiceT);

                if(String.valueOf(mejor).equals(indiceT))//significa que coinciden los tiempos del mejor con el que se busca en la lista de personas
                {
                    //horas, minutos, segundos
                    tiempoTotal =  PersonasMasInfo.get(tiempoTotalLista + 1) + " : " + PersonasMasInfo.get(tiempoTotalLista + 2) + " : " +  PersonasMasInfo.get(tiempoTotalLista + 3);
                    
                    
                    //System.out.println(tiempoTotal);

                    String nombreP = "" + posicion + "- ";//para la impresion en la tabla
                    nombreP += PersonasMasInfo.get(tiempoTotalLista - 1);//nombre en la lista
                    //System.out.println(nombreP);

                    info[0] = nombreP;
                    info[1] = tiempoTotal; 

                    intermedioAux.addRow(info);
                    
                    posicion ++;
                    break;//vuelve a buscar con el siguiente mejor tiempo
                }
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
            }
        }  
        MejoresTiempos.clear();
        PersonasMasInfo.clear();
    }
    
    public void listarFacil()
    {
        File archivoD = new File("TiemposFacil.dat");
             
        PersonasMasInfo = JUEGO.claseJuego.leerArchivoTop10(archivoD, PersonasMasInfo);//se crea una lista (a base del archivo guardado) con todos los tiempos y el nombre de las personas

        MejoresTiempos = JUEGO.claseJuego.obtenerMejorTop10(MejoresTiempos, PersonasMasInfo);//se crea una lista con los mejores tiempos ordenados
              
        facilAux.setRowCount(0);
        String [] info = new String [2];
        
        String tiempoTotal;
        
        int posicion = 1;
        for (Integer mejor: MejoresTiempos)//lista con los mejores tiempos oirdenados del menor al mayor
        {
            int tiempoTotalLista = 1;

            for(int g = 1; g <= PersonasMasInfo.size(); g++)//para hacer el ciclo en la lista con el elemento de arriba, se busca ese elemento en las posiciones de la lista de personas
            {
                String indiceT = PersonasMasInfo.get(tiempoTotalLista);//obtengo el elemento que esta en indice del tiempo en la lista de toda la informacion
                indiceT = indiceT.replaceAll("^0+", "");//se quitan los 0 ya que daba un problema

                //System.out.println("========");
                //System.out.println(mejor + " posicion:");
                //System.out.println(indiceT);

                if(String.valueOf(mejor).equals(indiceT))//significa que coinciden los tiempos del mejor con el que se busca en la lista de personas
                {
                    //horas, minutos, segundos
                    tiempoTotal =  PersonasMasInfo.get(tiempoTotalLista + 1) + " : " + PersonasMasInfo.get(tiempoTotalLista + 2) + " : " +  PersonasMasInfo.get(tiempoTotalLista + 3);
                    
                    
                    //System.out.println(tiempoTotal);

                    String nombreP = "" + posicion + "- ";//para la impresion en la tabla
                    nombreP += PersonasMasInfo.get(tiempoTotalLista - 1);//nombre en la lista
                    //System.out.println(nombreP);

                    info[0] = nombreP;
                    info[1] = tiempoTotal; 

                    facilAux.addRow(info);
                    
                    posicion ++;
                    break;//vuelve a buscar con el siguiente mejor tiempo
                }
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
                tiempoTotalLista ++;
            }
        } 
        MejoresTiempos.clear();
        PersonasMasInfo.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDificil = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaIntermedio = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaFacil = new javax.swing.JTable();
        salir = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TOP 10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaDificil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tablaDificil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jugador", "Tiempo"
            }
        ));
        tablaDificil.setMaximumSize(new java.awt.Dimension(2147483647, 300));
        tablaDificil.setMinimumSize(new java.awt.Dimension(30, 300));
        tablaDificil.setShowHorizontalLines(true);
        tablaDificil.setShowVerticalLines(true);
        jScrollPane2.setViewportView(tablaDificil);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TOP 10 Dificil");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TOP 10 Intermedio");

        tablaIntermedio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tablaIntermedio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jugador", "Tiempo"
            }
        ));
        tablaIntermedio.setMaximumSize(new java.awt.Dimension(2147483647, 300));
        tablaIntermedio.setMinimumSize(new java.awt.Dimension(30, 300));
        tablaIntermedio.setShowHorizontalLines(true);
        tablaIntermedio.setShowVerticalLines(true);
        jScrollPane3.setViewportView(tablaIntermedio);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TOP 10 Facil");

        tablaFacil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tablaFacil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jugador", "Tiempo"
            }
        ));
        tablaFacil.setMaximumSize(new java.awt.Dimension(2147483647, 300));
        tablaFacil.setMinimumSize(new java.awt.Dimension(30, 300));
        tablaFacil.setShowHorizontalLines(true);
        tablaFacil.setShowVerticalLines(true);
        jScrollPane4.setViewportView(tablaFacil);

        salir.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(salir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salir)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

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
            java.util.logging.Logger.getLogger(Top10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Top10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Top10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Top10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Top10().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton salir;
    private javax.swing.JTable tablaDificil;
    private javax.swing.JTable tablaFacil;
    private javax.swing.JTable tablaIntermedio;
    // End of variables declaration//GEN-END:variables
}
