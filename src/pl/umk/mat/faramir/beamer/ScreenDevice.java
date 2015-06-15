/*
 * Copyright (c) 2015, Marek Nowicki
 * All rights reserved.
 *
 * This file is distributable under the Simplified BSD license. See the terms
 * of the Simplified BSD license in the documentation provided with this file.
 */
package pl.umk.mat.faramir.beamer;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;

/**
 *
 * @author faramir
 */
public class ScreenDevice {

    final private GraphicsDevice device;

    public ScreenDevice(GraphicsDevice device) {
        this.device = device;
    }

    @Override
    public String toString() {
        if (device == null) {
            return "- nie wyświetlaj -";
        }
        DisplayMode displayMode = getDevice().getDisplayMode();
        return String.format("%s %dx%dx%d@%d", getDevice().getIDstring(), displayMode.getWidth(), displayMode.getHeight(), displayMode.getBitDepth(), displayMode.getRefreshRate());
    }

    /**
     * @return the device
     */
    public GraphicsDevice getDevice() {
        return device;
    }

}
