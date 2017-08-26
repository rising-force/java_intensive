package ru.geekbrains.java_intensive;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.java_intensive.base.Sprite;

public class ButtonNewGame extends Sprite {

    private final GameScreen gameScreen;

    public ButtonNewGame(TextureRegion textureRegion, GameScreen gameScreen, float worldCenterX, float worldCenterY) {
        super(textureRegion);
        this.gameScreen = gameScreen;
        pos.set(worldCenterX, worldCenterY);
    }

    void touchDown(Vector2 touch) {
        if(isMe(touch)) gameScreen.startNewGame();
    }
}
