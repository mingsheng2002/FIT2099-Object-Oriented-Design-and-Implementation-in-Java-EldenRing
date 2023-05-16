package game.actors.nonplayercharacters.traders;

import game.actors.nonplayercharacters.traders.Trader;
import game.enums.Status;
import game.items.RemembranceOfTheGrafted;
import game.weapons.portableweapons.*;

public class FingerReaderEnia extends Trader {

    /**
     * Starting hit point of
     */
    private static final int HIT_POINT = 100;

    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', HIT_POINT);
        this.addCapability(Status.PROVIDE_SELL_SERVICE);
        this.addCapability(Status.PROVIDE_EXCHANGE_SERVICE);
        this.addNewSellable(new Grossmesser());
        this.addNewSellable(new GreatKnife());
        this.addNewSellable(new Uchigatana());
        this.addNewSellable(new Club());
        this.addNewSellable(new Scimitar());
        this.addNewSellable(new AxeOfGodrick());
        this.addNewSellable(new GraftedDragon());
        this.addNewSellable(new RemembranceOfTheGrafted());
        this.addNewExchangeable(new RemembranceOfTheGrafted());
    }
}
