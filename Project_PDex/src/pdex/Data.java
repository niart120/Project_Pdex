package pdex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {
	List<Pokemon> pokeData= new ArrayList<>();
	List<Move> moveData = new ArrayList<>();

	public Data(){

		 try {
		      File f = new File("./lib/pokemon.csv");
		      BufferedReader br = new BufferedReader(new FileReader(f));
		      String line;
		      while ((line = br.readLine()) != null) {
		        String[] tempData = line.split(",", 0);
		        int[] data = new int[tempData.length-1];
		        for(int i=0;i<tempData.length-1;i++){
		        	data[i] = Integer.valueOf(tempData[i+1]);
		        }
		        pokeData.add(new Pokemon(tempData[0],data));
		      }
		      br.close();
		    } catch (IOException e) {
		      System.out.println(e);
		    }

		 try {
		      File f = new File("./lib/moves.csv");
		      BufferedReader br = new BufferedReader(new FileReader(f));
		      String line;
		      while ((line = br.readLine()) != null) {
		        String[] tempData = line.split(",", 0);
		        int[] data = new int[tempData.length-1];
		        for(int i=0;i<tempData.length-1;i++){
		        	data[i] = Integer.valueOf(tempData[i+1]);
		        }
		        moveData.add(new Move(tempData[0],data));
		      }
		      br.close();
		    } catch (IOException e) {
		      System.out.println(e);
		    }

	}

	public List<String> searchByName(String s){
		List<String> data= new ArrayList<>();
		for(Pokemon poke: pokeData) {
			if(poke.getName().contains(s)){
				data.add(poke.getName());
			}
		}
		return data;
	}

	public boolean isPokeNameOnList(String s) {
		for(Pokemon poke: pokeData) {
			if(poke.getName().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public Pokemon getPokeByName(String s) {
		for(Pokemon poke: pokeData) {
			if(poke.getName().equals(s)) {
				return poke;
			}
		}
		return null;
	}

}
