package com.mygdx.exercise1.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3

class Helicopter(x : Float, y : Float) {
    //private val GRAVITY : Int = -15

    private var position : Vector3 = Vector3(x, y,0F)
    private var velocity : Vector3 = Vector3(0F, 0F, 0F)
    private var heli : Texture = Texture("heli1.png")

    fun update(dt: Float){
        //velocity.add(0F, 0F, 0F)
        velocity.scl(dt)
        position.add(0F, velocity.y, 0F)
        velocity.scl(1/dt)
    }

    fun getPosition(): Vector3{
        return position
    }

    fun getTexture(): Texture{
        return heli
    }

    fun fly(x: Float, y: Float){
        velocity.add(x, y, 0F)
    }

}