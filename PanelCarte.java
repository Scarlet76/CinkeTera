import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class PanelCarte  extends JPanel 
{
	private Controleur			ctrl;

	private Paquet	lstCarte;                            
	private Paquet	lstPiocher;   
	private PanelControles pnl;


	public PanelCarte(Controleur ctrl, PanelControles pnl)
	{
		this.ctrl        = ctrl;  
		this.pnl 		 = pnl;
		this.setBackground(new Color(170, 159, 151));
		
		// Création des composants	
		this.lstCarte 	= this.pnl.getListCartes();
		this.lstPiocher = this.pnl.getListPioche();

		this.setVisible(true);
	} 

	public void paintComponent ( Graphics g )
	{
		this.lstCarte 	= this.pnl.getListCartes();
		this.lstPiocher = this.pnl.getListPioche();

		super.paintComponent( g );

		for (Carte c : this.lstCarte.getPaquet())
			g.drawImage(c.getImage(), c.getPosX()-50, c.getPosY(), this);
		
		if( this.lstPiocher.taille() != 0 )
		{
			for(Carte c : this.lstPiocher.getPaquet())
			try 
			{
				g.drawImage( ( ImageIO.read( new File("./cartes/dos.png") ) ).getScaledInstance( 70, 90, Image.SCALE_DEFAULT ), c.getPosX()-50, c.getPosY(), this);
			} catch (Exception ex) { System.out.println("Exception lors du chargement de l'image"); }
		}
	}
}