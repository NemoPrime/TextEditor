import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.undo.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import java.io.*;

public class TextMain extends JFrame{
    
         JTextArea area = new JTextArea();
         final JFileChooser chooser = new JFileChooser();
         
        TextMain() {
                JFrame frame = new JFrame("Wim");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

                JMenuBar bar = new JMenuBar();
                
                
                
                frame.getContentPane().add(area);

                JMenu file = new JMenu("File");
                
                
                JMenuItem exit = new JMenuItem("Exit",null);
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                        }
                });
                JMenuItem save = new JMenuItem("Save");
                save.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                
                            
                            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                    "JPG & GIF Images", "jpg", "gif");
                                //chooser.setFileFilter(filter);
                                int returnVal = chooser.showSaveDialog(getParent());
                                if(returnVal == JFileChooser.APPROVE_OPTION) {
                                   System.out.println("You chose to open this file: " +
                                        chooser.getSelectedFile().getName());}
                            
                           String filename = chooser.getSelectedFile().getName();
                           
                            BufferedWriter out;
                            try {
                                out = new BufferedWriter(new FileWriter(filename));
                                area.write(out);
                            } catch (IOException e2) {
                                // TODO Auto-generated catch block
                                e2.printStackTrace();
                            }
                           
                           
                           
                           
                        }
                });


                final UndoManager undoer = new UndoManager();
                
                JMenuItem open = new JMenuItem("Open");
                open.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                
                            
                            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                    "JPG & GIF Images", "jpg", "gif");
                                //chooser.setFileFilter(filter);
                                int returnVal = chooser.showOpenDialog(getParent());
                                if(returnVal == JFileChooser.APPROVE_OPTION) {
                                   System.out.println("You chose to open this file: " +
                                        chooser.getSelectedFile().getName());}
                                
                                String filename = chooser.getSelectedFile().getName();
                                
                                
                                FileReader in;
                                try {
                                    in = new FileReader(filename);
                                    BufferedReader reader = new BufferedReader(in);
                                    area.read(reader, "jTextArea1");
                		area.getDocument().addUndoableEditListener(undoer);
                                } catch (IOException e2) {
                                    // TODO Auto-generated catch block
                                    e2.printStackTrace();
                                }
                        }
                });
                file.add(exit);
                file.add(save);
                file.add(open);
                
               


                JMenu edit = new JMenu("Edit");
                
                JMenuItem undo = new JMenuItem("Undo",null);
                undo.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if(undoer.canUndo()) {
                                    
                                        undoer.undo();
                                }
                        }
                });
                JMenuItem redo = new JMenuItem("Redo",null);
                redo.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if(undoer.canRedo()) {
                                        undoer.redo();
                                }
                        }
                });

                JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
                cut.setText("Cut");
                JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
                copy.setText("Copy");
                JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());
                paste.setText("Paste");
                edit.add(undo);
                edit.add(redo);
                edit.add(cut);
                edit.add(copy);
                edit.add(paste);

                JMenu search = new JMenu("Search");
		final JTextField sField = new JTextField();
		JMenuItem enter = new JMenuItem("Next");

		final Highlighter hilit = new DefaultHighlighter();
        	final Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
        	area.setHighlighter(hilit);
		
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilit.removeAllHighlights();
				String s = sField.getText();
				if(s.length()<=0) {
					
				} else {
					String content = area.getText();
					int index = content.indexOf(s,0);
					if(index >=0) {
					try {
					int end = index + s.length();
					hilit.addHighlight(index, end, painter);
					area.setCaretPosition(end);
					//entry.setBackground(entryBg);
					//message("'" + s + "' found. Press ESC to end search");
				    } catch (BadLocationException b) {
					b.printStackTrace();
				    }
				}
		/*		
				if(!sField.getText().isEmpty()){
					String searchWord = sField.getText();
					String docText = area.getText();
					if(docText.contains(searchWord)) {
						int n = JOptionPane.showOptionDialog(null, "Click OK to continue", "'" + searchWord + "' was contained",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);	
						return n;
					}
		*/		}
			}
		});
		search.add(sField);
		search.add(enter);

                bar.add(file);
                bar.add(edit);
                bar.add(search);
                
                //frame.getContentPane().add(bar);

                area.getDocument().addUndoableEditListener(undoer);

                frame.setJMenuBar(bar);
                
                frame.setSize(600,400);

                frame.setVisible(true);
        }
       public static void main(String args[]){
            TextMain prog = new TextMain();
        }
}
