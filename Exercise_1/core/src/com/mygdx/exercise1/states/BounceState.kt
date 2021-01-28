package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class BounceState(gsm : GameStateManager) : State(gsm) {
    private var heli : Texture = Texture("heli1.png")

    init {
        cam.setToOrtho(false,
                Gdx.graphics.width / 2.toFloat(),
                Gdx.graphics.height / 2.toFloat())
    }

    override fun update(dt: Float) {

    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(heli, 50.toFloat(), 50.toFloat())
        sb.end()
    }

    override fun handleInput() {

    }

    override fun dispose() {

    }
}