package Game;

import java.io.IOException;
import java.io.PrintWriter;

public class HighScore {

	public static void main(String[] args)throws IOException {

		PrintWriter p = new PrintWriter ("HighScore.txt"); 
		p.print("Name");
		p.print(" 0");
		p.close();
	}

}
