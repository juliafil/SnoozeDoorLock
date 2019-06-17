/**
 * @author Julia
 * this enumeration provides all valid capsule states and corresponding strings
 * getString() returns the string of the current state
 * used in:
 * @see CapsuleStateContainer
 */


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