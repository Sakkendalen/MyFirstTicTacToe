/**
 * Tictactoe Main method which controls Playground.java tictactoe game
 * which also includes of creating Playground object where game 
 * moves, winchecks, empty spot check, board printing made.
 *
 * The class Tictactoe is authors projectwork of 
 * introduction to programming in Tampere Applied sciences of technology
 *
 * @author  Saku Tynjala <saku.tynjala@cs.tamk.fi>
 * @version 2017.1211
 * @since   1.8
 *
 */

class Tictactoe{
    /**
     * Main method for the  game.
     * 
     * @param args Command line parameters. Not used.
    */
    public static void main(String[] args) {

        //Creates object for game
        Playground game = new Playground();

        /**
         * boolean to used to define have
         * player or ai won
         */
        boolean on = true;

        /**
         * Playground method checkwin to use
         * boolean on when player or ai wins
         * while it's true game is on.
         * Playground methods Playermove,
         * AImove and printPG used to play the
         * game
         */
        while(game.checkwin(on)){
            
            game.playerMove();
            game.AImove();
            game.printPG();

        }

        //who won game
        System.out.println(game.winner());

    }
}