package fox.spiteful.lostmagic.items;

import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import WayofTime.bloodmagic.util.helper.TextHelper;
import fox.spiteful.lostmagic.LostMagic;
import fox.spiteful.lostmagic.compat.Compat;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IScribeTools;
import WayofTime.bloodmagic.iface.IBindable;

import java.util.List;


public class ItemScribeBlood extends Item implements IScribeTools, IBindable {

    public ItemScribeBlood(){
        this.setCreativeTab(LostMagic.tab);
        this.setRegistryName("scribe_blood");
        this.setUnlocalizedName("lostmagic.scribe_blood");
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setHasSubtypes(false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        if(!Compat.bloodMagic)
            return;

        if (!stack.hasTagCompound())
            return;

        Binding binding = getBinding(stack);
        if (binding != null)
            tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.currentOwner", binding.getOwnerName()));
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity holder, int itemSlot, boolean isSelected) {
        if(!Compat.bloodMagic)
            return;
        if(stack.getItemDamage() > 0){
            Binding binding = getBinding(stack);
            if (binding != null) {
                if(NetworkHelper.getSoulNetwork(binding).syphon(SoulTicket.item(stack, world, holder, 25)) > 0){
                    stack.setItemDamage(stack.getItemDamage() - 1);
                }
            }
        }
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        if(!Compat.bloodMagic)
            return;
        Binding binding = getBinding(stack);
        if (binding != null) {
            if(NetworkHelper.getSoulNetwork(binding).syphon(SoulTicket.item(stack, 25 * damage)) > 0){
                super.setDamage(stack, 0);
            }
            else
                super.setDamage(stack, damage);
        }
        else
            super.setDamage(stack, damage);
    }
}
