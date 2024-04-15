import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Game game = new Game("Monopoly");
    @Before
    public void setUp() throws Exception {



        Main.games.add(game);
        game.loadPlayerData();


    }

    @Test
    public void updatePosition() {
        Player p = game.getPlayers().get(0);
        // given the player is in position 1,
        // when asked to moved 3 steps back,
        // then the player should be in position 38

        int actual =  p.updatePosition(-3);
        int expected = 38;
        assertEquals(expected, actual);

        // given the player is in position 38,
        // when asked to moved 10 steps forward,
        // then the player should be in position 7
        actual =  p.updatePosition(10);
        expected = 8;
        assertEquals(expected, actual);


    }


    @Test
    public void getName() {
        Player p = game.getPlayers().get(1);
        String expected = "jesper";
        String actual = p.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void buyProperty() {
        Player p = game.getPlayers().get(1);
        Field f = game.getBoard().getField(40);
        p.buyProperty(f);
    }

    @Test
    public void pay() {
    }

    @Test
    public void receive() {
    }

    @Test
    public void testPay() {
    }

    @Test
    public void collect() {
    }

    @Test
    public void startPassed() {




    }
}