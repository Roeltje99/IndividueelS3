using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using WebSocketSharp;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        private WebSocket client;
        const string host = "ws://localhost:8080";

        public Form1()
        {
            InitializeComponent();

            client = new WebSocket(host);

            client.OnOpen += (ss, ee) =>
               listBox1.Items.Add(string.Format("Connected to {0} successfully", host));
            client.OnError += (ss, ee) =>
               listBox1.Items.Add("     Error: " +ee.Message);
            client.OnMessage += (ss, ee) =>
               listBox1.Items.Add("Echo: " + ee.Data);
            client.OnClose += (ss, ee) =>
               listBox1.Items.Add(string.Format("Disconnected with {0}", host));
        }

        private void SendButton_Click(object sender, EventArgs e)
        {
            var content = inputBox.Text;
            if (!string.IsNullOrEmpty(content))
                client.Send(content);
        }

        private void ConnectButton_Click(object sender, EventArgs e)
        {
            client.Connect();
        }

        private void DisconnectButton_Click(object sender, EventArgs e)
        {
            client.Close();
        }
    }
}
