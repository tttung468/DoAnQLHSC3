package com.toanhuuvuong.utils;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils 
{
	private static MailUtils instance = null;
	private Properties props;
	
	private MailUtils() 
	{
		props = resourceBundleToProperties(ResourceBundle.getBundle("mail"));
	}
	public static MailUtils getInstance()
	{
		if(instance == null)
			instance = new MailUtils();
		
		return instance;
	}
	private Properties resourceBundleToProperties(ResourceBundle bundle) 
	{
        Properties properties = new Properties();
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements())
        {
            String key = keys.nextElement();
            properties.put(key, bundle.getString(key));
        }
        return properties;
    }
	public void sendText(String from, String password, String to, String subject, String content) throws MessagingException
	{
		Session session = Session.getDefaultInstance(props, new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(from, password);
			}
		});
		
		MimeMessage message = new MimeMessage(session);
		message.addRecipients(RecipientType.TO, to);
		message.setSubject(subject);
		message.setText(content);
		
		Transport.send(message);
	}
	public void sendHtml(String from, String password, String to, String subject, String content) throws MessagingException
	{
		Session session = Session.getDefaultInstance(props, new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(from, password);
			}
		});
		
		MimeMessage message = new MimeMessage(session);
		message.addRecipients(RecipientType.TO, to);
		message.setSubject(subject);
		message.setContent(content, "text/html");
		
		Transport.send(message);
	}

}
