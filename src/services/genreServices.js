export async function getAllGenre() {
    try {
        const response = await fetch('http://localhost:8080/spotiHome/webresources/genre/getAllGenre');

        if (response.ok === true) {
            return response.json();
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la récupération des genres : " + error.message);
        throw error;
    }
}

export async function deleteGenreById(id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/genre/deleteGenre/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la suppression du genre : " + error.message);
        throw error;
    }
}

export async function addGenre(label) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/genre/addGenre/${label}`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de l’ajout d’un genre : " + error.message);
        throw error;
    }
}

export async function upDateGenre(label, id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/genre/updateGenre/${label}/genre/${id}`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la modification du genre : " + error.message);
        throw error;
    }
}