package game.actors.nonplayercharacters.traders;

import game.enums.Status;
import game.items.remembrances.RemembranceOfTheGrafted;
import game.weapons.portableweapons.*;

/**
 * Class representing the Finger Reader Enia which is a type of Trader.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Trader
 */
public class FingerReaderEnia extends Trader {

    /**
     * Starting hit point of FingerReaderEnia
     */
    private static final int HIT_POINT = 100;

    /**
     * Constructor for FingerReaderEnia.
     * @see Status#PROVIDE_SELL_SERVICE
     * @see Status#PROVIDE_PURCHASE_SERVICE
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', HIT_POINT);
        this.addCapability(Status.PROVIDE_SELL_SERVICE);
        this.addCapability(Status.PROVIDE_EXCHANGE_SERVICE);
        this.addNewSellable(new Grossmesser());
        this.addNewSellable(new GreatKnife());
        this.addNewSellable(new Uchigatana());
        this.addNewSellable(new Club());
        this.addNewSellable(new Scimitar());
        this.addNewSellable(new HeavyCrossbow());
        this.addNewSellable(new AxeOfGodrick());
        this.addNewSellable(new GraftedDragon());
        this.addNewSellable(new RemembranceOfTheGrafted());
        this.addNewExchangeable(new RemembranceOfTheGrafted());
    }
}
