package joc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

import peces.*;

public class Main {


	public static void main(String[] args) {
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Posa't en contacte amb l'administrador", "problema irresoluble",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void init() throws InvalidMovementException {
		MyColor torn;
		
		Tauler tauler = new Tauler();
		tauler.crearPartida();	
		
		tauler.imprimirTauler();
		
		System.out.println("");
		
		String line = "";
		String[] valors;
		Posicio posicioIniSeleccionada = null;
		Posicio posicioFiSeleccionada = null;
		
		do {
			torn = MyColor.BLANC;
			
			System.out.println("");
			System.out.println("Juguen BLANQUES");
			
			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
			
			do {
				System.out.println("Selecciona peça a moure (H,V):");
				try {
					line=buffer.readLine();
				
					valors = line.split(",");
					posicioIniSeleccionada = new Posicio(Integer.parseInt(valors[0]), Integer.parseInt(valors[1]));
						
					if (tauler.getPeca(posicioIniSeleccionada) == null) {
						throw new InvalidMovementException("A001", "No hi ha peça en aquest escac del tauler");
//						System.out.println("No hi ha peça en aquest escac del taulell"); 
//						posicioIniSeleccionada = null;
					} else {
						if (tauler.getPeca(posicioIniSeleccionada).getEquip() != torn) {
							throw new WrongTurnException();
//							System.out.println("No és el teu torn.... juguen BLANQUES");
//							posicioIniSeleccionada = null;
						} else {
							if (!tauler.getPeca(posicioIniSeleccionada).hihaMovimentsPosibles(tauler)) {
								throw new InvalidMovementException("A002", "Amb aquesta peça no hi ha moviments possibles, escull una altre" );
//								System.out.println("Amb aquesta peça no hi ha moviments possibles, escull una altre");
//								posicioIniSeleccionada = null;
							} else {
								System.out.println("La peça que vols moure és: " + tauler.getPeca(posicioIniSeleccionada).toString());
						
							}
						}
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (InvalidMovementException ime) {
					JOptionPane.showMessageDialog(null, ime.getMessage(), "Noooo.....", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (WrongTurnException wte) {
					JOptionPane.showMessageDialog(null,"No és el teu torn.... " , "Noooo.....", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (ArrayIndexOutOfBoundsException aiobe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)\nEls números han de ser de 0 a 7" , "Format incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)" , "Numero incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				}
			} while (posicioIniSeleccionada == null);
			
			
			do {
				System.out.println("A quina posició vols moure-la (H,V):");
				try {
					line=buffer.readLine();
				
					valors = line.split(",");
					posicioFiSeleccionada = new Posicio(Integer.parseInt(valors[0]), Integer.parseInt(valors[1]));
				
//					if (!tauler.getPeca(posicioIniSeleccionada).movimentsPosibles(tauler)[posicioFiSeleccionada.getX()][posicioFiSeleccionada.getY()] ) {
//						throw new InvalidMovementException("A003", "Posició incorrecte" );
////						System.out.println("posicio incorrecte");
////						posicioFiSeleccionada = null;
//					} else {
//						System.out.println("ok");
//					}
				
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException aiobe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)\nEls números han de ser de 0 a 7" , "Format incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)" , "Numero incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				}
			} while (posicioFiSeleccionada == null);
			
			
			tauler.mouPeca(posicioIniSeleccionada, posicioFiSeleccionada);
			
			tauler.imprimirTauler();
			
			/*************************************************************************************/
			System.out.println("");
			System.out.println("Juguen NEGRES");
			torn = MyColor.NEGRE;
			
			do {
				System.out.println("Selecciona peça a moure (H,V):");
				try {
					line=buffer.readLine();
				
					valors = line.split(",");
					posicioIniSeleccionada = new Posicio(Integer.parseInt(valors[0]), Integer.parseInt(valors[1]));
					
					if (tauler.getPeca(posicioIniSeleccionada) == null) {
						throw new InvalidMovementException("A004", "No hi ha peça en aquest escac del tauler");

//						System.out.println("No hi ha peça en aquest escac del taulell");
//						posicioIniSeleccionada = null;
					} else {
						if (tauler.getPeca(posicioIniSeleccionada).getEquip() != torn) {
							throw new WrongTurnException();
//							System.out.println("No és el teu torn.... juguen NEGRES");
//							posicioIniSeleccionada = null;
						} else {
							if (!tauler.getPeca(posicioIniSeleccionada).hihaMovimentsPosibles(tauler)) {
								throw new InvalidMovementException("A005", "Amb aquesta peça no hi ha moviments possibles, escull una altre" );
//								System.out.println("Amb aquesta peça no hi ha moviments possibles, escull una altre");
//								posicioIniSeleccionada = null;
							} else {
								System.out.println("La peça que vols moure és: " + tauler.getPeca(posicioIniSeleccionada).toString());
						
							}
						}
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (InvalidMovementException ime) {
					JOptionPane.showMessageDialog(null, ime.getMessage(), "Noooo.....", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (WrongTurnException wte) {
					JOptionPane.showMessageDialog(null,"No és el teu torn.... " , "Noooo.....", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (ArrayIndexOutOfBoundsException aiobe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)\nEls números han de ser de 0 a 7" , "Format incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)" , "Numero incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				}
			} while (posicioIniSeleccionada == null);
			
			
			do {
				System.out.println("A quina posició vols moure-la (H,V):");
				try {
					line=buffer.readLine();
				
					valors = line.split(",");
					posicioFiSeleccionada = new Posicio(Integer.parseInt(valors[0]), Integer.parseInt(valors[1]));
				
//					if (!tauler.getPeca(posicioIniSeleccionada).movimentsPosibles(tauler)[posicioFiSeleccionada.getX()][posicioFiSeleccionada.getY()] ) {
//						throw new InvalidMovementException("A006", "Posició incorrecte" );
//	//					System.out.println("posicio incorrecte");
//	//					posicioFiSeleccionada = null;
//					} else {
//						System.out.println("ok");
//					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException aiobe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)\nEls números han de ser de 0 a 7" , "Format incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,"El format ha de ser NumeroHoritzontal , NumeroVertical (p.e. 3,7)" , "Numero incorrecte", JOptionPane.ERROR_MESSAGE);
					posicioIniSeleccionada = null;
				}
			} while (posicioFiSeleccionada == null);
			
			
			tauler.mouPeca(posicioIniSeleccionada, posicioFiSeleccionada);
			
			tauler.imprimirTauler();
		
		} while (true);
	}
}
