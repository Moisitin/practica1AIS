package es.codeurjc.ais.tictactoe;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

class TicTacToeGameTest {
	private TicTacToeGame game;
	private Connection c1,c2;
	private Player p1,p2;
	
	@BeforeEach
	public void setUp() {
		game = new TicTacToeGame();
		c1 = mock(Connection.class);
		c2 = mock(Connection.class);
		p1 = new Player(1,"X","Jorge");
		p2= new Player(2,"O","Moises");
		game.addConnection(c1);
		game.addConnection(c2);
		game.addPlayer(p1);
		reset(c1);
		reset(c2);
		game.addPlayer(p2);
		
	}
	@Test
	void player1Wins() {
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		
		
		game.mark(2);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(0);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(4);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(8);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(6);
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
				verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
				WinnerValue event = (WinnerValue) argument.getValue();
				
				
		verify(c2).sendEvent(eq(EventType.GAME_OVER), eq(event));
		
		int[] expect= {2,4,6};
		assertThat(event.player.equals(p1));
		assertThat(event.pos.equals(expect));
		
		assertThat(!event.player.equals(p2));
		
		assertThat(!game.checkDraw());
		
		assertNotNull(event);
		
		
		
	}
	@Test
	void player2Wins() {
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		
		
		game.mark(0);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(1);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(2);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(4);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(6);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(7);
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
				verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
				WinnerValue event = (WinnerValue) argument.getValue();
				
				
		verify(c1).sendEvent(eq(EventType.GAME_OVER), eq(event));
		
		int[] expect= {1,4,7};
		assertThat(event.player.equals(p2));
		assertThat(event.pos.equals(expect));
		
		assertThat(!event.player.equals(p1));
		
		assertThat(!game.checkDraw());
		
		assertNotNull(event);
	}
	@Test
	void drawTest() {
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		
		
		game.mark(0);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(1);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(2);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(4);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(6);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(8);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(5);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		game.mark(3);
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		game.mark(7);
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = (WinnerValue) argument.getValue();
		
		assertNotNull(game.checkDraw());
		assertNull(event);
		
	}

}