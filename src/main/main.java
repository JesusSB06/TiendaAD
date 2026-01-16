package main;

import controller.MainController;
import view.MainJFrame;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainJFrame mainJFrame = new MainJFrame();
        mainJFrame.setVisible(true);
        MainController mc = new MainController(mainJFrame);
        mc.changeImage();
    }
    
}
