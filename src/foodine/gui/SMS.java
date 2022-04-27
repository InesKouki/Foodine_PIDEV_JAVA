package foodine.gui;

import foodine.entities.User;
import foodine.services.ServiceUtilisateur;
import java.sql.Date;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {

    ServiceUtilisateur su = new ServiceUtilisateur();
    ObservableList<User> usersList;
    String message = null;

    public static final String ACCOUNT_SID = "ACab7ec54e44e881456dd4b2a026236083";
    public static final String AUTH_TOKEN = "a237778eba2048aadca57a0f910adc17";

    public void sendSMS(String name, Date date_deb, Date date_fin){
//        String username = "azizmdk";
//        String password = "9428";
//        String address = "http://192.168.1.2";
//        String port = "8090";
//
//        usersList = su.getAll2();
//
//        for (User user : usersList) {
//            message = "Cher client, ne ratez pas notre nouvel événement [" + name + "] plein de promotions! Du " + date_deb + " jusqu'à " + date_fin;
//            URL url = new URL(
//                    address + ":" + port + "/SendSMS?username=" + username + "&password=" + password
//                    + "&phone=" + user.getPhone() + "&message=" + URLEncoder.encode(message, "UTF-8"));
//
//            URLConnection connection = url.openConnection();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            while ((inputLine = bufferedReader.readLine()) != null) {
//                System.out.println(inputLine);
//            }
//            bufferedReader.close();
//        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        usersList = su.getAll2();

        for (User user : usersList) {
            message = "Cher client, ne ratez pas notre nouvel événement [" + name + "] plein de promotions! Du " + date_deb + " jusqu'à " + date_fin;
            Message msg = Message.creator(new PhoneNumber("+216" + user.getPhone()),
                    new PhoneNumber("+16065546872"),
                    this.message).create();
//            System.out.println(msg.getSid());
        }
    }
}
