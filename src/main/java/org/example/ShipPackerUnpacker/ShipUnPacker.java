package org.example.ShipPackerUnpacker;

import javax.swing.*;
import java.io.*;

public class ShipUnPacker {
    private String PackName;

    public ShipUnPacker(String a) {
        this.PackName = a;
    }

    public void UnpackingActivity() {
        try {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("----------------------------Marvellous Packer Unpacker---------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("--------------------------------Unpacking Activity-------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");

            // Ask user where to extract
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choose folder to extract files to");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showSaveDialog(null);

            if (result != JFileChooser.APPROVE_OPTION) {
                System.out.println("User cancelled extraction.");
                return;
            }

            File saveDirectory = chooser.getSelectedFile();

            String Header;
            File fobjnew;
            int FileSize, iRet, iCountFile = 0;

            File fobj = new File(PackName);
            if (!fobj.exists()) {
                System.out.println("Unable to access packed file");
                return;
            }

            System.out.println("Packed file opened successfully");
            FileInputStream fiobj = new FileInputStream(fobj);

            byte[] HeaderBuffer = new byte[100];

            while ((iRet = fiobj.read(HeaderBuffer, 0, 100)) != -1) {
                Header = new String(HeaderBuffer).trim();

                String[] Tokens = Header.split(" ");

                // Construct full path for extracted file
                fobjnew = new File(saveDirectory, Tokens[0]);
                fobjnew.getParentFile().mkdirs(); // ensure parent directories exist
                fobjnew.createNewFile();

                FileSize = Integer.parseInt(Tokens[1]);

                byte[] Buffer = new byte[FileSize];
                FileOutputStream foobj = new FileOutputStream(fobjnew);
                fiobj.read(Buffer, 0, FileSize);
                foobj.write(Buffer, 0, FileSize);
                foobj.close();

                System.out.println("File unpacked: " + fobjnew.getAbsolutePath() + " (Size: " + Tokens[1] + ")");
                iCountFile++;
            }

            fiobj.close();

            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("-----------------------------------Statistical report----------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Total number of files unpacked: " + iCountFile);
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("------------------------Thank you for using our application----------------------------");
            System.out.println("---------------------------------------------------------------------------------------");

            JOptionPane.showMessageDialog(null, "Unpacking completed!\nFiles saved to:\n" + saveDirectory.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during unpacking: " + e.getMessage());
        }
    }
}
