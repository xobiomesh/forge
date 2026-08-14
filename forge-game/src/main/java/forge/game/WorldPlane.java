package forge.game;

/**
 * The World's shared plane state (Zenith / Horizon / Nadir). One value, shared by both
 * players — see docs/design/01-design-bible.md.
 */
public enum WorldPlane {
    ZENITH,
    HORIZON,
    NADIR;

    /**
     * Legal shift destinations from this plane. From Horizon, either neighbor; from an
     * extreme, only back to Horizon (P-A in docs/design/09-mechanics-brainstorm.md —
     * you cannot skip Horizon).
     */
    public WorldPlane[] legalShiftDestinations() {
        switch (this) {
            case ZENITH:
                return new WorldPlane[] { HORIZON };
            case NADIR:
                return new WorldPlane[] { HORIZON };
            case HORIZON:
            default:
                return new WorldPlane[] { ZENITH, NADIR };
        }
    }
}
