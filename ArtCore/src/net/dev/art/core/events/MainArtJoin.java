package net.dev.art.core.events;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityItem;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;

public class MainArtJoin implements Listener{

	private void sendItem(Player player, ItemStack item) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();

        EntityItem entityItem = new EntityItem(((CraftWorld)player.getWorld()).getHandle());
        entityItem.setItemStack(CraftItemStack.asNMSCopy(item));
        entityItem.setPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

        PacketPlayOutSpawnEntity itemPacket = new PacketPlayOutSpawnEntity(entityItem, 2, 1);
        entityPlayer.playerConnection.sendPacket(itemPacket);

        PacketPlayOutEntityMetadata meta = new PacketPlayOutEntityMetadata(entityItem.getId(), entityItem.getDataWatcher(), true);
        entityPlayer.playerConnection.sendPacket(meta);
       
        Location loc = new Location(((CraftItem)entityItem.getBukkitEntity()).getWorld(), entityItem.locX, entityItem.locY, entityItem.locZ);
       
        final EntityArmorStand e2 = new EntityArmorStand(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ());
        e2.setSmall(true);
        e2.setInvisible(true);
       
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInt("DisabledSlot", 2039589); // All slots are disabled to prevent client glitches
       
        e2.c(tag);
       
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(e2));
       
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutAttachEntity(0, entityItem, e2));
    }

	
}
