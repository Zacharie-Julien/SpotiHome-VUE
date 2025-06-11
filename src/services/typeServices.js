export async function getAllType() {
    try {
        const response = await fetch('http://localhost:8080/spotiHome/webresources/type/getAllType');

        if (response.ok === true) {
            return response.json();
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la récupération des types : " + error.message);
        throw error;
    }
}

export async function deleteTypeById(id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/type/deleteType/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la suppression du type : " + error.message);
        throw error;
    }
}

export async function addType(label) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/type/addType/${label}`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de l’ajout d’un type : " + error.message);
        throw error;
    }
}

export async function upDateType(label, id) {
    try {
        const response = await fetch(`http://localhost:8080/spotiHome/webresources/type/updateType/${label}/type/${id}`, {
            method: 'POST',
            credentials: 'include'
        });

        if (response.ok === true) {
            return response;
        }

        throw new Error('Impossible de contacter le serveur');
    } catch (error) {
        alert("Erreur lors de la modification du type : " + error.message);
        throw error;
    }
}