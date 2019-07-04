/**
 * @author Julia
 * This is a global singleton object that holds the state the capsule is currently in.
 * values of state are described in the CapsuleState enum:
 * @see CapsuleState
 * use like: CapsuleStateContainer.getInstance().getState().getString(), e.g. in gotTo(...)
 */

class CapsuleStateContainer {
    // singleton

    private CapsuleState state;
    private static CapsuleStateContainer instance;

    private CapsuleStateContainer() { state = CapsuleState.FREE; }

    /**
     *  getInstance method as in the Singleton pattern. Guarantees that there is only one instance of this class,
     *  by either returning an already created instance or making a new one.
     * @return the only instance of CapsuleStateContainer
     */
    static CapsuleStateContainer getInstance () {
        if (CapsuleStateContainer.instance == null) {
            CapsuleStateContainer.instance = new CapsuleStateContainer();
        }
        return CapsuleStateContainer.instance;
    }

    CapsuleState getState() { return state; }

    public void setState(CapsuleState newState) {
        state = newState;
    }
}
