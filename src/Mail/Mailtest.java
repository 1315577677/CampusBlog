package Mail;

import Enity.User;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import java.util.Properties;

import javax.activation.DataHandler;

import javax.activation.FileDataSource;

import javax.mail.Address;

import javax.mail.BodyPart;

import javax.mail.Multipart;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMultipart;

import javax.mail.internet.MimeUtility;

public class Mailtest {

    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private String smtpServer; // SMTP服务器地址

    private String port; // 端口

    private String username; // 登录SMTP服务器的用户名

    private String password; // 登录SMTP服务器的密码

    private List<String> recipients = new ArrayList<String>(); // 收件人地址集合

    private String subject; // 邮件主题

    private String content; // 邮件正文

    private List<String> attachmentNames = new ArrayList<String>(); // 附件路径信息集合

    public Mailtest() {

    }
    public Mailtest(String smtpServer, String port, String username,
                    String password, List<String> recipients, String subject,
                    String content) {

        this.smtpServer = smtpServer;

        this.port = port;

        this.username = username;

        this.password = password;

        this.recipients = recipients;

        this.subject = subject;

        this.content = content;



    }
    public void setSmtpServer(String smtpServer) {

        this.smtpServer = smtpServer;

    }

    public void setPort(String port) {

        this.port = port;

    }

    public void setUsername(String username) {

        this.username = username;

    }

