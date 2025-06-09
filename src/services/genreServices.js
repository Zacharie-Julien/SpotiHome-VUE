export async function getAllGenre() 
{
    const response = await fetch('http://localhost:8080/spotiHome/webresources/genre/getAllGenre');     

    if (response.ok === true) {
        return response.json();
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function deleteGenreById(id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/genre/deleteGenre/${id}`, 
    {
        method: 'DELETE',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function addGenre(label) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/genre/addGenre/${label}`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function upDateGenre(label, id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/genre/updateGenre/${label}/genre/${id}`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}