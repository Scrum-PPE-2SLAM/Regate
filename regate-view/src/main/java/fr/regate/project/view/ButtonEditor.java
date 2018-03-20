package fr.regate.project.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

class ButtonEditor extends DefaultCellEditor 
{
	private static final long serialVersionUID = 1L;
	private JButton button;

	public ButtonEditor(JCheckBox checkBox, LancementRegate lr) 
	{
		super(checkBox);
    
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		fireEditingStopped();
	    	}
	    });
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
	{
		if (isSelected) 
		{
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else 
		{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
		String label = (value == null) ? "" : value.toString();
    button.setText(label);
    return button;
  }

	public boolean stopCellEditing()
	{
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() 
	{
		super.fireEditingStopped();
	}
}
