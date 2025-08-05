package org.example.ShipPackerUnpacker;

import javax.swing.*;

public class Packer {
    public void Pack(JButton sourceButton) {
        try {
            // Show JFileChooser immediately
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int result = fileChooser.showOpenDialog(null); // IMPORTANT: You must call showOpenDialog

            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();

                String PackName = JOptionPane.showInputDialog("Enter the name of the file you want to create for packing:");

                if (selectedPath != null && PackName != null && !PackName.isEmpty()) {
                    ShipPacker mobj = new ShipPacker(PackName, selectedPath);
                    mobj.PackingActivity();
                    JOptionPane.showMessageDialog(null, "Packing completed!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Missing path or pack file name.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Packing cancelled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
