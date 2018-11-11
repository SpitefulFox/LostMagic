package fox.spiteful.lostmagic.compat;

import fox.spiteful.lostmagic.Config;
import fox.spiteful.lostmagic.Lumberjack;
import fox.spiteful.lostmagic.items.ItemScribeBlood;
import fox.spiteful.lostmagic.items.LostItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

public class Compat {

    public static boolean bloodMagic = false;

    public static void check(){
        bloodMagic = Config.bloodMagic && Loader.isModLoaded("bloodmagic");
    }

    public static void preCompatify(){
        if(bloodMagic){
            try {
                LostItems.scribeBlood = new ItemScribeBlood();
                ForgeRegistries.ITEMS.register(LostItems.scribeBlood);
            }
            catch(Throwable e){
                bloodMagic = false;
                Lumberjack.log(Level.INFO, e, "Lost Magic bled out.");
            }
        }
    }

    public static void compatify(){
        if(bloodMagic){
            try {
                ThaumcraftApi.registerResearchLocation(new ResourceLocation("lostmagic", "research/bloodmagic"));
                ItemStack weakOrb = new ItemStack(getItem("bloodmagic", "blood_orb"));
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("orb", "bloodmagic:weak");
                weakOrb.setTagCompound(tag);

                ItemStack bucketBlood = FluidUtil.getFilledBucket(FluidRegistry.getFluidStack("lifeessence", 1000));

                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("lostmagic:BloodScribe"), new ShapelessArcaneRecipe(new ResourceLocation(""), "BLOODSCRIBE", 50, (new AspectList()).add(Aspect.WATER, 1).add(Aspect.EARTH, 1), new ItemStack(LostItems.scribeBlood), new Object[]{ new ItemStack(Items.FEATHER), bucketBlood, weakOrb, new ItemStack(Items.GLASS_BOTTLE) }));
            }
            catch(Throwable e){
                bloodMagic = false;
                Lumberjack.log(Level.INFO, e, "Lost Magic bled out.");
            }
        }
    }

    public static Item getItem(String mod, String item) throws ItemNotFoundException {
        Item target = ForgeRegistries.ITEMS.getValue(new ResourceLocation(mod + ":" + item));
        if(target == null)
            throw new ItemNotFoundException(mod, item);
        return target;
    }

    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String mod, String item){
            super("Unable to find item " + item + " in mod " + mod + "! Are you using the correct version of the mod?");
        }
    }

}
