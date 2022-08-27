
package login;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static login.formUtama.txtnama;
import static login.formUtama.txtnim;
import static login.formUtama.txtpass;

public class formLogin extends javax.swing.JFrame {

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;

    public formLogin() {
        this.setUndecorated(true); // hapus Title Bar
        this.setAlwaysOnTop(true); // selalu di depan
        this.setResizable(false); // Tidakbisa di Resize
        this.setVisible(true);
        initComponents();
        // setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize, ysize);
        this.txtperingatan.setVisible(false);
        // Koneksi ke database
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

    }

    public String tanggal() {

        Date HariSekarang = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss a");

        System.out.println(ft.format(HariSekarang));
        return ft.format(HariSekarang);
    }

    public String getnamepc() {
        String pc = new String();
        try {
            InetAddress id = InetAddress.getLocalHost();
            pc = id.getHostName();
        } catch (UnknownHostException e) {
        }
        return pc;
    }

    private void datatoweb(String a, String b, String c, String d) {
        String active = " ";
        String login = " ";
        String nama = " ";

        try {
            String nim = a;
            String namapc = b;
            String status = c;
            String saran = d;
            // System.out.print(nim+' '+namapc);
            // String url =
            // "http://labtif.poltek-kampar.ac.id/api/historydesktop?nim="+nim+"&namapc="+namapc+"&status="+status;
            String url = "http://localhost/wpu-login/api/historydesktop?nim=" + nim + "&namapc=" + namapc + "&status="
                    + status;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            // add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject obj_JSONObject = new JSONObject(response.toString());
            JSONArray obj_JSONArray = obj_JSONObject.getJSONArray("status_");

            JSONObject Json_out = new JSONObject(response.toString());

            // System.out.println(obj_JSONArray);
            JSONObject obj_JSONObject2 = obj_JSONArray.getJSONObject(0);

            JSONArray address_components_array = obj_JSONObject2.getJSONArray("status2");

            JSONObject obj_JSONObject3 = address_components_array.getJSONObject(0);

            login = obj_JSONObject3.getString("status3");

            System.out.println(getnamepc());
        } catch (Exception e) {
            System.err.println("Ini Error web " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtusername = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();
        txtperingatan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtusername.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtusername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtusername.setCaretColor(new java.awt.Color(255, 255, 255));
        txtusername.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });
        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtusernameKeyTyped(evt);
            }
        });
        getContentPane().add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 400, 30));

        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, 90, 30));

        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, 400, 30));

        txtperingatan.setText("peringatan :");
        getContentPane().add(txtperingatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 560, 430, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Dekstop Login.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -170, 2244, 1111));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtusernameActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtusernameActionPerformed

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtpasswordActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        String login = " ";
        String active = " ";
        String nama = "";

        try {
            String nim = txtusername.getText();
            String password = txtpassword.getText();
            // System.out.print(nim+' '+password);
            // String url =
            // "http://labtif.poltek-kampar.ac.id/api/logindesktop?nim="+nim+"&password="+password;
            String url = "http://localhost/wpu-login/api/logindesktop?nim=" + nim + "&password=" + password;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            // add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject obj_JSONObject = new JSONObject(response.toString());
            JSONArray obj_JSONArray = obj_JSONObject.getJSONArray("status_");

            JSONObject Json_out = new JSONObject(response.toString());
            JSONArray Array_out = Json_out.getJSONArray("data");

            // System.out.println(obj_JSONArray);
            JSONObject obj_JSONObject2 = obj_JSONArray.getJSONObject(0);
            JSONObject Json_out2 = Array_out.getJSONObject(0);

            JSONArray address_components_array = obj_JSONObject2.getJSONArray("status2");
            JSONArray Array_out2 = Json_out2.getJSONArray("data2");

            JSONObject obj_JSONObject3 = address_components_array.getJSONObject(0);
            JSONObject Json_out3 = Array_out2.getJSONObject(0);

            login = obj_JSONObject3.getString("status3");
            active = obj_JSONObject3.getString("log");
            nama = Json_out3.getString("name");

            // Cek Hasil Dari Pemanggilan
            System.out.println("Nama :  " + Json_out3.getString("name"));
            System.out.println("Actvie :  " + Json_out3.getString("log"));
            System.out.println("Waktu :  " + tanggal());
            System.out.println(getnamepc());

            if (login.equals("berhasil") && active.equals("0")) {
                datatoweb("" + nim, getnamepc(), "1", "");
                formUtama start = new formUtama();
                txtnim.setText("" + nim);
                txtnama.setText("" + nama);
                txtpass.setText("" + password);
                start.setVisible(true);
                this.dispose();
            } else if (login.equals("berhasil") && active.equals("1")) {
                String saran;
                saran = JOptionPane.showInputDialog(this, "Masukkan Saran Penggunaan Terakhir Komputer");
                datatoweb(nim, getnamepc(), "0", saran);
            } else {
                // System.out.println("Maaf data Tidak Valid, pengecekan data " +login+ " status
                // active " +active);
                this.txtperingatan.setVisible(true);
                this.txtperingatan.setText("Maaf Data Tidak Valid");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:

    }// GEN-LAST:event_jButton1KeyPressed

    private void txtusernameKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtusernameKeyTyped

    }// GEN-LAST:event_txtusernameKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JLabel txtperingatan;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
