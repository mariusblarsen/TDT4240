package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MenuState(gsm: GameStateManager) : State(gsm) {
    private var taskOneBtn : Texture = Texture("task1btn.png")
    private var taskTwoBtn : Texture = Texture("task2btn.png")
    private var taskThreeBtn : Texture = Texture("task3btn.png")
    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(taskOneBtn,
                600 - (taskOneBtn.width / 2).toFloat(),
                Gdx.graphics.height/2.toFloat())
        sb.draw(taskTwoBtn,
                1200 - (taskOneBtn.width / 2).toFloat(),
                Gdx.graphics.height / 2.toFloat())
        sb.draw(taskThreeBtn,
                1800 - (taskOneBtn.width / 2).toFloat(),
                Gdx.graphics.height / 2.toFloat())
        sb.end()
    }

    override fun handleInput() {
        val posX = Gdx.input.x
        if (Gdx.input.justTouched()){
            when{
                posX > 1800 -> gsm.set(AnimationState(gsm))
                posX > 1200 -> gsm.set(MoveState(gsm))
                else -> {
                    gsm.set(BounceState(gsm))
                }

            }
            dispose()
        }

    }

    override fun dispose() {
        taskOneBtn.dispose()
    }
}