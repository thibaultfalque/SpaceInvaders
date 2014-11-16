package niveau;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Strategie.StrategieDoLogic;
import Strategie.StrategieMove;
import entities.AlienEntity;


public class UsineAlien {
	private StrategieMove move;
	private StrategieDoLogic doLogic;
	private ArrayList<AlienEntity> alien;
	private int alienCount =0;
	private String pathTexture;
	private ArrayList<String> tabFormation;
	public UsineAlien(String pathTexture,String pathFichier,StrategieMove move,StrategieDoLogic dl){
		alien=new ArrayList<AlienEntity>();
		this.move=move;
		this.doLogic=dl;
		this.pathTexture=pathTexture;
		tabFormation=new ArrayList<String>();
		alien=new ArrayList<AlienEntity>();
		try {
			BufferedReader br=ouvrirFichier(pathFichier);
			lireFichier(br);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<AlienEntity> getAlien() {
		return alien;
	}
	public int getAlienCount() {
		return alienCount;
	}
	public void createArrayAlien(Niveau game) {
		alienCount=0;
		int i=0;
		for(String s:tabFormation){
			for(int j=0;j<s.length();j++){
				if(s.charAt(j)=='1'){
					AlienEntity a=new AlienEntity(game, pathTexture, 15*j+j*64, 15*i+i*64, move, doLogic,75,40);
					alien.add(a);
					alienCount++;
				}
			}
			i++;
		}

	}
	
	private BufferedReader ouvrirFichier(String fichier) throws Exception{
		try{
			InputStream ips=new FileInputStream(fichier);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			return br;
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		return null;
	}
	private void lireFichier(BufferedReader br) throws IOException{
		String ligne;
		while((ligne=br.readLine())!=null){
			tabFormation.add(ligne);
		}
		br.close();
	}
	

}
