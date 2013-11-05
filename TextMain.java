import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.undo.*;
import javax.swing.text.*;

public class TextMain {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Wim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JMenuBar bar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit",null);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exit);

		final UndoManager undoer = new UndoManager();

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

		bar.add(file);
		bar.add(edit);
		bar.add(search);
		
		//frame.getContentPane().add(bar);

		JTextArea area = new JTextArea();
		area.getDocument().addUndoableEditListener(undoer);
		frame.getContentPane().add(area);

		frame.setJMenuBar(bar);
		
		frame.setSize(600,400);

		frame.setVisible(true);
	}
}
