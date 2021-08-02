package view.guiComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.constants.ViewConstants;
import controller.interfaces.LoginInterface;

public class LoginPanel extends JPanel{

	private static final long serialVersionUID = 5167045053891865205L;
	
	private LoginInterface controller;
	private JTable table;
    private DefaultTableModel tableModel;
    private Rectangle visibleRectangle;

	private static final String[] COLUMN_HEADERS = {"User ID", "Player Name", "Wins", "Games Played", "Saved game available" };

    private static final String deleteProfileString = "Delete Profile";
    private static final String createProfileString = "Create Profile";
    private JButton createProfileButton;
    private JButton deleteProfileButton;
    private JButton loginButton;
    private JTextField profileName;

    public LoginPanel(final LoginInterface controller, ActionListener loginListener) {
        super(new BorderLayout());
        this.controller = controller;
        
        loginButton = new JButton("Log in to Selected Profile");
        Dimension buttonSize = ViewConstants.buttonSize;		
		loginButton.setPreferredSize(new Dimension(buttonSize.width+45, buttonSize.height));
		loginButton.addActionListener(loginListener);

        JScrollPane tableScrollPane = createProfileSelectionPane();
        visibleRectangle = tableScrollPane.getVisibleRect();        

        createProfileButton = new JButton(createProfileString);
        CreateProfileListener createProfileListener = new CreateProfileListener();        
        createProfileButton.setActionCommand(createProfileString);
        createProfileButton.addActionListener(createProfileListener);
        createProfileButton.setEnabled(true);        

        deleteProfileButton = createDeleteProfileButton();

        profileName = new JTextField(20);
        profileName.addActionListener(createProfileListener);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(deleteProfileButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(profileName);
        buttonPane.add(createProfileButton);
        buttonPane.add(this.loginButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        
        checkButtonEnabling();
    }
    
    private JButton createDeleteProfileButton(){
    	final JButton deleteProfileButton = new JButton(deleteProfileString);
        deleteProfileButton.setActionCommand(deleteProfileString);
        deleteProfileButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                UUID selectedID = (UUID) tableModel.getValueAt(index, 0);
                controller.deleteProfile(selectedID);
                setTableData(controller.getTableData());

                int size = tableModel.getRowCount();

                if (size == 0) {
                    deleteProfileButton.setEnabled(false);
                    loginButton.setEnabled(false);
                }
                table.scrollRectToVisible(visibleRectangle);
            }
        });
        deleteProfileButton.setEnabled(true);
        return deleteProfileButton;
    }

	private JScrollPane createProfileSelectionPane(){
		table = createProfileList();
		
		table.addMouseListener(new MouseListener(){
			@Override public void mouseClicked(MouseEvent arg0) {}
			@Override public void mouseEntered(MouseEvent arg0) {}
			@Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mousePressed(MouseEvent arg0) {}
			@Override public void mouseReleased(MouseEvent arg0) {
				checkButtonEnabling();
			}			
		});
		
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        return scrollPane;
	}
	
	private JTable createProfileList(){
        final JTable table = new JTable(createTableModel());        
        table.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
	
	private DefaultTableModel createTableModel(){
		tableModel = new DefaultTableModel();
		Object[][] tableData = controller.getTableData();
		tableModel.setDataVector(tableData, COLUMN_HEADERS);
		return tableModel;
	}
	
	public void setTableData(Object[][] tableData){
		tableModel.setDataVector(tableData, COLUMN_HEADERS);
	}

    public UUID getSelectedProfileID(){
    	return (UUID) tableModel.getValueAt(table.getSelectedRow(), 0);
    }
    
    class CreateProfileListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
            String name = profileName.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                profileName.requestFocusInWindow();
                profileName.selectAll();
                return;
            }else{
                controller.createNewProfile(name);             
                setTableData(controller.getTableData());
                
                profileName.requestFocusInWindow();
                profileName.setText("");

                table.scrollRectToVisible(visibleRectangle);
            }
            
            checkButtonEnabling();
        }

        private boolean alreadyInList(String name) {
        	for(int index=0; index < tableModel.getRowCount(); index++){
        		String currentName = (String) tableModel.getValueAt(0, 1);
        		if(currentName.equalsIgnoreCase(name)){
        			return true;
        		}
        	}        	
        	return false;
        }
    }
    
    private void checkButtonEnabling(){
    	if (table.getSelectedRow() == -1) {
        	//No selection, disable fire button.
            deleteProfileButton.setEnabled(false);
            loginButton.setEnabled(false);
        } else {
        	//Selection, enable the fire button.
            deleteProfileButton.setEnabled(true);
            loginButton.setEnabled(true);
        }
    }
    
    public void setTableModel(DefaultTableModel tableModel){
    	this.tableModel = tableModel;
    }
}