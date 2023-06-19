import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class PanelImage extends JPanel
{

	 private Image imgSujet;

    public PanelImage()
    {
		  this.imgSujet = getToolkit().getImage ( "cinkeTera.png" );
      this.setBackground(new Color(255,255,255));
    }

    public void paintComponent (Graphics g)
	  {
		  super.paintComponent(g);

		  g.drawImage( this.imgSujet, 25 ,25 , this);
    }
    
    public void majIhm()  { this.repaint() ; }

}