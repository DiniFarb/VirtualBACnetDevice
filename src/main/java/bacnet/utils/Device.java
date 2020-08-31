package bacnet.utils;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.transport.Transport;

public class Device extends LocalDevice {

    public Device(int deviceNumber, Transport transport) {
        super(deviceNumber,transport);

    }

}
