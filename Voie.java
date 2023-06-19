import java.awt.Color;
import java.util.ArrayList;
import java.awt.geom.Line2D;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class Voie
{
	public static   ArrayList<Voie> lstVoieColorie;
	private         String          nom;
	private         Ile             ileUn;
	private         Ile             ileDeux;
	private         Color           colorVoie;        //Couleur Actuelle (celle qui est prise pour dessiner)
	private         Color           defaultColorVoie; //Couleur Beige
	private         int             bonus;

	public Voie(String nom,  Ile ileUn,  Ile ileDeux, int bonus, Color colorVoie)
	{
		this.nom                =   nom;
		this.ileUn              =   ileUn;
		this.ileDeux            =   ileDeux;
		this.colorVoie          =   colorVoie;
		this.defaultColorVoie   =   colorVoie;        
		this.bonus              =   bonus;
		this.lstVoieColorie     =   new ArrayList<Voie>();

		this.ileUn  .addVoie(this);
		this.ileDeux.addVoie(this);
	}

	public			String			getNom				()			{ return this.nom;              }
	public			Ile				getIleUn			()			{ return this.ileUn;             }
	public			Ile				getIleDeux			()			{ return this.ileDeux;           }
	public			Color			getColorVoie		()			{ return this.colorVoie;         }
	public			int				getBonus			()			{ return this.bonus;             }
	public			Color			getDefaultColorVoie	()			{ return this.defaultColorVoie;  }
	public static	void			addLstVoieColorie	(Voie v)	{ Voie.lstVoieColorie.add(v);    }
	public static   ArrayList<Voie> getLstVoieColorie   ()			{ return Voie.lstVoieColorie;    }
	
	public			void setColorVoie	(Color colorVoie)	{ this.colorVoie=colorVoie; }
	public			void setBonus		(int bonus      )	{ this.bonus=bonus;         }
	
	public boolean adjacentColorie(Color c) //TESTE SI UNE DES DEUX ILES DU LIEN QU'ON VEUT COLORIER A UN LIEN ADJACENT DE LA COULEUR QUON VEUT COLORIER
	{
		for (Voie v : ileUn.getLstVoie())
			if (v.getColorVoie().equals(c))
				return true;

		for (Voie v : ileDeux.getLstVoie())
			if (v.getColorVoie().equals(c))
				return true;

		return false;
	}

	public boolean sontSecantsColorie ()                                                         
	{
		boolean intersect  = false;
		int     positionXPointUn, positionYPointUn,positionXPointDeux,positionYPointDeux;

		positionXPointUn   = this.getIleUn()  .getPosXCentre() ;
		positionYPointUn   = this.getIleUn()  .getPosYCentre() ;
		positionXPointDeux = this.getIleDeux().getPosXCentre() ;
		positionYPointDeux = this.getIleDeux().getPosYCentre() ;
		
		Line2D line1 = new Line2D.Double (positionXPointUn , positionYPointUn , positionXPointDeux , positionYPointDeux);
		for (Voie v : Voie.lstVoieColorie)
		{
			Ile ile1  = v.getIleUn();  
			Ile ile2  = v.getIleDeux();

			if(! this.getIleUn()  .equals(ile1) && ! this.getIleUn()  .equals(ile2) 
		    && ! this.getIleDeux().equals(ile1) && ! this.getIleDeux().equals(ile2) )
			{
				Line2D line2 = new Line2D.Double ( ile1.getPosXCentre (), ile1.getPosYCentre (), ile2.getPosXCentre (), ile2.getPosYCentre () );

				// Vérification si les lignes se croisent
				if ( line1.intersectsLine ( line2 ) )
				{ 
					// Les arêtes se croisent
					// Vérification si les arêtes sont adjacentes
					if ( ! v.getColorVoie().equals(this.defaultColorVoie) )
					{
						intersect = true; // Les arêtes se croisent
					}
					else{
						System.out.println(this+" colorié !");
					}
				}
			}
		}
		return intersect;
	}
	public boolean estDansLstColorie()  { return Voie.lstVoieColorie.contains(this); }
}