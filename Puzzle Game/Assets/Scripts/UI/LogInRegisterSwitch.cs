using UnityEngine;

public class LogInRegisterSwitch : MonoBehaviour
{
    [SerializeField]
    private GameObject LogInField;
    [SerializeField]
    private GameObject RegisterField;

    public void SwitchField()
    {
        if (LogInField.active)
        {
            LogInField.SetActive(false);
            RegisterField.SetActive(true);
        }
        else
        {
            RegisterField.SetActive(false);
            LogInField.SetActive(true);
        }
    }
}
