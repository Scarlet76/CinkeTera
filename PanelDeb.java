import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JLabel;
import java.awt.Color;
import java.nio.charset.StandardCharsets;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/


public class PanelDeb extends JPanel implements ActionListener
{
    private ControleurDeb       ctrl;
    private PanelImage          panelImage;
    private JPanel              bout;
    
	private JButton btnPatieSolo;
    private JButton btnPatieDuo;
    private JButton btnScenario;
	private JButton btnQuiter;

	public PanelDeb(ControleurDeb ctrl)
	{
        this.ctrl = ctrl;
        this.setBackground(new Color(255,255,255));
		// Création des composants
        this.panelImage = new PanelImage();
        this.bout       = new JPanel();
        
        // Création d'un JLabel
        JLabel label = new JLabel();
 
		this.btnPatieSolo   = new JButton("Partie Solo"     );
        this.btnPatieDuo    = new JButton("Partie Duo"      );
        this.btnPatieDuo.setEnabled(false);
        this.btnScenario    = new JButton("Scénario"        );
		this.btnQuiter      = new JButton("Quitter"         );

		// Positionnement des composants
        this.bout.setLayout (new GridLayout(3,2));
        this.bout.add( this.btnPatieSolo);
        this.bout.add( this.btnScenario);
        this.bout.add( this.btnPatieDuo);

        this.bout.add( new JLabel(""));
        this.bout.add( new JLabel(""));
        this.bout.add( new JLabel("         En cours de Développement"));

        this.bout.add( new JLabel(""));
        this.bout.add( this.btnQuiter);
        this.bout.add( new JLabel(""));

        this.setLayout (new BorderLayout(5,5));

        this.add( this.panelImage, BorderLayout.CENTER);
        this.add( this.bout, BorderLayout.SOUTH);

		//activation des composants
        
        this.btnPatieSolo.addActionListener(this);
        this.btnPatieDuo.addActionListener(this);
        this.btnScenario.addActionListener(this);
        this.btnQuiter.addActionListener(this);
	
		this.setVisible(true);
	}
	
    public void actionPerformed(ActionEvent e)
    {
		if (e.getSource() == this.btnPatieSolo)
        {
            new Controleur(0, 0);
            this.ctrl.getFrameDeb().dispose(); 
        }
        
        if (e.getSource() == this.btnPatieDuo) { System.out.println( "Partie Duo" );}
        if (e.getSource() == this.btnScenario) 
        { 
            FrameScenario frame;
            frame = new FrameScenario(this.ctrl);
        }

        if (e.getSource() == this.btnQuiter)
            this.ctrl.frameDeb.dispose();		
	}
}