package view.guiComponents;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.interfaces.LoginInterface;

@SuppressWarnings("serial")
public class LoginSelectionPanel extends JPanel {

	private static final Dimension PREF_PANEL_SIZE = new Dimension(800,200);
	
	private LoginInterface controller;
	private JButton newProfileButton;
	private JButton deleteProfileButton;
	private TableModel tableModel;
	
	private static final String[] COLUMN_HEADERS = {"User ID", "Player Name", "Wins", "Games Played" };
	
	public LoginSelectionPanel(LoginInterface controller){
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		initGui();
	}
	
	private void initGui(){
		this.setPreferredSize(PREF_PANEL_SIZE);
		this.add(new JLabel("Select a profile"));
		this.add(this.createProfileSelectionPane());
		newProfileButton = new JButton("Create Profile");
//        HireListener hireListener = new HireListener(newProfileButton);
        newProfileButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
        });
        newProfileButton.setEnabled(false);

        deleteProfileButton = new JButton("Delete Profile");
//        fireButton.setActionCommand(fireString);
        deleteProfileButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
        });

        JTextField profileNameField = new JTextField(10);
//        profileNameField.addActionListener(hireListener);
//        profileNameField.getDocument().addDocumentListener(hireListener);
	}
	
	private JScrollPane createProfileSelectionPane(){
//		JPanel panel = new JPanel();
//		panel.setPreferredSize(PREF_PANEL_SIZE);
		JTable profileList = createProfileList();
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(profileList);
        //Add the scroll pane to this panel.
        return scrollPane;
	}
	
	private JTable createProfileList(){
        final JTable table = new JTable(createTableModel());        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
		return table;
	}
	
	private TableModel createTableModel(){
		DefaultTableModel tableModel = new DefaultTableModel();
		Object[][] tableData = controller.getTableData();
		tableModel.setDataVector(tableData, COLUMN_HEADERS);
		return tableModel;
	}
	
	private JPanel createNewProfilePanel(){
		JPanel panel = new JPanel();
		panel.setPreferredSize(PREF_PANEL_SIZE);
		return panel;
	}
	
	public void handleLoginAction(){
		
	}
	
//    class FireListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            //This method can be called only if
//            //there's a valid selection
//            //so go ahead and remove whatever's selected.
//            int index = list.getSelectedIndex();
//            listModel.remove(index);
//
//            int size = listModel.getSize();
//
//            if (size == 0) { //Nobody's left, disable firing.
//                fireButton.setEnabled(false);
//
//            } else { //Select an index.
//                if (index == listModel.getSize()) {
//                    //removed item in last position
//                    index--;
//                }
//
//                list.setSelectedIndex(index);
//                list.ensureIndexIsVisible(index);
//            }
//        }
//    }
//
//	//This listener is shared by the text field and the hire button.
//    /**
//     * This class is taken from the ListDemo.java example found on Sun Microsystem's tutorial site.
//     */
//    class NewProfileListener implements ActionListener, DocumentListener {
//        private boolean alreadyEnabled = false;
//        private JButton newProfileButton;
//        private JTextField profileNameField;
//
//        public NewProfileListener(JButton newProfileButton, JTextField profileNameField) {
//            this.newProfileButton = newProfileButton;
//            this.profileNameField = profileNameField;
//        }
//
//        //Required by ActionListener.
//        public void actionPerformed(ActionEvent e) {
//            String name = profileNameField.getText();
//
//            //User didn't type in a unique name...
//            if (name.equals("") || alreadyInList(name)) {
//                Toolkit.getDefaultToolkit().beep();
//                profileNameField.requestFocusInWindow();
//                profileNameField.selectAll();
//                return;
//            }
//
//            int index = list.getSelectedIndex(); //get selected index
//            if (index == -1) { //no selection, so insert at beginning
//                index = 0;
//            } else {           //add after the selected item
//                index++;
//            }
//
//            listModel.insertElementAt(profileNameField.getText(), index);
//            //If we just wanted to add to the end, we'd do this:
//            //listModel.addElement(employeeName.getText());
//
//            //Reset the text field.
//            profileNameField.requestFocusInWindow();
//            profileNameField.setText("");
//
//            //Select the new item and make it visible.
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
//        }
//        
//        //This method tests for string equality. You could certainly
//        //get more sophisticated about the algorithm.  For example,
//        //you might want to ignore white space and capitalization.
//        protected boolean alreadyInList(String name) {
//            return listModel.contains(name);
//        }
//
//        //Required by DocumentListener.
//        public void insertUpdate(DocumentEvent e) {
//            enableButton();
//        }
//
//        //Required by DocumentListener.
//        public void removeUpdate(DocumentEvent e) {
//            handleEmptyTextField(e);
//        }
//
//        //Required by DocumentListener.
//        public void changedUpdate(DocumentEvent e) {
//            if (!handleEmptyTextField(e)) {
//                enableButton();
//            }
//        }
//
//        private void enableButton() {
//            if (!alreadyEnabled) {
//            	newProfileButton.setEnabled(true);
//            }
//        }
//
//        private boolean handleEmptyTextField(DocumentEvent e) {
//            if (e.getDocument().getLength() <= 0) {
//            	newProfileButton.setEnabled(false);
//                alreadyEnabled = false;
//                return true;
//            }
//            return false;
//        }
//    }
}