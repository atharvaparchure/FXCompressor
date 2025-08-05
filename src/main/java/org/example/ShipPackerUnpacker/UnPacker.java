package org.example.ShipPackerUnpacker;

import org.example.ShipPackerUnpacker.ShipPacker;
import org.example.ShipPackerUnpacker.ShipUnPacker;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UnPacker
{
    public void UnPack(JButton bobj2)
    {
        bobj2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {


                    String PackName = JOptionPane.showInputDialog("Enter the name of the file you want to create for packing:");

                    if (PackName != null)
                    {
                        ShipUnPacker mobj1 = new ShipUnPacker(PackName);
                        mobj1.UnpackingActivity();
                        JOptionPane.showMessageDialog(null, "unpacking completed!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error unpacking file");
                    }
                }
                catch (Exception eobj)
                {
                    eobj.printStackTrace();  // Show error in console for debugging
                    JOptionPane.showMessageDialog(null, "Error: " + eobj.getMessage());
                }
            }
        });
    }//end of main

}
