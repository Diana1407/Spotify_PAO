//package Services;
//
//import Entities.App;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class AppService {
//    private static AppService instance;
//
//    private AppService(){}
//
//    public static AppService getInstance(){
//        if(instance==null)
//        {
//            instance = new AppService();
//        }
//        return instance;
//    }
//
//    public App readApp() throws ParseException{
//        Scanner scanner = new Scanner(System.in);
//        /// aici ar trb chemate celelalte servicii
//        App app = new App();
//        System.out.println("Id");
//
//        try{
//            app.setId(scanner.nextInt());
//        } catch (Exception e){
//            System.out.println("Enter a number");
//            app.setId(scanner.nextInt());
//        }
//
//        System.out.println("Songs");
//        ArrayList
//
//    }
//
//}
