package theGartic.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.actions.PickNewSummonToAddToPartyAction;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class TallGrass extends AbstractEasyCard {
    public final static String ID = makeID(TallGrass.class.getSimpleName());
    private final static int COST = 10;
    private final static int CHOICES = 3;
    private final static int UPGRADED_COST = 0;

    public TallGrass()
    {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new PickNewSummonToAddToPartyAction(CHOICES));
    }

    public void upp()
    {
        upgradeBaseCost(UPGRADED_COST);
    }
}