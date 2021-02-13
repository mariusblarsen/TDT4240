package com.mygdx.exercise1.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector3
import kotlin.random.Random

class MoveHelicopter(x : Float, y : Float) {
    private var position : Vector3 = Vector3(x, y,0F)
    private var velocity : Vector3 = Vector3(0F, 0F, 0F)
    private var heli : Texture = Texture("heli1.png")
    private var heliSprite : Sprite = Sprite(heli)
    private var headingLeft = true

    fun update(dt: Float){
        velocity.scl(dt)
        position.add(velocity.x, velocity.y, 0F)
        checkBorder()
        velocity.scl(1/dt)
    }

    private fun checkBorder(){
        val rightBorder = Gdx.graphics.width / 2 - heli.width
        val topBorder = Gdx.graphics.height / 2 - heli.height
        val posX = position.x
        val posY = position.y
        when{
            posX < 0 -> velocity.x = 0F
            posX > rightBorder -> velocity.x = 0F
        }
        when{
            posY < 0 -> velocity.y = 0F
            posY > topBorder -> velocity.y = 0F
        }
    }

    fun setVelocity(x: Float, y: Float){
        val posY = Gdx.graphics.height - y  // Input coordinates starts top-left
        val touchX = (x - heli.width) / 2
        val touchY = (posY - heli.height) / 2

        fly(2*(touchX - position.x),
                2*(touchY - position.y))



    }

    fun getPosition(): Vector3{
        return position
    }

    fun fly(x: Float, y: Float){
        if(x > 0 && headingLeft){
            heliSprite.flip(true, false)
            headingLeft = false
        }
        if(x < 0 && !headingLeft){
            heliSprite.flip(true, false)
            headingLeft = true
        }
        velocity.set(x, y, 0F)
    }

    fun getSprite(): Sprite {
        return heliSprite
    }

    fun dispose(){
        heli.dispose()
    }

}