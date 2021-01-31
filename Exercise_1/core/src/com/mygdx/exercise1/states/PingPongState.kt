package com.mygdx.exercise1.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.exercise1.sprites.Ball
import com.mygdx.exercise1.sprites.Paddle
import kotlin.random.Random

class PingPongState(gsm: GameStateManager) : State(gsm) {
    private var ball : Ball = Ball()
    private var player : Paddle = Paddle(50F, Gdx.graphics.height/4.toFloat())
    private var bot : Paddle = Paddle(Gdx.graphics.width/2 - 50F, Gdx.graphics.height/4.toFloat())
    private var hits : Int = 0
    private var score : Array<Int> = arrayOf(0, 0)
    private var font = BitmapFont()
    private val maxScore : Int = 21

    init {
        cam.setToOrtho(false,
                Gdx.graphics.width / 2.toFloat(),
                Gdx.graphics.height / 2.toFloat())
        font.data.setScale(3F)
    }

    override fun handleInput() {
        if(Gdx.input.isTouched){
            val posY =  Gdx.graphics.height - Gdx.input.y.toFloat()
            player.setPos(posY - player.getSprite().height/2)
        }
    }

    override fun update(dt: Float) {
        handleInput()
        botMove()
        player.update()
        bot.update()
        checkHit()
        ball.update(dt)
        checkGoal()
    }

    private fun checkHit(){
        if (ball.getHitbox().overlaps(player.getHitbox()) && ball.headLeft()){
            ball.flip(player.getVelocity())
        }
        else if(ball.getHitbox().overlaps(bot.getHitbox()) && !ball.headLeft()){
            ball.flip(bot.getVelocity())
        } else{
            return
        }
        adjustPaddles(++hits)
    }

    private fun checkGoal(){
        when{
            ball.getX() < 0F -> goal(bot)
            ball.getX() > Gdx.graphics.width/2  -> goal(player)
        }
    }

    private fun goal(scorer : Paddle){
        if (scorer == player){
            score[0]++
        }
        else if (scorer == bot){
            score[1]++
        }
        nextRound()
    }

    private fun nextRound(){
        if (maxScore in score){
            var winner = "The bot"
            if (score[0] == maxScore){
                winner = "You"
            }
            gsm.set(GameOverState(gsm, winner))
            dispose()
            return
        }
        hits = 0
        ball.reset()
        bot.reset()
        player.reset()
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        player.draw(sb)
        bot.draw(sb)
        ball.draw(sb)
        font.draw(sb,
                "${score[0]}",
                Gdx.graphics.width/4.toFloat() - 200,
                Gdx.graphics.height/2.toFloat() - 100)
        font.draw(sb,
                "${score[1]}",
                Gdx.graphics.width/4.toFloat() + 200,
                Gdx.graphics.height/2.toFloat() - 100)
        sb.end()
    }

    private fun botMove(){
        val lag = Random.nextInt(0, 10) > 4
        val error = Random.nextInt(-70, 70)
        if (!lag){
            bot.setPos(ball.getY() + error)
        }

    }

    private fun adjustPaddles(hits: Int){
        if (hits % 15 == 0){
            player.shrink()
            bot.shrink()
        }
        if (hits % 5 == 0){
            player.increaseSpeed()
            bot.increaseSpeed()
        }
    }

    override fun dispose() {
        player.dispose()
        bot.dispose()
        ball.dispose()
    }

}