/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import java.io.*;
/**
 *
 * @author PC
 */
public class SimpleCal extends Application
{
    private static final String idle = "-fx-background-color: transparent;";
    private static final String hover = "-fx-background-color: beige;";
    Font font = Font.font("Courier New", FontWeight.BOLD, 20);
    // The GUI elements are declared here, this is my personal habit, you can also declare them inside 
    // overridden start method as you see fit
    VBox main_1 = new VBox();
    GridPane main_1a = new GridPane();
    Label main_2 = new Label("YEAR:");
    TextField main_3 = new TextField();
    Label main_4a = new Label("MONTH:");
    TextField main_4 = new TextField();
    Button main_5 = new Button("CHECK");
    Label main_6 = new Label("SUN MON TUE WED THU  FRI SAT");
    GridPane main_7 = new GridPane();
    TextArea main_9 = new TextArea();

    @Override
    public void start (Stage maiin) 
    {
        try
            {
            //adding a background for the main stage
            Image img = new Image(new FileInputStream("C:\\Users\\PC\\Desktop\\Mobile\\Foodine_PIDEV_Desktop\\src\\img\\hbib.png"),350,364,false,false);
            BackgroundImage bImg = new BackgroundImage(img,
                                                       BackgroundRepeat.NO_REPEAT,
                                                       BackgroundRepeat.NO_REPEAT,
                                                       BackgroundPosition.DEFAULT,
                                                       BackgroundSize.DEFAULT);
            Background bGround = new Background(bImg);

            main_5.setStyle("-fx-border-color: grey;");
            main_3.setBackground(null);
            main_4.setBackground(null);
            main_5.setBackground(null);
            main_2.setFont(font);
            main_4a.setFont(font);
            main_5.setFont(font);
            main_6.setFont(font);
            main_2.setMinWidth(50);
            main_3.setMinWidth(50);
            main_4.setMinWidth(50);
            main_4a.setMinWidth(73);
            main_5.setMinWidth(50);
            main_6.setMinWidth(200);
            main_9.setMaxWidth(350);
            main_1a.add(main_2,0,0);
            main_1a.add(main_3,1,0);
            main_1a.add(main_4a,0,1);
            main_1a.add(main_4,1,1);
            main_1a.add(main_5,2,2);

            main_1.getChildren().addAll(main_1a,main_6,main_7);
            main_1.setBackground(bGround);
            main_5.setOnAction(e -> checkPressed());

            Scene sc = new Scene(main_1,350,364);
            maiin.setScene(sc);
            maiin.setTitle("SimpleCal");
            maiin.show();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Image cannot be found.");
        }
    }
    Button [] main_10;
    private void checkPressed()
    {
        if(!main_3.getText().equals("") && !main_4.getText().equals(""))
        {
            int year = Integer.parseInt(main_3.getText().replaceAll("\\s{1,}",""));
            int month = Integer.parseInt(main_4.getText().replaceAll("\\s{1,}",""));
            if(year > 1800 && month > 0 && month < 13)
            {
                CST curr_calen = new CST();
                int cp_totalDay = curr_calen.getNumOfDaysInMonth(year,month);
                int cp_startDay = curr_calen.startDay(year,month);
                main_10 = new Button [31];
                for(int i = 0; i < 31; i++)
                    main_10[i] = new Button(""+i);
                
                for(int j = 0; j < 31; j++)
                {
                    main_10[j].setMinWidth(50);
                    main_10[j].setMinHeight(50);
                    main_10[j].setBackground(null);
                }
                main_7.getChildren().clear();
                int day_ctrl = 0;
                for(int g = cp_startDay; g < 7; g++)
                {
                    main_7.add(main_10[day_ctrl],g,0);
                    day_ctrl++;
                }
                int row_ctrl = (int)(Math.ceil((cp_totalDay - day_ctrl)/7));
                for(int z = 1; z <= row_ctrl+1; z++)
                {
                    for(int y = 0; y < 7; y++)
                    {
                        if(day_ctrl < cp_totalDay)
                        {
                            main_7.add(main_10[day_ctrl],y,z);
                            day_ctrl++;
                        }
                    }
                }
                
                String ramp;
                if(month < 10)
                {
                    ramp = year + "0" + month + "";
                }
                else
                {
                    ramp = year + "" + month + "";
                }
                for(int y = 0; y < 31; y++)
                    setMouse(y);
                for(int y = 0; y < 31; y++)
                {
                    String mess2;
                    if(y < 10)
                        mess2 = "0" + y;
                    else
                        mess2 = "" + y;
                    setAct(ramp + mess2, y);
                }
            }
        }
    }
    
