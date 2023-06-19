import java.awt.Color;
import java.util.ArrayList;
import java.awt.Image;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class Ile
{
	private String          nom;
	private String          colorIleString;
	private int             posXCentre;
	private int             posXIHM;
	private int             posYCentre;
	private int             posYIHM;
	private Color           color;
	private Region          region;
	private Image           img;
	private Color			colorNom;
	private ArrayList<Voie> lstVoie;

	public Ile(String nom, String coul, int posXCentre, int posYCentre, int posXIHM, int posYIHM, Region region, Image img)
	{
		this.colorIleString =   coul;
		this.nom      		=   nom;
		this.posXCentre 	=   posXCentre;
		this.posYCentre 	=   posYCentre;
		this.posXIHM    	=   posXIHM;
		this.posYIHM    	=   posYIHM;
		this.setColor(coul);
		this.lstVoie   		=   new ArrayList<Voie>();
		this.region       	=   region;
		this.img        	=   img;
		this.colorNom		=	Color.BLACK;
	}

	public Voie researchVoie(Ile ile2) 
	{
		for (Voie v : this.lstVoie)
			if(v.getIleUn().equals(ile2) || v.getIleDeux().equals(ile2))
				return v;
		return null;
	}
 
	public String  getNom()
	{ 
		if (this == null)  {return "aucune ile " ;}
		else               {return this.nom    ;}
	}
		
	
	public int				getPosXCentre				() 	{ return this.posXCentre;			}
	public int				getPosYCentre				() 	{ return this.posYCentre;			}
	public int				getPosXIHM					() 	{ return this.posXIHM;				}
	public int				getPosYIHM					() 	{ return this.posYIHM;				}
	public Color			getColor  					() 	{ return this.color;				}
	public String			getColorIleString  			() 	{ return this.colorIleString;		}
	public ArrayList<Voie>	getLstVoie					() 	{ return this.lstVoie;				}
	public Region			getRegion					() 	{ return this.region;				}
	public Image			getImage					() 	{ return this.img;					}
	public Color			getColorNom					()	{ return this.colorNom;			}

	public void 			addVoie(Voie  voie)   	 { this.lstVoie.add(voie); 		}
	public void				setColorNom(Color color) { this.colorNom = color;	}
	public void 			setColor(String coul)
	{
		switch(coul) 
		{
			case "Rouge" -> this.color=new Color( 56,  44,  48);
			case "Jaune" -> this.color=new Color( 78,  69,  37);
			case "Brun"  -> this.color=new Color( 49,  16,  37);
			case "Vert"  -> this.color=new Color( 29,  47,  38);
		}
	}

	public ArrayList<Ile>   getLstVoisin		()
	{
		ArrayList<Ile> lstIle = new ArrayList<Ile>();
		
		for (Voie v: this.lstVoie)
		{
			if ( !v.getIleUn()  .getNom().equals(this.nom) )       { lstIle.add( v.getIleUn  () ); }
			if ( !v.getIleDeux().getNom().equals(this.nom) )       { lstIle.add( v.getIleDeux() ); }
		}
		
		return new ArrayList<Ile>();
	}

	public boolean estCycle(Ile ileNumDeux, Color color)
	{
		if (ileNumDeux != null)
		{
			for(Voie v: ileNumDeux.getLstVoie())
			{
				if( Voie.getLstVoieColorie().contains(v))
					if( Voie.getLstVoieColorie().get( Voie.getLstVoieColorie().indexOf(v) ).getColorVoie().equals(color))
						return true; 
				
			}
		}
		return false;
	}
	
	public boolean equals (Ile autreIle)
	{
		return this.nom.equals(autreIle.getNom()) && this.posXCentre == autreIle.getPosXCentre() && this.posYCentre == autreIle.getPosYCentre();
	}
		
}