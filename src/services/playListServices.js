export async function getPlayListById(id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/getPlayListByUserId/${id}`, {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response.json();
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la récupération des playlists : " + error.message);
        throw error;
    }
}

export async function deletePlayListById(id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/deletePlayList/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la suppression de la playlist : " + error.message);
        throw error;
    }
}

export async function addMusicToPlaylist(id_music, id_playlist) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/addMusicToPlaylist/${id_music}/playList/${id_playlist}/`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de l’ajout de la musique à la playlist : " + "La musique que vous shouaitez ajouter se trouve déja dans la playlist");
        throw error;
    }
}

export async function addPlaylist(label, id_auditor) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/addPlayList/${label}/auditor/${id_auditor}/`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la création de la playlist : " + error.message);
        throw error;
    }
}

export async function updatePlaylist(label, id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/updatePlaylist/${label}/playlist/${id}`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la mise à jour de la playlist : " + error.message);
        throw error;
    }
}