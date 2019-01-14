using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Assets.Scripts.UI.Models
{
    class User
    {
        private int userId = -1;
        private string email = string.Empty;
        private string password = string.Empty;
        private string token = string.Empty;
        private int lobbyId = -1;

        public User(int userId, string email, string password, int lobbyId)
        {
            this.userId = userId;
            this.email = email;
            this.password = password;
            this.lobbyId = lobbyId;
        }

        public User(string email, string password)
        {
            this.email = email;
            this.password = password;
        }

        public int UserId { get { return userId; } set { userId = value; } }

        public string Email { get { return email; } set { email = value; } }

        public string Password { get { return password; } set { password = value; } }

        public string Token { get { return token; } set { token = value; } }

        public int LobbyId { get { return lobbyId; } set { lobbyId = value; } }
    }
}
