package theGartic.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ResoluteParticleEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vY;
    private float dur_div2;
    private Texture img;
    
    public ResoluteParticleEffect() {
        this.img = ImageMaster.BLOCK_ICON;
        this.duration = MathUtils.random(1.3F, 1.8F);
        this.scale = MathUtils.random(0.6F, 1.0F) * Settings.scale;
        this.dur_div2 = this.duration / 2.0F;
        this.color = new Color(0.0F, MathUtils.random(0.5F, 1.0F),  MathUtils.random(0.0F, 0.2F), 0.0F);
        this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 2.0F - 30.0F * Settings.scale, AbstractDungeon.player.hb.width / 2.0F + 30.0F * Settings.scale);
        this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 2.0F - -10.0F * Settings.scale, AbstractDungeon.player.hb.height / 2.0F - 10.0F * Settings.scale);
        this.x -= (float)this.img.getWidth() / 2.0F;
        this.y -= (float)this.img.getHeight() / 2.0F;
        this.renderBehind = MathUtils.randomBoolean(0.2F + (this.scale - 0.5F));
        this.rotation = MathUtils.random(-8.0F, 8.0F);
    }
    
    public void update() {
        if (this.duration > this.dur_div2) {
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
        }
        
        this.vY += Gdx.graphics.getDeltaTime() * 40.0F * Settings.scale;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
        
    }
    
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(new TextureRegion(this.img), this.x, this.y + this.vY, (float)this.img.getWidth() / 2.0F, (float)this.img.getHeight() / 2.0F, (float)this.img.getWidth(), (float)this.img.getHeight(), this.scale * 0.8F, (0.1F + (this.dur_div2 * 2.0F - this.duration) * 2.0F * this.scale) * Settings.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }
    
    public void dispose() {
    }
}
