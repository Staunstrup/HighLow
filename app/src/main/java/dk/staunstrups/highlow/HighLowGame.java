package dk.staunstrups.highlow;

/**
 * This class implements the HighLow card game.
 * It is adapted from the JavaNotes textbook by J Staunstrup, Decemner 2014
 */
public class HighLowGame {
    int gamesPlayed = 0;		// Number of games user has played.
    int sumOfScores = 0;		// The sum of all the scores from all the games played.
    double averageScore= 0.0;	// Average score, computed by dividing sumOfScores by gamesPlayed.
    int scoreThisGame;       	// Score for current game
    Card currentCard;			// The current card, which the user sees.
    Card nextCard;				// The next card in the deck.  The user tries
								//    to predict whether this is higher or lower than the current card.
    int correctGuesses ;		// The number of correct predictions the  user has made.  At the end of the game,
    							//   this will be the user's score.
    Deck deck;
	
	public void gameInit() {
		gamesPlayed = 0;   
	    sumOfScores = 0;
	    averageScore= 0.0;
	}
	
	public GameResult newGame() {
		GameResult r= new GameResult();
		sumOfScores += scoreThisGame;
        gamesPlayed++;
        averageScore = ((double)sumOfScores) / gamesPlayed;    
        deck = new Deck();		// Get a new deck of cards, and store a reference to it in the variable, deck.
        deck.shuffle();			// Shuffle the deck into a random order before starting the game.
        
        correctGuesses = 0;
        currentCard = deck.dealCard();
        r.res= "The first card is the " + currentCard;
        r.gameover= false;
        return r;
	}
	
	public GameResult gameIteration(char guess) {
		 GameResult r= new GameResult();
		 nextCard = deck.dealCard();
         r.res= "The next card is " + nextCard + "\n";
         
         /* Check the user's prediction. */   
         if (nextCard.getValue() == currentCard.getValue()) {
            r.res= r.res + "The value is the same as the previous card."
            		 + " You lose on ties.  Sorry!"
            		 + "\nNumber of correct guesses:"+correctGuesses;
            r.gameover= true;
            return r; // End the game.
         }
         else if (nextCard.getValue() > currentCard.getValue()) {
            if (guess == 'H') {
                r.res= r.res + "\nYour prediction was correct.";
                correctGuesses++;
                r.gameover= false;
            }
            else {
            	r.res= r.res + "Your prediction was incorrect."
            			 + "\nNumber of correct guesses:"+correctGuesses;
            	r.gameover= true;
                return r; // End the game.
            }
         }
         else {  // nextCard is lower
            if (guess == 'L') {
            	r.res= r.res + "\nYour prediction was correct.";
                correctGuesses++;
                r.gameover= false;
            }
            else {
            	r.res= r.res + "Your prediction was incorrect."
            			 + "\nNumber of correct guesses:"+correctGuesses;
            	r.gameover= true;
                return r; // End the game..
            }
         }
         currentCard = nextCard;
         r.res= r.res+"\nWill the next card be higher or lower?\n";
         return r;
	}
}
 