import java.util.*;
import java.text.*;
import java.io.*;
class FakeOS{
    // VARIABLES
    // Current Version
    static String version = " v1.6";
    // Main Directory
    static String mainDirectory = "";
    static String mainDirectory2 = "";
    // Scanners
	static Scanner scanner = new Scanner(System.in);
    static Scanner fileScanner;
    static PrintStream fileWriter;
    // Text Colors
    static String ANSI_RESET = "\u001B[0m";
    static String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    static String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    static String ANSI_RED = "\u001B[31m";
    static String ANSI_GREEN = "\u001B[32m";
    static String ANSI_YELLOW = "\u001B[33m";
	static String ANSI_BLUE = "\u001B[34m";
	static String ANSI_CYAN = "\u001B[36m";
    static String ANSI_PURPLE = "\u001b[37m";
	static String ANSI_WHITE = "\u001B[37m";
    // APP VARIABLES
    // --Security--
    static boolean boughtSAV = false;   // Super Anti-Virus
    static boolean boughtUAV = false;   // Ultra Anti-Virus
    static boolean boughtNFTE = false;  // NFT Encrpter
    static boolean boughtNFtI = false;  // NFt InkrIpter
    static double chance = 0.8;
    static boolean ignoredUpdate = false;
    static int password = 0;
    // --FakeCoin--
    static double fakeCoin = 0.0;
    static Stock fakeInc = new Stock("FakeInc", "FIC", 10, 1, false);
    static Stock fakeCorp = new Stock("FakeCorp", "FCO", 12, 0.5, false);
    static Stock fackeInk = new Stock("FackeInk", "FIK", 10, -0.5, false);
    static Stock fakeOS = new Stock("FakeOS", "FOS", 8, 1.5, false);
    static Stock nftCorp = new Stock("NFTCorp", "NFT", 15, -2, false);
    static Stock[] stockList = {fakeInc, fakeCorp, fackeInk, fakeOS, nftCorp};
    // --NFTs--
    static NFT sword = new NFT("Sword", getFileContents("sword"),
        10, 5, false);
    static NFT java = new NFT("Java Text", getFileContents("java-text"),
        20, 10, false);
    static NFT customNFT = new NFT("Custom NFT",
        "Use 'Make an NFT' to unlock me!", 10, 0, false);
    static NFT[] nftList = {sword, java, customNFT};
    // --Other--
    static boolean delayedText = true;
    static String waitLength = "NORMAL";

    // MAIN METHOD
    public static void main(String[] args){
        clearScreen();
        // Welcome Screen
        delayedOutput(ANSI_CYAN + """
            Welcome to FakeOS""" + version + """
            !
            Press ENTER to begin.""" + ANSI_WHITE);
        scanner.nextLine();
        clearScreen();
        // Delayed text output on/off
        delayedOutput(ANSI_CYAN + """
            Would you like delayed text output?""");
        wait(750);
        delayedOutput("""
            This is delayed text output.""");
        wait(750);
        System.out.println("This is normal output.");
        wait(750);
        delayedOutput("""
            Delayed text may look nice, but it can
            be tedious to wait for. This setting can
            always be changed later in the Settings app.
            A - Delayed text output on
            B - Delayed text output off""" + ANSI_WHITE);
        if(getCommand(true) == 'B'){
            delayedText = false;
        }
        // Go to Main Menu
        mainMenu();
    }

