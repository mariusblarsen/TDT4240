package com.mygdx.exercise1.sprites

import com.badlogic.gdx.graphics.Texture

class Animation {
    private var frame : Int = 0
    private val frameCount : Int = 4
    private val maxFrameTime : Float = 0.100F
    private var currentFrameTime : Float = 0F
    private var frames : Array<String> = arrayOf("heli1.png", "heli2.png", "heli3.png", "heli4.png")

    fun update(dt: Float){
        currentFrameTime += dt
        if(currentFrameTime > maxFrameTime){
            frame++
            currentFrameTime = 0F
        }
        if (frame >= frameCount){
            frame = 0
        }
    }

    fun getFrame(): Texture{
        return Texture(frames[frame])
    }



}