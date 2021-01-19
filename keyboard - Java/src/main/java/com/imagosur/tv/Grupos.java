package com.imagosur.tv;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grupos {
	
	
	public void crear(JPanel panel, Tecla[] keys, int gap) {
		ejecutar(panel,keys,gap);
	}
	
	public void crear(JPanel[] paneles, Tecla[][] teclas, int gap) {
			
		if(paneles.length == teclas.length) {
				for(int i = 0; i < paneles.length; i++) {
					ejecutar(paneles[i],teclas[i],gap);
				}
		}else {
			error("Paneles es distinto a teclas");
		}
	}
	
	public void crearMain(JPanel[] paneles, JPanel main, JFrame keyboard) {

		switch(paneles.length) {
		
			case 2: error("MAIN no existe:" + paneles.length);
			break;
			case 3: error("MAIN no existe:" + paneles.length);
			break;
			case 4: conCuatro(paneles,main,keyboard);
			break;
			case 5: conCinco(paneles,main,keyboard);
			break;
			case 6: error("MAIN no existe:" + paneles.length);
			break;
			case 7: error("MAIN no existe:" + paneles.length);
			break;
			case 8: error("MAIN no existe:" + paneles.length);
			break;
			default: error("MAIN no existe:" + paneles.length);
		
		}
		
		
	}
	
	private void ejecutar(JPanel panel, Tecla[] keys, int gap) {
		
		switch(keys.length) {
			case 1: conUno(panel,keys,gap);
			break;
			case 2: conDos(panel,keys,gap);
			break;
			case 3: conTres(panel,keys,gap);
			break;
			case 4: conCuatro(panel,keys,gap);
			break;
			case 5: conCinco(panel,keys,gap);
			break;
			case 6: conSeis(panel,keys,gap);
			break;
			case 7: conSiete(panel,keys,gap);
			break;
			case 8: conOcho(panel,keys,gap);
			break;
			default: error("Ejecutar panel");
		}	
	}
	
	private void error(String lugar) {
		System.err.println("Ocurrió un error al intentar crear grupos: " + lugar);
	}
	
	//Group main panels
	
	private void conCuatro(JPanel[] paneles, JPanel main, JFrame keyboard) {

		GroupLayout panelCentral = new GroupLayout(main);
        main.setLayout(panelCentral);
        panelCentral.setHorizontalGroup(panelCentral.createSequentialGroup()
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[0])
        				.addComponent(paneles[1])
        				.addComponent(paneles[2])
        				.addComponent(paneles[3])));
        
        panelCentral.setVerticalGroup(panelCentral.createSequentialGroup()
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[0]))
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[1]))
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[2]))
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[3])));


        GroupLayout panel = new GroupLayout(keyboard.getContentPane());
        keyboard.getContentPane().setLayout(panel);
        panel.setHorizontalGroup(panel.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(panel.createSequentialGroup()
        				.addComponent(main)));
        
        panel.setVerticalGroup(panel.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(panel.createSequentialGroup()
        				.addComponent(main)));
	}

	private void conCinco(JPanel[] paneles, JPanel main, JFrame keyboard) {

		GroupLayout panelCentral = new GroupLayout(main);
        main.setLayout(panelCentral);
        panelCentral.setHorizontalGroup(panelCentral.createSequentialGroup()
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[0])
        				.addComponent(paneles[1])
        				.addComponent(paneles[2])
        				.addComponent(paneles[3])
        				.addComponent(paneles[4])));
        
        panelCentral.setVerticalGroup(panelCentral.createSequentialGroup()
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[0]))
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[1]))
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[2]))
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[3]))
        		.addGroup(panelCentral.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(paneles[4])));


        GroupLayout panel = new GroupLayout(keyboard.getContentPane());
        keyboard.getContentPane().setLayout(panel);
        panel.setHorizontalGroup(panel.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(panel.createSequentialGroup()
        				.addComponent(main)));
        
        panel.setVerticalGroup(panel.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(panel.createSequentialGroup()
        				.addComponent(main)));
	}
	//Group keys
	
	private void conOcho(JPanel panel, Tecla[] keys, int gap) {
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[1].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[2].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[3].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[4].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[5].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[6].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[7].getLabel()))
	        .addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    				.addComponent(keys[0].getLabel())
    				.addComponent(keys[1].getLabel())
    				.addComponent(keys[2].getLabel())
    				.addComponent(keys[3].getLabel())
    				.addComponent(keys[4].getLabel())
    				.addComponent(keys[5].getLabel())
    				.addComponent(keys[6].getLabel())
    				.addComponent(keys[7].getLabel()))
    		.addGap(gap)); 	
	}
	
	private void conSiete(JPanel panel, Tecla[] keys, int gap) {
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[1].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[2].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[3].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[4].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[5].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[6].getLabel()))
	        .addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    				.addComponent(keys[0].getLabel())
    				.addComponent(keys[1].getLabel())
    				.addComponent(keys[2].getLabel())
    				.addComponent(keys[3].getLabel())
    				.addComponent(keys[4].getLabel())
    				.addComponent(keys[5].getLabel())
    				.addComponent(keys[6].getLabel()))
    		.addGap(gap)); 	
	}
	
	private void conSeis(JPanel panel, Tecla[] keys, int gap) {
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[1].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[2].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[3].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[4].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[5].getLabel()))
	        .addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    				.addComponent(keys[0].getLabel())
    				.addComponent(keys[1].getLabel())
    				.addComponent(keys[2].getLabel())
    				.addComponent(keys[3].getLabel())
    				.addComponent(keys[4].getLabel())
    				.addComponent(keys[5].getLabel()))
    		.addGap(gap)); 	
	}

	private void conCinco(JPanel panel, Tecla[] keys, int gap) {    	
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[1].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[2].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[3].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[4].getLabel()))
	        .addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel())
				.addComponent(keys[1].getLabel())
				.addComponent(keys[2].getLabel())
				.addComponent(keys[3].getLabel())
				.addComponent(keys[4].getLabel()))
    		.addGap(gap)); 	
        
    }

	private void conCuatro(JPanel panel, Tecla[] keys, int gap) {    	
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[1].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[2].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[3].getLabel()))
	        .addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel())
				.addComponent(keys[1].getLabel())
				.addComponent(keys[2].getLabel())
				.addComponent(keys[3].getLabel()))
    		.addGap(gap)); 	
        
    }

	private void conTres(JPanel panel, Tecla[] keys, int gap) {    	
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[1].getLabel()))
	        .addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[2].getLabel()))
	        .addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel())
				.addComponent(keys[1].getLabel())
				.addComponent(keys[2].getLabel()))
    		.addGap(gap)); 	
        
    }

	private void conDos(JPanel panel, Tecla[] keys, int gap) {    	
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[1].getLabel()))
	        .addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel())
				.addComponent(keys[1].getLabel()))
    		.addGap(gap)); 	
        
    }
	
	private void conUno(JPanel panel, Tecla[] keys, int gap) {    	
		GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
			.addGap(gap));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(keys[0].getLabel()))
    		.addGap(gap)); 			
	}
}
