import java.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Wim");
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JTextArea area = new JTextArea();
		frame.getContentPane().add(area);
	}
}
