export async function getAllMusics() 
{
    const response = await fetch('http://localhost:8080/spotiHome/webresources/music/getMusics/');     

    if (response.ok === true) {
        return response.json();
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function getAllMusicByPlayListId(id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/getMusicsByPlaylistId/${id}`);     

    if (response.ok === true) {
        return response.json();
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function addMusic(fileName, id_administrateur, label_musique, label_auteur, id_type, id_genre) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/addMusic/${fileName}/${id_administrateur}/${label_musique}/${label_auteur}/${id_type}/${id_genre}/`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function uploadMusicFile(file) 
{

    const formData = new FormData();
    formData.append('file', file);


    const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/upload/`, 
    {
        method: 'POST',
        body: formData
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

