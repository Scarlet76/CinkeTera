import java.util.ArrayList;
import java.awt.Color;

/**  
 *
 * @author  Adrian		CHALANSONNET
 * @author  Justine 	BONDU
 * @author  Luca 		LANGLOIS
 * @author  Trystan 	BAILLOBAY
 *
 **/

public class Graph
{
	private Controleur			ctrl;
	private ArrayList<Ile>      lstIle;
	private ArrayList<Voie>     lstVoie;
	private ArrayList<Voie>     lstVoieColorie;
	public  ArrayList<Region>   lstRegion;
	private boolean             colorVoie;
	private Color               colorJoueur;
	private String              coulCarte;
	private Ile                 ileSelect;

	public Graph(Controleur ctrl)
	{
		this.lstIle         =   new ArrayList<Ile> ();
		this.lstVoie        =   new ArrayList<Voie>();
		this.lstRegion      =   new ArrayList<Region>();
		this.colorVoie      =   false;
		this.ileSelect      =   null;
		this.lstVoieColorie =   new ArrayList<Voie>();
		this.coulCarte      =   null;
		this.colorJoueur    =   null;
		this.ctrl           =   ctrl;
	}

	public void setCoulCarte(String c) { this.coulCarte = c; }

	public Voie colorVoie(Voie v)
	{   
		if ( !this.colorVoie && v != null)
		{
			this.colorVoie = true;
			this.ctrl.getFrame().getPanelJoueur().getPanelControles().setlblIleEnCour( "Vous êtes sur :" + this.ctrl.getFrame().getPanelGr().getPremiereIleSelectionne().getNom());
			return v;
		}

		if ( v != null && v.adjacentColorie(this.colorJoueur))
		{
			this.ctrl.getFrame().getPanelJoueur().getPanelControles().setlblIleEnCour( "Vous êtes sur :" + this.ctrl.getFrame().getPanelGr().getPremiereIleSelectionne().getNom());
			return v; 
		}
		return null;
	}

	
	public Color             getColorJoueur()			{ return this.colorJoueur;    }
	public ArrayList<Ile>    getLstIle()				{ return this.lstIle;         }
	public ArrayList<Voie>   getLstVoie()				{ return this.lstVoie;        }
	public ArrayList<Region> getLstRegion()				{ return this.lstRegion;        }
	public ArrayList<Voie>   getLstVoieColorie()		{ return this.lstVoieColorie; }
	public String            getCoulCarte()          
	{ 
		if(this.coulCarte == null){ return null;}
		return this.coulCarte;      
	}
	public Ile             getIle(String nom)
	{
		for ( Ile i : this.lstIle )
			if( i.getNom().equals(nom) )
				return i;  
		return null;
	}

	public void  setColorJoueur(Color colorJoueur)	{ this.colorJoueur = colorJoueur; }
	public void  setIleSelect  (Ile   ileSelect)	{ this.ileSelect = ileSelect;     }

	public void  addVoie(Voie voie )				{ this.lstVoie.add(voie);         }
	public void  addIle (Ile  ile  )				{ this.lstIle .add(ile );         }
	public void  addRegion(Region region )			{ this.lstRegion.add(region);     }

	public int getPts()
	{        
		ArrayList<String> lstRegionCoul1 = new ArrayList<String>();
		ArrayList<String> lstRegionCoul2 = new ArrayList<String>();
		
		int   ptsTot, ptsCoul1, ptsCoul2 ;
		int   maxIleCoul1, maxIleCoul2 ;
		int   cptIles ;     
		int   bonusCoulRouge, bonusCoulBleu, bonus;    
		
		ptsTot = ptsCoul1 = ptsCoul2 = maxIleCoul1 = maxIleCoul2 = cptIles = bonusCoulRouge = bonusCoulBleu = bonus = 0;   
			
		//Init des couleurs utilisées et des Regions rencontrés
		for (Voie v : Voie.lstVoieColorie)
		{
			if ( v.getColorVoie().equals( this.ctrl.getTabColor()[0] ) )
			{
				if(! lstRegionCoul1.contains(v.getIleUn().getRegion().getNom() ) )
					lstRegionCoul1.add(v.getIleUn().getRegion().getNom());

				if(! lstRegionCoul1.contains(v.getIleDeux().getRegion().getNom()))
					lstRegionCoul1.add(v.getIleDeux().getRegion().getNom());

				ptsCoul1 += v.getBonus();
			}
				
			if ( v.getColorVoie().equals( this.ctrl.getTabColor()[1] ) )
			{
				if(! lstRegionCoul2.contains(v.getIleUn  ().getRegion().getNom()))
					lstRegionCoul2.add(v.getIleUn().getRegion().getNom());

				if(! lstRegionCoul2.contains(v.getIleDeux().getRegion().getNom()))
					lstRegionCoul2.add(v.getIleDeux().getRegion().getNom());

				ptsCoul2 += v.getBonus();
			}   
		}

		//Calcul des points
		for (String a : lstRegionCoul1)
		{
			for (Ile i : this.lstIle)
				if (i.getRegion().getNom().equals(a))
					for(Voie v : i.getLstVoie())
						if (v.getColorVoie().equals( this.ctrl.getTabColor()[0] ))
						{
							cptIles +=1;
							break;
						}
			if(cptIles>maxIleCoul1) 
				maxIleCoul1 = cptIles;
			cptIles = 0;
		}

		cptIles = 0;
		for (String a : lstRegionCoul2)
		{
			for (Ile i : this.lstIle)
				if (i.getRegion().getNom().equals(a))
					for(Voie v : i.getLstVoie())
						if (v.getColorVoie().equals( this.ctrl.getTabColor()[1] ))
						{
							cptIles +=1;
							break;
						}
			if(cptIles>maxIleCoul2) 
				maxIleCoul2 = cptIles;
			cptIles = 0;
		}


		//Atribution des 2pts bonus. 
		for ( Ile i : this.lstIle )
		{
			bonusCoulRouge = 0;
			bonusCoulBleu  = 0;
			for(Voie v : i.getLstVoie())
			{
				if(v.getColorVoie().equals(this.ctrl.getTabColor()[0]) )
					bonusCoulRouge++;
				if(v.getColorVoie().equals(this.ctrl.getTabColor()[1]) )
					bonusCoulBleu++;
			}
			if (bonusCoulRouge > 0 && bonusCoulBleu > 0)
				bonus += 2;
		}


		ptsCoul1 += maxIleCoul1*lstRegionCoul1.size();
		ptsCoul2 += maxIleCoul2*lstRegionCoul2.size();
		ptsTot = ptsCoul1 + ptsCoul2 + bonus;

		return ptsTot;
	}
}