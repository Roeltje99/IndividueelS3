using UnityEngine;
using System.Collections;
using WebSocketSharp;
using System;

public class ConnectionHandler : MonoBehaviour
{
    public string Host = "localhost";
    public float Port = 8080;
    public bool canSendMessage = true;

    public GameObject Player2;

    private WebSocket ws;

    private Vector3 lastPosition;

    private Vector3 PositionPlayer2;
    private Quaternion RotationPlayer2;

    private void Awake()
    {
        ws = new WebSocket("ws://" + Host + ":" + Port);
        ws.OnMessage += (sender, e) => ReceivedMessage(e.Data);
        ws.Connect();

        lastPosition = transform.position;
        PositionPlayer2 = Player2.transform.position;
        RotationPlayer2 = Player2.transform.rotation;
    }

    private void Update()
    {
        if (canSendMessage && transform.position != lastPosition)
        {
            lastPosition = transform.position;
            Vector3 position = transform.position;
            //Quaternion rotation = transform.rotation;

            //string message = string.Format("{0},{1},{2},{3},{4},{5}", position.x, position.y, position.z, rotation.x, rotation.y, rotation.z);
            string message = string.Format("{0},{1},{2}", position.x, position.y, position.z);

            ws.Send(message);
        }
    }

    private void FixedUpdate()
    {
        if (Player2.transform.position != PositionPlayer2)
        {
            Player2.transform.position = PositionPlayer2;
            Player2.transform.rotation = RotationPlayer2;
        }
    }

    private void ReceivedMessage(string message)
    {
        string[] messageSplit = message.Split(':');
        float[] splitMessage = Array.ConvertAll(messageSplit[1].Split(','), float.Parse);
        PositionPlayer2 = new Vector3(splitMessage[0], splitMessage[1], splitMessage[2]);
        RotationPlayer2 = Quaternion.Euler(splitMessage[3], splitMessage[4], splitMessage[5]);
    }
}
