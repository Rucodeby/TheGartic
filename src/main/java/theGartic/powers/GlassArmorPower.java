package theGartic.powers;

import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theGartic.GarticMod.makeID;
import static theGartic.GarticMod.modID;
import static theGartic.util.Wiz.atb;

public class GlassArmorPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID(GarbageGolemPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    
    public GlassArmorPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_PLATED", 0.05F);
    }

    public void updateDescription() {
        if (this.owner.isPlayer) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
        }
    }

    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount += stackAmount;
        if (amount > 999)
            amount = 999;
        updateDescription();
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            flash();
            CardCrawlGame.sound.play(modID + ":GLASSARMOR");
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    public void onRemove() {
        if (!this.owner.isPlayer)
            atb(new ChangeStateAction((AbstractMonster) owner, "ARMOR_BREAK"));
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        flash();
        atb(new GainBlockAction(owner, owner, amount));
    }
}
