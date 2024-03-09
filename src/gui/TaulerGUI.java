package gui;

import joc.*;
import peces.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TaulerGUI {

    private static final int BOARD_SIZE = 8;
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 600;

    private JFrame taulerGUI;
    private JPanel panel;
    private EscacGUI[][] escacs;
    private Posicio posSelect;
    private Tauler board;
    private MyColor turn;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TaulerGUI window = new TaulerGUI();
                window.taulerGUI.setVisible(true);
            } catch (Exception e) {
                handleException(e);
            }
        });
    }

    public TaulerGUI() {
        initialize();
    }

    private void initialize() {
        taulerGUI = new JFrame();
        turn = MyColor.BLANC;
        taulerGUI.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        taulerGUI.setResizable(false);
        taulerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        taulerGUI.setTitle("Basic Chess Game");

        escacs = new EscacGUI[BOARD_SIZE][BOARD_SIZE];

        createBoard();
    }

    private void createBoard() {
        panel = new JPanel();
        taulerGUI.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 0, 0));

        board = new Tauler();
        board.crearPartida();

        initializeSquares();

        taulerGUI.add(panel);
    }

    private void initializeSquares() {
        for (int j = BOARD_SIZE - 1; j >= 0; j--) {
            for (int i = 0; i < BOARD_SIZE; i++) {
            	initializeSquare(i, j);
            }
        }
    }

    private void initializeSquare(int i, int j) {
        Posicio pos = new Posicio(i, j);
        Peca piece = board.getPeca(pos);
        EscacGUI escac = new EscacGUI(((i + j) % 2 == 0) ? MyColor.NEGRE : MyColor.BLANC, piece, this);
        escacs[i][j] = escac;
        panel.add(escac);
    }

    public void selectedSquare(int x, int y) {
        try {
            if (posSelect == null) {
            	handleSelection(x, y);
            } else {
            	handleMovement(x, y);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void handleSelection(int x, int y) {
        Peca piece = board.getPeca(new Posicio(x, y));
        if (piece != null) {
            try {
            	validateSelectedPiece(piece);
                posSelect = new Posicio(x, y);
                List<Posicio> movimientosPosibles = piece.movimentsPosibles(board);
                highlightMovements(movimientosPosibles);
            } catch (InvalidMovementException | WrongTurnException e) {
                handleException(e);
            }
        } else {
            handleException(new InvalidMovementException("A001", "No item selected"));
        }
    }

    private void handleMovement(int x, int y) {
        Peca piece = board.getPeca(posSelect);
        try {
        	validateSelectedPiece(piece);
        	validateMovement(piece, x, y);
            makeMovement(x, y);
        } catch (InvalidMovementException | WrongTurnException e) {
            handleException(e);
        }
    }

    private void validateSelectedPiece(Peca pieza) throws InvalidMovementException, WrongTurnException {
        if (pieza.getEquip() != turn) {
            throw new WrongTurnException("Not your turn.");
        }
        if (!pieza.hihaMovimentsPosibles(board)) {
            throw new InvalidMovementException("A002", "Invalid Movement!");
        }
    }

    private void validateMovement(Peca pieza, int x, int y) throws InvalidMovementException {
        if (!pieza.movimentPosible(board, new Posicio(x, y))) {
            throw new InvalidMovementException("A002", "Invalid Movement!");
        }
    }

    private void makeMovement(int x, int y) throws InvalidMovementException {
        turn = (turn == MyColor.BLANC) ? MyColor.NEGRE : MyColor.BLANC;
        board.mouPeca(posSelect, new Posicio(x, y));
        posSelect = null;
        updateBoard();
    }

    private void highlightMovements(List<Posicio> movimientosPosibles) {
        for (Posicio posicio : movimientosPosibles) {
        	highlightSquare(posicio.getX(), posicio.getY());
        }
    }

    private void highlightSquare(int x, int y) {
        escacs[x][y].setBackground(new Color(255, 153, 153));
        escacs[x][y].setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    private void updateBoard() {
        for (int j = BOARD_SIZE - 1; j >= 0; j--) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                Posicio pos = new Posicio(i, j);
                Peca pieza = board.getPeca(pos);
                EscacGUI escac = escacs[i][j];
                escac.removeResult();
                escac.removeImage();
                escac.addImage();
                escac.setPiece(pieza);
            }
        }
    }

    private static void handleException(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
