package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.exercise1.sprites.BounceHelicopter
import kotlin.random.Random

class BounceState(gsm : GameStateManager) : State(gsm) {
    private var startX = Gdx.graphics.width / 4.toFloat()
    private var startY = Gdx.graphics.height / 4.toFloat()
    private var heli : BounceHelicopter = BounceHelicopter(startX, startY)

    init {
        cam.setToOrtho(false,
                Gdx.graphics.width / 2.toFloat(),
                Gdx.graphics.height / 2.toFloat())
        val velX = Random.nextInt(-100,100).toFloat()
        val velY = Random.nextInt(-100, 100).toFloat()
        heli.fly(velX, velY)
        heli.setHeading()
    }

    override fun update(dt: Float) {
        handleInput()
        heli.update(dt)
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(heli.getSprite(), heli.getPosition().x, heli.getPosition().y)
        sb.end()
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(MenuState(gsm))
            dispose()
        }
    }

    override fun dispose() {
        heli.dispose()
    }
}