package com.mygdx.exercise1.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import kotlin.random.Random

class Ball {
    private var position : Vector2 = Vector2(
            Gdx.graphics.width/4.toFloat(),
            Gdx.graphics.height/4.toFloat())
    private var velocity : Vector2 = Vector2(750f, 0f)
    private var texture : Texture = Texture("ball.png")
    private var hitbox : Rectangle = Rectangle(position.x, position.y, texture.width.toFloat(), texture.height.toFloat())
    private var hits : Int = 0

    init {
        val startLeft = Random.nextInt(1, 3) == 1
        velocity.y = Random.nextInt(-150, 150).toFloat()
        if (startLeft){
            velocity.set(-velocity.x, velocity.y)
        }
    }

    fun flip(paddleVelocity: Float){
        velocity.set(-velocity.x, velocity.y + paddleVelocity*4)
        if (hits++ % 5 == 0){
            speedUp()
        }
    }

    fun update(dt: Float){
        checkBorder()
        velocity.scl(dt)
        position.add(velocity.x, velocity.y)
        velocity.scl(1/dt)
        hitbox.setPosition(position)
    }

    private fun checkBorder(){
        when{
            position.y <= 0 -> velocity.set(velocity.x, -velocity.y)
            position.y >= Gdx.graphics.height/2-texture.height -> velocity.set(velocity.x, -velocity.y)
        }
    }

    fun draw(sb: SpriteBatch){
        sb.draw(texture, position.x, position.y)
    }

    fun getY(): Float{
        return position.y
    }
    fun getX(): Float{
        return position.x
    }

    private fun speedUp(){
        val dv = 150F
        val maxSpeed = 2500F
        when{
            velocity.x > maxSpeed -> velocity.x = maxSpeed
            headLeft() -> velocity.add(-dv, 0F)
            else -> {
                velocity.add(dv, 0F)
            }
        }
    }

    fun getHitbox() : Rectangle{
        return hitbox
    }

    fun headLeft(): Boolean{
        return velocity.x <= 0
    }

    fun reset(){
        position.set(Gdx.graphics.width/4.toFloat(), Gdx.graphics.height/4.toFloat())
        val startLeft = Random.nextInt(1, 3) == 1
        velocity.x = 500F
        velocity.y = Random.nextInt(-150, 150).toFloat()
        if (startLeft){
            velocity.set(-velocity.x, velocity.y)
        }
    }

    fun dispose(){
        texture.dispose()
    }
}