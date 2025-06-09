export async function getAllType() 
{
    const response = await fetch('http://localhost:8080/spotiHome/webresources/type/getAllType');     

    if (response.ok === true) {
        return response.json();
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function deleteTypeById(id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/type/deleteType/${id}`, 
    {
        method: 'DELETE',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function addType(label) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/type/addType/${label}`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}

export async function upDateType(label, id) 
{
    const response = await fetch(`http://localhost:8080/spotiHome/webresources/type/updateType/${label}/type/${id}`, 
    {
        method: 'POST',
        credentials: 'include'
    });     

    if (response.ok === true) {
        return response;
    }
    
    throw new Error('Impossible de contacter le serveur');        
}