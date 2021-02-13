package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.exercise1.sprites.Paddle

class GameOverState(gsm: GameStateManager, private val winner: String ) : State(gsm) {
    private var title = BitmapFont()
    private var subtitle = BitmapFont()

    init {
        title.data.setScale(3f)
        subtitle.data.setScale(2f)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(MenuState(gsm))
            dispose()
        }
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        title.draw(sb,
                "$winner won the game!",
                Gdx.graphics.width/4.toFloat() - 200,
                Gdx.graphics.height/2.toFloat() - 100)
        subtitle.draw(sb,
                "Press anywhere to return to the menu",
                Gdx.graphics.width/4.toFloat() - 220,
                Gdx.graphics.height/2.toFloat() - 200)
        sb.end()
    }

    override fun dispose() {
    }

}