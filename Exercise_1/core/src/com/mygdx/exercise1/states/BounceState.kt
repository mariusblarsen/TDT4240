package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.exercise1.sprites.Helicopter
import kotlin.random.Random

class BounceState(gsm : GameStateManager) : State(gsm) {
    private var startX = Gdx.graphics.width/2.toFloat()
    private var startY = Gdx.graphics.height/2.toFloat()
    private var heli : Helicopter = Helicopter(50F, 100F)

    init {
        cam.setToOrtho(false,
                Gdx.graphics.width / 2.toFloat(),
                Gdx.graphics.height / 2.toFloat())
    }

    override fun update(dt: Float) {
        handleInput()
        heli.update(dt)
        flyRandom()

    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(heli.getTexture(), heli.getPosition().x, heli.getPosition().y)
        sb.end()
    }

    fun flyRandom(){
        // Random starting velocity
        // While true
        // If position is border
        // Change direction
        val velX = Random.nextInt(-10,10).toFloat()
        val velY = Random.nextInt(-10, 10).toFloat()
        heli.fly(velX, velY)
    }

    override fun handleInput() {

    }

    override fun dispose() {

    }
}