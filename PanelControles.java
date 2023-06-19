import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.Label;
import java.util.Collections;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class PanelControles  extends JPanel implements ActionListener
{
	private static Color[]		tabColor = {Color.RED, Color.BLUE};
	private Controleur			ctrl;

	private JButton				btnCommencer;
	private JButton				btnPioche;
	private JButton				btnManche;
	private JButton				btnJournal;

	private JLabel				lblPioche;
	private JLabel				lblManche;
	private JLabel				lblCouleur;
	private JScrollPane			scrollLog;
	private JLabel				lblIleEnCour;
	
	private Paquet				lstPiocher;
	private Paquet				lstCarte;
	private int					nbManches;
	private Color				coloEnCour;
	private int					feutredeb;
	private boolean				changManche;
	private JList<String>		listLog;
	private PanelJoueur 		pnl;

	
	public PanelControles(Controleur ctrl, PanelJoueur pnl)
	{
		this.ctrl = ctrl;  
		this.pnl  = pnl;
		this.ctrl.setTabColor(this.tabColor);
		this.setBackground(new Color(170, 159, 151));

		// Création des composants	
		this.lstPiocher   	= new Paquet(1);								//Arraylist vide par défaut
		this.lstCarte	    = new Paquet(this.ctrl.getNumPaquetM1());		//Arraylist de carte pleine changeant en fonction du int récup
		if(this.ctrl.getNumPaquetM1() == 10)  {this.lstPiocher	= new Paquet(11);}//CONDITION SCENARIO 2
		this.nbManches      = 1;
		this.btnCommencer	= new JButton(" Commencer ");
		this.btnPioche		= new JButton(" Piocher / Passer Tour ");
		this.btnManche		= new JButton(" Change Manche ");
		this.btnJournal		= new JButton("<html>Exporter<br>Journal de bord</html>");

		this.btnManche .setEnabled(false);
		this.btnJournal.setEnabled(false);
		this.btnPioche .setEnabled(false);

		this.lblIleEnCour	= new JLabel( " "  );

		if (this.ctrl.getNumPaquetM2() == 7) 
		{  
			this.btnManche.setText(" Terminer Scénario ");
		}

        // Création de la JList avec le modèle
		this.listLog 		= new JList<String>(this.ctrl.getListModel());

        // Création du JScrollPane contenant la JList 
		this.scrollLog		= new JScrollPane(listLog);

		this.lblCouleur		= new JLabel( " "   );
		this.lblManche		= new JLabel(" Manche n°" +  this.nbManches     );

		this.changManche	= true;

		// Positionnement des composants
		this.setLayout (new GridLayout(4,2));
		this.add( this.lblIleEnCour);
		this.add( this.btnCommencer);
		this.add( this.lblManche);
		this.add( this.btnManche);
		this.add( this.lblCouleur);
		this.add( this.btnPioche);
		this.add(this.btnJournal);
        this.add(this.scrollLog);

		//activation des composants
		this.btnJournal.addActionListener(this);
		this.btnCommencer.addActionListener(this);
		this.btnPioche.addActionListener(this);
		this.btnManche.addActionListener(this);
		this.setVisible(true);
	}
	
	public void setlblIleEnCour(String s)		{	this.lblIleEnCour.setText( s );	}
	public void setListLog(JList<String> list)	{	this.listLog = list; 			}


	public void		getCoulCarteAffichee()		{ this.ctrl.getGraph().setCoulCarte(this.lstPiocher.getCarte(this.lstPiocher.taille()).getCouleur()); }
	public Paquet	getListCartes()				{  return this.lstCarte;  }
	public Paquet	getListPioche()				{  return this.lstPiocher;  }
	public boolean	getBoolScenario()			{ return this.lstCarte.getScenario();	}

	
	public void tirageFeutre()
	{
		this.feutredeb = (int) (Math.random()*2);
		if (  this.lstCarte.getScenario()) { this.feutredeb = 0; }

		switch( this.feutredeb )
		{
			case 0 ->
			{
				this.coloEnCour  = Color.BLUE;
				this.ctrl.setColorJoueur(this.coloEnCour);
				this.ctrl.getFrame().getPanelGr().setDebutListe(this.ctrl.getGraph().getIle("Mutaa"));
			}
			case 1 ->
			{
				this.coloEnCour  = Color.RED;
				this.ctrl.setColorJoueur(this.coloEnCour);
 				this.ctrl.getFrame().getPanelGr().setDebutListe(this.ctrl.getGraph().getIle("Ticó"));
			}
		}
			
	}
	
	public void testFinDePartie()
	{
		this.ctrl.getFrame().getPanelGr().repaint();

		if ( this.nbManches >= 3 && !this.ctrl.getFrame().getPanelGr().getJouer() )
		{
			this.btnManche.setEnabled(false);
			this.btnPioche.setEnabled(false);
			FrameFinDePartie frame;
			this.ctrl.getFrame().setEnabled(false);
			frame = new FrameFinDePartie(this.ctrl);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		String  nomIm=""; 
		Carte   cartePioch;
		Toolkit toolkitTmp = Toolkit.getDefaultToolkit();
		
		if (e.getSource() == this.btnCommencer)
		{
			this.pnl.pnlRepaint();
			this.lstPiocher	= new Paquet(1);
			this.lstCarte	= new Paquet(this.ctrl.getNumPaquetM1());
			
			if(this.ctrl.getNumPaquetM1() == 10)  {
				this.lstPiocher	= new Paquet(11);//CONDITION SCENARIO 2
				this.nbManches	= 2;
				this.coloEnCour  = Color.RED;
				this.ctrl.setColorJoueur(this.coloEnCour);
 				this.ctrl.getFrame().getPanelGr().setFinListe(this.ctrl.getGraph().getIle("Laçao"));
				this.ctrl.getFrame().getPanelGr().setDebutListe(this.ctrl.getGraph().getIle("Ticó"));
			}

			if ( ! this.lstCarte.getScenario()) 
			{
				this.lstCarte.melanger();
				this.tirageFeutre();
			}
			this.ctrl.setCommencer(true);
			this.ctrl.getFrame().getPanelGr().repaint();
			this.lblIleEnCour.setText( "Vous êtes sur aucune ile");
			this.btnPioche.setEnabled(true);
			this.btnCommencer.setEnabled(false);
		}

		if (e.getSource() == this.btnPioche)
		{
			this.pnl.pnlRepaint();
			this.btnJournal.setEnabled(true);
			if ( ! this.lstCarte.getScenario()) { this.lstCarte.melanger(); }
			cartePioch =  this.lstCarte.piocher();
			this.ctrl.getGraph().setCoulCarte(cartePioch.getCouleur());
			this.lstPiocher.addCarte(cartePioch);
			this.ctrl.getFrame().getPanelGr().repaint();
			this.repaint();
			if ( cartePioch != null )
			{
				nomIm = "  " + cartePioch.getTypeChar() + "_" + cartePioch.getCouleur();

				this.lblCouleur.setText("<html>La carte choisie est<br>" + nomIm + "</html>" );


				this.ctrl.addToLog("La carte pioché est " + nomIm);
				this.ctrl.majListLog();
				this.repaint();
				this.ctrl.getFrame().getPanelGr().setJouer(true);
				
				if ( ! this.lstCarte.verifLstCarteARRET() )
				{
					this.nbManches += 1;
					if ( this.nbManches == 2 && (this.changManche) )
					{
						this.btnManche.setEnabled(true);
						this.btnPioche.setEnabled(false);
						this.changManche = false;
					}
					if ( this.nbManches > 3)
					{
						this.ctrl.getFrame().getPanelGr().setJouer(false);
						this.testFinDePartie();
					}
					

				}
			}
			this.ctrl.getFrame().getPanelGr().repaint();
			this.repaint();
		}	


		if (e.getSource() == this.btnManche)
		{
			if (this.ctrl.getNumPaquetM2() == 7) 
			{  
				FrameFinDePartie frame;
				this.ctrl.getFrame().setEnabled(false);
				frame = new FrameFinDePartie(this.ctrl);
			}
			if ( this.nbManches == 3 )
			{
				this.changManche = false;
			}
			this.btnManche.setEnabled(false);
			
			this.lblManche.setText(" Manche n°" +  this.nbManches );
			switch( this.feutredeb )
			{
				case 1 ->
				{
					this.coloEnCour  = Color.BLUE;
					this.ctrl.getGraph().setColorJoueur(this.coloEnCour);
					this.ctrl.getFrame().getPanelGr().setPremiereIleSelectionne(null);
					this.ctrl.getFrame().getPanelGr().setFinListe  (null);
					this.ctrl.getFrame().getPanelGr().setDebutListe(this.ctrl.getGraph().getIle("Mutaa"));
				}
				case 0 ->
				{
					this.coloEnCour  = Color.RED;
					this.ctrl.getGraph().setColorJoueur(this.coloEnCour);
					this.ctrl.getFrame().getPanelGr().setPremiereIleSelectionne(null);
					this.ctrl.getFrame().getPanelGr().setFinListe  (null);
					this.ctrl.getFrame().getPanelGr().setDebutListe(this.ctrl.getGraph().getIle("Ticó"));
				}
			}
			this.ctrl.setColorJoueur(this.coloEnCour);

			this.lstPiocher	= new Paquet(1);
			this.lstCarte	= new Paquet(this.ctrl.getNumPaquetM2());

			if ( ! this.lstCarte.getScenario()) { this.lstCarte.melanger(); }
			this.btnPioche.setEnabled(true);
			this.pnl.pnlRepaint();
			this.ctrl.getFrame().getPanelGr().repaint();
			this.repaint();
		}

		if (e.getSource() == this.btnJournal)
		{
			this.ctrl.exportLog();
			new FrameJournal(this.ctrl);
		}	

	}

	public void paintComponent ( Graphics g )
	{
		if(this.ctrl.getCommencer())
		{
			super.paintComponent( g );

			Graphics2D g2 = ( Graphics2D ) g;

			if ( this.lstPiocher.taille() != 0 )
				g.drawImage( this.lstPiocher.getLast().getImage().getScaledInstance( 25, 45, Image.SCALE_DEFAULT ), 150, 130, this);

			g2.drawString("Votre Score "+this.ctrl.getGraph().getPts(), 0, 70);
		}
	}
}