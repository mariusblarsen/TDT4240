package com.mygdx.exercise1.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector3
import javax.xml.soap.Text
import kotlin.random.Random

class AnimationHelicopter(x : Float, y : Float) {
    private var position : Vector3 = Vector3(x, y,0F)
    private var velocity : Vector3 = Vector3(0F, 0F, 0F)
    private var headingLeft : Boolean = true
    private var animation: Animation = Animation()
    private var heliSprite : Sprite = Sprite(animation.getFrame())

    fun update(dt: Float){
        animation.update(dt)
        heliSprite = Sprite(animation.getFrame())
        checkBorder()
        velocity.scl(dt)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/dt)
        setHeading()
    }

    private fun checkBorder(){
        val rightBorder = Gdx.graphics.width / 2 - animation.getFrame().width
        val topBorder = Gdx.graphics.height / 2 - animation.getFrame().height
        var velX = velocity.x
        var velY = velocity.y

        val posX = position.x
        val posY = position.y

        when{
            posX < 0 -> velX = Random.nextInt(40,100).toFloat()
            posX > rightBorder -> velX = Random.nextInt(-100,-40).toFloat()

            posY < 0 -> velY = Random.nextInt(40,100).toFloat()
            posY > topBorder -> velY = Random.nextInt(-100,-40).toFloat()
        }
        fly(velX, velY)
    }

    fun setHeading(){
        if (velocity.x > 0 && headingLeft){
            heliSprite.flip(true,false)
            headingLeft = false
        }
        if (velocity.x < 0 && !headingLeft){
            heliSprite.flip(true,false)
            headingLeft = true
        }
    }

    fun getPosition(): Vector3{
        return position
    }

    fun fly(x: Float, y: Float){
        velocity.set(x, y, 0F)
    }

    fun getSprite(): Sprite {
        return heliSprite
    }

    fun dispose(){

    }

}