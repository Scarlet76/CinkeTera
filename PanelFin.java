import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Label;


/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class PanelFin extends JPanel implements ActionListener
{
	private Controleur  ctrl;
	private FrameFinDePartie frame;
	private JPanel      score;
	private JPanel      bout;
	
	private JButton btnRecommencer;
	private JButton btnQuiter;
	private JButton btnJournal;

    private int     point;


	public PanelFin(Controleur ctrl, int point, FrameFinDePartie frame)
	{
        this.ctrl = ctrl;
		this.frame = frame;
        this.point = point;
        this.setLayout (new BorderLayout(5,5));

		// Création des composants
        this.score = new JPanel();
        this.bout  = new JPanel();

		this.btnRecommencer = new JButton(" Recommencer "  );
		this.btnJournal     = new JButton(" Extraire Journal de bord"  );
		this.btnQuiter      = new JButton(" Quitter "       );

		// Positionnement des composants

        //this.score.setLayout (new GridLayout(1,1));
        this.score.add( new Label("Score final : "+ this.point ));

        this.bout.setLayout (new GridLayout(1,3));
        this.bout.add( this.btnRecommencer);
        this.bout.add( this.btnJournal);
        this.bout.add( this.btnQuiter);

        this.add( this.score, BorderLayout.CENTER);
        this.add( this.bout, BorderLayout.SOUTH);

		//activation des composants
        
        this.btnRecommencer.addActionListener(this);
        this.btnJournal.addActionListener(this);
        this.btnQuiter.addActionListener(this);
	
		this.setVisible(true);
	}
	
    public void actionPerformed(ActionEvent e)
    {
		if (e.getSource() == this.btnRecommencer)
        {
			new Controleur(this.ctrl.getNumPaquetM1(), this.ctrl.getNumPaquetM2());
            this.ctrl.getFrame().dispose();
			this.frame.dispose();
        }
        if (e.getSource() == this.btnQuiter)  
		{   
			this.ctrl.getFrame().dispose(); 
            this.frame.dispose();
		}     
        if (e.getSource() == this.btnJournal) {   this.ctrl.exportLog();          }     
	}
}