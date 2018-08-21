package Game;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class Millionaire {

	// initialize global variables

	// score for entire game
	public static int score = 0;
	// ----------------------------------------------------------------------------------------

	// boolean variable for timer
	public static boolean flag = false;
	// ----------------------------------------------------------------------------------------

	// boolean variables to determine if topics are done
	public static boolean math = false;
	// ----------------------------------------------------------------------------------------
	public static boolean sci = false;
	// ----------------------------------------------------------------------------------------
	public static boolean eng = false;
	// ----------------------------------------------------------------------------------------
	public static boolean sprt = false;
	// ----------------------------------------------------------------------------------------
	public static boolean geo = false;
	// ----------------------------------------------------------------------------------------

	// counts number of questions done in each method
	public static int questionCount = 1;
	// ----------------------------------------------------------------------------------------

	// running timer multiple times
	public static Timer timer = new Timer();
	// ----------------------------------------------------------------------------------------

	// giving user option to continue game or not
	public static String[] opts = { "Yes", "No" };
	// ----------------------------------------------------------------------------------------

	// input name
	public static String resp = "";
	// -----------------------------------------------------------------------------------------

	public static void main(String[] args) throws Exception {

		JOptionPane.showMessageDialog(null, "WELCOME TO WHO WANTS TO BE A MILLIONAIRE");

		// inputing name
		resp = JOptionPane.showInputDialog("Enter name");

		JOptionPane.showMessageDialog(null, "Hi " + resp);
		
		// read text file with high score
		Scanner in = new Scanner(new File("HighScore.txt"));
		while (in.hasNext()) {
			PrintWriter p = new PrintWriter("HighScore.txt");
			String a = in.next();
			int b = Integer.parseInt(in.next());
			p.print(a);
			p.print(" " + b);
			p.close();
			// output player with highest score
			JOptionPane.showMessageDialog(null, a + " has the highscore of $" + (b * 20000));
		}
		in.close();
		// Method calls all other methods, main part of the program
		call();

	}

	// ----------------------------------------------------------------------------------------
	public static void highscore() throws Exception {

		// read name and score from high score file
		Scanner in = new Scanner(new File("HighScore.txt"));
		while (in.hasNext()) {
			String a = in.next();
			int b = Integer.parseInt(in.next());

			// if new score is greater than high score replace it in file

			if (score > b) {
				PrintWriter run = new PrintWriter("HighScore.txt");
				run.print(resp);
				run.print(" " + score);
				run.close();
				JOptionPane.showMessageDialog(null, "You beat the highscore");
				timer.cancel();
				timer.purge();
				System.exit(0);
			} else {
				timer.cancel();
				timer.purge();
				System.exit(0);
			}
		}
		in.close();
	}

	public static void call() throws Exception {

		int m = 0;

		// Different options available
		String picks[] = { "Math", "Geography", "Science", "Sports", "English" };

		// Run loop until all topics have been completed
		while (m <= 4) {

			// output all topics
			int options = JOptionPane.showOptionDialog(null, "Choose a topic", "Choose an option",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, picks, "");

			// if first button is picked, math == false determines if method has been done
			// or not
			if (options == 0 && math == false) {
				// if user picks math method, call math questions
				Math();
				m++;
				picks[0] = "";
			}

			// if first button is picked, geo == false determines if method has been done or
			// not
			if (options == 1 && geo == false) {
				// if user picks geography method, call geography questions
				Geography();
				// adds one to m so while loop can come to an end
				m++;
				// replaces topic name with blank
				picks[1] = "";
			}

			// if first button is picked, sci == false determines if method has been done or
			// not
			if (options == 2 && sci == false) {
				// if user picks science method, call science questions
				Science();
				m++;
				picks[2] = "";
			}

			// if first button is picked, sprt == false determines if method has been done
			// or not
			if (options == 3 && sprt == false) {
				// if user picks sports method, call sports questions
				Sports();
				m++;
				picks[3] = "";
			}

			// if first button is picked, eng == false determines if method has been done or
			// not
			if (options == 4 && eng == false) {
				// if user picks English method, call English questions
				English();
				m++;
				picks[4] = "";

			}

		}

		JOptionPane.showMessageDialog(null, "Congrations, you have won $1,000,000");

	}

	// ----------------------------------------------------------------------------------------

	public static void Math() throws Exception {

		// initialize variables
		int w, x, y, z, ans, input;

		JOptionPane.showMessageDialog(null,
				"Welcome to the math part of the game, you have 1 minute to answer all questions");
		JOptionPane.showMessageDialog(null,
				"For all division questions, you need to round the answer to the lowest whole number");

			// timer ---------------------------------------------------------------------

			int delay = 60000; // 1000 = 1 second

			// begin new timer
			timer = new Timer();

			timer.schedule(new TimerTask() {
				public void run() {
					try {
						// timer check method
						timerCheck();
					} catch (Exception e) {
						// makes sure everything is working, prevents errors
						e.printStackTrace();
					}
				}
			}, delay);

			// --------------------------------------------------------------------------

			// loop of questions, flag == false is for timer, if timer is still running or
			// not
			questionCount = 1;
			flag = false;
			while (questionCount <= 10 && flag == false) {

				// randomly generate 4 values
				w = (int) (Math.random() * (50) + 1);
				x = (int) (Math.random() * (50) + 1);
				y = (int) (Math.random() * 50);
				// generate random number to decide if it is add, subtract, multiply and divide
				z = (int) (Math.random() * (4l - 1 + 1) + 1);

				// if z is 1 do addition
				if (z == 1) {
					ans = w + x + y;
					input = Integer.parseInt(JOptionPane.showInputDialog(w + "+" + x + "+" + y));

					if (input == ans) {
						// increase score
						score++;
						JOptionPane.showMessageDialog(null, "Correct you have $" + score * 20000);
					} else {
						JOptionPane.showMessageDialog(null, "You lose the game");
						JOptionPane.showMessageDialog(null, "You win $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}

					// if z is 2 do subtraction
				} else if (z == 2) {
					// variable answer is w subtracted from x
					ans = w - x;
					input = Integer.parseInt(JOptionPane.showInputDialog(w + "-" + x));

					// if correct
					if (input == ans) {
						score++;
						JOptionPane.showMessageDialog(null, "Correct you have $" + score * 20000);
					} else {
						JOptionPane.showMessageDialog(null, "You lose the game");
						JOptionPane.showMessageDialog(null, "You win $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}

				}

				// if z is 3 do multiplication
				else if (z == 3) {
					// generate random numbers again but from 1 to 10
					w = (int) (Math.random() * 10);
					x = (int) (Math.random() * 10);

					// variable answer is two new variables multiplied
					ans = w * x;
					input = Integer.parseInt(JOptionPane.showInputDialog(w + "*" + x));

					// if correct
					if (input == ans) {
						score++;
						JOptionPane.showMessageDialog(null, "Correct you have $" + score * 20000);
					} else {
						JOptionPane.showMessageDialog(null, "You lose the game");
						JOptionPane.showMessageDialog(null, "You win $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}

				}

				// if z is 4 do division
				else if (z == 4) {
					// determine which number is higher, we don't want answers between 0 and 1
					// if w is greater
					if (w >= x) {
						// answer is w/x
						ans = w / x;
						input = Integer.parseInt(JOptionPane.showInputDialog(w + "/" + x));

						// if correct
						if (input == ans) {
							score++;
							JOptionPane.showMessageDialog(null, "Correct you have $" + score * 20000);
						} else {
							JOptionPane.showMessageDialog(null, "You lose the game");
							JOptionPane.showMessageDialog(null, "You win $" + (score * 20000) / 2);
							score = (int) (score / 2.0);
							// call high score method to see if score was beaten before ending game
							highscore();
						}
					}
					// if x is greater
					else {
						// answer is x/w
						ans = x / w;
						input = Integer.parseInt(JOptionPane.showInputDialog(x + "/" + w));
						// if correct
						if (input == ans) {
							score++;
							JOptionPane.showMessageDialog(null, "Correct you have $" + score * 20000);
						} else {
							JOptionPane.showMessageDialog(null, "You lose the game");
							JOptionPane.showMessageDialog(null, "You win $" + (score * 20000) / 2);
							score = (int) (score / 2.0);
							// call high score method to see if score was beaten before ending game
							highscore();
						}
					}

				}
				// add 1 to question count, once it hits 10 questions it will end the loop
				questionCount++;
				System.out.println(questionCount);
			}
			// once questions are done end timer
			timer.cancel();
			timer.purge();
			JOptionPane.showMessageDialog(null, "Congratulations, you beat the math round!!!");
			JOptionPane.showMessageDialog(null, "You currently have $" + score * 20000);
			// ask user if they want to continue, if not then the game ends
			int cont = JOptionPane.showOptionDialog(null, "Do you want to continue", "Choose an option",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opts, "");
			// if user picks option 1 and plays game
		
		if (cont == 0) {
			// set math boolean to true so topic can not be done again
			math = true;
			}
		// if they decide to quit game, get there money back
		else {
			JOptionPane.showMessageDialog(null, "You have decided to quit the game");
			JOptionPane.showMessageDialog(null, "You win $" + (score * 20000));
			highscore();
		}
	}

	// ----------------------------------------------------------------------------------------

	public static void timerCheck() throws Exception {
		// timer check method
		flag = true;
		// if timer is done and they are still on one of the questions
		if (questionCount <= 10) {
			JOptionPane.showMessageDialog(null, "Time is up");
			JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
			// divide score in half
			score = (int) (score / 2.0);
			// call high score method to see if score was beaten before ending game
			highscore();
		}
	}

	// ----------------------------------------------------------------------------------------

	public static void Geography() throws Exception {

		// reset number of questions completed at 1
		questionCount = 1;
		flag = false;

		// create array of questions
		String[] quest = { "Capital of Canada", "Capital of America", "Number of Continents",
				"Largest Country in the world", "Coutry with the Most population", "Number of people on the world",
				"Chinas Population", "What Continent is Mexico in", "Largest Continent", "Which is not a continent" };

		// create array of answers corresponding to the questions
		String[] ans = { "Ottawa", "Washington DC", "7", "Russia", "China", "7 Billion", "1.3 Billion", "North America",
				"Asia", "Eurasia" };

		// create array of incorrect answers corresponding to the questions
		String[] o1 = { "Toronto", "New York", "6", "Canada", "India", "6 Billion", "1 Billion", "South America",
				"North America", "Asia" };

		// create array of incorrect answers corresponding to the questions
		String[] o2 = { "Montreal", "Chicago", "8", "China", "USA", "8 Billion", "3 Billion", "Africa", "Europe",
				"Africa" };

		JOptionPane.showMessageDialog(null, "This is the Geography round");
		JOptionPane.showMessageDialog(null,
				"You will be given multiple choice questions which you will need to answer");
		JOptionPane.showMessageDialog(null, "You have 1 minute");

		// start timer
		int delay = 60000;

		// start new timer
		timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				try {
					// calls method if timer is completed
					timerCheck();
				} catch (Exception e) {
					// finds any mistakes
					e.printStackTrace();
				}
			}

		}, delay);

		// -----------------------------------------------------------------------------------------
		// create int array with 10 values
		int[] array1 = new int[10];

		// loop of questions, flag == false is for timer, if timer is still running or
		// not
		while (questionCount <= 10 && flag == false) {

			// runs a for loop to generate random numbers and not repeat
			for (int i = 0; i < array1.length; i++) {
				boolean check = false;
				do {
					// generate random number
					int randomNumber = (int) (Math.random() * (9 - 0 + 1) + 0);
					// store that random number as they value in array1
					array1[i] = randomNumber;
					check = false;

					// check if number has been repeated
					for (int j = 0; j < i; j++) {
						if (array1[i] == array1[j]) {
							// if not repeated make check true
							check = true;
						}
					}
					// run code while it is true
				} while (check == true);

				// generate random number from 1 to 3, used to determine order of the options
				int y = (int) (Math.random() * ((3 - 1) + 1) + 1);

				// if 1 is determined and timer running, answer will be option a
				if (y == 1 && flag == false) {

					// make new array with order of options available
					String array[] = { ans[array1[i]], o1[array1[i]], o2[array1[i]] };

					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o1[array1[i]]);

					// if correct
					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						score++;
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

						// if incorrect
					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();

						// if incorrect
					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}

				// if 2 is generated and timer running, answer will be option b
				else if (y == 2 && flag == false) {

					// make new array with order of options available
					String array[] = { o1[array1[i]], ans[array1[i]], o2[array1[i]] };

					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, ans[array1[i]]);

					// if incorrect
					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}

					// if correct
					else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						score++;
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

						// if incorrect
					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}

				// if 3 is generated and timer running, answer will be option c
				else if (y == 3 && flag == false) {

					// make new array with order of options available
					String array[] = { o1[array1[i]], o2[array1[i]], ans[array1[i]] };

					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o2[array1[i]]);

					// if incorrect
					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}

					// if incorrect
					else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();

						// if correct
					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						score++;
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
					}
				}
			}
		}

		// end timer once all questions are completed
		timer.cancel();
		timer.purge();
		JOptionPane.showMessageDialog(null, "Congratulations, you beat the geography round!!!");
		JOptionPane.showMessageDialog(null, "You currently have $" + score * 20000);

		// give user option to continue
		int cont = JOptionPane.showOptionDialog(null, "Do you want to continue", "Choose an option",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opts, "");

		if (cont == 0) {
			// disables geography method
			geo = true;
		} else {
			JOptionPane.showMessageDialog(null, "You have decided to leave, you win $" + score * 20000);
			// call high score method to see if score was beaten before ending game
			highscore();
		}
	}

	// ----------------------------------------------------------------------------------------

	public static void Science() throws Exception {

		// reset question count to 1
		questionCount = 1;

		// set boolean flag to false
		flag = false;

		// array of questions, answers and incorrect options
		String[] quest = { "What percentage of the earth is covered by water?", "What year was penicillin invented?",
				"Who was the first to walk on the moon?", "How many elements in the periodic table?",
				"What does Se stand for?", "How many layers does the earth have?",
				"Which of the following is an example of a vector quantity?", "What is the heaviest element?",
				"What year were uv rays invented?", "What year did charles darwin die?" };

		String[] ans = { "71 percent", "1928", "Neil Armstrong", "118", "Selenium", "4", "Velocity", "Ununoctium ",
				"1801", "1882" };

		String[] o1 = { "74 percent", "1856", "Chris Hadfield", "123", "Serenium", "3", "Volume", "Uranium", "1863",
				"1798" };

		String[] o2 = { "83 percent", "1934", "John Davis", "116", "Selenide", "2", "Mass", "Mercury", "1923", "1936" };

		JOptionPane.showMessageDialog(null, "This is the Science round");
		JOptionPane.showMessageDialog(null,
				"You will be given multiple choice questions which you will need to answer");
		JOptionPane.showMessageDialog(null, "You have 1 minute");

		// start timer

		// set delay to 60000 which is 60 seconds
		int delay = 60000;

		// start a new timer
		timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				try {
					// call method once timer is done
					timerCheck();
				} catch (Exception e) {
					// check and catch any errors
					e.printStackTrace();
				}
			}

		}, delay);

		// -----------------------------------------------------------------------------------------
		// create int array
		int[] array1 = new int[10];

		// run a while loop to ask questions, only do this while timer is running
		while (questionCount <= 10 && flag == false) {

			// for loop generates random number
			for (int i = 0; i < array1.length; i++) {
				boolean check = false;
				do {
					// makes sure number isn't repeated
					int randomNumber = (int) (Math.random() * (9 - 0 + 1) + 0);
					array1[i] = randomNumber;
					check = false;

					for (int j = 0; j < i; j++) {
						// if number has been used, keep generating until a new number is generated
						if (array1[i] == array1[j]) {
							// if numbers are the same keep check as true
							check = true;
						}
					}
				}
				// keep generating numbers until a new number is generated and check is false
				while (check == true);

				// generate random number from 1 to 3
				int y = (int) (Math.random() * ((3 - 1) + 1) + 1);

				// if number is 1 and timer is still running
				if (y == 1 && flag == false) {

					// store answers in array
					String array[] = { ans[array1[i]], o1[array1[i]], o2[array1[i]] };

					// output choices depending on order of array
					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o1[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to global variable score
						score++;
						// add 1 to question count
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}
				
				// if number is 2 and timer is still running
				else if (y == 2 && flag == false) {
					
					// store answers in array
					String array[] = { o1[array1[i]], ans[array1[i]], o2[array1[i]] };
					
					// output choices depending on order of array
					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, ans[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} 
					
					else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to global variable score
						score++;
						// add 1 to question count
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

					}
					
					else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}

				// if number generated is 3 and timer is still running
				else if (y == 3 && flag == false) {

					// create array with answer and false options
					String array[] = { o1[array1[i]], o2[array1[i]], ans[array1[i]] };

					// output options based on order of array
					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o2[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
					
					else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} 
					
					else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to global variable score
						score++;
						// add 1 to question count
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
					}
				}
			}
		}
		//  end timer if it is still running
		timer.cancel();
		timer.purge();
		JOptionPane.showMessageDialog(null, "Congratulations, you beat the Science round!!!");
		JOptionPane.showMessageDialog(null, "You currently have $" + score * 20000);
		int cont = JOptionPane.showOptionDialog(null, "Do you want to continue", "Choose an option",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opts, "");

		if (cont == 0) {
			// if they decide to continue set science to true so method is disabled
			sci = true;
		} else {
			JOptionPane.showMessageDialog(null, "You have decided to leave, you win $" + score * 20000);
			// call high score method to see if score was beaten before ending game
			highscore();
		}
	}

	// ----------------------------------------------------------------------------------------

	public static void Sports() throws Exception {
		
		// set question count to 1
		questionCount = 1;
		
		// make timer boolean false so it can still run
		flag = false;

		// create string arrays to store questions, answers and false options
		String[] quest = { "Who is the greatest scorer in NHL history?", "What year was Kobe Bryant drafted?",
				"Who did Argentina play in the FIFA world cup finals?", "What country is Alex Ovechkin from?",
				"How many championships does Tom Brady have?", "How many times did Tiger Woods win the masters?",
				"What was Muhammad Alis boxing record?", "Which NBA player has the most championships",
				"What year did Serena Williams win her first major?",
				"How many stanley cups does Sidney Crosby have?" };

		String[] ans = { "Wayne Gretzky", "1996", "Germany", "Russia", "5", "3", "56-5", "Bill Russell ", "1999", "3" };

		String[] o1 = { "Sidney Crosby", "1998", "Brazil", "Slovakia", "4", "1", "56-4", "Jason Kidd", "2001", "2" };

		String[] o2 = { "Mario Lemieux", "1997", "Spain", "Sweeden", "3", "2", "56-3", "Kobe Bryant", "1997", "4" };

		JOptionPane.showMessageDialog(null, "This is the Sports round");
		JOptionPane.showMessageDialog(null,
				"You will be given multiple choice questions which you will need to answer");
		JOptionPane.showMessageDialog(null, "You have 1 minute");

		// start timer

		// create delay of 60000 which is 60 seconds
		int delay = 60000;

		// start new timer so previous ones don't run
		timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				try {
					// call method once timer comes to an end
					timerCheck();
				} catch (Exception e) {
					// catch any errors that are made
					e.printStackTrace();
				}
			}

		}, delay);

		// -----------------------------------------------------------------------------------------
		// create array with 10 numbers, this will represent question numbers from array
		int[] array1 = new int[10];
		
		// run loop while questions are less than 10 and timer is running
		while (questionCount <= 10 && flag == false) {
			// run a for loop length of array
			for (int i = 0; i < array1.length; i++) {
				boolean check = false;
				do {
					// generate random number from 0 to 9 to represent array
					int randomNumber = (int) (Math.random() * (9 - 0 + 1) + 0);
					// store number generated in i value of array
					array1[i] = randomNumber;
					check = false;

					// check if any numbers have been repeated
					for (int j = 0; j < i; j++) {
						// checks if any numbers are the same
						if (array1[i] == array1[j]) {
							check = true;
						}
					}
				// keep running loop until new numbers generated each time
				} while (check == true);

				// generate random number from 1 to 3
				int y = (int) (Math.random() * ((3 - 1) + 1) + 1);

				// if number generated is 1 and timer is still running
				if (y == 1 && flag == false) {

					// create array with answer and false options
					String array[] = { ans[array1[i]], o1[array1[i]], o2[array1[i]] };

					// output choices based on order of array
					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o1[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to global variable score
						score++;
						// add 1 to question count
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						// divide score in half as they only get half the money
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}

				// if number generated is 2 and timer is still running
				else if (y == 2 && flag == false) {
					
					// create array with answer and false options
					String array[] = { o1[array1[i]], ans[array1[i]], o2[array1[i]] };

					// output choices based on order of array
					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, ans[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						// divide score in half as they only get half the money
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to global variable score
						score++;
						// add 1 to question count
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						// divide score in half as they only get half the money
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}
				
				// if number generated is 3 and timer is still running
				else if (y == 3 && flag == false) {

					// create array with answer and false options
					String array[] = { o1[array1[i]], o2[array1[i]], ans[array1[i]] };

					// output choices based on order of array
					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o2[array1[i]]);

						if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						// divide score in half as they only get half the money
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} 
					
						else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						// divide score in half as they only get half the money
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
					
						else if (choice == 2) {

						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to global variable score
						score++;
						// add 1 to question count
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
					}
				}
			}
		}
		// end timer once all questions are done
		timer.cancel();
		timer.purge();
		JOptionPane.showMessageDialog(null, "Congratulations, you beat the Sports round!!!");
		JOptionPane.showMessageDialog(null, "You currently have $" + score * 20000);
		int cont = JOptionPane.showOptionDialog(null, "Do you want to continue", "Choose an option",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opts, "");

		if (cont == 0) {
			// if decided to continue, disabled method
			sprt = true;
		} else {
			JOptionPane.showMessageDialog(null, "You have decided to leave, you win $" + score * 20000);
			// call high score method to see if score was beaten before ending game
			highscore();
		}
	}

	// ----------------------------------------------------------------------------------------

	public static void English() throws Exception {

		// reset question count to 1
		questionCount = 1;
		// set flag global variable to false for timer
		flag = false;

		JOptionPane.showMessageDialog(null, "This is English");
		JOptionPane.showMessageDialog(null, "You will be given a variety of questions");
		JOptionPane.showMessageDialog(null, "You have 1 minute");

		// array of questions, answers and incorrect options
		String[] quest = {
				" From shakespeares plays, which contains the famous quote 'O, beware, my lord, " + "of jealousy'",
				"'The student splashes into the water' is an example of", "How many letters are in the alphabet",
				"The words 'to' and 'two' are", "'Just Do It' by Nike is a" };

		String[] ans = { "Othello", "Onomatopoeia", "26", "Homonyms", "Slogan" };

		String[] o1 = { "Hamlet", "Hyperbole", "24", "Synonyms", "Motto" };

		String[] o2 = { "Macbeth", "Anaphora", "8", "Antonyms", "Quote" };

		// start timer

		// set delay of 60000 which is 60 seconds
		int delay = 60000;

		// start new timer
		timer = new Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				try {
					// call method once timer is done
					timerCheck();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}, delay);

		// create int array with 10 numbers
		int[] array1 = new int[10];

		// run a for loop depending on length of array
		for (int i = 0; i < array1.length; i++) {
			boolean check = false;
			do {
				// generate number from 0 to 9
				int randomNumber = (int) (Math.random() * (9 - 0 + 1) + 0);
				array1[i] = randomNumber;
				check = false;

				// make sure no number is generated twice
				for (int j = 0; j < i; j++) {
					// if number has been repeated run whole loop again
					if (array1[i] == array1[j]) {
						check = true;
					}
				}
				} 
			// keep running generator until a new number is generated
			while (check == true);

			if (array1[i] <= 4) {
				// generate number from 1 to 3
				int y = (int) (Math.random() * ((3 - 1) + 1) + 1);

				// if number generated is 1 and timers still running
				if (y == 1 && flag == false) {

					// create array with answers and incorrect options
					String array[] = { ans[array1[i]], o1[array1[i]], o2[array1[i]] };

					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o1[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add to global variable score
						score++;
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}

				// if number generated is 2 and timer still running
				else if (y == 2 && flag == false) {

					// create array with answer and options
					String array[] = { o1[array1[i]], ans[array1[i]], o2[array1[i]] };

					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, ans[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to total score
						score++;
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);

					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					}
				}

				// if number generated is 3 and timers still running
				else if (y == 3 && flag == false) {

					// create array with answer and options
					String array[] = { o1[array1[i]], o2[array1[i]], ans[array1[i]] };

					int choice = JOptionPane.showOptionDialog(null, quest[array1[i]], "Choose an option",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, array, o2[array1[i]]);

					if (choice == 0) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "That was wrong");
						JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
						score = (int) (score / 2.0);
						// call high score method to see if score was beaten before ending game
						highscore();
					} else if (choice == 2) {
						JOptionPane.showMessageDialog(null, "You got it right!!");
						// add 1 to total score
						score++;
						questionCount++;
						JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
					}
				}
			}
			complex(array1[i]);
		}

		// end timer
		timer.cancel();
		timer.purge();
		JOptionPane.showMessageDialog(null, "Congratulations, you beat the English round!!!");
		JOptionPane.showMessageDialog(null, "You currently have $" + score * 20000);

		int cont = JOptionPane.showOptionDialog(null, "Do you want to continue", "Choose an option",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opts, "");

		if (cont == 0) {
			// if wanting to continue, disable method
			eng = true;
		} else {
			JOptionPane.showMessageDialog(null, "You have decided to leave, you win $" + score * 20000);
			// compare texts to organize alphabetically
			highscore();
		}

	}
	
	public static void complex(int x1) throws Exception {
		 if (x1 == 5) {
			String s = "How many words are in this line?";
			int num = 0;
			int input = Integer.parseInt(JOptionPane.showInputDialog("How many words are in this line?"));

			// implement tokenizer
			StringTokenizer word = new StringTokenizer(s);
			// count number of words in string 
			while (word.hasMoreTokens()) {
				// keep running as long as there are more words
				word.nextToken();
				num++;
			}

			if (input == num) {
				JOptionPane.showMessageDialog(null, "You got it right!!");
				// add 1 to global variable score
				score++;
				questionCount++;
				JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
			} else {
				JOptionPane.showMessageDialog(null, "That was wrong");
				JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
				score = (int) (score / 2.0);
				// call high score method to see if score was beaten before ending game
				highscore();
			}

		}

		else if (x1 == 6) {
			String s = "The horse is fast";
			// count length of string after the eight character
			int lremain = s.substring(8).length();
			lremain--;
			System.out.println(lremain);
			int input = Integer.parseInt(JOptionPane
					.showInputDialog("How many characters come after the " + "second 'e' in the phrase: " + s));

			if (input == lremain) {
				JOptionPane.showMessageDialog(null, "You got it right!!");
				// add 1 to global variable score
				score++;
				questionCount++;
				JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
			} else {
				JOptionPane.showMessageDialog(null, "That was wrong");
				JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
				score = (int) (score / 2.0);
				// call high score method to see if score was beaten before ending game
				highscore();
			}

		} else if (x1 == 7) {
			String answer = "";
			String wrd = "Chocolate";
			// run for loop to run word backwards
			for (int k = wrd.length() - 1; k >= 0; k--) {
				answer = answer + (wrd.charAt(k));
			}
			// convert input to lower case
			String input = JOptionPane.showInputDialog("Enter the word 'Chocolate' backwards").toLowerCase();

			if (input.toLowerCase().equals(answer.toLowerCase())) {
				JOptionPane.showMessageDialog(null, "You got it right!!");
				// add 1 to global variable score
				score++;
				questionCount++;
				JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
			}

			else {
				JOptionPane.showMessageDialog(null, "That was wrong");
				JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
				score = (int) (score / 2.0);
				// call high score method to see if score was beaten before ending game
				 highscore();
			}

		} else if (x1 == 8) {
			String s = "The cat sat after eating a bat";
			// cut off first 8 characters
			String left = s.substring(8);
			String input = JOptionPane.showInputDialog("Enter the sentence after the second word " + s).trim();

			if (input.toLowerCase().equals(left.toLowerCase())) {
				JOptionPane.showMessageDialog(null, "You got it right!!");
				// add 1 to global variable string
				score++;
				questionCount++;
				JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
			} else {
				JOptionPane.showMessageDialog(null, "That was wrong");
				JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
				score = (int) (score / 2.0);
				// call high score method to see if score was beaten before ending game
				highscore();
			}

		}

		else if (x1 == 9) {
			String ans1 = "";
			String x = "Air";
			String y = "Apples";
			String z = "Adjacent";
			x.compareTo(y);
			x.compareTo(z);
			y.compareTo(z);

			// compare texts to organize alphabetically
			if (x.compareTo(y) < 0 && x.compareTo(z) < 0) {
				ans1 = ans1 + x;
				if (y.compareTo(z) < 0) {
					ans1 = ans1 + " " + y + " " + z;
				} else {
					ans1 = ans1 + " " + z + " " + y;
				}
			}
			
			// compare texts to organize alphabetically
			else if (x.compareTo(y) > 0 && y.compareTo(z) < 0) {
				ans1 = ans1 + y;
				if (x.compareTo(z) < 0) {
					ans1 = ans1 + " " + x + " " + z;
				} else {
					ans1 = ans1 + " " + z + " " + x;
				}
			}

			// compare texts to organize alphabetically
			else if (y.compareTo(z) > 0 && x.compareTo(z) > 0) {
				ans1 = ans1 + z;
				if (x.compareTo(y) < 0) {
					ans1 = ans1 + " " + x + " " + y;
				} else {
					ans1 = ans1 + " " + y + " " + x;
				}
			}

			String input = JOptionPane
					.showInputDialog("Organize the words alphabetically: " + x + " " + y + " " + z).trim();

			System.out.println(ans1);
			System.out.println(input);

			// check if user input matches computer
			if (input.toLowerCase().equals(ans1.toLowerCase())) {
				JOptionPane.showMessageDialog(null, "You got it right!!");
				// add 1 to global variable score
				score++;
				questionCount++;
				JOptionPane.showMessageDialog(null, "You have $" + score * 20000);
			} else {
				JOptionPane.showMessageDialog(null, "That was wrong");
				JOptionPane.showMessageDialog(null, "You get $" + (score * 20000) / 2);
				score = (int) (score / 2.0);
				// compare texts to organize alphabetically
				highscore();
			}

		}
	}
	
}