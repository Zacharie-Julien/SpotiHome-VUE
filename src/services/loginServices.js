export async function loginUser(login, password) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/auth/login/${login}/passwd/${password}`, {
            method: 'GET',
            credentials: 'include'
        });

        if (response.status === 200) {
            const jwt = document.cookie.split('JWT=')[1];
            if (!jwt) throw new Error("Token JWT introuvable");

            const role = JSON.parse(atob(jwt.split('.')[1])).role;

            if (role === 'admin') {
                window.location.replace('http://localhost:5173/homeAdmin');
            } else {
                window.location.replace('http://localhost:5173/home');
            }
        } else if (response.status === 401) {
            alert("Identifiants invalides. Veuillez réessayer.");
        } else {
            alert("Identifiant ou mot de passe invalide");
        }
    } catch (error) {
        alert("Erreur lors de la tentative de connexion : " + error.message);
        throw error;
    }
}