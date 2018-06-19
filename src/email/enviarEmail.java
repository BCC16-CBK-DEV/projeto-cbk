package email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class enviarEmail {  
    
  public static void main(String[] args) {  

      SimpleEmail email = new SimpleEmail();  

      try {  
      email.setDebug(true);  
      email.setHostName("smtp.gmail.com");  
      email.setAuthentication("igor492@gmail.com","casconi12");  
      email.setSSL(true);  
      email.addTo("igor_casconi@hotmail.com"); //pode ser qualquer email  
      email.setFrom("igor492@gmail.com"); //será passado o email que você fará a autenticação //  
      email.setSubject("Pedido de Peça");  
      email.setMsg("Teste de envio de email");  
      email.send();  

      } catch (EmailException e) {  

      System.out.println(e.getMessage());  

      }   

  }  

}
