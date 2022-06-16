/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmardocumentos;



import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.TransformerException;
import net.sf.jasperreports.engine.JRException;

/**
 *
  * @author cl
 */
public class Firmardocumentos {
   public static String correofinal="";
   public static String  valores ;
   public static String total;
   public static String iva;
   public static String subtotal;
   public static String numerofactura;
   public static String  fechagenerada;
   public static String  claveacceso;
   public static String cedula;
   public static String Direccion;
   public static String Cliente;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, CertificateException, InterruptedException, TransformerException, SQLException, JRException {
      //  principal();
  // bas q = new bas();
   Envio r = new Envio();
   r.setVisible(true);
   //"\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\GENERADOS\\"+claveacceso+".xml" 
   // "\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\galo_alfredo_alava_macas.p12"
   // "Staticpony1990"
   // "\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\FIRMADOS"
   //claveacceso+".xml"
                    
    /*    try {
            try {
                // TODO code application logic here
                
                // JOptionPane.showMessageDialog(null, args[0]+args[1]+args[2]+args[3]+args[4]);
                firmaryenvio(args[0],args[1],args[2],args[3],args[4]);
            } catch (TransformerException ex) {
                Logger.getLogger(Firmardocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | CertificateException ex) {
            JOptionPane.showMessageDialog(null,ex.getCause() );
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null,ex.getCause() );
            Logger.getLogger(Firmardocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    // [nuemrofactura] ,[cliente],[clave],[fechagenerada],[codigocontrato],[correo] ,[total],[iva] ,[subtotal],[direccion]
   /* q.bas1();
        ResultSet facturas =  q.tablas("SELECT * FROM [BDAirnet].[dbo].[tvdjFacurascliente]  where  SRI='NO' and nuemrofactura='000000307'");
        
            while(facturas.next()){
                correofinal=facturas.getString("correo").replace(" ", "");
                String claveacceso=facturas.getString("clave").replace(" ", "");
                 firmaryenvio("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\GENERADOS\\"+claveacceso+".xml"
                         ,"\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\galo_alfredo_alava_macas.p12"
                         ,"Staticpony39","\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\FIRMADOS",claveacceso+".xml");
                 valores="";
                 if(leerxml(claveacceso).contains("Error")){
                     System.out.println("No Autorizado "+claveacceso);
                 }else {
                     valores =leerxml(claveacceso);
               valores=valores+facturas.getString("Datoscliente").replace(":", "").replace("s/n", "").replace("S/N", "");
                ridefactura(valores,claveacceso);
                q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set SRI='SI' where clave='"+claveacceso+"'");
                Thread.sleep(1000);
                 }
                 
    
            } 
        
*/
    }  /*
    public static void firmaryenvio(String xmlPath,String pathSignature,String passSignature,String pathOut,String nameFileOut) throws IOException, CertificateException, InterruptedException, TransformerConfigurationException, TransformerException{
    Autorizacion autorizado = new Autorizacion();
   // System.out.println("Ruta del XML de entrada: " + xmlPath);
   // System.out.println("Ruta Certificado: " + pathSignature);
   // System.out.println("Clave del Certificado: " + passSignature);
   // System.out.println("Ruta de salida del XML: " + pathOut);
   // System.out.println("Nombre del archivo salido: " + nameFileOut);
   // String claveac[]=nameFileOut.split(".");
     
    
        // proceso de firmaelectronica//
        XAdESBESSignature.firmar(xmlPath, pathSignature, passSignature, pathOut, nameFileOut);
         String filePath = pathOut+"\\"+nameFileOut;
      
         String []claveacceso=nameFileOut.split(".xml");
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        System.setProperty("javax.net.ssl.keyStore","C:\\Program Files (x86)\\Java\\jre1.8.0_261\\lib\\security\\cacerts");
        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files (x86)\\Java\\jre1.8.0_261\\lib\\security\\cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        
        // envio de archivos al sri //
        RespuestaSolicitud respuesta = validarComprobante(bytes);
        String estado=respuesta.getEstado();
        Thread.sleep(3000);
        if(estado.equals("DEVUELTA")){
            // System.out.println(respuesta.getEstado()); 
        }else {
             if(estado.equals("RECIBIDA")){
                 autorizado.main(claveacceso[0]); 
             }
       
        }
       
       
         
       
   
     
}
private static RespuestaSolicitud validarComprobante(byte[] xml) {
       RecepcionComprobantesOfflineService service = new RecepcionComprobantesOfflineService();
        RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();
        return port.validarComprobante(xml);
        
    }
 public static String leerxml(String clave) throws InterruptedException{
     SAXBuilder builder = new SAXBuilder();
     System.out.println();
     File xmlFile = new File("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\AUTORIZADOS\\"+clave+".xml");
     
     
	  try {
		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();
		List<Content> soapbody =rootNode.getContent();
                Element sp=(Element) soapbody.get(0);
                Element ns2 =sp.getChildren().get(0);
                Element resouesta =ns2.getChild("RespuestaAutorizacionComprobante");
                Element autori = resouesta.getChild("autorizaciones");
                Element art = autori.getChild("autorizacion");
                Element numero = art.getChild("numeroAutorizacion");
                Element estado =art.getChild("estado");
                if(estado.getValue().contains("NO AUTORIZADO")){
                    
                    return "Error";
                            
                }else {
                  Element fecha =art.getChild("fechaAutorizacion");
                return numero.getValue()+"-"+estado.getValue()+"-"+fecha.getValue().replace("-", "/")+"-";  
                }
               
                } catch (IOException | JDOMException io) {
		
                return "Error";
	  }  
 }
 
 
 public static void ridefactura(String datos,String claveacceso) throws IOException, JRException, SQLServerException{
      bas q = new bas();
      String jr="";
      
         String [] param =datos.split("-");
                                  
                             Map parametro = new HashMap();
                             parametro.put("cliente",param[4].replace("-", ""));
                             parametro.put("direccion",param[6].replace("-", ""));
                             parametro.put("fechaemision",param[3].replace("-", "").replace(" ", ""));
                             parametro.put("cedula",param[7].replace("-", "").replace(" ", ""));
                             parametro.put("claveaacceso",param[2].replace("-", "").replace(" ", ""));
                             parametro.put("fechaautorizacion",param[2].replace("-", "").replace(" ", ""));
                             parametro.put("numerofactura",param[8].replace("-", "").replace(" ", ""));
                             parametro.put("subtotal",param[9].replace("-", "").replace(" ", ""));
                             parametro.put("iva",param[10].replace("-", "").replace(" ", ""));
                             parametro.put("total",param[11].replace("-", "").replace(" ", ""));
                             parametro.put("numeroauto",param[0].replace("-", "").replace(" ", ""));
                             parametro.put("estado",param[1].replace("-", ""));
                            jr = "\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\factura.jasper";
                            String printFileName;
                            printFileName = JasperFillManager.fillReportToFile(jr, parametro,q.cone());
                 pdfg(printFileName,param[4].replace("-", ""),param[8].replace("-", "").replace(" ", ""),correofinal);
          
  }
 public static  void pdfg(String printFileName,String cliente,String numero,String email) throws IOException, JRException{
     bas q = new bas();
     File diretorio= new File("\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\"+cliente.replace(" ", "")+"\\Facturas");
     if(!diretorio.exists())
         diretorio.mkdirs();
      
         
         if (printFileName != null) {
            JasperExportManager.exportReportToPdfFile(printFileName,
               "\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\"+cliente.replace(" ", "")+"\\Facturas\\"+numero+".pdf");
       File objetofile = new File ("\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\"+cliente.replace(" ", "")+"\\Facturas\\"+numero+".pdf");
             System.out.println("archivo generado");
       if(email.contains("@")){
           q.email(email,"Factura generada por AIRNET ISP", true, objetofile.toString());
       }else{
           q.email("","El cliente "+cliente+" no posee un email valido actualizar datos \n ", true, numero);
            }
             System.out.println(cliente);
      //Desktop.getDesktop().open(objetofile);
            }
     
    }
 public static void principal() throws SQLException, IOException, CertificateException, InterruptedException, TransformerException, JRException{
     // 
    bas q= new bas ();
     q.bas1();
        ResultSet facturas =  q.tablas("SELECT [nuemrofactura] ,[cliente],[clave],[fechagenerada],[codigocontrato],[correo] ,[total],[iva] ,[subtotal],[direccion] FROM [BDAirnet].[dbo].[tvdjFacurascliente]  where  SRI='NO' and (fechadepago between '11/12/2020' and '11/12/2020' )");
        
            while(facturas.next()){
                correofinal=facturas.getString("correo").replace(" ", "");
                total=facturas.getString("total").replace(" ", "");
                iva=facturas.getString("iva").replace(" ", "");
                subtotal=facturas.getString("subtotal").replace(" ", "");
                numerofactura=facturas.getString("nuemrofactura").replace(" ", "");
                fechagenerada=facturas.getString("fechagenerada").replace(" ", "");
                claveacceso=facturas.getString("clave").replace(" ", "");
                cedula=facturas.getString("codigocontrato").replace(" ", "");
                Direccion=facturas.getString("direccion");
               Cliente=facturas.getString("cliente");
                 firmaryenvio("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\GENERADOS\\"+claveacceso+".xml"
                         ,"\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\galo_alfredo_alava_macas.p12"
                         ,"Staticpony39","\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\FIRMADOS",claveacceso+".xml");
                 valores="";
                 if(leerxml(claveacceso).contains("Error")){
                     System.out.println("No Autorizado "+claveacceso);
                 }else {
                     valores =valores+leerxml(claveacceso);
               
                ridefacturav2(valores,claveacceso);
                q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set SRI='SI' where clave='"+claveacceso+"'");
                Thread.sleep(1000);
                 }
                 
    
            } 
 }
  public static void ridefacturav2(String datos,String claveacceso) throws IOException, JRException, SQLServerException{
      bas q = new bas();
      String jr="";
    
         String [] param =datos.split("-");
                                  
                             Map parametro = new HashMap();
                             parametro.put("cliente",Cliente);
                             parametro.put("direccion",Direccion);
                             parametro.put("fechaemision",fechagenerada);
                             parametro.put("cedula",cedula);
                             parametro.put("claveaacceso",claveacceso);
                             parametro.put("fechaautorizacion",param[2].replace("-", "").replace(" ", ""));
                             parametro.put("numerofactura",numerofactura);
                             parametro.put("subtotal",subtotal);
                             parametro.put("iva",iva);
                             parametro.put("total",total);
                             parametro.put("numeroauto",param[0].replace("-", "").replace(" ", ""));
                             parametro.put("estado",param[1].replace("-", ""));
                            jr = "\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\factura.jasper";
                            String printFileName;
                            printFileName = JasperFillManager.fillReportToFile(jr, parametro,q.cone());
                 pdfgV2(printFileName,Cliente,numerofactura,correofinal);
          
  }
   public static  void pdfgV2(String printFileName,String cliente,String numero,String email) throws IOException, JRException{
     bas q = new bas();
     File diretorio= new File("\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\"+cliente.replace(" ", "")+"\\Facturas");
     if(!diretorio.exists())
         diretorio.mkdirs();
      
         
         if (printFileName != null) {
            JasperExportManager.exportReportToPdfFile(printFileName,
               "\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\"+cliente.replace(" ", "")+"\\Facturas\\"+numero+".pdf");
       File objetofile = new File ("\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\"+cliente.replace(" ", "")+"\\Facturas\\"+numero+".pdf");
             System.out.println("archivo generado");
       if(email.contains("@")){
           q.email(email,"Factura generada por AIRNET ISP", true, objetofile.toString());
       }else{
           q.email("","El cliente "+cliente+" no posee un email valido actualizar datos \n ", true, numero);
            }
             System.out.println(cliente);
     // Desktop.getDesktop().open(objetofile);
            }
     
    }*/
}
