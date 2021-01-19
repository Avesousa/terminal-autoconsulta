package com.imagosur.tv;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.imagosur.tv.Controlador;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int heigth = getHeigthParam(args);
        int width = getWidthParam(args);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Unable to set look and feel");
        }
        
        EventQueue.invokeLater(() -> {
        	Controlador con = new Controlador();
        	new Thread(new KeyboardUI(heigth,width,true,false,con)).run();
        	new Thread(new KeyboardUI(heigth,width,false,false,con)).run();
        });
    }

    public static int getHeigthParam(String[] args) {
        if(args != null && args.length > 0) {
            String primerParametro = args[0];
            try {
                return Integer.parseInt(primerParametro);
            } catch(NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public static int getWidthParam(String[] args) {
        if(args != null && args.length > 1) {
            String segundoParametro = args[1];
            try {
                return Integer.parseInt(segundoParametro);
            } catch(NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}
