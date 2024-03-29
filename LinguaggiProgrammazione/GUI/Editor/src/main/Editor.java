package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class Editor extends JFrame{
    
    private final JTextArea text = new JTextArea();
    Font f = new Font("monospace", Font.PLAIN, 12);

    private final JMenuBar menu = new JMenuBar();
    //----
    private final JMenu menuFile = new JMenu("file");
    private final JMenu menuEdit = new JMenu("edit");
    private final JMenu menuSearch = new JMenu("search");
    //----
    private final JMenuItem save = new JMenuItem("save file");
    private final JMenuItem open = new JMenuItem("open file");
    private final JMenuItem nuovo = new JMenuItem("new file");
    //----
    private final JMenuItem dark = new JMenuItem("dark");
    private final JMenuItem light = new JMenuItem("light");
    private final JMenuItem sepia = new JMenuItem("sepia");

    private final JFileChooser fileChooser = new JFileChooser();
    private File current = null;


    JPanel panel = new JPanel();



    public Editor(){
        super("Editor");

        text.setFont(new Font("SansSerif", Font.PLAIN, 14));

        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setBackground(Color.WHITE);
       
        JScrollPane scroll = new JScrollPane(text);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scroll,BorderLayout.CENTER);

        getContentPane().add(panel,BorderLayout.CENTER);

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));


        menuFile.setFont(f);
        menuEdit.setFont(f);
        menuSearch.setFont(f);
        nuovo.setFont(f);
        save.setFont(f);
        open.setFont(f);
        dark.setFont(f);
        light.setFont(f);
        sepia.setFont(f);

        open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                int result = fileChooser.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    openFile(fileChooser.getSelectedFile());
                }
            }
        });

        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                    saveFile();
            }
        });

        nuovo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                    newFile();
            }
        });

        light.addActionListener(e -> setLight());
        dark.addActionListener(e -> setDark());
        sepia.addActionListener(e -> setSepia());

        menuFile.add(nuovo);
        menuFile.add(save);
        menuFile.add(open);

        menuEdit.add(light);
        menuEdit.add(dark);
        menuEdit.add(sepia);

        menu.add(menuFile);
        menu.add(menuEdit);
        menu.add(menuSearch);
        this.setJMenuBar(menu);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JTextArea getTextArea(){
        return this.text;
    }

    private void openFile(File file){
        try{
            StringBuilder textfile = new StringBuilder();
            BufferedReader in = new BufferedReader(new FileReader(file));

            this.current = file; // così che possa salvare
            this.setTitle(file.getAbsolutePath()+" (*)");

            String line;
            while((line = in.readLine()) != null)
                textfile.append(line+"\n");
            this.text.setText(textfile.toString());
            in.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(new JFrame(), "Il file selezionato non esiste", "Dialog",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile(){
        if(this.current != null){
            try{
                PrintWriter out = new PrintWriter(new FileWriter(this.current));
                out.print(this.text.getText());
                out.close();

                this.setTitle(this.current.getAbsolutePath());

            }
            catch(IOException e){
                JOptionPane.showMessageDialog(new JFrame(), "Errore salvataggio File", "Dialog",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            //int result = fileChooser.showOpenDialog(null);
            int result = fileChooser.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                this.current = fileChooser.getSelectedFile();
                saveFile();
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Errore salvataggio File", "Dialog",JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void newFile(){
        if(this.current == null){
           text.setText(""); 
           this.setTitle("Editor");
        }
        else{
            String message = "Salvare le modifiche per"+this.current.getAbsolutePath()+" ?";
            int result = JOptionPane.showConfirmDialog((Component) null,message,"Alert", JOptionPane.YES_NO_CANCEL_OPTION);
            if(result == 0){
                saveFile();
                this.current = null;
                newFile();
            }
            else if(result == 1){
                this.current = null;
                newFile();
            }
        }
    }

    private void setLight(){
       text.setBackground(Color.WHITE); 
       text.setForeground(Color.BLACK);
       panel.setBackground(Color.WHITE);
    }

    private void setDark(){
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        panel.setBackground(Color.BLACK);
    }

    private void setSepia(){
        Color c = new Color(236,227,161);
        text.setBackground(c);
        text.setForeground(Color.darkGray);
        panel.setBackground(c);
    }
}
