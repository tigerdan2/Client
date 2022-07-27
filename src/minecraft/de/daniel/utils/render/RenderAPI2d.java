package de.daniel.utils.render;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class RenderAPI2d {

    public RenderAPI2d() {
        maps.add(upperPos1);
        maps.add(lowerPos1);
        maps.add(lowerPos2);
        maps.add(upperPos2);
        maps.add(lowerPos3);
        maps.add(upperPos4);
        maps.add(lowerPos4);
    }

    public HashMap<Entity, float[]> upperPos1 = new HashMap<>();
    public HashMap<Entity, float[]> lowerPos1 = new HashMap<>();
    public HashMap<Entity, float[]> upperPos2 = new HashMap<>();
    public HashMap<Entity, float[]> lowerPos2 = new HashMap<>();
    public HashMap<Entity, float[]> upperPos3 = new HashMap<>();
    public HashMap<Entity, float[]> lowerPos3 = new HashMap<>();
    public HashMap<Entity, float[]> upperPos4 = new HashMap<>();
    public HashMap<Entity, float[]> lowerPos4 = new HashMap<>();
    public java.util.ArrayList<HashMap<Entity, float[]>> maps = new java.util.ArrayList<>();
    public boolean shouldConvert2d;
    public ScaledResolution sr;
    public void on3dRender() {



        if(!this.shouldConvert2d){
            return;
        }else{
            this.shouldConvert2d = false;
        }

        for(HashMap m : this.maps){
            m.clear();
        }

        for (Object objectEntity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            Entity entity = (Entity) objectEntity;
            if (entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().thePlayer) {
                double pX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) Minecraft.getMinecraft().timer.renderPartialTicks - RenderManager.renderPosX;
                double pY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) Minecraft.getMinecraft().timer.renderPartialTicks - RenderManager.renderPosY;
                double pZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) Minecraft.getMinecraft().timer.renderPartialTicks - RenderManager.renderPosZ;
                convert2d(entity, pX, pY, pZ);
            }
        }
    }

    public float[] getBoundingBoxes(Entity e) {
        if (this.upperPos1.containsKey(e) && (upperPos1.get(e)[2] >= 0 && upperPos1.get(e)[2] < 1) ) {
            float xu1 = upperPos1.get(e)[0];
            float yu1 = upperPos1.get(e)[1];
            float xu2 = upperPos2.get(e)[0];
            float yu2 = upperPos2.get(e)[1];
            float xu3 = upperPos3.get(e)[0];
            float yu3 = upperPos3.get(e)[1];
            float xu4 = upperPos4.get(e)[0];
            float yu4 = upperPos4.get(e)[1];
            float xl1 = lowerPos1.get(e)[0];
            float yl1 = lowerPos1.get(e)[1];
            float xl2 = lowerPos2.get(e)[0];
            float yl2 = lowerPos2.get(e)[1];
            float xl3 = lowerPos3.get(e)[0];
            float yl3 = lowerPos3.get(e)[1];
            float xl4 = lowerPos4.get(e)[0];
            float yl4 = lowerPos4.get(e)[1];
            float highestY = -Integer.MAX_VALUE;
            float lowestY = Integer.MAX_VALUE;
            float highestX = -Integer.MAX_VALUE;
            float lowestX = Integer.MAX_VALUE;
            //HIGHESTX;
            if (xu1 > highestX) {
                highestX = xu1;
            }
            if (xu2 > highestX) {
                highestX = xu2;
            }
            if (xu3 > highestX) {
                highestX = xu3;
            }
            if (xu4 > highestX) {
                highestX = xu4;
            }
            if (xl1 > highestX) {
                highestX = xl1;
            }
            if (xl2 > highestX) {
                highestX = xl2;
            }
            if (xl3 > highestX) {
                highestX = xl3;
            }
            if (xl4 > highestX) {
                highestX = xl4;
            }
            //LOWESTX
            if (xu1 < lowestX) {
                lowestX = xu1;
            }
            if (xu2 < lowestX) {
                lowestX = xu2;
            }
            if (xu3 < lowestX) {
                lowestX = xu3;
            }
            if (xu4 < lowestX) {
                lowestX = xu4;
            }
            if (xl1 < lowestX) {
                lowestX = xl1;
            }
            if (xl2 < lowestX) {
                lowestX = xl2;
            }
            if (xl3 < lowestX) {
                lowestX = xl3;
            }
            if (xl4 < lowestX) {
                lowestX = xl4;
            }
            //HIGHESTY;
            if (yu1 > highestY) {
                highestY = yu1;
            }
            if (yu2 > highestY) {
                highestY = yu2;
            }
            if (yu3 > highestY) {
                highestY = yu3;
            }
            if (yu4 > highestY) {
                highestY = yu4;
            }
            if (yl1 > highestY) {
                highestY = yl1;
            }
            if (yl2 > highestY) {
                highestY = yl2;
            }
            if (yl3 > highestY) {
                highestY = yl3;
            }
            if (yl4 > highestY) {
                highestY = yl4;
            }
            //LOWESTY
            if (yu1 < lowestY) {
                lowestY = yu1;
            }
            if (yu2 < lowestY) {
                lowestY = yu2;
            }
            if (yu3 < lowestY) {
                lowestY = yu3;
            }
            if (yu4 < lowestY) {
                lowestY = yu4;
            }
            if (yl1 < lowestY) {
                lowestY = yl1;
            }
            if (yl2 < lowestY) {
                lowestY = yl2;
            }
            if (yl3 < lowestY) {
                lowestY = yl3;
            }
            if (yl4 < lowestY) {
                lowestY = yl4;
            }
            return new float[]{highestX, highestY, lowestX, lowestY};
        }
        return new float[]{0, 0, 0, 0};
    }

    public float getHighestX(Entity entity) {
        return this.getBoundingBoxes(entity)[0];
    }

    public float getLowestX(Entity entity) {
        return this.getBoundingBoxes(entity)[2];
    }

    public float getHighestY(Entity entity) {
        return this.getBoundingBoxes(entity)[1];
    }

    public float getLowestY(Entity entity) {
        return this.getBoundingBoxes(entity)[3];
    }



    public void convert2d(Entity e, double posX, double posY, double posZ) {
        if (e == Minecraft.getMinecraft().thePlayer) {
            return;
        }
        //TODO UPPER POSITIONS
        float[] upcoords1 = this.getScreenCoords(posX + e.width / 1.9, posY + e.height + 0.1, posZ - e.width / 1.9);
        float[] upcoords2 = this.getScreenCoords(posX + e.width / 1.9, posY + e.height + 0.1, posZ + e.width / 1.9);
        float[] upcoords3 = this.getScreenCoords(posX - e.width / 1.9, posY + e.height + 0.1, posZ - e.width / 1.9);
        float[] upcoords4 = this.getScreenCoords(posX - e.width / 1.9, posY + e.height + 0.1, posZ + e.width / 1.9);
        upperPos1.put(e, upcoords1);
        upperPos2.put(e, upcoords2);
        upperPos3.put(e, upcoords3);
        upperPos4.put(e, upcoords4);
        //TODO LOWER POSITIONS
        float[] lowcoords1 = this.getScreenCoords(posX + e.width / 1.9, posY, posZ - e.width / 1.9);
        float[] lowcoords2 = this.getScreenCoords(posX + e.width / 1.9, posY, posZ + e.width / 1.9);
        float[] lowcoords3 = this.getScreenCoords(posX - e.width / 1.9, posY, posZ - e.width / 1.9);
        float[] lowcoords4 = this.getScreenCoords(posX - e.width / 1.9, posY, posZ + e.width / 1.9);
        lowerPos1.put(e, lowcoords1);
        lowerPos2.put(e, lowcoords2);
        lowerPos3.put(e, lowcoords3);
        lowerPos4.put(e, lowcoords4);
        // }
    }

    public float[] getScreenCoords(double x, double y, double z) {
        FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelView);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);
        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
        boolean result = GLU.gluProject((float) x, (float) y, (float) z, modelView, projection, viewport, screenCoords);
        if (result) {
            return new float[]{screenCoords.get(0) / sr.getScaleFactor(), sr.getScaledHeight() - screenCoords.get(1) / sr.getScaleFactor(), screenCoords.get(2)};
        }
        return null;
    }
}
