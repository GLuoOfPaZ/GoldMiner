package minegame;

import minegame.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author AliasQli
 * 2023/4/9
 */
public class HookTest {
    @Test
    public void testHookLaunch() throws IOException {
        Hook hook = new Hook(0, 0);
        assertEquals(hook.state, Hook.HookState.WAIT);
        hook.launch();
        assertEquals(hook.state, Hook.HookState.FORWARD);
    }

    @Test
    public void testHookLaunchTwice() throws IOException {
        Hook hook = new Hook(0, 0);
        hook.launch();
        hook.launch();
        assertEquals(hook.state, Hook.HookState.FORWARD);
    }

    @Test
    public void testHookMineral() throws IOException {
        Hook hook = new Hook(0, 0);
        hook.launch();
        Mineral mineral = new Gold(0, 0, 10, 500);
        assertTrue(hook.hookMineral(mineral));
        assertEquals(hook.mineral, mineral);
    }

    @Test
    public void testHookBomb() throws IOException {
        Hook hook = new Hook(0, 0);
        hook.launch();
        Bomb bomb = new Bomb(0, 0, 10, new Stage());
        assertTrue(hook.explodeBomb(bomb));
    }

    @Test
    public void testScoreAdd() throws IOException {
        Hook hook = new Hook(0, 0);
        hook.launch();
        Mineral mineral = new Gold(0, 0, 10, 500);
        hook.hookMineral(mineral);
        Stage stage = new Stage();
        hook.refresh(stage);
        assertEquals(stage.score, 500);
    }
}
