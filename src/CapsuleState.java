public enum CapsuleState
    {
        FREE("free"),
        IN_USE("inUse"),
        DOOR_OPEN("doorOpen"),
        ERROR("error");

        private String stateString;

        private CapsuleState(String stateString) {
            this.stateString = stateString;
        }

        public String getString() {
            return stateString;
        }
    }