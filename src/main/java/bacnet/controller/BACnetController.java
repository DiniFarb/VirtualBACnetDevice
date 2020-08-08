package bacnet.controller;

import bacnet.utils.Device;

public class BACnetController {
    Device device;
    int deviceId = 1234568;


    public BACnetController(Device device) {
        this.device = device;
    }
    
}

