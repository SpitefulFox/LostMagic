package fox.spiteful.lostmagic.research;

import fox.spiteful.lostmagic.Config;
import fox.spiteful.lostmagic.LostMagic;
import fox.spiteful.lostmagic.items.LostItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;

public class LostResearch {


    public static void findResearch(){

        ResearchCategories.registerCategory("APOCRYPHA", null, (new AspectList()).add(Aspect.LIGHT, 5).add(Aspect.ELDRITCH, 5).add(Aspect.AURA, 5).add(Aspect.MAGIC, 5).add(Aspect.DARKNESS, 5).add(Aspect.FLUX, 3).add(Aspect.MIND, 5), new ResourceLocation("lostmagic", "textures/misc/forbidden.png"), new ResourceLocation("lostmagic", "textures/misc/runecircle2.png"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("lostmagic", "research/apocrypha"));
        if(Config.ringNutrition)
            ThaumcraftApi.registerResearchLocation(new ResourceLocation("lostmagic", "research/gluttony"));
    }

    public static void findRecipes(){
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("lostmagic:PureShovel"), new InfusionRecipe("PURESHOVEL", new ItemStack(LostItems.shovelPurifier), 2, (new AspectList()).add(Aspect.LIGHT, 60).add(Aspect.TOOL, 30), new ItemStack(ItemsTC.thaumiumShovel, 1, 32767), new Object[]{LostMagic.getCrystal(Aspect.AURA, 1), LostMagic.getCrystal(Aspect.AURA, 1), new ItemStack(ItemsTC.quicksilver), new ItemStack(BlocksTC.logSilverwood)}));
        if(Config.ringNutrition)
            ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("lostmagic:RingNutrition"), new InfusionRecipe("RINGNUTRITION", new ItemStack(LostItems.ringNutrition), 1, (new AspectList()).add(Aspect.LIFE, 30).add(Aspect.ALCHEMY, 30).add(Aspect.ENERGY, 30), new ItemStack(ItemsTC.baubles, 1, 5), new Object[]{LostMagic.getCrystal(Aspect.ALCHEMY, 1), new ItemStack(Items.GOLDEN_APPLE), new ItemStack(Items.GOLDEN_CARROT)}));
    }

}
