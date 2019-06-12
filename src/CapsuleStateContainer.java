class CapsuleStateContainer {
    // singleton

    private CapsuleState state;
    private static CapsuleStateContainer instance;

    private CapsuleStateContainer() { state = CapsuleState.FREE; }

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
