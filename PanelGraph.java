import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Font;
import java.io.IOException;
import java.awt.FontFormatException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;



/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class PanelGraph  extends JPanel
{
	private Controleur  	ctrl;
	private Ile             debutListe;
	private Ile             finListe;
	private Ile         	premiereIleSelectionne;
	private Ile         	deuxiemeIleSelectionne;
	private boolean         peutJouer;
	private boolean			boolScenario;

	public PanelGraph(Controleur ctrl)
	{
		this.ctrl 					= ctrl;  
		this.addMouseListener( new GereSouris() );  //Rendue cliquable
		this.setBackground   ( new Color(172,209,232) );
		this.debutListe 			= null;
		this.finListe   			= null;
		this.premiereIleSelectionne = null;
		this.deuxiemeIleSelectionne = null;
		this.peutJouer 				= false;
		this.boolScenario 			= true;
	} 

	public void 	setJouer(boolean b)               		{ this.peutJouer = b;    				}
	public boolean 	getJouer()                    	  		{ return this.peutJouer; 				}
	public Ile  	getDebutListe()                   		{ return this.debutListe;				}
	public void 	setDebutListe(Ile i)              		
	{ 
		this.debutListe = i ; 
		this.debutListe.setColorNom(Color.YELLOW);  				
	}
	public Ile  	getFinListe()                    		{ return this.finListe;  				}
	public void 	setFinListe(Ile i)                		{ this.finListe = i ; 					}
	public Ile  	getPremiereIleSelectionne() 	  		{ return this.premiereIleSelectionne; 	}
	public void	    setPremiereIleSelectionne(Ile i) 
	{
		this.premiereIleSelectionne = i ;   
		if(this.premiereIleSelectionne != null)
			this.premiereIleSelectionne.setColorNom(this.ctrl.getGraph().getColorJoueur());
	}

	public Ile getDeuxiemeIleSelectionne() 		 { return this.deuxiemeIleSelectionne; }

	public void paintComponent ( Graphics g )
	{

		super.paintComponent( g );

		Graphics2D g2 = ( Graphics2D ) g;

		float[] paternPointil = {10f, 10f};
		String policeRegion = "./Police_Texte/YadaYadaYada.otf";
		
		int posX1 = 0;
		int posY1 = 0;
		int posX2 = 0;
		int posY2 = 0;
		int posXIHM = 0;
		int posYIHM = 0;	
		
		if(this.ctrl.getNumPaquetM1() == 10 && this.boolScenario )
		{	//Dessine les voies prédéfinis par le scénario 2.

			ArrayList<String> lstNomBleu  = new ArrayList<String>();
			ArrayList<String> lstNomRouge = new ArrayList<String>();
			lstNomBleu.add("Mokah/Mutaa"); 
			lstNomBleu.add("Mokah/Fuego");  
			lstNomBleu.add("Fuego/Kiola"); 
			lstNomBleu.add("Kiola/Kita");   
			lstNomBleu.add("Ticó/Kita"); 
			lstNomRouge.add("Tiamù/Ticó");  
			lstNomRouge.add("Tavula/Tiamù"); 
			lstNomRouge.add("Tavula/Tokha"); 
			lstNomRouge.add("Tokha/Laçao"); 
			this.ctrl.addToLog("On colorie le lien Mokah/Mutaa");
			this.ctrl.addToLog("On colorie le lien Mokah/Fuego");
			this.ctrl.addToLog("On colorie le lien Fuego/Kiola");
			this.ctrl.addToLog("On colorie le lien Kiola/Kita" );
			this.ctrl.addToLog("On colorie le lien Ticó/Kita"  );
			this.ctrl.addToLog("On colorie le lien Tiamù/Ticó" );
			this.ctrl.addToLog("On colorie le lien Tavula/Tiamù");
			this.ctrl.addToLog("On colorie le lien Tavula/Tokha");
			this.ctrl.addToLog("On colorie le lien Tokha/Laçao");
			PanelGraph.this.ctrl.majListLog();

			for(Voie v : this.ctrl.getLstVoie() )
			{
				if( lstNomBleu.contains(v.getNom()))
				{
					v.setColorVoie(Color.BLUE);
					Voie.addLstVoieColorie(v);
				}

				if( lstNomRouge.contains(v.getNom()))
				{
					v.setColorVoie(Color.RED);
					Voie.addLstVoieColorie(v);
				}

			}
			boolScenario = false;
		}

		try
		{
			Font policeCustom = Font.createFont(Font.TRUETYPE_FONT, new File(policeRegion));
			g2.setFont    ( policeCustom.deriveFont(18f) );
		} catch (FontFormatException | IOException e) { System.out.println("Police pas trouvé"); }

		// Set le trait avec le motif de pointillé
		g2.setStroke( new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1f, paternPointil, 0f) );

		// Dessine les lignes en pointillés
		posX1 =  0;
		posY1 =  (int)(380*this.ctrl.getCoeff());
		posX2 =  (int)(750*this.ctrl.getCoeff());
		posY2 =  (int)(380*this.ctrl.getCoeff());
		g2.drawLine(  posX1,   posY1,  posX2, posY2);
		//posY1 ne change pas
		posX1 =  (int)( 750*this.ctrl.getCoeff());
		posX2 =  (int)(1300*this.ctrl.getCoeff());
		posY2 =  (int)( 760*this.ctrl.getCoeff());
		g2.drawLine(posX1,   posY1,  posX2, posY2);
		//posX1 et X2 ne change pas ne change pas
		posY1 =  (int)(380*this.ctrl.getCoeff());
		posY2 =  0;
		g2.drawLine(posX1, posY1,  posX2, posY2);
		
		posX1 =  (int)(425*this.ctrl.getCoeff());
		posY1 =  0;
		posX2 =  (int)( 425*this.ctrl.getCoeff());
		posY2 =  (int)(900*this.ctrl.getCoeff());
		g2.drawLine(posX1, posY1,  posX2, posY2);

		for( Region r : this.ctrl.getLstRegion() )
		{
			posX1 = (int)(r.getPosX()*this.ctrl.getCoeff());
			posY1 = (int)(r.getPosY()*this.ctrl.getCoeff());
			g2.setColor   ( new Color(247,145,82) );
			g2.drawString ( String.format("%10s",r.getNom()),posX1,posY1 );
		}

		g2.setFont    ( new Font("Arial", Font.PLAIN, 14) );

		for( Voie v : this.ctrl.getLstVoie() )
		{
			posX1 = (int)(v.getIleUn().getPosXCentre()*this.ctrl.getCoeff());
			posY1 = (int)(v.getIleUn().getPosYCentre()*this.ctrl.getCoeff());

			posX2 = (int)(v.getIleDeux().getPosXCentre()*this.ctrl.getCoeff());
			posY2 = (int)(v.getIleDeux().getPosYCentre()*this.ctrl.getCoeff());


			g2.setColor   ( v.getColorVoie()   );
			g2.setStroke  ( new BasicStroke(3) );
			g2.drawLine   ( posX1, posY1, posX2, posY2);
		}

		for( Ile i : ctrl.getLstIle() )
		{
			posXIHM = (int)(i.getPosXIHM()*this.ctrl.getCoeff());
			posYIHM = (int)(i.getPosYIHM()*this.ctrl.getCoeff());

			g.setColor   ( i.getColorNom() );
			g.drawString ( String.format("%2s",i.getNom()),posXIHM-15, posYIHM+5);   //Pour pouvoir voir le nom de l'ile.
			g.drawImage  ( i.getImage(), posXIHM, posYIHM , this);
		}



		/*---------------------------*/
		/*   Section surbrillance    */
		/*---------------------------*/
		Stroke contourColor = new BasicStroke(4f);// Créer un contour épais pour la surbrillance
		int    tailleOvale  = (int)(70*this.ctrl.getCoeff());
		int    tailleSelect = (int)(35*this.ctrl.getCoeff());
		// Dessiner le contour autour de l'île sélectionnée
		g2.setStroke(contourColor);
		g2.setColor(Color.YELLOW);

		if(this.premiereIleSelectionne == null && this.debutListe != null )
		{
			g2.drawOval((int)(this.debutListe.getPosXCentre()*this.ctrl.getCoeff()) - tailleSelect,(int)(this.debutListe.getPosYCentre()*this.ctrl.getCoeff()) - tailleSelect, tailleOvale, tailleOvale);
		}
		if(this.premiereIleSelectionne == null && this.finListe   != null )
		{
			g2.drawOval((int)(this.finListe.getPosXCentre  ()*this.ctrl.getCoeff()) - tailleSelect,(int)(this.finListe.getPosYCentre  ()*this.ctrl.getCoeff()) - tailleSelect, tailleOvale, tailleOvale);
		}
		
		if(this.premiereIleSelectionne != null)
		{
			this.premiereIleSelectionne.setColorNom(this.ctrl.getGraph().getColorJoueur());
			for (Ile i : ctrl.getLstIle() )
			{
				Voie tmp2 = this.premiereIleSelectionne.researchVoie(i);

				if(tmp2 != null                                               	  // SI le lien temporaire n'est pas nul
					&& this.premiereIleSelectionne != i                           // SI premiere ile n'est pas la deuxième ile
					&& this.premiereIleSelectionne != null                        // SI premiere ile n'est pas nulle
					&& !this.ctrl.getGraph().getLstVoieColorie().contains(tmp2)   // SI l'Voie retourné plus tôt n'est pas nul

					&& !this.premiereIleSelectionne.estCycle(i, this.ctrl.getGraph().getColorJoueur())
					&& !tmp2.sontSecantsColorie()  // SI l'Voie n'est pas sécant avec un autre lien déjà coloré
					&& (this.ctrl.getGraph().getCoulCarte()!= null &&(this.ctrl.getGraph().getCoulCarte().equals(i.getColorIleString()) || this.ctrl.getGraph().getCoulCarte().equals("Multi")))
					&& this.peutJouer
				)
				{
						g2.drawOval((int)(i.getPosXCentre()*this.ctrl.getCoeff()) - tailleSelect,(int)(i.getPosYCentre()*this.ctrl.getCoeff()) - tailleSelect, tailleOvale, tailleOvale);
				}
			}
			posX1 =(int)(this.premiereIleSelectionne.getPosXCentre()*this.ctrl.getCoeff())-tailleSelect;//correspond au X de la première ile selectionnée
			posY1 =(int)(this.premiereIleSelectionne.getPosYCentre()*this.ctrl.getCoeff())-tailleSelect;//correspond au Y de la première ile selectionnée
			g2.setColor(this.ctrl.getGraph().getColorJoueur());
			g2.drawOval(posX1,posY1, tailleOvale, tailleOvale);
		}

	}

	private class GereSouris extends MouseAdapter                          
	{
		public void mouseClicked(MouseEvent e) 
		{
			String  nom 	= "aucune ile."; 
			Voie    tmp 	= null;
			int     tailleSelect = (int)(35*PanelGraph.this.ctrl.getCoeff());

			if (PanelGraph.this.premiereIleSelectionne == null)
			{
				
				for(Ile i : ctrl.getLstIle())
				{
					if
					(
						   ( e.getX() >=(int) (i.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getX() <=(int) (i.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
						&& ( e.getY() >=(int) (i.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getY() <=(int) (i.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
						&& PanelGraph.this.ctrl.getCommencer()
						&& PanelGraph.this.finListe != null
						&& i.equals(PanelGraph.this.finListe)
					)
					{
						PanelGraph.this.debutListe.setColorNom(Color.BLACK);
						PanelGraph.this.premiereIleSelectionne = i;
						PanelGraph.this.premiereIleSelectionne.setColorNom(PanelGraph.this.ctrl.getGraph().getColorJoueur());
						PanelGraph.this.repaint();
					}
					if
					(
						   ( e.getX() >= (int) (i.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getX() <= (int) (i.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
						&& ( e.getY() >= (int) (i.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getY() <= (int) (i.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
						&& PanelGraph.this.ctrl.getCommencer()
						&& i.equals(PanelGraph.this.debutListe)
					)
					{
						if(PanelGraph.this.finListe != null) {PanelGraph.this.finListe.setColorNom(Color.BLACK);}
						PanelGraph.this.premiereIleSelectionne = i;
						PanelGraph.this.premiereIleSelectionne.setColorNom(PanelGraph.this.ctrl.getGraph().getColorJoueur());
						PanelGraph.this.repaint();
					}
		
				}
			}
			else
			{
				if(   ( e.getX() >= (int)(premiereIleSelectionne.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getX() <= (int)(premiereIleSelectionne.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
					&&( e.getY() >= (int)(premiereIleSelectionne.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getY() <= (int)(premiereIleSelectionne.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
					&& PanelGraph.this.ctrl.getCommencer())
				{
					PanelGraph.this.debutListe.setColorNom(Color.YELLOW);
					if(PanelGraph.this.finListe != null) {PanelGraph.this.finListe.setColorNom(Color.YELLOW);}

					PanelGraph.this.premiereIleSelectionne = null;
					PanelGraph.this.repaint();
				}
				else
				{	
					for(Ile i : ctrl.getLstIle())
					{
						if((e.getX() >= (int) (i.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getX() <= (int) (i.getPosXCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
						&&( e.getY() >= (int) (i.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())-tailleSelect && e.getY() <= (int) (i.getPosYCentre()*PanelGraph.this.ctrl.getCoeff())+tailleSelect )
						&& PanelGraph.this.ctrl.getCommencer() &&  PanelGraph.this.peutJouer )
						{
							tmp = PanelGraph.this.premiereIleSelectionne.researchVoie(i); // On cherche le lien qui existe entre premiere Ile select et i sinon on retourne null  

							if(tmp != null                                                          // SI le lien temporaire n'est pas nul
							&& PanelGraph.this.premiereIleSelectionne != i                          // SI premiere ile n'est pas la deuxième ile
							&& PanelGraph.this.premiereIleSelectionne != null                       // SI premiere ile n'est pas nulle
							&& !PanelGraph.this.ctrl.getGraph().getLstVoieColorie().contains(tmp)   // SI l'Voie retourné plus tôt n'est pas nul

							&& !PanelGraph.this.premiereIleSelectionne.estCycle(i, PanelGraph.this.ctrl.getGraph().getColorJoueur())
							&& !tmp.sontSecantsColorie())                                           // SI l'Voie n'est pas sécant avec un autre lien déjà color
							{
								if( 
									(PanelGraph.this.ctrl.getGraph().getCoulCarte()!= null && PanelGraph.this.ctrl.getGraph().getCoulCarte().equals(i.getColorIleString()) )
									|| PanelGraph.this.ctrl.getGraph().getCoulCarte().equals("Multi"))
								{
									PanelGraph.this.deuxiemeIleSelectionne = i;

									tmp.setColorVoie(PanelGraph.this.ctrl.getColorJoueur());

									PanelGraph.this.ctrl.getGraph().colorVoie(tmp); 
									PanelGraph.this.ctrl.getGraph().getLstVoieColorie().add(tmp);
									Voie.addLstVoieColorie(tmp);

									
									PanelGraph.this.ctrl.addToLog("On colorie le lien " + tmp.getNom()); // Ajout dans le log de la coloration du lien
									PanelGraph.this.ctrl.majListLog();                                   // Update du scrollpane.
									
									PanelGraph.this.premiereIleSelectionne.setColorNom(Color.BLACK);

									if( PanelGraph.this.finListe == null && PanelGraph.this.premiereIleSelectionne.equals(PanelGraph.this.debutListe) )
										PanelGraph.this.finListe = PanelGraph.this.deuxiemeIleSelectionne; 
									else
									{
										if(PanelGraph.this.premiereIleSelectionne.equals(PanelGraph.this.debutListe) )
											PanelGraph.this.debutListe = PanelGraph.this.deuxiemeIleSelectionne; 
									
										if(PanelGraph.this.premiereIleSelectionne.equals(PanelGraph.this.finListe)) 
												PanelGraph.this.finListe   = PanelGraph.this.deuxiemeIleSelectionne;
									}
									PanelGraph.this.premiereIleSelectionne = PanelGraph.this.deuxiemeIleSelectionne;
									PanelGraph.this.premiereIleSelectionne.setColorNom(PanelGraph.this.ctrl.getGraph().getColorJoueur());
									PanelGraph.this.deuxiemeIleSelectionne=null;
									PanelGraph.this.setJouer(false);
									PanelGraph.this.ctrl.getFrame().getPanelJoueur().getPanelControles().testFinDePartie();

								}
							}
							else
							{
								PanelGraph.this.deuxiemeIleSelectionne     = null;
							}
							
							PanelGraph.this.repaint();
						}				
					}
				}
			}
			if (PanelGraph.this.premiereIleSelectionne!=null)
				nom = PanelGraph.this.premiereIleSelectionne.getNom();

			if (PanelGraph.this.deuxiemeIleSelectionne!=null)
				nom += "-" +PanelGraph.this.deuxiemeIleSelectionne.getNom();

			if(PanelGraph.this.premiereIleSelectionne == null) {PanelGraph.this.ctrl.getFrame().getPanelJoueur().getPanelControles().setlblIleEnCour(  " Vous n'êtes sur aucune ile ");}
			else {PanelGraph.this.ctrl.getFrame().getPanelJoueur().getPanelControles().setlblIleEnCour(" Vous êtes sur " + PanelGraph.this.premiereIleSelectionne.getNom() );}
		}

	}
}