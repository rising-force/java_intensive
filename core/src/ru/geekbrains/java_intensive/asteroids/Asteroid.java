package ru.geekbrains.java_intensive.asteroids;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.java_intensive.base.Rnd;
import ru.geekbrains.java_intensive.base.Sprite;

public class Asteroid extends Sprite {

    private final Vector2 v = new Vector2();
    private final float w = Rnd.nextFloat(-90, 90);
    private final float worldTop;
    private final float worldRight;

    public Asteroid(TextureRegion textureRegion, float vy0, float worldTop, float worldRight) {
        super(textureRegion);
        float scale = Rnd.nextFloat(0.3f, 1f);
        halfWidth = halfWidth * scale;
        halfHeight = halfHeight * scale;
        this.worldTop = worldTop;
        this.worldRight = worldRight;
        v.set(0f, vy0);
        moveUp();
    }

    public void update(float deltaTime) {
        pos.mulAdd(v, deltaTime);
        angle += w * deltaTime;
    }

    private void moveUp() {
        pos.x = Rnd.nextFloat(halfWidth, worldRight - halfWidth);
        setBottom(worldTop);
    }

    @Override
    public boolean isMe(Vector2 touch){
        return pos.dst2(touch) <= halfWidth * halfWidth;
    }
}
