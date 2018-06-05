package com.themastergeneral.ctdmythos.integration.imc;

import java.util.List;
import java.util.Locale;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;
import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;

public class IMCHandler
{
    public static final IMCHandler INSTANCE = new IMCHandler();

    public void handleIMC(List<IMCMessage> messages)
    {
        NBTTagCompound nbt;
        for (IMCMessage message : messages)
        {
            try
            {
                if (!message.isNBTMessage())
                {
                    continue;
                }
                nbt = message.getNBTValue();
                String operation = message.key.toLowerCase(Locale.US);

                switch (operation)
                {
                case ADD_FLIGHT_ITEM:
                    WandFlightItems.instance().addFlight(
                            new ItemStack(nbt.getCompoundTag(INPUT)),
                            nbt.getInteger(FLIGHTTIME),
                            nbt.getInteger(FLIGHTMOD),
                            nbt.getInteger(RESISTANCETIME));
                    continue;
                case REMOVE_FLIGHT_ITEM:
                    WandFlightItems.instance().removeFlight(
                            new ItemStack(nbt.getCompoundTag(INPUT)));
                    continue;
                case ADD_MB_CRAFT:
                    MultiblockRecipes.instance().addRecipe(
                            new ItemStack(nbt.getCompoundTag(INPUT)),
                            new ItemStack(nbt.getCompoundTag(OUTPUT)));
                    continue;
                case REMOVE_MB_CRAFT:
                    MultiblockRecipes.instance().removeRecipe(
                            new ItemStack(nbt.getCompoundTag(INPUT)),
                            new ItemStack(nbt.getCompoundTag(OUTPUT)));
                    continue;
                }
                CTDMythos.logger
                        .warn("CTD Mythos received an invalid IMC from "
                                + message.getSender() + "! Key was "
                                + message.key);
            }
            catch (Exception e)
            {
                CTDMythos.logger.warn("CTD Mythos received a broken IMC from "
                        + message.getSender() + "!");
                e.printStackTrace();
            }
        }
    }

    /*
     * IMC Strings
     */
    static final String INPUT = "input";
    static final String OFFHAND = "offhand";
    static final String FLIGHTTIME = "flighttime";
    static final String RESISTANCETIME = "resistance";
    static final String OUTPUT = "output";
    static final String FLIGHTMOD = "flightmod";

    public static final String ADD_FLIGHT_ITEM = "add_flight_wand_item";
    public static final String REMOVE_FLIGHT_ITEM = "remove_flight_wand_item";

    public static final String ADD_MB_CRAFT = "add_mb_craft";
    public static final String REMOVE_MB_CRAFT = "remove_mb_craft";
}
