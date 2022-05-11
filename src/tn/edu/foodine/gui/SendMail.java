package tn.edu.foodine.gui;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public void mail() {

        // Recipient's email ID needs to be mentioned.
        String to = "azzouzhedi48@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "foodine01@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "foodinefoodine");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setContent("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"<head>\n" +
"    <!--[if gte mso 9]>\n" +
"    <xml>\n" +
"        <o:OfficeDocumentSettings>\n" +
"            <o:AllowPNG/>\n" +
"            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"        </o:OfficeDocumentSettings>\n" +
"    </xml>\n" +
"    <![endif]-->\n" +
"    <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\" />\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
"    <meta name=\"format-detection\" content=\"date=no\" />\n" +
"    <meta name=\"format-detection\" content=\"address=no\" />\n" +
"    <meta name=\"format-detection\" content=\"telephone=no\" />\n" +
"    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
"    <!--[if !mso]><!-->\n" +
"    <link href=\"https://fonts.googleapis.com/css?family=Kreon:400,700|Playfair+Display:400,400i,700,700i|Raleway:400,400i,700,700i|Roboto:400,400i,700,700i\" rel=\"stylesheet\" />\n" +
"    <!--<![endif]-->\n" +
"    <title>Email Template</title>\n" +
"    <!--[if gte mso 9]>\n" +
"    <style type=\"text/css\" media=\"all\">\n" +
"        sup { font-size: 100% !important; }\n" +
"    </style>\n" +
"    <![endif]-->\n" +
"\n" +
"\n" +
"    <style type=\"text/css\" media=\"screen\">\n" +
"        /* Linked Styles */\n" +
"        body { padding:0 !important; margin:0 !important; display:block !important; min-width:100% !important; width:100% !important; -webkit-text-size-adjust:none }\n" +
"        a { color:#000001; text-decoration:none }\n" +
"        p { padding:0 !important; margin:0 !important }\n" +
"        img { -ms-interpolation-mode: bicubic; /* Allow smoother rendering of resized image in Internet Explorer */ }\n" +
"        .mcnPreviewText { display: none !important; }\n" +
"        .text-footer2 a { color: #ffffff; }\n" +
"\n" +
"        /* Mobile styles */\n" +
"        @media only screen and (max-device-width: 480px), only screen and (max-width: 480px) {\n" +
"            .mobile-shell { width: 100% !important; min-width: 100% !important; }\n" +
"\n" +
"            .m-center { text-align: center !important; }\n" +
"            .m-left { text-align: left !important; margin-right: auto !important; }\n" +
"\n" +
"            .center { margin: 0 auto !important; }\n" +
"            .content2 { padding: 8px 15px 12px !important; }\n" +
"            .t-left { float: left !important; margin-right: 30px !important; }\n" +
"            .t-left-2  { float: left !important; }\n" +
"\n" +
"            .td { width: 100% !important; min-width: 100% !important; }\n" +
"\n" +
"            .content { padding: 30px 15px !important; }\n" +
"            .section { padding: 30px 15px 0px !important; }\n" +
"\n" +
"            .m-br-15 { height: 15px !important; }\n" +
"            .mpb5 { padding-bottom: 5px !important; }\n" +
"            .mpb15 { padding-bottom: 15px !important; }\n" +
"            .mpb20 { padding-bottom: 20px !important; }\n" +
"            .mpb30 { padding-bottom: 30px !important; }\n" +
"            .m-padder { padding: 0px 15px !important; }\n" +
"            .m-padder2 { padding-left: 15px !important; padding-right: 15px !important; }\n" +
"            .p70 { padding: 30px 0px !important; }\n" +
"            .pt70 { padding-top: 30px !important; }\n" +
"            .p0-15 { padding: 0px 15px !important; }\n" +
"            .p30-15 { padding: 30px 15px !important; }\n" +
"            .p30-15-0 { padding: 30px 15px 0px 15px !important; }\n" +
"            .p0-15-30 { padding: 0px 15px 30px 15px !important; }\n" +
"\n" +
"\n" +
"            .text-footer { text-align: center !important; }\n" +
"\n" +
"            .m-td,\n" +
"            .m-hide { display: none !important; width: 0 !important; height: 0 !important; font-size: 0 !important; line-height: 0 !important; min-height: 0 !important; }\n" +
"\n" +
"            .m-block { display: block !important; }\n" +
"\n" +
"            .fluid-img img { width: 100% !important; max-width: 100% !important; height: auto !important; }\n" +
"\n" +
"            .column,\n" +
"            .column-dir,\n" +
"            .column-top,\n" +
"            .column-empty,\n" +
"            .column-top-30,\n" +
"            .column-top-60,\n" +
"            .column-empty2,\n" +
"            .column-bottom { float: left !important; width: 100% !important; display: block !important; }\n" +
"\n" +
"            .column-empty { padding-bottom: 15px !important; }\n" +
"            .column-empty2 { padding-bottom: 30px !important; }\n" +
"\n" +
"            .content-spacing { width: 15px !important; }\n" +
"        }\n" +
"    </style>\n" +
"</head>\n" +
"<body class=\"body\"style=\"padding:0 !important; margin:0 !important; display:block !important; min-width:100% !important; width:100% !important;  -webkit-text-size-adjust:none;\">\n" +
"<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"    <tr>\n" +
"        <td align=\"center\" valign=\"top\">\n" +
"            <table width=\"650\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"mobile-shell\">\n" +
"                <tr>\n" +
"                    <td class=\"td\" style=\"width:650px; min-width:650px; font-size:0pt; line-height:0pt; padding:0; margin:0; font-weight:normal;\">\n" +
"                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"                            <tr>\n" +
"                                <td bgcolor=\"#ffffff\" class=\"p30-15 img-center\" style=\"padding: 30px; border-radius: 20px 20px 0px 0px; font-size:0pt; line-height:0pt; text-align:center;\"><a href=\"#\" target=\"_blank\"><img src=\"https://i.ibb.co/nLxpyC3/logoo.png\" width=\"244\" height=\"75\" border=\"0\" alt=\"\" /></a></td>\n" +
"                            </tr>\n" +
"                        </table>\n" +
"                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ebebeb\">\n" +
"                            <tr>\n" +
"                                <td class=\"p30-15-0\" style=\"padding: 50px 30px 0px;\" bgcolor=\"#ffffff\">\n" +
"                                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
"\n" +
"                                        <tr>\n" +
"                                            <td class=\"h2-center\"style=\"color:#000000; font-family:'Playfair Display', Times, 'Times New Roman', serif; font-size:32px; line-height:36px; text-align:center; padding-bottom:20px;\">Le chef a ajouté une nouvelle Recette</td>\n" +
"                                        </tr>\n" +
"                                    </table>\n" +
"                                 </td>\n" +
"                              </tr>\n" +
"                           </table>   \n" +
"                        </td>\n" +
"                     </tr>\n" +
"            </table>\n" +
"        </td>\n" +
"     </tr>\n" +
"</table>\n" +
"</body>\n" +
"</html>","text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}