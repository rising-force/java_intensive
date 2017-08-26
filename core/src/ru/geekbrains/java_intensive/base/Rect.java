package ru.geekbrains.java_intensive.base;

import com.badlogic.gdx.math.Vector2;

public class Rect {

    protected final Vector2 pos = new Vector2();
    protected float halfWidth;
    protected float halfHeight;

    public float getLeft() {
        return pos.x - halfWidth;
    }

    public float getTop() {
        return pos.y + halfHeight;
    }

    public float getRight() {
        return pos.x + halfWidth;
    }

    public float getBottom() {
        return pos.y - halfHeight;
    }

    public float getWidth() {
        return halfWidth * 2f;
    }

    public float getHeight() {
        return halfHeight * 2f;
    }

    public boolean isMe(Vector2 touch) {
        return touch.x >= getLeft() && touch.x <= getRight() && touch.y >= getBottom() && touch.y <= getTop();
    }

    public void setBottom(float bottom) {
        pos.y = bottom + halfHeight;
    }
}
