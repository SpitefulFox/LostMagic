package fox.spiteful.lostmagic;

import baubles.api.BaublesApi;
import fox.spiteful.lostmagic.items.LostItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

public class LostEvents {

    @SubscribeEvent
    public void onEat(LivingEntityUseItemEvent.Finish event){
        if(!Config.ringNutrition)
            return;
        if(event.getEntityLiving() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)event.getEntityLiving();
            if(event.getItem().getItem() instanceof ItemFood) {
                if(player instanceof EntityPlayerMP) {
                    ItemFood food = (ItemFood) event.getItem().getItem();
                    IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
                    if(!knowledge.isResearchComplete("RINGNUTRITION") && knowledge.isResearchKnown("RINGNUTRITION@1")) {
                        if (!knowledge.isResearchKnown("f_badfood") && (food.getSaturationModifier(event.getItem()) * food.getHealAmount(event.getItem()) * 2.0f) < 0.5f) {
                            knowledge.addResearch("f_badfood");
                            knowledge.sync((EntityPlayerMP) player);
                            player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.translateToLocal("got.lm_badfood")), true);
                        }
                        if (!knowledge.isResearchKnown("f_goodfood") && (food.getSaturationModifier(event.getItem()) * food.getHealAmount(event.getItem()) * 2.0f) > 7.0f) {
                            knowledge.addResearch("f_goodfood");
                            knowledge.sync((EntityPlayerMP) player);
                            player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.translateToLocal("got.lm_goodfood")), true);
                        }
                    }
                }

                int slot = BaublesApi.isBaubleEquipped(player, LostItems.ringNutrition);
                if (slot == 1 || slot == 2) {
                    player.getFoodStats().addStats(2, 1.0F);
                }
            }
        }
    }

}
