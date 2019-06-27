/**
 * @author Ertel
 * Interface holds paths for easy acces in case they need to be changed
 */
public interface config {
    String pythonPath = "python"; // to run the script(s) as python3 set to "python3", to run as python 2 type "python"
    String sensorScriptPath = "/etc/Snooze/sensor.py"; // path to the script which checks for sensor input
    String openScriptPath = "/etc/Snooze/open.py"; // path to the script which opens the door
}
