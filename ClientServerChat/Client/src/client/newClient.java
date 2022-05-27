package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author serhat
 */
public class newClient extends javax.swing.JFrame {

    String username, host = "localhost";
    int port = 5000;
    ArrayList<String> users = new ArrayList();
    Boolean isConnected = false;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

   
     // Create new newClient form 
     
    public newClient() {
        initComponents();
    }


    public class ClientRead implements Runnable {

        @Override
        public void run() {
            String[] data;
            String message;
            PrivateRoom privateRoom = null;
            try {
                while ((message = reader.readLine()) != null) {
                    data = message.split(":");
                    System.out.println(message);
                    if (data[2].equals("Chat")) {
                        msgBox.append(data[0] + ": " + data[1] + "\n");

                    } else if (data[2].equals("Connect")) {
                        msgBox.removeAll();
                        users.add(data[0]); 

                    } else if (data[2].equals("Request")) {
                        DefaultListModel dlm = new DefaultListModel();
                        String[] dataUsers = data[1].split(",");
                        for (String user : dataUsers) {
                            dlm.addElement(user);
                        }
                        jList.setModel(dlm);

                    }else if(data[2].equals("Exist")){
                        JOptionPane.showMessageDialog(null, "This room is already created.");
                        
                    }else if (data[2].equals("Join Room")) {
                        privateRoom = new PrivateRoom();
                        privateRoom.setVisible(true);
                        String[] dataUsers = data[1].split(",");

                        privateRoom.setNames(dataUsers, message);

                    } else if (data[2].equals("Rooms")) {
                        DefaultListModel dlm = new DefaultListModel();
                        String[] dataRooms = data[1].split(",");
                        for (String room : dataRooms) {
                            dlm.addElement(room);
                        }
                        jList.setModel(dlm);

                    } else if (data[2].equals("Private Chat")) {
                        privateRoom.btn_send(message);
                    }
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        textField1 = new java.awt.TextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        btn_connect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        txt_msg = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        txt_room = new javax.swing.JTextField();
        btn_createRoom = new javax.swing.JButton();
        btn_join = new javax.swing.JButton();
        btn_rooms = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 127, 80));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Enter User Name");

        btn_connect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_connect.setText("Connect");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });

        msgBox.setColumns(20);
        msgBox.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        msgBox.setRows(5);
        jScrollPane1.setViewportView(msgBox);

        txt_msg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btn_send.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_send.setText("Send");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList);

        btn_createRoom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_createRoom.setText("Create Room");
        btn_createRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createRoomActionPerformed(evt);
            }
        });

        btn_join.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_join.setText("Join");
        btn_join.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_joinActionPerformed(evt);
            }
        });

        btn_rooms.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_rooms.setText("All Rooms");
        btn_rooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_roomsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_username)
                            .addGap(18, 18, 18)
                            .addComponent(btn_connect)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_rooms, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_createRoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_join, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_room, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_room, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_createRoom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_rooms)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_join))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_msg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_send))
                .addContainerGap(33, Short.MAX_VALUE))
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

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        // TODO add your handling code here:
        if (isConnected == false) {
            if (!users.contains(txt_username.getText())) {

                username = txt_username.getText();
                txt_username.setEditable(false);

                try {
                    isConnected = true;
                    socket = new Socket(host, port);
                    InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                    reader = new BufferedReader(streamreader);
                    writer = new PrintWriter(socket.getOutputStream());
                    writer.println(username + ": has connected :Connect");
                    writer.flush();
                } catch (Exception ex) {
                    msgBox.append("Cannot Connect! Try Again. \n");
                }
                Thread IncomingReader = new Thread(new ClientRead());
                IncomingReader.start();

            } else {
                JOptionPane.showMessageDialog(null, "This name is already taken");
            }

        } else if (isConnected == true) {
            msgBox.append("You are already connected. \n");
        }

    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        // TODO add your handling code here:

        if ((txt_msg.getText()).equals("")) {
            txt_msg.setText("");
        } else {
            try {
                writer.println(username + ":" + txt_msg.getText() + ":" + "Chat");
                writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                msgBox.append("Message was not sent. \n");
            }
            txt_msg.setText("");
        }
        txt_msg.setText("");

    }//GEN-LAST:event_btn_sendActionPerformed

    private void btn_createRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createRoomActionPerformed
        // TODO add your handling code here:
        String name = txt_room.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid name! Please try again!");
        } else {
            writer.println(username + ":" + txt_room.getText() + ":Create Room");
            writer.flush(); // flushes the buffer
        }


    }//GEN-LAST:event_btn_createRoomActionPerformed

    private void btn_roomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_roomsActionPerformed
        // TODO add your handling code here:
        try {
            writer.println(username + ":" + "Show rooms" + ":" + "Rooms");
            writer.flush(); // flushes the buffer
        } catch (Exception ex) {
            msgBox.append("Message was not sent. \n");
        }
    }//GEN-LAST:event_btn_roomsActionPerformed

    private void btn_joinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_joinActionPerformed
        // TODO add your handling code here:     
        try {
            writer.println(username + ":" + jList.getSelectedValue() + ":" + "Join Room");
            writer.flush(); // flushes the buffer      

        } catch (Exception ex) {
            msgBox.append("Message was not sent. \n");
        }

    }//GEN-LAST:event_btn_joinActionPerformed

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
            java.util.logging.Logger.getLogger(newClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new newClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_createRoom;
    private javax.swing.JButton btn_join;
    private javax.swing.JButton btn_rooms;
    private javax.swing.JButton btn_send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea msgBox;
    private java.awt.TextField textField1;
    private javax.swing.JTextField txt_msg;
    private javax.swing.JTextField txt_room;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
