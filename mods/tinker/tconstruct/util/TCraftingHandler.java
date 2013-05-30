package mods.tinker.tconstruct.util;

import mods.tinker.tconstruct.TConstruct;
import mods.tinker.tconstruct.common.TContent;
import mods.tinker.tconstruct.library.tools.AbilityHelper;
import mods.tinker.tconstruct.util.player.TPlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.ICraftingHandler;

public class TCraftingHandler implements ICraftingHandler
{

    @Override
    public void onCrafting (EntityPlayer player, ItemStack item, IInventory craftMatrix)
    {
        if (!player.worldObj.isRemote)
        {
            int itemID = item.getItem().itemID;
            if (itemID == TContent.toolStationWood.blockID)
            {
            	TPlayerStats stats = TConstruct.playerTracker.getPlayerStats(player.username);
                NBTTagCompound tags = player.getEntityData().getCompoundTag("TConstruct");
                if (!tags.getBoolean("materialManual") || !stats.materialManual)
                {
                	stats.materialManual = true;
                    tags.setBoolean("materialManual", true);
                    AbilityHelper.spawnItemAtPlayer(player, new ItemStack(TContent.manualBook, 1, 1));
                }
            }
            if (itemID == TContent.smeltery.blockID || itemID == TContent.lavaTank.blockID)
            {
            	TPlayerStats stats = TConstruct.playerTracker.getPlayerStats(player.username);
                NBTTagCompound tags = player.getEntityData().getCompoundTag("TConstruct");
                if (!tags.getBoolean("smelteryManual") || !stats.smelteryManual)
                {
                	stats.smelteryManual = true;
                    tags.setBoolean("smelteryManual", true);
                    AbilityHelper.spawnItemAtPlayer(player, new ItemStack(TContent.manualBook, 1, 2));
                }
            }
        }
    }

    @Override
    public void onSmelting (EntityPlayer player, ItemStack item)
    {
    }

}
