package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    DRINKING,
    RESTING,
    WANDERING,
    CHASING,
    ENRAGE,
    DEAD,
    TAKEN,
    RESET,
    CHARGING,
    HAS_DYING_MESSAGE,
    ACTIVATING_SKILL,
    STUNNED,
    FULLY_CHARGED,
    ENEMY,
    WEAK_AGAINST_STORM_RULER,
    DISARMED,
    REVIVED,
    EMBER_FORM,
    LIT
}
