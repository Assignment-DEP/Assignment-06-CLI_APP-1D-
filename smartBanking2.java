import java.util.Arrays;
import java.util.Scanner;

public class smartBanking2 {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to smart Banking App";
        final String OPEN_NEW_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DROP_EXISTING_ACCOUNT = "Drop Existing Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);
        
        

        String[][] customer = new String[0][];
        String screen = DASHBOARD;



        do {
            final String APP_TITLE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("-".repeat(40));
            System.out.println(" ".repeat((40 - APP_TITLE.length() + 7)/2).concat(APP_TITLE));
            System.out.println("-".repeat(40));

            switch(screen){
                case DASHBOARD: 
                    System.out.println("\n[1]. Add New Account");
                    System.out.println("[2]. Deposit Money");
                    System.out.println("[3]. Withdraw Money");
                    System.out.println("[4]. Transfer Money");
                    System.out.println("[5]. Check Account Balance");
                    System.out.println("[6]. Drop Existing Account");
                    System.out.println("[7]. Exit\n");
                    System.out.print("Enter an option to continue > ");
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
                String accountNumber =  String.format("CDB-%05d", (customer.length + 1));
                System.out.println("New Account Number: "+accountNumber+"\n");
                
                    do{
                        valid = true;
                        System.out.print("Enter Costomer Name: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()){
                            System.out.printf("%sName can't be empty%s\n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf("%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                valid = false;
                                break;
                            }
                        }
                  
                       if(valid == true) {  
                            System.out.println("Enter your initial Deposit ");
                                try
                                {
                                    depositMoney = SCANNER.nextDouble();  
                                    SCANNER.nextLine();     
                                }
                                catch(Exception e){System.out.println("Not an Valid input...");
                                                    valid = false;
                                                        }
                            if(depositMoney<5000){
                                System.out.println("Initial deposit should be greater than 5000 Rupees");
                                valid= false;
                            
                            }
                        }

                  
                          


                    }while(!valid);

                    String[][] newCustomer = new String[customer.length+1][3];
                    for (int i = 0; i < customer.length; i++) {
                        newCustomer[i] = customer[i];}
                    newCustomer[newCustomer.length - 1][0] = accountNumber;
                    newCustomer[newCustomer.length - 1][1] = name;
                    newCustomer[newCustomer.length - 1][2] = depositMoney+"";
                    customer = newCustomer;

         
                    System.out.println(Arrays.toString(customer));
                   

                    System.out.println();
                    System.out.printf(SUCCESS_MSG,"added sucessfully. Do you want to add new student (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                case DEPOSIT_MONEY:
                valid = true;

                  do { System.out.println("Enter your Account Number  ");
                    accountNumber = SCANNER.nextLine().strip();
                    if(accountNumber.isBlank()){
                        System.out.println("Account number coudn't be empty");
                        valid =false;
                    }
                    if((accountNumber.startsWith("CDB-")) && (accountNumber.length()==10)){
                         System.out.println("Invalid Format");
                        valid = false;
                    }
                  
                    for (int i = 4; i < customer.length; i++) {
                            if(!Character.isDigit(i)){
                                System.out.println("Invalid Format");
                                valid = false;

                            }
                            
                    }
                    
                    for (int i = 0; i < customer.length; i++) {
                        if(!(accountNumber == customer[i])){
                                continue;
                        }
                        else{
                            System.out.println("Account number is "+ accountNumber);
                            System.out.printf("Accoiunt balance : %s.2f",deposit[i]);
                            break;
                        }
                    }
                }while(!valid);
                    
                
                default:
                    System.exit(0);
            }
            
        }while(true);
    }
} 

