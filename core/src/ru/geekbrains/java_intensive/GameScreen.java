package ru.geekbrains.java_intensive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import ru.geekbrains.java_intensive.asteroids.Asteroid;

class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture textureBackground;
    private Texture textureAsteroid;
    private Texture textureBtnNewGame;
    private TextureRegion regionAsteroid;
    private final ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

    @Override
    public void show() {
        batch = new SpriteBatch();
        textureBackground = new Texture("textures/background.png");
        textureAsteroid = new Texture("textures/asteroid_1.png");
        textureBtnNewGame = new Texture("textures/btn_new_game.png");
        regionAsteroid = new TextureRegion(textureAsteroid);
        asteroids.add(new Asteroid(regionAsteroid));
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollision();
        draw();
    }

    private void update(float deltaTime) {

    }

    private void checkCollision() {

    }

    private void draw() {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(textureBackground, 0, 0);
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        textureBackground.dispose();
        textureAsteroid.dispose();
        textureBtnNewGame.dispose();
    }
}
