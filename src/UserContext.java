/**
 * Represents runtime context and capabilities of a user during a transaction.
 *
 * This includes dynamic attributes that can influence payment recommendations,
 * such as device capabilities (e.g., UPI enabled), network conditions, or
 * platform-specific constraints.
 *
 * Unlike the User entity, which contains persistent data (e.g., saved payment instruments),
 * UserContext captures request-time information and should be passed separately
 * to the recommendation engine.
 *
 * Example:
 * - A user may have UPI configured (User),
 *   but UPI may not be available on the current device/session (UserContext).
 */
public class UserContext {
    private boolean isUPIEnabled;
    private boolean isNetBankingEnabled;

    public UserContext(boolean isUPIEnabled, boolean isNetBankingEnabled){
        this.isUPIEnabled = isUPIEnabled;
        this.isNetBankingEnabled = isNetBankingEnabled;
    }

    private String deviceType; // MOBILE, WEB (optional)

}
