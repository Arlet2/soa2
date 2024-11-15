package su.arlet.soa2.core;

public enum Weapon {
    HEAVY_BOLTGUN ("heavyBoltgun"),
    GRENADE_LAUNCHER ("grenadeLauncher"),
    MULTI_MELTA ("multiMelta"),;

    private String weaponName;

    Weapon(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getValue() {
        return weaponName;
    }
}
