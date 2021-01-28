package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MenuState(gsm: GameStateManager) : State(gsm) {
    private var taskOneBtn : Texture = Texture("task1btn.png")
    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(taskOneBtn,
                Gdx.graphics.width / 2 - (taskOneBtn.width / 2).toFloat(),
                Gdx.graphics.height / 2.toFloat())
        sb.end()
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(BounceState(gsm))
            dispose()
        }

    }

    override fun dispose() {
        taskOneBtn.dispose()
    }
}