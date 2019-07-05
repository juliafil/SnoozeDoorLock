/**
 * @author Ertel
 * Interface holds paths for easy acces in case they need to be changed
 */
public interface config {
    String pythonPath = "python"; // to run the script(s) as python3 set to "python3", to run as python 2 type "python"
    String sensorScriptPath = "home/pi/Mob_app_ex/Open_Close_Buzzer.py"; // path to the script which checks for sensor input
    String openScriptPath = "home/pi/Mob_App_ex/Elock.py"; // path to the script which opens the door
}