    // 2022 April 8 fix:
    // use function call to perform event action can let us deal with different elements in the array without repeated lines
    // makes the code clean and neat
    private void setMouse(int u)
    {
        main_10[u].setOnMouseEntered(e -> main_10[u].setStyle(hover));
        main_10[u].setOnMouseExited(e -> main_10[u].setStyle(idle));
    }
    
    private void setAct(String ramp, int y)
    {
        main_10[y].setOnAction(e -> notesOpen(ramp));
    }
    
    Button main_11 = new Button("Update");
    private void notesOpen(String temp) 
    {
        try
        {
            FileInputStream fis = new FileInputStream("userNotes/image1.jpg");
            Image img = new Image(fis,350,300,false,false);
            BackgroundImage bImg = new BackgroundImage(img,
                                                       BackgroundRepeat.NO_REPEAT,
                                                       BackgroundRepeat.NO_REPEAT,
                                                       BackgroundPosition.DEFAULT,
                                                       BackgroundSize.DEFAULT);
            Background bGround = new Background(bImg);
            main_9.setWrapText(true);
            main_11.setBackground(null);
            main_9.setFont(font);
            main_11.setFont(font);
            System.out.println(temp);
            String show = "";
            int count = 0; 
            try
            (
                BufferedReader ip = new BufferedReader(new FileReader("userNotes\\" + temp + ".txt"));
            )
            {
                while(ip.readLine() != null)
                {
                    count++;
                }
                System.out.println("The number of line in file " + temp + " is: " + count);
                String [] content = new String[count];
                try
                (
                    BufferedReader ip2 = new BufferedReader(new FileReader("userNotes\\" + temp + ".txt"));
                )
                {
                    for(int p = 0; p < count; p++)
                    {
                        content[p] = ip2.readLine();
                    }
                }
                catch(FileNotFoundException ex)
                {
                    System.out.println("Read file failed. (file not found)");
                }
                catch(IOException ox)
                {
                    System.out.println("Read file failed. (corrupt file)");
                }
                for(int o = 0; o < count; o++)
                {
                    show += content[o] + "\n";
                }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("Read file failed. (file not found)");
                System.out.print("Create new file now: ");
                fileCreation(temp+".txt");
            }
            catch(IOException ox)
            {
                System.out.println("Read file failed. (corrupt file)");
                System.out.print("Create new file now: ");
                fileCreation(temp+".txt");
            }
            main_11.setOnAction(e -> updatePressed(temp+".txt"));
            main_9.setText(show);
            VBox second2 = new VBox();
            second2.getChildren().addAll(main_9,main_11);
            second2.setBackground(bGround);
            Stage second = new Stage();
            Scene sc2 = new Scene(second2, 350, 300);
            second.setTitle("Notes");
            second.setScene(sc2);
            second.show();
        }
        catch(FileNotFoundException ex)
        {}
    }
    
    private void updatePressed(String path)
    {
        int rows_in_textArea = main_9.getText().split("\n").length;
        System.out.println(rows_in_textArea);
        try 
        {
            File fout = new File("userNotes\\" + path);
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (String line : main_9.getText().split("\n"))
            {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Fail to write into file:" + ex);
        }   
    }
    
    private void fileCreation(String path)
    {
        // launched when the file cannot be found orhas IOException, which the later is usually not the case
        try
        {
            File new_file = new File("userNotes\\" + path);
            if(new_file.createNewFile())
            {
                System.out.println("New file created.");
            }
            else
                System.out.println("New file not created.");
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
    public static void main (String [] args)
    {
        Application.launch();
    }
}



class CST 
{
    CST()
    {}
    public boolean isLeapYear(int year)
    {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }
    public int getTotalNumOfDays(int year, int month)
    {
        int total = 0;
        for(int i = 1800; i < year; i++)
        {
            if(isLeapYear(i)) total += 366;
            else total += 365;
        }
        for(int i = 1; i < month; i++)
        {
            total += getNumOfDaysInMonth(year, i);
        }
        return total;
    }
    public int getNumOfDaysInMonth(int year, int month)
    {
        if(month == 1 ||month == 3 ||month == 5 ||month == 7 ||month == 8 ||month == 10 ||month == 12)
            return 31;
        if(month == 4 ||month == 6 ||month == 9 ||month == 11)
            return 30;
        else
        {
            if(isLeapYear(year)) 
                return 29;
            else 
                return 28;
        }
    }
    public int startDay(int year, int month)
    {
        System.out.println("Start day:" + (getTotalNumOfDays(year,month) + 3)%7);
        return (getTotalNumOfDays(year,month) + 3)%7;
    }
}
