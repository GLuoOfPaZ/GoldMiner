package jtest;

import minegame.*;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;

import static minegame.Hook.HookState.BACKWARD;
import static minegame.Hook.HookState.FORWARD;
import static minegame.Stage.StageState.GAME_OVER;
import static org.junit.Assert.*;

/**
 * @author zzxy
 * 2023-04-2023/4/5-17:17
 */
public class JunitTest {
    private Bomb bomb;
    private Stage stage;

    @Before
    public void setUp() throws Exception {
        // 初始化炸弹和场景
        stage = new Stage();
        stage.order = 1;
        Hook hook = new Hook(0, 0);
        stage.load(1);
        stage.hook.mineral= stage.mineralList.get(1);
        stage.hook = hook;
        bomb = new Bomb(100, 100, 10, stage);
        stage.bombList.add(bomb);
    }

    @Test
    public void testExplodeWithMineral() {
        // 测试有炸弹和钩子上有物品的情况
        bomb.explode();
        assertFalse(stage.hook.hasMineral());
        assertEquals(40000.0 / stage.hook.weight, stage.hook.getPullVelocity(),0.000001);
        assertEquals(0, stage.bombList.size());
    }

    @Test
    public void testExplodeWithoutMineralAndBACKWARD() {
        // 测试有炸弹和钩子上没有物品的情况，且钩子正在上升
        stage.hook.mineral= null;
        stage.hook.state = BACKWARD;
        bomb.explode();
        assertEquals(40000.0 / stage.hook.weight, stage.hook.getPullVelocity(),0.000001);
        assertEquals(0, stage.bombList.size());
    }

    @Test
    public void testExplodeWithNoBomb() {
        // 测试炸弹数为0的情况
        stage.bombList = null;
        bomb.explode();
        assertTrue(stage.hook.hasMineral());
    }

    @Test
    public void testExplodeWhileFORWARD() {
        // 测试钩子正在下降的情况
        stage.hook.state = FORWARD;
        bomb.explode();
        assertEquals(1, stage.bombList.size());
    }

    @Test
    public void testHookNull() {
        // 测试空钩速度
        stage.hook.mineral= null;
        assertEquals(40000.0 / stage.hook.weight, stage.hook.getPullVelocity(),0.000001);
    }
    @Test
    public void testHookMinerals() {
        // 测试钩子上有物品时速度
        stage.hook.state = FORWARD;
        for(int i = 0;i < stage.mineralList.size();i++) {
            stage.hook.mineral = stage.mineralList.get(i);
            assertEquals(40000.0 / (stage.hook.weight+stage.hook.mineral.density * stage.hook.mineral.r * stage.hook.mineral.r), stage.hook.getPullVelocity(),0.000001);
        }
    }

    @Test
    public void testWin() throws IOException {
        // 测试通关成功
       stage.score = stage.requireScore + 100;
       stage.next();
       assertEquals(2, stage.order);
    }

    @Test
    public void testDefault() throws IOException {
        // 测试通关失败
        stage.score = stage.requireScore - 100;
        stage.next();
        assertEquals(stage.stageState,GAME_OVER);
    }
}
