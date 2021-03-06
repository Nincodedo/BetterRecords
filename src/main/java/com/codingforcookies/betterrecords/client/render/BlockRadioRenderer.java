package com.codingforcookies.betterrecords.client.render;

import com.codingforcookies.betterrecords.api.connection.RecordConnection;
import com.codingforcookies.betterrecords.api.wire.IRecordWireManipulator;
import com.codingforcookies.betterrecords.client.model.ModelRadio;
import com.codingforcookies.betterrecords.common.BetterRecords;
import com.codingforcookies.betterrecords.common.block.tile.TileEntityRadio;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Map.Entry;

public class BlockRadioRenderer extends TileEntitySpecialRenderer<TileEntityRadio> {

    private static final ModelRadio MODEL = new ModelRadio();
    private static final ResourceLocation TEXTURE = new ResourceLocation(BetterRecords.ID, "textures/models/radio.png");

    @Override
    public void renderTileEntityAt(TileEntityRadio te, double x, double y, double z, float scale, int destroyStage) {
        if(te == null) {
            GL11.glPushMatrix();
            {
                GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
                GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                //GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
                bindTexture(TEXTURE);
                MODEL.render(null, 0, 0, 0F, 0.0F, 0.0F, 0.0625F, null);
            }
            GL11.glPopMatrix();
            return;
        }

        if(Minecraft.getMinecraft().player.getHeldItemMainhand() != null && Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof IRecordWireManipulator) {
            GL11.glPushMatrix();
            {
                GL11.glTranslatef((float)x + .5F, (float)y + .5F, (float)z + .5F);

                if(te.getConnections().size() != 0) {
                    GL11.glColor3f(0F, 0F, 0F);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);

                    GL11.glLineWidth(2F);
                    for(RecordConnection rec : te.getConnections()) {
                        int x1 = -(te.getPos().getX() - rec.x2);
                        int y1 = -(te.getPos().getY() - rec.y2);
                        int z1 = -(te.getPos().getZ() - rec.z2);
                        GL11.glPushMatrix();
                        {
                            GL11.glBegin(GL11.GL_LINE_STRIP);
                            {
                                GL11.glVertex3f(0F, 0F, 0F);
                                GL11.glVertex3f(x1, y1, z1);
                            }
                            GL11.glEnd();
                        }
                        GL11.glPopMatrix();
                    }

                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glColor3f(1F, 1F, 1F);
                }

                GL11.glScalef(.01F, -.01F, .01F);
                GL11.glRotatef(-Minecraft.getMinecraft().getRenderManager().playerViewY - 180F, 0F, 1F, 0F);

                GL11.glColor3f(1F, 1F, 1F);
                int currentY = te.wireSystemInfo.size() * -10 - 75;
                FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
                fontRenderer.drawString("Play Radius: " + te.getSongRadius(), -fontRenderer.getStringWidth("Play Radius: " + te.getSongRadius()) / 2, currentY, 0xFFFFFF);
                for(Entry<String, Integer> nfo : te.wireSystemInfo.entrySet()) {
                    currentY += 10;
                    fontRenderer.drawString(nfo.getValue() + "x " + nfo.getKey(), -fontRenderer.getStringWidth(nfo.getValue() + "x " + nfo.getKey()) / 2, currentY, 0xFFFFFF);
                }
            }
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        {
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(te.getBlockMetadata() * 90 + 180, 0.0F, 1.0F, 0.0F);
            bindTexture(TEXTURE);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
            MODEL.render(null, te.openAmount, te.crystalFloaty, 0F, 0.0F, 0.0F, 0.0625F, te.crystal);
        }
        GL11.glPopMatrix();
    }
}
