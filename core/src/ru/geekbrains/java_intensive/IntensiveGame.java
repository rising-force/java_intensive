package ru.geekbrains.java_intensive;

import com.badlogic.gdx.Game;

public class IntensiveGame extends Game  {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
