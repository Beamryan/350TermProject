package main.game;
import java.io.*;

public class AsciiToFile {
  public static void main(String args[]) throws IOException {
    
    FileInputStream in = null;
    Writer out = null;
    
    try {
      in = new FileInputStream("asciiMap.txt");
      out = new FileWriter("output.txt");
      
      int c;
      int spaceCode = 0;
      int itemCode = 0;
      
//      In map creation from asciiMap.txt:
//	  	  ',' (44) are spaces
//    	  '!' (33) are trees
//    	  '#' (35) are rocks
//    	  letters correspond to a specific item 
      
      while ((c = in.read()) != -1){
        if(c == 44){
          spaceCode = 1;
        }
        if(c == 33){
          spaceCode = 1 << 1;
        }
        if(c == 35){
          spaceCode = 1 << 2;
        }
        if(c > 64 && c < 123){
          spaceCode = 1 << 3;
          itemCode = c - 64; //makes item inventory go from 1-58
        }
        if(c == 10){
          spaceCode = -1;
        }
        if(spaceCode != -1){
          String output = spaceCode + " " + itemCode;
          out.write(output + "\n");
          itemCode = 0;
        }
      }
    }
    finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }
}

