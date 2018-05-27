package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	private Board board;
		@Before
		public void setUp() {
			board=new Board();
		}
	
		@Test
		public void firstPlayerWinsTest() {
			
			board.getCell(0).value="x";
			board.getCell(1).value="o";
			board.getCell(4).value="x";
			board.getCell(7).value="o";
			board.getCell(8).value="x";
			int[] expect= {0,4,8};
			int[] resultX=board.getCellsIfWinner("x");
			int[] resultO=board.getCellsIfWinner("o");
			boolean resultDraw=board.checkDraw();
			assertArrayEquals(expect,resultX);
			assertNull(resultO);
			assertFalse(resultDraw);
		}
		@Test
		public void secondPlayerWinsTest() {
			
			board.getCell(0).value="x";
			board.getCell(2).value="o";
			board.getCell(4).value="x";
			board.getCell(5).value="o";
			board.getCell(3).value="x";
			board.getCell(8).value="o";
			
			int[] resultX=board.getCellsIfWinner("x");
			int[] expect2= {2,5,8};
			int[] resultO=board.getCellsIfWinner("o");
			boolean resultDraw=board.checkDraw();
			assertNull(resultX);
			assertArrayEquals(expect2,resultO);	
			assertFalse(resultDraw);
		}
		@Test
		public void drawTest() {
			
			board.getCell(0).value="x";
			board.getCell(1).value="o";
			board.getCell(4).value="x";
			board.getCell(8).value="o";
			board.getCell(2).value="x";
			board.getCell(6).value="o";
			board.getCell(7).value="x";
			board.getCell(3).value="o";
			board.getCell(5).value="x";
			
			int[] result=board.getCellsIfWinner("x");
			int[] result2=board.getCellsIfWinner("o");
			boolean resultDraw=board.checkDraw();
			assertNull(result);
			assertNull(result2);
			assertTrue(resultDraw);
		}
}