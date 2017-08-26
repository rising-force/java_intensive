package ru.geekbrains.java_intensive.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite extends Rect {

    private TextureRegion textureRegion;
    protected float angle;

    public Sprite(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        halfWidth = textureRegion.getRegionWidth() / 2f;
        halfHeight = textureRegion.getRegionHeight() / 2f;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                textureRegion,
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                1f, 1f, angle);
    }
}
