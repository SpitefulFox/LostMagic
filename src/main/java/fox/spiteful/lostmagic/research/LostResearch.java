package fox.spiteful.lostmagic.research;

import fox.spiteful.lostmagic.LostMagic;
import fox.spiteful.lostmagic.items.LostItems;
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
    }

    public static void findRecipes(){
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("lostmagic:PureShovel"), new InfusionRecipe("PURESHOVEL", new ItemStack(LostItems.shovelPurifier), 1, (new AspectList()).add(Aspect.LIGHT, 60).add(Aspect.TOOL, 30), new ItemStack(ItemsTC.thaumiumShovel, 1, 32767), new Object[]{LostMagic.getCrystal(Aspect.AURA, 1), LostMagic.getCrystal(Aspect.AURA, 1), new ItemStack(ItemsTC.quicksilver), new ItemStack(BlocksTC.logSilverwood)}));
    }

}
