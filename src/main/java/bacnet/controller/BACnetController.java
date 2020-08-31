package bacnet.controller;

import bacnet.utils.Device;
import bacnet.utils.VirtualBinaryValueObject;
import bacnet.utils.VirtualStructureViewObject;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.npdu.ip.IpNetworkBuilder;
import com.serotonin.bacnet4j.transport.DefaultTransport;
import com.serotonin.bacnet4j.type.constructed.EventTransitionBits;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.NotifyType;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BACnetController {
    static final Logger LOG = LoggerFactory.getLogger(BACnetController.class);
    private final Device device;
    private final int deviceId = 1234568;
    private final int bacPort = 47808;



    public BACnetController() {
        this.device = new Device(deviceId, createNetwork());
    }

    public void initializeDevice(){
        try {
            this.device.initialize();
        } catch (Exception e) {
            LOG.error("Device could not be initialize.. stack: ", e.fillInStackTrace());
        }
    }

    private DefaultTransport createNetwork() {
        LOG.debug("Create Network on port: {}", bacPort);
        IpNetworkBuilder ipNetworkBuilder = new IpNetworkBuilder();
        ipNetworkBuilder.withLocalBindAddress(IpNetwork.DEFAULT_BIND_IP);
        ipNetworkBuilder.withBroadcast("255.255.255.255", IpNetwork.BVLC_TYPE);
        ipNetworkBuilder.withPort(this.bacPort);
        return new DefaultTransport(ipNetworkBuilder.build());
    }

    public void createBinaryValueObject(){
        try {
            LOG.debug("Create Binary Value Object");
            VirtualBinaryValueObject binaryValue = new VirtualBinaryValueObject(device,1,
                    "B1'E'BV01",BinaryPV.inactive,false,new CharacterString("Test Alarm 1"));
            binaryValue.supportStateText("Normal","Alarm");
            binaryValue.supportCovReporting();
            binaryValue.supportActiveTime();
            binaryValue.supportWritable();
            binaryValue.supportIntrinsicReporting(
                    0,
                    12,
                    BinaryPV.active,
                    new EventTransitionBits(true,true,true),
                    NotifyType.alarm,
                    0);
        } catch (BACnetServiceException e) {
            e.printStackTrace();
        }
    }

    public void createStructureObjects(){
        try {
        LOG.debug("Create Structure View Objects");
        new VirtualStructureViewObject(device, new ObjectIdentifier(ObjectType.structuredView,1),"B1", new CharacterString("Gebäude 1"));
        new VirtualStructureViewObject(device, new ObjectIdentifier(ObjectType.structuredView,2),"B1'E", new CharacterString("Elektro"));
    } catch (BACnetServiceException e) {
        e.printStackTrace();
    }
    }


    
}

