package view.guiComponents;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -196348379589869798L;

	private GridPanel gridPanel;
	private ControlsPanel controlsPanel;
	
	public GridPanel getGridPanel() {
		return gridPanel;
	}
	public void setGridPanel(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
	}
	public ControlsPanel getControlsPanel() {
		return controlsPanel;
	}
	public void setControlsPanel(ControlsPanel controlsPanel) {
		this.controlsPanel = controlsPanel;
	}
	
	public void init(){
		this.add(gridPanel);
		this.add(controlsPanel);
	}
	
	@Override
	public void paint(Graphics graphics){
		super.paint(graphics);
	}
}