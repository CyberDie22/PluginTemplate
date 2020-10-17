package cyberdie22.template;

public enum ItemType {
    ITEM,
    PET_ITEM,
    WAND,
    SWORD,
    BOW,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,
    FISHING_ROD,
    COSMETIC,
    TRAVEL_SCROLL,
    PICKAXE,
    AXE,
    SHOVEL,
    HOE;

    public static Boolean isWeapon(ItemType type) {
        return type.equals(ItemType.SWORD) || type.equals(ItemType.BOW);
    }

    public static Boolean isArmor(ItemType type) {
        return type.equals(ItemType.HELMET) || type.equals(ItemType.CHESTPLATE) || type.equals(ItemType.LEGGINGS) || type.equals(ItemType.BOOTS);
    }

}
