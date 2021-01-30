package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.exercise1.sprites.MenuButton
import com.badlogic.gdx.math.Rectangle

class MenuState(gsm: GameStateManager) : State(gsm) {
    private var taskOneBtn : MenuButton = MenuButton(Gdx.graphics.width/(2*5).toFloat(),Gdx.graphics.height/4.toFloat(),
            "task1btn.png", BounceState(gsm))
    private var taskTwoBtn : MenuButton = MenuButton(2*Gdx.graphics.width/(2*5).toFloat(),Gdx.graphics.height/4.toFloat(),
            "task2btn.png", MoveState(gsm))
    private var taskThreeBtn : MenuButton = MenuButton(3*Gdx.graphics.width/(2*5).toFloat(),Gdx.graphics.height/4.toFloat(),
            "task3btn.png", AnimationState(gsm))

    init {
        cam.setToOrtho(false,
                Gdx.graphics.width / 2.toFloat(),
                Gdx.graphics.height / 2.toFloat())
    }


    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        taskOneBtn.draw(sb)
        taskTwoBtn.draw(sb)
        taskThreeBtn.draw(sb)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            val clicksize = 50F
            val posY = Gdx.graphics.height - Gdx.input.y
            val click = Rectangle(Gdx.input.x.toFloat()/2 - clicksize/2,
                    posY/2 - clicksize/2,
                    clicksize, clicksize)
            when{
                click.overlaps(taskOneBtn.getHitbox()) -> taskOneBtn.clicked(gsm)
                click.overlaps(taskTwoBtn.getHitbox()) -> taskTwoBtn.clicked(gsm)
                click.overlaps(taskThreeBtn.getHitbox()) -> taskThreeBtn.clicked(gsm)
                else -> {return}
            }
            dispose()
        }
    }

    override fun dispose() {
        taskOneBtn.dispose()
        taskTwoBtn.dispose()
        taskThreeBtn.dispose()
    }
}