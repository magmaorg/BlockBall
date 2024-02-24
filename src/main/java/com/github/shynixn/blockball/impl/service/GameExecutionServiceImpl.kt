package com.github.shynixn.blockball.impl.service

import com.github.shynixn.blockball.contract.GameExecutionService
import com.github.shynixn.blockball.contract.ProxyService
import com.github.shynixn.blockball.entity.Game
import com.github.shynixn.blockball.enumeration.Team
import com.google.inject.Inject
import org.bukkit.entity.Player

class GameExecutionServiceImpl @Inject constructor(private val proxyService: ProxyService) : GameExecutionService {
    /**
     * Applies points to the belonging teams when the given [player] dies in the given [game].
     */
    override fun <G : Game> applyDeathPoints(game: G, player: Player) {
        if (!game.ingamePlayersStorage.containsKey(player)) {
            return
        }

        val team = game.ingamePlayersStorage[player]!!.team

        if (team == Team.RED) {
            game.blueScore += game.arena.meta.blueTeamMeta.pointsPerEnemyDeath
        } else {
            game.redScore += game.arena.meta.redTeamMeta.pointsPerEnemyDeath
        }
    }

    /**
     * Lets the given [player] in the given [game] respawn at the specified spawnpoint.
     */
    override fun <G : Game> respawn(game: G, player: Player) {
        if (!game.ingamePlayersStorage.containsKey(player)) {
            return
        }

        val team = game.ingamePlayersStorage[player]!!.goalTeam

        val teamMeta = if (team == Team.RED) {
            game.arena.meta.redTeamMeta
        } else {
            game.arena.meta.blueTeamMeta
        }

        if (teamMeta.spawnpoint == null) {
            proxyService.setPlayerLocation(player, game.arena.meta.ballMeta.spawnpoint!!)
        } else {
            proxyService.setPlayerLocation(player, teamMeta.spawnpoint!!)
        }
    }
}
