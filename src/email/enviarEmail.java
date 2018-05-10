/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author igorcasconi
 */
public class enviarEmail {  
    
  public static void main(String[] args) {  

      SimpleEmail email = new SimpleEmail();  

      try {  
      email.setDebug(true);  
      email.setHostName("smtp.gmail.com");  
      email.setAuthentication("nome_de_usuario","senha");  
      email.setSSL(true);  
      email.addTo("destinatario_email"); //pode ser qualquer email  
      email.setFrom("email_gmail"); //será passado o email que você fará a autenticação //  
      email.setSubject("Pedido de Peça");  
      email.setMsg("Teste de envio de email");  
      email.send();  

      } catch (EmailException e) {  

      System.out.println(e.getMessage());  

      }   

  }  

}
