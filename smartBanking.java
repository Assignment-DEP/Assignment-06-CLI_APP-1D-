import java.util.Arrays;
import java.util.Scanner;

public class smartBanking {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to smart Banking App";
        final String OPEN_NEW_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DROP_EXISTING_ACCOUNT = "Drop Existing Account";
        
        

        String[] costomer = new String[0];
        String screen = DASHBOARD;
        double[] deposit = new double[0];
        String[] accountNumbers = new String[0];

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
                String accountNumber =  String.format("CDB-%05d \n", (costomer.length + 1));
                System.out.printf("New Account Number: "+accountNumber);
                
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

                  String[] newAccountNumbers = new String[accountNumbers.length + 1];
                    for (int i = 0; i < costomer.length; i++) {
                        newAccountNumbers[i] = accountNumbers[i];
                    }
                    newAccountNumbers[newAccountNumbers.length -1] = accountNumber;
                    accountNumbers = newAccountNumbers;

                    String[] newCostomer = new String[costomer.length + 1];
                    for (int i = 0; i < costomer.length; i++) {
                        newCostomer[i] = costomer[i];
                    }
                    newCostomer[newCostomer.length -1] = name;
                    costomer = newCostomer;

                    double[] newDeposit = new double[deposit.length + 1];
                    for (int i = 0; i < deposit.length; i++) {
                        newDeposit[i] = deposit[i];
                    }
                    newDeposit[newDeposit.length -1] = depositMoney;
                    deposit = newDeposit;
                    System.out.println(Arrays.toString(accountNumbers));
                    System.out.println(Arrays.toString(deposit));
                    System.out.println(Arrays.toString(costomer));

                    System.out.println();
                    System.out.print(name+" added sucessfully. Do you want to add new student (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                case DEPOSIT_MONEY:

                    System.out.println("Enter your Account Number  ");

                default:
                    System.exit(0);
            }
            
        }while(true);
    }
} 
