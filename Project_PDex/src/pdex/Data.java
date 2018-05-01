package pdex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	ArrayList<Pokemon> pokeData= new ArrayList<>();
	ArrayList<Move> moveData = new ArrayList<>();

	public Data(){
		 try {
		      File f = new File("Pokemon.csv");
		      BufferedReader br = new BufferedReader(new FileReader(f));
		      String line;
		      while ((line = br.readLine()) != null) {
		        String[] tempData = line.split(",", 0);
		        int[] data = new int[tempData.length-1];
		        for(int i=0;i<tempData.length-1;i++){
		        	data[i] = Integer.valueOf(tempData[i+1]);
		        }
		        pokeData.add(new Pokemon(tempData[1],data));
		      }
		      br.close();
		    } catch (IOException e) {
		      System.out.println(e);
		    }

		 try {
		      File f = new File("Moves.csv");
		      BufferedReader br = new BufferedReader(new FileReader(f));
		      String line;
		      while ((line = br.readLine()) != null) {
		        String[] tempData = line.split(",", 0);
		        int[] data = new int[tempData.length-1];
		        for(int i=0;i<tempData.length-1;i++){
		        	data[i] = Integer.valueOf(tempData[i+1]);
		        }
		        moveData.add(new Move(tempData[1],data));


		      }
		      br.close();
		    } catch (IOException e) {
		      System.out.println(e);
		    }

	}

}
