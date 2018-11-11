package fox.spiteful.lostmagic;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import org.apache.logging.log4j.Level;

public class Config {

    public static boolean bloodMagic = true;

    public static void configurate(File targ)
    {
        Configuration conf = new Configuration(targ);

        try
        {
            conf.load();

            bloodMagic = conf.get("Compatibility", "Blood Magic", bloodMagic, "Disable to disable Blood Magic compatibility").getBoolean(true);

        }
        catch (Exception e)
        {
            Lumberjack.log(Level.ERROR, e, "Lost Magic was unable to load its config.");
        }
        finally
        {
            conf.save();
        }

    }
}