package com.codingforcookies.betterrecords.api.wire;

import com.codingforcookies.betterrecords.api.connection.RecordConnection;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

public interface IRecordWireHome {
    void addTreble(float form);
    void addBass(float form);

    ArrayList<RecordConnection> getConnections();
    void increaseAmount(IRecordWire wireComponent);
    void decreaseAmount(IRecordWire wireComponent);
    float getSongRadius();
    TileEntity getTileEntity();
    ItemStack getItemStack();
}
