class CapsuleStateManager {
    // singleton

    private CapsuleState state;
    private static CapsuleStateManager instance;

    private CapsuleStateManager () { state = CapsuleState.FREE; }

    static CapsuleStateManager getInstance () {
        if (CapsuleStateManager.instance == null) {
            CapsuleStateManager.instance = new CapsuleStateManager ();
        }
        return CapsuleStateManager.instance;
    }

    CapsuleState getState() { return state; }

    public void setState(CapsuleState newState) {
        state = newState;
    }
}
