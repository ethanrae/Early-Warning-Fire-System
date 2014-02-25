/**
 *
 * @author ethan.rae045
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Listener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //System.out.println(e.getActionCommand());
        
        //Should use switch statement on String Object but only two buttons for now
        if(e.getActionCommand().equalsIgnoreCase("Close"))
        {
            System.exit(0);
        }
        else if (e.getActionCommand().equalsIgnoreCase("Generate Data")) //could be else
        {
            //Does nothing as of now
            //Need to reconfigure the methods and organization
            
            /*
            Window.createSensors();
            Window.setGridAlertColors(Window.rightMainPanel);
            Window.rightMainPanel.repaint();
            */
        }  
    }
}
