package fox.spiteful.lostmagic.client;

import fox.spiteful.lostmagic.CommonProxy;
import fox.spiteful.lostmagic.items.LostItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

    @Override
    public void doTheRenderThing(){
        ModelLoader.setCustomModelResourceLocation(LostItems.shovelPurifier, 0, new ModelResourceLocation(LostItems.shovelPurifier.getRegistryName(), "inventory"));
        if(LostItems.scribeBlood != null)
            ModelLoader.setCustomModelResourceLocation(LostItems.scribeBlood, 0, new ModelResourceLocation(LostItems.scribeBlood.getRegistryName(), "inventory"));
    }
}
