import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.io.FileInputStream;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.Charset;

/** Classe 
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class Controleur
{
	private static 	Color[]						tabColor;
	private         Double                      coeff;							 //Tableau de couleur du joueur
	private 		Graph           			graph;                           //Pour accéder au graph
	private 		Color           			colorJoueur;                     //Couleur qu'on récupéra en fonction du stylo du joueur, rouge ou bleu.
	private 		Color           			defaultColorVoie;                //Couleur Beige, qui est par défaut aux Voies.
	private 		FrameGraph      			frame;                           //Pour accéder à la frame. 
	private 		boolean						commencer;
	private 		ArrayList<String>			log;
	private 		DefaultListModel<String>	listModel;
	private 		int 						numPaquetM1;
	private 		int 						numPaquetM2;


	public Controleur(int numPaquetM1, int numPaquetM2)
	{
		this.defaultColorVoie       = new Color (249,228,183);
		this.graph                  = new Graph (this);
        this.commencer              = false;
		this.log					= new ArrayList<String>();
		this.coeff                  = 0.8;
		this.numPaquetM1			= numPaquetM1;
		this.numPaquetM2			= numPaquetM2;
		
		// Création du modèle de liste pour la JList affiché dans le ScrollPane
		this.listModel = new DefaultListModel<>();
        for (String s : this.log) 
            listModel.addElement(s);

		this.frame                  = new FrameGraph (this);



		this.genererGraph(); 
		this.graph.setColorJoueur(this.colorJoueur);
	}

	public Color[] getTabColor() 		 {  return this.tabColor;  }
	public void    setTabColor(Color[] c) { this.tabColor = c;	   }

	public ArrayList<Ile>		getLstIle()			{ return this.graph.getLstIle();    }
	public ArrayList<Voie>		getLstVoie()		{ return this.graph.getLstVoie();   }
	public ArrayList<Region>	getLstRegion()		{ return this.graph.getLstRegion(); }
	public Graph				getGraph()			{ return this.graph;                }
	public Color				getColorJoueur()	{ return this.colorJoueur;          }
	public FrameGraph			getFrame()			{ return this.frame;                }
    public boolean				getCommencer()		{ return this.commencer;            }
	public ArrayList<String>	getLog()			{ return this.log;         	        }
	public Double               getCoeff()          { return this.coeff;                }
	public int 					getNumPaquetM1()	{ return this.numPaquetM1;			}
	public int 					getNumPaquetM2()	{ return this.numPaquetM2;			}

	public void 				addToLog(String s)	{ this.log.add(s);         	        }

	public void 			setLog(ArrayList<String> s)	{ this.log = s;         	    }
	public void				setCommencer(Boolean bool)	{ this.commencer = bool;	    }
	public void				setColorJoueur(Color c) //Méthode qui stock la couleur du joueur dans le controleur, qui la met a jour dans le graph.
	{
		this.colorJoueur = c;
		this.graph.setColorJoueur(this.colorJoueur);
	}

	public void genererGraph()
	{
		graph.addRegion( new Region("EfTera", 1100, 180, this)); //CREATIONS DES REGIONS
		graph.addRegion( new Region("EmTera",  900,	800, this));
		graph.addRegion( new Region("HelTera",  50, 420, this));
		graph.addRegion( new Region("KhaTera", 900, 100, this));
		graph.addRegion( new Region("TéhTera", 280, 360, this));

		int cptSautLigne = 0;                             //Compteur pour différencier les régions.
		Toolkit toolkitTmp = Toolkit.getDefaultToolkit(); //Pour pouvoir affecter une Image au Ile.

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "data.txt" )); //Lecture du fichier data.

			sc.nextLine();
			sc.nextLine();
			
			while ( sc.hasNextLine() ) //CREATIONS DES ILES
			{
				ArrayList<String> dec = decomposer(sc.nextLine()); 

				if ( dec.size() <= 1 ) 
				{
					dec = decomposer(sc.nextLine());
					if (dec.get(0).equals("[ARETES]")) break;    //Si on passe dans la partie ARETE, on sort de la boucle.
					cptSautLigne++;                                             //Chaque saut de ligne égale un changment de régions.
				}

				graph.addIle ( new Ile( dec.get(0),
										dec.get(1),
										Integer.parseInt(dec.get(2)),
										Integer.parseInt(dec.get(3)),
										Integer.parseInt(dec.get(4)),
										Integer.parseInt(dec.get(5)),
										graph.getLstRegion().get(cptSautLigne) ,
										toolkitTmp.getImage( "./iles/"+dec.get(0)+".png" ) ) );
			}
			
			while ( sc.hasNextLine() ) //CREATIONS DES LIENS
			{
				ArrayList<String> dec = decomposer(sc.nextLine());              
				graph.addVoie( new Voie( (dec.get(0)+"/"+dec.get(1)), graph.getIle( dec.get(0) ), graph.getIle( dec.get(1) ), Integer.parseInt(dec.get(2)), defaultColorVoie ) );
			}
			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}


	public ArrayList<String> decomposer(String s) //DECOMPOSEUR
	{
		ArrayList<String> decompose = new ArrayList<String>();

		String sTmp="";
		
		for(int cpt=0; cpt < s.length(); cpt++)
		{
			if(s.charAt(cpt)=='\t')
			{
				decompose.add(sTmp);
				sTmp="";
			}
			else
				sTmp+=s.charAt(cpt);
		}

		decompose.add(sTmp);

		return decompose;
	}

	public void exportLog()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("export.log")))
        {
            for (String s : this.log)
            {
                bw.write(s);
                bw.newLine();
            }
            System.out.println("Exportation terminée avec succès !");
        } catch (IOException e) { System.err.println("Erreur lors de l'exportation vers le fichier : " + e.getMessage()); }
    }

	public DefaultListModel<String> getListModel() {return this.listModel;}
	public void majListLog()
	{
		for (String s : this.log) 
			if(!listModel.contains(s))
            	listModel.addElement(s);
		this.frame.getPanelJoueur().getPanelControles().setListLog(new JList<String>(this.listModel));
	}
	
}