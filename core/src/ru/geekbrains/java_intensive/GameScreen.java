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

    private static final float ASTEROID_VY_PERCENT = 0.3f;

    private SpriteBatch batch;
    private Texture textureBackground;
    private Texture textureAsteroid;
    private Texture textureBtnNewGame;
    private TextureRegion regionAsteroid;
    private final ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    private float worldHeight;
    private float worldWidth;

    @Override
    public void show() {
        worldWidth = Gdx.graphics.getWidth();
        worldHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        textureBackground = new Texture("textures/background.png");
        textureAsteroid = new Texture("textures/asteroid_1.png");
        textureBtnNewGame = new Texture("textures/btn_new_game.png");
        regionAsteroid = new TextureRegion(textureAsteroid);
        startNewGame();
    }

    private void addNewAsteroid(){
        asteroids.add(new Asteroid(regionAsteroid, -ASTEROID_VY_PERCENT * worldHeight, worldHeight - 1, worldWidth - 1));
    }

    void startNewGame() {
        asteroids.clear();
        addNewAsteroid();
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollision();
        draw();
    }

    private void update(float deltaTime) {
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).update(deltaTime);
        }
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
