package mods.eln.node;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.EnumSet;

import mods.eln.Eln;
import mods.eln.sim.ElectricalConnection;
import mods.eln.sim.ElectricalLoad;
import mods.eln.sim.IProcess;
import mods.eln.sim.ThermalConnection;
import mods.eln.sim.ThermalLoad;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;


public class NodeClient{
	public static final ArrayList<NodeBlockEntity> nodeNeedRefreshList = new ArrayList<NodeBlockEntity>();	

	public NodeClient()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	public void init()
	{
		nodeNeedRefreshList.clear();
	}
	public void stop()
	{
		nodeNeedRefreshList.clear();
	}
	
	public static final int refreshDivider = 5;
	public int refreshCounter = 0;
	@SubscribeEvent
	public void tick(ClientTickEvent event) {
		if(event.phase != Phase.START) return;
		/*
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (server == null) return;
        if(refreshCounter++ < refreshDivider) return;

    	try{
			refreshCounter = 0;
			
    	    
	    	EntityClientPlayerMP player =  Minecraft.getMinecraft().thePlayer;
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream stream = new DataOutputStream(bos);   	

            stream.writeByte(Eln.packetNodeRefreshRequest);
            
			int x,y,z;
			x = (int)player.posX;
			y = (int)player.posY;
			z = (int)player.posZ;


			
			stream.writeInt(x);
			stream.writeInt(y);
			stream.writeInt(z);
			

		    for (NodeBlockEntity node : NodeBlockEntity.nodeAddedList)
		    {
		    	stream.writeShort((short) (node.xCoord - x));
		    	stream.writeShort((short) (node.yCoord - y));
		    	stream.writeShort((short) (node.zCoord - z));
		    }

		    Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = Eln.channelName;
            packet.data = bos.toByteArray();
            packet.length = bos.size();
            	    	
	    	PacketDispatcher.sendPacketToServer(packet);
	    	
	    	nodeNeedRefreshList.clear();
   
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
	}

	
}
