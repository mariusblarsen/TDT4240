package com.mygdx.exercise1.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class State protected constructor(protected var gsm: GameStateManager) {
    protected var cam: OrthographicCamera = OrthographicCamera()
    protected abstract fun handleInput()

    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch)
    abstract fun dispose()

}