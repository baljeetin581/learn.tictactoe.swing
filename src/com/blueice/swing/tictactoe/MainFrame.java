package com.blueice.swing.tictactoe;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

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
				btnMatrix[i][j] = new JButton();

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				btnMatrix[i][j].setFont(btnMatrix[i][j].getFont().deriveFont(220.0f));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				btnMatrix[i][j].setFocusable(false);

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				btnMatrix[i][j].addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {

		((JButton) e.getSource()).setText("X");

		Move bestMove = findBestMove();

		if (bestMove.val == 10)
			new Dialog(this, "Computer wins!!", true).setVisible(true);
		else if (bestMove.val == -10)
			new Dialog(this, "You win!", true).setVisible(true);
		else
			btnMatrix[bestMove.row][bestMove.col].setText("O");
	}

	private class Move {

		int val, row, col;

		public Move() {

			val = -1000;
			row = -1;
			col = -1;
		}
	}

	private Move findBestMove() {

		Move bestMove = new Move();

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (btnMatrix[i][j].getText().isEmpty()) {

					btnMatrix[i][j].setText("O");

					int moveVal = minimax(0, true);

					btnMatrix[i][j].setText("");

					if (moveVal > bestMove.val) {
						bestMove.row = i;
						bestMove.col = j;
						bestMove.val = moveVal;
					}
				}

		return bestMove;
	}

	private int minimax(int depth, boolean isMax) {

		int score = evaluate();

		if (score == 10 || score == -10)
			return score;

		if (!isMovesLeft())
			return 0;

		if (isMax) {

			int best = -1000;

			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (btnMatrix[i][j].getText().isEmpty()) {

						btnMatrix[i][j].setText("O");

						best = Math.max(best, minimax(depth + 1, !isMax));

						btnMatrix[i][j].setText("");
					}

			return best;
		} else {

			int best = 1000;

			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (btnMatrix[i][j].getText().isEmpty()) {

						btnMatrix[i][j].setText("X");

						best = Math.min(best, minimax(depth + 1, isMax));

						btnMatrix[i][j].setText("");
					}

			return best;
		}
	}

	private int evaluate() {

		// Checking for Rows for X or O victory.
		for (int row = 0; row < 3; row++) {
			if (btnMatrix[row][0].getText().equals("X") && btnMatrix[row][1].getText().equals("X")
					&& btnMatrix[row][2].getText().equals("X"))
				return +10;
			else if (btnMatrix[row][0].getText().equals("O") && btnMatrix[row][1].getText().equals("O")
					&& btnMatrix[row][2].getText().equals("O"))
				return -10;
		}

		// Checking for Columns for X or O victory.
		for (int col = 0; col < 3; col++) {
			if (btnMatrix[0][col].getText().equals("X") && btnMatrix[1][col].getText().equals("X")
					&& btnMatrix[2][col].getText().equals("X"))
				return +10;
			else if (btnMatrix[0][col].getText().equals("O") && btnMatrix[1][col].getText().equals("O")
					&& btnMatrix[2][col].getText().equals("O"))
				return -10;
		}

		// Checking for Diagonals for X or O victory.
		if (btnMatrix[0][0].getText().equals("X") && btnMatrix[1][1].getText().equals("X")
				&& btnMatrix[2][2].getText().equals("X"))
			return +10;
		else if (btnMatrix[0][0].getText().equals("O") && btnMatrix[1][1].getText().equals("O")
				&& btnMatrix[2][2].getText().equals("O"))
			return -10;

		if (btnMatrix[0][2].getText().equals("X") && btnMatrix[1][1].getText().equals("X")
				&& btnMatrix[2][0].getText().equals("X"))
			return +10;
		else if (btnMatrix[0][2].getText().equals("O") && btnMatrix[1][1].getText().equals("O")
				&& btnMatrix[2][0].getText().equals("O"))
			return -10;

		// Else if none of them have won then return 0
		return 0;
	}

	private boolean isMovesLeft() {

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (btnMatrix[i][j].getText().isEmpty())
					return true;

		return false;
	}
}
