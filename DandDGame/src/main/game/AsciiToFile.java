package main.game;
import java.io.*;

/**
 * Class to convert map input to output file.
 *
 */
public class AsciiToFile {
	
  /**
 * @throws IOException for reading map file and writing output.
 */
void run() throws IOException {
    
    FileInputStream in = null;
    Writer out = null;
    
    try {
      in = new FileInputStream("asciiMap.txt");
      out = new FileWriter("output.txt");
      
      int c;
      int spaceCode = 0;
      int itemCode = 0;
      int monsterCode = 0;
      
//      In map creation from asciiMap.txt:
//	  	  ',' (44) are spaces
//    	  '!' (33) are trees
//    	  '#' (35) are rocks
//		  '~' (126) is water
//    	  letters correspond to a specific item (excluding X)
//		  numbers and :;<=>?@ chars are monsters
      
      while ((c = in.read()) != -1) {
    	  
    	if (c == 88) { //cleared
    		spaceCode = 1;
    	} else if (c == 44) { //space
          spaceCode = 1 << 1;
        } else if (c == 33) { //tree
          spaceCode = 1 << 2;
        } else if (c == 35) { //rock
          spaceCode = 1 << 3;
        } else if (c == 126) { //water
            spaceCode = 1 << 4;
        } else if (c > 64 && c < 123) { //item
          spaceCode = 1 << 1;
          itemCode = c - 64; //makes item inventory go from 1-57
        } else if (c > 47 && c < 65) { //monster
            spaceCode = 1 << 1;
            monsterCode = c - 47; // monster values range 1 - 16
        } else if (c == 10) { //new line
          spaceCode = -1;
        }

        if (spaceCode != -1) {
          String output = spaceCode + " " + itemCode + " " + monsterCode;
          out.write(output + "\n");
          itemCode = 0;
          monsterCode = 0;
        }
      }
    } finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }
  
  /**
 * @param args none
 * @throws IOException for exceptions thrown by running AsciiToFile class
 */
public static void main(final String[] args) throws IOException {
	  AsciiToFile run = new AsciiToFile();
	  run.run();
  }
}
