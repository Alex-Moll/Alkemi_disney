package com.disney.demo.service.impl;

import com.disney.demo.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    // aqui le vamos a enviar la API key
    @Autowired
    private Environment env;
    
    // estas Value estan en el aplication.properties
    @Value("${alkemy.icons.email.sender}")
    private String emailSender;
    
    @Value("${alkemy.icons.email.enabled}")
    private boolean enabled;
       
    @Override
    // en el to guardo la direccion del nuevo destinatario
    public void sendWelcomeEmailTo(String to) {
        
        // si el booleano llega en false, se corta la ejecucion
        if(!enabled){
            return;
        }
        
        //aqui vamos a cargar la clave que nos da sendgrid
        String apiKey = env.getProperty("EMAIL_API_KEY");
        
        // en fromEmail va quien lo envia
        Email fromEmail = new Email(emailSender);
        
        // en el toEmail va quien lo recibe
        Email toEmail = new Email(to);
        
        //en content cargamos el cuerpo del mensaje
        Content content = new Content("text/plain", "Contenido del cuerpo del email, \nBienvenida/o a PiedraMora");
        
        // en subject ponemos el asunto
        String subject = "Alkemy Icons";
        
        //                      desde    asunto   para    contenido
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        
        SendGrid sg = new SendGrid(apiKey);
        
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            
        }catch(IOException ex){
            System.out.println("Error trying to send the mail");
        }
        
        
    }
    
}
