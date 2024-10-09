package com.example.NoSlownessSprintJump;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.bukkit.plugin.java.JavaPlugin;

public class NoSlownessSprintJump extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoSlownessSprintJump Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoSlownessSprintJump Plugin disabled!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.hasPotionEffect(PotionEffectType.SLOW)) {
            var slownessEffect = player.getPotionEffect(PotionEffectType.SLOW);
            int slownessLevel = slownessEffect.getAmplifier();
            if (event.getFrom().getY() < event.getTo().getY()) {
                double reductionFactor = reductionFactor(slownessLevel);
                Vector velocity = player.getVelocity();
                velocity.setX(velocity.getX() * reductionFactor);
                velocity.setZ(velocity.getZ() * reductionFactor);
                player.setVelocity(velocity);
            }
        }
    }

    private double reductionFactor(int slownessLevel) {
        return 1.0 - (0.15 * (slownessLevel + 1));
    }
}