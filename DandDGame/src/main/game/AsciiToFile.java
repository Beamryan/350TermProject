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
      while ((c = in.read()) != -1){
        if(c == 32){
          spaceCode = 1;
        }
        if(c == 116){
          spaceCode = 1 << 1;
        }
        if(c == 114){
          spaceCode = 1 << 2;
        }
        if(c == 105){
          spaceCode = 1 << 3;
        }
        if(c == 10){
          spaceCode = -1;
        }
        if(spaceCode != -1){
          String output = "" + spaceCode;
          out.write(spaceCode + "\n");
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

