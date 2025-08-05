package org.example.ShipPackerUnpacker;
import java.util.*;
import java.io.*;

public class ShipPacker
{
    private String PackName;
    private String DirName;

    public ShipPacker(String a, String b)
    {
        this.PackName = a;
        this.DirName = b;
    }
    public void PackingActivity()
    {
        try
        {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("----------------------------Marvellous Packer Unpacker---------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("---------------------------------Packing Activity--------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");

            int i = 0, j = 0, iRet = 0, iCountFile = 0;


            File fobj = new File(DirName);

            //check the exixtsnce of the directory
            if(fobj.exists() && fobj.isDirectory())
            {
                System.out.println(DirName+" is successfully opened");

                File Packobj = new File(PackName);

                //create a packed fle
                boolean bRet = Packobj.createNewFile();

                if(bRet == false)
                {
                    System.out.println("Unable to create Pack File");
                    return;
                }
                System.out.println("Packed file gets successfully created with name:"+PackName);

                //retrieve all files from directory
                File Arr[] = fobj.listFiles();

                //packed file object
                FileOutputStream foobj = new FileOutputStream(Packobj);


                //buffer for read and write activity
                byte Buffer[] = new byte[1024];


                String header = null;

                //Directory traversal
                for(i = 0; i < Arr.length; i++)
                {
                    header = Arr[i].getName() + " " + Arr[i].length();

                    //loop to form 100 bytes header
                    for(j = header.length(); j < 100 ; j++)
                    {
                        header = header + " ";
                    }
                    //write header into packed file
                    foobj.write(header.getBytes());


                    //open file from directory for reading
                    FileInputStream fiobj = new FileInputStream(Arr[i]);


                    //write contents of file into packed file
                    while((iRet = fiobj.read(Buffer)) != -1)
                    {
                        foobj.write(Buffer,0,iRet);
                        System.out.println("file size read is : "+iRet);
                        System.out.println("File name scanned is "+Arr[i].getName());
                    }
                    fiobj.close();
                    iCountFile++;

                }
                System.out.println("Packing Activity Done");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("-----------------------------------Statistical report----------------------------------");
                System.out.println("---------------------------------------------------------------------------------------");

                //to be added
                System.out.println("Total files packed:"+iCountFile);

                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("------------------------Thank you for using our application----------------------------");
                System.out.println("---------------------------------------------------------------------------------------");


            }
            else
            {
                System.out.println("There is no such Directory");
            }
        }//end of try
        catch(Exception eobj)
        {

        }


    }//end of PackingActivity function

}
