package com.mygdx.exercise1.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.exercise1.states.GameStateManager
import com.mygdx.exercise1.states.State

class MenuButton(x: Float, y: Float, path: String, private val state: State) {
    private var texture : Texture = Texture(path)
    private val hitbox : Rectangle = Rectangle(x, y,
            texture.width.toFloat(), texture.height.toFloat())

    fun clicked(gsm: GameStateManager){
        gsm.set(state)
    }

    fun getHitbox(): Rectangle{
        return hitbox
    }

    fun draw(sb: SpriteBatch){
        sb.begin()
        sb.draw(texture, hitbox.x, hitbox.y)
        sb.end()
    }

    fun dispose(){
        texture.dispose()
    }
}