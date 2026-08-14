package forge.game;

import forge.ai.AITest;
import forge.game.player.Player;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class WorldPlaneShiftTest extends AITest {

    @Test
    public void testDefaultPlaneIsHorizon() {
        Game game = initAndCreateGame();
        AssertJUnit.assertEquals(WorldPlane.HORIZON, game.getWorldPlane());
    }

    @Test
    public void testShiftFromHorizonToZenithIsLegal() {
        Game game = initAndCreateGame();
        Player p = game.getPlayers().get(0);

        game.action.shiftWorld(p, WorldPlane.ZENITH);

        AssertJUnit.assertEquals(WorldPlane.ZENITH, game.getWorldPlane());
    }

    @Test
    public void testShiftFromHorizonToNadirIsLegal() {
        Game game = initAndCreateGame();
        Player p = game.getPlayers().get(0);

        game.action.shiftWorld(p, WorldPlane.NADIR);

        AssertJUnit.assertEquals(WorldPlane.NADIR, game.getWorldPlane());
    }

    @Test
    public void testShiftSkippingHorizonIsIllegalNoOp() {
        Game game = initAndCreateGame();
        Player p = game.getPlayers().get(0);

        game.action.shiftWorld(p, WorldPlane.ZENITH);
        AssertJUnit.assertEquals(WorldPlane.ZENITH, game.getWorldPlane());

        // Zenith -> Nadir directly is illegal; the World must pass through Horizon.
        game.action.shiftWorld(p, WorldPlane.NADIR);
        AssertJUnit.assertEquals(WorldPlane.ZENITH, game.getWorldPlane());
    }

    @Test
    public void testShiftToSamePlaneIsNoOp() {
        Game game = initAndCreateGame();
        Player p = game.getPlayers().get(0);

        game.action.shiftWorld(p, WorldPlane.HORIZON);

        AssertJUnit.assertEquals(WorldPlane.HORIZON, game.getWorldPlane());
    }

    @Test
    public void testShiftFromZenithBackToHorizonIsLegal() {
        Game game = initAndCreateGame();
        Player p = game.getPlayers().get(0);

        game.action.shiftWorld(p, WorldPlane.ZENITH);
        AssertJUnit.assertEquals(WorldPlane.ZENITH, game.getWorldPlane());

        game.action.shiftWorld(p, WorldPlane.HORIZON);
        AssertJUnit.assertEquals(WorldPlane.HORIZON, game.getWorldPlane());
    }

    @Test
    public void testShiftToSamePlaneFromExtremeIsNoOp() {
        Game game = initAndCreateGame();
        Player p = game.getPlayers().get(0);

        game.action.shiftWorld(p, WorldPlane.ZENITH);
        AssertJUnit.assertEquals(WorldPlane.ZENITH, game.getWorldPlane());

        game.action.shiftWorld(p, WorldPlane.ZENITH);
        AssertJUnit.assertEquals(WorldPlane.ZENITH, game.getWorldPlane());
    }
}
