package com.github.shynixn.blockball.api.persistence.entity

import com.github.shynixn.ball.api.business.entity.Ball
import com.github.shynixn.blockball.api.business.enumeration.GameStatus
import com.github.shynixn.blockball.api.business.proxy.HologramProxy

/**
 * Created by Shynixn 2018.
 * <p>
 * Version 1.2
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2018 by Shynixn
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
interface Game {
    /**
     *  Arena of the game.
     */
    val arena: Arena

    /**
     * Is the game closed.
     */
    var closed: Boolean

    /**
     * Marks the game for being closed and will automatically
     * switch to close state once the resources are cleard.
     */
    var closing: Boolean

    /**
     * RedScore.
     */
    var redScore: Int

    /**
     * Status.
     */
    var status: GameStatus

    /**
     * Ingame scoreboard.
     */
    var scoreboard: Any?

    /**
     * Ingame bossbar.
     */
    var bossBar: Any?

    /**
     * Ingame holograms.
     */
    val holograms: MutableList<HologramProxy>

    /**
     * Contains players which are in cooldown by doublejump.
     */
    val doubleJumpCoolDownPlayers: MutableMap<Any, Int>

    /**
     * Ball.
     */
    var ball: Ball<*, *, *>?

    /**
     * Blue Score.
     */
    var blueScore: Int

    /**
     * Are currently players actively playing in this game?
     */
    var playing: Boolean

    /**
     * All players associated with the game. Regardless if playing, lobby, spectating etc.
     */
    val inGamePlayers: List<Any>

    /**
     * The last interacted entity with the ball. Can also be a non player.
     */
    val lastInteractedEntity: Any?

    /**
     * Storage for the [inGamePlayers].
     */
    val ingamePlayersStorage: MutableMap<Any, GameStorage>

    /**
     * List of players which are already in the [redTeam] or [blueTeam].
     */
    val inTeamPlayers: List<Any>

    /**
     * List of players in the world of this game which are not [inGamePlayers]
     * with their boolean flag if they are nearby to receive notifications.
     * Returns an empty list if notifying is disabled.
     */
    val notifiedPlayers: List<Pair<Any, Boolean>>

    /**
     * All players which are already fix in team red.
     */
    val redTeam: List<Any>

    /**
     * All players which are already fix in team blue.
     */
    val blueTeam: List<Any>
}