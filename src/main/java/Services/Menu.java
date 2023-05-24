package Services;


import java.util.Scanner;

public class Menu {

    private static Menu instance = null;
    private static final MainService service = new MainService();

    private Menu()
    {
        service.configureTables();
        service.loadData();
    }

    public static Menu getInstance()
    {
        if(instance == null)
            instance = new Menu();

        return instance;
    }

    private void showMenu()
    {
        System.out.println("-----------------------------------");
        System.out.println("Choose an action:");
        System.out.println("1: Add new artist");
        System.out.println("2: Print all artists");
        System.out.println("3: Print artists sorted alphabetically");
        System.out.println("4: Print artists by a given id");
        System.out.println("5: Update artist followers by a given id");
        System.out.println("6: Delete artist by a given id");
        System.out.println("-----------------------------------");
        System.out.println("7: Add new premiumUser");
        System.out.println("8: Print all premiumUsers");
        System.out.println("9: Print premiumUser by a given id");
        System.out.println("10: Update premiumUser plan by a given id");
        System.out.println("11: Delete premiumUser by a given id");
        System.out.println("0: Exit");
        System.out.println("-----------------------------------");
    }

    public void runMenu()
    {
        showMenu();

        Scanner scan = new Scanner(System.in);
        int opt;
        String ok;

        do
        {
            opt = scan.nextInt();
            scan.nextLine();

            if(opt == 0)
            {
                service.closeConnection();
                System.out.println("Close Program!");
            }

            if(opt == 1)
            {
                service.addArtist();
                System.out.println("-----------------------------------");
            }

            if(opt == 2)
            {
                service.printArtists();
                System.out.println("-----------------------------------");
            }

            if(opt == 3)
            {
                service.printSortedArtists();
                System.out.println("-----------------------------------");
            }

            if(opt == 4)
            {
                service.printArtistById();
                System.out.println("-----------------------------------");
            }

            if(opt == 5)
            {
                service.updateArtist();
                System.out.println("-----------------------------------");
            }

            if(opt == 6)
            {
                service.deleteArtistById();
                System.out.println("-----------------------------------");
            }

            if(opt == 7)
            {
                service.addPremiumUser();
                System.out.println("-----------------------------------");
            }

            if(opt == 8)
            {
                service.printPremiumUsers();
                System.out.println("-----------------------------------");
            }

            if(opt == 9)
            {
                service.printPremiumUserById();
                System.out.println("-----------------------------------");
            }

            if(opt == 10)
            {
                service.updatePremiumUser();
                System.out.println("-----------------------------------");
            }

            if(opt == 11)
            {
                service.deletePremiumUserById();
                System.out.println("-----------------------------------");
            }

            if(opt != 0)
            {
                System.out.println("Do u want another action? yes/no");
                ok = scan.nextLine();
                ok = ok.toLowerCase();

                if(ok.equals("yes"))
                    showMenu();
                else
                {
                    opt = 0;
                    service.closeConnection();
                    System.out.println("Close Program!");
                }
            }
        } while (opt != 0);

        scan.close();
    }
}
























