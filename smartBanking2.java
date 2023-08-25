import java.util.Arrays;
import java.util.Scanner;

public class smartBanking2 {
        private static final Scanner SCANNER = new Scanner(System.in);
        final static String CLEAR = "\033[H\033[2J";
        final static String COLOR_BLUE_BOLD = "\033[34;1m";
        final static String COLOR_GREEN_BOLD = "\033[33;1m";
        final static String COLOR_RED_BOLD = "\033[31;1m";
        final static String RESET = "\033[0m";

        final static String DASHBOARD = "Welcome to smart Banking App";
        final static String OPEN_NEW_ACCOUNT = "Open New Account";
        final static String DEPOSIT_MONEY = "Deposit Money";
        final static String WITHDRAW_MONEY = "Withdraw Money";
        final static String TRANSFER_MONEY = "Transfer Money";
        final static String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final static String DROP_EXISTING_ACCOUNT = "Drop Existing Account";
        static String screen;
        final static String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final static String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);


        static String[][] customer = {{"CDB-00001","NISHADA","76543"},
        {"CDB-00002","SHALINA","79543"},
        {"CDB-00003","KALPADEEEEP","96543"}};

    public static void main(String[] args) {
        screen = DASHBOARD;
        do {
            final String APP_TITLE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t".concat("-".repeat(40)));
            System.out.println("\t".concat(" ".repeat((40 - APP_TITLE.length() + 7)/2).concat(APP_TITLE)));
            System.out.println("\t".concat("-".repeat(40)));

            switch(screen){
                case DASHBOARD: 
                    System.out.println("\n\t[1]. Add New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an option to continue > ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen = OPEN_NEW_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY;break;
                        case 4: screen = TRANSFER_MONEY;break;
                        case 5: screen = CHECK_ACCOUNT_BALANCE;break;
                        case 6: screen = DROP_EXISTING_ACCOUNT;break;
                        case 7:System.exit(0); break;
                        default: continue;
                    }
                    break;
                case OPEN_NEW_ACCOUNT:
                boolean valid;
                String name;
                double depositMoney=0;
                // AccountNumber
                String accountNumber =  String.format("CDB-%05d", (customer.length + 1));
                System.out.println("\tNew Account Number: "+accountNumber+"\n");
                
            loop:do{ //Name
                        valid = true;
                        System.out.print("\tEnter Costomer Name: ");
                        name = SCANNER.nextLine().strip();

                        // Empty
                        if (name.isBlank()){
                            System.out.printf("%sName can't be empty%s\n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }
                        // Format
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf("%s\tInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                valid = false;
                                continue loop;
                            }
                        }
                        // Deposit
                        if(valid == true)
                        {  
                            do{valid = true;
                                System.out.print("\tEnter your initial Deposit ");
                                try
                                {
                                    depositMoney = SCANNER.nextDouble();  
                                         
                                }
                                catch(Exception e){System.out.printf(ERROR_MSG,"Not an Valid input...");
                                valid = false; 
                              }
                              SCANNER.nextLine();
                                               
                            }while(!valid);                            
                            
                        }
                        // check initial Deposit  
                        if(depositMoney<5000){
                            System.out.printf(ERROR_MSG,"Initial deposit should be greater than 5000 Rupees");
                            valid= false;                            
                        }                        
                    }while(!valid);
                    // Adding new customer to Array
                    String[][] newCustomer = new String[customer.length+1][3];
                    for (int i = 0; i < customer.length; i++) {
                        newCustomer[i] = customer[i];}
                    newCustomer[newCustomer.length - 1][0] = accountNumber;
                    newCustomer[newCustomer.length - 1][1] = name;
                    newCustomer[newCustomer.length - 1][2] = depositMoney+"";
                    customer = newCustomer;

         
                    System.out.println("\t"+Arrays.toString(customer[customer.length-1]));
                   
                    // check whether want to continue or not
                    System.out.println();
                    System.out.printf(SUCCESS_MSG,"added sucessfully. Do you want to add new customer (Y/n)? ");
                    System.out.print("\t");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                case DEPOSIT_MONEY:
                 int j = accountValidation();
                        System.out.printf("\tCurrent Balance : Rs%,.2f\n",Double.valueOf(customer[j][2]));
                        double deposit;
                        do{
                            valid = true;
                            System.out.print("\tDeposit Amount : ");
                            deposit = SCANNER.nextInt();
                            SCANNER.nextLine();

                            if(deposit<500){
                                System.out.printf(ERROR_MSG,"Deposit should be greater than Rs.500.00");
                                valid = false;
                            }
                        }while(!valid);

                        double total = Double.valueOf(customer[j][2])+deposit;
                        customer[j][2]= total+"";
                        System.out.printf("\tNew Balance : Rs%,.2f\n",Double.valueOf(customer[j][2]));

                         System.out.printf(SUCCESS_MSG,"added sucessfully. Do you want to continue(Y/n)? ");
                        if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                        screen = DASHBOARD;
                        break;
                        
                case WITHDRAW_MONEY:
                   
                    j = accountValidation();
                       
                        System.out.printf("\tCurrent Balance : Rs%,.2f\n",Double.valueOf(customer[j][2]));
                        System.out.print("\tEnter ");
                        double withdraw = withdraw(j);
                       

                        total = Double.valueOf(customer[j][2])-withdraw;
                        customer[j][2]= total+"";
                        System.out.printf("\tNew Balance : Rs%,.2f\n",Double.valueOf(customer[j][2]));

                        System.out.printf(SUCCESS_MSG,"Withdraw sucessfully. Do you want to continue(Y/n)? ");
                        System.out.print("\t");
                        if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                        screen = DASHBOARD;
                        break;

                case TRANSFER_MONEY:
                int j1=-1;;
                   do{valid = true; 
                    System.out.print("Transfer phase-->\n");
                    j = accountValidation();
                    if(j==-1) break;

                    System.out.print("\b".repeat(30).concat("Reciver phase -->\n"));
                    j1 = accountValidation();
                    if(j1==-1) break;
                    if(j==j1){
                        {System.out.println();
                            System.out.printf(ERROR_MSG,"Transfer and Reciver Account shouldn't be same. \n");
                        valid = false;}
                    }}while(!valid);

                    System.out.println();
                    System.out.println("\tFrom Account Name : " +customer[j1][1]);
                    System.out.printf("\tCurrent Balance   : Rs%,.2f\n",Double.valueOf(customer[j1][2]));
                    System.out.println("\tTo Account Name   : " +customer[j][1]);
                    System.out.printf("\tCurrent Balance   : Rs%,.2f\n",Double.valueOf(customer[j][2]));
                    System.out.println();
                    System.out.print("\tTransfer ");
                    double transferAmount = withdraw(j);
                    total = Double.valueOf(customer[j][2])-transferAmount;
                    customer[j][2]= total+"";
                    System.out.printf("\tNew From Account Balance : Rs%,.2f\n",Double.valueOf(customer[j][2]));
                    total = Double.valueOf(customer[j1][2])+transferAmount;
                    customer[j][2]= total+"";
                    System.out.printf("\tNew To Account Balance : Rs%,.2f\n",Double.valueOf(customer[j][2]));
                    System.out.printf(SUCCESS_MSG,"added sucessfully. Do you want to continue(Y/n)? ");
                    System.out.printf("\t");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                        screen = DASHBOARD;
                        break;


                     
                   

                case CHECK_ACCOUNT_BALANCE:
                j = accountValidation();
                System.out.println("\tName : " +customer[j][1]);
                System.out.printf("\tCurrent Account Balance : Rs%,.2f\n",Double.valueOf(customer[j][2]));
                System.out.printf("\tAvalible Account Balance : Rs%,.2f\n",(Double.valueOf(customer[j][2])-500));
                System.out.printf(SUCCESS_MSG,"Do you want to continue(Y/n)? ");
                System.out.print("\t");
                if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                screen = DASHBOARD;
                break;

                case DROP_EXISTING_ACCOUNT:
                
                j = accountValidation();
                String[][] removedCustomer = new String[customer.length-1][2];
                int k = 0;

                

                for (int i = 0; i < customer.length; i++) {
                    if(i == j){
                        System.out.println(j);
                        continue;
                    }
                    removedCustomer[k] = customer[i];
                    
                    k++;

                    
                }
                customer=removedCustomer;
                for (String[] strings : customer) {
                    System.out.println(Arrays.toString(strings));
                }
                System.out.printf(SUCCESS_MSG,"Removed sucessfully. Do you want to continue(Y/n)? ");
                System.out.printf("\t");
                if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                screen = DASHBOARD;
                 break;

                

                default:
                    System.exit(0);
            }
            
        }while(true);
    }


    public static int accountValidation(){
        String accountNumber;
         boolean valid;
        int j = 0;
                        do{valid = true;
                        System.out.print("\tEnter Account Number  ");
                        accountNumber = SCANNER.nextLine().strip();
                        
                            /* Empty */
                            if (accountNumber.isEmpty()) {
                                valid = false;
                                System.out.printf(ERROR_MSG, "Account number Can't be empty");
                                continue;
                            }

                            /* Format */
                            else if (!accountNumber.startsWith("CDB-") || accountNumber.length() != 9) {
                                valid = false;
                                System.out.printf(ERROR_MSG, "Invalid Account number format");
                                continue;
                            } 
                            else{
                                String number = accountNumber.substring(4);
                                for (int i = 0; i < number.length(); i++) {
                                    if (!Character.isDigit(number.charAt(i))){
                                        System.out.printf(ERROR_MSG, "Invalid Account number format");
                                        valid = false;
                                        break;
                                    }
                                }
                                boolean exists = false;
                                for (j = 0; j < customer.length; j++) {
                                    if (customer[j][0].equals(accountNumber)){
                                        exists = true;
                                        break;
                                    }
                                }    
                                if (!exists){
                                    valid = false;
                                    System.out.printf(ERROR_MSG, "Account Number does not exist");
                                }
                                
                            }
                            if (!valid) {
                                System.out.print("\n\tDo you want to try again? (Y/n)");
                                if (!SCANNER.nextLine().strip().toUpperCase().equals("Y")){
                                    screen = DASHBOARD;
                                    j=-1;
                                    break;
                                }
                                System.out.println();
                            }
                        } while (!valid);
                     
                        
                        return j;

    }
    public static double withdraw(int j){
        double withdraw = 0.0;
        boolean valid;
        do{
           do{valid = true;
                System.out.print("Amount : ");
                try
                    {
                    withdraw = SCANNER.nextDouble();  
                    SCANNER.nextLine();     
                     }
                catch(Exception e){System.out.println("\tNot an Valid input...");
                        valid = false; }                        
                }while(!valid);

            if(withdraw>Double.valueOf(customer[j][2])-1000){
                System.out.printf(ERROR_MSG,"Account Balance should be greater than Rs.1000.00");
                System.out.print("\tEnter");
                valid = false;
            }

         }while(!valid);
        return withdraw;
    }
} 

