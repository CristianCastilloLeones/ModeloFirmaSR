/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmardocumentos;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
  * @author cl
 */
public class bas {

    private String NombreBD = "BDAirnet";//"BDComiSuper2";"BDComiVillacis";
    public String DireccionBD = "131.196.15.242";//"192.168.0.98";"SAIRCONTROLVILL";
    public String UsuarioBD = "ISP";//"COMI";
    public String ClaveBD = "server@aircontrol@1";//"Server@aircontrol@1";
    private String SentenciaSQL;
    public Connection CanalBD;
    private Statement Instruccion;
    private ResultSet Resultado;
    SQLServerDataSource ds = new SQLServerDataSource();

    public void bas1() {

        //   CanalBD=null;
        try {
            nuevo();
        } catch (SQLException SQLE) {
            JOptionPane.showMessageDialog(null, SQLE.getMessage());
            //    JOptionPane.showMessageDialog(null,"ERROR EN LA CONEXION CON BD\nERROR : " + SQLE.getMessage());
        }

    }

    public void InsertInsertar(String SentenciaSQL) {
        try {
            Connection con = ds.getConnection();

            CallableStatement cstmt = con.prepareCall(SentenciaSQL);
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpdateModificar(String SentenciaSQL) {
        bas1();
        try {
            Connection con = ds.getConnection();

            CallableStatement cstmt = con.prepareCall(SentenciaSQL);
            cstmt.executeQuery();
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void DeleteEliminar(String SentenciaSQL) {
        bas1();
        try {
            Connection con = ds.getConnection();

            CallableStatement cstmt = con.prepareCall(SentenciaSQL);
            cstmt.executeQuery();
        } catch (SQLException e) {

        }
    }

    //-- ESTE METODO DEVUELVE UNA TABLA PARA MOSTRAR
    //-- PERO SI QUIERES LO PUEDES MODIFICAR PARA QUE
    //-- TE DEVUELVA UNA MATRIZ, O LO QUE QUIERAS
    public int SelectConsultar(String SentenciaSQL) {
        bas1();
        int c = 0;
        this.SentenciaSQL = SentenciaSQL;

        //-- ESTO PUEDE VARIAR VARIAR SEGUN A LA BD CON LA QUE TRABAJAS
        try {
            this.Resultado = Instruccion.executeQuery(this.SentenciaSQL);
            c++;

        } catch (SQLException SQLE) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LOS DATOS DE LA BD \n ERROR : " + SQLE.getMessage());
        }

        return c;
    }

    public ResultSet tablas(String x) {
        bas1();
        ResultSet datos = null;
        try {
            Connection con = ds.getConnection();

            CallableStatement cstmt = con.prepareCall(x);
            datos = cstmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return datos;
    }

    public int SelectConsultarf(String SentenciaSQL) {
        int c = 0;
        this.SentenciaSQL = SentenciaSQL;

        //-- ESTO PUEDE VARIAR VARIAR SEGUN A LA BD CON LA QUE TRABAJAS
        try {

            this.Resultado = Instruccion.executeQuery(this.SentenciaSQL);
            ResultSet a = this.Resultado;
            c = Integer.parseInt(a.getString("CANTIDAD"));
        } catch (SQLException SQLE) {
            JOptionPane.showMessageDialog(null, SQLE.getMessage());
            //    JOptionPane.showMessageDialog(null,"ERROR AL CARGAR LOS DATOS DE LA BD \n ERROR : " + SQLE.getMessage());
        }

        return c;
    }

    public void nuevo() throws SQLServerException {

        ds.setUser(UsuarioBD);
        ds.setPassword(ClaveBD);
        ds.setServerName(DireccionBD);
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName(NombreBD);
        ds.getConnection();

    }

    public Connection cone() throws SQLServerException {
        ds.setUser(UsuarioBD);
        ds.setPassword(ClaveBD);
        ds.setServerName(DireccionBD);
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName(NombreBD);
        return ds.getConnection();
    }

    public void email(String receptor, String detalle, boolean fact, String archivo) {
        final String username = "facturacion@airfiber.ec";
        final String password = "pagofacil@2021@mail";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.airfiber.ec");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject("Aviso de Facturacion");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            message.setText(detalle);
            if (fact) {
                BodyPart parteAdjunto = new MimeBodyPart();
                String nomArch = archivo;
                DataSource arch = new FileDataSource(archivo);
                parteAdjunto.setDataHandler(new DataHandler(arch));
                parteAdjunto.setFileName(nomArch);
                Multipart multipart = new MimeMultipart();
                message.setContent(multipart);
                multipart.addBodyPart(parteAdjunto);
            }
            Transport.send(message);
        } catch (Exception e) {

        }
    }

    public void emailVerficaciondeDatos(String detalle) {
        final String username = "facturacion@airfiber.ec";
        final String password = "pagofacil@2021@mail";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.airfiber.ec");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject("Aviso de Facturacion");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("operaciones@airfiber.ec"));
            message.setText("El Cliente " + detalle + " no presenta una direccion correo porfavor condine con el cliente para actualizar los datos.");

            Transport.send(message);
        } catch (Exception e) {

        }
    }

}
