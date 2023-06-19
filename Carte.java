import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;

/**  
  *
  * @author  Adrian		CHALANSONNET
  * @author  Justine 	BONDU
  * @author  Luca 		LANGLOIS
  * @author  Trystan 	BAILLOBAY
  *
  **/

public class Carte
{
    private static  int 				num = 0;
	private static  String[]			tabCoul = {"Multi", "Vert", "Rouge", "Jaune", "Brun"};
	private  		int 				ident;
    private  		String             	type;
	private  		char             	typeChar;
    private  		String             	coul;
	private  		int 				posX; 
	private  		int 				posY;
	private	 		Image				image;

    public Carte()
    {
		this.ident = this.num;
		this.num = this.num + 1;

		if ( this.ident < 5 )
		{
			this.type   = "Primaire";
			this.typeChar = 'P';
			this.posX = 0 + ( this.num * 80 );
			this.posY = 30;
		}	
		else
		{
			this.type   = "Secondaire";
			this.typeChar = 'S';
			this.posX = 0 + ( (this.num-5) * 80 );
			this.posY = 130;
		}

		this.coul = tabCoul[this.ident % 5];

		setImage();
    }

	public void 			resetNUM	() 		{ this.num = 0; 		}
	public int				getIdent	() 		{ return this.ident;	}
    public String			getType		() 		{ return this.type; 	}
	public char				getTypeChar	() 		{ return this.typeChar;	}
    public String			getCouleur	() 		{ return this.coul; 	}
    public void 			setPosX		(int x) { this.posX = x; 		}
    public void 			setPosY		(int y) { this.posY = y; 		}
    public int  			getPosX		() 		{ return this.posX; 	}
    public int  			getPosY		()		{ return this.posY; 	}
	public Image			getImage	()		{ return this.image;	}

	public void setImage()
	{
		try 
		{
			this.image = ( ImageIO.read( new File("./cartes/" + this.type + "_" + this.coul + ".png") ) ).getScaledInstance( 70, 90, Image.SCALE_DEFAULT);
		} catch (Exception ex) { System.out.println("Exception lors du chargement de l'image"); }
	}

public String toString() 
{
	 return "[" + this.ident + "] " + this.type + ":" + this.coul + "(" + this.posX + ":" + this.posY + ")" + '\n'; 
} 

}