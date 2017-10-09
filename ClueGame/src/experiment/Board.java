package experiment;
import clueGame.BoardCell;
import java.util.*;

public class Board {
	public static final int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;

	// finish adding functions


	// I added the skeleton for the functions but was unsure what each should accomplish 

	public void initialize() {

	}

	public void loadRoomConfig() { 

	}

	public void loadBoardConfig() {

	}

	public void calcAdjancies() { 
		// Should the repeat functions from IntBoard.java be copy and pasted or should we use inheritance? 
	}

	public void calcTargets(BoardCell cell, int pathlength) { 

	}

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

}
