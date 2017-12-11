package exceptions;

import logic.Equipment;

public class EquipmentTypeNotKnownException extends Throwable {
    public EquipmentTypeNotKnownException(Equipment.Type type) {
        super(type.name());
    }
}
