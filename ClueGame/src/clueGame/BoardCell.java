/*
 * Author: Demonna Wade and Erica Manzer 
 */

/*
 * I made row, col, & initial private and then created getters and setters 
 * This created some problems in IntBoard since we had been accessing them directly. 
 * I went through and corrected by calling the getters and setters in IntBoard.
 * 
 * Mona 
 * 
 */
package clueGame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
public class BoardCell {
	// three member variables to represent the row, column, and initial 
	private int row, col;
	private char initial;
	private DoorDirection doorDir;
	private Point pixel;
	private Color color; 
	// 22 rows, 23 cols
	public final int MARGIN = 50;
	public final int SCALE = 32;
	private final int WIDTH = 30;
	private final int HEIGHT = 30;

	// default constructor
	public BoardCell() {
		this.row = 0;
		this.col = 0;
		this.initial = 'P';
		this.doorDir = DoorDirection.NONE;
		pixel = new Point( this.row, this.col);
		this.color = Color.BLACK;
	} 
	// constructor with parameters
	public BoardCell(int c, int r, char initial, DoorDirection doorDir) { 
		this.row = r; 
		this.col = c; 
		this.initial = initial;
		this.doorDir = doorDir;
		pixel = new Point (this.row * SCALE + MARGIN, this.col * SCALE + MARGIN);
		this.color = Color.BLACK;
	}

	public boolean isWalkway() { 
		if (initial == 'P') {
			return true; 
		}
		return false;
	}
	public boolean isRoom() { 
		if (initial != 'K' && initial != 'P') { 
			return true; 
		}
		return false;
	}
	public boolean isDoorway() {
		if (doorDir != DoorDirection.NONE) {  
			return true; 
		}
		return false;
	}

	public void draw ( Graphics g )
	{
		// TODO implement
		// set color 
		if (this.initial == 'P') 
		{
			this.color = Color.GRAY;
			g.setColor(this.color);
			g.fillRect(this.pixel.x , this.pixel.y, WIDTH, HEIGHT);
		}
		if (this.isRoom()) 
		{
			if ( isDoorway())
			{
				if (doorDir == DoorDirection.UP)
				{
					this.color = Color.WHITE;
					g.setColor(this.color);
					g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.YELLOW);
					g.fillRect(this.pixel.x, this.pixel.y, WIDTH, 5);
				}
				else if (doorDir == DoorDirection.DOWN)
				{
					this.color = Color.WHITE;
					g.setColor(this.color);
					g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.YELLOW);
					g.fillRect(this.pixel.x, this.pixel.y + 25, WIDTH, 5);
				}
				else if (doorDir == DoorDirection.LEFT)
				{
					this.color = Color.WHITE;
					g.setColor(this.color);
					g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.YELLOW);
					g.fillRect(this.pixel.x, this.pixel.y, 5,HEIGHT);
				}
				else if (doorDir == DoorDirection.RIGHT)
				{
					this.color = Color.WHITE;
					g.setColor(this.color);
					g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
					g.setColor(Color.YELLOW);
					g.fillRect(this.pixel.x + 25, this.pixel.y, 5,HEIGHT);
				}

			}
			else
			{
				this.color = Color.WHITE;
				g.setColor(this.color);
				g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
			}


		}
		if (this.initial == 'K') 
		{
			this.color = Color.GREEN;
			g.setColor(this.color);
			g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
		}


	}

	public void drawTargets( Graphics g)
	{
		this.color = Color.CYAN;
		g.setColor(this.color);
		g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
	}

	public void reDrawTargets ( Graphics g)
	{
		if ( isDoorway())
		{		
			this.color = Color.WHITE;
			g.setColor(this.color);
			g.fillRect(this.pixel.x, this.pixel.y, WIDTH, HEIGHT);
		}
		if (this.initial == 'P') 
		{
			this.color = Color.GRAY;
			g.setColor(this.color);
			g.fillRect(this.pixel.x , this.pixel.y, WIDTH, HEIGHT);
		}
	}


// Getters {
public int getRow() 
{
	return this.row;
}

public void setRow(int row) 
{
	this.row = row;
}

public int getCol() 
{
	return this.col;
}

public void setCol(int col) 
{
	this.col = col;
}

public char getInitial() 
{
	return this.initial;
}

public void setInitial(char initial) 
{
	this.initial = initial;
}

public DoorDirection getDoorDirection()
{
	return doorDir;
}

// NOTE: Keep this because I think this is how it is supposed to be done!
public boolean containsClick(int mouseX, int mouseY)
{
	Rectangle rect = new Rectangle(pixel.x, pixel.y, WIDTH, HEIGHT);
	if (rect.contains(new Point(mouseX, mouseY)))
	{
		System.out.println("Click was found to be in a cell: BoardCell class");
		return true;
	}
	else
	{
		return false;
	}
}
}
