package com.imagosur.tv;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import static java.awt.event.KeyEvent.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.AbstractBorder;

public class KeyboardUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final String[] FN_KEY_OFF_TEXT = new String[]{
        "<html>~<br />&nbsp;&nbsp;`</html>", "<html>!<br />&nbsp;&nbsp;1</html>", "<html>@<br />&nbsp;&nbsp;2</html>",
        "<html>#<br />&nbsp;&nbsp;3</html>", "<html>$<br />&nbsp;&nbsp;4</html>", "<html>%<br />&nbsp;&nbsp;5</html>",
        "<html>^<br />&nbsp;&nbsp;6</html>", "<html>&<br />&nbsp;&nbsp;7</html>", "<html>*<br />&nbsp;&nbsp;8</html>",
        "<html>(<br />&nbsp;&nbsp;9</html>", "<html>)<br />&nbsp;&nbsp;0</html>", "<html>_<br />&nbsp;&nbsp;-</html>",
        "<html>+<br />&nbsp;&nbsp;=</html>"
    };
    private static final String[] FN_KEY_OFF_VK = new String[]{
        "0xC0", "0x31", "0x32", "0x33", "0x34", "0x35", "0x36", "0x37", "0x38", "0x39", "0x30", "0x2D", "0x3D"
    };

    private static final String[] FN_KEY_ON_TEXT = new String[]{
        "Esc", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12"
    };
    private static final String[] FN_KEY_ON_VK = new String[]{
        "0x1B", "0x70", "0x71", "0x72", "0x73", "0x74", "0x75", "0x76", "0x77", "0x78", "0x79", "0x7A", "0x7B"
    };

    private static final Color SELECTED_COLOR = Color.GRAY;
    private static final Color DEFAULT_KEY_COLOR = new Color(0,0,0);
    private static boolean IS_FUNCTION_KEY_PRESSED = false;
    private final ArrayList<JLabel> alphabetKeys;
    private final ArrayList<JLabel> functionKeys;
    private final ArrayList<JLabel> numeroKeys;

    //Controlador de teclados
    private Controlador controlador;
    
    private int heigth;
    private int width;
    private boolean esDerecho;
    private boolean esNumero;
    
    /**
     * Creates new GUI form KeyboardUI
     */
    public KeyboardUI(int paramHeigth, int paramWidth, boolean der, boolean number, Controlador controlador) {
    	this.controlador = controlador;
        alphabetKeys = new ArrayList<>();
        functionKeys = new ArrayList<>();
        numeroKeys = new ArrayList<>();
    	this.heigth = paramHeigth;
    	this.width = paramWidth;
    	this.esDerecho = der;
    	this.esNumero = number;
    }
    
    public void setControlador(Controlador controlador) {
    	this.controlador = controlador;
    }
    
    private void initKeys(ArrayList<JLabel> lista, JPanel panel) {
        for(Component component : panel.getComponents()) {
            try {
                JLabel key = (JLabel)component;
                
                String name = key.getText();
                
                if(key.getText().length()==1) {
                    lista.add(key);
                }

            } catch(ClassCastException ex) {
                
            }
        }
    }
    
    private void initFunction() {
    	for(Component component : this.funcionPanel.getComponents()) {
            try {
                JLabel key = (JLabel) component;
                
                functionKeys.add(key);
            } catch(ClassCastException ex) { }
        }
        functionKeys.remove(keybackspace);
    }

    private void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("tv-icon.png"));
        setIconImage(icon.getImage());
    }

    /**
     * Sets the default location of the Onscreen Keyboard on bottom of the screen 
     * just above the taskbar.
     */
    private void setLocationKeyBoard(int paramHeigth, int paramWidth, boolean der) {
        if(paramHeigth == 0 && paramWidth == 0){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Insets inset = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());

            int workingScreen = screenSize.height - inset.bottom;
            int add = this.esNumero ? 75 : 5;

            int width = (der ? screenSize.width - (getWidth()+add) : 0 + add) ; //Positions horizontally to middle of screen
            int height = workingScreen - getHeight(); //Positions vertically just above the taskbar

            setLocation(width, height);
        } else {
            setLocation(paramWidth, paramHeigth);
        }
    }

    /**
     * Performs a key press event on the specific key. Sends a key pressed event 
     * to the current foreground application.
     * @param evt 
     */
    private void keyPressed(MouseEvent evt) {
        JLabel key = (JLabel)evt.getSource();
        key.setBackground(SELECTED_COLOR);
    }

    /**
     * Releases a key that was pressed. Sends a key release event to the current
     * foreground application.
     * @param evt 
     */
    private void keyReleased(MouseEvent evt) {
        JLabel key = (JLabel)evt.getSource();
        key.setBackground(DEFAULT_KEY_COLOR);
    }

    
    /**
     * Performs a mouse click event on the key. Mouse click generally send a key associated
     * with the source of the event object to the currently focusable foreground application.
     * @param evt 
     */
    private void keyClicked(MouseEvent evt) {
        JLabel key = (JLabel)evt.getSource();           //Source clicked key.
        int keycode = Integer.decode(key.getName());    //Virtual key code associated with the key
        boolean specialkey = false;
        System.out.println("tecla" + keycode);
        switch(keycode) {
	        case VK_SHIFT:
	            if (Keyboard.isShiftPressed()) {
	                    Keyboard.releaseKey(VK_SHIFT);
	                }
	            else {
	                Keyboard.pressKey(VK_SHIFT);
	            }
	            specialkey = true;
	            break;
	        case VK_CONTROL:
	            if (Keyboard.isCtrlPressed())
	                Keyboard.releaseKey(VK_CONTROL);
	            else
	                Keyboard.pressKey(VK_CONTROL);
	            specialkey = true;
	            break;
	        case VK_ALT:
	            if(Keyboard.isAltPressed())
	                Keyboard.releaseKey(VK_ALT);
	            else
	                Keyboard.pressKey(VK_ALT);
	            specialkey = true;
	            break;
	        case VK_CAPS_LOCK:
	        	if (Keyboard.isCapsLockOn()) {
	        		Keyboard.releaseKey(VK_CAPS_LOCK);
	            }else {
	                Keyboard.pressKey(VK_CAPS_LOCK);
	            }
	            specialkey = true;
	            break;
	        case VK_NUM_LOCK:
	            Keyboard.typeKey(VK_NUM_LOCK);
	            specialkey = true;
	            break;
	        case VK_SCROLL_LOCK:
	            Keyboard.typeKey(VK_SCROLL_LOCK);
	            specialkey = true;
	            break;
	        case 0xff: //Function key pressed
	            IS_FUNCTION_KEY_PRESSED = !IS_FUNCTION_KEY_PRESSED;
	            specialkey = true;
	            break;
        }
        
		if(!specialkey) {
			if((keycode > 43 && keycode < 58) || (keycode == 97 || keycode ==98))
				updateKeys(keycode);
			else 
				Keyboard.typeKey(keycode);
	    }
		controlador.controlarTeclas(keycode);
    }
    
    public void actualizar(int keycode) {
    	this.updateAlphabeticKeys();
    	this.updateFunctionKeys();
    	this.updateNumeroKeys();
    	this.updateSpecialKeys();
    }

    private void updateKeys(int keycode) {
    	int keys[] = null;
    	switch(keycode) {
	    	case 44:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {101,105};
					keys = valor;    				
				}
				break;
    		case 45:
    			if(Keyboard.isCapsLockOn()) {
    				int valor[] = {105,101};
    				keys = valor;    				
    			}
    			break;
    		case 46:
    			if(Keyboard.isCapsLockOn()) {
    				int valor[] = {101,104};
    				keys = valor;    				
    			}
    			break;
			case 48:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {102,97};
					keys = valor;
				}
				break;
    		case 49:
    			if(Keyboard.isCapsLockOn()) {
    				int valor[] = {99,105};
    				keys = valor;    				
    			}
				break;
			case 50:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {102,100};
					keys = valor;					
				}
				break;
			case 51:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {99,101};
					keys = valor;					
				}
				break;
			case 52:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {99,102};
					keys = valor;					
				}
				break;
			case 53:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {99,103};
					keys = valor;					
				}
				break;
			case 54:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {99,104};
					keys = valor;					
				}
				break;
			case 55:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {100,103};
					keys = valor;					
				}
				break;
			case 56:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {100,96};
					keys = valor;
				}
				break;
			case 57:
				if(Keyboard.isCapsLockOn()) {
					int valor[] = {100,97};
					keys = valor;
				}
				break;
    		case 97: 
    			if(Keyboard.isCapsLockOn()) {
    				int valor[] = {102,99};
    				keys = valor;
    			} else {
    				int valor[] = {97,102,104};  
    				keys = valor;
    			} 
    			break;
    		case 98:
    			if(Keyboard.isCapsLockOn()) {
    				int valor[] = {99,99};
    				keys = valor;
    			} else {
    				int valor[] = {97,103,99};
    				keys = valor;
    			}
    			break;
			default: break;
    	}
    	
    	if(keys != null) 
    		utilizarAlt(keys);
    	else 
			Keyboard.typeKey(keycode);
    }
	
    private void utilizarAlt(int[] keys) {
    	
    	/*
    	 *	Funciona con los valores de las teclas del teclado númerico
    	 *	Cada key que tiene en la lista funciona como número a marcar del
    		teclado númerico
    	*/
    	
    	Keyboard.pressKey(18);
    	for(int key : keys) {
    		System.out.println("ALT TRABAJANDO : " + key);
    		Keyboard.typeKey(key);
    	}
    	Keyboard.releaseKey(18);
    	
    }
    
	/**
     * Performs a hover effect on the key button.
     * @param evt 
     */
    private void keyMouseEntered(MouseEvent evt) {
        JLabel source = (JLabel) evt.getSource();
        
        /**
         * Check if the current key is a special key or not.
         * Perform no hover effect if special keys(e.g. CapsLock, NumLock, Shift etc)
         * keys are pressed.
         */
        int keycode = Integer.decode(source.getName());
        switch(keycode) {
            case VK_SHIFT:
                if(Keyboard.isShiftPressed())
                    return;
                break;
            case VK_CONTROL:
                if(Keyboard.isCtrlPressed())
                    return;
                break;
            case VK_ALT:
                if(Keyboard.isAltPressed())
                    return;
                break;
            case VK_CAPS_LOCK:
                if(Keyboard.isCapsLockOn())
                    return;
                break;
            case VK_NUM_LOCK:
                if(Keyboard.isNumLockOn())
                    return;
                break;
            case VK_SCROLL_LOCK:
                if(Keyboard.isScrollLockOn())
                    return;
                break;
            case 0xff: //If the function key is pressed
                if(IS_FUNCTION_KEY_PRESSED)
                    return;
        }
        
        source.setBackground(Color.GRAY);
    }

    /**
     * Performs a hover effect on the key button.
     * @param evt 
     */
    private void keyMouseExited(MouseEvent evt) {
        JLabel source = (JLabel) evt.getSource();
        
        /**
         * Check if the current key is special key or not.
         * Perform no hover effect if the special keys(e.g. CapsLock, NumLock, Shift key etc)
         * are pressed.
         */
        int keycode = Integer.decode(source.getName());
        switch(keycode) {
            case VK_SHIFT:
                if(Keyboard.isShiftPressed())
                    return;
                break;
            case VK_CONTROL:
                if(Keyboard.isCtrlPressed())
                    return;
                break;
            case VK_ALT:
                if(Keyboard.isAltPressed())
                    return;
                break;
            case VK_CAPS_LOCK:
                if(Keyboard.isCapsLockOn())
                    return;
                break;
            case VK_NUM_LOCK:
                if(Keyboard.isNumLockOn())
                    return;
                break;
            case VK_SCROLL_LOCK:
                if(Keyboard.isScrollLockOn())
                    return;
                break;
            case 0xff: //If the function key is pressed
                if(IS_FUNCTION_KEY_PRESSED)
                    return;
        }
        
        source.setBackground(DEFAULT_KEY_COLOR);
    }

    /**
     * Changes the text of the alphabetic keys. This function to be called on every 
     * shift or caps key press or release. This function changes the text of the
     * alphabetic keys to CAPS and to small according to the current status of the
     * Caps lock key and Shift key.
     * *****EN CASO TAL DE AGREGAR LA TECLA CAPS *****
     * boolean caps =  (Keyboard.isShiftPressed() && !Keyboard.isCapsLockOn()) || 
     * (!Keyboard.isShiftPressed() && Keyboard.isCapsLockOn());
     */

    private void updateAlphabeticKeys() {
        for(JLabel key : alphabetKeys) {
        	if(Keyboard.isCapsLockOn())
                key.setText(key.getText().toUpperCase());
            else
                key.setText(key.getText().toLowerCase());
        }
    }
    
    private void updateNumeroKeys() {
        for(Tecla key : numeros) {
        	if(Keyboard.isCapsLockOn())
                key.getLabel().setText(key.getTextAlt());
            else
                key.getLabel().setText(key.getText());
        }
    }

    /**
     * Changes the text of the function/numeric keys. This function is to be called
     * if the function key is clicked. 
     */
    private void updateFunctionKeys() {
        int i = 0;
        for(JLabel key : functionKeys) {
            if(IS_FUNCTION_KEY_PRESSED) {
                key.setText(FN_KEY_ON_TEXT[i]);
                key.setName(FN_KEY_ON_VK[i]);
            } else {
                key.setText(FN_KEY_OFF_TEXT[i]);
                key.setName(FN_KEY_OFF_VK[i]);
            }
            
            i++;
        }
    }

    /**
     * Changes the current state of the special keys(e.g. CapsLock, NumLock, Shift etc).
     * Sets whether the special keys pressed or not. If the special key is pressed 
     * then blue colored keys are shown else normal keys.
     */
    private void updateSpecialKeys() {
        /*if(Keyboard.isCapsLockOn())
            keycaps.setBackground(SELECTED_COLOR);
        else
            keycaps.setBackground(DEFAULT_KEY_COLOR);
        */
        /*if(Keyboard.isNumLockOn())
            keynumlock.setBackground(SELECTED_COLOR);
        else
            keynumlock.setBackground(DEFAULT_KEY_COLOR);
        
        if(Keyboard.isScrollLockOn())
            keyscrolllock.setBackground(SELECTED_COLOR);
        else
            keyscrolllock.setBackground(DEFAULT_KEY_COLOR);
        */
        
        if(Keyboard.isCapsLockOn()) {
            keyshiftl.getLabel().setBackground(SELECTED_COLOR);
            keyshiftl.getLabel().setText(keyshiftl.getTextAlt());
            //keyshiftr.setBackground(SELECTED_COLOR);
        } else {
            keyshiftl.getLabel().setBackground(DEFAULT_KEY_COLOR);
            keyshiftl.getLabel().setText(keyshiftl.getText());
            //keyshiftr.setBackground(DEFAULT_KEY_COLOR);
        }
        
        /*
        if(Keyboard.isCtrlPressed()) {
            keyctrl1.setBackground(SELECTED_COLOR);
            keyctrl2.setBackground(SELECTED_COLOR); 
        } else {
            keyctrl1.setBackground(DEFAULT_KEY_COLOR);
            keyctrl2.setBackground(DEFAULT_KEY_COLOR);
        }
        
        if(Keyboard.isAltPressed()) {
            keyalt.setBackground(SELECTED_COLOR);
            keyaltgr.setBackground(SELECTED_COLOR);
        } else {
            keyalt.setBackground(DEFAULT_KEY_COLOR);
            keyaltgr.setBackground(DEFAULT_KEY_COLOR);
        }

        if(IS_FUNCTION_KEY_PRESSED)
            keyfn.setBackground(SELECTED_COLOR);
        else
            keyfn.setBackground(DEFAULT_KEY_COLOR);
            */
    }
    
    private void armarGrilla() {
    	if(!this.esNumero) {
    		armarLetras();
    		inicializarKeys(letras);   		
    	}
		armarFunciones();
		inicializarKeys(funciones); 
		armarNumeros();
		inicializarKeys(numeros);
    }
    
    private void initComponents(boolean der) {

        mainPanel = new JPanel();
        AbstractBorder borderMain = new TextBubbleBorder(Color.BLACK,6,2,-6);
        mainPanel.setBorder(borderMain);
        mainPanel.setBackground(new Color(0,0,0));
        armarGrilla();
        Grupos grupo = new Grupos();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Imago Teclado");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setBackground(Color.GRAY);
        setFocusable(false);
        setFocusableWindowState(false);
        setResizable(false);
        Tecla[][] teclas;
        if(der) {
        	if(this.esNumero) {
        		Tecla[][] valores = {
    	        		{key7,key8,key9},
    	        		{key4,key5,key6},
    	        		{key1,key2,key3},
    	        		{key0,keybackspace}
    	        		};
    	        teclas = valores;
        	}else {
        		Tecla[][] valores = {
        				{key6,key7,key8,key9,key0},
        				{keyu,keyi,keyo,keyp,keyguion,keypunto},
        				{keyh,keyj,keyk,keyl,keybackspace},
        				{keyb,keyn,keym,keycoma},
        				{keyspace,keycaps}
        		};
        		teclas = valores;        		
        	}
        } else {
	    	Tecla[][] valores ={
	        		{key1,key2,key3,key4,key5},
	        		{keyq,keyw,keye,keyr,keyt,keyy},
	        		{keya,keys,keyd,keyf,keyg},
	        		{keyz,keyx,keyc,keyv},
	        		{keyexclamacion,keyinterrogacion,keyspace}
	        		};
		    	
	    	teclas = valores;
        }
        
        JPanel[] paneles = inicializarPaneles();
        grupo.crear(paneles, teclas,3);
        grupo.crearMain(paneles, mainPanel, this);
        

        pack();
    }
    
    private JPanel[] inicializarPaneles() {
    	
    	primerPanel = new JPanel();
    	segundoPanel = new JPanel();
    	tercerPanel = new JPanel();
		cuartoPanel = new JPanel();
		
    	if(!this.esNumero) {
    		numeroPanel = new JPanel();
    	}
    	
    	JPanel[] panelesNumber = {primerPanel,segundoPanel,tercerPanel,cuartoPanel};
    	JPanel[] paneles = {primerPanel,segundoPanel,tercerPanel,cuartoPanel,numeroPanel};
    	
		desingPanel(this.esNumero?panelesNumber:paneles);
		return this.esNumero?panelesNumber:paneles;
		
	}

	private void desingPanel(JPanel[] paneles) {
    	
		for(JPanel panel : paneles) {
			panel.setBackground(new Color(0,0,0));
			panel.setFocusable(false);
		}

    }
  
    private void inicializarKeys(List<Tecla>lista) {

        AbstractBorder border = new TextBubbleBorder(Color.WHITE,1,8,0);
        Font fuenteLetra = new Font("Helvetica",Font.BOLD,16);
        
        for(Tecla tecla:lista) {
        	JLabel t = tecla.getLabel();
        	t.setBorder(border);
        	t.setFont(fuenteLetra);
        	t.setBackground(DEFAULT_KEY_COLOR);
        	t.setForeground(java.awt.Color.white);
        	t.setHorizontalAlignment(SwingConstants.CENTER);
        	t.setText(tecla.getText());
        	t.setFocusable(false);
            t.setMaximumSize(new java.awt.Dimension(tecla.getWidthMax(), tecla.getHeigth()));
            t.setMinimumSize(new java.awt.Dimension(tecla.getWidth(), tecla.getHeigth()));
            t.setName(tecla.getName());
            t.setOpaque(true);
            //t.setPreferredSize(new java.awt.Dimension(tecla.getWidth(), tecla.getHeigth()));
            t.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    keyClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    keyMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    keyMouseExited(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    keyPressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    keyReleased(evt);
                }
            });
        }
    }
    
    private void armarFunciones() {
    	
    	keyshiftl = new Tecla(new JLabel(),"@&$ MAYUS","0x10",80,40,120,"minuscula");
    	funciones.add(keyshiftl);
    	
    	keyspace = new Tecla(new JLabel()," ","0x20",150,40,3000);
    	funciones.add(keyspace);
    	
    	keybackspace = new Tecla(new JLabel(),"BORRAR","0x08",120,40,120);
    	funciones.add(keybackspace);
    	
    	keyenter = new Tecla(new JLabel(), "ENTER", "0x0A",80,40,105);
    	funciones.add(keyenter);
    	
    	keycaps = new Tecla(new JLabel(),"@&$ MAYUS","0x14",80,51,120,"minuscula");
    	funciones.add(keycaps);
    	
    }
    
    private void armarLetras() {
    	keya = new Tecla(new JLabel(),"a","0x41");
    	letras.add(keya);
    	
    	keyb = new Tecla(new JLabel(),"b","0x42");
    	letras.add(keyb);
    	
    	keyc = new Tecla(new JLabel(),"c","0x43");
    	letras.add(keyc);
    	
    	keyd = new Tecla(new JLabel(),"d","0x44");
    	letras.add(keyd);
    	
    	keye = new Tecla(new JLabel(),"e","0x45");
    	letras.add(keye);
    	
    	keyf = new Tecla(new JLabel(),"f","0x46");
    	letras.add(keyf);
    	
    	keyg = new Tecla(new JLabel(),"g","0x47");
    	letras.add(keyg);
    	
    	keyh = new Tecla(new JLabel(),"h","0x48");
    	letras.add(keyh);
    	
    	keyi = new Tecla(new JLabel(),"i","0x49");
    	letras.add(keyi);
    	
    	keyj = new Tecla(new JLabel(),"j","0x4A");
    	letras.add(keyj);
    	
    	keyk = new Tecla(new JLabel(),"k","0x4B");
    	letras.add(keyk);
    	
    	keyl = new Tecla(new JLabel(),"l","0x4C");
    	letras.add(keyl);
    	
    	keym = new Tecla(new JLabel(),"m","0x4D");
    	letras.add(keym);
    	
    	keyn = new Tecla(new JLabel(),"n","0x4E");
    	letras.add(keyn);
    	
    	keyo = new Tecla(new JLabel(),"o","0x4F");
    	letras.add(keyo);
    	
    	keyp = new Tecla(new JLabel(),"p","0x50");
    	letras.add(keyp);
    	
    	keyq = new Tecla(new JLabel(),"q","0x51");
    	letras.add(keyq);
    	
    	keyr = new Tecla(new JLabel(),"r","0x52");
    	letras.add(keyr);
    	
    	keys = new Tecla(new JLabel(),"s","0x53");
    	letras.add(keys);
    	
    	keyt = new Tecla(new JLabel(),"t","0x54");
    	letras.add(keyt);
    	
    	keyu = new Tecla(new JLabel(),"u","0x55");
    	letras.add(keyu);
    	
    	keyv = new Tecla(new JLabel(),"v","0x56");
    	letras.add(keyv);
    	
    	keyw = new Tecla(new JLabel(),"w","0x57");
    	letras.add(keyw);
    	
    	keyx = new Tecla(new JLabel(),"x","0x58");
    	letras.add(keyx);
    	
    	keyy = new Tecla(new JLabel(),"y","0x59");
    	letras.add(keyy);
    	
    	keyz = new Tecla(new JLabel(),"z","0x5A");
    	letras.add(keyz);
    }
    
    private void armarNumeros() {    	
    	key0 = new Tecla(new JLabel(),"0","0x30","=");
    	numeros.add(key0);
    	
    	key1 = new Tecla(new JLabel(),"1","0x31","'");
    	numeros.add(key1);
    	
    	key2 = new Tecla(new JLabel(),"2","0x32","@");
    	numeros.add(key2);
    	
    	key3 = new Tecla(new JLabel(),"3","0x33","#");
    	numeros.add(key3);
    	
    	key4 = new Tecla(new JLabel(),"4","0x34","$");
    	numeros.add(key4);
    	
    	key5 = new Tecla(new JLabel(),"5","0x35","%");
    	numeros.add(key5);
    	
    	key6 = new Tecla(new JLabel(),"6","0x36","&");
    	numeros.add(key6);
    	
    	key7 = new Tecla(new JLabel(),"7","0x37","/");
    	numeros.add(key7);
    	
    	key8 = new Tecla(new JLabel(),"8","0x38","(");
    	numeros.add(key8);
    	
    	key9 = new Tecla(new JLabel(),"9","0x39",")");
    	numeros.add(key9);
    	
    	keycoma = new Tecla(new JLabel(),",","0x2C",";");
    	numeros.add(keycoma);
    	
    	keypunto = new Tecla(new JLabel(),".","0x2E",":");
    	numeros.add(keypunto);
    	
    	keyguion = new Tecla(new JLabel(),"-","0x2D","_");
    	numeros.add(keyguion);
    	
    	keyinterrogacion = new Tecla(new JLabel(),"¿","0x61","?");
    	numeros.add(keyinterrogacion);
    	
    	keyexclamacion = new Tecla(new JLabel(),"¡","0x62","!");
    	numeros.add(keyexclamacion);
    	
    	
    }

    private JPanel primerPanel;
    private JPanel segundoPanel;
    private JPanel tercerPanel;
    private JPanel cuartoPanel;
    private JPanel letraPanel;
    private JPanel funcionPanel;
    private JPanel mainPanel;
    private JPanel numeroPanel;

    private Tecla key0;
    private Tecla key1;
    private Tecla key2;
    private Tecla key3;
    private Tecla key4;
    private Tecla key5;
    private Tecla key6;
    private Tecla key7;
    private Tecla key8;
    private Tecla key9;
    private Tecla keya;
    private JLabel keyalt;
    private JLabel keyaltgr;
    private Tecla keyb;
    private JLabel keybackquote;
    private Tecla keybackspace;
    private JLabel keybslash;
    private Tecla keyc;
    private Tecla keycaps;
    private JLabel keyclosebigbracket;
    private Tecla keycoma;
    private Tecla keypunto;
    private Tecla keyguion;
    private Tecla keyinterrogacion;
    private Tecla keyexclamacion;
    private JLabel keyctrl1;
    private JLabel keyctrl2;
    private Tecla keyd;
    private JLabel keydelete;
    private JLabel keydot;
    private JLabel keydownarrow;
    private Tecla keye;
    private JLabel keyend;
    private Tecla keyenter;
    private JLabel keyequal;
    private Tecla keyf;
    private JLabel keyfn;
    private JLabel keyfslash;
    private Tecla keyg;
    private Tecla keyh;
    private JLabel keyhome;
    private Tecla keyi;
    private JLabel keyinsert;
    private Tecla keyj;
    private Tecla keyk;
    private Tecla keyl;
    private JLabel keyleftarrow;
    private Tecla keym;
    private JLabel keyminus;
    private Tecla keyn;
    private JLabel keynum0;
    private JLabel keynum1;
    private JLabel keynum2;
    private JLabel keynum3;
    private JLabel keynum4;
    private JLabel keynum5;
    private JLabel keynum6;
    private JLabel keynum7;
    private JLabel keynum8;
    private JLabel keynum9;
    private JLabel keynumdivide;
    private JLabel keynumdot;
    private JLabel keynumenter;
    private JLabel keynumlock;
    private JLabel keynumminus;
    private JLabel keynummult;
    private JLabel keynumplus;
    private Tecla keyo;
    private JLabel keyopenbigbracket;
    private Tecla keyp;
    private JLabel keypgdn;
    private JLabel keypgup;
    private JLabel keyprtscr;
    private Tecla keyq;
    private JLabel keyquote;
    private Tecla keyr;
    private JLabel keyrightarrow;
    private Tecla keys;
    private JLabel keyscrolllock;
    private JLabel keysemicolon;
    private Tecla keyshiftl;
    private JLabel keyshiftr;
    private Tecla keyspace;
    private Tecla keyt;
    private JLabel keytab;
    private Tecla keyu;
    private JLabel keyuparrow;
    private Tecla keyv;
    private Tecla keyw;
    private JLabel keycommercialat;
    private Tecla keyx;
    private Tecla keyy;
    private Tecla keyz;
    
    private List<Tecla> numeros = new ArrayList();
    private List<Tecla> letras = new ArrayList();
    private List<Tecla> funciones = new ArrayList();

	@Override
	public void run() {
		setUndecorated(true);
		initComponents(this.esDerecho);
        setLocationKeyBoard(this.heigth, this.width, this.esDerecho);
        setIcon();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(250,150));
        getContentPane().setBackground(new Color(0,0,0));
		setVisible(true);
        initKeys(!this.esNumero?alphabetKeys:numeroKeys,primerPanel);
        initKeys(!this.esNumero?alphabetKeys:numeroKeys,segundoPanel);
        initKeys(!this.esNumero?alphabetKeys:numeroKeys,tercerPanel);
        initKeys(!this.esNumero?alphabetKeys:numeroKeys,cuartoPanel);
		if(!this.esNumero) {
			initKeys(numeroKeys,numeroPanel);			
		}
        this.controlador.setTeclado(this);
		
	}

}
