package com.imagosur.terminal_autoconsulta.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imagosur.terminal_autoconsulta.mail.*;


@Configuration
public class MailConfig {
	
	// Connect to mail//
	
	@Value("${correo.host}")
	private String host;
	
	@Value("${correo.port}")
	private String port;
	
	@Value("${correo.user}")
	private String user;
	
	@Value("${correo.password}")
	private String password;
	
	// Info to mail //
	
	@Value("${correo.formato}")
	private String formato;
	
	@Value("${correo.asunto}")
	private String asunto;
	
	@Value("${correo.from}")
	private String from;
	
	@Value("${correo.name}")
	private String name;
	
	@Bean
	public MailSender mailSenderSmtpImpl() {
		MailSenderSmtp mail = new MailSenderSmtp(host,port,true,user,password,"","",name);
		mail.setMessage(readLineByLine());
		mail.setSubject(asunto);
		mail.setContent("text/html");
		mail.setFrom(from);
		return mail;
	}
	
   private String readLineByLine(){
       StringBuilder contentBuilder = new StringBuilder();
       try (Stream<String> stream = Files.lines(Paths.get(formato), StandardCharsets.UTF_8))
       {
           stream.forEach(s -> contentBuilder.append(s).append("\n"));
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
       return contentBuilder.toString();
   	}

}
