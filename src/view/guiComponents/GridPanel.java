package view.guiComponents;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.constants.ViewConstants;
import controller.interfaces.GridInterface;

public class GridPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 198183154238105700L;

	private GridInterface controller;
	
	public GridPanel(final GridInterface controller){
		this.controller = controller;
		this.controller.addObserver(this);
		this.setPreferredSize(ViewConstants.getGridPanelDimension());
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent arg0) {
				controller.handleMouseDrag(arg0);
			}
			public void mouseMoved(MouseEvent arg0) {
				controller.handleMouseMove(arg0);
			}			
		});
		
		this.setVisible(true);
	}
	
	@Override
    public void paint(Graphics g) {
		super.paint(g);
		controller.repaint(g);			
    }

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
}