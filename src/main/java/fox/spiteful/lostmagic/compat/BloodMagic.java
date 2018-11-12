package fox.spiteful.lostmagic.compat;

import fox.spiteful.lostmagic.items.ItemScribeBlood;
import fox.spiteful.lostmagic.items.LostItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

public class BloodMagic {

    public static void preRitual(){
        LostItems.scribeBlood = new ItemScribeBlood();
        ForgeRegistries.ITEMS.register(LostItems.scribeBlood);
    }

    public static void ritual() throws Compat.ItemNotFoundException {
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("lostmagic", "research/bloodmagic"));
        ItemStack weakOrb = new ItemStack(Compat.getItem("bloodmagic", "blood_orb"));
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("orb", "bloodmagic:weak");
        weakOrb.setTagCompound(tag);

        ItemStack bucketBlood = FluidUtil.getFilledBucket(FluidRegistry.getFluidStack("lifeessence", 1000));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("lostmagic:BloodScribe"), new ShapelessArcaneRecipe(new ResourceLocation(""), "BLOODSCRIBE", 50, (new AspectList()).add(Aspect.WATER, 1).add(Aspect.EARTH, 1), new ItemStack(LostItems.scribeBlood), new Object[]{ new ItemStack(Items.FEATHER), bucketBlood, weakOrb, new ItemStack(Items.GLASS_BOTTLE) }));
    }

}
