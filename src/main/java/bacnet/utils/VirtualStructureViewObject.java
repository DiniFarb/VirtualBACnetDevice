package bacnet.utils;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.exception.BACnetServiceException;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;

public class VirtualStructureViewObject extends BACnetObject {

    public VirtualStructureViewObject(LocalDevice localDevice, ObjectIdentifier id, String name, CharacterString description) throws BACnetServiceException {
        super(localDevice, id, name);
        this.properties.put(PropertyIdentifier.description, description);
        localDevice.addObject(this);
    }
}
