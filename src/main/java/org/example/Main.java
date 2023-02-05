package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.print.PrinterException;
import java.io.*;

class notepad extends JFrame implements ActionListener{
    JTextArea t;
    JFrame f;
    
    notepad() {
        //initialising the frame and the textArea

        f = new JFrame("notepad");
        t = new JTextArea();


        //initialising menuBar and all Individual Items
        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu();

        JMenuItem f1 = new JMenuItem("new");
        JMenuItem f2 = new JMenuItem("open");
        JMenuItem f3 = new JMenuItem("save");
        JMenuItem f4 = new JMenuItem("print");

        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

    //creating the edit Menu
        JMenu edit = new JMenu("edit");

        JMenuItem f5 = new JMenuItem("cut");
        JMenuItem f6 = new JMenuItem("copy");
        JMenuItem f7 = new JMenuItem("paste");


        f5.addActionListener(this);
        f6.addActionListener(this);
        f7.addActionListener(this);


        edit.add(f5);
        edit.add(f6);
        edit.add(f7);

        JMenu close = new JMenu("close");
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(1000,1000);
        f.show();



    }
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        switch(s){
            case "new":
                t.setText("");
                break;
            case "open":
                //creating objewct of jfilechooser and initializing it ot a location in the computer
                JFileChooser j = new JFileChooser("D:");

                //intialising the openDialoagbox
                int r =  j.showOpenDialog(null);

                //if the user seleects a file
                if(r == JFileChooser.APPROVE_OPTION){
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    String s1, s2;

                    FileReader fr = null;
                    try {
                        fr = new FileReader(fi);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    BufferedReader br = new BufferedReader(fr);
                    try {
                        s1= br.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    while(true){
                        try {
                            if (!((s2 = br.readLine())!= null)) break;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        s1 = s1+"\n"+s2;

                    }
                    t.setText(s1);
                }
                else{
                    JOptionPane.showMessageDialog(f,"operation cancelled");
                }


                break;
            case "save":

                //creating objewct of jfilechooser and initializing it ot a location in the computer
                JFileChooser jii = new JFileChooser("D:");

                //intialising the openDialoagbox
                int rii =  jii.showSaveDialog(null);

                //if the user seleects a file
                if(rii == JFileChooser.APPROVE_OPTION){
                    File fi = new File(jii.getSelectedFile().getAbsolutePath());



                    FileWriter fr = null;
                    try {
                        fr = new FileWriter(fi);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    BufferedWriter br = new BufferedWriter(fr);

                    try {
                        br.write(t.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        br.flush();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        br.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f,"operation cancelled");
                }

                break;
            case "print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "cut":
                t.cut();
                break;
            case "copy":
                t.copy();
                break;
            case "paste":
                t.paste();
                break;
            case "close":
                f.setVisible(false);
                break;
        }

    }
}
public class Main {

    public static void main(String args[]) {
        notepad note = new notepad();
    }
}