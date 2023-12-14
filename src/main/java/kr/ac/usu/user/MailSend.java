package kr.ac.usu.user;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailSend {
	
	
	
	
	public void mailSend(String email,String content) throws IOException {
        Properties props = new Properties();

        try {
        	// SMTP 설정
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.debug", "true");
            props.put("mail.smtp.auth", "true");

            // 인증정보
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getDefaultInstance(props, auth);

            // 메세지 생성
            javax.mail.Message msg = new MimeMessage(session);

            // 보내는 사람 메일주소
            InternetAddress addressFrom = new InternetAddress("wogur951216@gmail.com");
            msg.setFrom(addressFrom);

            InternetAddress addressTo = new InternetAddress(email);
            msg.addRecipient(javax.mail.Message.RecipientType.TO, addressTo);
            msg.setSubject("USU 초기화된 비밀번호입니다.");
            msg.setContent(content, "text/html; charset=utf-8");
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 구글 사용자 메일 계정 아이디/패스 정보
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "wogur951216@gmail.com"; // gmail 사용자;
            String password = "mzyrujcqofypkjvm"; // 패스워드;
            return new PasswordAuthentication(username, password);
        }
    }
}
