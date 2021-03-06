package com.codingforcookies.betterrecords.client.render;

import com.codingforcookies.betterrecords.client.model.ModelRecordEtcher;
import com.codingforcookies.betterrecords.common.BetterRecords;
import com.codingforcookies.betterrecords.common.block.tile.TileEntityRecordEtcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BlockRecordEtcherRenderer extends TileEntitySpecialRenderer<TileEntityRecordEtcher> {

    private static final ModelRecordEtcher MODEL = new ModelRecordEtcher();
    private static final ResourceLocation TEXTURE = new ResourceLocation(BetterRecords.ID, "textures/models/recordetcher.png");

    @Override
    public void renderTileEntityAt(TileEntityRecordEtcher te, double x, double y, double z, float scale, int destroyStage) {
        if(te == null) {
            GL11.glPushMatrix();
            {
                GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
                GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                bindTexture(TEXTURE);
                MODEL.render(null, 0, 0, 0F, 0.0F, 0.0F, 0.0625F);
            }
            GL11.glPopMatrix();
            return;
        }

        if(te.recordEntity != null) {
            GL11.glPushMatrix();
            {
                GL11.glTranslatef((float)x + .5F, (float)y + .65F, (float)z + .5F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(te.recordRotation * 57.3F, 0F, 0F, 1F);
                if(Minecraft.getMinecraft().gameSettings.fancyGraphics)
                    Minecraft.getMinecraft().getRenderItem().renderItem(te.recordEntity.getEntityItem(), ItemCameraTransforms.TransformType.NONE);
                else{
                    Minecraft.getMinecraft().gameSettings.fancyGraphics = true;
                    Minecraft.getMinecraft().getRenderItem().renderItem(te.recordEntity.getEntityItem(), ItemCameraTransforms.TransformType.NONE);
                    Minecraft.getMinecraft().gameSettings.fancyGraphics = false;
                }
            }
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        {
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            bindTexture(TEXTURE);
            MODEL.render(null, te.needleLocation, te.recordRotation, 0F, 0.0F, 0.0F, 0.0625F);
        }
        GL11.glPopMatrix();
    }
}
