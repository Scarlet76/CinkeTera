import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JLabel;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class FrameJournal extends JFrame implements ActionListener
{
    private Controleur  ctrl;
    private JPanel      panel;

    private JPanel   text;
    private JPanel   bout;
	private JButton  btnOk;

    public FrameJournal(Controleur ctrl)
    {

        this.ctrl = ctrl;
        this.setTitle   ( "Journal de Bord" );
        this.setSize ( 400, 200 );
        this.setLocation(300, 300);

        // Création des composants
		this.panel = new JPanel();

        this.text  = new JPanel();
        this.bout  = new JPanel();

		this.btnOk = new JButton(" D'accord. "  );

		// Positionnement des composants
        this.text.setLayout (new GridLayout(3,1));
        this.text.add( new JLabel("  "));
        this.text.add( new JLabel(" Journale de bord exporté ! " ));
        this.text.add( new JLabel("  "));

        this.bout.setLayout (new GridLayout(1,1));
        this.bout.add( this.btnOk);

        this.panel.setLayout (new GridLayout(2,2));

        this.panel.add ( this.text);
        this.panel.add ( this.bout);

		this.add ( this.panel);
        this.btnOk.addActionListener(this);
        
		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)  {   if (e.getSource() == this.btnOk) { this.dispose();  }
    }
}


