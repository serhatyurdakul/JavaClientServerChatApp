import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/*
 *
 * @author serhat
 */
public class Server extends javax.swing.JFrame {

    ArrayList clientOutputStreams = new ArrayList<>();//client outputs
    ArrayList<String> users = new ArrayList<>(); //Sunucuya Baglanan Kullanicilar
    ArrayList<String> rooms = new ArrayList<>();// Client tarafından olusturulan odalar
    ArrayList<String> roomUsers = new ArrayList<>();//Client tarafindan olusuturlan Oda Kullanicilari

    public class ClientHandler implements Runnable {

        Socket socket;
        BufferedReader reader;
        PrintWriter client;

        public ClientHandler(Socket cSocket, PrintWriter user) {
            try {
                client = user;
                socket = cSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception ex) {
                msgBox.append("Unexpected error... \n");
            }
        }

        public void run() {
            String[] data;
            String message;

            try {
                while ((message = reader.readLine()) != null) {
                    data = message.split(":");
                    msgBox.append(data[0] + ": " + data[1] + "\n");

                    if (data[2].equals("Connect")) {
                        sendMessage((data[0] + ":" + data[1] + ":" + "Chat"));
                        String msg;
                        users.add(data[0]);
                        for (String user : users) {
                            msg = (user + ": :Connect");
                            sendMessage(msg);
                        }

                    } else if (data[2].equals("Chat")) {
                        sendMessage(message);
                    } else if (data[2].equals("Request")) {

                        int userID;
                        StringBuilder stringBuilder = new StringBuilder();

                        for (String current_user : users) {
                            stringBuilder.append(current_user).append(",");
                        }

                        userID = users.indexOf(data[0]);
                        String finalString = stringBuilder.toString();
                        finalString = data[0] + ":" + finalString + ":" + "Request";
                        sendOneClient(finalString, userID, data[0]); // d[0] bu durumda gönderenin kendisi oldugu alici kisidir

                    } else if (data[2].equals("Create Room")) {
                        if (rooms.contains(data[1])) {
                            sendMessage((data[0] + ":" + " has created " + data[1] + " room." + ":" + "Exist"));
                        } else {
                            sendMessage((data[0] + ":" + " has created " + data[1] + " room." + ":" + "Chat"));
                            rooms.add(data[1]);
                        }

                    } else if (data[2].equals("Rooms")) {
                        int userID;
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String room : rooms) {
                            stringBuilder.append(room).append(",");
                        }

                        userID = users.indexOf(data[0]);
                        String str = stringBuilder.toString();
                        str = data[0] + ":" + str + ":" + "Rooms";
                        sendOneClient(str, userID, data[0]);

                    } else if (data[2].equals("Join Room")) {
                        sendMessage((data[0] + ":joined " + data[1] + ":" + "Chat"));
                        int userID;
                        roomUsers.add(data[0]);

                        StringBuilder stringBuilder = new StringBuilder();
                        for (String rUser : roomUsers) {
                            stringBuilder.append(rUser).append(",");
                        }
                        userID = users.indexOf(data[0]);
                        String str = stringBuilder.toString();
                        String finalString = data[0] + "," + data[1] + ":" + str + ":" + "Join Room";
                        sendOneClient(finalString, userID, data[0]);

                    } else if (data[2].equals("Private Chat")) {
                        sendMessage(data[0] + ":" + data[1] + ":" + "Private Chat");

                    } else {
                        msgBox.append("No Conditions were met. \n");
                    }
                }
            } catch (Exception ex) {
                msgBox.append("Lost the connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
            }
        }

    }

   
     // Creates Server  new form ü
   
    public Server() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        btn_start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 127, 80));

        msgBox.setColumns(20);
        msgBox.setRows(5);
        jScrollPane1.setViewportView(msgBox);

        btn_start.setText("Start Server");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_start)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_start)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        // TODO add your handling code here:
        Thread starter = new Thread(new ServerStart());
        msgBox.append("Sever Started.\n");
        starter.start();
    }//GEN-LAST:event_btn_startActionPerformed

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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    public class ServerStart implements Runnable {

        @Override
        public void run() {

            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                while (true) {
                    Socket clientSock = serverSocket.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);

                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
                }
            } catch (Exception ex) {
                msgBox.append("Connection Error! \n");
            }
        }
    }

    public void sendOneClient(String msg, int id, String recievername) {
        if (clientOutputStreams.get(id) != null) {

            try {
                PrintWriter writer = (PrintWriter) clientOutputStreams.get(id);
                writer.println(msg);
                writer.flush();
                msgBox.append("Sending to " + recievername + " this msg :  " + msg + "\n");

            } catch (Exception ex) {
                msgBox.append("Error " + recievername + "." + "\n");
            }
        } else {
            msgBox.append("Error, ID not found  " + recievername + "." + "\n");
        }
    }

    public void sendRoomUsers(String msg, ArrayList<String> users) {

        for (String user : users) {
            System.out.println(user + ",");
            try {
                PrintWriter writer = (PrintWriter) clientOutputStreams.get(users.indexOf(user));
                writer.println(msg);
                writer.flush();

            } catch (Exception ex) {
                msgBox.append("Error \n");
            }
        }
    }

    public void sendMessage(String message) {
        Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();

            } catch (Exception ex) {
                msgBox.append("Error sending message. \n");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_start;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgBox;
    // End of variables declaration//GEN-END:variables
}
