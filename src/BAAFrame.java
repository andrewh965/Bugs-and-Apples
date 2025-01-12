/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: BAAFrame centers window and creates a frame that adds
a BAAPanel object.
*****************************************************************************/
import javax.swing.*;
import java.awt.*;

public class BAAFrame extends JFrame {
	String player = "";

	/***************************************************************************************
	 * Function to center the frame
	 ************************************************************************************/
	private void centerWindow(Window w) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setLocation((d.width - w.getWidth()) / 2, (d.height - w.getHeight()) / 2);
	}

	/***************************************************************************************
	 * creates the main frame for the program to run in.
	 ************************************************************************************/

	public BAAFrame() {

		setTitle("Bugs And Apples");
		setSize(820, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new BAAPanel();

		this.add(panel);
		centerWindow(this);

	}
}
