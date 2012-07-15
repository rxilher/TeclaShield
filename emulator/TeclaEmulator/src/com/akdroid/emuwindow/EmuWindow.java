/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akdroid.emuwindow;

import com.komodo.socket.tecla.BluetoothEventListener;
import com.komodo.socket.tecla.TeclaSocket;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import teclaemulator.Pinger;
import teclaemulator.server;

/**
 *
 * @author Akhil
 */
public class EmuWindow extends javax.swing.JFrame {

    /**
     * Creates new form EmuWindow
     */
    TeclaSocket sock; //The Socket for communication
    Pinger pin;       // The Pinger to maintain the connection 
    server srv;       // Server to accept bluetooth connections 
    boolean conn;     
    public EmuWindow() {
        initComponents();
        //setframeicon(this,"TeclaEmulator");
        conn=false;
        connect();    //start the server
        Switch.addMouseListener(new MouseListener(){  //Connection and Disconnect Switch

            @Override
            public void mouseClicked(MouseEvent e) { 
               if(sock==null) return;
               conn=sock.isConnected();
               if(conn){
                   Switch.setText("CONNECT");
                   disconnect();
               }
               else{
                   Switch.setText("DISCONNECT");
                   connect();
               } 
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mousePressed(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
        
        });
    }
    public void setsocket(TeclaSocket so){  //sets the socket form the server as and when it connects
        sock=so;
        ecu1.addMouseListener(new TeclaEventListener((byte)0x01,"ECU1",stat,byt,sock));
        ecu2.addMouseListener(new TeclaEventListener((byte)0x02,"ECU2",stat,byt,sock));
        ecu3.addMouseListener(new TeclaEventListener((byte)0x04,"ECU3",stat,byt,sock));
        ecu4.addMouseListener(new TeclaEventListener((byte)0x08,"ECU4",stat,byt,sock));
        e1.addMouseListener(new TeclaEventListener((byte)0x10,"E1",stat,byt,sock));
        e2.addMouseListener(new TeclaEventListener((byte)0x20,"E2",stat,byt,sock));
        Switch.setText("DISCONNECT");
        sock.addBluetoothEventListener(new BluetoothEventListener(){

            @Override
            public void onConnect() {
                
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onDisconnect() {
                pin.end();
                Switch.setText("CONNECT");
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onReceive(DataInputStream datain) { //used for pinger
                try {
                    Byte b=datain.readByte();
                    sock.send(b);
                   // throw new UnsupportedOperationException("Not supported yet.");
                } catch (IOException ex) {
                    Logger.getLogger(EmuWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onSent() {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
        
        });
        
    }
    public void setStatus(String text){
        stat.setText("  "+text);
    }
    public void setState(String state){
        byt.setText("  "+state);
    }
    public void connect(){
        srv=new server(this,pin);
        srv.start();
    }
    public void disconnect(){
        if(sock!=null)
            sock.disconnect();
    }
    public void setframeicon(EmuWindow window, String icon){ 
    try{ 
        InputStream imgStream = this.getClass().getResourceAsStream(System.getProperty("project.path")+"res/emulator.png"); 
        BufferedImage bi = ImageIO.read(imgStream); 
        ImageIcon myImg = new ImageIcon(bi); 
        window.setIconImage(myImg.getImage()); 
        }catch(Exception e){ 
            System.out.println(e); 
        }    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ecu3 = new javax.swing.JButton();
        ecu2 = new javax.swing.JButton();
        ecu4 = new javax.swing.JButton();
        ecu1 = new javax.swing.JButton();
        e1 = new javax.swing.JButton();
        e2 = new javax.swing.JButton();
        stat = new javax.swing.JLabel();
        byt = new javax.swing.JLabel();
        Switch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ecu3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ecu3.setText("ECU 3 /LEFT");

        ecu2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ecu2.setText("ECU2 /DOWN ");

        ecu4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ecu4.setText("ECU4/RIGHT");
        ecu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecu4ActionPerformed(evt);
            }
        });

        ecu1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ecu1.setLabel("ECU1 / UP ");

        e1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        e1.setText("E1/SELECT");

        e2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        e2.setText("E2/CANCEL");

        stat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        stat.setText("Emulator Status: ");
        stat.setAlignmentX(0.5F);
        stat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        byt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        byt.setText("Byte : ");
        byt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Switch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Switch.setText("CONNECT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ecu3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ecu1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ecu2)
                                .addGap(18, 18, 18)
                                .addComponent(ecu4))))
                    .addComponent(stat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Switch, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(e1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(byt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {e1, e2, ecu1, ecu2, ecu3, ecu4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Switch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stat, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(byt, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ecu1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ecu3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ecu2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ecu4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {e1, e2, ecu1, ecu2, ecu3, ecu4});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ecu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecu4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ecu4ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Switch;
    private javax.swing.JLabel byt;
    private javax.swing.JButton e1;
    private javax.swing.JButton e2;
    private javax.swing.JButton ecu1;
    private javax.swing.JButton ecu2;
    private javax.swing.JButton ecu3;
    private javax.swing.JButton ecu4;
    private javax.swing.JLabel stat;
    // End of variables declaration//GEN-END:variables
}
