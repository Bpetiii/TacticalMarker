package biraw.online.b_s_TacticalMarker.Utils;

import biraw.online.b_s_TacticalMarker.B_s_TacticalMarker;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;


public class MarkerUtils {

    /*-----------+/
        MARKERS
    /+-----------*/

    // Puts a mark on a location
    public static BlockDisplay markBlock(Location location){
        // Spawn the marker
        BlockDisplay marker = location.getWorld().spawn(location, BlockDisplay.class);

        // Set the block type (material)
        marker.setBlock(Material.GLASS.createBlockData());

        // Create the transformation
        Vector3f translation = new Vector3f(0, 0, 0);
        AxisAngle4f rotation = new AxisAngle4f(0, 0, 0, 0);
        Vector3f scale = new Vector3f(0.998f, 0.998f, 0.998f);
        Transformation transform = new Transformation(translation, rotation, scale, rotation);

        // Apply the transformation
        marker.setTransformation(transform);

        // Create the appearance and details of the marker;
        marker.setInvisible(true);
        marker.setInvulnerable(true);
        marker.setPersistent(true);
        marker.setGlowing(true);
        marker.setGlowColorOverride(Color.WHITE);

        return marker;
    }

    public static Entity markEntity(Entity entity)
    {
        entity.setGlowing(true);
        return entity;
    }



    /*-------------+/
        RAYCASTS
    /+-------------*/

    // Get the block that the player is looking at
    public static Block raycastBlock(Player player, double maxDistance) {
        // Get necessary variables
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        // Perform the ray trace and get the hit block
        Block hitBlock = eyeLocation.getWorld()
                .rayTraceBlocks(eyeLocation, direction, maxDistance).getHitBlock();

        return hitBlock;
    }
    public static double raycastBlockDistance(Player player, double maxDistance) {
        // Get necessary variables
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        // Perform the ray trace and get the hit block
        RayTraceResult rtr =eyeLocation.getWorld()
                .rayTraceBlocks(eyeLocation, direction, maxDistance);
        if (rtr == null) return Double.MAX_VALUE; // Failsafe...
        Block hitBlock = rtr.getHitBlock();

        // Failsafe for if the player clicks on nothing
        if (hitBlock == null) return Double.MAX_VALUE;

        return hitBlock.getLocation().distance(player.getEyeLocation());
    }

    // Get the entity that the player is looking at
    public static Entity raycastEntity(Player player, double maxDistance) {
        // Get necessary variables
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        // Perform the ray trace and get the hit block
        Entity hitEntity = eyeLocation.getWorld()
                .rayTraceEntities(eyeLocation, direction, maxDistance,entity -> !entity.equals(player)).getHitEntity();

        return hitEntity;
    }
    public static double raycastEntityDistance(Player player, double maxDistance) {
        // Get necessary variables
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        // Perform the ray trace and get the hit block
        RayTraceResult rtr = eyeLocation.getWorld()
                .rayTraceEntities(eyeLocation, direction, maxDistance,entity -> !entity.equals(player));
        if (rtr == null) return Double.MAX_VALUE; // Failsafe...
        Entity hitEntity = rtr.getHitEntity();

        // Failsafe for if the player clicks on nothing
        if (hitEntity == null) return Double.MAX_VALUE;

        return hitEntity.getLocation().distance(player.getEyeLocation());
    }

    /*------------+/
         DECAYS
    /+------------*/


    public static void markerDecayEntity(Entity entity, double time){
        Bukkit.getScheduler().runTaskLater(B_s_TacticalMarker.getPlugin(),() -> {
            entity.setGlowing(false);
        }, (long) (20*time) ); // 20*time Tick = time Sec
    }

    public static void markerDecayBlock(BlockDisplay block, double time){
        Bukkit.getScheduler().runTaskLater(B_s_TacticalMarker.getPlugin(),() -> {
            block.remove();
        }, (long) (20*time) ); // 20*time Tick = time Sec
    }

}
