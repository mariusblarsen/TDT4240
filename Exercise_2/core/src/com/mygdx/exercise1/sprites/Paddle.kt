package com.mygdx.exercise1.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import kotlin.math.min

class Paddle(x: Float, y: Float) {
    private var texture : Texture = Texture("padlet.png")
    private var velocity : Float = 0F
    private var speed : Float = 10F
    private var position : Vector2 = Vector2(x - texture.width, y)
    private var hitbox : Rectangle = Rectangle(position.x, y, texture.width.toFloat(), texture.height.toFloat())
    private var paddleSprite : Sprite = Sprite(texture)

    fun setPos(y: Float){
        velocity = when{
            y > getY() + speed/2 -> speed
            y < getY() - speed/2 -> -speed
            else -> {
                return
            }
        }
        var nextPos = position.y + velocity
        val topBorder = Gdx.graphics.height/2 - paddleSprite.height

        when {
            nextPos > topBorder -> nextPos = topBorder
            nextPos < 0 -> nextPos = 0F
        }
        position.y = nextPos
    }

    fun shrink(){
        val scale = 0.9F
        val minSize = 50F
        if (paddleSprite.height > minSize){
            paddleSprite.setSize(paddleSprite.texture.width.toFloat(), paddleSprite.height*scale)
        }
    }

    fun update(){
        hitbox.y = position.y
        hitbox.height = paddleSprite.height
    }

    fun draw(sb: SpriteBatch){
        sb.draw(paddleSprite, position.x, position.y, paddleSprite.width, paddleSprite.height)
    }

    fun reset(){
        paddleSprite.setSize(texture.width.toFloat(), texture.height.toFloat())
    }

    fun increaseSpeed(){
        speed = min(speed + 3F, 25F)
    }

    fun getY(): Float{
        /**
         * @return y-value middle of paddle.
         */
        return position.y + paddleSprite.height/2
    }

    fun getVelocity() : Float{
        return velocity
    }

    fun getHitbox(): Rectangle{
        return hitbox
    }

    fun getSprite(): Sprite{
        return paddleSprite
    }
    fun dispose(){
        texture.dispose()
    }
}