package ru.geekbrains.java_games.common.enemies;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;
import ru.geekuniversity.engine.utils.Regions;

public class EnemiesEmitter {

    private int stage;

    private final Rect worldBounds;
    private final EnemyPool enemyPool;

    private final Sound sndBullet;
    private final TextureRegion bulletRegion;

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private final TextureRegion[] enemySmallRegions;
    private final Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private final TextureRegion[] enemyMediumRegions;
    private final Vector2 enemyMediumV = new Vector2(0f, -0.03f);
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.25f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private final TextureRegion[] enemyBigRegions;
    private final Vector2 enemyBigV = new Vector2(0f, -0.005f);
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 20;

    private static final float ENEMY_PLANE_HEIGHT = 0.2f;
    private final TextureRegion[] enemyPLANERegions;
    private final Vector2 enemyPLANEV = new Vector2(0f, -0.25f);
    private static final float ENEMY_PLANE_BULLET_HEIGHT = 0.05f;
    private static final float ENEMY_PLANE_BULLET_VY = -0.9f;
    private static final int ENEMY_PLANE_BULLET_DAMAGE = 20;
    private static final float ENEMY_PLANE_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_PLANE_HP = 5;

    private static final float ENEMY_STAR_HEIGHT = 0.1f;
    private final TextureRegion[] enemySTARRegions;
    private final Vector2 enemySTARV = new Vector2(0f, -0.15f);
    private static final float ENEMY_STAR_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_STAR_BULLET_VY = -2f;
    private static final int ENEMY_STAR_BULLET_DAMAGE = 2;
    private static final float ENEMY_STAR_RELOAD_INTERVAL = 0.5f;
    private static final int ENEMY_STAR_HP = 7;

    private static final float ENEMY_BIGPLANE_HEIGHT = 0.5f;
    private final TextureRegion[] enemyBIGPLANERegions;
    private final Vector2 enemyBIGPLANEV = new Vector2(0f, -0.001f);
    private static final float ENEMY_BIGPLANE_BULLET_HEIGHT = 0.1f;
    private static final float ENEMY_BIGPLANE_BULLET_VY = -0.01f;
    private static final int ENEMY_BIGPLANE_BULLET_DAMAGE = 50;
    private static final float ENEMY_BIGPLANE_RELOAD_INTERVAL = 9f;
    private static final int ENEMY_BIGPLANE_HP = 25;


    private float generateInterval = 4f;
    private float generateTimer;

    public EnemiesEmitter(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas, Sound sndBullet, TextureAtlas atlas2) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        this.sndBullet = sndBullet;
        TextureRegion region0 = atlas.findRegion("enemy0");
        enemySmallRegions = Regions.split(region0, 1, 2, 2);
        TextureRegion region1 = atlas.findRegion("enemy1");
        enemyMediumRegions = Regions.split(region1, 1, 2, 2);
        TextureRegion region2 = atlas.findRegion("enemy2");
        enemyBigRegions = Regions.split(region2, 1, 2, 2);
        TextureRegion region3 = atlas2.findRegion("flat");
        enemyPLANERegions = Regions.split(region3,1, 1, 1);
        TextureRegion region4 = atlas2.findRegion("key");
        enemySTARRegions = Regions.split(region4,1, 1, 1);
        TextureRegion region5 = atlas2.findRegion("we");
        enemyBIGPLANERegions = Regions.split(region5,1, 1, 1);
        bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void setToNewGame() {
        stage = 1;
    }

    public int getStage() {
        return stage;
    }

    public void generateEnemies(float deltaTime, int frags) {
        stage = frags / 10 + 1;
        generateTimer += deltaTime;
        if(generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if(type < 0.2f) {
                enemy.set(
                        enemySmallRegions,
                        enemySmallV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE * stage,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        sndBullet,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP * stage);
            } else if(type < 0.3f) {
                enemy.set(
                        enemyMediumRegions,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_BULLET_DAMAGE * stage,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        sndBullet,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP * stage);
            } else if (type < 0.4f) {
                enemy.set(
                        enemyBigRegions,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE * stage,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        sndBullet,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP * stage);
            } else if(type < 0.5f) {
                enemy.set(
                        enemyBIGPLANERegions,
                        enemyBIGPLANEV,
                        bulletRegion,
                        ENEMY_BIGPLANE_BULLET_HEIGHT,
                        ENEMY_BIGPLANE_BULLET_VY,
                        ENEMY_BIGPLANE_BULLET_DAMAGE * stage,
                        ENEMY_BIGPLANE_RELOAD_INTERVAL,
                        sndBullet,
                        ENEMY_BIGPLANE_HEIGHT,
                        ENEMY_BIGPLANE_HP * stage);
            } else if(type < 0.7f) {
                enemy.set(
                        enemyPLANERegions,
                        enemyPLANEV,
                        bulletRegion,
                        ENEMY_PLANE_BULLET_HEIGHT,
                        ENEMY_PLANE_BULLET_VY,
                        ENEMY_PLANE_BULLET_DAMAGE * stage,
                        ENEMY_PLANE_RELOAD_INTERVAL,
                        sndBullet,
                        ENEMY_PLANE_HEIGHT,
                        ENEMY_PLANE_HP * stage);
            }
            else if(type < 0.9f) {
                enemy.set(
                        enemySTARRegions,
                        enemySTARV,
                        bulletRegion,
                        ENEMY_STAR_BULLET_HEIGHT,
                        ENEMY_STAR_BULLET_VY,
                        ENEMY_STAR_BULLET_DAMAGE * stage,
                        ENEMY_STAR_RELOAD_INTERVAL,
                        sndBullet,
                        ENEMY_STAR_HEIGHT,
                        ENEMY_STAR_HP * stage);
            }
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
//            enemy.pos.x = worldBounds.getLeft() + enemy.getHalfWidth();
            enemy.setBottom(worldBounds.getTop());
        }
    }
}
