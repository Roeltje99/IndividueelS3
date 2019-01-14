using Assets.Scripts.UI.Models;
using Proyecto26;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class ApiLobbyHandler : MonoBehaviour
{
    [SerializeField]
    private string UrlPath = "http://localhost:8888/";
    [SerializeField]
    private InputField LobbyNameBox;
    [SerializeField]
    private Text ExeptionText;

    public void CreateLobby()
    {
        if (LobbyNameBox.text.Trim() == string.Empty)
        {
            ExeptionText.text = "Fill in all the required fields";
        }
        else
        {
            RestClient.DefaultRequestHeaders["Authorization"] = "Bearer " + PlayerPrefs.GetString("token");
            LobbyModel model = new LobbyModel();
            model.lobbyName = LobbyNameBox.text;
            Lobby responseJson;
            RestClient.Post(UrlPath, model).Then(response =>
            {
                if (response != null)
                {
                    responseJson = JsonUtility.FromJson<Lobby>(response.Text);
                    SceneManager.LoadScene("Game");
                }
            }); 
        }
    }
}
