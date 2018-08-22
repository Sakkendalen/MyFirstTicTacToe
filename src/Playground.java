import java.util.Scanner;
/**
 * Playground.java contains tictactoe game methods of printing playground, 
 * Player and AI moves, Setting playground size
 *
 * The class Tictactoe is authors projectwork of 
 * introduction to programming in Tampere Applied sciences of technology
 *
 * @author  Saku Tynjala <saku.tynjala@cs.tamk.fi>
 * @version 2017.1211
 * @since   1.8
 * 
 */

class Playground{
    private char [][] playground;
    private int width;
    private int heigth;
    private String whowon;
    private int winlength;
    private boolean gameon = true;

    /**
     * Default Constructor
     * 
     * <p> uses setPGsize method
     */
    Playground(){
        setPGsize();
    }

    /**
     * Set 2D array height and width.
     * 
     * <p> Ask user width and height.
     * min 3 and max 50.
     * Sets every spot in array "empty"(space)
     */
    void setPGsize(){

        Scanner userinput = new Scanner(System.in);
        boolean correctinput = false;

        //while to force player to do correct move
        while(!correctinput){

            //try-catch to protect that player don't crash the game
            try{
            System.out.println("Give playground width (min 3 max 50)");
            width = userinput.nextInt();
            System.out.println("Give playground heigth (min 3 max 50)");
            heigth = userinput.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input!");
                userinput.nextLine();
            }

            //checks is player given inputs in between 3 and 50
            if(width < 3 || heigth < 3){
                System.out.println("incorrect width or heigth!");
            }
            else if (width > 50 || heigth > 50){
                System.out.println("Whoa chill down bro! are you playing with 50 inch TV?");
            }
            else{
                correctinput = true;
            }
        }

        //sets playgrounds size
        playground = new char [heigth][width];

        //sets every spot in playground empty.
        for(int row = 0; row<heigth; row++){
            for(int col = 0; col<width; col++){
                playground [row][col] = ' ';
            }
        }

        if(width < 10 || heigth < 10){
            winlength = 3;
        }
        if(width >= 10 && heigth >= 10){
            winlength = 5;
        }

        printPG();
        System.out.println("For win: " + winlength + " in a row");
        System.out.println("You play as X");
    }

    /**
     * returns PG. not used.
     * 
     * @return 2D array of playground
     */
    char [][] getPG(){
        return this.playground;
    }

    /**
     * Prints 2D array for player.
     * 
     * <p> prints cross-lines, vertical lines,
     * column and row numbers to player easier to read
     * game board.
     */
    void printPG(){

        /**
         * variables to help define what
         * row and column is going in printing
         * and x & y variables to help indent
         * print.
         */
        int arraycol = 0;
        int arrayrow = 0;
        int x = width*2+2;
        int y = heigth*2+1;
        
        /**
         * for loops to print out PG and adds 1 to arraycol and arrayrow
         * in what spot is going and print row and column numbers. x and y is 2 times bigger than width and length
         * and last 2 and 1 was determited by author when PG is easiest to read.
         */
        for(int row = 0; row<y; row++){
            for(int col = 0; col<x; col++){

                /**
                 * Playgrounds column numbers and indentation by spaces
                 *if column number is under 10 this adds space before 
                 *and after column number and grows arraycol by 1.
                 */
                if(row == 0 && (col%2) == 1 && !(col == x-1) ){
                    if(arraycol >= 9){
                        System.out.print(' ');
                        System.out.print(arraycol+1);
                    }
                    else{
                        System.out.print(' ');
                        System.out.print(arraycol+1);
                        System.out.print(' ');
                    }
                    arraycol++;
                }

                //prints row number at end of line.
                else if ( row>=1 && (row%2) == 1 && col == x-1){
                    System.out.print(arrayrow+1);
                }

                //prints crosslines between 2d array spots
                else if ((row%2) == 0 && !(row == 0) && !(col == x-1)){
                    if (col == x-2){
                        System.out.print('-');
                    }
                    else{
                        System.out.print('-');
                        System.out.print('-');
                    }
                }

                //prints vertical lines between 2D array array spots
                else if(col == 0 && !(row == 0) || (col%2) == 0 && !(row == 0)){
                    System.out.print('|');
                    System.out.print(' ');
                }
                //prints char from array and space after that.
                else if (!(row == 0) && !(col == x-1) && !(row == y-1)){
                    System.out.print(playground[arrayrow][arraycol]);
                    System.out.print(' ');
                    arraycol++;
                }
                //space to help indent column numbers.
                else{
                    System.out.print(' ');
                }
            }
            //adds 1 to arrayrow in every other row to determite what row number player sees
            if ((row%2) == 1){
                arrayrow++;
            }
            //resets arraycol to 0 for the next line and ends line.
            arraycol = 0;
            System.out.println();
        }
    }

