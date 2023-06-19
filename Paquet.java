import java.util.ArrayList;
import java.util.Collections;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class Paquet
{
	private ArrayList<Carte>    paquet;
	private boolean 			scenario;	


	public Paquet(int num)
	{
		this.paquet	= new ArrayList<Carte>();
		
		if ( num == 0 )
		{
			this.initPioche();
			this.scenario = false;
		}
			
			
		if ( num == 1 )
		{
			this.paquet.clear();
			this.paquet	= new ArrayList<Carte>();
			this.scenario = false;
		}

		if ( num == 50 )
		{
			this.paquet.clear();
			this.paquet	= new ArrayList<Carte>();
			this.scenario = true;
		}

		if ( num == 5 )
		{
			this.initPioche();
			this.echangeCarte(0, 3);
			this.echangeCarte(1, 4);
			this.scenario = true;
		}
		
		if ( num == 6 ) //POUR SCENARIO 1
		{
			this.initPioche();
			this.echangeCarte(0, 2); 
			this.echangeCarte(1, 3); 
			this.echangeCarte(2, 3); 
			this.echangeCarte(3, 4); 
			this.scenario = true;
		}

		if ( num == 7 )
		{
			this.initPioche();
			this.scenario = false;
		}

		if ( num == 10 )
		{
			this.initPioche();
			this.paquetJaune();
			this.scenario = true;
		}

		if ( num == 11 )
		{
			this.initPioche();
			this.paquetSansJaune();
			this.scenario = true;
		}


		
	}

	public void initPioche()
	{
		new Carte().resetNUM();
		this.paquet.clear();
		this.paquet	= new ArrayList<Carte>();
		
		for ( int cpt=1; cpt <= 10; cpt++ )		
			this.addCarte(new Carte());	
	}

	public void melanger() 
	{ 
		if ( this.taille() != 0 )
			Collections.shuffle(this.paquet); 		
	}

	public Carte piocher()
	{
		if ( ! this.scenario )
			this.melanger();

		if ( this.taille() == 0 )
			return null;
		return this.paquet.remove(0);
	}


	public boolean verifLstCarteARRET()
	{
		boolean bool = false;
		for ( Carte val : this.paquet)
			if ( val.getType() == "Primaire" )
				bool = true;
		return bool;
	}


	public ArrayList<Carte>	getPaquet()				{	return this.paquet;							}
	public Carte			getCarte(int indice)	{	return this.paquet.get(indice);				}
	public Carte			getLast()				{	return this.paquet.get(this.taille()-1);	}
	public boolean			getScenario()			{	return this.scenario;	}
	public void				addCarte(Carte carte)	{	this.paquet.add(carte);						}
	public int				taille()				{	return this.paquet.size();					}



	public void init2()
	{
		this.echangeCarte(0, 1);
		this.echangeCarte(1, 2);
		this.echangeCarte(2, 3);
	}


	public void echangeCarte(int ind1, int ind2)
	{
		Carte c1, c2;

		if ( ind1 < ind2 )
		{
			c2 = this.paquet.remove(ind2);
			c1 = this.paquet.remove(ind1);
			this.paquet.add(ind1, c2);
			this.paquet.add(ind2, c1);
		}
		else 
		{
			c1 = this.paquet.remove(ind1);
			c2 = this.paquet.remove(ind2);
			this.paquet.add(ind1, c2);
			this.paquet.add(ind2, c1);
		}
	}

	public void paquetJaune()
	{
		this.initPioche();
		this.scenario = true;
		this.paquet.remove(9);
		this.paquet.remove(7);
		this.paquet.remove(6);
		this.paquet.remove(5);

		this.paquet.remove(4);
		this.paquet.remove(2);
		this.paquet.remove(1);
		this.paquet.remove(0);

	}

	public void paquetSansJaune()
	{
		this.initPioche();
		this.scenario = true;
		this.paquet.remove(3);
		this.paquet.remove(7);

	}


	public String toString() 
	{
		String s;
		s= "" + this.paquet;
		return "" + this.paquet; 
	} 
}