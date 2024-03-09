package gui;

import joc.*;
import peces.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EscacGUI extends JLabel implements MouseListener {

    private static final Color LIGHT_COLOR = new Color(240, 240, 240);
    private static final Color DARK_COLOR = new Color(160, 160, 160);

    private Peca piece;
    private Point piecePosition;
    private TaulerGUI board;
    private MyColor color;

    public EscacGUI(MyColor color, Peca piece, TaulerGUI board) {
        this.piece = piece;
        this.board = board;
        this.piecePosition = new Point();
        this.color = color;
        initialize(color);
        addMouseListener(this);
        updateView();
    }

    private void initialize(MyColor color) {
        setOpaque(true);
        setBackground((color == MyColor.NEGRE) ? DARK_COLOR : LIGHT_COLOR);
    }

    private void updateView() {
    	removeImage();
        addImage();
    }

    public void addImage() {
        if (piece != null) {
            String name = piece.getClass().getSimpleName().toLowerCase();
            String team = piece.toString().substring(1);
            String result = name + "_" + team + ".png";
            setIcon(new ImageIcon("img/" + result));
        }
    }

    public void removeImage() {
        setIcon(null);
    }

    public void removeResult() {
        setBackground((color == MyColor.BLANC) ? LIGHT_COLOR : DARK_COLOR);
        setBorder(null);
    }

    public void selectedSquare() {
        int squareSize = getWidth();
        piecePosition.x = getX() / squareSize;
        piecePosition.y = 7 - getY() / squareSize;
        board.selectedSquare(piecePosition.x, piecePosition.y);
    }

    public void setPiece(Peca piece) {
        this.piece = piece;
        updateView();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	selectedSquare();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
