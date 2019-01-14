using Assets.Scripts.UI.Models;
using Proyecto26;
using UnityEngine;
using UnityEngine.UI;
using JWT.Builder;
using Newtonsoft.Json.Linq;
using Assets.Scripts.UI.Wrapper;
using UnityEngine.SceneManagement;

public class ApiUserHandler : MonoBehaviour
{
    [SerializeField]
    private string UrlPath = "http://localhost:8888/";
    [SerializeField]
    private Text ExeptionText;
    [SerializeField]
    private InputField EmailBox;
    [SerializeField]
    private InputField PasswordBox;

    public void LogIn()
    {
        if (EmailBox.text.Trim() == string.Empty || PasswordBox.text.Trim() == string.Empty)
        {
            ExeptionText.text = "Fill in all the required fields";
        }
        else
        {
            string token = string.Empty;

            UserModel model = new UserModel();
            model.email = EmailBox.text;
            model.password = PasswordBox.text;
            RestClient.Post(UrlPath + "login", model).Then(response =>
            {
                if (response == null)
                {
                    ExeptionText.text = "Username and/or Password incorrect";
                }
                else
                {
                    ExeptionText.text = string.Empty;
                    token = GetToken(response.Text);
                    PlayerPrefs.SetString("token", token);
                    SceneManager.LoadScene("CreateLobby");
                }
            });
        }
    }

    public void Register()
    {
        if (EmailBox.text.Trim() == string.Empty || PasswordBox.text.Trim() == string.Empty)
        {
            ExeptionText.text = "Fill in all the required fields";
        }
        else
        {
            UserModel model = new UserModel();
            model.email = EmailBox.text;
            model.password = PasswordBox.text;
            RestClient.Post(UrlPath + "user/register", model).Then(response =>
            {
                if (response == null)
                {
                    ExeptionText.text = "A user with that email already exists";
                }
            });
        }
    }

    private string GetToken(string rawToken)
    {
        string token = rawToken.Substring(10, rawToken.Length - 12);
        return token;
    }
}