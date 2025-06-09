export async function getPlayListById(id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/getPlayListByUserId/${id}`,
        {
        method: 'GET',
        credentials: 'include'
    });   

    if (response.ok === true) {
        return response.json();
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function deletePlayListById(id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/deletePlayList/${id}`, 
    {
        method: 'DELETE',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function addMusicToPlaylist(id_music, id_playlist) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/addMusicToPlaylist/${id_music}/playList/${id_playlist}/`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function addPlaylist(label, id_auditor) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/addPlayList/${label}/auditor/${id_auditor}/`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function updatePlaylist(label, id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/playlist/updatePlaylist/${label}/playlist/${id}`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}