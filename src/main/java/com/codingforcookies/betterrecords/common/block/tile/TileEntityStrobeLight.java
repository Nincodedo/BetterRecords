package com.codingforcookies.betterrecords.common.block.tile;

import com.codingforcookies.betterrecords.api.connection.RecordConnection;
import com.codingforcookies.betterrecords.api.record.IRecordAmplitude;
import com.codingforcookies.betterrecords.api.wire.IRecordWire;
import com.codingforcookies.betterrecords.common.core.helper.ConnectionHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

import java.util.ArrayList;

public class TileEntityStrobeLight extends BetterTile implements IRecordWire, IRecordAmplitude, ITickable {
    public ArrayList<RecordConnection> connections = null;
    public ArrayList<RecordConnection> getConnections() { return connections; }

    public float bass = 0;

    public void setTreble(float amplitude) { }
    public void setTreble(float amplitude, float r, float g, float b) { }
    public float getTreble() { return 0; }

    public void setBass(float amplitude) { this.bass = (amplitude < 8F ? 0F : amplitude); }
    public void setBass(float amplitude, float r, float g, float b) { this.bass = (amplitude < 5F ? 0F : amplitude); }
    public float getBass() { return bass; }

    public String getName() { return "Strobe Light"; }
    public float getSongRadiusIncrease() { return 0F; }

    public TileEntityStrobeLight() {
        connections = new ArrayList<RecordConnection>();
    }

    @Override
    public void update() {
        if(bass > 0F)
            bass--;
        if(bass < 0F)
            bass = 0F;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if(compound.hasKey("connections"))
            connections = ConnectionHelper.unserializeConnections(compound.getString("connections"));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setString("connections", ConnectionHelper.serializeConnections(connections));

        return compound;
    }
}
