package ru.geekbrains.java_intensive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.ArrayList;

import ru.geekbrains.java_intensive.asteroids.Asteroid;

class GameScreen implements Screen, InputProcessor {

    private static final float ASTEROID_VY_PERCENT = 0.3f;
    private static final String STR_SCORE = "Score: ";

    private SpriteBatch batch;
    private Texture textureBackground;
    private Texture textureAsteroid;
    private Texture textureBtnNewGame;
    private TextureRegion regionAsteroid;
    private final ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    private float worldHeight;
    private float worldWidth;
    private BitmapFont font;
    private int score;
    private ButtonNewGame buttonNewGame;

    @Override
    public void show() {
        worldWidth = Gdx.graphics.getWidth();
        worldHeight = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        textureBackground = new Texture("textures/background.png");
        textureAsteroid = new Texture("textures/asteroid_1.png");
        textureBtnNewGame = new Texture("textures/btn_new_game.png");
        regionAsteroid = new TextureRegion(textureAsteroid);

        font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font.png"), false, true);
        buttonNewGame = new ButtonNewGame(new TextureRegion(textureBtnNewGame), this, worldWidth / 2f, worldHeight / 2f);
        startNewGame();
    }

    private void addNewAsteroid(){
        asteroids.add(new Asteroid(regionAsteroid, -ASTEROID_VY_PERCENT * worldHeight, worldHeight - 1, worldWidth - 1));
    }

    void startNewGame() {
        score = 0;
        asteroids.clear();
        addNewAsteroid();
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollision();
        draw();
    }

    private float addAsteroidInterval = 10f;
    private float addAsteroidTimer;

    private void update(float deltaTime) {
        addAsteroidTimer += deltaTime;
        if(addAsteroidTimer >= addAsteroidInterval) {
            addAsteroidTimer = 0f;
            addNewAsteroid();
        }
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).update(deltaTime);
        }
    }

    private void checkCollision() {

    }

    private final StringBuilder sbScore = new StringBuilder();

    private void draw() {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(textureBackground, 0, 0);
        for (int i = 0; i < asteroids.size(); i++) asteroids.get(i).draw(batch);

        sbScore.setLength(0);
        sbScore.append(STR_SCORE);
        sbScore.append(score);
        font.draw(batch, sbScore, 0, worldHeight - 1);
        buttonNewGame.draw(batch);
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
        font.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    private final Vector2 touch = new Vector2();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, worldHeight - screenY - 1);
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            if(asteroids.get(i).touchDown(touch)) {
                score++;
                break;
            }
        }
        buttonNewGame.touchDown(touch);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