    // GENERAL METHODS
    /**
     * Clears the console.
     */
    public static void clearScreen(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
    /**
     * Prints delayed output to the console.
     * @param mes - string to print
     */
    public static void delayedOutput(String mes){
        if(delayedText){
            for(int i = 0; i < mes.length(); i++){
                System.out.print(mes.charAt(i));
                wait(35);
            }
            System.out.println();
        }else{
            System.out.println(mes);
        }
    }
    /**
     * Gets a command from the user.
     * <p>
     * Gets a command from the user, returned as a character. Input is
     * a string, but only the first character is returned. Always returns
     * uppercase.
     * <p>
     * @param endClearScreen - whether or not the invoke clearScreen()
     * @return command, returned as char
     */
    public static char getCommand(boolean endClearScreen){
        String input = scanner.nextLine();
        char command;
        if(! input.equals("")){
            command = Character.toUpperCase(input.charAt(0));
        }else{
            command = 'Z';
        }
        if(endClearScreen){
            clearScreen();
        }
        return command;
    }
    /**
     * Gets an integer from the user.
     * <p>
     * Gets an integer from the user, returns it, or -1 if incorrect input
     * type. Also uses scanner.nextLine() to prevent later errors.
     * <p>
     * @param min - minimun value (inclusive)
     * @param max - maximum value (inclusive, as all max values should be)
     * @return integer
     */
    public static int getInteger(int min, int max){
        try{
            int answer = scanner.nextInt();
            scanner.nextLine();
            while(max < answer || min > answer){
                if(min > answer){
                    System.out.println(ANSI_RED + "Too low, try again." +
                        ANSI_WHITE);
                }else{
                    System.out.println(ANSI_RED + "Too high, try again." +
                        ANSI_WHITE);
                }
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            return answer;
        }catch(Exception InputMismatchException){
            return -1;
        }
    }
    /**
     * Returns a random element from a given array.
     * <p>
     * Only works on String arrays.
     * <p>
     * @param array1 - the array to get an element from
     * @return a random element as a String
     */
    public static String randomElement(String[] array1){
        Random rand = new Random();
        String myRandomElement = array1[rand.nextInt(array1.length)];
        return myRandomElement;
    }
    /**
     * Basically Thread.sleep(), but with error handling.
     * @param time - the time to wait
     */
    public static void wait(int time){
        if(waitLength.equals("FAST")){
            time /= 2;
        }
        if(! waitLength.equals("NONE")){
            try{
                Thread.sleep(time);
            }catch(InterruptedException e){
                System.out.println(ANSI_RED +
                    "There was a problem with the timer.");
            }
        }
    }
    /**
     * Fixes tiny rounding and math errors.
     * @param inputValue - double to fix
     * @return fixed double
     */
    public static double fixRoundError(double inputValue){
        double doubleValue = (double) Math.round(inputValue * 10) / 10;
        return doubleValue;
    }
    /**
     * Gets file contents for NFTs
     * <p>
     * Returns modified contents from a given file
     * to be used as NFT contents.
     * <p>
     * @param fileName - the file to pull from
     * @return the modified file contents
     */
    public static String getFileContents(String fileName){
        fileName = mainDirectory2 + "NFTs/" + fileName + ".txt";
        try{
            fileScanner = new Scanner(new File(fileName));
            String fileOgContents = "", fileContents = "";
            while(fileScanner.hasNextLine()){
                fileOgContents += fileScanner.nextLine() + "x";
            }
            char currentChar;
            for(int i = 0; i < fileOgContents.length(); i++){
                currentChar = fileOgContents.charAt(i);
                switch(currentChar){
                    case ' ':
                        fileContents += " ";
                        break;
                    case '0':
                        fileContents += ANSI_GREEN + "0";
                        break;
                    case '1':
                        fileContents += ANSI_BLUE + "1";
                        break;
                    case 'A':
                        fileContents += ANSI_RED + "0";
                        break;
                    case 'B':
                        fileContents += ANSI_RED + "1";
                        break;
                    case '@':
                        fileContents += ANSI_BLACK_BACKGROUND + " ";
                        break;
                    case '%':
                        fileContents += ANSI_GREEN_BACKGROUND + " ";
                        break;
                    case 'x':
                        if(i != fileOgContents.length() - 1){
                            fileContents += "\n";
                        }
                        break;
                    default:
                        fileContents += currentChar;
                }
                fileContents += ANSI_RESET;
            }
            return fileContents;
        }catch(Exception FileNotFoundException){
            return (ANSI_RED + "Could not find file contents");
        }
    }

    // APP METHODS
    // Main Menu
    public static void mainMenu(){
        delayedOutput(ANSI_CYAN + """
            Welcome to the Main Menu.
            --Security--
            A - Security
            B - Security Shop
            C - Software Updates
            --FakeCoin--
            D - Bank
            E - FakeCoin Miner
            F - FakeStock
            G - Black Market
            --NFTs--
            H - MyNFTs
            I - NFT Store
            --Other--
            J - Clock
            K - FakePad File Editor
            L - Password Cracker
            M - Settings
            N - FakeOS Shutdown""" + ANSI_WHITE);
        switch(getCommand(true)){
            case 'A':
                security();
                break;
            case 'B':
                securityShop();
                break;
            case 'C':
                softwareUpdates();
                break;
            case 'D':
                bank();
                break;
            case 'E':
                fakeCoinMiner();
                break;
            case 'F':
                fakeStock();
                break;
            case 'G':
                blackMarket();
                break;
            case 'H':
                myNFTs();
                break;
            case 'I':
                nftStore();
                break;
            case 'J':
                clock();
                break;
            case 'K':
                fakePad();
                break;
            case 'L':
                passwordCracker();
                break;
            case 'M':
                settings();
                break;
            case 'N':
                delayedOutput(ANSI_RED + "Goodbye" + ANSI_WHITE);
                wait(1000);
                break;
            default:
                mainMenu();
        }
    }
    // --Security--
    // Security
    public static void security(){
        delayedOutput(ANSI_CYAN + """
            Welcome to the Security Menu
            A - Virus scan
            B - App scan
            C - Go to Security Shop
            D - Go to Software Updates
            E - Return to Main Menu""" + ANSI_WHITE);
        switch(getCommand(true)){
            case 'A': // Scan for viruses
                System.out.println(ANSI_CYAN + "Scanning...");
                if(Math.random() <= chance &&
                ! boughtSAV){ // There is a virus
                    delayedOutput(ANSI_RED + """
                        VIRIS DETECTED
                        PaY aLL Ur MonIEs tO REmOvE!!!1""" + ANSI_CYAN + """
                        \nA - Pay
                        B - Don't pay""" + ANSI_WHITE);
                    if(getCommand(true) == 'A'){
                        fakeCoin = 0;
                        delayedOutput(ANSI_RED +
                            "You lost all of your FakeCoin.");
                        File folder =
                            new File(mainDirectory2 + "Text Files");
                        File[] allFiles = folder.listFiles();
                        for(File file : allFiles){
                            try{
                                fileWriter = new PrintStream(file);
                                fileWriter.print("""
                                    uR FIle hAs bEN HhacKed
                                    HhaAhhaAha""");
                            }catch(Exception FileNotFoundException){
                                System.out.println(ANSI_RED +
                                    "There was a problem" + 
                                    "with the virus.");
                            }
                        }
                        delayedOutput(ANSI_RED + """
                            Your files were corrupted by a virus
                            masquerading as an anti-virus.""");
                        delayedOutput(ANSI_CYAN +
                            "Press ENTER to return to Security Menu." +
                            ANSI_WHITE);
                        scanner.nextLine();
                        clearScreen();
                        security();
                    }else{
                        delayedOutput(ANSI_CYAN +
                            "Press ENTER to return to Security Menu." +
                            ANSI_WHITE);
                        scanner.nextLine();
                        clearScreen();
                        security();
                    }
                }else{ // There is not a virus
                    delayedOutput(ANSI_GREEN + "No viruses found." +
                        ANSI_CYAN +
                        "\nPress ENTER to return to Security Menu." +
                        ANSI_WHITE);
                    scanner.nextLine();
                    clearScreen();
                    security();
                }
                break;
            case 'B': // App scan
                System.out.println(ANSI_CYAN + "Scanning...");
                wait(1000);
                int badSoftware = 0;
                if(boughtUAV){
                    badSoftware++;
                }
                if(boughtNFtI){
                    badSoftware++;
                }
                clearScreen();
                delayedOutput("1" + ANSI_RED + " SUSPICIOUS " +
                    ANSI_CYAN + "app found.\n0" + ANSI_BLUE + " REAL " +
                    ANSI_CYAN + "apps found.\n" + 
                    badSoftware + ANSI_RED + " SUSPICIOUS " + ANSI_CYAN +
                    "Security Software Packs found." + 
                    "\nPassword Cracker is a" + ANSI_RED + " SUSPICIOUS " +
                    ANSI_CYAN + """
                    app.
                    It is recommended that you inspect it.
                    Press ENTER to return to Security Menu.""" + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                security();
                break;
            case 'C': // Go to Security Shop
                securityShop();
                break;
            case 'D': // Go to Software Updates
                softwareUpdates();
                break;
            default: // Go to Main Menu
                mainMenu();
        }
    }
    public static void securityShop(){
        delayedOutput(ANSI_CYAN + """
            Welcome to the Security Shop
            Below is a list of available software.""");
        // Print Software List
        wait(150);
        System.out.println(ANSI_YELLOW + "Super Anti-Virus" + 
            ANSI_BLUE + "\nby FakeInc." +
            ANSI_PURPLE + "\n10 FakeCoin");
        if(boughtSAV){
            System.out.println(ANSI_GREEN + "Owned");
        }else{
            System.out.println(ANSI_RED + "Not Owned");
        }
        System.out.println();
        wait(150);
        System.out.println(ANSI_YELLOW + "Ultra Anti-Virus" + 
            ANSI_BLUE + "\nby FakeCorp." +
            ANSI_PURPLE + "\n20 FakeCoin");
        if(boughtUAV){
            System.out.println(ANSI_GREEN + "Owned");
        }else{
            System.out.println(ANSI_RED + "Not Owned");
        }
        System.out.println();
        wait(150);
        System.out.println(ANSI_YELLOW + "NFT Encrypter" + 
            ANSI_BLUE + "\nby FakeInc." +
            ANSI_PURPLE + "\n15 FakeCoin");
        if(boughtNFTE){
            System.out.println(ANSI_GREEN + "Owned");
        }else{
            System.out.println(ANSI_RED + "Not Owned");
        }
        System.out.println();
        wait(150);
        System.out.println(ANSI_YELLOW + "NFt InkrIpter" + 
            ANSI_BLUE + "\nby FackeInk." +
            ANSI_PURPLE + "\n5 FakeCoin");
        if(boughtNFtI){
            System.out.println(ANSI_GREEN + "Owned");
        }else{
            System.out.println(ANSI_RED + "Not Owned");
        }
        System.out.println();
        // Software List Printed
        delayedOutput(ANSI_CYAN + """
            A - Buy software
            B - Go to Security Menu
            C - Go to Main Menu""" + ANSI_WHITE);
        switch(getCommand(true)){
            case 'A': // Buy Software
                delayedOutput(ANSI_CYAN + """
                    A - Super Anti-Virus
                    B - Ultra Anti-Virus
                    C - NFT Encrypter
                    D - NFt InkrIpter
                    E - Cancel""" + ANSI_WHITE);
                boolean userContinue = true;
                switch(getCommand(true)){
                    case 'A': // Prevents viruses in Security --> Virus Scan
                        if(! boughtSAV){
                            fakeCoin -= 10;
                            boughtSAV = true;
                        }else{
                            delayedOutput(ANSI_RED +
                                "You already own this software.");
                        }
                        break;
                    case 'B': /* Prints fake error messages and 
                    messes with text colors*/
                        if(! boughtUAV){
                            fakeCoin -= 20;
                            boughtUAV = true;
                            // Error Messages (all fake)
                            int randomNum =
                                (int) (Math.round(Math.random() * 20) + 10);
                            String[] errorList1 =
                                {"System ", "Reboot ", "SYS_",
                                    "FakeOS ", "OS ", "FakeEXE-"};
                            String[] errorList2 =
                                {"Failue...", "FAIL;", "DOWN()",
                                    "BROKE!", "ERROR ", "OUT"};
                            String[] errorList3 =
                                {"sys.", "fakeos.", "runner.",
                                    "os.", "main.", "001_"};
                            String[] errorList4 =
                                {"exe.", "allSys().", "all.",
                                    "exec.", "conv.", "file."};
                            String[] errorList5 =
                                {"ALL_SYS_FAIL", "Reboot().FAIL",
                                    "file`Failed", "`OFF/FAILED`", "\\OFF\\",
                                    "*failed", "!!FAIL!!", "quargsOFF"};
                            for(int i = 0; i < randomNum; i++){
                                wait((int) ((Math.random() * 1000) + 200));
                                if(Math.random() <= 0.9){
                                    System.out.print(ANSI_RED);
                                }else{
                                    System.out.print(ANSI_WHITE);
                                }
                                System.out.println(randomElement(errorList1) +
                                    randomElement(errorList2));
                                wait((int) ((Math.random() * 1000) + 200));
                                if(Math.random() <= 0.6){
                                    System.out.print(ANSI_RED);
                                }else{
                                    System.out.print(ANSI_WHITE);
                                }
                                System.out.println(randomElement(errorList3) +
                                    randomElement(errorList4) +
                                    randomElement(errorList5));
                            }
                            wait(3000);
                            // Change all Ansi Colors
                            ANSI_CYAN = "\u001B[31m";
                            ANSI_RED = "\u001B[33m";
                            ANSI_YELLOW = "\u001B[36m";
                            ANSI_PURPLE = "\u001B[32m";
                            ANSI_GREEN = "\u001B[37m";
                            ANSI_WHITE = "\u001B[34m";
                            ANSI_BLUE = "\u001B[35m";
                            // Return to Main Menu
                            clearScreen();
                            System.out.println(ANSI_RED + "SYSTEM REBOOTED");
                            userContinue = false;
                        }else{
                            delayedOutput(ANSI_RED +
                                "You already own this software.");
                        }
                        break;
                    case 'C': // Prevents NFT Theft
                        if(! boughtNFTE){
                            fakeCoin -= 15;
                            boughtNFTE = true;
                        }else{
                            delayedOutput(ANSI_RED +
                                "You already own this software.");
                        }
                        break;
                    case 'D': // Corrupts all of your NFTs
                        if(! boughtNFtI){
                            fakeCoin -= 5;
                            boughtNFtI = true;
                            for(NFT nft : nftList){
                                if(nft.owned){
                                    nft.owned = false;
                                    nft.contents = """
                                        ___________
                                        |Your NFT |
                                        |has been |
                                        |corrupted|
                                        |_________|""";
                                }
                            }
                            delayedOutput(ANSI_RED +
                                "Your NFTs have been corrupted.");
                        }else{
                            delayedOutput(ANSI_RED +
                                "You already own this software.");
                        }
                        break;
                }
                if(userContinue){
                    // Return to Security Shop Menu
                    delayedOutput(ANSI_CYAN +
                        "Press ENTER to return to Security Shop." +
                        ANSI_WHITE);
                    scanner.nextLine();
                    clearScreen();
                    securityShop();
                }else{ // Go to Main Menu (only for Ultra Anti-Virus)
                    mainMenu();
                }
                break;
            case 'B': // Go to Security
                security();
                break;
            default: // Go to Main Menu
                mainMenu();
        }
    }
    // Software Updates
    public static void softwareUpdates(){
        if(Math.random() <= 0.6 || ignoredUpdate){ // Found an update
            delayedOutput(ANSI_RED + "Update found!" + ANSI_CYAN + """
                \nA - Pay 7.5 FakeCoin to update software
                B - Ignore update, go to Main Menu""" + ANSI_WHITE);
            if(getCommand(true) == 'A'){
                ignoredUpdate = false;
                fakeCoin -= 7.5;
                double timeLeft = 20;
                String timeLeftString1, timeLeftString2;
                while(timeLeft > 0){
                    timeLeft += Math.random() * 5 - 7;
                    timeLeftString1 = String.valueOf(timeLeft);
                    timeLeftString2 = "" + // World's worst rounding
                        timeLeftString1.charAt(0) +
                        timeLeftString1.charAt(1) +
                        timeLeftString1.charAt(2) +
                        timeLeftString1.charAt(3);
                    if(timeLeftString1.charAt(1) != '.'){
                        timeLeftString2 +=
                            timeLeftString1.charAt(4);
                    }
                    clearScreen();
                    if(timeLeft > 0){
                        System.out.println("\nTime remaining: " +
                            timeLeftString2 + "\n\n");
                    }else{
                        System.out.println("""
                            
                            Time Remaining: 0.00
                            
                            """);
                    }
                    wait(1000);
                }
                clearScreen();
                chance -= 0.1; /* Controls chance for future updates,
                virus scans, and NFT theft */
                delayedOutput(ANSI_CYAN + """
                    The system has been updated.
                    You will not be able to tell the difference.
                    Press ENTER to go to Main Menu.""" + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                mainMenu();
            }else{
                mainMenu();
            }
        }else{ // Didn't find an update
            delayedOutput(ANSI_GREEN + "No updates found." + ANSI_CYAN +
                "\nPress ENTER to go to Main Menu." + ANSI_WHITE);
            scanner.nextLine();
            clearScreen();
            mainMenu();
        }
    }
    // --FakeCoin--
    // Bank
    public static void bank(){
        if(fakeCoin < 0){ // Sell assests if broke
            for(Stock stock : stockList){
                if(stock.owned){
                    if(fakeCoin >= 0){
                        break;
                    }
                    stock.owned = false;
                    fakeCoin += stock.value;
                    System.out.println(ANSI_RED + "Your stock " + stock.name +
                        " was sold to decrease your debt.");
                }
            }
            for(NFT nft : nftList){
                if(nft.owned){
                    if(fakeCoin >= 0){
                        break;
                    }
                    nft.owned = false;
                    fakeCoin += nft.sellValue;
                    System.out.println(ANSI_RED + "Your NFT " + nft.name +
                        " was sold to decrease your debt.");
                }
            }
        }
        fakeCoin = fixRoundError(fakeCoin);
        double netWorth = fakeCoin;
        for(Stock stock : stockList){
            if(stock.owned){
                netWorth += stock.value;
            }
        }
        for(NFT nft : nftList){
            if(nft.owned){
                netWorth += nft.sellValue;
            }
        }
        netWorth = fixRoundError(netWorth);
        delayedOutput(ANSI_CYAN + "Balance: " + fakeCoin + " FakeCoin" +
            "\nNet Worth: " + netWorth + " FakeCoin" +
            "\nYour Password: " + password +"""
            \nA - Change password
            B - Go to FakeCoin Miner
            C - Go to FakeStock
            D - FakeCoin issues?
            E - Go to Main Menu""" + ANSI_WHITE);
        switch(getCommand(true)){
            case 'A': // Change password
                delayedOutput(ANSI_CYAN +
                    "Enter your new password below." + ANSI_WHITE);
                int answer = getInteger(1, 99999);
                if(answer == -1){
                    password = 0;
                    delayedOutput(ANSI_RED + """
                        Password changing failed, input must be a number.
                        Password is now 0.""");
                }else{
                    password = answer;
                    delayedOutput(ANSI_GREEN + """
                        Password changed successfully.
                        Password is now: """ + " " + password);
                }
                delayedOutput(ANSI_CYAN +
                    "Press ENTER to continue." + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                bank();
                break;
            case 'B':
                fakeCoinMiner();
                break;
            case 'C':
                fakeStock();
                break;
            case 'D':
                fakeCoin = fixRoundError(fakeCoin);
                delayedOutput(ANSI_CYAN + """
                    If your FakeCoin value seemed strange
                    (like 2.399999 or 3.4000001),
                    it should be fixed now.
                    Press ENTER to return to Bank Menu.""" + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                bank();
            default: // Go to Main Menu
                mainMenu();
        }
    }
    // FakeCoin Miner
    public static void fakeCoinMiner(){
        delayedOutput(ANSI_CYAN + """
            Welcome to FakeCoin Miner
            A - Mine FakeCoin
            B - Go to Main Menu""" + ANSI_WHITE);
        if(getCommand(true) == 'B'){ // Go to Main Menu
            mainMenu();
        }else{ // Mine FakeCoin
            double randomDouble =
                (double) Math.round(Math.random() * 10) / 10 + 0.5;
            fakeCoin += randomDouble;
            delayedOutput(ANSI_GREEN + "You got " + randomDouble +
                " FakeCoin!" + ANSI_CYAN +
                "\nPress ENTER to return to FakeCoin Miner Menu." +
                ANSI_WHITE);
            scanner.nextLine();
            clearScreen();
            fakeCoinMiner();
        }
    }
    // FakeStock
    public static void fakeStock(){
        delayedOutput(ANSI_CYAN + """
            Welcome to FakeStock,
            the stock market for FakeCoin.
            I totally know how this stuff works.
            Stocks:""");
        for(Stock stock : stockList){ // Print stocks
            wait(150);
            stock.valueChange +=
                (double) Math.round((Math.random() * 2 - 1) * 10) / 10;
            if(Math.abs(stock.valueChange) > 7 && Math.random() <= 0.5){
                stock.valueChange = 0;
            }
            stock.value += stock.valueChange;
            stock.value = fixRoundError(stock.value);
            if(stock.value < 0.5){
                stock.value = 0.5;
                stock.valueChange = 0;
            }
            System.out.println(ANSI_YELLOW + stock.abrv + "\n" + stock.name +
                ANSI_WHITE + "\n" + stock.value + " FakeCoin");
            if(stock.valueChange > 0){
                System.out.println(ANSI_GREEN + "Trending upward");
            }else if(stock.valueChange < 0){
                System.out.println(ANSI_RED + "Trending downward");
            }else{
                System.out.println(ANSI_YELLOW + "No trend");
            }
            if(stock.owned){
                System.out.println(ANSI_GREEN + "Owned");
            }else{
                System.out.println(ANSI_RED + "Not Owned");
            }
            System.out.println();
        }
        delayedOutput(ANSI_CYAN + """
            A - Buy stock
            B - Sell stock
            C - Go to Main Menu""" + ANSI_WHITE);
        char command = getCommand(true);
        if(command == 'A' || command == 'B'){
            delayedOutput(ANSI_CYAN + """
                A - FakeInc
                B - FakeCorp
                C - FackeInk
                D - FakeOS
                E - NFTCorp
                F - Cancel""" + ANSI_WHITE);
            Stock choice = fakeInc;
            boolean userContinue;
            userContinue = true;
            switch(getCommand(true)){
                case 'A':
                    break;
                case 'B':
                    choice = fakeCorp;
                    break;
                case 'C':
                    choice = fackeInk;
                    break;
                case 'D':
                    choice = fakeOS;
                    break;
                case 'E':
                    choice = nftCorp;
                    break;
                default:
                    userContinue = false;
                    fakeStock();
            }
            if(userContinue){
                if(command == 'A'){
                    if(choice.owned){
                        delayedOutput(ANSI_RED +
                            "You already own this stock.");
                    }else{
                        choice.owned = true;
                        fakeCoin -= choice.value;
                        delayedOutput(ANSI_GREEN + "Success");
                    }
                }else{
                    if(choice.owned){
                        choice.owned = false;
                        fakeCoin += choice.value;
                        delayedOutput(ANSI_GREEN + "Success");
                    }else{
                        delayedOutput(ANSI_RED +
                            "You don't own this stock.");
                    }
                }
                delayedOutput(ANSI_CYAN +
                    "Press ENTER to return to FakeStock." + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                fakeStock();
            }
        }else{
            clearScreen();
            mainMenu();
        }
    }
    // Black Market
    public static void blackMarket(){
        int valueStocks = 0;
        int valueNFTs = 0;
        for(Stock stock : stockList){
            if(stock.owned){
                valueStocks += stock.value;
            }
        }
        for(NFT nft : nftList){
            if(nft.owned){
                valueNFTs += nft.sellValue;
            }
        }
        valueStocks *= 10;
        valueNFTs *= 10;
        delayedOutput(ANSI_CYAN + """
            Welcome to the Black Market
            A - Sell all stocks (""" + valueStocks + ")" +
            "\nB - Sell all NFTs (" + valueNFTs + """
            )
            C - Return to Main Menu""" + ANSI_WHITE);
        char command = getCommand(true);
        boolean userContinue = true;
        if(command == 'A'){
            fakeCoin += valueStocks;
            for(Stock stock : stockList){
                stock.owned = false;
            }
        }else if(command == 'B'){
            fakeCoin += valueNFTs;
            for(NFT nft : nftList){
                nft.owned = false;
            }
        }else{
            userContinue = false;
            mainMenu();
        }
        if(userContinue && Math.random() <= 0.4){
            delayedOutput(ANSI_RED +
                "You lost everything to a scammer.");
            fakeCoin = 0;
            for(Stock stock : stockList){
                stock.owned = false;
            }
            for(NFT nft : nftList){
                nft.owned = false;
            }
        }
        delayedOutput(ANSI_CYAN +
            "Press ENTER to return to Black Market Menu." + ANSI_WHITE);
        scanner.nextLine();
        clearScreen();
        blackMarket();
        
    }
    // --NFTs--
    // myNFTs
    public static void myNFTs(){
        if(Math.random() <= chance / 2 && !boughtNFTE){
            for(NFT nft : nftList){
                nft.owned = false;
            }
            delayedOutput(ANSI_RED + """
                Your NFTs were stolen by a virus.
                Consider buying an NFT Encrypter.""");
        }
        delayedOutput(ANSI_CYAN + """
            Welcome to MyNFTs,
            below is a list of all your NFTs,
            or NoForesight Tokens""");
        boolean haveNFTs = false;
        for(NFT nft : nftList){
            if(nft.owned){
                haveNFTs = true;
                wait(150);
                System.out.println(ANSI_YELLOW + nft.name +
                    ANSI_WHITE + "\n" + nft.contents +
                    ANSI_BLUE + "\n" + nft.sellValue + " FakeCoin");
            }
        }
        if(! haveNFTs){
            System.out.println(ANSI_RED + "You don't have any NFTs.");
        }
        delayedOutput(ANSI_CYAN + """
            A - Go to NFT Store
            B - Go to Main Menu""" + ANSI_WHITE);
        if(getCommand(true) == 'A'){
            nftStore();
        }else{
            mainMenu();
        }
    }
    // NFT Store
    public static void nftStore(){
        delayedOutput(ANSI_CYAN + """
            Welcome to the NFT Store,
            your one place to buy, sell, and make
            NoForesight Tokens (NFTs).""");
        for(NFT nft : nftList){
            wait(150);
            System.out.println(ANSI_YELLOW + nft.name +
                "\n" + ANSI_WHITE + nft.contents);
            if(nft.owned){
                System.out.println(ANSI_GREEN + "Owned");
                System.out.print(ANSI_BLUE + nft.sellValue);
            }else{
                System.out.println(ANSI_RED + "Not Owned");
                System.out.print(ANSI_BLUE + nft.buyValue);
            }
            System.out.println(" FakeCoin\n");
        }
        delayedOutput(ANSI_CYAN + """
            A - Buy an NFT
            B - Sell an NFT
            C - Make an NFT
            D - Go to Main Menu""" + ANSI_WHITE);
        switch(getCommand(true)){
            case 'A': // Buy an NFT
                delayedOutput(ANSI_CYAN +
                    "Enter the name of the NFT you wish to buy." +
                    ANSI_WHITE);
                String nftToBuy = scanner.nextLine();
                boolean success = false;
                for(NFT nft : nftList){
                    if(nft.name.equals(nftToBuy) &&
                            ! nft.owned && nft != customNFT){
                        delayedOutput(ANSI_CYAN + "Price: " +
                            nft.buyValue + """
                                \nA - Confirm transaction
                                B - Cancel""" + ANSI_WHITE);
                        char command = getCommand(false);
                        if(command == 'A' && ! nft.owned){
                            fakeCoin -= nft.buyValue;
                            nft.owned = true;
                            delayedOutput(ANSI_GREEN +
                                "Transaction successful.");
                        }else{
                            if(command == 'B'){
                                delayedOutput(ANSI_GREEN +
                                    "Transaction canceled.");
                            }else{
                                delayedOutput(ANSI_RED +
                                    "You already own this NFT.");
                            }
                        }
                        success = true;
                    }
                }
                if(! success){
                    delayedOutput(ANSI_RED + "NFT not found.");
                }
                delayedOutput(ANSI_CYAN + "Press ENTER to go to Main Menu." +
                    ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                mainMenu();
                break;
            case 'B': // Sell an NFT
                delayedOutput(ANSI_CYAN +
                    "Enter the name of the NFT you wish to sell." +
                    ANSI_WHITE);
                String nftToSell = scanner.nextLine();
                success = false;
                for(NFT nft : nftList){
                    if(nft.name.equals(nftToSell) && nft.owned){
                        delayedOutput(ANSI_CYAN + "Price: " +
                            nft.sellValue + """
                                \nA - Confirm transaction
                                B - Cancel""" + ANSI_WHITE);
                        if(getCommand(false) == 'A'){
                            fakeCoin += nft.sellValue;
                            nft.owned = false;
                            delayedOutput(ANSI_GREEN +
                                "Transaction successful.");
                        }else{
                            delayedOutput(ANSI_GREEN +
                                "Transaction canceled.");
                        }
                        success = true;
                    }
                }
                if(! success){
                    delayedOutput(ANSI_RED + "NFT not found.");
                }
                delayedOutput(ANSI_CYAN + "Press ENTER to go to Main Menu." +
                    ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                mainMenu();
                break;
            case 'C': // Make an NFT
                boolean userContinue = true;
                if(customNFT.owned){
                    delayedOutput(ANSI_CYAN + """
                        You are overwriting the current custom NFT. 
                        A - Continue
                        B - Cancel""" + ANSI_WHITE);
                    if(getCommand(true) != 'A'){
                        userContinue = false;
                    }
                }
                if(userContinue){
                    delayedOutput(ANSI_CYAN +
                        "Enter the name for your NFT." + ANSI_WHITE);
                    customNFT.name = scanner.nextLine();
                    delayedOutput(ANSI_CYAN + """
                        Enter the NFT's contents (the art itself) below.
                        Begin with a '`' symbol. This symbol
                        will be removed later, and cannot be
                        used anywhere else in the content.
                        The contents must be 20 lines long.
                        Once you finish writing the
                        contents, press ENTER until something
                        happens. Blank lines at the end of the
                        contents and before the '`' will be removed.""" +
                        ANSI_WHITE);
                    String contents = "";
                    for(int i = 0; i < 20; i++){
                        contents += scanner.nextLine() + "\n";
                    }
                    contents = contents.trim();
                    contents = contents.replace("`", "");
                    customNFT.contents = contents;
                    customNFT.owned = true;
                    fakeCoin -= customNFT.buyValue;
                    int value = 0;
                    for(int i = 0; i < contents.length(); i++){
                        if(contents.charAt(i) != ' ' &&
                                contents.charAt(i) != '\n'){
                            value += 1;
                        }
                    }
                    customNFT.sellValue = value;
                    clearScreen();
                    System.out.println(ANSI_YELLOW + customNFT.name +
                        ANSI_WHITE + "\n" + customNFT.contents +
                        ANSI_BLUE + "\n" + customNFT.sellValue + " FakeCoin" +
                        ANSI_CYAN + "\nPress ENTER to go to Main Menu." +
                        ANSI_WHITE);
                    scanner.nextLine();
                    clearScreen();
                    mainMenu();
                }else{
                    mainMenu();
                }
                break;
            default:
                mainMenu();
        }
    }
    // --Other--
    // Clock
    public static void clock(){
        String myTime = new SimpleDateFormat("HH:mm:ss").
            format(new Date()); // Get current time
        System.out.println(ANSI_CYAN + myTime + """
            \nA - Update time
            B - Return to Main Menu""" + ANSI_WHITE);
        if(getCommand(true) == 'B'){ // Go to Main Menu
            mainMenu();
        }else{ // Go to Clock / Update time
            clock();
        }
    }
    // FakePad
    public static void fakePad(){
        delayedOutput(ANSI_CYAN + """
            Welcome to FakePad File Editor
            Current Files:""");
        File folder = new File(mainDirectory2 + "Text Files");
        File[] allFiles = folder.listFiles();
        for(File file : allFiles){ // Print files and content
            try{
                wait(150);
                String fileName = file.toString();
                fileScanner = new Scanner(new File(fileName));
                fileName = fileName
                    .replace(mainDirectory + "Text Files\\", "")
                    .replace(mainDirectory2 + "Text Files/", "");
                fileName = fileName.replace(".txt", "");
                String fileContents = "";
                while(fileScanner.hasNextLine()){
                    fileContents += fileScanner.nextLine();
                    if(fileScanner.hasNextLine()){
                        fileContents += "\n";
                    }
                }
                System.out.println(ANSI_YELLOW + fileName);
                System.out.println(ANSI_WHITE + fileContents);
                System.out.println();
            }catch(Exception FileNotFoundException){
                System.out.println(ANSI_RED +
                    "There was a problem getting this file.");
            }
        }
        delayedOutput(ANSI_CYAN + """
            Enter the name of the file you
            wish to edit or create, or ENTER 
            to not edit or create any files
            and return to the Main Menu.""" +
            ANSI_WHITE);
        String fileToEdit = scanner.nextLine();
        clearScreen();
        if(fileToEdit.equals("")){ // Go to Main Menu
            mainMenu();
        }else{
            try{ // Edit a file
                System.out.println(ANSI_YELLOW + fileToEdit);
                fileToEdit = 
                    mainDirectory2 + "Text Files/" + fileToEdit + ".txt";
                fileScanner = new Scanner(new File(fileToEdit));
                String fileContents = "";
                while(fileScanner.hasNextLine()){
                    fileContents += fileScanner.nextLine();
                    if(fileScanner.hasNextLine()){
                        fileContents += "\n";
                    }
                }
                System.out.println(ANSI_WHITE + fileContents);
                delayedOutput(ANSI_CYAN + """
                    A - Add to existing contents
                    B - Overwrite existing contents""" + ANSI_WHITE);
                char command = getCommand(false);
                delayedOutput(ANSI_CYAN +
                    "How many lines are you adding to the file?" +
                    ANSI_WHITE);
                int lineNum = getInteger(1, 10000);
                if(lineNum == -1){
                    lineNum = 5;
                    delayedOutput(ANSI_RED + """
                        Input must be a number.
                        You will now be adding 5 lines.""");
                }
                clearScreen();
                delayedOutput(ANSI_CYAN + """
                    Press ENTER to start a new line.
                    Blank lines at the beginning or the end
                    will be removed.""" + ANSI_WHITE);
                if(command == 'A'){
                    System.out.print(fileContents);
                }
                String newFileContents = "";
                for(int i = 0; i < lineNum; i++){
                    newFileContents += scanner.nextLine() + "\n";
                }
                fileContents = fileContents.trim();
                newFileContents = newFileContents.trim();
                fileWriter = new PrintStream(fileToEdit);
                if(command == 'A'){
                    fileWriter.print(fileContents);
                }
                fileWriter.print(newFileContents);
                clearScreen();
                fakePad();
            }catch(Exception FileNotFoundException){ // Make a new file
                clearScreen();
                delayedOutput(ANSI_RED + "File Not Found" +
                    ANSI_CYAN +
                    """
                    \nTo create a file with this name,
                    retype the file name below.
                    Do NOT end name with .txt""" + ANSI_WHITE);
                String fileToMake = scanner.nextLine();
                fileToMake = fileToMake // Replace illegal filename characters
                    .replace("<", "")
                    .replace(">", "")
                    .replace("\\", "")
                    .replace("/", "")
                    .replace("|", "")
                    .replace(";", "")
                    .replace("'", "")
                    .replace("?", "")
                    .replace("*", "")
                    .replace(":", "")
                    .replace("\"", "")
                    .replace(".", "")
                    .trim();
                String[] illegalStarts = {"0", "1", "2", "3", "4",
                                          "5", "6", "7", "8", "9"};
                for(String start : illegalStarts){
                    while(fileToMake.startsWith(start)){
                        fileToMake =
                            fileToMake.replaceFirst(start, "");
                    }
                }
                delayedOutput(ANSI_CYAN +
                    "Illegal filename characters will be removed.");
                File newFile = new File(mainDirectory + "Text Files\\" +
                    fileToMake + ".txt");
                try{ // Make new file
                    if(newFile.createNewFile()){
                        delayedOutput(ANSI_GREEN +
                            "File creation successful.");
                    }else{
                        delayedOutput(ANSI_RED +
                            "File creation failed, file already exists.");
                    }
                }catch(IOException e){ // Something went wrong
                    delayedOutput(ANSI_RED +
                        "File creation fialed, an error occurred.");
                }
                delayedOutput(ANSI_CYAN +
                    "Press ENTER to return to FakePad." + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                fakePad();
            }
        }
    }
    // Password Cracker
    public static void passwordCracker(){ // Just steals all your FakeCoin
        delayedOutput(ANSI_CYAN + "Press ENTER to begin." + ANSI_WHITE);
        scanner.nextLine();
        clearScreen();
        System.out.println(ANSI_CYAN + "Loading...");
        wait(500);
        int i = 0;
        while(i != password){
            i++;
        }
        fakeCoin = 0;
        clearScreen();
        delayedOutput("It took " + (password + 1) +
            " tries to guess your password." + ANSI_RED + 
            "\nAll your FakeCoin has been stolen." + ANSI_CYAN + 
            "\nPress ENTER to go to Main Menu." + ANSI_WHITE);
        scanner.nextLine();
        clearScreen();
        mainMenu();
    }
    // Settings
    public static void settings(){ // Edit user settings
        delayedOutput(ANSI_CYAN + """
            Welcome to Settings
            You can edit the following settings:
            Delayed Text Output - controls whether or not
            certain text output will be printed character-
            by-character.
            Wait Time - Controls all delays and wait times.
            Choosing \"NONE\" will also turn off delayed text.""");
        wait(150);
        System.out.print("A - Delayed Text Output    ");
        if(delayedText){
            System.out.println(ANSI_BLUE + "YES" + ANSI_CYAN + " / NO");
        }else{
            System.out.println(ANSI_CYAN + "YES / " + ANSI_BLUE + "NO");
        }
        wait(150);
        System.out.print(ANSI_CYAN + "B - Wait Time    ");
        switch(waitLength){
            case "NORMAL":
                System.out.println(ANSI_BLUE + "NORMAL" +
                    ANSI_CYAN + " / FAST / NONE");
                break;
            case "FAST":
                System.out.println(ANSI_CYAN + "NORMAL / " +
                    ANSI_BLUE + "FAST" + ANSI_CYAN + " / NONE");
                break;
            default:
                System.out.println(ANSI_CYAN + "NORMAL / FAST / " +
                    ANSI_BLUE + "NONE");
                break;
        }
        wait(150);
        System.out.println(ANSI_CYAN + "C - Return to Main Menu." +
            ANSI_WHITE);
        switch(getCommand(true)){
            case 'A':
                delayedText = ! delayedText;
                delayedOutput(ANSI_CYAN + """
                    Delayed Text setting changed.
                    Press ENTER to return to Settings Menu.""" + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                settings();
                break;
            case 'B':
                delayedOutput(ANSI_CYAN + """
                    A - Change to \"NORMAL\"
                    B - Change to \"FAST\"
                    C - Change to \"NONE\"""" + ANSI_WHITE);
                switch(getCommand(true)){
                    case 'A':
                        waitLength = "NORMAL";
                        break;
                    case 'B':
                        waitLength = "FAST";
                        break;
                    default:
                        waitLength = "NONE";
                }
                delayedOutput(ANSI_CYAN + """
                    Wait Time setting changed.
                    Press ENTER to return to Settings Menu.""" + ANSI_WHITE);
                scanner.nextLine();
                clearScreen();
                settings();
                break;
            default:
                mainMenu();
        }
    }
}

class NFT{ // Class for NFTs
    String name;
    String contents;
    double buyValue;
    double sellValue;
    boolean owned;
    NFT(String passedName, String passedContents,
    double passedBuyValue, double passedSellValue,
    boolean passedOwned){ // Passed variables
        name = passedName;
        contents = passedContents;
        buyValue = passedBuyValue;
        sellValue = passedSellValue;
        owned = passedOwned;
    }
}

class Stock{ // Class for Stocks
    String name;
    String abrv;
    double value;
    double valueChange;
    boolean owned;
    Stock(String passedName, String passedAbrv, double passedValue,
    double passedValueChange, boolean passedOwned){ // Passed variables
        name = passedName;
        abrv = passedAbrv;
        value = passedValue;
        valueChange = passedValueChange;
        owned = passedOwned;
    }
}
