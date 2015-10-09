import javax.swing.*;

/**
 * Created by alanflood on 19/04/15.
 */
public class shoppingCart extends JFrame
{
    public static void main(String[] args)
    {
        ShoppingFrame shoppingFrame = new ShoppingFrame();
        shoppingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shoppingFrame.setSize(500, 720);
        shoppingFrame.setVisible(true);
        shoppingFrame.setResizable(false);
    }
        
}