    /**
     * Returns string whowon in the end of game
     * 
     * @return String of winner
     */
    String winner(){
        return whowon;
    }

    /**
     * looks 2D array spot is it empty
     * 
     * <p> Looks array at given input values.
     * Return boolean true if it's empty.
     * 
     * @param row 2D array row number
     * @param column 2D array column number
     * @return boolean true if spot is empty
     */
    boolean checkspot(int row, int column){

        //boolean to define is spot empty
        boolean spotfree = false;

        //simple if to look is give spot empty
        if(playground[row][column] == ' '){
            spotfree = true;
        }

        //return boolean depending was it empty.
        return spotfree;
    }
    
    /**
     * Checks is there 3 or 5 in row of marks
     * 
     * <p> goes through 2d array at every spot vertically,
     * horizontally and diagonally down is there 3 or 5 in row
     * to win. winlength depends on playground size.
     * Return boolean true if game is won and sets String whowon
     * to player or Ai who won.
     * 
     * @param win takes in boolean value.
     * @return boolean true if there is winner.
     */
    boolean checkwin(boolean win){

        
        //for loops to go trough array
        for(int row=0; row<heigth; row++){
            for(int column=0; column<width; column++){

                //if array is samller than 10 checks these
                if(winlength==3){

                    //player marks
                    if(playground[row][column] == 'X'){

                        //vertically right
                        if(column+2 < width && playground[row][column+1] == 'X' && playground[row][column+2] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }

                        //horizontal down
                        if(row+2 < heigth && playground[row+1][column] == 'X' && playground[row+2][column] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }

                        //bottom right cross
                        if(column+2 < width && row+2 < heigth && playground[row+1][column+1] == 'X' && playground[row+2][column+2] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }

                        //bottom left cross
                        if(column-2 >= 0 && row+2 < heigth && playground[row+1][column-1] == 'X' && playground[row+2][column-2] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }
                    }

                    //AI marks
                    if(playground[row][column] == 'O'){

                        //vertically right
                        if(column+2 < width && playground[row][column+1] == 'O' && playground[row][column+2] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }

                        //horizontal down
                        if(row+2 < heigth && playground[row+1][column] == 'O' && playground[row+2][column] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }

                        //bottom right cross
                        if(column+2 < width && row+2 < heigth && playground[row+1][column+1] == 'O' && playground[row+2][column+2] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }

                        //bottom left cross
                        if(column-2 >= 0 && row+2 < heigth && playground[row+1][column-1] == 'O' && playground[row+2][column-2] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }
                    }
                }

                //if array is 10 or greater checks these
                if(winlength==5){

                    //player marks
                    if(playground[row][column] == 'X'){

                        //vertically right
                        if(column+4 < width && playground[row][column+1] == 'X' && playground[row][column+2] == 'X' 
                        && playground[row][column+3] == 'X' && playground[row][column+4] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }

                        //horizontal down
                        if(row+4 < heigth && playground[row+1][column] == 'X' && playground[row+2][column] == 'X'
                        && playground[row+3][column] == 'X' && playground[row+4][column] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }

                        //bottom right cross
                        if(column+4 < width && row+4 < heigth && playground[row+1][column+1] == 'X' && playground[row+2][column+2] == 'X'
                        && playground[row+3][column+3] == 'X' && playground[row+4][column+4] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }

                        //bottom left cross
                        if(column-4 >= 0 && row+4 < heigth && playground[row+1][column-1] == 'X' && playground[row+2][column-2] == 'X'
                        && playground[row+3][column-3] == 'X' && playground[row+4][column-4] == 'X'){
                            whowon = "player wins!";
                            win = false;
                        }
                    }

                    //AI marks.
                    if(playground[row][column] == 'O'){

                        //vertically right
                        if(column+4 < width && playground[row][column+1] == 'O' && playground[row][column+2] == 'O' 
                        && playground[row][column+3] == 'O' && playground[row][column+4] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }

                        //horizontal down
                        if(row+4 < heigth && playground[row+1][column] == 'O' && playground[row+2][column] == 'O'
                        && playground[row+3][column] == 'O' && playground[row+4][column] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }

                        //bottom right cross
                        if(column+4 < width && row+4 < heigth && playground[row+1][column+1] == 'O' && playground[row+2][column+2] == 'O'
                        && playground[row+3][column+3] == 'O' && playground[row+4][column+4] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }

                        //bottom left cross
                        if(column-4 >= 0 && row+4 < heigth && playground[row+1][column-1] == 'O' && playground[row+2][column-2] == 'O'
                        && playground[row+3][column-3] == 'O' && playground[row+4][column-4] == 'O'){
                            whowon = "AI wins!";
                            win = false;
                        }
                    }
                }
            }
        }

        return win;
    }

