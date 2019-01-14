using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Assets.Scripts.UI.Models
{
    class Lobby
    {
        int lobbyId = -1;
        string lobbyName = string.Empty;
        List<User> users = new List<User>();
    }
}
