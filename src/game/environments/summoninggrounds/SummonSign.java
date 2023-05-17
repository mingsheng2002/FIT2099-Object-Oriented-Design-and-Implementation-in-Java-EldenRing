package game.environments.summoninggrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.nonplayercharacters.summonables.Ally;
import game.actors.nonplayercharacters.summonables.Invader;

/**
 * TBC
 * A type of ground that the player will fall off and get killed instantly.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Ground
 */
public class SummonSign extends SummoningGround {

    /**
     * Constructor for Summon.
     */
    public SummonSign() {
        super('=');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        getSummonables().clear();
        getSummonables().add(new Ally());
        getSummonables().add(new Invader());
        return super.allowableActions(actor, location, direction);
    }
}
