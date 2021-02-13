package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.exercise1.sprites.AnimationHelicopter
import kotlin.random.Random

class AnimationState(gsm : GameStateManager) : State(gsm) {
    private var startX = Gdx.graphics.width
    private var startY = Gdx.graphics.height / 4.toFloat()
    private var heli : AnimationHelicopter = AnimationHelicopter(startX/6.toFloat(), startY)
    private var heli2 : AnimationHelicopter = AnimationHelicopter(startX/3.toFloat(), startY)

    init {
        cam.setToOrtho(false,
                Gdx.graphics.width / 2.toFloat(),
                Gdx.graphics.height / 2.toFloat())
        val velX = Random.nextInt(-100,100).toFloat()
        val velY = Random.nextInt(-100, 100).toFloat()
        heli.fly(velX, velY)
        heli.setHeading()

        val velX2 = Random.nextInt(-100,100).toFloat()
        val velY2 = Random.nextInt(-100, 100).toFloat()
        heli2.fly(velX2, velY2)
        heli2.setHeading()
    }

    override fun update(dt: Float) {
        handleInput()
        collision()
        heli.update(dt)
        heli2.update(dt)
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(heli.getSprite(), heli.getPosition().x, heli.getPosition().y)
        sb.draw(heli2.getSprite(), heli2.getPosition().x, heli2.getPosition().y)
        sb.end()
    }

    fun collision(){
        if(heli.getHitbox().overlaps(heli2.getHitbox())){
            var vel = Random.nextInt(50,150).toFloat()
            val heliPos = heli.getPosition()
            val heli2Pos = heli2.getPosition()
            val heliVel = heli.getVelocity()
            val heli2Vel = heli2.getVelocity()
            if (heliPos.x > heli2Pos.x){
                heli.fly(vel, heliVel.y)
                heli2.fly(-vel, heli2Vel.y)
            }else{
                heli.fly(-vel, heliVel.y)
                heli2.fly(vel, heli2Vel.y)
            }
            if (heliPos.y > heli2Pos.y){
                heli.fly(heliVel.x, vel)
                heli2.fly(heli2Vel.x, -vel)
            }else{
                heli.fly(heliVel.x, -vel)
                heli2.fly(heli2Vel.y, vel)
            }
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(MenuState(gsm))
            dispose()
        }
    }

    override fun dispose() {
        heli.dispose()
        heli2.dispose()
    }
}