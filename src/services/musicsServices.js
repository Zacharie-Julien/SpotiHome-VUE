export async function getAllMusics() {
    try {
        const response = await fetch('http://localhost:8080/spotiHome/webresources/music/getMusics/', {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response.json();
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la récupération des musiques : " + error.message);
        throw error;
    }
}

export async function getAllMusicByPlayListId(id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/getMusicsByPlaylistId/${id}`, {
            method: 'GET',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response.json();
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la récupération des musiques de la playlist : " + error.message);
        throw error;
    }
}

export async function addMusic(fileName, id_administrateur, label_musique, label_auteur, id_type, id_genre) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/addMusic/${fileName}/${id_administrateur}/${label_musique}/${label_auteur}/${id_type}/${id_genre}/`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de l’ajout d’une musique : " + error.message);
        throw error;
    }
}

export async function uploadMusicFile(file) {
    try {
        const formData = new FormData();
        formData.append('file', file);

        const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/upload/`, {
            method: 'POST',
            body: formData
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de l’upload du fichier musique : " + error.message);
        throw error;
    }
}


export async function deleteMusic(id) {
    try {

        const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/deleteMusic/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la suppression de la  musique : " + error.message);
        throw error;
    }
}

export async function getSharedMusic(id_auditeur, id_musique, dateShare) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/readShare/${id_auditeur}/${id_musique}/${dateShare}`, {
            method: 'GET'
        });

        if (response.ok === true) {
            return response.text();            
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la suppression de la  musique : " + error.message);
        throw error;
    }
}

export async function addSharedMusic(id_auditeur, id_musique) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/share/${id_auditeur}/${id_musique}`, {
            method: 'POST'
        });

        if (response.ok === true) {
            return response.text();            
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la suppression de la  musique : " + error.message);
        throw error;
    }
}

export async function updateMusic(idMusic, auteur, name, idGenre, idType) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/updateMsuic/${idMusic}/${auteur}/${name}/${idGenre}/${idType}`, {
            method: 'POST'
        });

        if (response.ok === true) {
            return response            
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la suppression de la  musique : " + error.message);
        throw error;
    }
}