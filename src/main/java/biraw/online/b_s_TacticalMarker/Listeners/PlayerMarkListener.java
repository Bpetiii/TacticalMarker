package biraw.online.b_s_TacticalMarker.Listeners;

import biraw.online.b_s_TacticalMarker.Utils.MarkerUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerMarkListener implements Listener {
    @EventHandler
    public void playerMarkListener(PlayerInteractEvent event)
    {

        // Get the player and world
        Player player = event.getPlayer();
        World world = player.getWorld();


        // Check if it's a valid mark
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.COMPASS) return;

        // Get the distances
        double block_distance = MarkerUtils.raycastBlockDistance(player,16*(player.getViewDistance()-1));
        double entity_distance = MarkerUtils.raycastEntityDistance(player,16*(player.getViewDistance()-1));

        if ( block_distance < entity_distance && block_distance != Double.MAX_VALUE)
        {
            // Get the location where the mark will be set.
            Location marked_location = MarkerUtils.raycastBlock(player,16*(player.getViewDistance()-1)).getLocation();
            marked_location = marked_location.add(0.001f,0.001f,0.001f);

            BlockDisplay marker = MarkerUtils.markBlock(marked_location);

            MarkerUtils.markerDecayBlock(marker,5);
        }
        else if ( block_distance >= entity_distance && entity_distance != Double.MAX_VALUE)
        {
            Entity marked_entity = MarkerUtils.raycastEntity(player,16*(player.getViewDistance()-1));
            MarkerUtils.markEntity(marked_entity);
            MarkerUtils.markerDecayEntity(marked_entity,5);
        }
    }
}
