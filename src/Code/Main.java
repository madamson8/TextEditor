package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    /*
     * Created by Matthew Adamson as an attempt to improve his coding skills!
     * Started Dec. 28th! Almost Happy new years!
     * TODO::01/01/2018
     * TODO::Priority - Low
     * change the file opener to a separate method so it can be called on any frame,
     * ? remove jtextarea from panel and add new one to panel
     *
     * right now it is only on the initial frame.
     * TODO::Priority - High
     * This is crazy not thread safe, at the end of debugging I had 2 instances open.
     * If the user exits, CLOSE THE PROGRAM
     * TODO::END
     */


    public static void main(String[] args) {
        Main main = new Main();

        main.createFrame();

    }

    private void createFrame() {

        int columnSize = 60;
        int rowSize = 60;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Matthew's text editor");
        JPanel panel = new JPanel();

        JTextArea textArea = new JTextArea();
        textArea.setRows(rowSize);
        textArea.setColumns(columnSize);
        if(textArea.getWidth() > 80) {
            textArea.setColumns(80);
        }
//        textArea.setSize(screenSize.width,screenSize.height);

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

//        textArea.setLineWrap(true);
//        panel.add(textArea);
        panel.add(scroll);


        textArea.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
//                int key = e.getKeyCode();
//                switch (key) {
//                    case 17:
//                        //Control is pressed
//                        isCtrlPressed = true;
//                        System.out.println("Ctrl is now pressed");
//                        break;
//                    case 79:
//                        isOPressed = true;
//                        System.out.println("O is pressed");
//                        break;
//                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("Pressed " + e.getKeyCode());
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                    JFileChooser chooser = new JFileChooser();
                    int returnVal = chooser.showOpenDialog(panel);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        save(chooser.getName());
                        File file = chooser.getSelectedFile();
                        try {
                            FileWriter fw = new FileWriter(file);
//                        fw.write(contents);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
                    JFileChooser chooser = new JFileChooser();
                    int returnVal = chooser.showOpenDialog(panel);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("You chose to open this file: " +
                                chooser.getSelectedFile().getPath());

//                        scroll.remove(textArea);
//                        scroll.add(readFile(chooser.getSelectedFile().getPath()));
//                        frame.setVisible(true);
                        JFrame testFrame = new JFrame(chooser.getSelectedFile().getName());
                        JPanel testPanel = new JPanel();
                        JTextArea cTextA = readFile(chooser.getSelectedFile().getPath());
                        cTextA.setColumns(columnSize);
                        cTextA.setRows(rowSize);

                        testPanel.add(cTextA);

                        testFrame.setSize(screenSize.width, screenSize.height);

                        testFrame.setLocationRelativeTo(null);
                        testFrame.setVisible(true);
                        testFrame.add(testPanel);
                        frame.setVisible(false);
                    }
                }
            }

        });

        textArea.setFocusable(true);
        textArea.requestFocusInWindow();
        frame.setSize(screenSize.width, screenSize.height);
//        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    private JTextArea readFile(String filePath) {
        JTextArea newTextArea = new JTextArea();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                newTextArea.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return newTextArea;
    }

    public void save(String path) {
        System.out.println(path);

    }

}
