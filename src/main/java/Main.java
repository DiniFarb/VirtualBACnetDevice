import bacnet.controller.BACnetController;

public class Main {

    public static void main(String[] args) {
        BACnetController bacnetController = new BACnetController();
        bacnetController.initializeDevice();
        bacnetController.createBinaryValueObject();
        bacnetController.createStructureObjects();
    }
}