    /**
     * Asks player move
     * 
     * <p>determites player char place and uses checkspot method to check
     * if that place is free to use. if player gives invalid number or input
     * method will ask does player want to print playground for easier to check
     * playground situation.
     */
    void playerMove(){

        Scanner input = new Scanner(System.in);
        
        /**
         * boolean to define when spot is empty and correct move made
         * String for when player gives false inputs to print
         * Playground in middle of method.
         * row and column int must be initialized 
         * to be used in in if condition.
         */
        boolean correctmove = false;
        String ask = "";
        int row = -1;
        int column = -1;

        //while loop to force player move to free space.
        while(!correctmove){

            //try-catch to force player insert integer to row and column inputs.
            try{
                System.out.println("Player move");
                System.out.println("Row?");
                row = input.nextInt() -1;
                System.out.println("Column?");
                column = input.nextInt() -1;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }

            //if contition to check is spot in the playground.
            if(row < heigth && column < width && row >= 0 && column >= 0){

                //if contition to check is spot free to use.
                if(checkspot(row, column)){
                    playground [row][column] = 'X';
                    correctmove = true;
                    }


                /**
                 * if spot is taken move is invalid and this will
                 * ask do player want to print PG to check game situtation 
                 */
                else if (!checkspot(row, column)){
                    System.out.println("!!INVALID MOVE!!");
                    System.out.println("Print playground? (typing yes prints)");
                    input.nextLine();
                    ask = input.nextLine();
                    if(ask.equals("yes")){
                        printPG();
                    }
                }
            }

            /**
             * and if spot is out of 2d array move is invalid and this will
             *  ask do player want to print PG to check game situtation
             */
            else{
                System.out.println("!!INVALID MOVE!!");
                System.out.println("Print playground? (typing yes prints)");
                input.nextLine();
                ask = input.nextLine();
                if(ask.equals("yes")){
                    printPG();
                }
            }
        }
    }
    
    /**
     * AI move.
     * 
     * <p> places Ai mark randomly in 2D arrays
     * if spot is empty
     */
    void AImove(){

        /**
         * boolean and 2x int to used to define 
         * correct move and random place in 
         * array.
         */
        boolean correctmove = false;
        int rand1 = 0;
        int rand2 = 0;

        //if to make sure AI doesn't make extra move in the end
        if(checkwin(gameon)){

            /**
             * while loop to use boolean when correct move is made 
             * and method checkspot used to check is randomly generated 
             * row & column place free to use. Everytime when place is 
             * taken new random numbers are generated for new spot.
             */
            while(!correctmove){

                rand1 = (int) (Math.random() * width);
                rand2 = (int) (Math.random() * heigth);

                if(rand1 < heigth && rand2 < width && rand1 >= 0 && rand2 >= 0){
                    if(checkspot(rand1, rand2)){
                        playground [rand1][rand2] = 'O';
                        correctmove = true;
                    }
                }
            }
        }
    }
}