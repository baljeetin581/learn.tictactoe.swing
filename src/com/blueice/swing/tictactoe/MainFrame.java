package com.blueice.swing.tictactoe;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JButton[][] btnMatrix = new JButton[3][3];

	public MainFrame() {

		initMatrix();

		initLayout();
		
		setTitle("Tic Tac Toe");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void initMatrix() {

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				btnMatrix[i][j] = new JButton(String.valueOf(i) + ", " + String.valueOf(j));
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				btnMatrix[i][j].setFont(btnMatrix[i][j].getFont().deriveFont(220.0f));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				btnMatrix[i][j].setFocusable(false);
	}

	private void initLayout() {

		GroupLayout layout = new GroupLayout(getContentPane());

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup().addComponent(btnMatrix[0][0], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[1][0], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[2][0], -1, -1, Integer.MAX_VALUE))
				.addGroup(layout.createParallelGroup().addComponent(btnMatrix[0][1], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[1][1], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[2][1], -1, -1, Integer.MAX_VALUE))
				.addGroup(layout.createParallelGroup().addComponent(btnMatrix[0][2], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[1][2], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[2][2], -1, -1, Integer.MAX_VALUE)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(btnMatrix[0][0], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[0][1], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[0][2], -1, -1, Integer.MAX_VALUE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(btnMatrix[1][0], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[1][1], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[1][2], -1, -1, Integer.MAX_VALUE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(btnMatrix[2][0], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[2][1], -1, -1, Integer.MAX_VALUE)
						.addComponent(btnMatrix[2][2], -1, -1, Integer.MAX_VALUE)));

		getContentPane().setLayout(layout);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				new MainFrame().setVisible(true);
			}
		});
	}
}
