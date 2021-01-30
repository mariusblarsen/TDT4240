package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.exercise1.sprites.MoveHelicopter


class MoveState(gsm : GameStateManager) : State(gsm) {
    private var startX = Gdx.graphics.width / 4.toFloat()
    private var startY = Gdx.graphics.height / 4.toFloat()
    private var heli : MoveHelicopter = MoveHelicopter(startX, startY)
    private var font = BitmapFont()

    init {
        cam.setToOrtho(false,
                Gdx.graphics.width / 2.toFloat(),
                Gdx.graphics.height / 2.toFloat())
    }

    override fun update(dt: Float) {
        handleInput()
        heli.update(dt)
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(heli.getSprite(), heli.getPosition().x, heli.getPosition().y)
        font.draw(sb,
                "X:${heli.getPosition().x} \nY:${heli.getPosition().y}",
                10F,
                startY*2)
        font.draw(sb, "Exit", 20F, 20F)
        sb.end()
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            heli.setVelocity(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
            if (Gdx.input.x <= 100 && Gdx.input.y >= Gdx.graphics.height-100){
                gsm.set(MenuState(gsm))
                dispose()
            }
        }
    }

    override fun dispose() {
        heli.dispose()
    }
}